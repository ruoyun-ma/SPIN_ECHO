//---------------------------------------------------------------------
//
//                 SPIN ECHO PSD V5.8
//
// ---------------------------------------------------------------------
//  last modification:
//  V7.8 - 01-02-07 modif Sequence time when KS_CENTER_MODE
//  V7.8 - 17-02-07
//  V7.7 - 20-11
//  V7.5 - 20-11
//		new calculation for ETL ZeroFilling in FSE
//   	update preScan SEQUENCE_TIME
//  V7.4 - 17-11 sequenceVersion  && SWITCH_READ_PHASE
///  V7.3 - 2-11 bugs memory
//  V7.2 - 30-11
//       KS_CENTER_MODE Frequency_offset_init
//  V7 - 31-07
//	delete min_flush_delay and replace all call with  min_instruction_delay
//      Loop A loop B
//      rename all the "USER_PARAM" rs2d.sequence.spinecho.SPIN_ECHO_devParams.USER_PARAM
//      remove off_center_distance_2D and off_center_distance_3D in order to do it in the process
// 	    bug  freqoffset_tx_prep_90 190 IR Order.ThreeLoop
//
//  V6
//		SPECIMEN -> SUBJECT
//  V5.8
//		Delete nucleus.getRatio() dans : ((GradientMath.GAMMA / nucleus.getRatio())
//  V5.7
// 		   Introduce Gradient Delay on  90deg 180deg and ADC event
//           getUnreachParamExceptionManager().addParam(rs2d.sequence.spinecho.SPIN_ECHO_devParams.GRADIENT_RISE_TIME.name(), grad_rise_time, new_grad_rise_time,((NumberParam) getParam(rs2d.sequence.spinecho.SPIN_ECHO_devParams.GRADIENT_RISE_TIME")).getMaxValue(), "Gradient ramp time too short ");
//  V5.6
//  double min_instruction_delay = 0.000005;

package rs2d.sequence.spinecho;

import rs2d.commons.log.Log;
import rs2d.sequence.common.Gradient;
import rs2d.sequence.common.RFPulse;
import rs2d.sequence.common.HardwareShim;
import rs2d.sequence.common.HardwarePreemphasis;
import rs2d.spinlab.data.transformPlugin.TransformPlugin;
import rs2d.spinlab.hardware.controller.HardwareHandler;
import rs2d.spinlab.instrument.Instrument;
import rs2d.spinlab.instrument.util.GradientMath;
import rs2d.spinlab.sequence.element.TimeElement;
import rs2d.spinlab.sequence.table.Shape;
import rs2d.spinlab.sequence.table.Table;
import rs2d.spinlab.sequence.table.Utility;
import rs2d.spinlab.sequenceGenerator.SequenceGeneratorAbstract;
import rs2d.spinlab.tools.param.*;
import rs2d.spinlab.tools.role.RoleEnum;
import rs2d.spinlab.tools.table.Order;
import rs2d.spinlab.tools.utility.GradientAxe;
import rs2d.spinlab.tools.utility.Nucleus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static rs2d.sequence.spinecho.SpinEchoParams.*;

import static rs2d.sequence.spinecho.SpinEchoSequenceParams.*;


// **************************************************************************************************
// *************************************** SEQUENCE GENERATOR ***************************************
// **************************************************************************************************
//
public class SpinEcho extends SequenceGeneratorAbstract {
    private String sequenceVersion = "Version7.10";
    private double protonFrequency;
    private double observeFrequency;
    private double min_time_per_acq_point;
    private double gMax;
    private TransformPlugin plugin;
    private Nucleus nucleus;

    private boolean isMultiplanar;

    private int number_of_averages;

    private int acquisitionMatrixDimension1D;
    private int acquisitionMatrixDimension2D;
    private int acquisitionMatrixDimension3D;
    private int acquisitionMatrixDimension4D;
    private int preScan;

    private int userMatrixDimension1D;
    private int userMatrixDimension2D;
    private int userMatrixDimension3D;

    private int nb_scan_1d;
    private int nb_scan_2d;
    private int nb_scan_3d;
    private int nb_scan_4d;
    private int echoTrainLength;
    int nbOfInterleavedSlice;

    private double spectralWidth;
    private boolean isSW;
    private double tr;
    private double te;
    private double echo_spacing;

    private double sliceThickness;
    private double pixelDimension;
    private double fov;
    private double fovPhase;
    private boolean isFovDoubled;
    private double off_center_distance_1D;
    private double off_center_distance_2D;
    private double off_center_distance_3D;

    private double txLength90;
    private double txLength180;

    private boolean isDynamic;
    private int numberOfDynamicAcquisition;

    private boolean isInversionRecovery;
    private ListNumberParam inversionRecoveryTime;
    private int numberOfInversionRecovery;

    private boolean isTrigger;
    private ListNumberParam triggerTime;
    private int numberOfTrigger;

    private boolean isKSCenterMode;

    private boolean isEnablePhase;
    private boolean isEnablePhase3D;
    private boolean isEnableSlice;
    private boolean isEnableRead;

    private double observation_time;

    // get hardware memory limit
    private int offset_channel_memory = 512;
    private int phase_channel_memory = 512;
    private int amp_channel_memory = 2048;
    private int loopIndice_memory = 2048;

    private double default_instruction_delay = 0.000010;     // single instruction minimal duration
    private double min_instruction_delay = 0.000005;     // single instruction minimal duration

    public SpinEcho() {
        super();
        initParam();
    }

    @Override
    public void init() {
        super.init();

        // Define default, min, max and suggested values regarding the instrument
        getParam(MAGNETIC_FIELD_STRENGTH).setDefaultValue(Instrument.instance().getDevices().getMagnet().getField());
        getParam(DIGITAL_FILTER_SHIFT).setDefaultValue(Instrument.instance().getDevices().getCameleon().getAcquDeadPointCount());
        getParam(DIGITAL_FILTER_REMOVED).setDefaultValue(Instrument.instance().getDevices().getCameleon().isRemoveAcquDeadPoint());

        List<String> tx_shape = asList(
                "HARD",
                "GAUSSIAN",
                "SINC3",
                "SINC5");
        ((TextParam) getParam(TX_SHAPE_90)).setSuggestedValues(tx_shape);
        ((TextParam) getParam(TX_SHAPE_90)).setRestrictedToSuggested(true);
        ((TextParam) getParam(TX_SHAPE_180)).setSuggestedValues(tx_shape);
        ((TextParam) getParam(TX_SHAPE_180)).setRestrictedToSuggested(true);

        //TRANSFORM PLUGIN
        List<String> list = asList("Centered2D",
                "Bordered2D",
                "Sequential4D",
                "Sequential2D");
        ((TextParam) this.getParamFromName(MriDefaultParams.TRANSFORM_PLUGIN.name())).setSuggestedValues(list);
        ((TextParam) this.getParamFromName(MriDefaultParams.TRANSFORM_PLUGIN.name())).setRestrictedToSuggested(true);

    }

    // ==============================
// -----   GENERATE
// ==============================
    public void generate() throws Exception {
        initUserParam();
        this.beforeRouting();
        if (!this.isRouted()) {
            this.route();
            this.initAfterRouting();//init before setup
        }
        //   if (!((BooleanParam) getParam( SETUP_MODE)).getValue()) {
        this.afterRouting();    //avoid exception during setup
        // }

        this.checkAndFireException();
    }

    private void initUserParam() {
        isMultiplanar = (((BooleanParam) getParam(MULTI_PLANAR_EXCITATION)).getValue());


//        acquisitionMatrixDimension1D = ((NumberParam) getParam(ACQUISITION_MATRIX_DIMENSION_1D)).getValue().intValue();
        acquisitionMatrixDimension2D = ((NumberParam) getParam(ACQUISITION_MATRIX_DIMENSION_2D)).getValue().intValue();
        acquisitionMatrixDimension3D = ((NumberParam) getParam(ACQUISITION_MATRIX_DIMENSION_3D)).getValue().intValue();
        acquisitionMatrixDimension4D = ((NumberParam) getParam(ACQUISITION_MATRIX_DIMENSION_4D)).getValue().intValue();
        preScan = ((NumberParam) getParam(DUMMY_SCAN)).getValue().intValue();

        number_of_averages = ((NumberParam) getParam(NUMBER_OF_AVERAGES)).getValue().intValue();
        userMatrixDimension1D = ((NumberParam) getParam(USER_MATRIX_DIMENSION_1D)).getValue().intValue();
        userMatrixDimension2D = ((NumberParam) getParam(USER_MATRIX_DIMENSION_2D)).getValue().intValue();
        userMatrixDimension3D = ((NumberParam) getParam(USER_MATRIX_DIMENSION_3D)).getValue().intValue();

        echoTrainLength = ((NumberParam) getParam(ECHO_TRAIN_LENGTH)).getValue().intValue();

        spectralWidth = ((NumberParam) getParam(SPECTRAL_WIDTH)).getValue().doubleValue();            // get user defined spectral width
        isSW = (((BooleanParam) getParam(SPECTRAL_WIDTH_OPT)).getValue());
        tr = ((NumberParam) getParam(REPETITION_TIME)).getValue().doubleValue();
        te = ((NumberParam) getParam(ECHO_TIME)).getValue().doubleValue();
        echo_spacing = ((NumberParam) getParam(ECHO_SPACING)).getValue().doubleValue();

        sliceThickness = ((NumberParam) getParam(SLICE_THICKNESS)).getValue().doubleValue();
        pixelDimension = ((NumberParam) getParam(RESOLUTION_FREQUENCY)).getValue().doubleValue();
        fov = ((NumberParam) getParam(FIELD_OF_VIEW)).getValue().doubleValue();
        fovPhase = ((NumberParam) getParam(FIELD_OF_VIEW_PHASE)).getValue().doubleValue();
        isFovDoubled = ((BooleanParam) getParam(FOV_DOUBLED)).getValue();
        off_center_distance_1D = ((NumberParam) getParam(OFF_CENTER_FIELD_OF_VIEW_1D)).getValue().doubleValue();
        off_center_distance_2D = ((NumberParam) getParam(OFF_CENTER_FIELD_OF_VIEW_2D)).getValue().doubleValue();
        off_center_distance_3D = ((NumberParam) getParam(OFF_CENTER_FIELD_OF_VIEW_3D)).getValue().doubleValue();

        txLength90 = ((NumberParam) getParam(TX_LENGTH_90)).getValue().doubleValue();
        txLength180 = ((NumberParam) getParam(TX_LENGTH_180)).getValue().doubleValue();

        isDynamic = ((BooleanParam) getParam(DYNAMIC_SEQUENCE)).getValue();
        numberOfDynamicAcquisition = isDynamic ? ((NumberParam) getParam(DYN_NUMBER_OF_ACQUISITION)).getValue().intValue() : 1;
        isDynamic = isDynamic && (numberOfDynamicAcquisition >= 1);

        isInversionRecovery = ((BooleanParam) getParam(INVERSION_RECOVERY)).getValue();
        inversionRecoveryTime = (ListNumberParam) getParam(INVERSION_TIME_MULTI);
        numberOfInversionRecovery = isInversionRecovery ? inversionRecoveryTime.getValue().size() : 1;
        isInversionRecovery = isInversionRecovery && (numberOfInversionRecovery >= 1);

        isTrigger = ((BooleanParam) getParam(TRIGGER_EXTERNAL)).getValue();
        triggerTime = (ListNumberParam) getParam(TRIGGER_TIME);
        numberOfTrigger = isTrigger ? triggerTime.getValue().size() : 1;
        isTrigger = isTrigger && (numberOfTrigger >= 1);

        isKSCenterMode = ((BooleanParam) getParam(KS_CENTER_MODE)).getValue();

        isEnablePhase3D = !isKSCenterMode && ((BooleanParam) getParam(GRADIENT_ENABLE_PHASE_3D)).getValue();
        isEnablePhase = !isKSCenterMode && ((BooleanParam) getParam(GRADIENT_ENABLE_PHASE)).getValue();
        isEnableSlice = ((BooleanParam) getParam(GRADIENT_ENABLE_SLICE)).getValue();
        isEnableRead = ((BooleanParam) getParam(GRADIENT_ENABLE_READ)).getValue();

        observation_time = ((NumberParam) getParam(ACQUISITION_TIME_PER_SCAN)).getValue().doubleValue();
    }

    // --------------------------------------------------------------------------------------------------------------------------------------------
    // -- INIT AFTER ROUTING --- INIT AFTER ROUTING --- INIT AFTER ROUTING --- INIT AFTER ROUTING --- INIT AFTER ROUTING --- INIT AFTER ROUTING --
    // --------------------------------------------------------------------------------------------------------------------------------------------
    //
    //                                                          INIT AFTER ROUTING
    //
    // --------------------------------------------------------------------------------------------------------------------------------------------
    private void initAfterRouting() {

    }

    // --------------------------------------------------------------------------------------------------------------------------------------------
    // -- BEFORE ROUTING --- BEFORE ROUTING --- BEFORE ROUTING --- BEFORE ROUTING --- BEFORE ROUTING --- BEFORE ROUTING --- BEFORE ROUTING ---
    // --------------------------------------------------------------------------------------------------------------------------------------------
    //
    //                                                          BEFORE ROUTING
    //
    // --------------------------------------------------------------------------------------------------------------------------------------------
    private void beforeRouting() throws Exception {
        Log.debug(getClass(), "------------ BEFORE ROUTING -------------");

        setParamValue(SEQUENCE_VERSION, sequenceVersion);
        setParamValue(MODALITY, "MRI");

        // -----------------------------------------------
        // RX parameters : nucleus, RX gain & frequencies
        // -----------------------------------------------
        nucleus = Nucleus.getNucleusForName((String) getParam(NUCLEUS_1).getValue());
        protonFrequency = Instrument.instance().getDevices().getMagnet().getProtonFrequency();
        double freq_offset1 = ((NumberParam) getParam(OFFSET_FREQ_1)).getValue().doubleValue();
        observeFrequency = nucleus.getFrequency(protonFrequency) + freq_offset1;
        setParamValue(BASE_FREQ_1, nucleus.getFrequency(protonFrequency));

        min_time_per_acq_point = HardwareHandler.getInstance().getSequenceHandler().getCompiler().getTransfertTimePerDataPt();
        gMax = GradientMath.getMaxGradientStrength();

        setSequenceParamValue(Rx_gain, RECEIVER_GAIN);
        setParamValue(RECEIVER_COUNT, Instrument.instance().getObservableRxs(nucleus).size());

        setSequenceParamValue(Intermediate_frequency, Instrument.instance().getIfFrequency());
        setParamValue(INTERMEDIATE_FREQUENCY, Instrument.instance().getIfFrequency());

        setSequenceParamValue(Tx_frequency, observeFrequency);
        setParamValue(OBSERVED_FREQUENCY, observeFrequency);

        setSequenceParamValue(Tx_nucleus, NUCLEUS_1);
        setParamValue(OBSERVED_NUCLEUS, nucleus);

        // -----------------------------------------------
        //      CONTRAST
        // -----------------------------------------------
        boolean is_FSE_vs_MultiEcho;
        if (echoTrainLength == 1) {
            setParamValue(ECHO_SPACING, 0);
        }
        //  adapt the TRANSFORM_PLUGIN and the TE/TR depending on the IMAGE_CONTRAST
        if (("FSE".equalsIgnoreCase((String) (getParam(SE_TYPE).getValue())))) {
            is_FSE_vs_MultiEcho = true;

            if (("Sequential4D".equalsIgnoreCase((String) (getParam(TRANSFORM_PLUGIN).getValue())))) {
                setParamValue(TRANSFORM_PLUGIN, ("Centered4D"));
            }

//            ListNumberParam contrast_TE_TR_lim;
            switch ((String) getParam(IMAGE_CONTRAST).getValue()) {
                case "T1-weighted":
                    setParamValue(TRANSFORM_PLUGIN, "Centered2D"); // first echo should be the significant echo
//                    contrast_TE_TR_lim = (ListNumberParam) getParam(LIM_T1_WEIGHTED);
                    if (echoTrainLength > 4) { // echo train should be lower than 4 because of blurring
                        echoTrainLength = 4;
                        setParamValue(ECHO_TRAIN_LENGTH, echoTrainLength);
                    }
                    break;
                case "PD-weighted":
                    setParamValue(TRANSFORM_PLUGIN, "Centered2D"); // first echo should be the significant echo
//                    contrast_TE_TR_lim = (ListNumberParam) getParam(LIM_PD_WEIGHTED);
                    if (echoTrainLength > 6) {  // echo train should be lower than 6 because of blurring
                        echoTrainLength = 6;
                        setParamValue(ECHO_TRAIN_LENGTH, echoTrainLength);
                    }
                    break;
                case "T2-weighted":
                    setParamValue(TRANSFORM_PLUGIN, "Bordered2D"); // last echo should be the significant echo
//                    contrast_TE_TR_lim = (ListNumberParam) getParam(LIM_T2_WEIGHTED);
                    break;
                default: // Custom

                    switch ((String) getParam(TRANSFORM_PLUGIN).getValue()) {
                        case "Sequential2D":
                            setParamValue(TRANSFORM_PLUGIN, echoTrainLength == 1 ? "Sequential2D" : "Centered2D");
                            break;
                        default: // Custom
                            break;
                    }
                    break;
            }
        } else {
            is_FSE_vs_MultiEcho = false;
            if (!("Sequential4D".equalsIgnoreCase((String) (getParam(TRANSFORM_PLUGIN).getValue())))) {
                setParamValue(TRANSFORM_PLUGIN, "Sequential4D");
            }
            if (!("Custom".equalsIgnoreCase((String) (getParam(IMAGE_CONTRAST).getValue())))) {
                echoTrainLength = 1;
                setParamValue(ECHO_TRAIN_LENGTH, echoTrainLength);
            }
            //setParamValue(rs2d.sequence.spinecho.SPIN_ECHO_devParams.IMAGE_CONTRAST, "Custom");
        }

        // -----------------------------------------------
        // 1stD managment
        // -----------------------------------------------
        // FOV

        double fov_eff = isFovDoubled ? (fov * 2) : fov;
        setParamValue(FOV_EFF, fov_eff);

        // Pixel dimension calculation
        acquisitionMatrixDimension1D = userMatrixDimension1D * (isFovDoubled ? 2 : 1);
        pixelDimension = fov_eff / acquisitionMatrixDimension1D;
        setParamValue(RESOLUTION_FREQUENCY, pixelDimension); // frequency true resolution for display

        // MATRIX
        double spectralWidthPerPixel = ((NumberParam) getParam(SPECTRAL_WIDTH_PER_PIXEL)).getValue().doubleValue();
        spectralWidth = isFovDoubled ? (spectralWidth * 2) : spectralWidth;
        spectralWidth = isSW ? spectralWidth : spectralWidthPerPixel * acquisitionMatrixDimension1D;

        spectralWidth = HardwareHandler.getInstance().getSequenceHandler().getCompiler().getNearestSW(spectralWidth);      // get real spectral width from Chameleon
        double spectralWidthUP = isFovDoubled ? (spectralWidth / 2) : spectralWidth;
        spectralWidthPerPixel = spectralWidth / acquisitionMatrixDimension1D;
        setParamValue(SPECTRAL_WIDTH_PER_PIXEL, spectralWidthPerPixel);
        setParamValue(SPECTRAL_WIDTH, spectralWidthUP);
        observation_time = acquisitionMatrixDimension1D / spectralWidth;
        setParamValue(ACQUISITION_TIME_PER_SCAN, observation_time);   // display observation time



        nb_scan_1d = number_of_averages;
        // -----------------------------------------------
        // 2nd D managment
        // -----------------------------------------------
        // FOV
        prepareFovPhase();

        // MATRIX
        setSquarePixel(((BooleanParam) getParam(SQUARE_PIXEL)).getValue());
        double partial_phase = ((NumberParam) getParam(USER_PARTIAL_PHASE)).getValue().doubleValue();
        double zero_filling_2D = (100 - partial_phase) / 100f;
        if (is_FSE_vs_MultiEcho) {
            int shoot = (int) Math.round((1 - zero_filling_2D) * userMatrixDimension2D / (2.0 * echoTrainLength));
            zero_filling_2D = 1.0 - shoot * (2.0 * echoTrainLength) / ((double) userMatrixDimension2D);
            if (zero_filling_2D < 0)
                zero_filling_2D = 1.0 - (shoot - 1) * (2.0 * echoTrainLength) / ((double) userMatrixDimension2D);
            partial_phase = (100 - zero_filling_2D * 100f);
        }
        setParamValue(USER_ZERO_FILLING_2D, (100 - partial_phase));
        acquisitionMatrixDimension2D = floorEven((1 - zero_filling_2D) * userMatrixDimension2D);
        acquisitionMatrixDimension2D = (acquisitionMatrixDimension2D < 4) && isEnablePhase ? 4 : acquisitionMatrixDimension2D;

        // Pixel dimension calculation
        double pixelDimensionPhase = fovPhase / acquisitionMatrixDimension2D;
        setParamValue(RESOLUTION_PHASE, pixelDimensionPhase); // phase true resolution for display

        // -----------------------------------------------
        // 2nd D managment  ETL
        // -----------------------------------------------
        nb_scan_2d = acquisitionMatrixDimension2D;
        if (is_FSE_vs_MultiEcho) {
            echoTrainLength = getInferiorDivisorToGetModulusZero(echoTrainLength, acquisitionMatrixDimension2D / 2);
            setParamValue(ECHO_TRAIN_LENGTH, echoTrainLength);
            nb_scan_2d = Math.floorDiv(acquisitionMatrixDimension2D, echoTrainLength);
            if (!("Sequential2D".equalsIgnoreCase((String) (getParam(TRANSFORM_PLUGIN).getValue())))) {
                nb_scan_2d = floorEven(nb_scan_2d); // Centred or bordered skim need to be multiple of 2
            }
            acquisitionMatrixDimension2D = nb_scan_2d * echoTrainLength;
        }
        userMatrixDimension2D = userMatrixDimension2D < acquisitionMatrixDimension2D ? acquisitionMatrixDimension2D : userMatrixDimension2D;
        setParamValue(USER_MATRIX_DIMENSION_2D, userMatrixDimension2D);

        // -----------------------------------------------
        // 3D managment 1/2: matrix & scan
        // ------------------------------------------------
        // MATRIX
        double partial_slice;
        // 3D ZERO FILLING
        if (isMultiplanar) {
            setParamValue(USER_PARTIAL_SLICE, 100);
            partial_slice = 100;
        } else {
            partial_slice = ((NumberParam) getParam(USER_PARTIAL_SLICE)).getValue().doubleValue();
        }
        double zero_filling_3D = (100 - partial_slice) / 100f;
        setParamValue(USER_ZERO_FILLING_3D, (100 - partial_slice));

        //Calculate the number of k-space lines acquired in the 3rd Dimension : acquisitionMatrixDimension3D
        if (!isMultiplanar) {
            acquisitionMatrixDimension3D = floorEven((1 - zero_filling_3D) * userMatrixDimension3D);
            acquisitionMatrixDimension3D = (acquisitionMatrixDimension3D < 4) && isEnablePhase3D ? 4 : acquisitionMatrixDimension3D;
            userMatrixDimension3D = userMatrixDimension3D < acquisitionMatrixDimension3D ? acquisitionMatrixDimension3D : userMatrixDimension3D;
            setParamValue(USER_MATRIX_DIMENSION_3D, userMatrixDimension3D);
        } else {
            /*if ((userMatrixDimension3D * 3 + ((is_rf_spoiling) ? 1 : 0) + 3 + 1) >= offset_channel_memory) {/// ToDO check memory
                userMatrixDimension3D = ((int) Math.floor((offset_channel_memory - 4 - ((is_rf_spoiling) ? 1 : 0)) / 3.0));
                setParamValue(USER_MATRIX_DIMENSION_3D, userMatrixDimension3D);
            }*/
            acquisitionMatrixDimension3D = userMatrixDimension3D;
        }

        int nb_of_shoot_3d = ((NumberParam) getParam(NUMBER_OF_SHOOT_3D)).getValue().intValue();
        nb_of_shoot_3d = isMultiplanar ? getInferiorDivisorToGetModulusZero(nb_of_shoot_3d, userMatrixDimension3D) : 0;
        nbOfInterleavedSlice = isMultiplanar ? (int) Math.ceil((acquisitionMatrixDimension3D / nb_of_shoot_3d)) : 1;
        setParamValue(NUMBER_OF_SHOOT_3D, nb_of_shoot_3d);
        setParamValue(NUMBER_OF_INTERLEAVED_SLICE, isMultiplanar ? nbOfInterleavedSlice : 0);

        nb_scan_3d = isMultiplanar ? nb_of_shoot_3d : acquisitionMatrixDimension3D;

        // -----------------------------------------------
        // 3D managment 2/2: MEMORY LIMITATION
        // -----------------------------------------------

        if (isMultiplanar) {
            // correct number of slices if Chameleon memory limit is reached
            boolean memory_limit_reached = false;
            // Check memory: PHASE GRADIENT AMPLITUDE
            int max_nb_interleaved_excitation_for_phase_amp = nbOfInterleavedSlice;
            if (is_FSE_vs_MultiEcho && echoTrainLength != 1) {
                if ((acquisitionMatrixDimension2D * 2) >= (amp_channel_memory - 1)) {
                    //  maximum_possible_phase_amp_memory = (int) ((amp_channel_memory / 2) / acquisitionMatrixDimension2D);
                    max_nb_interleaved_excitation_for_phase_amp = (int) Math.floor((amp_channel_memory - 1) / ((double) (acquisitionMatrixDimension2D * 2)));
                    memory_limit_reached = true;
                }
            }

            // Check memory: TX OFFSET FREQUENCY
            // the 180° is inside the Echo loop therefore , the slices_offset need to be repeated for each ETL
            int max_nb_planar_excitation_for_freq_off = userMatrixDimension3D;
            if ((1 + 1 /* F0 */ + 3 + (isMultiplanar ? /* 2D */ userMatrixDimension3D * 2 :/* 3D */ 2)) > offset_channel_memory) {
                max_nb_planar_excitation_for_freq_off = (int) Math.floor((offset_channel_memory - 5) / (float) (2));
                memory_limit_reached = true;
                Log.info(getClass(), "OFFSET MEMORY ERROR : number of slices too large \n");
//                System.out.println("OFFSET MEMORY ERROR : echo train length * number of slices too large");
//                System.out.println("tx_180° inside the EchoLoop -> frequency offset has to be repeated for each ETL  ");
//                System.out.println(" ");
            }
            if (memory_limit_reached) {
                int max_nb_interleaved_excitation_for_freq_off = (int) Math.floor(max_nb_planar_excitation_for_freq_off / ((double) nb_scan_3d));
                int min_max_nb_interleaved_excitation = Math.min(max_nb_interleaved_excitation_for_phase_amp, max_nb_interleaved_excitation_for_freq_off);
                if (min_max_nb_interleaved_excitation == 0) {
                    getUnreachParamExceptionManager().addParam(NUMBER_OF_SHOOT_3D.name(), nb_scan_3d, ((NumberParam) getParam(NUMBER_OF_SHOOT_3D)).getMinValue(), max_nb_planar_excitation_for_freq_off, "Memory limit (PHASE GRADIENT AMPLITUDE): ( Acq Mx2D * User Mx3D / Nb shoot 3D * 2 )too large");
                    nb_scan_3d = max_nb_planar_excitation_for_freq_off;
                    min_max_nb_interleaved_excitation = 1;
                }
                String unreach_message;
                if (max_nb_interleaved_excitation_for_phase_amp < max_nb_interleaved_excitation_for_freq_off) {
                    unreach_message = "Memory limit (PHASE GRADIENT AMPLITUDE): ( Acq_Mx2D * 2 ) too large";
                } else {
                    unreach_message = "Memory limit (TX OFFSET FREQUENCY): 4 + User_Mx3D *3 too large";
                }
                System.out.println(unreach_message);
                System.out.println(nb_scan_3d);
                System.out.println(min_max_nb_interleaved_excitation);
                getUnreachParamExceptionManager().addParam(USER_MATRIX_DIMENSION_3D.name(), userMatrixDimension3D, ((NumberParam) getParam(USER_MATRIX_DIMENSION_3D)).getMinValue(), (nb_scan_3d * min_max_nb_interleaved_excitation), unreach_message);

                userMatrixDimension3D = nb_scan_3d * min_max_nb_interleaved_excitation;
                nbOfInterleavedSlice = min_max_nb_interleaved_excitation;
                acquisitionMatrixDimension3D = userMatrixDimension3D;
            }
        }

        // FOV
        double spacing_between_slice;
        if (isMultiplanar) {
            spacing_between_slice = ((NumberParam) getParam(SPACING_BETWEEN_SLICE)).getValue().doubleValue();
        } else {
            setParamValue(SPACING_BETWEEN_SLICE, 0);
            spacing_between_slice = 0;
        }
        double fov_3d = sliceThickness * userMatrixDimension3D + spacing_between_slice * (userMatrixDimension3D - 1);
        setParamValue(FIELD_OF_VIEW_3D, fov_3d);    // FOV ratio for display

        // Pixel dimension calculation
        double pixel_dimension_3D;
        if (isMultiplanar) {
            pixel_dimension_3D = sliceThickness;
        } else {
            pixel_dimension_3D = sliceThickness * userMatrixDimension3D / acquisitionMatrixDimension3D; //true resolution
        }
        setParamValue(RESOLUTION_SLICE, pixel_dimension_3D); // phase true resolution for display

        if (isMultiplanar) {
            double tmp = ((NumberParam) getParam(GRADIENT_PHASE_APPLICATION_TIME)).getValue().doubleValue();
            setParamValue(GRADIENT_CRUSHER_TOP_TIME, tmp);
        }
        // -----------------------------------------------
        // 4D managment:  Dynamic, MultiEcho, External triggering, Multi Echo
        // -----------------------------------------------
        int numberOfEcho = !is_FSE_vs_MultiEcho ? echoTrainLength : 1;

        // Avoid multi IR time when Multi echo
        if (numberOfInversionRecovery != 1 && (numberOfEcho != 1)) {
            double tmp = inversionRecoveryTime.getValue().get(0).doubleValue();
            inversionRecoveryTime.getValue().clear();
            inversionRecoveryTime.getValue().add(tmp);
            numberOfInversionRecovery = 1;
        }
        // Avoid multi trigger time when multi Inversion time or Multi echo or dynamic
        if (numberOfTrigger != 1 && (numberOfInversionRecovery != 1 || isDynamic || (numberOfEcho != 1))) {
            double tmp = triggerTime.getValue().get(0).doubleValue();
            triggerTime.getValue().clear();
            triggerTime.getValue().add(tmp);
            numberOfTrigger = 1;
        }

        nb_scan_4d = numberOfTrigger * numberOfInversionRecovery * numberOfDynamicAcquisition;
        acquisitionMatrixDimension4D = nb_scan_4d * numberOfEcho;
        setParamValue(USER_MATRIX_DIMENSION_4D, nb_scan_4d);


        // -----------------------------------------------
        // set the ACQUISITION_MATRIX and Nb XD
        // -----------------------------------------------        // set the calculated acquisition matrix

        if (isKSCenterMode) {
            nb_scan_1d = 1;
            nb_scan_2d = 2;
            nb_scan_3d = !isMultiplanar ? 1 : nb_scan_3d;
            nb_scan_4d = 1;
        }

        setParamValue(ACQUISITION_MATRIX_DIMENSION_1D, acquisitionMatrixDimension1D);
        setParamValue(ACQUISITION_MATRIX_DIMENSION_2D, acquisitionMatrixDimension2D);
        setParamValue(ACQUISITION_MATRIX_DIMENSION_3D, acquisitionMatrixDimension3D);
        setParamValue(ACQUISITION_MATRIX_DIMENSION_4D, acquisitionMatrixDimension4D);



        // set the calculated sequence dimensions
        setSequenceParamValue(Pre_scan, DUMMY_SCAN); // Do the prescan
        setSequenceParamValue(Nb_point, acquisitionMatrixDimension1D);
        setSequenceParamValue(Nb_1d, nb_scan_1d);
        setSequenceParamValue(Nb_2d, nb_scan_2d);
        setSequenceParamValue(Nb_3d, nb_scan_3d);
        setSequenceParamValue(Nb_4d, nb_scan_4d);
        // set the calculated Loop dimensions
        setSequenceParamValue(Nb_echo, echoTrainLength - 1);
        setSequenceParamValue(Nb_interleaved_slice, nbOfInterleavedSlice - 1);

        // -----------------------------------------------
        // SEQ_DESCRIPTION
        // -----------------------------------------------
        String seqDescription = "SE_";
        if (isMultiplanar) {
            seqDescription += "2D_";
        } else {
            seqDescription += "3D_";
        }
        String orientation = (String) getParam(ORIENTATION).getValue();
        seqDescription += orientation.substring(0, 3);

        String seqMatrixDescription = "_";
        seqMatrixDescription += String.valueOf(userMatrixDimension1D) + "x" + String.valueOf(acquisitionMatrixDimension2D) + "x" + String.valueOf(acquisitionMatrixDimension3D);
        if (acquisitionMatrixDimension4D != 1) {
            seqMatrixDescription += "x" + String.valueOf(acquisitionMatrixDimension4D);
        }
        seqDescription += seqMatrixDescription;

        if (echoTrainLength != 1) {
            seqDescription += "_ETL=" + String.valueOf(echoTrainLength);
        }

        if (isInversionRecovery && numberOfInversionRecovery != 1) {
            seqDescription += "_IR-" + String.valueOf(numberOfInversionRecovery);
        } else if (isInversionRecovery) {
            seqDescription += "_IR=" + String.valueOf(inversionRecoveryTime.getValue().get(0).doubleValue()) + "s";
        }
        if (isTrigger && numberOfTrigger != 1) {
            seqDescription += "_TRIG=" + String.valueOf(numberOfTrigger);
        } else if (isTrigger) {
            seqDescription += "_TRIG ";
        }
        if (isDynamic) {
            seqDescription += "_DYN=" + String.valueOf(numberOfDynamicAcquisition);
        }
        setParamValue(SEQ_DESCRIPTION, seqDescription);

        // -----------------------------------------------
        // Image Orientation
        // -----------------------------------------------
        //READ PHASE and SLICE matrix
        off_center_distance_1D = getOff_center_distance_1D_2D_3D(1);
        off_center_distance_2D = getOff_center_distance_1D_2D_3D(2);
        off_center_distance_3D = getOff_center_distance_1D_2D_3D(3);

        //Offset according to ENABLE READ PHASE and SLICE
        off_center_distance_1D = !isEnableRead ? 0 : off_center_distance_1D;
        off_center_distance_2D = !isEnablePhase ? 0 : off_center_distance_2D;

        if (!isEnableSlice && (isMultiplanar || (!isMultiplanar && !isEnablePhase3D))) {
            off_center_distance_3D = 0;
        }


        boolean is_read_phase_inverted = ((BooleanParam) getParam(SWITCH_READ_PHASE)).getValue();
        if (is_read_phase_inverted) {
            setSequenceParamValue(Gradient_axe_phase, GradientAxe.R);
            setSequenceParamValue(Gradient_axe_read, GradientAxe.P);
            double off_center_distance_tmp = off_center_distance_2D;
            off_center_distance_2D = off_center_distance_1D;
            off_center_distance_1D = off_center_distance_tmp;
        } else {
            setSequenceParamValue(Gradient_axe_phase, GradientAxe.P);
            setSequenceParamValue(Gradient_axe_read, GradientAxe.R);
        }
        setParamValue(OFF_CENTER_FIELD_OF_VIEW_3D, off_center_distance_3D);
        setParamValue(OFF_CENTER_FIELD_OF_VIEW_2D, off_center_distance_2D);
        setParamValue(OFF_CENTER_FIELD_OF_VIEW_1D, off_center_distance_1D);

        // -----------------------------------------------
        // activate gradient rotation matrix
        // -----------------------------------------------
        appliedGradientRotation();
    }

    private int floorEven(double value) {
        return (int) Math.floor(Math.round(value) / 2.0) * 2;
    }

    private void setSquarePixel(boolean square) {
        if (square) {
            userMatrixDimension2D = (int) Math.round(userMatrixDimension1D * fovPhase / fov);
            setParamValue(USER_MATRIX_DIMENSION_2D, userMatrixDimension2D);
        }
    }

    private void prepareFovPhase() {
        fovPhase = (((BooleanParam) getParam(FOV_SQUARE)).getValue()) ? fov : fovPhase;
        fovPhase = fovPhase > fov ? fov : fovPhase;
        setParamValue(FIELD_OF_VIEW_PHASE, fovPhase);

        setParamValue(PHASE_FIELD_OF_VIEW_RATIO, (fovPhase / fov * 100.0));    // FOV ratio for display
        setParamValue(FOV_RATIO_PHASE, Math.round(fovPhase / fov * 100.0));    // FOV ratio for display
    }

    // --------------------------------------------------------------------------------------------------------------------------------------------
    // -- AFTER ROUTING --- AFTER ROUTING --- AFTER ROUTING --- AFTER ROUTING --- AFTER ROUTING --- AFTER ROUTING --- AFTER ROUTING ---  AFTER ROUTING ---
    // --------------------------------------------------------------------------------------------------------------------------------------------
    //
    //                                                          AFTER ROUTING
    //
    // --------------------------------------------------------------------------------------------------------------------------------------------

    private void afterRouting() throws Exception {
        Log.debug(getClass(), "------------ AFTER ROUTING -------------");
        plugin = getTransformPlugin();
        plugin.setParameters(this.getParams());
        // -----------------------------------------------
        // enable gradient lines
        // -----------------------------------------------
        setSequenceParamValue(Grad_enable_read, isEnableRead);              // pass gradient line status to sequence
        setSequenceParamValue(Grad_enable_phase_2D, isEnablePhase);
        setSequenceParamValue(Grad_enable_phase_3D, (!isMultiplanar && isEnablePhase3D));
        setSequenceParamValue(Grad_enable_slice, isEnableSlice);
        setSequenceParamValue(Grad_enable_Slice_180, isEnableSlice);
        setSequenceParamValue(Grad_enable_slice_crush, GRADIENT_ENABLE_SLICE_CRUSH);
        setSequenceParamValue(Grad_enable_spoiler, GRADIENT_ENABLE_SPOILER);

        // -----------------------------------------------
        // enable Inversion recovery elements
        // -----------------------------------------------
        setSequenceParamValue(Grad_enable_IR, isInversionRecovery);
        setSequenceParamValue(Tx_enable_IR, isInversionRecovery);
        boolean isEnableCrusherIR = ((BooleanParam) getParam(GRADIENT_ENABLE_CRUSHER_IR)).getValue();
        setSequenceParamValue(Grad_enable_crush_IR, isInversionRecovery ? isEnableCrusherIR : false);


        // -----------------------------------------------
        // calculate gradient equivalent rise time
        // -----------------------------------------------
        double grad_rise_time = ((NumberParam) getParam(GRADIENT_RISE_TIME)).getValue().doubleValue();
        double min_rise_time_factor = ((NumberParam) getParam(MIN_RISE_TIME_FACTOR)).getValue().doubleValue();

        double min_rise_time_sinus = GradientMath.getShortestRiseTime(100.0) * Math.PI / 2 * 100 / min_rise_time_factor;
        if (grad_rise_time < min_rise_time_sinus) {
            double new_grad_rise_time = ceilToSubDecimal(min_rise_time_sinus, 5);
            getUnreachParamExceptionManager().addParam(GRADIENT_RISE_TIME.name(), grad_rise_time, new_grad_rise_time, ((NumberParam) getParam(GRADIENT_RISE_TIME)).getMaxValue(), "Gradient ramp time too short ");
            grad_rise_time = new_grad_rise_time;
        }
        setSequenceTableSingleValue(Time_grad_ramp, grad_rise_time);

        double grad_shape_rise_factor_up = Utility.voltageFillingFactor((Shape) getSequence().getPublicTable(Grad_shape_rise_up));
        double grad_shape_rise_factor_down = Utility.voltageFillingFactor((Shape) getSequence().getPublicTable(Grad_shape_rise_down));
        double grad_shape_rise_time = grad_shape_rise_factor_up * grad_rise_time + grad_shape_rise_factor_down * grad_rise_time;        // shape dependant equivalent rise time

        // -----------------------------------------------
        // get user defined matrix dimensions from panel
        // -----------------------------------------------

        //  get limits for the image contrast
        double[] TE_TR_lim = {0, 10000, 0, 10000}; // limite {TEmin TEmax, TRmin TRmax}
        boolean is_FSE_vs_MultiEcho = ("FSE".equalsIgnoreCase((String) (getParam(SE_TYPE).getValue())));
        //if (is_FSE_vs_MultiEcho) {
        switch ((String) getParam(IMAGE_CONTRAST).getValue()) {
            case "T1-weighted":
                ListNumberParam T1_contrast_TE_TR_lim = (ListNumberParam) getParam(LIM_T1_WEIGHTED);
                TE_TR_lim[1] = T1_contrast_TE_TR_lim.getValue().get(0).doubleValue(); //TEmax
                TE_TR_lim[2] = T1_contrast_TE_TR_lim.getValue().get(1).doubleValue(); //TRmin
                TE_TR_lim[3] = T1_contrast_TE_TR_lim.getValue().get(2).doubleValue(); //TRmax
                break;
            case "PD-weighted":
                ListNumberParam PD_contrast_TE_TR_lim = (ListNumberParam) getParam(LIM_PD_WEIGHTED);
                TE_TR_lim[1] = PD_contrast_TE_TR_lim.getValue().get(0).doubleValue(); //TEmax
                TE_TR_lim[2] = PD_contrast_TE_TR_lim.getValue().get(1).doubleValue(); //TRmin
                break;
            case "T2-weighted":
                ListNumberParam T2_contrast_TE_TR_lim = (ListNumberParam) getParam(LIM_T2_WEIGHTED);
                TE_TR_lim[0] = T2_contrast_TE_TR_lim.getValue().get(0).doubleValue(); //TEmin
                TE_TR_lim[2] = T2_contrast_TE_TR_lim.getValue().get(1).doubleValue(); //TRmin
                break;
            default: // Custom
                break;
        }


        // -----------------------------------------------
        // Calculation RF pulse parameters  1/3 : Shape
        // -----------------------------------------------
        setSequenceTableFirstValue(Time_tx_90, TX_LENGTH_90.name());     // set RF pulse length to sequence
        setSequenceTableFirstValue(Time_tx_180, TX_LENGTH_180.name());   // set 180° RF pulse length to sequence
        RFPulse pulseTX90 = RFPulse.createRFPulse(getSequence(), Tx_att, Tx_amp_90, Tx_phase_90, Time_tx_90, Tx_shape_90, Tx_shape_phase_90, Tx_freq_offset_90);
        RFPulse pulseTX180 = RFPulse.createRFPulse(getSequence(), Tx_att, Tx_amp_180, Tx_phase_180, Time_tx_180, Tx_shape_180, Tx_shape_phase_180, Tx_freq_offset_180);

        int nb_shape_points = 128;
        pulseTX90.setShape(((String) getParam(TX_SHAPE_90).getValue()), nb_shape_points, "Hamming");
        pulseTX180.setShape(((String) getParam(TX_SHAPE_180).getValue()), nb_shape_points, "Hamming");

        // -----------------------------------------------
        // Calculation RF pulse parameters  2/3 : RF pulse & attenuation
        // -----------------------------------------------
        boolean is_tx_amp_att_auto = ((BooleanParam) getParam(TX_AMP_ATT_AUTO)).getValue();
        if (is_tx_amp_att_auto) {
            if (!pulseTX180.checkPower(180, observeFrequency, nucleus)) {
                getUnreachParamExceptionManager().addParam(TX_LENGTH_180.name(), txLength180, pulseTX180.getPulseDuration(), ((NumberParam) getParam(TX_LENGTH_180)).getMaxValue(), "Pulse length too short to reach RF power with this pulse shape");
                txLength180 = pulseTX180.getPulseDuration();
            }
            if (!pulseTX90.checkPower(90, observeFrequency, nucleus)) {
                getUnreachParamExceptionManager().addParam(TX_LENGTH_90.name(), txLength90, pulseTX90.getPulseDuration(), ((NumberParam) getParam(TX_LENGTH_90)).getMaxValue(), "Pulse length too short to reach RF power with this pulse shape");
                txLength90 = pulseTX90.getPulseDuration();
            }
            RFPulse pulseMaxPower = pulseTX90.getPower() > pulseTX180.getPower() ? pulseTX90 : pulseTX180;
            pulseMaxPower.prepAtt(80, (List<Integer>) getParam(TX_ROUTE).getValue());

            pulseTX90.prepTxAmp((List<Integer>) getParam(TX_ROUTE).getValue());
            pulseTX180.prepTxAmp((List<Integer>) getParam(TX_ROUTE).getValue());
            this.setParamValue(TX_ATT, pulseTX90.getAtt());            // display PULSE_ATT
            this.setParamValue(TX_AMP_90, pulseTX90.getAmp90());     // display 90° amplitude
            this.setParamValue(TX_AMP_180, pulseTX180.getAmp180());   // display 180° amplitude
        } else {
            pulseTX90.setAtt(((NumberParam) getParam(TX_ATT)));
            pulseTX90.setAmp(((NumberParam) getParam(TX_AMP_90)));
            pulseTX180.setAmp(((NumberParam) getParam(TX_AMP_180)));
        }

        // -----------------------------------------------
        // Calculation RF pulse parameters  3/3: bandwidth
        // -----------------------------------------------
        double tx_bandwidth_factor_90 = getTx_bandwidth_factor_90(TX_SHAPE_90, TX_BANDWIDTH_FACTOR, TX_BANDWIDTH_FACTOR_3D);
        double tx_bandwidth_90 = tx_bandwidth_factor_90 / txLength90;
        double tx_bandwidth_factor_180 = getTx_bandwidth_factor_90(TX_SHAPE_180, TX_BANDWIDTH_FACTOR, TX_BANDWIDTH_FACTOR_3D);
        double tx_bandwidth_180 = tx_bandwidth_factor_180 / txLength180;

        // ---------------------------------------------------------------------
        // calculate SLICE gradient amplitudes for RF pulses
        // ---------------------------------------------------------------------
        double slice_thickness_excitation = (isMultiplanar ? sliceThickness : (sliceThickness * userMatrixDimension3D));

        //SLICE gradient amp for 180
        boolean is_wide_180_slice_thickness = ((BooleanParam) getParam(SLICE_THICKNESS_180_WIDER)).getValue();
        double spacing_between_slice = ((NumberParam) getParam(SPACING_BETWEEN_SLICE)).getValue().doubleValue();
        double slice_thickness_excitation_180;
        if (((spacing_between_slice > slice_thickness_excitation) || !isMultiplanar) && is_wide_180_slice_thickness) {
            slice_thickness_excitation_180 = slice_thickness_excitation * (1 + 0.5);
        } else if ((spacing_between_slice != 0) && is_wide_180_slice_thickness) {
            slice_thickness_excitation_180 = slice_thickness_excitation + spacing_between_slice / 2.0;
        } else {
            slice_thickness_excitation_180 = slice_thickness_excitation;
        }

        Gradient gradSlice90 = Gradient.createGradient(getSequence(), Grad_amp_slice_90, Time_tx_90, Grad_shape_rise_up, Grad_shape_rise_down, Time_grad_ramp);
        Gradient gradSlice180 = Gradient.createGradient(getSequence(), Grad_amp_slice_180, Time_tx_180, Grad_shape_rise_up, Grad_shape_rise_down, Time_grad_ramp);
        if (isEnableSlice) {
            boolean test90 = gradSlice90.prepareSliceSelection(tx_bandwidth_90, slice_thickness_excitation);
            boolean test180 = gradSlice180.prepareSliceSelection(tx_bandwidth_180, slice_thickness_excitation_180);
            if (!test90 || !test180) {
                slice_thickness_excitation = Math.max(gradSlice90.getSliceThickness(), gradSlice180.getSliceThickness());
                double slice_thickness_min = (isMultiplanar ? slice_thickness_excitation : (slice_thickness_excitation / userMatrixDimension3D));
                getUnreachParamExceptionManager().addParam(SLICE_THICKNESS.name(), sliceThickness, slice_thickness_min, ((NumberParam) getParam(SLICE_THICKNESS)).getMaxValue(), "Pulse length too short to reach this slice thickness");
                sliceThickness = slice_thickness_min;
            }
        }
        gradSlice90.applyAmplitude(slice_thickness_excitation);
        gradSlice180.applyAmplitude(slice_thickness_excitation_180);

        // -----------------------------------------------
        // calculate ADC observation time
        // -----------------------------------------------
        setSequenceTableSingleValue(Time_rx, observation_time);

        // -----------------------------------------------
        // calculate READ gradient amplitude
        // -----------------------------------------------
        Gradient gradReadout = Gradient.createGradient(getSequence(), Grad_amp_read_read, Time_rx, Grad_shape_rise_up, Grad_shape_rise_down, Time_grad_ramp);
        if (isEnableRead && !gradReadout.calculateReadoutGradient(spectralWidth, pixelDimension * acquisitionMatrixDimension1D)) {
            double spectral_width_max = gradReadout.getSpectralWidth();
            if (isSW) {
                getUnreachParamExceptionManager().addParam(SPECTRAL_WIDTH.name(), spectralWidth, ((NumberParam) getParam(SPECTRAL_WIDTH)).getMinValue(), (spectral_width_max / (isFovDoubled ? 2 : 1)), "SPECTRAL_WIDTH too high for the readout gradient");
            } else {
                getUnreachParamExceptionManager().addParam(SPECTRAL_WIDTH_PER_PIXEL.name(), (spectralWidth / acquisitionMatrixDimension1D), ((NumberParam) getParam(SPECTRAL_WIDTH_PER_PIXEL)).getMinValue(), (spectral_width_max / acquisitionMatrixDimension1D), "SPECTRAL_WIDTH too high for the readout gradient");
            }
            spectralWidth = spectral_width_max;
        }
        gradReadout.applyAmplitude();
        setSequenceParamValue(Spectral_width, spectralWidth);

        // -------------------------------------------------------------------------------------------------
        // calculate READ_PREP  & SLICE_REF
        // -------------------------------------------------------------------------------------------------
        double grad_read_prep_application_time = ((NumberParam) getParam(GRADIENT_READ_PREPHASING_APPLICATION_TIME)).getValue().doubleValue();
        setSequenceTableSingleValue(Time_grad_read_prep_top, grad_read_prep_application_time);

        // pre-calculate READ_prephasing max area
        Gradient gradReadPrep = Gradient.createGradient(getSequence(), Grad_amp_read_prep, Time_grad_read_prep_top, Grad_shape_rise_up, Grad_shape_rise_down, Time_grad_ramp);
        if (isEnableRead)
            gradReadPrep.refocalizeGradient(gradReadout, -((NumberParam) getParam(PREPHASING_READ_GRADIENT_RATIO)).getValue().doubleValue());

        // pre-calculate SLICE_refocusing
        double grad_ratio_slice_refoc = 0.5;   // get slice refocussing ratio
        this.setParamValue(SLICE_REFOCUSING_GRADIENT_RATIO, grad_ratio_slice_refoc);   // display 180° amplitude
        Gradient gradSliceRef = Gradient.createGradient(getSequence(), Grad_amp_slice_refoc, Time_grad_read_prep_top, Grad_shape_rise_up, Grad_shape_rise_down, Time_grad_ramp);
        if (isEnableSlice) {
            gradSliceRef.refocalizeGradient(gradSlice90, grad_ratio_slice_refoc);
        }

        // Check if enougth time for 2D_PHASE, 3D_PHASE SLICE_REF or READ_PREP
        double grad_area_sequence_max = 100 * (grad_read_prep_application_time + grad_shape_rise_time);
        double grad_area_max = Math.max(gradReadPrep.getTotalArea(), gradSliceRef.getTotalArea());            // calculate the maximum gradient aera between SLICE REFOC & READ PREPHASING
        if (grad_area_max > grad_area_sequence_max) {
            double grad_read_prep_application_time_min = ceilToSubDecimal(grad_area_max / 100.0 - grad_shape_rise_time, 5);
            getUnreachParamExceptionManager().addParam(GRADIENT_READ_PREPHASING_APPLICATION_TIME.name(), grad_read_prep_application_time, grad_read_prep_application_time_min, ((NumberParam) getParam(GRADIENT_READ_PREPHASING_APPLICATION_TIME)).getMaxValue(), "Gradient application time too short to reach this pixel dimension");
            grad_read_prep_application_time = grad_read_prep_application_time_min;
            setSequenceTableSingleValue(Time_grad_read_prep_top, grad_read_prep_application_time);
            gradSliceRef.rePrepare();
            gradReadPrep.rePrepare();
        }
        gradSliceRef.applyAmplitude();
        gradReadPrep.applyAmplitude();

        // -------------------------------------------------------------------------------------------------
        // calculate PHASE_3D  & PHASE_2D
        // -------------------------------------------------------------------------------------------------
        double grad_phase_application_time = ((NumberParam) getParam(GRADIENT_PHASE_APPLICATION_TIME)).getValue().doubleValue();
        boolean is_k_s_centred = ((BooleanParam) getParam(KS_CENTERED)).getValue();  // symetrique around 0 or go through k0
        setSequenceTableSingleValue(Time_grad_phase_top, grad_phase_application_time);

        // pre-calculate PHASE_3D
        Gradient gradSlicePhase3D = Gradient.createGradient(getSequence(), Grad_amp_phase_3D_prep, Time_grad_phase_top, Grad_shape_rise_up, Grad_shape_rise_down, Time_grad_ramp);
        if (!isMultiplanar && isEnablePhase3D)
            gradSlicePhase3D.preparePhaseEncodingForCheck(acquisitionMatrixDimension3D, acquisitionMatrixDimension3D, slice_thickness_excitation, is_k_s_centred);

        // pre-calculate PHASE_2D
        Gradient gradPhase2D = Gradient.createGradient(getSequence(), Grad_amp_phase_2D_prep, Time_grad_phase_top, Grad_shape_rise_up, Grad_shape_rise_down, Time_grad_ramp);
        gradPhase2D.preparePhaseEncodingForCheck(acquisitionMatrixDimension2D, acquisitionMatrixDimension2D, fovPhase, is_k_s_centred);
        if (is_FSE_vs_MultiEcho && echoTrainLength != 1) {
            gradPhase2D.reoderPhaseEncoding(plugin, echoTrainLength, acquisitionMatrixDimension2D, acquisitionMatrixDimension1D);
        }

        // Check if enougth time for 2D_PHASE, 3D_PHASE SLICE_REF or READ_PREP
        grad_area_sequence_max = 100 * (grad_phase_application_time + grad_shape_rise_time);
        grad_area_max = Math.max(gradSlicePhase3D.getTotalArea(), gradPhase2D.getTotalArea());            // calculate the maximum gradient aera between SLICE REFOC & READ PREPHASING

        if (grad_area_max > grad_area_sequence_max) {
            double grad_phase_application_time_min = ceilToSubDecimal(grad_area_max / 100.0 - grad_shape_rise_time, 5);
            getUnreachParamExceptionManager().addParam(GRADIENT_PHASE_APPLICATION_TIME.name(), grad_phase_application_time, grad_phase_application_time_min, ((NumberParam) getParam(GRADIENT_PHASE_APPLICATION_TIME)).getMaxValue(), "Gradient application time too short to reach this pixel dimension");
            grad_phase_application_time = grad_phase_application_time_min;
            setSequenceTableSingleValue(Time_grad_phase_top, grad_phase_application_time);
            gradPhase2D.rePrepare();
            gradSlicePhase3D.rePrepare();
        }
        gradSlicePhase3D.applyAmplitude(Order.Three);
        gradPhase2D.applyAmplitude((is_FSE_vs_MultiEcho && echoTrainLength != 1) ? Order.TwoLoopB : Order.Two);

        // Reorder Phase Encoding
        Gradient gradSlicePhase3D_comp = Gradient.createGradient(getSequence(), Grad_amp_phase_3D_comp, Time_grad_phase_top, Grad_shape_rise_up, Grad_shape_rise_down, Time_grad_ramp);
        Gradient gradPhase2D_comp = Gradient.createGradient(getSequence(), Grad_amp_phase_2D_comp, Time_grad_phase_top, Grad_shape_rise_up, Grad_shape_rise_down, Time_grad_ramp);

        if (!isMultiplanar && isEnablePhase3D)
            gradSlicePhase3D_comp.refocalizePhaseEncodingGradient(gradSlicePhase3D);
        if (isEnablePhase)
            gradPhase2D_comp.refocalizePhaseEncodingGradient(gradPhase2D);
        gradSlicePhase3D_comp.applyAmplitude();
        gradPhase2D_comp.applyAmplitude();

        // -------------------------------------------------------------------------------------------------
        // Calculate Crusher Grad AMPLITUDE
        // -------------------------------------------------------------------------------------------------
        double grad_amp_crusher = ((NumberParam) getParam(GRADIENT_AMP_CRUSHER)).getValue().doubleValue();
        double time_grad_crusher_top = ((NumberParam) getParam(GRADIENT_CRUSHER_TOP_TIME)).getValue().doubleValue();
        setSequenceTableSingleValue(Time_grad_crusher_top, time_grad_crusher_top);

        Gradient gradSliceCrusher = Gradient.createGradient(getSequence(), Grad_amp_slice_crush, Time_grad_crusher_top, Grad_shape_rise_up, Grad_shape_rise_down, Time_grad_ramp);
        gradSliceCrusher.addSpoiler(grad_amp_crusher);
        gradSliceCrusher.applyAmplitude();
        double grad_area_crusher = (time_grad_crusher_top + grad_shape_rise_time) * grad_amp_crusher * gMax / 100.0;
        setParamValue(GRADIENT_AREA_CRUSHER, grad_area_crusher);

        // --------------------------------------------------------------------------------------------------------------------------------------------
        // TIMING --- TIMING --- TIMING --- TIMING --- TIMING --- TIMING --- TIMING --- TIMING --- TIMING --- TIMING --- TIMING --- TIMING --- TIMING
        // --------------------------------------------------------------------------------------------------------------------------------------------
        // ------------------------------------------
        // delays for FIR
        // ------------------------------------------
        boolean is_FIR = Instrument.instance().getDevices().getCameleon().isRemoveAcquDeadPoint();
        double lo_FIR_dead_point = is_FIR ? Instrument.instance().getDevices().getCameleon().getAcquDeadPointCount() : 0;
        double min_FIR_delay = (lo_FIR_dead_point + 2) / spectralWidth;
        double min_FIR_4pts_delay = 4 / spectralWidth;

        // ------------------------------------------
        // delays for sequence instructions
        // ------------------------------------------
        setSequenceTableSingleValue(Time_min_instruction, min_instruction_delay);
        // ------------------------------------------
        // calculate delays adapted to current TE 1/2 :Check min TE
        // ------------------------------------------+


        // calculate actual delays between pulses: time1 ,time2, (time3 + time3bis)
        double time1 = getTimeBetweenEvents(Events.TX90 + 1, Events.Delay1 - 1) + getTimeBetweenEvents(Events.Delay1 + 4, Events.TX180 - 1);
        time1 = time1 + txLength90 / 2.0 + (txLength180) / 2.0;
        time1 += 3 * min_instruction_delay;

        double time2 = getTimeBetweenEvents(Events.TX180 + 1, Events.Delay2 - 1) + getTimeBetweenEvents(Events.Acq - 1, Events.Acq - 1);
        time2 = time2 + txLength180 / 2.0 + observation_time / 2.0; // time sans le PE gradient et la pause
        double time2_min = time2 + (isMultiplanar ? 3 * min_instruction_delay : grad_phase_application_time + grad_rise_time * 2);

        double time3 = (getTimeBetweenEvents(Events.Acq, Events.Acq + 1) - observation_time) + getTimeBetweenEvents(Events.Delay3 + 1, Events.LoopEndEcho);
        time3 = time3 + observation_time / 2.0;
//        time3 = time3 + (isMultiplanar ? (-getTimeBetweenEvents(Events.Acq + 2, Events.Acq + 4) + 3 * min_instruction_delay) : 0) + min_instruction_delay;
        time3 = time3 + (isMultiplanar ? 3 * min_instruction_delay : grad_phase_application_time + grad_rise_time * 2);

        double time3_for_min_FIR_delay = min_FIR_4pts_delay + getTimeBetweenEvents(Events.LoopEndEcho, Events.LoopEndEcho) + observation_time / 2.0;
        double time3_min = Math.max(time3, time3_for_min_FIR_delay);
//        System.out.println(" + + + + + + ");
//        System.out.println( time3_min+" +  "+time3 + "  D3 ="+delay3);
        double time3bis = getTimeBetweenEvents(Events.LoopStartEcho, Events.TX180 - 1);
        time3bis = time3bis + txLength180 / 2.0;

        // FIR delay from one ETL to the next
        double time4_for_min_FIR_delay = Math.max(min_FIR_delay, min_instruction_delay) + observation_time;

        // get minimal TE value & search for incoherence

        double max_time = ceilToSubDecimal(Math.max(time1, Math.max(time2_min, (time3_min + time3bis))), 5);
        double te_min = Math.max(2 * (max_time + min_instruction_delay), time4_for_min_FIR_delay);
        te_min = ceilToSubDecimal(te_min, 5);

        //double new_te = te;
        // calculate echo time depending on image contrast and transform plugin
        if (te < te_min) {
            if (te_min > TE_TR_lim[1]) {
                getUnreachParamExceptionManager().addParam(ECHO_TIME.name(), te, te_min, ((NumberParam) getParam(ECHO_TIME)).getMaxValue(), "(1):TE too short for the User Mx1D and SW (2):as TE increases, T1-weighted or PD-weighted imaging is not guaranteed");
                //setParamValue(rs2d.sequence.spinecho.SPIN_ECHO_devParams.IMAGE_CONTRAST, "Custom");
            } else {
                getUnreachParamExceptionManager().addParam(ECHO_TIME.name(), te, te_min, ((NumberParam) getParam(ECHO_TIME)).getMaxValue(), "TE too short for the User Mx1D and SW");
                //te = te_min;
            }
            te = te_min;
        }

        if (is_FSE_vs_MultiEcho) {
            if (!("Custom".equalsIgnoreCase((String) (getParam(IMAGE_CONTRAST).getValue())))) {
                echo_spacing = te_min; // echo spacing should be minimal for FSE if not "Custom"
            } else { // if "Custom" FSE, user can change echo spacing
                if (echo_spacing < te_min) {
                    echo_spacing = te_min;
                }
                if (echoTrainLength == 1) {
                    echo_spacing = te;
                }
            }
        } else { // if MultiEcho, user can change echo time
            if (echoTrainLength == 1) {
                switch ((String) getParam(IMAGE_CONTRAST).getValue()) {
                    case "T1-weighted":
                    case "PD-weighted":
                        te = te_min;
                        break;
                    case "T2-weighted":
                        te = TE_TR_lim[0];
                        if (te < te_min) {
                            te = te_min;
                        }
                        break;
                    default: // Custom
                        break;
                }
            }
            echo_spacing = te;
        }

        if (echoTrainLength > 1) {
            double previous_echo_spacing = ((NumberParam) getParam(ECHO_SPACING)).getValue().doubleValue();
            if (roundToDecimal(previous_echo_spacing, 5) != roundToDecimal(echo_spacing, 5)) {
                this.setParamValue(ECHO_SPACING, echo_spacing);
            }
        }

        // ------------------------------------------
        // calculate delays adapted to current TE 2/2 :calculated  delays to get the proper TE
        // ------------------------------------------
        // set calculated the delay1
        double delay1 = echo_spacing / 2.0 - time1;
        double grad_read_prep_application_time_2 = min_instruction_delay;
        double grad_rise_time_read_2 = min_instruction_delay;
        boolean enable_read_prep_2 = false;
        if (delay1 + (3 * min_instruction_delay) > grad_read_prep_application_time + 2 * grad_rise_time + min_instruction_delay) {
            delay1 = delay1 + 3 * min_instruction_delay - (grad_read_prep_application_time + 2 * grad_rise_time);
            grad_rise_time_read_2 = grad_rise_time;
            grad_read_prep_application_time_2 = grad_read_prep_application_time;
            enable_read_prep_2 = true;
        }
        setSequenceTableSingleValue(Time_grad_ramp_read_2, grad_rise_time_read_2);
        setSequenceTableSingleValue(Time_grad_read_prep_top_2, grad_read_prep_application_time_2);
        setSequenceParamValue(Grad_enable_read_prep_1, (!enable_read_prep_2 && isEnableRead));
        setSequenceParamValue(Grad_enable_read_prep_2, (enable_read_prep_2 && isEnableRead));
        setSequenceTableSingleValue(Time_TE_delay1, delay1);

        // set calculated the delay2 and delay3
        double delay2 = echo_spacing / 2.0 - time2_min;
        double delay3 = echo_spacing / 2.0 - (time3_min + time3bis);

        double grad_phase_application_time_2 = isMultiplanar ? min_instruction_delay : grad_phase_application_time;
        double grad_rise_time_phase_2 = isMultiplanar ? min_instruction_delay : grad_rise_time;
        boolean enable_phase_2 = !isMultiplanar;

        // in 2D if the delay is loong pack the Phase close to the redaout
        if ((delay2 + (3 * min_instruction_delay - default_instruction_delay) > grad_phase_application_time + 2 * grad_rise_time) && (delay3 + (3 * min_instruction_delay - default_instruction_delay) > grad_phase_application_time + 2 * grad_rise_time)) {
            delay2 = delay2 + 3 * min_instruction_delay - (grad_phase_application_time + 2 * grad_rise_time);
            delay3 = delay3 + 3 * min_instruction_delay - (grad_phase_application_time + 2 * grad_rise_time);
            grad_rise_time_phase_2 = grad_rise_time;
            grad_phase_application_time_2 = grad_phase_application_time;
            enable_phase_2 = true;
        }
        if (echoTrainLength == 1) {
            delay3 = min_instruction_delay;
        }

        setSequenceTableSingleValue(Time_grad_ramp_phase, grad_rise_time_phase_2);
        setSequenceTableSingleValue(Time_grad_phase_top, grad_phase_application_time_2);
        setSequenceParamValue(Grad_enable_phase_2D, enable_phase_2 && isEnablePhase);
        setSequenceParamValue(Grad_enable_phase_bis, !enable_phase_2 && isEnablePhase);
        setSequenceTableSingleValue(Time_TE_delay2, delay2);
        setSequenceTableSingleValue(Time_TE_delay3, delay3);

        // calculate Effective echo time
        double effective_te = echo_spacing; //  in case of Sequential4D and Centered2D
        if (is_FSE_vs_MultiEcho) {
            int SignificantEcho = plugin.getSignificantEcho();
            effective_te = echo_spacing * SignificantEcho;
        }
        double previous_effective_te = ((NumberParam) getParam(ECHO_TIME_EFFECTIVE)).getValue().doubleValue();
        if (roundToDecimal(previous_effective_te, 5) != roundToDecimal(effective_te, 5)) {
            this.setParamValue(ECHO_TIME_EFFECTIVE, effective_te);
        }
        double previous_te = ((NumberParam) getParam(ECHO_TIME)).getValue().doubleValue();
        if (roundToDecimal(previous_te, 5) != roundToDecimal(effective_te, 5)) {
            this.setParamValue(ECHO_TIME, effective_te);
        }

        // -------------------------------------------------------------------------------------------------
        // SPoiler Gradient
        // -------------------------------------------------------------------------------------------------
        Gradient gradSliceSpoiler = Gradient.createGradient(getSequence(), Grad_amp_spoiler_slice, Time_grad_crusher_top, Grad_shape_rise_up, Grad_shape_rise_down, Time_grad_ramp);
        boolean is_spoiler = ((BooleanParam) getParam(GRADIENT_ENABLE_SPOILER)).getValue();
        if (is_spoiler) {
            gradSliceSpoiler.addSpoiler(((NumberParam) getParam(GRADIENT_AMP_SPOILER)).getValue().doubleValue());
        }
        gradSliceSpoiler.applyAmplitude();

        //--------------------------------------------------------------------------------------
        //  External triggering
        //--------------------------------------------------------------------------------------
        setSequenceParamValue(Synchro_trigger, isTrigger ? TimeElement.Trigger.External : TimeElement.Trigger.Timer);
        double time_external_trigger_delay_max = min_instruction_delay;

        Table triggerdelay = setSequenceTableValues(Time_trigger_delay, Order.Four);
        if ((!isTrigger)) {
            triggerdelay.add(min_instruction_delay);
        } else {
            for (int i = 0; i < numberOfTrigger; i++) {
                double time_external_trigger_delay = roundToDecimal(triggerTime.getValue().get(i).doubleValue(), 7);
                time_external_trigger_delay = time_external_trigger_delay < default_instruction_delay ? default_instruction_delay : time_external_trigger_delay;
                triggerdelay.add(time_external_trigger_delay);
                time_external_trigger_delay_max = Math.max(time_external_trigger_delay_max, time_external_trigger_delay);
            }
        }

        // -------------------------------------------------------------------------------------------------
        // INVERSION RECOVERY
        // -------------------------------------------------------------------------------------------------
        if (isInversionRecovery) {
            boolean is_IR_crusher = ((BooleanParam) getParam(GRADIENT_ENABLE_CRUSHER_IR)).getValue();
            // prepare Crusher gradient
            if (is_IR_crusher) {
                double time_grad_IR_tmp_top = ((NumberParam) getParam(GRADIENT_CRUSHER_IR_TOP_TIME)).getValue().doubleValue();

                // IR Crusher:
                double grad_amp_crusher_IR = ((NumberParam) getParam(GRADIENT_AMP_CRUSHER_IR)).getValue().doubleValue();
                setSequenceTableSingleValue(Grad_amp_crusher_IR, grad_amp_crusher_IR);
                setSequenceTableSingleValue(Time_grad_IR_crusher_top, time_grad_IR_tmp_top);
                setSequenceTableSingleValue(Time_grad_IR_crusher_ramp, grad_rise_time);
            } else {
                setSequenceTableSingleValue(Time_grad_IR_crusher_top, default_instruction_delay);
                setSequenceTableSingleValue(Time_grad_IR_crusher_ramp, default_instruction_delay);
            }
            setSequenceTableSingleValue(Time_tx_IR_length, txLength180);
            setSequenceTableSingleValue(Time_grad_IR_ramp, grad_rise_time);
        } else {
            setSequenceTableSingleValue(Time_tx_IR_length, default_instruction_delay);
            setSequenceTableSingleValue(Time_grad_IR_ramp, default_instruction_delay);
            setSequenceTableSingleValue(Time_grad_IR_crusher_top, default_instruction_delay);
            setSequenceTableSingleValue(Time_grad_IR_crusher_ramp, default_instruction_delay);
        }

        // ------------------------------------------
        // calculate delays adapted to current TI
        // ------------------------------------------
        int number_of_IR_acquisition = isInversionRecovery ? inversionRecoveryTime.getValue().size() : 1;
        double time_IR_delay_max = 0.00001;

        Table time_TI_delay = getSequence().getPublicTable("Time_TI_delay");
        time_TI_delay.clear();

        //  double time_TI_delay;
        if (isInversionRecovery) {
            //TI
            double time0 = getTimeBetweenEvents(Events.IR + 1, Events.IRDelay - 1) + getTimeBetweenEvents(Events.IRDelay + 1, Events.TX90 - 1);
            time0 = time0 + txLength90 / 2.0 + txLength180 / 2.0;
            boolean increaseTI = false;
            // ArrayList<Number> arrayListTI = new ArrayList<Number>();
            ArrayList<Number> arrayListTI_min = new ArrayList<Number>();
            for (int i = 0; i < number_of_IR_acquisition; i++) {
                double IR_time = inversionRecoveryTime.getValue().get(i).doubleValue();
                // arrayListTI.add(IR_time);
                double delay0 = IR_time - time0;

                if ((delay0 < default_instruction_delay)) {
                    double ti_min = ceilToSubDecimal((time0 + default_instruction_delay), 4);
//                    System.out.println("IR_time-" + IR_time + " -- >" + ti_min + " car " + delay0);
                    IR_time = ti_min;
                    increaseTI = true;
                }
                arrayListTI_min.add(IR_time);
                time_IR_delay_max = Math.max(time_IR_delay_max, IR_time);
                delay0 = IR_time - time0;
                time_TI_delay.add(delay0);
            }
            if (increaseTI) {
                System.out.println(" --- -- -- - - - increaseTI-------------------- ");

                inversionRecoveryTime.getValue().clear();
                inversionRecoveryTime.getValue().addAll(arrayListTI_min);
                //this.getUnreachParamExceptionManager().addParam(rs2d.sequence.spinecho.SPIN_ECHO_devParams.INVERSION_TIME_MULTI.name(), arrayListTI, arrayListTI_min, ((NumberParam) getParam(rs2d.sequence.spinecho.SPIN_ECHO_devParams.INVERSION_TIME_MULTI")).getMaxValue(), "TI too short");
            }
            time_TI_delay.setOrder(Order.Four);
            time_TI_delay.setLocked(true);
        } else {
            time_IR_delay_max = default_instruction_delay;
            time_TI_delay.add(default_instruction_delay);
        }

        // ------------------------------------------
        // calculate TR & search for incoherence
        // ------------------------------------------
        int nb_planar_excitation = (isMultiplanar ? acquisitionMatrixDimension3D : 1);
        int slices_acquired_in_single_scan = (nb_planar_excitation > 1) ? (nbOfInterleavedSlice) : 1;
        double delay_before_multi_planar_loop = getTimeBetweenEvents(Events.Start, Events.TriggerDelay - 1) + getTimeBetweenEvents(Events.TriggerDelay + 1, Events.LoopMultiPlanarStart - 1) + time_external_trigger_delay_max;
        double delay_before_echo_loop = getTimeBetweenEvents(Events.LoopMultiPlanarStart, Events.IRDelay - 1) + getTimeBetweenEvents(Events.IRDelay + 1, Events.LoopStartEcho - 1) + time_IR_delay_max;
        double delay_echo_loop = getTimeBetweenEvents(Events.LoopStartEcho, Events.LoopEndEcho);
        double delay_spoiler = getTimeBetweenEvents(Events.LoopEndEcho + 1, Events.LoopMultiPlanarEnd - 2);// grad_phase_application_time + grad_rise_time * 2;
        double min_flush_delay = min_time_per_acq_point * acquisitionMatrixDimension1D * echoTrainLength * slices_acquired_in_single_scan * 2;   // minimal time to flush Chameleon buffer (this time is doubled to avoid hidden delays);
        min_flush_delay = Math.max(min_flush_delay, default_instruction_delay);

        double time_seq_to_end_spoiler = (delay_before_multi_planar_loop + (delay_before_echo_loop + (echoTrainLength * delay_echo_loop) + delay_spoiler) * slices_acquired_in_single_scan);
        double tr_min = time_seq_to_end_spoiler + default_instruction_delay * (slices_acquired_in_single_scan * 2 + 1) + min_instruction_delay;// 2 +( 2 default_instruction_delay: Events.event 22 +(20&21
        tr_min = ceilToSubDecimal(tr_min, 4);

        switch ((String) getParam(IMAGE_CONTRAST).getValue()) {
            case "Custom":
                break;
            default:
                if (tr_min < TE_TR_lim[2]) {
                    tr = TE_TR_lim[2]; // TR should be not lower than the inferior limit
                } else {
                    tr = tr_min; // TR should be minimal
                }
                double previous_tr = ((NumberParam) getParam(REPETITION_TIME)).getValue().doubleValue();
                if (roundToDecimal(previous_tr, 5) != roundToDecimal(tr, 5)) {
                    this.setParamValue(REPETITION_TIME, tr);
                }
                break;
        }
        if (tr < tr_min) {
            //tr_min = Math.ceil(tr_min * Math.pow(10, 3)) / Math.pow(10, 3);
            if (tr_min > TE_TR_lim[3]) {
                getUnreachParamExceptionManager().addParam(REPETITION_TIME.name(), tr, tr_min, ((NumberParam) getParam(REPETITION_TIME)).getMaxValue(), "(1): TR too short to reach (ETL * User Mx3D/Shoot3D) in a singl scan - (2):as TR increases, T1-weighted imaging is not guaranteed");
                //setParamValue(rs2d.sequence.spinecho.SPIN_ECHO_devParams.IMAGE_CONTRAST, "Custom");
            } else {
                getUnreachParamExceptionManager().addParam(REPETITION_TIME.name(), tr, tr_min, ((NumberParam) getParam(REPETITION_TIME)).getMaxValue(), "TR too short to reach (ETL * User Mx3D/Shoot3D) in a singl scan");
            }
            tr = tr_min;
        }

        // ------------------------------------------
        // set calculated TR
        // ------------------------------------------
        // set  TR delay to compensate and trigger delays
        double last_delay = min_instruction_delay;
        double tr_delay;
        Table time_tr_delay = setSequenceTableValues(Time_TR_delay, Order.Four);
        if (number_of_IR_acquisition != 1) {
            for (int i = 0; i < number_of_IR_acquisition; i++) {
                double tmp_time_seq_to_end_spoiler = time_seq_to_end_spoiler + (time_TI_delay.get(i).doubleValue() - time_IR_delay_max) * slices_acquired_in_single_scan;
                tr_delay = (tr - (tmp_time_seq_to_end_spoiler - +last_delay + min_instruction_delay)) / slices_acquired_in_single_scan - default_instruction_delay;
                time_tr_delay.add(tr_delay);
            }
        } else if (numberOfTrigger != 1) {
            for (int i = 0; i < numberOfTrigger; i++) {
                double tmp_time_seq_to_end_spoiler = time_seq_to_end_spoiler - time_external_trigger_delay_max + triggerdelay.get(i).doubleValue();
                tr_delay = (tr - (tmp_time_seq_to_end_spoiler - +last_delay + min_instruction_delay)) / slices_acquired_in_single_scan - default_instruction_delay;
                time_tr_delay.add(tr_delay);
            }
        } else {
            tr_delay = (tr - (time_seq_to_end_spoiler + last_delay + min_instruction_delay)) / slices_acquired_in_single_scan - default_instruction_delay;
            time_tr_delay.add(tr_delay);
        }
        setSequenceTableSingleValue(Time_last_delay, last_delay);
        setSequenceTableSingleValue(Time_flush_delay, min_flush_delay);

        //----------------------------------------------------------------------
        // DYNAMIC SEQUENCE
        // Calculate frame acquisition time
        // Calculate delay between 4D acquisition
        //----------------------------------------------------------------------
        boolean is_dynamic_min_time = ((BooleanParam) getParam(DYNAMIC_MIN_TIME)).getValue();

        Table dyn_delay = setSequenceTableValues(Time_btw_dyn_frames, Order.Four);
        double frame_acquisition_time = nb_scan_1d * nb_scan_3d * nb_scan_2d * tr;
        double time_between_frames_min = ceilToSubDecimal((frame_acquisition_time * number_of_IR_acquisition + min_instruction_delay * (number_of_IR_acquisition)), 1);
        double time_between_frames = time_between_frames_min;
        double interval_between_frames_delay = min_flush_delay;

        if (isDynamic) {
            //Dynamic Sequence
            time_between_frames = ((NumberParam) getParam(DYN_TIME_BTW_FRAMES)).getValue().doubleValue();
            if (is_dynamic_min_time) {
                time_between_frames = time_between_frames_min;
                setParamValue(DYN_TIME_BTW_FRAMES, time_between_frames_min);
            } else if (time_between_frames < (time_between_frames_min)) {
                this.getUnreachParamExceptionManager().addParam(DYN_TIME_BTW_FRAMES.name(), time_between_frames, time_between_frames_min, ((NumberParam) getParam(DYN_TIME_BTW_FRAMES)).getMaxValue(), "Minimum frame acquisition time ");
                time_between_frames = time_between_frames_min;
            }
            interval_between_frames_delay = Math.max(time_between_frames - frame_acquisition_time * number_of_IR_acquisition - min_instruction_delay * (number_of_IR_acquisition - 1), min_instruction_delay);
            if (number_of_IR_acquisition != 1) {
                for (int i = 0; i < number_of_IR_acquisition - 1; i++) {
                    dyn_delay.add(min_instruction_delay);
                }
                dyn_delay.add(interval_between_frames_delay);
            } else {
                dyn_delay.add(interval_between_frames_delay);
            }
        } else {
            dyn_delay.add(interval_between_frames_delay);
        }

        // ------------------------------------------------------------------
        // Total Acquisition Time
        // ------------------------------------------------------------------
        double total_acquisition_time = (frame_acquisition_time * number_of_IR_acquisition + min_instruction_delay * (number_of_IR_acquisition - 1) + interval_between_frames_delay) * numberOfDynamicAcquisition + tr * preScan;
        setParamValue(SEQUENCE_TIME, total_acquisition_time);

        // -----------------------------------------------
        // Phase Reset
        // -----------------------------------------------
        setSequenceParamValue(Phase_reset, PHASE_RESET);

        // ----------- init Freq offset---------------------
        setSequenceTableSingleValue(Frequency_offset_init, 0.0);// PSD should start with a zero offset frequency pulse

        // ------------------------------------------------------------------
        //calculate TX FREQUENCY offsets tables for slice positionning
        // ------------------------------------------------------------------
        RFPulse pulseTXIR = RFPulse.createRFPulse(getSequence(), Tx_att, Tx_amp_180, Tx_phase_180, Time_tx_IR_length, Tx_shape_180, Tx_shape_phase_180, Tx_freq_offset_IR);

        if (isMultiplanar && nb_planar_excitation > 1 && isEnableSlice) {
            //MULTI-PLANAR case : calculation of frequency offset table
            pulseTX90.prepareOffsetFreqMultiSlice(gradSlice90, nb_planar_excitation, spacing_between_slice, off_center_distance_3D);
            pulseTX90.reoderOffsetFreq(plugin, acquisitionMatrixDimension1D * echoTrainLength, slices_acquired_in_single_scan);
            pulseTX90.setFrequencyOffset(slices_acquired_in_single_scan != 1 ? Order.ThreeLoop : Order.Three);
            pulseTX180.prepareOffsetFreqMultiSlice(gradSlice180, nb_planar_excitation, spacing_between_slice, off_center_distance_3D);
            pulseTX180.reoderOffsetFreq(plugin, acquisitionMatrixDimension1D * echoTrainLength, slices_acquired_in_single_scan);
            pulseTX180.setFrequencyOffset(slices_acquired_in_single_scan != 1 ? Order.ThreeLoop : Order.Three);
            if (isInversionRecovery) {
                pulseTXIR.prepareOffsetFreqMultiSlice(gradSlice180, nb_planar_excitation, spacing_between_slice, off_center_distance_3D);
                pulseTXIR.reoderOffsetFreq(plugin, acquisitionMatrixDimension1D * echoTrainLength, slices_acquired_in_single_scan);
            }
            pulseTXIR.setFrequencyOffset(isInversionRecovery ? (slices_acquired_in_single_scan != 1 ? Order.ThreeLoop : Order.Three) : Order.FourLoop);

        } else {
            //3D CASE :
            pulseTX90.prepareOffsetFreqMultiSlice(gradSlice90, 1, 0, off_center_distance_3D);
            pulseTX90.setFrequencyOffset(Order.Three);
            pulseTX180.prepareOffsetFreqMultiSlice(gradSlice180, 1, 0, off_center_distance_3D);
            pulseTX180.setFrequencyOffset(Order.Three);
            if (isInversionRecovery) {
                pulseTXIR.prepareOffsetFreqMultiSlice(gradSlice180, 1, 0, off_center_distance_3D);
            }
            pulseTXIR.setFrequencyOffset(isInversionRecovery ? Order.Three : Order.FourLoop);
        }

        // ------------------------------------------------------------------
        // calculate TX FREQUENCY offsets tables for multi-slice acquisitions and
        // ------------------------------------------------------------------
        RFPulse pulseTX90Prep = RFPulse.createRFPulse(getSequence(), Time_grad_ramp, FreqOffset_tx_prep_90);
        pulseTX90Prep.setCompensationFrequencyOffset(pulseTX90, grad_ratio_slice_refoc);

        RFPulse pulseTX180Prep = RFPulse.createRFPulse(getSequence(), Time_grad_ramp, FreqOffset_tx_prep_180);
        pulseTX180Prep.setCompensationFrequencyOffset(pulseTX180, grad_ratio_slice_refoc);

        RFPulse pulseTXIRPrep = RFPulse.createRFPulse(getSequence(), Time_grad_ramp, FreqOffset_tx_prep_IR);
        pulseTXIRPrep.setCompensationFrequencyOffset(pulseTXIR, grad_ratio_slice_refoc);

        //----------------------------------------------------------------------
        // OFF CENTER FIELD OF VIEW 1D
        // modify RX FREQUENCY OFFSET
        //----------------------------------------------------------------------
        RFPulse pulseRX = RFPulse.createRFPulse(getSequence(), Time_rx, Rx_freq_offset, Rx_phase);
        pulseRX.setFrequencyOffsetReadout(gradReadout, off_center_distance_1D);

        //fill the OFF_CENTER_FIELD_OF_VIEW_EFF User Parameter
        ArrayList<Number> off_center_distanceList = new ArrayList<>();
        off_center_distanceList.add(off_center_distance_1D);
        off_center_distanceList.add(0);
        off_center_distanceList.add(0);

        setParamValue(OFF_CENTER_FIELD_OF_VIEW_EFF, off_center_distanceList);

        //----------------------------------------------------------------------
        // modify RX FREQUENCY Prep and comp
        //----------------------------------------------------------------------
        double timeADC1 = getTimeBetweenEvents(Events.Acq - 1, Events.Acq - 1) + observation_time / 2.0;
        double timeADC2 = getTimeBetweenEvents(Events.Acq, Events.LoopEndEcho - 1) - observation_time + observation_time / 2.0;

        RFPulse pulseRXPrep = RFPulse.createRFPulse(getSequence(), Time_grad_ramp, FreqOffset_rx_1D_3Dprep);
        pulseRXPrep.setCompensationFrequencyOffsetWithTime(pulseRX, timeADC1);

        RFPulse pulseRXComp = RFPulse.createRFPulse(getSequence(), Time_min_instruction, FreqOffset_rx_1D_3Dcomp);
        pulseRXComp.setCompensationFrequencyOffsetWithTime(pulseRX, timeADC2);

        //--------------------------------------------------------------------------------------
        // modify TX PHASE TABLE to handle PHASE CYCLING
        //--------------------------------------------------------------------------------------
        boolean is_phase_cycling = ((BooleanParam) getParam(PHASE_CYCLING)).getValue();
        if (is_phase_cycling) { // to do modify the phase cycling
            pulseTX90.setPhase(number_of_averages != 1 ? Order.One : Order.Two, 0.0, 180.0);
            pulseRX.setPhase(number_of_averages != 1 ? Order.One : Order.Two, 0.0, 180.0);
        } else {
            pulseTX90.setPhase(0.0);
            pulseRX.setPhase(0.0);
        }
        pulseTX180.setPhase(90);

        //--------------------------------------------------------------------------------------
        //Export DICOM
        //--------------------------------------------------------------------------------------

        // Set  TRIGGER_TIME for dynamic or trigger acquisition
        ArrayList<Number> arrayListTrigger = new ArrayList<>();
        if (isDynamic && (isInversionRecovery && numberOfDynamicAcquisition != 1)) {
            if (!is_FSE_vs_MultiEcho && echoTrainLength != 1) {
                for (int i = 0; i < acquisitionMatrixDimension4D; i++) {
                    arrayListTrigger.add(Math.floor(i / echoTrainLength) * time_between_frames);
                }
            } else if (isInversionRecovery && number_of_IR_acquisition != 1) {
                for (int i = 0; i < acquisitionMatrixDimension4D; i++) {
                    arrayListTrigger.add(Math.floor(i / number_of_IR_acquisition) * time_between_frames);
                }
            } else {
                for (int i = 0; i < acquisitionMatrixDimension4D; i++) {
                    arrayListTrigger.add(i * time_between_frames);
                }
            }
            if (!isTrigger) {
                ListNumberParam list = new ListNumberParam((NumberParam) getParamFromName(MriDefaultParams.TRIGGER_TIME.name()), arrayListTrigger);       // associate TE to images for DICOM export
                putVariableParameter(list, (4));
            }
        }

        // Set  ECHO_TIME
        if (!is_FSE_vs_MultiEcho) {
            ArrayList<Number> arrayListEcho = new ArrayList<>();
            for (int i = 0; i < acquisitionMatrixDimension4D; i++) {
                arrayListEcho.add(te * i);
            }
            ListNumberParam list = new ListNumberParam((NumberParam) getParamFromName(MriDefaultParams.ECHO_TIME.name()), arrayListEcho);       // associate TE to images for DICOM export
            putVariableParameter(list, (4));
        }

        if (isInversionRecovery) {
            ArrayList<Number> arrayListIR = new ArrayList<>();
            for (int i = 0; i < acquisitionMatrixDimension4D; i++) {
                arrayListIR.add(inversionRecoveryTime.getValue().get((i % number_of_IR_acquisition)).doubleValue());
            }
            ListNumberParam list = new ListNumberParam(MriDefaultParams.INVERSION_TIME.name(), arrayListIR, NumberEnum.Time);       // associate TE to images for DICOM export
            putVariableParameter(list, (4));
        }

        // ------------------------------------------------------------------
        // calculate Acquisition Time Offset and MultiSeries Parameter list
        // ------------------------------------------------------------------

        int number_of_MultiSeries = 1;
        double time_between_MultiSeries = 0;
        ArrayList<Number> multiseries_valuesList = new ArrayList<>();
        String multiseries_parametername = "";

        if ((!is_FSE_vs_MultiEcho && echoTrainLength != 1)) {
            number_of_MultiSeries = echoTrainLength;
            time_between_MultiSeries = echo_spacing;
            multiseries_parametername = "TE";
            for (int i = 1; i <= number_of_MultiSeries; i++) {
                double multiseries_value = roundToDecimal(i * te, 5) * 1e3;
                multiseries_valuesList.add(multiseries_value);
            }
        } else if (isInversionRecovery && number_of_IR_acquisition != 1) {
            number_of_MultiSeries = number_of_IR_acquisition;
            time_between_MultiSeries = frame_acquisition_time;
            multiseries_parametername = "TI";
            for (int i = 0; i < number_of_MultiSeries; i++) {
                double IR_time = roundToDecimal(inversionRecoveryTime.getValue().get(i).doubleValue(), 5) * 1e3;
                multiseries_valuesList.add(IR_time);
            }
        } else if (isTrigger && numberOfTrigger != 1) {
            number_of_MultiSeries = numberOfTrigger;
            time_between_MultiSeries = frame_acquisition_time;
            multiseries_parametername = "TRIGGER DELAY";
            for (int i = 0; i < number_of_MultiSeries; i++) {
                double multiseries_value = roundToDecimal(triggerTime.getValue().get(i).doubleValue(), 5) * 1e3;
                multiseries_valuesList.add(multiseries_value);
            }
        }
        setParamValue(MULTISERIES_PARAMETER_VALUE, multiseries_valuesList);
        setParamValue(MULTISERIES_PARAMETER_NAME, multiseries_parametername);

        ArrayList<Number> acquisition_timesList = new ArrayList<>();
        int dummy_scans = ((NumberParam) getParam(DUMMY_SCAN)).getValue().intValue();
        double acqusition_time;
        for (int i = 0; i < numberOfDynamicAcquisition; i++) {
            for (int j = 0; j < number_of_MultiSeries; j++) {
                acqusition_time = (i * time_between_frames + j * time_between_MultiSeries);
                if (i > 0) { // only the first dynamic phase has dummy scans
                    acqusition_time = acqusition_time + dummy_scans * tr;
                }
                acqusition_time = roundToDecimal(acqusition_time, 3);
                acquisition_timesList.add(acqusition_time);
            }
        }
        setParamValue(ACQUISITION_TIME_OFFSET, acquisition_timesList);
        //--------------------------------------------------------------------------------------
        // Comments
        //--------------------------------------------------------------------------------------
        if (false) { // Show the comments
            System.out.println("");
            System.out.println((((NumberParam) getSequence().getParam(Nb_1d)).getValue().intValue()));
            System.out.println((((NumberParam) getSequence().getParam(Nb_2d)).getValue().intValue()));
            System.out.println((((NumberParam) getSequence().getParam(Nb_3d)).getValue().intValue()));
            System.out.println((((NumberParam) getSequence().getParam(Nb_4d)).getValue().intValue()));
            System.out.println((((NumberParam) getSequence().getParam(Nb_echo)).getValue().intValue()));
            System.out.println((((NumberParam) getSequence().getParam(Nb_interleaved_slice)).getValue().intValue()));
            System.out.println("");

            for (int i = 0; i < 58; i++) {
                System.out.println((((TimeElement) getSequence().getTimeChannel().get(i)).getTime().getFirst().doubleValue() * 1000000));
            }
        }

    }
    // --------------------------------------------------------------------------------------------------------------------------------------------
    // End After Routine
    // --------------------------------------------------------------------------------------------------------------------------------------------
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    //
    //                                                          END OF SEQUENCE GENERATOR
    //
    // *********************************************************************************************************************************************
    // *** END OF SEQUENCE GENERATOR *********  END OF SEQUENCE GENERATOR *********  END OF SEQUENCE GENERATOR ********* END OF SEQUENCE GENERATOR
    // *********************************************************************************************************************************************


    private double getTx_bandwidth_factor_90(SpinEchoParams tx_shape, SpinEchoParams tx_bandwith_factor_param, SpinEchoParams tx_bandwith_factor_param3d) {
        double tx_bandwidth_factor_90;
        ListNumberParam tx_bandwith_factor_table = (ListNumberParam) getParam(tx_bandwith_factor_param);
        ListNumberParam tx_bandwith_factor_3D_table = (ListNumberParam) getParam(tx_bandwith_factor_param3d);

        if (isMultiplanar) {
            if ("GAUSSIAN".equalsIgnoreCase((String) getParam(tx_shape).getValue())) {
                tx_bandwidth_factor_90 = tx_bandwith_factor_table.getValue().get(1).doubleValue();
            } else if ("SINC3".equalsIgnoreCase((String) getParam(tx_shape).getValue())) {
                tx_bandwidth_factor_90 = tx_bandwith_factor_table.getValue().get(2).doubleValue();
            } else if ("SINC5".equalsIgnoreCase((String) getParam(tx_shape).getValue())) {
                tx_bandwidth_factor_90 = tx_bandwith_factor_table.getValue().get(3).doubleValue();
            } else {
                tx_bandwidth_factor_90 = tx_bandwith_factor_table.getValue().get(0).doubleValue();
            }
        } else {
            if ("GAUSSIAN".equalsIgnoreCase((String) getParam(tx_shape).getValue())) {
                tx_bandwidth_factor_90 = tx_bandwith_factor_3D_table.getValue().get(1).doubleValue();
            } else if ("SINC3".equalsIgnoreCase((String) getParam(tx_shape).getValue())) {
                tx_bandwidth_factor_90 = tx_bandwith_factor_3D_table.getValue().get(2).doubleValue();
            } else if ("SINC5".equalsIgnoreCase((String) getParam(tx_shape).getValue())) {
                tx_bandwidth_factor_90 = tx_bandwith_factor_3D_table.getValue().get(3).doubleValue();
            } else {
                tx_bandwidth_factor_90 = tx_bandwith_factor_3D_table.getValue().get(0).doubleValue();
            }
        }
        return tx_bandwidth_factor_90;
    }

    private double ceilToSubDecimal(double numberToBeRounded, double Order) {
        return Math.ceil(numberToBeRounded * Math.pow(10, Order)) / Math.pow(10, Order);
    }

    private double roundToDecimal(double numberToBeRounded, double order) {
        return Math.round(numberToBeRounded * Math.pow(10, order)) / Math.pow(10, order);
    }


    private Table setSequenceTableSingleValue(String tableName, double... values) {
        // uses Order.One because there are no tables in this dimension: compilation issue
        return setSequenceTableValues(tableName, Order.FourLoop, values);
    }

    private Table setSequenceTableValues(String tableName, Order order, double... values) {
        Table table = getSequence().getPublicTable(tableName);
        table.clear();
        table.setOrder(order);
        table.setLocked(true);

        for (double value : values) {
            table.add(value);
        }
        return table;
    }


    private double getOff_center_distance_1D_2D_3D(int dim) {
        ListNumberParam image_orientation = (ListNumberParam) getParam(IMAGE_ORIENTATION_SUBJECT);
        double[] direction_index = new double[9];
        direction_index[0] = image_orientation.getValue().get(0).doubleValue();
        direction_index[1] = image_orientation.getValue().get(1).doubleValue();
        direction_index[2] = image_orientation.getValue().get(2).doubleValue();
        direction_index[3] = image_orientation.getValue().get(3).doubleValue();
        direction_index[4] = image_orientation.getValue().get(4).doubleValue();
        direction_index[5] = image_orientation.getValue().get(5).doubleValue();
        direction_index[6] = direction_index[1] * direction_index[5] - direction_index[2] * direction_index[4];
        direction_index[7] = direction_index[2] * direction_index[3] - direction_index[0] * direction_index[5];
        direction_index[8] = direction_index[0] * direction_index[4] - direction_index[1] * direction_index[3];

        double norm_vector_read = Math.sqrt(Math.pow(direction_index[0], 2) + Math.pow(direction_index[1], 2) + Math.pow(direction_index[2], 2));
        double norm_vector_phase = Math.sqrt(Math.pow(direction_index[3], 2) + Math.pow(direction_index[4], 2) + Math.pow(direction_index[5], 2));
        double norm_vector_slice = Math.sqrt(Math.pow(direction_index[6], 2) + Math.pow(direction_index[7], 2) + Math.pow(direction_index[8], 2));

        //Offset according to animal position
        double off_center_distance_Z = ((NumberParam) getParam(OFF_CENTER_FIELD_OF_VIEW_Z)).getValue().doubleValue();
        double off_center_distance_Y = ((NumberParam) getParam(OFF_CENTER_FIELD_OF_VIEW_Y)).getValue().doubleValue();
        double off_center_distance_X = ((NumberParam) getParam(OFF_CENTER_FIELD_OF_VIEW_X)).getValue().doubleValue();

        //Offset according to READ PHASE and SLICE
        double off_center_distance;
        switch (dim) {
            case 1:
                off_center_distance = off_center_distance_X * direction_index[0] / norm_vector_read + off_center_distance_Y * direction_index[1] / norm_vector_read + off_center_distance_Z * direction_index[2] / norm_vector_read;
                break;
            case 2:
                off_center_distance = off_center_distance_X * direction_index[3] / norm_vector_phase + off_center_distance_Y * direction_index[4] / norm_vector_phase + off_center_distance_Z * direction_index[5] / norm_vector_phase;
                break;
            case 3:
                off_center_distance = off_center_distance_X * direction_index[6] / norm_vector_slice + off_center_distance_Y * direction_index[7] / norm_vector_slice + off_center_distance_Z * direction_index[8] / norm_vector_slice;
                break;
            default:
                off_center_distance = 0;
                break;
        }
        return off_center_distance;
    }


    /**
     * Find the next inferior integer which can divide the dividend : dividend /
     * -divisor- = integer
     *
     * @param divisor  dividend / DIVISOR = integer
     * @param dividend DIVIDEND / divisor = integer
     * @return Next inferior integer which is a multiple of the dividend
     */
    private int getInferiorDivisorToGetModulusZero(int divisor, int dividend) {
        boolean exit = true;
        int div;
        int new_divisor;
        do {
            div = (int) Math.ceil(dividend / ((double) divisor));
            new_divisor = (int) Math.floor(dividend / ((double) div));
            if (dividend % new_divisor == 0) {
                exit = false;
            } else {
                divisor = new_divisor;
            }
        } while (exit);
        return new_divisor;
    }

    /**
     * Calculate the time during 2 including events correspnding to the index
     *
     * @param indexFirstEvent The index of the first time event
     * @param indexLastEvent  The index of the last time event
     * @return The total time between the 2 events (including)
     */
    public double getTimeBetweenEvents(int indexFirstEvent, int indexLastEvent) {
        double time = 0;
        for (int i = indexFirstEvent; i < indexLastEvent + 1; i++) {
            time += ((TimeElement) getSequence().getTimeChannel().get(i)).getTime().getFirst().doubleValue();
        }
        return time;
    }

    /**
     * Substract the time value of the event corresponding to the "index"
     * parameter from the parameter "time"
     *
     * @param time       time duration to be modified
     * @param indexEvent The index of the time event to be substract to the
     *                   value of the parameter time
     * @return The calculated time
     */
    public double removeTimeForEvents(double time, int... indexEvent) {
        for (int i = 0; i < indexEvent.length; i++) {
            time -= ((TimeElement) getSequence().getTimeChannel().get(indexEvent[i])).getTime().getFirst().doubleValue();
        }
        return time;
    }

    public List<RoleEnum> getPluginAccess() {
        return Collections.singletonList(RoleEnum.User);
    }

    //<editor-fold defaultstate="collapsed" desc="Generated Code (RS2D)">
    public void initParam() {
        for (SpinEchoParams param : SpinEchoParams.values()) {
            addParam(param.build());
        }
    }

    public Param getParam(SpinEchoParams param) {
        return getParamFromName(param.name());
    }

    public void setParamValue(SpinEchoParams param, Object value) {
        setParamValue(param.name(), value);
    }

    public String getName() {
        return "SPIN_ECHO";
    }

    public float getVersion() {
        return 0.0f;
    }
    //</editor-fold>
}