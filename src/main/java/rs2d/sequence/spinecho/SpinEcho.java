package rs2d.sequence.spinecho;

//---------------------------------------------------------------------
//
//                 SPIN ECHO PSD 
//
// ---------------------------------------------------------------------


import rs2d.commons.log.Log;
import rs2d.spinlab.data.transformPlugin.TransformPlugin;
import rs2d.spinlab.instrument.Instrument;
import rs2d.spinlab.instrument.InstrumentTxChannel;
import rs2d.spinlab.instrument.util.GradientMath;
import rs2d.spinlab.sequence.SequenceTool;
import rs2d.spinlab.sequence.element.TimeElement;
import rs2d.spinlab.sequenceGenerator.BaseSequenceGenerator;
import rs2d.spinlab.sequenceGenerator.util.GradientRotation;
import rs2d.spinlab.sequenceGenerator.util.Hardware;
import rs2d.spinlab.sequenceGenerator.util.TimeEvents;
import rs2d.spinlab.sequence.table.*;
import rs2d.spinlab.tools.param.*;
import rs2d.spinlab.tools.role.RoleEnum;
import rs2d.spinlab.tools.table.Order;
import rs2d.spinlab.tools.utility.GradientAxe;
import rs2d.spinlab.tools.utility.Nucleus;

import java.util.*;

import static java.util.Arrays.asList;

import rs2d.sequence.common.*;


//import javax.vecmath.Matrix3d;

import static rs2d.sequence.spinecho.S.*;
import static rs2d.sequence.spinecho.U.*;

// **************************************************************************************************
// *************************************** SEQUENCE GENERATOR ***************************************
// **************************************************************************************************
//
public class SpinEcho extends BaseSequenceGenerator {

    private String sequenceVersion = "Version9.12";
    private boolean CameleonVersion105 = false;

    private double protonFrequency;
    private double observeFrequency;
    private double min_time_per_acq_point;
    private double gMax;
    private TransformPlugin plugin;
    private String transformplugin;
    private Nucleus nucleus;

    private boolean isMultiplanar;

    private int acquisitionMatrixDimension1D;
    private int acquisitionMatrixDimension2D;
    private int acquisitionMatrixDimension3D;
    private int acquisitionMatrixDimension4D;
    private int preScan;

    private int number_of_averages;
    private int userMatrixDimension1D;
    private int userMatrixDimension2D;
    private int userMatrixDimension3D;

    private int nb_scan_1d;
    private int nb_scan_2d;
    private int nb_scan_3d;
    private int nb_scan_4d;
    private int echoTrainLength;
    private int nbOfInterleavedSlice;
    private int nb_planar_excitation;
    private int nb_of_shoot_3d;

    private double spectralWidth;
    private boolean isSW;
    private double tr;
    private double te;
    private double echo_spacing;
    private int echoEffective;
    private double[] TE_TR_lim;

    private double sliceThickness;
    private double spacingBetweenSlice;
    private boolean isSlicePacked;

    private double pixelDimension;
    private double fov;
    private double fovPhase;
    private double fov3d;
    private boolean isFovDoubled;
    private double off_center_distance_1D;
    private double off_center_distance_2D;
    private double off_center_distance_3D;

    private double txLength90;
    private double txLength180;

    private boolean isDynamic;
    private int numberOfDynamicAcquisition;
    private boolean isDynamicMinTime;

    private boolean is_FSE_vs_MultiEcho;
    SE sequenceSE;

    private boolean isDixon;
    private double dixonPeriode;

    private boolean isInversionRecovery;
    private List<Double> inversionRecoveryTime;
    private int numberOfInversionRecovery;

    private boolean isTrigger;
    private List<Double> triggerTime;
    private int numberOfTrigger;

    private boolean is_fatsat_enabled;
    private boolean is_fatsat_binomial;
    private double fatFreq;

    private int nb_satband;
    private boolean is_satband_enabled;
    private int[] position_sli_ph_rea;

    private boolean isKSCenterMode;
    private boolean isFSETrainTest;

    private boolean isEnablePhase;
    private boolean isEnablePhase3D;
    private boolean isEnableSlice;
    private boolean isEnableRead;

    private double observation_time;

    private enum SE {FSE, MultiEcho, OneShotFSE}


    private double minInstructionDelay = 0.000005;     // single instruction minimal duration

    public SpinEcho() {
        addUserParams();

    }

    @Override
    public void init() {
        super.init();
        ;

        // Define default, min, max and suggested values regarding the instrument
        getParam(MAGNETIC_FIELD_STRENGTH).setDefaultValue(Instrument.instance().getDevices().getMagnet().getField());
        getParam(DIGITAL_FILTER_SHIFT).setDefaultValue(Instrument.instance().getDevices().getCameleon().getAcquDeadPointCount());
        getParam(DIGITAL_FILTER_REMOVED).setDefaultValue(Instrument.instance().getDevices().getCameleon().isRemoveAcquDeadPoint());

        List<String> tx_shape = asList(
                "HARD",
                "GAUSSIAN",
                "SINC3",
                "SINC5",
                "SLR_8_5152",
                "SLR_4_2576");
        ((TextParam) getParam(TX_SHAPE_90)).setSuggestedValues(tx_shape);
        ((TextParam) getParam(TX_SHAPE_90)).setRestrictedToSuggested(true);
        ((TextParam) getParam(TX_SHAPE_180)).setSuggestedValues(tx_shape);
        ((TextParam) getParam(TX_SHAPE_180)).setRestrictedToSuggested(true);
        ((TextParam) getParam(SATBAND_TX_SHAPE)).setSuggestedValues(tx_shape);
        ((TextParam) getParam(SATBAND_TX_SHAPE)).setRestrictedToSuggested(true);
        ((TextParam) getParam(FATSAT_TX_SHAPE)).setSuggestedValues(tx_shape);
        ((TextParam) getParam(FATSAT_TX_SHAPE)).setRestrictedToSuggested(true);

        //TRANSFORM PLUGIN
        TextParam transformPlugin = getParam(TRANSFORM_PLUGIN);
        transformPlugin.setSuggestedValues(asList("Centered2DRot",
                "Bordered2D",
                "Sequential4D",
                "Sequential2D",
                "FSE_TRAIN_1D",
                "Sequential2DInterleaved"));
        transformPlugin.setRestrictedToSuggested(true);

        //List<String> tx_shape = Arrays.asList("HARD", "GAUSSIAN", "SIN3", "xSINC5");
        TextParam triggerChanel = getParam(TRIGGER_CHANEL);
        triggerChanel.setSuggestedValues(asList(
                SequenceTool.ExtTrigSource.Ext1.name(),
                SequenceTool.ExtTrigSource.Ext2.name(),
                SequenceTool.ExtTrigSource.Ext1_AND_Ext2.name(),
                SequenceTool.ExtTrigSource.Ext1_XOR_Ext2.name()));
        triggerChanel.setRestrictedToSuggested(true);

        List<String> satbandOrientationAllowed = asList("CRANIAL", "CAUDAL", "CRANIAL AND CAUDAL",
                "ANTERIOR", "POSTERIOR", "ANTERIOR AND POSTERIOR",
                "RIGHT", "LEFT", "RIGHT AND LEFT",
                "ALL");
        //List<String> tx_shape = Arrays.asList("HARD", "GAUSSIAN", "SIN3", "SINC5");
        ((TextParam) getParam(SATBAND_ORIENTATION)).setSuggestedValues(satbandOrientationAllowed);
        ((TextParam) getParam(SATBAND_ORIENTATION)).setRestrictedToSuggested(true);

        protonFrequency = Math.ceil(Instrument.instance().getDevices().getMagnet().getProtonFrequency() / Math.pow(10, 6));
        fatFreq = protonFrequency * 3.5;
        ((NumberParam) getParam(FATSAT_BANDWIDTH)).setDefaultValue(fatFreq);
        ((NumberParam) getParam(FATSAT_OFFSET_FREQ)).setDefaultValue(-fatFreq);
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
        //   if (!getBoolean( SETUP_MODE)) {
        this.afterRouting();    //avoid exception during setup
        // }

        this.checkAndFireException();
    }

    private void initUserParam() {
        isMultiplanar = getBoolean(MULTI_PLANAR_EXCITATION);

        transformplugin = getText(TRANSFORM_PLUGIN);

//        acquisitionMatrixDimension1D = getInt(ACQUISITION_MATRIX_DIMENSION_1D);
        acquisitionMatrixDimension2D = getInt(ACQUISITION_MATRIX_DIMENSION_2D);
        acquisitionMatrixDimension3D = getInt(ACQUISITION_MATRIX_DIMENSION_3D);
        acquisitionMatrixDimension4D = getInt(ACQUISITION_MATRIX_DIMENSION_4D);
        preScan = getInt(DUMMY_SCAN);

        number_of_averages = getInt(NUMBER_OF_AVERAGES);
        userMatrixDimension1D = getInt(USER_MATRIX_DIMENSION_1D);
        userMatrixDimension2D = getInt(USER_MATRIX_DIMENSION_2D);
        userMatrixDimension3D = getInt(USER_MATRIX_DIMENSION_3D);

        is_FSE_vs_MultiEcho = getText(SE_TYPE).equalsIgnoreCase("FSE");

        switch (getText(SE_TYPE)) {
            case "MultiEcho":
                sequenceSE = SE.MultiEcho;
                break;
            case "OneShotFSE":
                sequenceSE = SE.OneShotFSE;
                break;
            default:
            case "FSE":
                sequenceSE = SE.FSE;
                break;
        }

        echoTrainLength = getInt(ECHO_TRAIN_LENGTH);

        spectralWidth = getDouble(SPECTRAL_WIDTH);            // get user defined spectral width
        isSW = getBoolean(SPECTRAL_WIDTH_OPT);
        tr = getDouble(REPETITION_TIME);
        te = getDouble(ECHO_TIME);
        echo_spacing = getDouble(ECHO_SPACING);
        echoEffective = getInt(ECHO_EFFECTIVE);

        sliceThickness = getDouble(SLICE_THICKNESS);
        spacingBetweenSlice = getDouble(SPACING_BETWEEN_SLICE);
        nb_of_shoot_3d = getInt(NUMBER_OF_SHOOT_3D);
        isSlicePacked = getBoolean(MULTISLICE_PACKED);

        pixelDimension = getDouble(RESOLUTION_FREQUENCY);
        fov = getDouble(FIELD_OF_VIEW);
        fovPhase = getDouble(FIELD_OF_VIEW_PHASE);
        isFovDoubled = getBoolean(FOV_DOUBLED);
        off_center_distance_1D = getDouble(OFF_CENTER_FIELD_OF_VIEW_1D);
        off_center_distance_2D = getDouble(OFF_CENTER_FIELD_OF_VIEW_2D);
        off_center_distance_3D = getDouble(OFF_CENTER_FIELD_OF_VIEW_3D);

        txLength90 = getDouble(TX_LENGTH_90);
        txLength180 = getDouble(TX_LENGTH_180);

        isDynamic = getBoolean(DYNAMIC_SEQUENCE);
        numberOfDynamicAcquisition = isDynamic ? getInt(DYN_NUMBER_OF_ACQUISITION) : 1;
        isDynamic = isDynamic && (numberOfDynamicAcquisition > 1);
        isDynamicMinTime = getBoolean(DYNAMIC_MIN_TIME);


        isDixon = getBoolean(DIXON_3PTS);
        dixonPeriode = getDouble(DIXON_FAT_PERIODE);

        isInversionRecovery = getBoolean(INVERSION_RECOVERY);
        inversionRecoveryTime = getListDouble(INVERSION_TIME_MULTI);
        numberOfInversionRecovery = isInversionRecovery ? inversionRecoveryTime.size() : 1;
        isInversionRecovery = isInversionRecovery && (numberOfInversionRecovery >= 1);

        isTrigger = getBoolean(TRIGGER_EXTERNAL);
        triggerTime = getListDouble(TRIGGER_TIME);
        numberOfTrigger = isTrigger ? triggerTime.size() : 1;
        isTrigger = isTrigger && (numberOfTrigger > 0);

        is_fatsat_enabled = !getText(FATSAT).equalsIgnoreCase("disable");
        is_fatsat_binomial = getText(FATSAT).equalsIgnoreCase("binomial");

        System.out.println("  ");
        is_satband_enabled = getBoolean(SATBAND_ENABLED);
        position_sli_ph_rea = satBandPrep(SATBAND_ORIENTATION, ORIENTATION, IMAGE_ORIENTATION_SUBJECT);
        nb_satband = is_satband_enabled ? (int) Arrays.stream(position_sli_ph_rea).filter(item -> item == 1).count() : 1;

        isKSCenterMode = getBoolean(KS_CENTER_MODE);

        isFSETrainTest = getBoolean(TOOL_FSE_TRAIN_1D);

        isEnablePhase3D = !isKSCenterMode && !isFSETrainTest && getBoolean(GRADIENT_ENABLE_PHASE_3D);
        isEnablePhase = !isKSCenterMode && !isFSETrainTest && getBoolean(GRADIENT_ENABLE_PHASE);
        isEnableSlice = getBoolean(GRADIENT_ENABLE_SLICE);
        isEnableRead = getBoolean(GRADIENT_ENABLE_READ);

        observation_time = getDouble(ACQUISITION_TIME_PER_SCAN);
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
        //  System.out.println(" BEFORE ROUTING ");

        getParam(SEQUENCE_VERSION).setValue(sequenceVersion);
        getParam(MODALITY).setValue("MRI");

        // -----------------------------------------------
        // RX parameters : nucleus, RX gain & frequencies
        // -----------------------------------------------
        nucleus = Nucleus.getNucleusForName(getText(NUCLEUS_1));
        protonFrequency = Instrument.instance().getDevices().getMagnet().getProtonFrequency();
        fatFreq = -protonFrequency * 3.5;

        double freq_offset1 = getDouble(OFFSET_FREQ_1);
        observeFrequency = nucleus.getFrequency(protonFrequency) + freq_offset1;
        getParam(BASE_FREQ_1).setValue(nucleus.getFrequency(protonFrequency));

        min_time_per_acq_point = Hardware.getSequenceCompiler().getTransfertTimePerDataPt();
        gMax = GradientMath.getMaxGradientStrength();

        set(Rx_gain, RECEIVER_GAIN);
        getParam(RECEIVER_COUNT).setValue(Instrument.instance().getObservableRxs(nucleus).size());

        set(Intermediate_frequency, Instrument.instance().getIfFrequency());
        getParam(INTERMEDIATE_FREQUENCY).setValue(Instrument.instance().getIfFrequency());

        set(Tx_frequency, observeFrequency);
        getParam(OBSERVED_FREQUENCY).setValue(observeFrequency);

        set(Tx_nucleus, nucleus);
        getParam(OBSERVED_NUCLEUS).setValue(nucleus);

        // -----------------------------------------------
        //      CONTRAST
        // -----------------------------------------------

        isFSETrainTest = !isKSCenterMode && isFSETrainTest;
        isEnablePhase3D = !isKSCenterMode && !isFSETrainTest && isEnablePhase3D;
        isEnablePhase = !isKSCenterMode && !isFSETrainTest && isEnablePhase;

        echoEffective = Math.min(echoTrainLength, echoEffective);

        //  adapt the TRANSFORM_PLUGIN and the TE/TR depending on the IMAGE_CONTRAST

        if (isKSCenterMode) {
            transformplugin = "Sequential2D";
        } else if (isFSETrainTest) {
            transformplugin = "FSE_TRAIN_1D";
        } else if (sequenceSE == SE.FSE) {
            switch (getText(IMAGE_CONTRAST)) {
                case "T1-weighted":
                case "PD-weighted":
                    transformplugin = "Centered2DRot"; // first echo should be the significant echo
                    echoEffective = 1;
                    break;
                case "T2-weighted":
                    transformplugin = "Centered2DRot"; // first echo should be the significant echo
                    echoEffective = Math.max((int) Math.ceil(echoTrainLength / 2), echoEffective);
                    break;
                default: // Custom
                    switch (transformplugin) {
                        case "Centered2D": // not used anymore, replace by Centered2DRot
                        case "Sequential4D":
                            transformplugin = "Centered2DRot";
                            echoEffective = 1;
                            break;
                        case "Bordered2D": // not used anymore, replace by Centered2DRot
                            transformplugin = "Centered2DRot";
                            echoEffective = echoTrainLength;
                            break;
                        case "Sequential2D":
                            if (echoTrainLength != 1)// not isKSCenterMode anymore, restore FSE
                                transformplugin = "Centered2DRot";
                            break;
                        case "FSE_TRAIN_1D":// not isFSETrainTest anymore, restore FSE
                            transformplugin = "Centered2DRot";
                            break;
                        default:
                            break;
                    }
                    break;
            }
            te = echoEffective * echo_spacing;
        } else if (sequenceSE == SE.OneShotFSE) { //central line in the center but Centred2DRot to do different T2 ponderation
            transformplugin = "Sequential2D";
            echoEffective = (int) Math.ceil(echoTrainLength / 2.0);
            getParam(IMAGE_CONTRAST).setValue("Custom");
        } else { // is_FSE_vs_MultiEcho = false  :  multi echo for T2 measurement
            transformplugin = "Sequential4D";
            getParam(IMAGE_CONTRAST).setValue("T2-weighted");
            echo_spacing = te;
        }

        getParam(ECHO_SPACING).setValue(echo_spacing);
        getParam(ECHO_EFFECTIVE).setValue(echoEffective);
        getParam(TRANSFORM_PLUGIN).setValue(transformplugin);

        // TE/TR can be constrained to help the user to obtain the target contrast
        // By default IMAGE_CONTRAST = custom : no constrain
        TE_TR_lim = new double[]{0, 1000000, 0, 1000000}; // limit {TEmin, TEmax, TRmin TRmax}
        switch (getText(IMAGE_CONTRAST)) {
            case "T1-weighted": // Short TE, Short TR
                ListNumberParam T1_contrast_TE_TR_lim = getParam(LIM_T1_WEIGHTED);
                TE_TR_lim[2] = T1_contrast_TE_TR_lim.getValue().get(0).doubleValue(); //TRmin
                TE_TR_lim[3] = T1_contrast_TE_TR_lim.getValue().get(1).doubleValue(); //TRmax
                break;
            case "PD-weighted": // Short TE, Long TR
                ListNumberParam PD_contrast_TE_TR_lim = getParam(LIM_PD_WEIGHTED);
                TE_TR_lim[2] = PD_contrast_TE_TR_lim.getValue().get(0).doubleValue(); //TRmin
                break;
            case "T2-weighted": // Long TE, Long TR
                ListNumberParam T2_contrast_TE_TR_lim = getParam(LIM_T2_WEIGHTED);
                TE_TR_lim[0] = T2_contrast_TE_TR_lim.getValue().get(0).doubleValue(); //TEmin
                TE_TR_lim[2] = T2_contrast_TE_TR_lim.getValue().get(1).doubleValue(); //TRmin
                break;
            default: // Custom
                break;
        }

        // -----------------------------------------------
        // 1stD managment
        // -----------------------------------------------
        // FOV
        double fov_eff = isFovDoubled ? (fov * 2) : fov;
        getParam(FOV_EFF).setValue(fov_eff);

        // Pixel dimension calculation
        acquisitionMatrixDimension1D = userMatrixDimension1D * (isFovDoubled ? 2 : 1);
        pixelDimension = fov_eff / acquisitionMatrixDimension1D;
        getParam(RESOLUTION_FREQUENCY).setValue(pixelDimension); // frequency true resolution for display

        // MATRIX
        double spectralWidthPerPixel = getDouble(SPECTRAL_WIDTH_PER_PIXEL);
        spectralWidth = isFovDoubled ? (spectralWidth * 2) : spectralWidth;
        spectralWidth = isSW ? spectralWidth : spectralWidthPerPixel * acquisitionMatrixDimension1D;

        spectralWidth = Hardware.getSequenceCompiler().getNearestSW(spectralWidth);      // get real spectral width from Chameleon
        double spectralWidthUP = isFovDoubled ? (spectralWidth / 2) : spectralWidth;
        spectralWidthPerPixel = spectralWidth / acquisitionMatrixDimension1D;
        getParam(SPECTRAL_WIDTH_PER_PIXEL).setValue(spectralWidthPerPixel);
        getParam(SPECTRAL_WIDTH).setValue(spectralWidthUP);
        observation_time = acquisitionMatrixDimension1D / spectralWidth;
        System.out.println("spectralWidth " + spectralWidth);
        getParam(ACQUISITION_TIME_PER_SCAN).setValue(observation_time);   // display observation time

        nb_scan_1d = number_of_averages;

        // -----------------------------------------------
        // 2nd D managment
        // -----------------------------------------------
        // FOV
        prepareFovPhase();

        // MATRIX
        setSquarePixel(getBoolean(SQUARE_PIXEL));

        double partial_phase = getDouble(USER_PARTIAL_PHASE);
        double zero_filling_2D = (100 - partial_phase) / 100f;
        if (!(sequenceSE == SE.MultiEcho)) {
            int acq2D = (int) Math.floor((1 - zero_filling_2D) * userMatrixDimension2D);
            int fact2FSE = (sequenceSE == SE.OneShotFSE ? 1 : 2);
            echoTrainLength = Math.min(echoTrainLength, acq2D / fact2FSE);
            int shoot = (int) Math.round(acq2D / (fact2FSE * echoTrainLength));
            zero_filling_2D = 1.0 - shoot * fact2FSE * echoTrainLength / ((double) userMatrixDimension2D);
            if (zero_filling_2D < 0)
                zero_filling_2D = 1.0 - (shoot - 1) * (fact2FSE * echoTrainLength) / ((double) userMatrixDimension2D);
            partial_phase = (100 - zero_filling_2D * 100f);
        }
        getParam(USER_ZERO_FILLING_2D).setValue((100 - partial_phase));

        acquisitionMatrixDimension2D = floorEven((1 - zero_filling_2D) * userMatrixDimension2D);
        acquisitionMatrixDimension2D = (acquisitionMatrixDimension2D < 4) && isEnablePhase ? 4 : acquisitionMatrixDimension2D;

        // Pixel dimension calculation
        double pixelDimensionPhase = fovPhase / acquisitionMatrixDimension2D;
        getParam(RESOLUTION_PHASE).setValue(pixelDimensionPhase); // phase true resolution for display

        // -----------------------------------------------
        // 2nd D managment  ETL
        // -----------------------------------------------
        nb_scan_2d = acquisitionMatrixDimension2D;
        if (!(sequenceSE == SE.MultiEcho)) {
            echoTrainLength = (sequenceSE == SE.OneShotFSE ? acquisitionMatrixDimension2D : getInferiorDivisorToGetModulusZero(echoTrainLength, acquisitionMatrixDimension2D / 2));
            getParam(ECHO_TRAIN_LENGTH).setValue(echoTrainLength);
            nb_scan_2d = Math.floorDiv(acquisitionMatrixDimension2D, echoTrainLength);
            if (!(transformplugin.equalsIgnoreCase("Sequential2D"))) {
                nb_scan_2d = floorEven(nb_scan_2d); // Centred or bordered skim need to be multiple of 2
            }
            acquisitionMatrixDimension2D = nb_scan_2d * echoTrainLength;
        }
        userMatrixDimension2D = Math.max(userMatrixDimension2D, acquisitionMatrixDimension2D);
        getParam(USER_MATRIX_DIMENSION_2D).setValue(userMatrixDimension2D);

        // -----------------------------------------------
        // 3D managment 1/2: matrix & scan
        // ------------------------------------------------
        // MATRIX
        // 3D ZERO FILLING
        double partial_slice;
        if (isMultiplanar) {
            getParam(USER_PARTIAL_SLICE).setValue(100);
            partial_slice = 100;
        } else {
            partial_slice = getDouble(USER_PARTIAL_SLICE);
        }
        double zero_filling_3D = (100 - partial_slice) / 100f;
        getParam(USER_ZERO_FILLING_3D).setValue((100 - partial_slice));

        //Calculate the number of k-space lines acquired in the 3rd Dimension : acquisitionMatrixDimension3D
        if (!isMultiplanar) {
            acquisitionMatrixDimension3D = floorEven((1 - zero_filling_3D) * userMatrixDimension3D);
            acquisitionMatrixDimension3D = (acquisitionMatrixDimension3D < 4) && isEnablePhase3D ? 4 : acquisitionMatrixDimension3D;
            userMatrixDimension3D = Math.max(userMatrixDimension3D, acquisitionMatrixDimension3D);
            getParam(USER_MATRIX_DIMENSION_3D).setValue(userMatrixDimension3D);
            nb_of_shoot_3d = 0;
            nbOfInterleavedSlice = 1;
            nb_planar_excitation = 1;
        } else {
            acquisitionMatrixDimension3D = userMatrixDimension3D;
            nb_planar_excitation = userMatrixDimension3D;
            nb_of_shoot_3d = getInt(NUMBER_OF_SHOOT_3D);
            nb_of_shoot_3d = getInferiorDivisorToGetModulusZero(nb_of_shoot_3d, userMatrixDimension3D);
            nbOfInterleavedSlice = (int) Math.ceil((float) acquisitionMatrixDimension3D / nb_of_shoot_3d);
        }
        getParam(NUMBER_OF_SHOOT_3D).setValue(nb_of_shoot_3d);
        getParam(NUMBER_OF_INTERLEAVED_SLICE).setValue(isMultiplanar ? nbOfInterleavedSlice : 0);

        nb_scan_3d = isMultiplanar ? nb_of_shoot_3d : acquisitionMatrixDimension3D;

        // -----------------------------------------------
        // 3D managment 2/2: FOV
        // -----------------------------------------------

        if (isMultiplanar) {
            spacingBetweenSlice = getDouble(SPACING_BETWEEN_SLICE);
        } else {
            getParam(SPACING_BETWEEN_SLICE).setValue(0);
            spacingBetweenSlice = 0;
        }

        fov3d = sliceThickness * userMatrixDimension3D + spacingBetweenSlice * (userMatrixDimension3D - 1);
        getParam(FIELD_OF_VIEW_3D).setValue(fov3d);    // FOV ratio for display

        // Pixel dimension calculation
        double pixel_dimension_3D;
        if (isMultiplanar) {
            pixel_dimension_3D = sliceThickness;
        } else {
            pixel_dimension_3D = sliceThickness * userMatrixDimension3D / acquisitionMatrixDimension3D; //true resolution
        }
        getParam(RESOLUTION_SLICE).setValue(pixel_dimension_3D); // phase true resolution for display

        if (isMultiplanar) {
            double tmp = getDouble(GRADIENT_PHASE_APPLICATION_TIME);
            getParam(GRADIENT_CRUSHER_TOP_TIME).setValue(tmp);
        }

        // -----------------------------------------------
        // 4D managment:  Dynamic, MultiEcho, External triggering, Multi Echo
        // -----------------------------------------------
        int numberOfEcho = (sequenceSE == SE.MultiEcho) ? echoTrainLength : 1;

        // Avoid multi IR time when Multi echo
        if (numberOfInversionRecovery != 1 && (numberOfEcho != 1)) {
            double tmp = inversionRecoveryTime.get(0);
            inversionRecoveryTime.clear();
            inversionRecoveryTime.add(tmp);
            numberOfInversionRecovery = 1;
        }

        // Avoid multi trigger time when multi Inversion time or Multi echo or dynamic or Dixon
        if (numberOfTrigger != 1 && (numberOfInversionRecovery != 1 || isDynamic || isDixon || (numberOfEcho != 1))) {
            double tmp = triggerTime.get(0);
            triggerTime.clear();
            triggerTime.add(tmp);
            numberOfTrigger = 1;
        }
        // Avoid Dixon  when multi Inversion time or Multi echo
        if (isDixon && (numberOfInversionRecovery != 1 || (numberOfEcho != 1))) {
            isDixon = false;
        }
        dixonPeriode = 1 / (3.5 * observeFrequency * 1e-6);
        getParam(DIXON_FAT_PERIODE).setValue(dixonPeriode);

        nb_scan_4d = numberOfTrigger * numberOfInversionRecovery * numberOfDynamicAcquisition * (isDixon ? 3 : 1);
        acquisitionMatrixDimension4D = nb_scan_4d * numberOfEcho;
        getParam(USER_MATRIX_DIMENSION_4D).setValue(acquisitionMatrixDimension4D);

        // -----------------------------------------------
        // set the ACQUISITION_MATRIX and Nb XD
        // -----------------------------------------------        // set the calculated acquisition matrix

        getParam(ACQUISITION_MATRIX_DIMENSION_1D).setValue(acquisitionMatrixDimension1D);
        getParam(ACQUISITION_MATRIX_DIMENSION_2D).setValue(acquisitionMatrixDimension2D);
        getParam(ACQUISITION_MATRIX_DIMENSION_3D).setValue(acquisitionMatrixDimension3D);
        getParam(ACQUISITION_MATRIX_DIMENSION_4D).setValue(acquisitionMatrixDimension4D);

        if (isKSCenterMode) { // Do only the center of the k-space for auto RG
            nb_scan_1d = 1;
            nb_scan_2d = 2;
            nb_scan_3d = !isMultiplanar ? 1 : nb_scan_3d;
            getParam(ACQUISITION_MATRIX_DIMENSION_3D).setValue(!isMultiplanar ? 1 : acquisitionMatrixDimension3D);
            nb_scan_4d = 1;
        }
        if (isFSETrainTest) { // Do only the center of the k-space for auto RG
            getParam(ACQUISITION_MATRIX_DIMENSION_1D).setValue(acquisitionMatrixDimension1D * echoTrainLength);
            getParam(ACQUISITION_MATRIX_DIMENSION_2D).setValue(nb_scan_2d);
            nb_scan_1d = 1;
            nb_scan_3d = !isMultiplanar ? 1 : nb_scan_3d;
            getParam(ACQUISITION_MATRIX_DIMENSION_3D).setValue(!isMultiplanar ? 1 : acquisitionMatrixDimension3D);
            nb_scan_4d = 1;

        }
        // set Nb_scan  Values
        set(Pre_scan, preScan); // Do the prescan
        set(Nb_point, acquisitionMatrixDimension1D);
        set(Nb_1d, nb_scan_1d);
        set(Nb_2d, nb_scan_2d);
        set(Nb_3d, nb_scan_3d);
        set(Nb_4d, nb_scan_4d);
        // set the calculated Loop dimensions
        set(Nb_echo, echoTrainLength - 1);
        set(Nb_interleaved_slice, nbOfInterleavedSlice - 1);
        set(Nb_sb_loop, nb_satband - 1);

        // -----------------------------------------------
        // SEQ_DESCRIPTION
        // -----------------------------------------------
        String seqDescription = "SE_";
        if (isMultiplanar) {
            seqDescription += "2D_";
        } else {
            seqDescription += "3D_";
        }
        String orientation = getText(ORIENTATION);
        seqDescription += orientation.substring(0, 3);

        String seqMatrixDescription = "_";
        seqMatrixDescription += userMatrixDimension1D + "x" + acquisitionMatrixDimension2D + "x" + acquisitionMatrixDimension3D;
        if (acquisitionMatrixDimension4D != 1) {
            seqMatrixDescription += "x" + acquisitionMatrixDimension4D;
        }
        seqDescription += seqMatrixDescription;

        if (echoTrainLength != 1) {
            seqDescription += "_ETL=" + echoTrainLength;
        }

        if (isInversionRecovery && numberOfInversionRecovery != 1) {
            seqDescription += "_IR-" + numberOfInversionRecovery;
        } else if (isInversionRecovery) {
            seqDescription += "_IR=" + inversionRecoveryTime.get(0) + "s";
        }
        if (isTrigger && numberOfTrigger != 1) {
            seqDescription += "_TRIG=" + numberOfTrigger;
        } else if (isTrigger) {
            seqDescription += "_TRIG";
        }
        if (isDynamic) {
            seqDescription += "_DYN=" + numberOfDynamicAcquisition;
        }
        if (isDixon) {
            seqDescription += "_3PTS_DIXON(-PI_0+PI)";
        }
        if (is_satband_enabled) {
            seqDescription += "_SATBAND";
        }
        if (is_fatsat_enabled) {
            seqDescription += "_FATSAT";
        }
        if (is_fatsat_binomial) {
            seqDescription += "_FATSATWEP";
        }
        getParam(SEQ_DESCRIPTION).setValue(seqDescription);

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

        if (!isEnableSlice && (isMultiplanar || !isEnablePhase3D)) {
            off_center_distance_3D = 0;
        }

        getParam(OFF_CENTER_FIELD_OF_VIEW_X).setValue(roundToDecimal(getOff_center_distance_X_Y_Z(1, off_center_distance_1D, off_center_distance_2D, off_center_distance_3D), 5));
        getParam(OFF_CENTER_FIELD_OF_VIEW_Y).setValue(roundToDecimal(getOff_center_distance_X_Y_Z(2, off_center_distance_1D, off_center_distance_2D, off_center_distance_3D), 5));
        getParam(OFF_CENTER_FIELD_OF_VIEW_Z).setValue(roundToDecimal(getOff_center_distance_X_Y_Z(3, off_center_distance_1D, off_center_distance_2D, off_center_distance_3D), 5));


        boolean is_read_phase_inverted = getBoolean(SWITCH_READ_PHASE);
        if (is_read_phase_inverted) {
            set(Gradient_axe_phase, GradientAxe.R);
            set(Gradient_axe_read, GradientAxe.P);
            double off_center_distance_tmp = off_center_distance_2D;
            off_center_distance_2D = off_center_distance_1D;
            off_center_distance_1D = off_center_distance_tmp;
        } else {
            set(Gradient_axe_phase, GradientAxe.P);
            set(Gradient_axe_read, GradientAxe.R);
        }
        getParam(OFF_CENTER_FIELD_OF_VIEW_3D).setValue(off_center_distance_3D);
        getParam(OFF_CENTER_FIELD_OF_VIEW_2D).setValue(off_center_distance_2D);
        getParam(OFF_CENTER_FIELD_OF_VIEW_1D).setValue(off_center_distance_1D);

        // -----------------------------------------------
        // activate gradient rotation matrix
        // -----------------------------------------------
        GradientRotation.setSequenceGradientRotation(this);

    }

    private int floorEven(double value) {
        return (int) Math.floor(Math.round(value) / 2.0) * 2;
    }

    private void setSquarePixel(boolean square) {
        if (square) {
            userMatrixDimension2D = (int) Math.round(userMatrixDimension1D * fovPhase / fov);
            getParam(USER_MATRIX_DIMENSION_2D).setValue(userMatrixDimension2D);
        }
    }

    private void prepareFovPhase() {
        fovPhase = (getBoolean(FOV_SQUARE)) ? fov : fovPhase;
        fovPhase = Math.min(fovPhase, fov);
        getParam(FIELD_OF_VIEW_PHASE).setValue(fovPhase);
        getParam(PHASE_FIELD_OF_VIEW_RATIO).setValue((fovPhase / fov * 100.0));    // FOV ratio for display
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
        plugin.setParameters(new ArrayList<>(getUserParams()));

        // -----------------------------------------------
        // enable gradient lines
        // -----------------------------------------------
        set(Grad_enable_read, isEnableRead);              // pass gradient line status to sequence
        set(Grad_enable_phase_2D, isEnablePhase);
        set(Grad_enable_phase_3D, (!isMultiplanar && isEnablePhase3D));
        set(Grad_enable_slice, isEnableSlice);
        set(Grad_enable_Slice_180, isEnableSlice);
        set(Grad_enable_slice_crush, GRADIENT_ENABLE_SLICE_CRUSH);
        set(Grad_enable_spoiler, GRADIENT_ENABLE_SPOILER);

        // -----------------------------------------------
        // enable Inversion recovery elements
        // -----------------------------------------------
        set(Grad_enable_IR, isInversionRecovery);
        set(Tx_enable_IR, isInversionRecovery);
        boolean isEnableCrusherIR = getBoolean(GRADIENT_ENABLE_CRUSHER_IR);
        set(Grad_enable_crush_IR, isInversionRecovery && isEnableCrusherIR);
        set(Enable_sb, is_satband_enabled);
        set(Enable_fs, is_fatsat_enabled );
        set(Enable_fs_wep, is_fatsat_binomial);

        // ------------------------------------------
        // delays for sequence instructions
        // ------------------------------------------
        set(Time_min_instruction, minInstructionDelay);

        InstrumentTxChannel txCh = Instrument.instance().getTxChannels().get(getListInt(TX_ROUTE).get(0));
        double blankingDelay = Math.max(minInstructionDelay, txCh.getRfAmpChannel().getBlankingDelay());

        // -----------------------------------------------
        // calculate gradient equivalent rise time
        // -----------------------------------------------
        double grad_rise_time = getDouble(GRADIENT_RISE_TIME);
        double min_rise_time_factor = getDouble(MIN_RISE_TIME_FACTOR);

        double min_rise_time_sinus = GradientMath.getShortestRiseTime(100.0) * Math.PI / 2 * 100 / min_rise_time_factor;
        if (grad_rise_time < min_rise_time_sinus) {
            double new_grad_rise_time = ceilToSubDecimal(min_rise_time_sinus, 5);
            notifyOutOfRangeParam(GRADIENT_RISE_TIME, new_grad_rise_time, ((NumberParam) getParam(GRADIENT_RISE_TIME)).getMaxValue(), "Gradient ramp time too short ");
            grad_rise_time = new_grad_rise_time;
        }
        if (grad_rise_time < blankingDelay) {
            double new_grad_rise_time = ceilToSubDecimal(blankingDelay, 5);
            notifyOutOfRangeParam(GRADIENT_RISE_TIME, new_grad_rise_time, ((NumberParam) getParam(GRADIENT_RISE_TIME)).getMaxValue(), "Gradient ramp time too short because of blanking delay ");
            grad_rise_time = new_grad_rise_time;
        }
        set(Time_grad_ramp, grad_rise_time);

        double grad_shape_rise_factor_up = Utility.voltageFillingFactor(getSequenceTable(Grad_shape_rise_up));
        double grad_shape_rise_factor_down = Utility.voltageFillingFactor(getSequenceTable(Grad_shape_rise_down));
        double grad_shape_rise_time = grad_shape_rise_factor_up * grad_rise_time + grad_shape_rise_factor_down * grad_rise_time;        // shape dependant equivalent rise time

        // -----------------------------------------------
        // Calculation RF pulse parameters  1/4 : Pulse declaration & Fatsat Flip angle calculation
        // -----------------------------------------------
        set(Time_tx_90, TX_LENGTH_90);     // set RF pulse length to sequence
        set(Time_tx_180, TX_LENGTH_180);   // set 180° RF pulse length to sequence
        RFPulse pulseTX90 = RFPulse.createRFPulse(getSequence(), Tx_att, Tx_amp_90, Tx_phase_90, Time_tx_90, Tx_shape_90, Tx_shape_phase_90, Tx_freq_offset_90);
        RFPulse pulseTX180 = RFPulse.createRFPulse(getSequence(), Tx_att, Tx_amp_180, Tx_phase_180, Time_tx_180, Tx_shape_180, Tx_shape_phase_180, Tx_freq_offset_180);

        // Fat SAT RF pulse
        double tx_bandwidth_90_fs = getDouble(FATSAT_BANDWIDTH);
        double tx_bandwidth_factor_90_fs = getTx_bandwidth_factor(FATSAT_TX_SHAPE, TX_BANDWIDTH_FACTOR, TX_BANDWIDTH_FACTOR_3D);
        double tx_length_90_fs = minInstructionDelay;
        double tx_length_90_fs_wep = minInstructionDelay;
        if (is_fatsat_enabled && !is_fatsat_binomial) {

            tx_length_90_fs = tx_bandwidth_factor_90_fs / tx_bandwidth_90_fs;
            getParam(FATSAT_TX_LENGTH).setValue(tx_length_90_fs);

        } else if (is_fatsat_binomial) {
            tx_length_90_fs = getDouble(FATSAT_TX_LENGTH);
            tx_length_90_fs_wep = tx_length_90_fs;
        }
        set(Time_tx_fatsat, tx_length_90_fs);
        set(Time_tx_fatsat_wep, tx_length_90_fs_wep);


        // FatSAT timing seting needed for Flip Angle calculation
        double grad_fatsat_application_time = getDouble(FATSAT_GRAD_APP_TIME);
        set(Time_grad_fatsat, is_fatsat_enabled ? grad_fatsat_application_time : minInstructionDelay);
        set(Time_before_fatsat_pulse, blankingDelay);
        set(Time_before_fatsat_wep_pulse, blankingDelay);
        set(Time_grad_ramp_fatsat, is_fatsat_enabled ? grad_rise_time : minInstructionDelay);
        //
        RFPulse pulseTXFatSat = RFPulse.createRFPulse(getSequence(), Tx_att, Tx_amp_fatsat, Tx_phase_fatsat, Time_tx_fatsat, Tx_shape_fatsat, Tx_shape_phase_fatsat, Freq_offset_tx_fatsat);

        // SAT Band  RF pulse
        set(Time_grad_ramp_sb, is_satband_enabled ? grad_rise_time : minInstructionDelay);
        set(Time_grad_sb, is_satband_enabled ? 0.0005 : minInstructionDelay);
        double tx_length_sb = is_satband_enabled ? txLength90 : minInstructionDelay;
        set(Time_tx_sb, tx_length_sb);
        //
        RFPulse pulseTXSatBand = RFPulse.createRFPulse(getSequence(), Tx_att, Tx_amp_sb, Tx_phase_sb, Time_tx_sb, Tx_shape_sb, Tx_shape_phase_sb, Freq_offset_tx_sb);

        //Correction of the 90 water saturation RF angle according to the water T1 relaxation.
        double flip_angle = getDouble(FLIP_ANGLE);
        flip_angle = pulseTXSatBand.isSlr() ? 90 : flip_angle;
        getParam(FLIP_ANGLE).setValue(flip_angle);
        double flip_angle_satband = 0;
        if (is_satband_enabled) {
            double time_tau_sat = TimeEvents.getTimeBetweenEvents(getSequence(), Events.FatSatPulse.ID, Events.TX90.ID); /////////////////////////////////////////////////////////////////////////////////////////////////////////
            double time_t1_satband = getDouble(SATBAND_T1);
            double t1_relax_time_sat = time_t1_satband / 1000.0;   // T1_tissue = 500ms
            //
            double flip_90_sat = flip_angle == 90 ? Math.acos((1 - Math.exp(time_tau_sat / t1_relax_time_sat)) / (1 - Math.exp((time_tau_sat - tr) / t1_relax_time_sat))) : Math.acos(1 - Math.exp(time_tau_sat / t1_relax_time_sat));
            double flip_90_sat_degree = Math.toDegrees(flip_90_sat);
            flip_angle_satband = pulseTXSatBand.isSlr() ? 90 : flip_90_sat_degree;  //ha slr,akkor legyen 90,különben szar a szeletprofil!
        }

        // -----------------------------------------------
        // Calculation RF pulse parameters  2/4 : Shape
        // -----------------------------------------------
        int nb_shape_points = 128;
        pulseTX90.setShape((getText(TX_SHAPE_90)), nb_shape_points, "90 degree");
        pulseTX180.setShape((getText(TX_SHAPE_180)), nb_shape_points, "Refocusing (spin-echo)");
        pulseTXFatSat.setShape((getText(FATSAT_TX_SHAPE)), nb_shape_points, "90 degree");
        pulseTXSatBand.setShape(getText(SATBAND_TX_SHAPE), nb_shape_points, "90 degree");

        // -----------------------------------------------
        // Calculation RF pulse parameters  3/4 : RF pulse & attenuation
        // -----------------------------------------------
        boolean is_tx_amp_att_auto = getBoolean(TX_AMP_ATT_AUTO);
        double tx_frequency_offset_90_fs = -Math.abs(getDouble(FATSAT_OFFSET_FREQ));
        if (is_tx_amp_att_auto) {
            // Calculate the power to reach the target flip angle
            // If the power exceed limit, the pulse duration is lengthened
            if (!pulseTX180.prepPower(180, observeFrequency, nucleus)) {
                notifyOutOfRangeParam(TX_LENGTH_180, pulseTX180.getPulseDuration(), ((NumberParam) getParam(TX_LENGTH_180)).getMaxValue(), "Pulse length too short to reach RF power with this pulse shape");
                txLength180 = pulseTX180.getPulseDuration();
            }
            if (!pulseTX90.prepPower(flip_angle, observeFrequency, nucleus)) {
                notifyOutOfRangeParam(TX_LENGTH_90, pulseTX90.getPulseDuration(), ((NumberParam) getParam(TX_LENGTH_90)).getMaxValue(), "Pulse length too short to reach RF power with this pulse shape");
                txLength90 = pulseTX90.getPulseDuration();
            }

            double FlipAngleFatSat = is_fatsat_enabled ? (is_fatsat_binomial ? 45.0 : 90.0) : 0.0 ;
            double FreqFatSat = observeFrequency + (is_fatsat_enabled && !is_fatsat_binomial ? tx_frequency_offset_90_fs : 0.0);
            if (!pulseTXFatSat.prepPower(FlipAngleFatSat, FreqFatSat, nucleus)) {
                tx_length_90_fs = pulseTXFatSat.getPulseDuration();
                set(Time_tx_fatsat, tx_length_90_fs);
                getParam(FATSAT_TX_LENGTH).setValue(tx_length_90_fs);
                if (is_fatsat_binomial) {
                    tx_length_90_fs_wep = tx_length_90_fs;
                    set(Time_tx_fatsat_wep, tx_length_90_fs_wep);
                }
            }
            if (!pulseTXSatBand.prepPower(flip_angle_satband, observeFrequency, nucleus)) {
//                double tx_length_sb = pulseTXSatBand.getPulseDuration();
//                notifyOutOfRangeParam(TX_LENGTH, pulseTXFatSat.getPulseDuration(), ((NumberParam) getParam(TX_LENGTH)).getMaxValue(), "Pulse length too short to reach RF power with this pulse shape");
                set(Time_tx_sb, pulseTXSatBand.getPulseDuration());
            }
            // choose the attenuation that will be applied on TX_ROUTE in order to have 80% amplitude for the pulse that requires the most power
            double referencePower = Math.max(Math.max(Math.max(pulseTX90.getPower(), pulseTX180.getPower()), pulseTXFatSat.getPower()), pulseTXSatBand.getPower());
            pulseTX90.prepAtt(referencePower, 80, getListInt(TX_ROUTE));

            // compute the pulse amplitude knowing the channel attenuation and the pulse powers
            pulseTX90.prepTxAmp(getListInt(TX_ROUTE));
            pulseTX180.prepTxAmp(getListInt(TX_ROUTE));
            pulseTXFatSat.prepTxAmp(getListInt(TX_ROUTE));
            pulseTXSatBand.prepTxAmp(getListInt(TX_ROUTE));

            this.getParam(TX_ATT).setValue(pulseTX90.getAtt());            // display PULSE_ATT
            this.getParam(TX_AMP_90).setValue(pulseTX90.getAmp());     // display 90° amplitude
            this.getParam(TX_AMP_180).setValue(pulseTX180.getAmp());   // display 180° amplitude
            this.getParam(FATSAT_TX_AMP).setValue(pulseTXFatSat.getAmp());   // display 180° amplitude
            this.getParam(SATBAND_TX_AMP).setValue(pulseTXSatBand.getAmp());   // display 180° amplitude

        } else {
            pulseTX90.setAtt(getParam(TX_ATT));
            pulseTX90.setAmp(getParam(TX_AMP_90));
            pulseTX180.setAmp(getParam(TX_AMP_180));
            pulseTXFatSat.setAmp(getParam(FATSAT_TX_AMP));
            pulseTXSatBand.setAmp(getParam(SATBAND_TX_AMP));
        }
        this.getParam(FATSAT_FLIP_ANGLE).setValue(is_fatsat_enabled ? (is_fatsat_binomial ? 45 : 90) : 0);

        // -----------------------------------------------
        // Calculation RF pulse parameters  4/4: bandwidth
        // -----------------------------------------------
        double tx_bandwidth_factor_90 = getTx_bandwidth_factor(TX_SHAPE_90, TX_BANDWIDTH_FACTOR, TX_BANDWIDTH_FACTOR_3D);
        double tx_bandwidth_90 = tx_bandwidth_factor_90 / txLength90;

        double tx_bandwidth_factor_180 = getTx_bandwidth_factor(TX_SHAPE_180, TX_BANDWIDTH_FACTOR, TX_BANDWIDTH_FACTOR_3D);
        double tx_bandwidth_180 = tx_bandwidth_factor_180 / txLength180;

        double tx_bandwidth_factor_sb = getTx_bandwidth_factor(SATBAND_TX_SHAPE, TX_BANDWIDTH_FACTOR, TX_BANDWIDTH_FACTOR_3D);
        double tx_bandwidth_sb = tx_bandwidth_factor_sb / tx_length_sb;

        // ---------------------------------------------------------------------
        // calculate SLICE gradient amplitudes for RF pulses
        // ---------------------------------------------------------------------
        double slice_thickness_excitation = (isMultiplanar ? sliceThickness : (sliceThickness * userMatrixDimension3D));

        //SLICE gradient amp for 180
        boolean is_wide_180_slice_thickness = getBoolean(SLICE_THICKNESS_180_WIDER);
        double slice_thickness_excitation_180;
        if (((spacingBetweenSlice > slice_thickness_excitation) || !isMultiplanar) && is_wide_180_slice_thickness) {
            slice_thickness_excitation_180 = slice_thickness_excitation * (1 + 0.5);
        } else if ((spacingBetweenSlice != 0) && is_wide_180_slice_thickness) {
            slice_thickness_excitation_180 = slice_thickness_excitation + spacingBetweenSlice / 2.0;
        } else {
            slice_thickness_excitation_180 = slice_thickness_excitation;
        }

        Gradient gradSlice90 = Gradient.createGradient(getSequence(), Grad_amp_slice_90, Time_tx_90, Grad_shape_rise_up, Grad_shape_rise_down, Time_grad_ramp, nucleus);
        Gradient gradSlice180 = Gradient.createGradient(getSequence(), Grad_amp_slice_180, Time_tx_180, Grad_shape_rise_up, Grad_shape_rise_down, Time_grad_ramp, nucleus);
        if (isEnableSlice) {
            boolean test90 = gradSlice90.prepareSliceSelection(tx_bandwidth_90, slice_thickness_excitation);
            boolean test180 = gradSlice180.prepareSliceSelection(tx_bandwidth_180, slice_thickness_excitation_180);
            if (!test90 || !test180) {
                slice_thickness_excitation = Math.max(gradSlice90.getSliceThickness(), gradSlice180.getSliceThickness());
                double slice_thickness_min = (isMultiplanar ? slice_thickness_excitation : (slice_thickness_excitation / userMatrixDimension3D));
                notifyOutOfRangeParam(SLICE_THICKNESS, slice_thickness_min, ((NumberParam) getParam(SLICE_THICKNESS)).getMaxValue(), "Pulse length too short to reach this slice thickness");
                sliceThickness = slice_thickness_min;
            }
        }
        gradSlice90.applyAmplitude(slice_thickness_excitation);
        gradSlice180.applyAmplitude(slice_thickness_excitation_180);

        // -----------------------------------------------
        // calculate ADC observation time
        // -----------------------------------------------
        set(Time_rx, observation_time);
        double grad_crusher_read_time = getDouble(GRADIENT_CRUSHER_READ_TOP_TIME);
        set(Time_grad_read_crusher, grad_crusher_read_time);

        set(LO_att, Instrument.instance().getLoAttenuation());

        // -----------------------------------------------
        // calculate READ gradient amplitude
        // -----------------------------------------------
        Gradient gradReadout = Gradient5Event.createGradient(getSequence(), Grad_amp_read_read, Time_grad_read_crusher, Time_rx, Time_grad_read_crusher, Grad_shape_rise_up, Grad_shape_rise_down, Time_grad_ramp, nucleus);
        if (isEnableRead && !gradReadout.calculateReadoutGradient(spectralWidth, pixelDimension * acquisitionMatrixDimension1D)) {
            double spectral_width_max = gradReadout.getSpectralWidth();
            if (isSW) {
                notifyOutOfRangeParam(SPECTRAL_WIDTH, ((NumberParam) getParam(SPECTRAL_WIDTH)).getMinValue(), (spectral_width_max / (isFovDoubled ? 2 : 1)), "SPECTRAL_WIDTH too high for the readout gradient");
            } else {
                notifyOutOfRangeParam(SPECTRAL_WIDTH_PER_PIXEL, ((NumberParam) getParam(SPECTRAL_WIDTH_PER_PIXEL)).getMinValue(), (spectral_width_max / acquisitionMatrixDimension1D), "SPECTRAL_WIDTH too high for the readout gradient");
            }
            spectralWidth = spectral_width_max;
        }
        gradReadout.applyAmplitude();
        set(Spectral_width, spectralWidth);

        // -------------------------------------------------------------------------------------------------
        // calculate READ_PREP  & SLICE_REF
        // -------------------------------------------------------------------------------------------------
        double grad_read_prep_application_time = getDouble(GRADIENT_READ_PREPHASING_APPLICATION_TIME);
        double grad_read_prep_offset = getDouble(GRADIENT_READ_OFFSET);
        set(Time_grad_read_prep_top, grad_read_prep_application_time);

        // pre-calculate READ_prephasing max area
        Gradient gradReadPrep = Gradient.createGradient(getSequence(), Grad_amp_read_prep, Time_grad_read_prep_top, Grad_shape_rise_up, Grad_shape_rise_down, Time_grad_ramp, nucleus);
        if (isEnableRead)
            gradReadPrep.refocalizeGradient(gradReadout, -getDouble(PREPHASING_READ_GRADIENT_RATIO));
        if (!gradReadPrep.addSpoiler(grad_read_prep_offset))
            getParam(GRADIENT_READ_OFFSET).setValue(grad_read_prep_offset - gradReadPrep.getSpoilerExcess());   // display observation time

        // pre-calculate SLICE_refocusing
        double grad_ratio_slice_refoc = 0.5;   // get slice refocusing ratio
        this.getParam(SLICE_REFOCUSING_GRADIENT_RATIO).setValue(grad_ratio_slice_refoc);   // display 180° amplitude
        Gradient gradSliceRef = Gradient.createGradient(getSequence(), Grad_amp_slice_refoc, Time_grad_read_prep_top, Grad_shape_rise_up, Grad_shape_rise_down, Time_grad_ramp, nucleus);
        if (isEnableSlice) {
            gradSliceRef.refocalizeGradient(gradSlice90, grad_ratio_slice_refoc);
        }

        // Check if enough time for 2D_PHASE, 3D_PHASE SLICE_REF or READ_PREP
        double grad_area_sequence_max = 100 * (grad_read_prep_application_time + grad_shape_rise_time);
        double grad_area_max = Math.max(gradReadPrep.getTotalArea(), gradSliceRef.getTotalArea());            // calculate the maximum gradient area between SLICE REFOC & READ PREPHASING
        if (grad_area_max > grad_area_sequence_max) {
            double grad_read_prep_application_time_min = ceilToSubDecimal(grad_area_max / 100.0 - grad_shape_rise_time, 5);
            notifyOutOfRangeParam(GRADIENT_READ_PREPHASING_APPLICATION_TIME, grad_read_prep_application_time_min, ((NumberParam) getParam(GRADIENT_READ_PREPHASING_APPLICATION_TIME)).getMaxValue(), "Gradient application time too short to reach this pixel dimension");
            grad_read_prep_application_time = grad_read_prep_application_time_min;
            set(Time_grad_read_prep_top, grad_read_prep_application_time);
            gradSliceRef.rePrepare();
            gradReadPrep.rePrepare();
        }
        gradSliceRef.applyAmplitude();
        gradReadPrep.applyAmplitude();

        // -------------------------------------------------------------------------------------------------
        // calculate PHASE_3D  & PHASE_2D
        // -------------------------------------------------------------------------------------------------
        double grad_phase_application_time = getDouble(GRADIENT_PHASE_APPLICATION_TIME);
        boolean is_k_s_centred = getBoolean(KS_CENTERED);  // symetrique around 0 or go through k0
        set(Time_grad_phase_top, grad_phase_application_time);

        // pre-calculate PHASE_3D
        Gradient gradSlicePhase3D = Gradient.createGradient(getSequence(), Grad_amp_phase_3D_prep, Time_grad_phase_top, Grad_shape_rise_up, Grad_shape_rise_down, Time_grad_ramp, nucleus);
        if (!isMultiplanar && isEnablePhase3D)
            gradSlicePhase3D.preparePhaseEncodingForCheck(acquisitionMatrixDimension3D, acquisitionMatrixDimension3D, slice_thickness_excitation, is_k_s_centred);

        // pre-calculate PHASE_2D
        Gradient gradPhase2D = Gradient.createGradient(getSequence(), Grad_amp_phase_2D_prep, Time_grad_phase_top, Grad_shape_rise_up, Grad_shape_rise_down, Time_grad_ramp, nucleus);
        gradPhase2D.preparePhaseEncodingForCheck(acquisitionMatrixDimension2D, acquisitionMatrixDimension2D, fovPhase, is_k_s_centred);
        if (sequenceSE == SE.FSE && echoTrainLength != 1 && !isKSCenterMode && !isFSETrainTest) {
            gradPhase2D.reoderPhaseEncoding(plugin, echoTrainLength, acquisitionMatrixDimension2D, acquisitionMatrixDimension1D);
        }

        // Check if enougth time for 2D_PHASE, 3D_PHASE SLICE_REF or READ_PREP
        grad_area_sequence_max = 100 * (grad_phase_application_time + grad_shape_rise_time);
        grad_area_max = Math.max(gradSlicePhase3D.getTotalArea(), gradPhase2D.getTotalArea());            // calculate the maximum gradient aera between SLICE REFOC & READ PREPHASING

        if (grad_area_max > grad_area_sequence_max) {
            double grad_phase_application_time_min = ceilToSubDecimal(grad_area_max / 100.0 - grad_shape_rise_time, 5);
            notifyOutOfRangeParam(GRADIENT_PHASE_APPLICATION_TIME, grad_phase_application_time_min, ((NumberParam) getParam(GRADIENT_PHASE_APPLICATION_TIME)).getMaxValue(), "Gradient application time too short to reach this pixel dimension");
            grad_phase_application_time = grad_phase_application_time_min;
            set(Time_grad_phase_top, grad_phase_application_time);
            gradPhase2D.rePrepare();
            gradSlicePhase3D.rePrepare();
        }
        gradSlicePhase3D.applyAmplitude(Order.Three);
        gradPhase2D.applyAmplitude((sequenceSE != SE.MultiEcho && echoTrainLength != 1) ? Order.TwoLoopB : Order.Two);

        // Reorder Phase Encoding
        Gradient gradSlicePhase3D_comp = Gradient.createGradient(getSequence(), Grad_amp_phase_3D_comp, Time_grad_phase_top, Grad_shape_rise_up, Grad_shape_rise_down, Time_grad_ramp, nucleus);
        Gradient gradPhase2D_comp = Gradient.createGradient(getSequence(), Grad_amp_phase_2D_comp, Time_grad_phase_top, Grad_shape_rise_up, Grad_shape_rise_down, Time_grad_ramp, nucleus);

        if (!isMultiplanar && isEnablePhase3D)
            gradSlicePhase3D_comp.refocalizePhaseEncodingGradient(gradSlicePhase3D);
        if (isEnablePhase)
            gradPhase2D_comp.refocalizePhaseEncodingGradient(gradPhase2D);
        gradSlicePhase3D_comp.applyAmplitude();
        gradPhase2D_comp.applyAmplitude();

        // -------------------------------------------------------------------------------------------------
        // Calculate Crusher Grad AMPLITUDE
        // -------------------------------------------------------------------------------------------------
        double grad_amp_crusher = getDouble(GRADIENT_AMP_CRUSHER);
        double time_grad_crusher_top = getDouble(GRADIENT_CRUSHER_TOP_TIME);
        set(Time_grad_crusher_top, time_grad_crusher_top);

        Gradient gradSliceCrusher = Gradient.createGradient(getSequence(), Grad_amp_slice_crush, Time_grad_crusher_top, Grad_shape_rise_up, Grad_shape_rise_down, Time_grad_ramp, nucleus);
        gradSliceCrusher.addSpoiler(grad_amp_crusher);
        gradSliceCrusher.applyAmplitude();
        double grad_area_crusher = (time_grad_crusher_top + grad_shape_rise_time) * grad_amp_crusher * gMax / 100.0;
        getParam(GRADIENT_AREA_CRUSHER).setValue(grad_area_crusher);
        getParam(GRADIENT_AREA_CRUSHER_PI).setValue(grad_area_crusher * (GradientMath.GAMMA) * sliceThickness);

        // --------------------------------------------------------------------------------------------------------------------------------------------
        // TIMING --- TIMING --- TIMING --- TIMING --- TIMING --- TIMING --- TIMING --- TIMING --- TIMING --- TIMING --- TIMING --- TIMING --- TIMING
        // --------------------------------------------------------------------------------------------------------------------------------------------
        Events.checkEventShortcut(getSequence());

        // ------------------------------------------
        // delays for FIR
        // ------------------------------------------
        boolean is_FIR = Instrument.instance().getDevices().getCameleon().isRemoveAcquDeadPoint();
        double lo_FIR_dead_point = is_FIR ? Instrument.instance().getDevices().getCameleon().getAcquDeadPointCount() : 0;
        double min_FIR_delay = (lo_FIR_dead_point + 2) / spectralWidth;
        double min_FIR_4pts_delay = 4 / spectralWidth;

        // ------------------------------------------
        // calculate delays adapted to current TE 1/2 :Check min TE
        // ------------------------------------------+
        // phase encoding may be played during Crusher to save some time in 2D
        double grad_phase_application_time_2 = isMultiplanar ? minInstructionDelay : grad_phase_application_time;
        double grad_rise_time_phase_2 = isMultiplanar ? minInstructionDelay : grad_rise_time;
        boolean enable_phase_2 = !isMultiplanar;

        //  time  duration of event blocks
        double time90toDelay1 = txLength90 / 2.0
                + TimeEvents.getTimeBetweenEvents(getSequence(), Events.TX90.ID + 1, Events.DelayTE1.ID - 1);
        double timeCrusherToLoopEcho = TimeEvents.getTimeBetweenEvents(getSequence(), Events.CrusherStart.ID, Events.LoopStartEcho.ID - 1);
        double timeLoopEchoTo180h2 = TimeEvents.getTimeBetweenEvents(getSequence(), Events.LoopStartEcho.ID, Events.TX180.ID - 1)
                + txLength180 / 2.0;
        double time180h2ToDelay2 = txLength180 / 2.0 +
                TimeEvents.getTimeBetweenEvents(getSequence(), Events.TX180.ID + 1, Events.DelayTE2.ID - 1);
        double timeADCStartToEcho = observation_time / 2.0;
        double timeROStartToEcho = TimeEvents.getTimeBetweenEvents(getSequence(), Events.Acq.ID - 2, Events.Acq.ID - 1)
                + observation_time / 2.0;
        double timeEchoToFIR = observation_time / 2.0;

        double timeEchoToROEnd = observation_time / 2.0 +
                TimeEvents.getTimeBetweenEvents(getSequence(), Events.Acq.ID + 1, Events.Acq.ID + 2);
        double timeDelay3ToLoopEchoEnd = TimeEvents.getTimeBetweenEvents(getSequence(), Events.DelayTE3.ID + 1, Events.LoopEndEcho.ID);
        double timeFIRToLoopEchoEnd = TimeEvents.getTimeBetweenEvents(getSequence(), Events.LoopEndEcho.ID, Events.LoopEndEcho.ID);

        //  time without adjustable delay
        double time90to180_noDelayNoPrephasing_hTE = time90toDelay1 + timeCrusherToLoopEcho + timeLoopEchoTo180h2;
        double time180toEcho_noDelayNoPhase_hTE = time180h2ToDelay2 + timeROStartToEcho;
        double timeEchoTo180_noDelayNoPhase_hTE = timeEchoToROEnd + timeDelay3ToLoopEchoEnd + timeLoopEchoTo180h2;

        //  min time for  90°--180° ,180°-echo, echo-180, echo-FIR4pts-180° and echo-FIR-echo°
        double timeHalfEchoMin1 = time90to180_noDelayNoPrephasing_hTE + 4 * minInstructionDelay; // Delay 1 + GradPrephasing
        double timeHalfEchoMin2 = time180toEcho_noDelayNoPhase_hTE + minInstructionDelay
                + grad_phase_application_time_2 + grad_rise_time_phase_2
                + (isDixon ? dixonPeriode / 2 : 0);// Delay 2 + GradPhase
        double timeHalfEchoMin3 = timeEchoTo180_noDelayNoPhase_hTE + minInstructionDelay
                + grad_phase_application_time_2 + grad_rise_time_phase_2
                + (isDixon ? dixonPeriode / 2 : 0);// Delay 3 + GradPhase
        double timeHalfEchoMin4 = timeEchoToFIR + min_FIR_4pts_delay + timeFIRToLoopEchoEnd + timeLoopEchoTo180h2;
        double timeEchoMin5 = timeEchoToFIR + min_FIR_delay + timeADCStartToEcho;

        // get minimal TE value & search for incoherence
        double echoSpacing_min = Math.max(timeHalfEchoMin1, timeHalfEchoMin2);
        echoSpacing_min = Math.max(echoSpacing_min, timeHalfEchoMin3);
        echoSpacing_min = Math.max(echoSpacing_min, timeHalfEchoMin4);
        echoSpacing_min = Math.max(echoSpacing_min, timeEchoMin5 / 2);
        echoSpacing_min = ceilToSubDecimal(echoSpacing_min * 2, 5);

        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        //force echo_spacing to echoSpacing_min in depending on the contrast
        if (sequenceSE == SE.FSE || echoTrainLength == 1) { // FSE ou SE
            switch (getText(IMAGE_CONTRAST)) {
                case "T1-weighted":
                case "PD-weighted":
                    echo_spacing = echoSpacing_min;
                    break;
                case "T2-weighted":
                    if (echoTrainLength > 1)
                        echo_spacing = echoSpacing_min;
                    break;
                default: // Custom
                    break;
            }
            te = echo_spacing * echoEffective;
        }

        double previous_echo_spacing = getDouble(ECHO_SPACING);
        if (echoTrainLength > 1) {
            if (roundToDecimal(previous_echo_spacing, 5) != roundToDecimal(echo_spacing, 5)) {
                this.getParam(ECHO_SPACING).setValue(echo_spacing);
            }
        }

        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        // check that Echo_Spacing > echoSpacing_min
        if (!(sequenceSE == SE.MultiEcho)) {
            if (echo_spacing < echoSpacing_min) {
                getUnreachParamExceptionManager().addParam(ECHO_SPACING.name(), echo_spacing, echoSpacing_min, ((NumberParam) getParam(ECHO_SPACING)).getMaxValue(), "Echo_Spacing too short for the User Mx1D and SW");
                echo_spacing = echoSpacing_min;
                te = echo_spacing * echoEffective;
            }
        } else {
            if (te < echoSpacing_min) {
                getUnreachParamExceptionManager().addParam(ECHO_TIME.name(), te, echoSpacing_min, ((NumberParam) getParam(ECHO_TIME)).getMaxValue(), "TE too short for the User Mx1D and SW");
                te = echoSpacing_min;
            }
        }

        // For FSE or SE with contrast, check the TE_min boundary, mainly for T2 contrast
        if ((echo_spacing * echoEffective < TE_TR_lim[0])
                && !"custom".equalsIgnoreCase(getText(IMAGE_CONTRAST))) {
//            if  (sequenceSE == SE.FSE || echoTrainLength == 1) {
            if (!(sequenceSE == SE.MultiEcho)) {
                int echoEffective_min = (int) Math.ceil(TE_TR_lim[0] / echoSpacing_min);
                getUnreachParamExceptionManager().addParam(ECHO_EFFECTIVE.name(), echoEffective, echoEffective_min, ((NumberParam) getParam(ECHO_EFFECTIVE)).getMaxValue(), "TE too short to meet contrast : or adjust LIM_T1 T2 or PD");
                if (echoEffective_min > echoTrainLength) {
                    getUnreachParamExceptionManager().addParam(ECHO_TRAIN_LENGTH.name(), echoTrainLength, echoEffective_min, ((NumberParam) getParam(ECHO_TRAIN_LENGTH)).getMaxValue(), "TE too short to meet contrast : or adjust LIM_T1 T2 or PD");
                    echoTrainLength = echoEffective_min;
                }
                echoEffective = echoEffective_min;
                te = echo_spacing * echoEffective;
            }
        }

        // ------------------------------------------
        // calculate delays adapted to current TE 2/2 :calculated  delays to get the proper TE
        // ------------------------------------------
        double grad_read_prep_application_time_2 = minInstructionDelay;
        double grad_rise_time_read_2 = minInstructionDelay;
        boolean enable_read_prep_2 = false;
        double delay1 = echo_spacing / 2.0 - time90to180_noDelayNoPrephasing_hTE;
        if (delay1 > grad_read_prep_application_time + 2 * grad_rise_time + minInstructionDelay) {
            delay1 -= (grad_read_prep_application_time + 2 * grad_rise_time);
            grad_rise_time_read_2 = grad_rise_time;
            grad_read_prep_application_time_2 = grad_read_prep_application_time;
            enable_read_prep_2 = true;
        } else {
            delay1 -= 3 * minInstructionDelay;
        }

        set(Time_grad_ramp_read_2, grad_rise_time_read_2);
        set(Time_grad_read_prep_top_2, grad_read_prep_application_time_2);
        set(Grad_enable_read_prep_1, (!enable_read_prep_2 && isEnableRead));
        set(Grad_enable_read_prep_2, (enable_read_prep_2 && isEnableRead));
        set(Time_TE_delay1, delay1);

        // set calculated the delay2 and delay3
        double delay2 = echo_spacing / 2.0 - time180toEcho_noDelayNoPhase_hTE;
        double delay3 = echo_spacing / 2.0 - timeEchoTo180_noDelayNoPhase_hTE;
        // in 2D if the delay is loong pack the Phase close to the readout
        if (!isMultiplanar ||
                ((delay2 > grad_phase_application_time + grad_rise_time + minInstructionDelay + (isDixon ? dixonPeriode / 2 : 0))
                        && (delay3 > grad_phase_application_time + grad_rise_time + minInstructionDelay + (isDixon ? dixonPeriode / 2 : 0)))) {
            delay2 -= (grad_phase_application_time + grad_rise_time);
            delay3 -= (grad_phase_application_time + grad_rise_time);
            grad_rise_time_phase_2 = grad_rise_time;
            grad_phase_application_time_2 = grad_phase_application_time;
            enable_phase_2 = true;
        } else {
            delay2 -= 2 * minInstructionDelay;
            delay3 -= 2 * minInstructionDelay;
        }
        if (echoTrainLength == 1) {
            delay3 = minInstructionDelay;
        }

        set(Time_grad_ramp_phase, grad_rise_time_phase_2);
        set(Time_grad_phase_top, grad_phase_application_time_2);
        set(Grad_enable_phase_2D, enable_phase_2 && isEnablePhase);
        set(Grad_enable_phase_bis, !enable_phase_2 && isEnablePhase);

        Table tableDelay2 = setSequenceTableValues(Time_TE_delay2, Order.Four);
        Table tableDelay3 = setSequenceTableValues(Time_TE_delay3, Order.Four);

        if (isDixon && !isKSCenterMode) {
            tableDelay2.add(delay2 - dixonPeriode / 2);
            tableDelay3.add(delay3 + dixonPeriode / 2);
            tableDelay2.add(delay2);
            tableDelay3.add(delay3);
            tableDelay2.add(delay2 + dixonPeriode / 2);
            tableDelay3.add(delay3 - dixonPeriode / 2);
        } else {
            tableDelay2.add(delay2);
            tableDelay3.add(delay3);
        }

        // calculate Effective echo time
        double effective_te = echo_spacing; //  in case of Sequential4D and Centered2D
        if (!(sequenceSE == SE.MultiEcho)) {
            int SignificantEcho = plugin.getSignificantEcho();
            if (sequenceSE == SE.OneShotFSE)
                SignificantEcho = echoEffective;
            effective_te = echo_spacing * SignificantEcho;
        }
        double previous_effective_te = getDouble(ECHO_TIME_EFFECTIVE);
        if (roundToDecimal(previous_effective_te, 5) != roundToDecimal(effective_te, 5)) {
            this.getParam(ECHO_TIME_EFFECTIVE).setValue(effective_te);
        }
        double previous_te = getDouble(ECHO_TIME);
        if (roundToDecimal(previous_te, 5) != roundToDecimal(effective_te, 5)) {
            this.getParam(ECHO_TIME).setValue(effective_te);
        }

        // -------------------------------------------------------------------------------------------------
        // Spoiler Gradient
        // -------------------------------------------------------------------------------------------------
        double time_grad_crusher_end_top = getDouble(GRADIENT_CRUSHER_END_TOP_TIME);
        set(Time_grad_crusher_top2, time_grad_crusher_end_top);

        Gradient gradSliceSpoiler = Gradient.createGradient(getSequence(), Grad_amp_spoiler_slice, Time_grad_crusher_top2, Grad_shape_rise_up, Grad_shape_rise_down, Time_grad_ramp, nucleus);
        boolean is_spoiler = getBoolean(GRADIENT_ENABLE_SPOILER);
        if (is_spoiler) {
            gradSliceSpoiler.addSpoiler(getDouble(GRADIENT_AMP_SPOILER));
        }
        gradSliceSpoiler.applyAmplitude();

        //--------------------------------------------------------------------------------------
        //  External triggering
        //--------------------------------------------------------------------------------------
        set(Synchro_trigger, isTrigger ? TimeElement.Trigger.External : TimeElement.Trigger.Timer);
        getSequenceParam(Synchro_trigger).setLocked(true);
        double time_external_trigger_delay_max = minInstructionDelay;

        Table triggerdelay = setSequenceTableValues(Time_trigger_delay, Order.Four);
        if ((!isTrigger)) {
            triggerdelay.add(minInstructionDelay);
        } else {
            for (int i = 0; i < numberOfTrigger; i++) {
                double time_external_trigger_delay = roundToDecimal(triggerTime.get(i), 7);
                time_external_trigger_delay = Math.max(time_external_trigger_delay, minInstructionDelay);
                triggerdelay.add(time_external_trigger_delay);
                time_external_trigger_delay_max = Math.max(time_external_trigger_delay_max, time_external_trigger_delay);
            }
        }
        set(Ext_trig_source, TRIGGER_CHANEL);

        //--------------------------------------------------------------------------------------
        //  Sat-Band Fat-Sat timing
        //--------------------------------------------------------------------------------------
        //Calculated in RF pulse calculation because needed for Flip angle calculation
//        double timeFatSatWepDelay =
        double timeFatSatWepDelay = minInstructionDelay;
        double phaseFatSat = 0.0;
        if (is_fatsat_binomial) {
            double timeFatSatWepBtwPulse = TimeEvents.getTimeForEvents(getSequence(), Events.FatSatPulseWep.ID, Events.FatSatPulse.ID) / 2
                    + TimeEvents.getTimeForEvents(getSequence(), Events.FatSatPulse.ID - 1);

            int nbFatWaterRotation = (int) Math.round(timeFatSatWepBtwPulse / (1 / Math.abs(tx_frequency_offset_90_fs)));
            timeFatSatWepDelay = (1 / Math.abs(tx_frequency_offset_90_fs)) * (nbFatWaterRotation + 1 / 2.0)
                    - timeFatSatWepBtwPulse;

            phaseFatSat = 180;
        }
        set(Time_wep_delay, timeFatSatWepDelay);


        set(Tx_phase_fatsat_wep, 0);
        set(Tx_phase_fatsat, phaseFatSat);


        //  Fat-Sat gradient
        Gradient gradFatsatRead = Gradient.createGradient(getSequence(), Grad_amp_fatsat_read, Time_grad_fatsat, Grad_shape_rise_up, Grad_shape_rise_down, Time_grad_ramp_fatsat, nucleus);
        Gradient gradFatsatPhase = Gradient.createGradient(getSequence(), Grad_amp_fatsat_phase, Time_grad_fatsat, Grad_shape_rise_up, Grad_shape_rise_down, Time_grad_ramp_fatsat, nucleus);
        Gradient gradFatsatSlice = Gradient.createGradient(getSequence(), Grad_amp_fatsat_slice, Time_grad_fatsat, Grad_shape_rise_up, Grad_shape_rise_down, Time_grad_ramp_fatsat, nucleus);

        if (is_fatsat_enabled) {
            double pixel_dimension_ph = getDouble(RESOLUTION_PHASE);
            double pixel_dimension_sl = getDouble(RESOLUTION_SLICE);

            int pixmax = (pixelDimension > pixel_dimension_ph) ? 1 : 2;
            pixmax = (Math.max(pixelDimension, pixel_dimension_ph) > pixel_dimension_sl) ? pixmax : 3;
            boolean test_grad;
            double min_fatsat_application_time;
            switch (pixmax) {
                case 1:
                    test_grad = gradFatsatRead.addSpoiler(pixelDimension, 3);
                    min_fatsat_application_time = gradFatsatRead.getMinTopTime();
                    break;
                case 2:
                    test_grad = gradFatsatPhase.addSpoiler(pixel_dimension_ph, 3);
                    min_fatsat_application_time = gradFatsatPhase.getMinTopTime();
                    break;
                default:
                    test_grad = gradFatsatSlice.addSpoiler(pixel_dimension_sl, 3);
                    min_fatsat_application_time = gradFatsatSlice.getMinTopTime();
            }

            if (!test_grad) {
                notifyOutOfRangeParam(FATSAT_GRAD_APP_TIME, min_fatsat_application_time, ((NumberParam) getParam(FATSAT_GRAD_APP_TIME)).getMaxValue(), "FATSAT_GRAD_APP_TIME too short to get correct Spoiling");
                grad_fatsat_application_time = min_fatsat_application_time;
                set(Time_grad_fatsat, grad_fatsat_application_time);
                gradFatsatRead.rePrepare();
                gradFatsatPhase.rePrepare();
                gradFatsatSlice.rePrepare();
            }
        }
        gradFatsatRead.applyAmplitude();
        gradFatsatPhase.applyAmplitude();
        gradFatsatSlice.applyAmplitude();

        // -------------------------------------------------------------------------------------------------
        // INVERSION RECOVERY
        // -------------------------------------------------------------------------------------------------
        if (isInversionRecovery) {
            boolean is_IR_crusher = getBoolean(GRADIENT_ENABLE_CRUSHER_IR);
            // prepare Crusher gradient
            if (is_IR_crusher) {
                double time_grad_IR_tmp_top = getDouble(GRADIENT_CRUSHER_IR_TOP_TIME);

                // IR Crusher:
                double grad_amp_crusher_IR = getDouble(GRADIENT_AMP_CRUSHER_IR);
                set(Grad_amp_crusher_IR, grad_amp_crusher_IR);
                set(Time_grad_IR_crusher_top, time_grad_IR_tmp_top);
                set(Time_grad_IR_crusher_ramp, grad_rise_time);
            } else {
                set(Time_grad_IR_crusher_top, minInstructionDelay);
                set(Time_grad_IR_crusher_ramp, minInstructionDelay);
            }
            set(Time_tx_IR_length, txLength180);
            set(Time_grad_IR_ramp, grad_rise_time);
        } else {
            set(Time_tx_IR_length, minInstructionDelay);
            set(Time_grad_IR_ramp, minInstructionDelay);
            set(Time_grad_IR_crusher_top, minInstructionDelay);
            set(Time_grad_IR_crusher_ramp, minInstructionDelay);
        }

        // ------------------------------------------
        // calculate delays adapted to current TI
        // ------------------------------------------

        double time_IR_delay_max = minInstructionDelay;

        Table time_TI_delay = getSequenceTable(Time_TI_delay);
        time_TI_delay.clear();

        //  double time_TI_delay;
        if (isInversionRecovery) {
            //TI
            double time_IRto90_noIRDelay = txLength180 / 2.0
                    + TimeEvents.getTimeBetweenEvents(getSequence(), Events.IR.ID + 1, Events.LoopSatBandStart.ID - 1)
                    + TimeEvents.getTimeBetweenEvents(getSequence(), Events.LoopSatBandStart.ID, Events.LoopSatBandEnd.ID) * nb_satband
                    + TimeEvents.getTimeBetweenEvents(getSequence(), Events.LoopSatBandEnd.ID + 1, Events.IRDelay.ID - 1)
                    + TimeEvents.getTimeBetweenEvents(getSequence(), Events.IRDelay.ID + 1, Events.TX90.ID - 1)
                    + txLength90 / 2.0;

            boolean increaseTI = false;
            // ArrayList<Number> arrayListTI = new ArrayList<Number>();
            ArrayList arrayListTI_min = new ArrayList();
            for (int i = 0; i < numberOfInversionRecovery; i++) {
                double IR_time = inversionRecoveryTime.get(i);
                // arrayListTI.add(IR_time);
                double iRDelay = IR_time - time_IRto90_noIRDelay;
                System.out.println("delay0 " + iRDelay);

                if ((iRDelay < minInstructionDelay)) {
                    double ti_min = ceilToSubDecimal((time_IRto90_noIRDelay + minInstructionDelay), 4);
                    System.out.println("IR_time-" + IR_time + " -- >" + ti_min);
                    IR_time = ti_min;
                    increaseTI = true;
                }
                arrayListTI_min.add(IR_time);
                iRDelay = IR_time - time_IRto90_noIRDelay;
                time_IR_delay_max = Math.max(time_IR_delay_max, iRDelay);
                time_TI_delay.add(iRDelay);
            }
            if (increaseTI) {
                System.out.println(" --- -- -- - - - increaseTI-------------------- ");
                inversionRecoveryTime.clear();
                inversionRecoveryTime.addAll(arrayListTI_min);
                getParam(INVERSION_TIME_MULTI).setValue(inversionRecoveryTime);
            }
            time_TI_delay.setOrder(Order.Four);
            time_TI_delay.setLocked(true);
        } else {
            time_IR_delay_max = minInstructionDelay;
            time_TI_delay.add(minInstructionDelay);
        }

        // ------------------------------------------
        // calculate TR & search for incoherence
        // ------------------------------------------
        // for Cam3
        double min_flush_delay = min_time_per_acq_point * acquisitionMatrixDimension1D * echoTrainLength * nbOfInterleavedSlice * 2;   // minimal time to flush Chameleon buffer (this time is doubled to avoid hidden delays);
        min_flush_delay = Math.max(CameleonVersion105 ? min_flush_delay : minInstructionDelay, minInstructionDelay);
        set(Time_flush_delay, min_flush_delay);

        //  time  duration of event blocks
        double delay_before_multi_planar_loop = TimeEvents.getTimeBetweenEvents(getSequence(), Events.Start.ID, Events.TriggerDelay.ID - 1)
                + time_external_trigger_delay_max
                + TimeEvents.getTimeBetweenEvents(getSequence(), Events.TriggerDelay.ID + 1, Events.LoopMultiPlanarStart.ID - 1);
        double delay_satBand_loop = TimeEvents.getTimeBetweenEvents(getSequence(), Events.LoopSatBandStart.ID, Events.LoopSatBandEnd.ID);
        double delay_before_echo_loop = TimeEvents.getTimeBetweenEvents(getSequence(), Events.LoopMultiPlanarStart.ID, Events.LoopSatBandStart.ID - 1)
                + delay_satBand_loop * nb_satband
                + TimeEvents.getTimeBetweenEvents(getSequence(), Events.LoopSatBandEnd.ID + 1, Events.IRDelay.ID - 1)
                + time_IR_delay_max
                + TimeEvents.getTimeBetweenEvents(getSequence(), Events.IRDelay.ID + 1, Events.LoopStartEcho.ID - 1);

        double delay_echo_loop = TimeEvents.getTimeBetweenEvents(getSequence(), Events.LoopStartEcho.ID, Events.DelayTE2.ID - 1)
                + delay2
                + TimeEvents.getTimeBetweenEvents(getSequence(), Events.DelayTE2.ID + 1, Events.DelayTE3.ID - 1) +
                +delay3
                + TimeEvents.getTimeBetweenEvents(getSequence(), Events.DelayTE3.ID + 1, Events.LoopEndEcho.ID);

        double delay_spoiler = TimeEvents.getTimeBetweenEvents(getSequence(), Events.LoopEndEcho.ID + 1, Events.LoopMultiPlanarEnd.ID - 2);// grad_phase_application_time + grad_rise_time * 2;

        double delay_afterEchoLoop_noTRDelay = TimeEvents.getTimeBetweenEvents(getSequence(), Events.LoopEndEcho.ID + 1, Events.DelayTR4.ID - 1) +
                TimeEvents.getTimeBetweenEvents(getSequence(), Events.DelayTR4.ID + 1, Events.LoopMultiPlanarEnd.ID);
        double delay_afterMultiplanarLoop_NoLastDelay = TimeEvents.getTimeBetweenEvents(getSequence(), Events.LoopMultiPlanarEnd.ID + 2, Events.TimeFlush.ID);

        //  = = = = =  = = = = = = = = = = = = = =
        double timeSliceLoop_noDelay = delay_before_echo_loop + delay_echo_loop * echoTrainLength + delay_afterEchoLoop_noTRDelay;

        double timeSliceLoop_min = timeSliceLoop_noDelay + minInstructionDelay;
        double timeBeforeAndAfterSlice_min = delay_before_multi_planar_loop + minInstructionDelay + delay_afterMultiplanarLoop_NoLastDelay;


        if (getText(IMAGE_CONTRAST).equalsIgnoreCase("T1-weighted") && isMultiplanar) {
            nbOfInterleavedSlice = (int) Math.floor((TE_TR_lim[3] - timeBeforeAndAfterSlice_min) / timeSliceLoop_min);
            nbOfInterleavedSlice = getInferiorDivisorToGetModulusZero(nbOfInterleavedSlice, acquisitionMatrixDimension3D);
            nb_of_shoot_3d = userMatrixDimension3D / nbOfInterleavedSlice;

            getParam(NUMBER_OF_SHOOT_3D).setValue(nb_of_shoot_3d);
            getParam(NUMBER_OF_INTERLEAVED_SLICE).setValue(nbOfInterleavedSlice);
            plugin = getTransformPlugin();
            plugin.setParameters(new ArrayList<>(getUserParams()));
        }

//        double time_1Slc_to_end_spoiler = (delay_before_multi_planar_loop + (delay_before_echo_loop + (echoTrainLength * delay_echo_loop) + delay_spoiler));
//        time_seq_to_end_spoiler = time_1Slc_to_end_spoiler * nbOfInterleavedSlice;

        double tr_min = timeBeforeAndAfterSlice_min + timeSliceLoop_min * nbOfInterleavedSlice;
        tr_min = ceilToSubDecimal(tr_min, 4);
        switch (getText(IMAGE_CONTRAST)) {
            case "T1-weighted":
                tr = Math.max(TE_TR_lim[2], tr_min); // TR should be not lower than the inferior limit
                break;
            case "PD-weighted":
            case "T2-weighted":
                // TR should be minimal
                tr = Math.max(TE_TR_lim[2], tr); // TR should be not lower than the inferior limit
                break;
            default:
                break;
        }
        double previous_tr = getDouble(REPETITION_TIME);
        if (roundToDecimal(previous_tr, 5) != roundToDecimal(tr, 5)) {
            getParam(REPETITION_TIME).setValue(tr);
        }

        if (tr > TE_TR_lim[3] && isMultiplanar) { // for T1w if TR_min already too large, advises ways to reduce TR
            //     notifyOutOfRangeParam(REPETITION_TIME, tr_min, ((NumberParam) getParam(REPETITION_TIME)).getMaxValue(), "(1): TR too short to reach (ETL * User Mx3D/Shoot3D) in a single scan - (2):as TR increases, T1-weighted imaging is not guaranteed");
            int max_nb_interleaved_excitation = (int) Math.floor(
                    (TE_TR_lim[3] - timeBeforeAndAfterSlice_min)
                            / (tr_min - timeBeforeAndAfterSlice_min)
                            * ((float) userMatrixDimension3D / nb_of_shoot_3d));
            max_nb_interleaved_excitation = getInferiorDivisorToGetModulusZero(max_nb_interleaved_excitation, userMatrixDimension3D);
            int nb_of_shoot_3d_min = userMatrixDimension3D / max_nb_interleaved_excitation;
            notifyOutOfRangeParam(NUMBER_OF_SHOOT_3D, nb_of_shoot_3d_min, ((NumberParam) getParam(NUMBER_OF_SHOOT_3D)).getMaxValue(), "TR need to be reduce to guaranty T1-weighted imaging: increase NUMBER_OF_SHOOT_3D \"");
            nb_of_shoot_3d = nb_of_shoot_3d_min;
        }

        if (tr < tr_min) {
            notifyOutOfRangeParam(REPETITION_TIME, tr_min, ((NumberParam) getParam(REPETITION_TIME)).getMaxValue(), "TR too short to reach (ETL * User Mx3D / Shoot3D) in a single scan");
            tr = tr_min;
        }

        // ------------------------------------------
        // set calculated TR
        // ------------------------------------------
        // set  TR delay to compensate and trigger delays
        double tr_delay, last_delay;

        Table time_tr_delay = setSequenceTableValues(Time_TR_delay, Order.Four);
        Table time_last_delay = setSequenceTableValues(Time_last_delay, Order.Four);

        if (isSlicePacked) {
            tr_delay = minInstructionDelay;
            time_tr_delay.add(tr_delay);

            last_delay = tr - (delay_before_multi_planar_loop + delay_afterMultiplanarLoop_NoLastDelay) - timeSliceLoop_min * nbOfInterleavedSlice;
            if (numberOfInversionRecovery != 1) {
                for (int i = 0; i < numberOfInversionRecovery; i++) {
                    double last_delay_i = last_delay - (time_TI_delay.get(i).doubleValue() - time_IR_delay_max) * nbOfInterleavedSlice;
                    time_last_delay.add(last_delay_i);
                }
            } else if (numberOfTrigger != 1) {
                for (int i = 0; i < numberOfTrigger; i++) {
                    double last_delay_i = last_delay - (triggerdelay.get(i).doubleValue() - time_external_trigger_delay_max);
                    time_last_delay.add(last_delay_i);
                }
            } else {
                time_last_delay.add(last_delay);
            }
        } else {
            tr_delay = (tr - timeBeforeAndAfterSlice_min) / nbOfInterleavedSlice - timeSliceLoop_noDelay;
            if (numberOfInversionRecovery != 1) {
                for (int i = 0; i < numberOfInversionRecovery; i++) {
                    double tr_delay_i = tr_delay - (time_TI_delay.get(i).doubleValue() - time_IR_delay_max);
                    time_tr_delay.add(tr_delay_i);
                }
            } else if (numberOfTrigger != 1) {
                for (int i = 0; i < numberOfTrigger; i++) {
                    double tr_delay_i = tr_delay - (triggerdelay.get(i).doubleValue() - time_external_trigger_delay_max) / nbOfInterleavedSlice;
                    time_tr_delay.add(tr_delay_i);
                }
            } else {
                time_tr_delay.add(tr_delay);
            }

            last_delay = minInstructionDelay;
            time_last_delay.add(last_delay);
        }

        //----------------------------------------------------------------------
        // DYNAMIC SEQUENCE
        // Calculate frame acquisition time
        // Calculate delay between 4D acquisition
        //----------------------------------------------------------------------
        Table dyn_delay = setSequenceTableValues(Time_btw_dyn_frames, Order.Four);
        double frame3D_acquisition_time = nb_scan_1d * nb_scan_3d * nb_scan_2d * tr;
        int nb_4d_withoutDyn = numberOfInversionRecovery * (isDixon ? 3 : 1) * numberOfTrigger;
        double time_4DFramesNoDyn_withoutDynDelay = (frame3D_acquisition_time * nb_4d_withoutDyn
                + min_flush_delay * (nb_4d_withoutDyn - 1));

        double interval_between_dynFrames_delay = min_flush_delay;
        if (isDynamic) {
            double time_between_dynFrames_min = ceilToSubDecimal(time_4DFramesNoDyn_withoutDynDelay + min_flush_delay, 3);

            //    double time_between_frames = getDouble(DYN_TIME_BTW_FRAMES);
            double time_between_dynFrames = isDynamicMinTime ? time_between_dynFrames_min : getDouble(DYN_TIME_BTW_FRAMES);
            getParam(DYN_TIME_BTW_FRAMES).setValue(time_between_dynFrames);

            if (time_between_dynFrames < (time_between_dynFrames_min)) {
                this.notifyOutOfRangeParam(DYN_TIME_BTW_FRAMES, time_between_dynFrames_min, ((NumberParam) getParam(DYN_TIME_BTW_FRAMES)).getMaxValue(), "Minimum frame acquisition time ");
                time_between_dynFrames = time_between_dynFrames_min;
            }

            interval_between_dynFrames_delay = time_between_dynFrames - time_4DFramesNoDyn_withoutDynDelay;
            if (numberOfInversionRecovery != 1) {
                for (int i = 0; i < numberOfInversionRecovery - 1; i++) {
                    dyn_delay.add(min_flush_delay);
                }
            } else if (isDixon) {
                for (int i = 0; i < 3 - 1; i++) {
                    dyn_delay.add(minInstructionDelay);
                }
            }
        }
        dyn_delay.add(interval_between_dynFrames_delay);

        // ------------------------------------------------------------------
        // Total Acquisition Time
        // ------------------------------------------------------------------
        double sequenceTime = (time_4DFramesNoDyn_withoutDynDelay + interval_between_dynFrames_delay) * numberOfDynamicAcquisition + tr * preScan;
        getParam(SEQUENCE_TIME).setValue(sequenceTime);

        // -----------------------------------------------
        // Phase Reset
        // -----------------------------------------------
        set(Phase_reset, PHASE_RESET);

        // ----------- init Freq offset---------------------
        set(Frequency_offset_init, 0.0);// PSD should start with a zero offset frequency pulse

        // ------------------------------------------------------------------
        //calculate TX FREQUENCY offsets tables for slice positionning
        // ------------------------------------------------------------------
        RFPulse pulseTXIR = RFPulse.createRFPulse(getSequence(), Tx_att, Tx_amp_180, Tx_phase_180, Time_tx_IR_length, Tx_shape_180, Tx_shape_phase_180, Tx_freq_offset_IR);

        if (isMultiplanar && nb_planar_excitation > 1 && isEnableSlice) {
            //MULTI-PLANAR case : calculation of frequency offset table
            pulseTX90.prepareOffsetFreqMultiSlice(gradSlice90, nb_planar_excitation, spacingBetweenSlice, off_center_distance_3D);
            pulseTX90.reoderOffsetFreq(plugin, acquisitionMatrixDimension1D * echoTrainLength, nbOfInterleavedSlice);
            pulseTX90.setFrequencyOffset(nbOfInterleavedSlice != 1 ? Order.ThreeLoop : Order.Three);
            pulseTX180.prepareOffsetFreqMultiSlice(gradSlice180, nb_planar_excitation, spacingBetweenSlice, off_center_distance_3D);
            pulseTX180.reoderOffsetFreq(plugin, acquisitionMatrixDimension1D * echoTrainLength, nbOfInterleavedSlice);
            pulseTX180.setFrequencyOffset(nbOfInterleavedSlice != 1 ? Order.ThreeLoop : Order.Three);
            if (isInversionRecovery) {
                pulseTXIR.prepareOffsetFreqMultiSlice(gradSlice180, nb_planar_excitation, spacingBetweenSlice, off_center_distance_3D);
                pulseTXIR.reoderOffsetFreq(plugin, acquisitionMatrixDimension1D * echoTrainLength, nbOfInterleavedSlice);
            }
            pulseTXIR.setFrequencyOffset(isInversionRecovery ? (nbOfInterleavedSlice != 1 ? Order.ThreeLoop : Order.Three) : Order.FourLoop);

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


        //--------------------------------------------------------------------------------------
        //  SAT-BAND gradient and offsetFreq
        //--------------------------------------------------------------------------------------

        // Calculate gradient Amplitude
        double satband_thickness = getDouble(SATBAND_THICKNESS);
        double grad_amp_satband = 0;
        double grad_amp_satband_mTpm = 0;

        if (is_satband_enabled) {
            Gradient gradSB = Gradient.createGradient(getSequence(), Grad_amp_sb_read, Time_tx_sb, Grad_shape_rise_up, Grad_shape_rise_down, Time_grad_ramp_sb, nucleus);
            if (!gradSB.prepareSliceSelection(tx_bandwidth_sb, satband_thickness)) {
                double satband_thickness_mod = gradSB.getSliceThickness();
                notifyOutOfRangeParam(SATBAND_THICKNESS, satband_thickness_mod, ((NumberParam) getParam(SATBAND_THICKNESS)).getMaxValue(), "Pulse length too short to reach this satband slice thickness");
                satband_thickness = satband_thickness_mod;
            }
            grad_amp_satband = gradSB.getAmplitude();
            grad_amp_satband_mTpm = gradSB.getAmplitude_mTpm();
        }

        // Calculate allocate Gradient amplitude, spoiler, and freq offset
        double satband_distance_from_fov = getDouble(SATBAND_DISTANCE_FROM_FOV);

        double[] gradAmpSBSliceTable = new double[nb_satband];
        double[] gradAmpSBPhaseTable = new double[nb_satband];
        double[] gradAmpSBReadTable = new double[nb_satband];
        double[] gradAmpSBSliceSpoilerTable = new double[nb_satband];
        double[] gradAmpSBPhaseSpoilerTable = new double[nb_satband];
        double[] gradAmpSBReadSpoilerTable = new double[nb_satband];
        double[] offsetFreqSBTable = new double[nb_satband];

        double grad_amp_sat_spoiler = getDouble(SATBAND_GRAD_AMP_SPOILER);
        if (is_satband_enabled) {
            position_sli_ph_rea = satBandPrep(SATBAND_ORIENTATION, ORIENTATION, IMAGE_ORIENTATION_SUBJECT);
            int n = 0;
            if (position_sli_ph_rea[0] == 1) { // SB  in slice position Sup
                gradAmpSBSliceTable[n] = grad_amp_satband;
                gradAmpSBPhaseTable[n] = 0;
                gradAmpSBReadTable[n] = 0;
                gradAmpSBSliceSpoilerTable[n] = 0;
                gradAmpSBPhaseSpoilerTable[n] = grad_amp_sat_spoiler;
                gradAmpSBReadSpoilerTable[n] = grad_amp_sat_spoiler;
                double off_center_pos = off_center_distance_3D + fov3d / 2.0 + satband_distance_from_fov + satband_thickness / 2.0;
                offsetFreqSBTable[n] = new RFPulse().calculateOffsetFreq(grad_amp_satband_mTpm, off_center_pos);
                n += 1;
            }
            if (position_sli_ph_rea[1] == 1) {
                gradAmpSBSliceTable[n] = grad_amp_satband;
                gradAmpSBPhaseTable[n] = 0;
                gradAmpSBReadTable[n] = 0;
                gradAmpSBSliceSpoilerTable[n] = 0;
                gradAmpSBPhaseSpoilerTable[n] = grad_amp_sat_spoiler;
                gradAmpSBReadSpoilerTable[n] = grad_amp_sat_spoiler;
                double off_center_neg = off_center_distance_3D - (fov3d / 2.0 + satband_distance_from_fov + satband_thickness / 2.0);
                offsetFreqSBTable[n] = new RFPulse().calculateOffsetFreq(grad_amp_satband_mTpm, off_center_neg);
                n += 1;
            }
            if (position_sli_ph_rea[2] == 1) {
                gradAmpSBSliceTable[n] = 0;
                gradAmpSBPhaseTable[n] = grad_amp_satband;
                gradAmpSBReadTable[n] = 0;
                gradAmpSBSliceSpoilerTable[n] = grad_amp_sat_spoiler;
                gradAmpSBPhaseSpoilerTable[n] = 0;
                gradAmpSBReadSpoilerTable[n] = grad_amp_sat_spoiler;
                double off_center_pos = off_center_distance_2D + fovPhase / 2.0 + satband_distance_from_fov + satband_thickness / 2.0;
                offsetFreqSBTable[n] = new RFPulse().calculateOffsetFreq(grad_amp_satband_mTpm, off_center_pos);
                n += 1;
            }
            if (position_sli_ph_rea[3] == 1) {
                gradAmpSBSliceTable[n] = 0;
                gradAmpSBPhaseTable[n] = grad_amp_satband;
                gradAmpSBReadTable[n] = 0;
                gradAmpSBSliceSpoilerTable[n] = grad_amp_sat_spoiler;
                gradAmpSBPhaseSpoilerTable[n] = 0;
                gradAmpSBReadSpoilerTable[n] = grad_amp_sat_spoiler;
                double off_center_neg = off_center_distance_2D - (fovPhase / 2.0 + satband_distance_from_fov + satband_thickness / 2.0);
                offsetFreqSBTable[n] = new RFPulse().calculateOffsetFreq(grad_amp_satband_mTpm, off_center_neg);
                n += 1;
            }
            if (position_sli_ph_rea[4] == 1) {
                gradAmpSBSliceTable[n] = 0;
                gradAmpSBPhaseTable[n] = 0;
                gradAmpSBReadTable[n] = grad_amp_satband;
                gradAmpSBSliceSpoilerTable[n] = grad_amp_sat_spoiler;
                gradAmpSBPhaseSpoilerTable[n] = grad_amp_sat_spoiler;
                gradAmpSBReadSpoilerTable[n] = 0;
                double off_center_pos = off_center_distance_1D + fov / 2.0 + satband_distance_from_fov + satband_thickness / 2.0;
                offsetFreqSBTable[n] = new RFPulse().calculateOffsetFreq(grad_amp_satband_mTpm, off_center_pos);
                n += 1;
            }
            if (position_sli_ph_rea[5] == 1) {
                gradAmpSBSliceTable[n] = 0;
                gradAmpSBPhaseTable[n] = 0;
                gradAmpSBReadTable[n] = grad_amp_satband;
                gradAmpSBSliceSpoilerTable[n] = grad_amp_sat_spoiler;
                gradAmpSBPhaseSpoilerTable[n] = grad_amp_sat_spoiler;
                gradAmpSBReadSpoilerTable[n] = 0;
                double off_center_neg = off_center_distance_1D - (fov / 2.0 + satband_distance_from_fov + satband_thickness / 2.0);
                offsetFreqSBTable[n] = new RFPulse().calculateOffsetFreq(grad_amp_satband_mTpm, off_center_neg);
//                n += 1;
            }
        }

        // Apply values ot Gradient
        Gradient gradSatBandSlice = Gradient.createGradient(getSequence(), Grad_amp_sb_slice, Time_tx_sb, Grad_shape_rise_up, Grad_shape_rise_down, Time_grad_ramp_sb, nucleus);
        gradSatBandSlice.setAmplitude(gradAmpSBSliceTable);
        gradSatBandSlice.applyAmplitude(is_satband_enabled ? Order.LoopB : Order.FourLoop);

        Gradient gradSatBandPhase = Gradient.createGradient(getSequence(), Grad_amp_sb_phase, Time_tx_sb, Grad_shape_rise_up, Grad_shape_rise_down, Time_grad_ramp_sb, nucleus);
        gradSatBandPhase.setAmplitude(gradAmpSBPhaseTable);
        gradSatBandPhase.applyAmplitude(is_satband_enabled ? Order.LoopB : Order.FourLoop);

        Gradient gradSatBandRead = Gradient.createGradient(getSequence(), Grad_amp_sb_read, Time_tx_sb, Grad_shape_rise_up, Grad_shape_rise_down, Time_grad_ramp_sb, nucleus);
        gradSatBandRead.setAmplitude(gradAmpSBReadTable);
        gradSatBandRead.applyAmplitude(is_satband_enabled ? Order.LoopB : Order.FourLoop);

        Gradient gradSatBandSpoilerSlice = Gradient.createGradient(getSequence(), Grad_amp_sb_slice_spoiler, Time_grad_sb, Grad_shape_rise_up, Grad_shape_rise_down, Time_grad_ramp_sb, nucleus);
        gradSatBandSpoilerSlice.setAmplitude(gradAmpSBSliceSpoilerTable);
        gradSatBandSpoilerSlice.applyAmplitude(is_satband_enabled ? Order.LoopB : Order.FourLoop);

        Gradient gradSatBandSpoilerPhase = Gradient.createGradient(getSequence(), Grad_amp_sb_phase_spoiler, Time_grad_sb, Grad_shape_rise_up, Grad_shape_rise_down, Time_grad_ramp_sb, nucleus);
        gradSatBandSpoilerPhase.setAmplitude(gradAmpSBPhaseSpoilerTable);
        gradSatBandSpoilerPhase.applyAmplitude(is_satband_enabled ? Order.LoopB : Order.FourLoop);

        Gradient gradSatBandSpoilerRead = Gradient.createGradient(getSequence(), Grad_amp_sb_read_spoiler, Time_grad_sb, Grad_shape_rise_up, Grad_shape_rise_down, Time_grad_ramp_sb, nucleus);
        gradSatBandSpoilerRead.setAmplitude(gradAmpSBReadSpoilerTable);
        gradSatBandSpoilerRead.applyAmplitude(is_satband_enabled ? Order.LoopB : Order.FourLoop);

        // ------------------------------------------------------------------
        //calculate TX FREQUENCY SatBand  compensation
        // ------------------------------------------------------------------
        // Apply values ot Tx offset
        pulseTXSatBand.addFrequencyOffset(offsetFreqSBTable);
        pulseTXSatBand.setFrequencyOffset(is_satband_enabled ? Order.LoopB : Order.FourLoop);
        RFPulse pulseTXSatBandPrep = RFPulse.createRFPulse(getSequence(), Time_grad_ramp_sb, Freq_offset_tx_sb_prep);
        pulseTXSatBandPrep.setCompensationFrequencyOffset(pulseTXSatBand, 0.5);
        RFPulse pulseTXSatBandComp = RFPulse.createRFPulse(getSequence(), Time_grad_ramp_sb, Freq_offset_tx_sb_comp);
        pulseTXSatBandComp.setCompensationFrequencyOffset(pulseTXSatBand, 0.5);

        // ------------------------------------------------------------------
        //calculate TX FREQUENCY FATSAT and compensation
        // ------------------------------------------------------------------
        pulseTXFatSat.setFrequencyOffset(is_fatsat_enabled && is_fatsat_binomial? tx_frequency_offset_90_fs : 0.0);

        RFPulse pulseTXFatSatPrep = RFPulse.createRFPulse(getSequence(), Time_before_fatsat_pulse, Freq_offset_tx_fatsat_prep);
        pulseTXFatSatPrep.setCompensationFrequencyOffset(pulseTXFatSat, 0.5);
        RFPulse pulseTXFatSatComp = RFPulse.createRFPulse(getSequence(), Time_grad_ramp_fatsat, Freq_offset_tx_fatsat_comp);
        pulseTXFatSatComp.setCompensationFrequencyOffset(pulseTXFatSat, 0.5);

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

        getParam(OFF_CENTER_FIELD_OF_VIEW_EFF).setValue(off_center_distanceList);

        //----------------------------------------------------------------------
        // modify RX FREQUENCY Prep and comp
        //----------------------------------------------------------------------
        double timeADC1 = TimeEvents.getTimeBetweenEvents(getSequence(), Events.Acq.ID - 1, Events.Acq.ID - 1) + observation_time / 2.0;
        double timeADC2 = TimeEvents.getTimeBetweenEvents(getSequence(), Events.Acq.ID, Events.LoopEndEcho.ID - 1)
                - observation_time + observation_time / 2.0;

        RFPulse pulseRXPrep = RFPulse.createRFPulse(getSequence(), Time_grad_ramp, FreqOffset_rx_1D_3Dprep);
        pulseRXPrep.setCompensationFrequencyOffsetWithTime(pulseRX, timeADC1);

        RFPulse pulseRXComp = RFPulse.createRFPulse(getSequence(), Time_min_instruction, FreqOffset_rx_1D_3Dcomp);
        pulseRXComp.setCompensationFrequencyOffsetWithTime(pulseRX, timeADC2);

        //--------------------------------------------------------------------------------------
        // modify TX PHASE TABLE to handle PHASE CYCLING
        //--------------------------------------------------------------------------------------
        boolean is_phase_cycling = getBoolean(PHASE_CYCLING);
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
        // todo check that condition
        if (isDynamic && isInversionRecovery && numberOfDynamicAcquisition != 1) {
            if ((sequenceSE == SE.MultiEcho) && echoTrainLength != 1) {
                for (int i = 0; i < acquisitionMatrixDimension4D; i++) {
                    arrayListTrigger.add(Math.floor(i / (double) echoTrainLength) * frame3D_acquisition_time);
                }
            } else if (numberOfInversionRecovery != 1) {
                for (int i = 0; i < acquisitionMatrixDimension4D; i++) {
                    arrayListTrigger.add(Math.floor(i / (double) numberOfInversionRecovery) * frame3D_acquisition_time);
                }
            } else {
                for (int i = 0; i < acquisitionMatrixDimension4D; i++) {
                    arrayListTrigger.add(i * frame3D_acquisition_time);
                }
            }
            if (!isTrigger) {
                ListNumberParam list = new ListNumberParam(getParam(MriDefaultParams.TRIGGER_TIME), arrayListTrigger);       // associate TE to images for DICOM export
                putVariableParameter(list, (4));
            }
        }

        // Set  ECHO_TIME
        if (sequenceSE == SE.MultiEcho) {
            ArrayList<Number> arrayListEcho = new ArrayList<>();
            for (int i = 0; i < acquisitionMatrixDimension4D; i++) {
                arrayListEcho.add(te * i);
            }
            ListNumberParam list = new ListNumberParam(getParam(MriDefaultParams.ECHO_TIME), arrayListEcho);       // associate TE to images for DICOM export
            putVariableParameter(list, (4));
        }

        if (isInversionRecovery) {
            ArrayList<Number> arrayListIR = new ArrayList<>();
            for (int i = 0; i < acquisitionMatrixDimension4D; i++) {
                arrayListIR.add(inversionRecoveryTime.get((i % numberOfInversionRecovery)));
            }
            ListNumberParam list = new ListNumberParam(MriDefaultParams.INVERSION_TIME.name(), arrayListIR, NumberEnum.Time);       // associate TE to images for DICOM export
            putVariableParameter(list, (4));
        }

        // ------------------------------------------------------------------
        // calculate Acquisition Time Offset and MultiSeries Parameter list
        // ------------------------------------------------------------------
        int number_of_MultiSeries = 1;
        double time_between_MultiSeries = 0;
        ArrayList<Number> multiseries_values_list = new ArrayList<>();
        String multiseries_parameter_name = "";

        if ((sequenceSE == SE.MultiEcho && echoTrainLength != 1)) {
            number_of_MultiSeries = echoTrainLength;
            time_between_MultiSeries = echo_spacing;
            multiseries_parameter_name = "TE";
            for (int i = 0; i < number_of_MultiSeries; i++) {
                double multiseries_value = roundToDecimal(te + i * te, 5) * 1e3;
                multiseries_values_list.add(multiseries_value);
            }
        } else if (isInversionRecovery && numberOfInversionRecovery != 1) {
            number_of_MultiSeries = numberOfInversionRecovery;
            time_between_MultiSeries = frame3D_acquisition_time;
            multiseries_parameter_name = "TI";
            for (int i = 0; i < number_of_MultiSeries; i++) {
                double IR_time = roundToDecimal(inversionRecoveryTime.get(i), 5) * 1e3;
                multiseries_values_list.add(IR_time);
            }
        } else if (isTrigger && numberOfTrigger != 1) {
            number_of_MultiSeries = numberOfTrigger;
            time_between_MultiSeries = frame3D_acquisition_time;
            multiseries_parameter_name = "TD";
            for (int i = 0; i < number_of_MultiSeries; i++) {
                double multiseries_value = roundToDecimal(triggerTime.get(i), 5) * 1e3;
                multiseries_values_list.add(multiseries_value);
            }
        } else if (isDixon) {
            number_of_MultiSeries = 3;
            multiseries_parameter_name = "Water-Fat Phase Shift";
            multiseries_values_list.add(-roundToDecimal(Math.PI,2));
            multiseries_values_list.add(0);
            multiseries_values_list.add(roundToDecimal(Math.PI,2));
        }
        getParam(MULTISERIES_PARAMETER_VALUE).setValue(multiseries_values_list);
        getParam(MULTISERIES_PARAMETER_NAME).setValue(multiseries_parameter_name);

        ArrayList<Number> acquisition_timesList = new ArrayList<>();

        double acquisition_time;
        for (
                int i = 0;
                i < numberOfDynamicAcquisition; i++) {
            for (int j = 0; j < number_of_MultiSeries; j++) {
                acquisition_time = (i * frame3D_acquisition_time + j * time_between_MultiSeries);
                if (i > 0) { // only the first dynamic phase has dummy scans
                    acquisition_time = acquisition_time + preScan * tr;
                }
                acquisition_time = roundToDecimal(acquisition_time, 3);
                acquisition_timesList.add(acquisition_time);
            }
        }
        getParam(ACQUISITION_TIME_OFFSET).setValue(acquisition_timesList);

        //--------------------------------------------------------------------------------------
        // Comments
        //--------------------------------------------------------------------------------------

        if (false) { // Show the comments
            System.out.println("");
            System.out.println(((NumberParam) getSequenceParam(Pre_scan)).intValue() + " ; Pre_scan");
            System.out.println(((NumberParam) getSequenceParam(Nb_1d)).intValue() + " ; Nb_1d");
            System.out.println((((NumberParam) getSequenceParam(Nb_2d)).getValue().intValue()) + " ; Nb_2d");
            System.out.println((((NumberParam) getSequenceParam(Nb_3d)).getValue().intValue()) + " ; Nb_3d");
            System.out.println((((NumberParam) getSequenceParam(Nb_4d)).getValue().intValue()) + " ; Nb_4d)");
            System.out.println((getSequenceTable(Nb_echo).get(0).intValue() + 1) + " ; Nb_echo");
            System.out.println((getSequenceTable(Nb_interleaved_slice).get(0).intValue() + 1) + " ; Nb_interleaved_slice");
            System.out.println((getSequenceTable(Nb_sb_loop).get(0).intValue() + 1) + " ; Nb_sb_loop");
            System.out.println(" ");
            System.out.println(tr + " ; TR");
            System.out.println(te + " ; TE");
            System.out.println(echo_spacing + " ; echo_spacing");
            System.out.println(sequenceTime + " ; sequenceTime");

            System.out.println(" ");
            for (int i = 0; i < Events.TimeFlush.ID; i++) {
                System.out.println((((TimeElement) getSequence().getTimeChannel().get(i)).getTime().getFirst().doubleValue() * 1000000));
            }
        }

//        System.out.println("  END ");

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


    private int[] satBandPrep(U satbandOrientation, U orientation, U imageOrientationSubject) {
        int[] position_sli_ph_rea = new int[6];
        boolean cranial = false;
        boolean caudal = false;
        boolean anterior = false;
        boolean posterior = false;
        boolean right = false;
        boolean left = false;
        if ("CRANIAL".equalsIgnoreCase(getText(satbandOrientation))) {
            cranial = true;
        } else if ("CAUDAL".equalsIgnoreCase(getText(satbandOrientation))) {
            caudal = true;
        } else if ("CRANIAL AND CAUDAL".equalsIgnoreCase(getText(satbandOrientation))) {
            cranial = true;
            caudal = true;
        } else if ("ANTERIOR".equalsIgnoreCase(getText(satbandOrientation))) {
            anterior = true;
        } else if ("POSTERIOR".equalsIgnoreCase(getText(satbandOrientation))) {
            posterior = true;
        } else if ("ANTERIOR AND POSTERIOR".equalsIgnoreCase(getText(satbandOrientation))) {
            anterior = true;
            posterior = true;
        } else if ("RIGHT".equalsIgnoreCase(getText(satbandOrientation))) {
            right = true;
        } else if ("LEFT".equalsIgnoreCase(getText(satbandOrientation))) {
            left = true;
        } else if ("RIGHT AND LEFT".equalsIgnoreCase(getText(satbandOrientation))) {
            right = true;
            left = true;
        } else if ("RIGHT AND LEFT".equalsIgnoreCase(getText(satbandOrientation))) {
            right = true;
            left = true;
        } else if ("ALL".equalsIgnoreCase(getText(satbandOrientation))) {
            cranial = true;
            caudal = true;
            anterior = true;
            posterior = true;
            right = true;
            left = true;
        }

        position_sli_ph_rea[0] = 0;
        position_sli_ph_rea[1] = 0;
        position_sli_ph_rea[2] = 0;
        position_sli_ph_rea[3] = 0;
        position_sli_ph_rea[4] = 0;
        position_sli_ph_rea[5] = 0;
        if ("AXIAL".equalsIgnoreCase(getText(orientation))) {
            position_sli_ph_rea[0] = cranial ? 1 : 0;
            position_sli_ph_rea[1] = caudal ? 1 : 0;
            position_sli_ph_rea[2] = anterior ? 1 : 0;
            position_sli_ph_rea[3] = posterior ? 1 : 0;
            position_sli_ph_rea[4] = right ? 1 : 0;
            position_sli_ph_rea[5] = left ? 1 : 0;
        } else if ("SAGITTAL".equalsIgnoreCase(getText(orientation))) {
            position_sli_ph_rea[0] = left ? 1 : 0;
            position_sli_ph_rea[1] = right ? 1 : 0;
            position_sli_ph_rea[2] = anterior ? 1 : 0;
            position_sli_ph_rea[3] = posterior ? 1 : 0;
            position_sli_ph_rea[4] = cranial ? 1 : 0;
            position_sli_ph_rea[5] = caudal ? 1 : 0;
        } else if ("CORONAL".equalsIgnoreCase(getText(orientation))) {
            position_sli_ph_rea[0] = anterior ? 1 : 0;
            position_sli_ph_rea[1] = posterior ? 1 : 0;
            position_sli_ph_rea[2] = right ? 1 : 0;
            position_sli_ph_rea[3] = left ? 1 : 0;
            position_sli_ph_rea[4] = cranial ? 1 : 0;
            position_sli_ph_rea[5] = caudal ? 1 : 0;
        } else if ("OBLIQUE".equalsIgnoreCase(getText(orientation))) {
            List<Double> image_orientation = getListDouble(imageOrientationSubject);
            double[][] dir_ind = new double[3][3];
            for (int i = 0; i < 3; i++) {
                dir_ind[0][i] = image_orientation.get(i);
                dir_ind[1][i] = image_orientation.get(i + 3);
            }
            dir_ind[2][0] = dir_ind[0][1] * dir_ind[1][2] - dir_ind[0][2] * dir_ind[1][1];
            dir_ind[2][1] = dir_ind[0][2] * dir_ind[1][0] - dir_ind[0][0] * dir_ind[1][2];
            dir_ind[2][2] = dir_ind[0][0] * dir_ind[1][1] - dir_ind[0][1] * dir_ind[1][0];
            int i, j;
            int max_index = 0;
            double norm_vector_re = Math.sqrt(Math.pow(dir_ind[0][0], 2) + Math.pow(dir_ind[0][1], 2) + Math.pow(dir_ind[0][2], 2));
            double norm_vector_ph = Math.sqrt(Math.pow(dir_ind[1][0], 2) + Math.pow(dir_ind[1][1], 2) + Math.pow(dir_ind[1][2], 2));
            double norm_vector_sl = Math.sqrt(Math.pow(dir_ind[2][0], 2) + Math.pow(dir_ind[2][1], 2) + Math.pow(dir_ind[2][2], 2));
            //normalizing vectors
            dir_ind[0][0] = dir_ind[0][0] / norm_vector_re;
            dir_ind[0][1] = dir_ind[0][1] / norm_vector_re;
            dir_ind[0][2] = dir_ind[0][2] / norm_vector_re;
            dir_ind[1][0] = dir_ind[1][0] / norm_vector_ph;
            dir_ind[1][1] = dir_ind[1][1] / norm_vector_ph;
            dir_ind[1][2] = dir_ind[1][2] / norm_vector_ph;
            dir_ind[2][0] = dir_ind[2][0] / norm_vector_sl;
            dir_ind[2][1] = dir_ind[2][1] / norm_vector_sl;
            dir_ind[2][2] = dir_ind[2][2] / norm_vector_sl;
            for (i = 0; i < 3; i++) {
                for (j = 0; j < 3; j++) {
                    System.out.println("dir_ind[" + i + "][" + j + "]" + dir_ind[i][j]);
                }
            }

            // System.out.println(" direction index and dir ind:  "+direction_index[2]+" "+dir_ind[0][2]);
            int[] max_vector = new int[3];

            // read, phase and slice vector which component has the largest value
            for (i = 0; i < 3; i++) {
                for (j = 0; j < 3; j++) {
                    if (Math.abs(dir_ind[i][j]) >= Math.abs(dir_ind[i][max_index])) {
                        max_index = j;
                    }
                }
                max_vector[i] = max_index; // storing each vector's maximum value index
                //System.out.println("max_vector["+i+"]"+max_vector[i]);
            }

            boolean[][] anatomy_to_local_mx = new boolean[6][6];

            for (i = 0; i < 6; i++) {
                for (j = 0; j < 6; j++) {
                    anatomy_to_local_mx[i][j] = false;
                }
            }

            for (i = 0; i < 3; i++) {

                if (dir_ind[i][max_vector[i]] < 0) {
                    anatomy_to_local_mx[i][max_vector[i] + 3] = true;
                    anatomy_to_local_mx[i + 3][max_vector[i]] = true;
                } else {
                    anatomy_to_local_mx[i][max_vector[i]] = true;
                    anatomy_to_local_mx[i + 3][max_vector[i] + 3] = true;
                }
            }
            boolean[] local_vector = new boolean[6];

            local_vector[0] = false;
            local_vector[1] = false;
            local_vector[2] = false;
            local_vector[3] = false;
            local_vector[4] = false;
            local_vector[5] = false;

            boolean[] anatomy_vector = new boolean[6];

            anatomy_vector[0] = right;
            anatomy_vector[1] = posterior;
            anatomy_vector[2] = caudal;
            anatomy_vector[3] = left;
            anatomy_vector[4] = anterior;
            anatomy_vector[5] = cranial;

            boolean sum;
            for (i = 0; i < 6; i++) {
                sum = false;
                for (j = 0; j < 6; j++) {
                    sum = sum || (anatomy_to_local_mx[i][j] & anatomy_vector[j]);
                    //	System.out.println("sum= "+sum+" + "+anatomy_to_local_mx[i][j]+"*"+anatomy_vector[j]);
                }
                local_vector[i] = sum;
                // System.out.println("local vector "+local_vector[i]);

            }
            position_sli_ph_rea[4] = local_vector[0] ? 1 : 0;
            position_sli_ph_rea[2] = local_vector[1] ? 1 : 0;
            position_sli_ph_rea[0] = local_vector[2] ? 1 : 0;
            position_sli_ph_rea[5] = local_vector[3] ? 1 : 0;
            position_sli_ph_rea[3] = local_vector[4] ? 1 : 0;
            position_sli_ph_rea[1] = local_vector[5] ? 1 : 0;

            // System.out.println("read+ "+position_sli_ph_rea[4]+" phase+ "+position_sli_ph_rea[2]+" slice+ "+position_sli_ph_rea[0]);
            // System.out.println("read- "+position_sli_ph_rea[5]+" phase- "+position_sli_ph_rea[3]+" slice- "+position_sli_ph_rea[1]);
        }
        boolean is_switch = getBoolean(SWITCH_READ_PHASE);
        boolean phase_pos_temp = position_sli_ph_rea[2] == 1;
        boolean phase_neg_temp = position_sli_ph_rea[3] == 1;
        boolean read_pos_temp = position_sli_ph_rea[4] == 1;
        boolean read_neg_temp = position_sli_ph_rea[5] == 1;
        if (is_switch) {
            position_sli_ph_rea[2] = read_pos_temp ? 1 : 0;
            position_sli_ph_rea[3] = read_neg_temp ? 1 : 0;
            position_sli_ph_rea[4] = phase_pos_temp ? 1 : 0;
            position_sli_ph_rea[5] = phase_neg_temp ? 1 : 0;
        }
        return position_sli_ph_rea;
    }

    private double getTx_bandwidth_factor(U tx_shape, U tx_bandwith_factor_param, U tx_bandwith_factor_param3d) {
        double tx_bandwidth_factor;

        List<Double> tx_bandwith_factor_table = getListDouble(tx_bandwith_factor_param);
        List<Double> tx_bandwith_factor_3D_table = getListDouble(tx_bandwith_factor_param3d);

        if (isMultiplanar) {
            if ("GAUSSIAN".equalsIgnoreCase(getText(tx_shape))) {
                tx_bandwidth_factor = tx_bandwith_factor_table.get(1);
            } else if ("SINC3".equalsIgnoreCase(getText(tx_shape))) {
                tx_bandwidth_factor = tx_bandwith_factor_table.get(2);
            } else if ("SINC5".equalsIgnoreCase(getText(tx_shape))) {
                tx_bandwidth_factor = tx_bandwith_factor_table.get(3);
            } else if ("RAMP".equalsIgnoreCase(getText(tx_shape))) {
                tx_bandwidth_factor = tx_bandwith_factor_table.get(3);
            } else if ("SLR_8_5152".equalsIgnoreCase(getText(tx_shape))) {
                tx_bandwidth_factor = tx_bandwith_factor_table.get(4);
            } else if ("SLR_4_2576".equalsIgnoreCase(getText(tx_shape))) {
                tx_bandwidth_factor = tx_bandwith_factor_table.get(5);
            } else {
                tx_bandwidth_factor = tx_bandwith_factor_table.get(0);
            }
        } else {
            if ("GAUSSIAN".equalsIgnoreCase(getText(tx_shape))) {
                tx_bandwidth_factor = tx_bandwith_factor_3D_table.get(1);
            } else if ("SINC3".equalsIgnoreCase(getText(tx_shape))) {
                tx_bandwidth_factor = tx_bandwith_factor_3D_table.get(2);
            } else if ("SINC5".equalsIgnoreCase(getText(tx_shape))) {
                tx_bandwidth_factor = tx_bandwith_factor_3D_table.get(3);
            } else if ("RAMP".equalsIgnoreCase(getText(tx_shape))) {
                tx_bandwidth_factor = tx_bandwith_factor_3D_table.get(3);
            } else if ("SLR_8_5152".equalsIgnoreCase(getText(tx_shape))) {
                tx_bandwidth_factor = tx_bandwith_factor_3D_table.get(4);
            } else if ("SLR_4_2576".equalsIgnoreCase(getText(tx_shape))) {
                tx_bandwidth_factor = tx_bandwith_factor_3D_table.get(5);
            } else {
                tx_bandwidth_factor = tx_bandwith_factor_3D_table.get(0);
            }
        }
        return tx_bandwidth_factor;
    }

    private double ceilToSubDecimal(double numberToBeRounded, double Order) {
        return Math.ceil(numberToBeRounded * Math.pow(10, Order)) / Math.pow(10, Order);
    }

    private double roundToDecimal(double numberToBeRounded, double order) {
        return Math.round(numberToBeRounded * Math.pow(10, order)) / Math.pow(10, order);
    }

    private void set(S tableName, double... values) {
        // uses Order.One because there are no tables in this dimension: compilation issue
        setSequenceTableValues(tableName, Order.FourLoop, values);
    }

    private Table setSequenceTableValues(S tableName, Order order, double... values) {
        Table table = getSequenceTable(tableName);
        table.clear();
        table.setOrder(order);
        table.setLocked(true);

        for (double value : values) {
            table.add(value);
        }
        return table;
    }

    private double getOff_center_distance_1D_2D_3D(int dim) {
        List<Double> image_orientation = getListDouble(IMAGE_ORIENTATION_SUBJECT);
        double[] direction_index = new double[9];
        direction_index[0] = image_orientation.get(0);
        direction_index[1] = image_orientation.get(1);
        direction_index[2] = image_orientation.get(2);
        direction_index[3] = image_orientation.get(3);
        direction_index[4] = image_orientation.get(4);
        direction_index[5] = image_orientation.get(5);
        direction_index[6] = direction_index[1] * direction_index[5] - direction_index[2] * direction_index[4];
        direction_index[7] = direction_index[2] * direction_index[3] - direction_index[0] * direction_index[5];
        direction_index[8] = direction_index[0] * direction_index[4] - direction_index[1] * direction_index[3];

        double norm_vector_read = Math.sqrt(Math.pow(direction_index[0], 2) + Math.pow(direction_index[1], 2) + Math.pow(direction_index[2], 2));
        double norm_vector_phase = Math.sqrt(Math.pow(direction_index[3], 2) + Math.pow(direction_index[4], 2) + Math.pow(direction_index[5], 2));
        double norm_vector_slice = Math.sqrt(Math.pow(direction_index[6], 2) + Math.pow(direction_index[7], 2) + Math.pow(direction_index[8], 2));

        //Offset according to animal position
        double off_center_distance_Z = getDouble(OFF_CENTER_FIELD_OF_VIEW_Z);
        double off_center_distance_Y = getDouble(OFF_CENTER_FIELD_OF_VIEW_Y);
        double off_center_distance_X = getDouble(OFF_CENTER_FIELD_OF_VIEW_X);

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


    private double getOff_center_distance_X_Y_Z(int dim, double off_center_distance_1D,
                                                double off_center_distance_2D, double off_center_distance_3D) {
        List<Double> image_orientation = getListDouble(IMAGE_ORIENTATION_SUBJECT);
        double[] direction_index = new double[9];
        direction_index[0] = image_orientation.get(0);
        direction_index[1] = image_orientation.get(1);
        direction_index[2] = image_orientation.get(2);
        direction_index[3] = image_orientation.get(3);
        direction_index[4] = image_orientation.get(4);
        direction_index[5] = image_orientation.get(5);
        direction_index[6] = direction_index[1] * direction_index[5] - direction_index[2] * direction_index[4];
        direction_index[7] = direction_index[2] * direction_index[3] - direction_index[0] * direction_index[5];
        direction_index[8] = direction_index[0] * direction_index[4] - direction_index[1] * direction_index[3];

        double norm_vector_read = Math.sqrt(Math.pow(direction_index[0], 2) + Math.pow(direction_index[1], 2) + Math.pow(direction_index[2], 2));
        double norm_vector_phase = Math.sqrt(Math.pow(direction_index[3], 2) + Math.pow(direction_index[4], 2) + Math.pow(direction_index[5], 2));
        double norm_vector_slice = Math.sqrt(Math.pow(direction_index[6], 2) + Math.pow(direction_index[7], 2) + Math.pow(direction_index[8], 2));

        //Offset according to READ PHASE and SLICE
        double off_center_distance;
        switch (dim) {
            case 1:
                off_center_distance = off_center_distance_1D * direction_index[0] / norm_vector_read + off_center_distance_2D * direction_index[3] / norm_vector_phase + off_center_distance_3D * direction_index[6] / norm_vector_slice;
                break;
            case 2:
                off_center_distance = off_center_distance_1D * direction_index[1] / norm_vector_read + off_center_distance_2D * direction_index[4] / norm_vector_phase + off_center_distance_3D * direction_index[7] / norm_vector_slice;
                break;
            case 3:
                off_center_distance = off_center_distance_1D * direction_index[2] / norm_vector_read + off_center_distance_2D * direction_index[5] / norm_vector_phase + off_center_distance_3D * direction_index[8] / norm_vector_slice;
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

    public List<RoleEnum> getPluginAccess() {
        return Collections.singletonList(RoleEnum.User);
    }

    //<editor-fold defaultstate="collapsed" desc="Generated Code (RS2D)">
    protected void addUserParams() {
        addMissingUserParams(U.values());
    }

    public String getName() {
        return "SPIN_ECHO";
    }

    public String getVersion() {
        return "master";
    }
    //</editor-fold>
}