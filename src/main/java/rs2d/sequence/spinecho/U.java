//-- generated code, will be overwritten at each recompilation

package rs2d.sequence.spinecho;

import rs2d.spinlab.tools.param.*;
import rs2d.spinlab.tools.table.*;
import rs2d.spinlab.sequenceGenerator.GeneratorParamEnum;

import java.util.List;
import static java.util.Arrays.asList;

public enum U implements GeneratorParamEnum {
    ACCU_DIM("ACCU_DIM") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("ACCU_DIM");
            param.setDisplayedName("ACCU_DIM");
            param.setDescription("Dimension on which averaging is performed by the Cameleon");
            param.setLocked(true);
            param.setGroup(EnumGroup.Scan);
            param.setCategory(Category.Acquisition);
            param.setUuid("92558d05-b46d-47f1-a5e1-43d24aa01a53");
            param.setNumberEnum(NumberEnum.Integer);
            param.setMinValue(0);
            param.setMaxValue(3);
            param.setValue(1);
            param.setDefaultValue(1);
            return param;
        }
    },

    ACQUISITION_MATRIX_DIMENSION_1D("ACQUISITION_MATRIX_DIMENSION_1D") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("ACQUISITION_MATRIX_DIMENSION_1D");
            param.setDisplayedName("Acquisition Matrix 1D");
            param.setDescription("Info: Size of the initial dataset (raw data) in the first dimension");
            param.setGroup(EnumGroup.Scan);
            param.setCategory(Category.Acquisition);
            param.setUuid("d418dd0c-8ed3-462d-9139-dab73889eed7");
            param.setNumberEnum(NumberEnum.Scan);
            param.setMinValue(0);
            param.setMaxValue(65536);
            param.setValue(256);
            param.setDefaultValue(128);
            return param;
        }
    },

    ACQUISITION_MATRIX_DIMENSION_2D("ACQUISITION_MATRIX_DIMENSION_2D") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("ACQUISITION_MATRIX_DIMENSION_2D");
            param.setDisplayedName("Acquisition Matrix 2D");
            param.setDescription("Info: Size of the initial dataset (raw data) in the second dimension");
            param.setGroup(EnumGroup.Scan);
            param.setCategory(Category.Acquisition);
            param.setUuid("cf0dfa27-fe29-4ca7-b64e-815d9c207dec");
            param.setNumberEnum(NumberEnum.Scan);
            param.setMinValue(0);
            param.setMaxValue(65536);
            param.setValue(192);
            param.setDefaultValue(128);
            return param;
        }
    },

    ACQUISITION_MATRIX_DIMENSION_3D("ACQUISITION_MATRIX_DIMENSION_3D") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("ACQUISITION_MATRIX_DIMENSION_3D");
            param.setDisplayedName("Acquisition Matrix 3D");
            param.setDescription("Info: Size of the initial dataset (raw data) in the third dimension");
            param.setGroup(EnumGroup.Scan);
            param.setCategory(Category.Acquisition);
            param.setUuid("a6af4bab-f892-46a9-9fb2-b7b89a4e5630");
            param.setNumberEnum(NumberEnum.Scan);
            param.setMinValue(0);
            param.setMaxValue(65536);
            param.setValue(16);
            param.setDefaultValue(1);
            return param;
        }
    },

    ACQUISITION_MATRIX_DIMENSION_4D("ACQUISITION_MATRIX_DIMENSION_4D") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("ACQUISITION_MATRIX_DIMENSION_4D");
            param.setDisplayedName("Acquisition Matrix 4D");
            param.setDescription("Info: Size of the initial dataset (raw data) in the fourth dimension");
            param.setGroup(EnumGroup.Scan);
            param.setCategory(Category.Acquisition);
            param.setUuid("00451c15-5652-4f8f-88f4-78c3a71c7e53");
            param.setNumberEnum(NumberEnum.Scan);
            param.setMinValue(0);
            param.setMaxValue(65536);
            param.setValue(1);
            param.setDefaultValue(1);
            return param;
        }
    },

    ACQUISITION_MODE("ACQUISITION_MODE") {
        public Param build() {
            ListTextParam param = new ListTextParam();
            param.setName("ACQUISITION_MODE");
            param.setDisplayedName("ACQUISITION_MODE");
            param.setDescription("Info: ACQUISITION_MODE and DATA_REPRESENTATION are filled according to the phase modulation. They ensure that data will be correctly processed.");
            param.setLocked(true);
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Acquisition);
            param.setUuid("9ac625c0-0c3d-4139-b81c-9f83cc3b5cbf");
            param.setValue(asList("COMPLEX", "REAL", "REAL", "REAL"));
            param.setDefaultValue(asList("COMPLEX", "REAL", "REAL", "REAL"));
            return param;
        }
    },

    ACQUISITION_TIME_OFFSET("ACQUISITION_TIME_OFFSET") {
        public Param build() {
            ListNumberParam param = new ListNumberParam();
            param.setName("ACQUISITION_TIME_OFFSET");
            param.setDisplayedName("Acquisition Time Offset");
            param.setDescription("Relative acquisition start times in Dynamic or MultiSeries scans");
            param.setGroup(EnumGroup.Delay);
            param.setCategory(Category.Acquisition);
            param.setUuid("a7b7ad1a-753f-4a2e-9616-a071181eba83");
            param.setMinValue(0.0);
            param.setMaxValue(1.0E9);
            param.setNumberEnum(NumberEnum.Time);
            param.setValue(asListNumber(0.0));
            param.setDefaultValue(asListNumber(0.0));
            return param;
        }
    },

    ACQUISITION_TIME_PER_SCAN("ACQUISITION_TIME_PER_SCAN") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("ACQUISITION_TIME_PER_SCAN");
            param.setDisplayedName("Observation Time");
            param.setDescription("Info: Time during which the signal is sampled by the ADC");
            param.setGroup(EnumGroup.Reception);
            param.setCategory(Category.Acquisition);
            param.setUuid("10919155-05d8-4b40-a93b-4007d45a744e");
            param.setNumberEnum(NumberEnum.Time);
            param.setMinValue(0.0);
            param.setMaxValue(1.0E9);
            param.setValue(0.0056885248);
            param.setDefaultValue(1.0);
            return param;
        }
    },

    BASE_FREQ_1("BASE_FREQ_1") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("BASE_FREQ_1");
            param.setDisplayedName("Base Frequency 1");
            param.setDescription("Info: Frequency of the observed nucleus");
            param.setLocked(true);
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setUuid("ab181ac3-550f-4ab3-9cac-d6488d910098");
            param.setNumberEnum(NumberEnum.Frequency);
            param.setMinValue(0.0);
            param.setMaxValue(3.0E9);
            param.setValue(6.3806597364338815E7);
            param.setDefaultValue(1.27552944E8);
            return param;
        }
    },

    BASE_FREQ_2("BASE_FREQ_2") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("BASE_FREQ_2");
            param.setDisplayedName("Base Frequency 2");
            param.setDescription("Info: Base frequency of the second channel");
            param.setLocked(true);
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setUuid("29409764-8fb3-43ba-b6ae-787f858501f5");
            param.setNumberEnum(NumberEnum.Frequency);
            param.setMinValue(0.0);
            param.setMaxValue(3.0E9);
            param.setValue(1.28E8);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    BASE_FREQ_3("BASE_FREQ_3") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("BASE_FREQ_3");
            param.setDisplayedName("Base Frequency 3");
            param.setDescription("Info: Base frequency of the third channel");
            param.setLocked(true);
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setUuid("5cd19900-6a91-41d0-803f-487664302b02");
            param.setNumberEnum(NumberEnum.Frequency);
            param.setMinValue(0.0);
            param.setMaxValue(3.0E9);
            param.setValue(1.28E8);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    BASE_FREQ_4("BASE_FREQ_4") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("BASE_FREQ_4");
            param.setDisplayedName("Base Frequency 4");
            param.setDescription("Info: Base frequency of the fourth channel");
            param.setLocked(true);
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setUuid("abf3e614-aeb2-4d0c-8a7d-d2cce53cf69b");
            param.setNumberEnum(NumberEnum.Frequency);
            param.setMinValue(0.0);
            param.setMaxValue(3.0E9);
            param.setValue(1.28E8);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    DATA_REPRESENTATION("DATA_REPRESENTATION") {
        public Param build() {
            ListTextParam param = new ListTextParam();
            param.setName("DATA_REPRESENTATION");
            param.setDisplayedName("DATA_REPRESENTATION");
            param.setDescription("Info: ACQUISITION_MODE and DATA_REPRESENTATION are filled according to the phase modulation. They ensure that data will be correctly processed.");
            param.setLocked(true);
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Miscellaneous);
            param.setUuid("b4a683a4-1e48-4959-a357-196bd52622de");
            param.setValue(asList("COMPLEX", "REAL", "REAL", "REAL"));
            param.setDefaultValue(asList("COMPLEX", "REAL", "REAL", "REAL"));
            return param;
        }
    },

    DIGITAL_FILTER_REMOVED("DIGITAL_FILTER_REMOVED") {
        public Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("DIGITAL_FILTER_REMOVED");
            param.setDisplayedName("DIGITAL_FILTER_REMOVED");
            param.setDescription("Enable to activate the data shift");
            param.setLockedToDefault(true);
            param.setGroup(EnumGroup.Reception);
            param.setCategory(Category.Acquisition);
            param.setUuid("54e2a900-302b-49c8-a8f8-b61a84a58cea");
            param.setValue(true);
            param.setDefaultValue(true);
            return param;
        }
    },

    DIGITAL_FILTER_SHIFT("DIGITAL_FILTER_SHIFT") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("DIGITAL_FILTER_SHIFT");
            param.setDisplayedName("DIGITAL_FILTER_SHIFT");
            param.setDescription("Data shift due to the digital filter");
            param.setLockedToDefault(true);
            param.setGroup(EnumGroup.Reception);
            param.setCategory(Category.Acquisition);
            param.setUuid("cdc5b826-3bc8-487d-84a2-7128dfa96d2e");
            param.setNumberEnum(NumberEnum.Integer);
            param.setMinValue(-2147483648);
            param.setMaxValue(2147483647);
            param.setValue(19);
            param.setDefaultValue(19);
            return param;
        }
    },

    DIXON_3PTS("DIXON_3PTS") {
        public Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("DIXON_3PTS");
            param.setDisplayedName("Dixon 3pts");
            param.setDescription("Enable 3-points Dixon measurement");
            param.setCategory(Category.Acquisition);
            param.setUuid("d92f05c9-9d69-44aa-9cbe-b33d687c6431");
            param.setValue(false);
            param.setDefaultValue(false);
            return param;
        }
    },

    DIXON_FAT_PERIODE("DIXON_FAT_PERIODE") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("DIXON_FAT_PERIODE");
            param.setDisplayedName("Fat-Water Periode");
            param.setDescription("Periode of the phase cycling between fat and water (3.5ppm i.e. 2.2ms at 3T, 4.4 at 1.5T)");
            param.setCategory(Category.Acquisition);
            param.setUuid("6e6b0a41-aa80-435a-80d6-2e47c0fbb2fd");
            param.setNumberEnum(NumberEnum.Time);
            param.setMinValue(0.0);
            param.setMaxValue(1.0E9);
            param.setValue(0.004477817302854172);
            param.setDefaultValue(0.0035499999999999998);
            return param;
        }
    },

    DUMMY_SCAN("DUMMY_SCAN") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("DUMMY_SCAN");
            param.setDisplayedName("Dummy Scans");
            param.setDescription("Number of dummy cycles used to reach steady-state");
            param.setGroup(EnumGroup.Scan);
            param.setCategory(Category.Acquisition);
            param.setUuid("0c3a2ea9-b44c-44cb-9a2c-fd11b26ba40f");
            param.setNumberEnum(NumberEnum.Scan);
            param.setMinValue(0);
            param.setMaxValue(65536);
            param.setValue(2);
            param.setDefaultValue(128);
            return param;
        }
    },

    DWELL_TIME("DWELL_TIME") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("DWELL_TIME");
            param.setDisplayedName("DW");
            param.setDescription("Reception dwell time");
            param.setGroup(EnumGroup.Reception);
            param.setCategory(Category.Acquisition);
            param.setUuid("39501b8b-d5f4-4d07-80bf-ae2b42ebc720");
            param.setNumberEnum(NumberEnum.Time);
            param.setMinValue(0.0);
            param.setMaxValue(1.0E9);
            param.setValue(0.0);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    DYNAMIC_MIN_TIME("DYNAMIC_MIN_TIME") {
        public Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("DYNAMIC_MIN_TIME");
            param.setDisplayedName("Dyn - Min Time");
            param.setDescription("Enable to use the minimum time allowed to perform dynamic acquisition ");
            param.setGroup(EnumGroup.Delay);
            param.setCategory(Category.Acquisition);
            param.setUuid("a08bbe5f-ed1d-4fa4-a7e0-3678a8b93f05");
            param.setValue(true);
            param.setDefaultValue(false);
            return param;
        }
    },

    DYNAMIC_SEQUENCE("DYNAMIC_SEQUENCE") {
        public Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("DYNAMIC_SEQUENCE");
            param.setDisplayedName("Dynamic");
            param.setDescription("Enable dynamic acquisition measurements");
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Acquisition);
            param.setUuid("5819c06d-f9a7-457e-b252-4e31eced6c91");
            param.setValue(false);
            param.setDefaultValue(false);
            return param;
        }
    },

    DYN_NUMBER_OF_ACQUISITION("DYN_NUMBER_OF_ACQUISITION") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("DYN_NUMBER_OF_ACQUISITION");
            param.setDisplayedName("Dyn - No. Frames");
            param.setDescription("Number of frames acquired in dynamic sequence");
            param.setGroup(EnumGroup.Scan);
            param.setCategory(Category.Acquisition);
            param.setUuid("f4f11dee-e721-47bf-b266-09d4a7a97001");
            param.setNumberEnum(NumberEnum.Scan);
            param.setMinValue(1);
            param.setMaxValue(65536);
            param.setValue(5);
            param.setDefaultValue(1);
            return param;
        }
    },

    DYN_TIME_BTW_FRAMES("DYN_TIME_BTW_FRAMES") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("DYN_TIME_BTW_FRAMES");
            param.setDisplayedName("Dyn - Time btw Frames");
            param.setDescription("Time delay between frames in dynamic sequence");
            param.setGroup(EnumGroup.Delay);
            param.setCategory(Category.Acquisition);
            param.setUuid("b1d071a8-c694-4afd-ab5a-39e1ea6fc8b0");
            param.setNumberEnum(NumberEnum.Time);
            param.setMinValue(0.0);
            param.setMaxValue(1.0E9);
            param.setValue(0.001);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    ECHO_EFFECTIVE("ECHO_EFFECTIVE") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("ECHO_EFFECTIVE");
            param.setDisplayedName("Echo k0 Position");
            param.setDescription("ID of the echo used for the central line encoding");
            param.setGroup(EnumGroup.Scan);
            param.setCategory(Category.Acquisition);
            param.setUuid("9d373171-2609-4c0d-9f61-c7309e28b386");
            param.setNumberEnum(NumberEnum.Integer);
            param.setMinValue(1);
            param.setMaxValue(2147483647);
            param.setValue(1);
            param.setDefaultValue(1);
            return param;
        }
    },

    ECHO_SPACING("ECHO_SPACING") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("ECHO_SPACING");
            param.setDisplayedName("Echo Spacing");
            param.setDescription("Delay between two consecutive echoes");
            param.setGroup(EnumGroup.Delay);
            param.setCategory(Category.Acquisition);
            param.setUuid("3c181002-7b56-47b9-8a59-c1c6b5b577b0");
            param.setNumberEnum(NumberEnum.Time);
            param.setMinValue(0.0);
            param.setMaxValue(100.0);
            param.setValue(0.01094);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    ECHO_TIME("ECHO_TIME") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("ECHO_TIME");
            param.setDisplayedName("TE");
            param.setDescription("Echo Time");
            param.setGroup(EnumGroup.Delay);
            param.setCategory(Category.Acquisition);
            param.setUuid("1fcfdf7f-f41c-4b51-9d6c-6bd347f074b2");
            param.setNumberEnum(NumberEnum.Time);
            param.setMinValue(0.0);
            param.setMaxValue(1.0E9);
            param.setValue(0.01094);
            param.setDefaultValue(0.005);
            return param;
        }
    },

    ECHO_TIME_EFFECTIVE("ECHO_TIME_EFFECTIVE") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("ECHO_TIME_EFFECTIVE");
            param.setDisplayedName("TE Eff");
            param.setDescription("Info: Effective echo time in Fast Spin Echo sequence");
            param.setGroup(EnumGroup.Delay);
            param.setCategory(Category.Acquisition);
            param.setUuid("60a9081d-2188-4d4a-bc43-f55e692c9bba");
            param.setNumberEnum(NumberEnum.Time);
            param.setMinValue(0.001);
            param.setMaxValue(10.0);
            param.setValue(0.01094);
            param.setDefaultValue(0.01);
            return param;
        }
    },

    ECHO_TRAIN_LENGTH("ECHO_TRAIN_LENGTH") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("ECHO_TRAIN_LENGTH");
            param.setDisplayedName("ETL");
            param.setDescription("Echo train lentgth");
            param.setGroup(EnumGroup.Delay);
            param.setCategory(Category.Acquisition);
            param.setUuid("1d38f9a3-b8b3-43e8-b46e-3818344e6003");
            param.setNumberEnum(NumberEnum.Scan);
            param.setMinValue(0);
            param.setMaxValue(65536);
            param.setValue(12);
            param.setDefaultValue(1);
            return param;
        }
    },

    FATSAT("FATSAT") {
        public Param build() {
            TextParam param = new TextParam();
            param.setName("FATSAT");
            param.setDisplayedName("Fat Saturation Pulse");
            param.setDescription("Select the fat saturation pulse type: \"Disable\" no FatSat pulse ; \"Spectrally Selective\" 90° spectrally selective pulse ; \"Binomial\" (45°,-45°) composite non-selective pulse");
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setUuid("6ee35242-3f1f-4a51-906d-78186a7654ad");
            param.setValue("Disable");
            param.setDefaultValue("Disable");
            param.setSuggestedValues(asList("Disable", "Spectrally Selective", "Binomial"));
            param.setRestrictedToSuggested(true);
            return param;
        }
    },

    FATSAT_BANDWIDTH("FATSAT_BANDWIDTH") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("FATSAT_BANDWIDTH");
            param.setDisplayedName("FatSat - TX BW");
            param.setDescription("Fat-Sat pulse Bandwidth (input for spectrally selective pulse)");
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setUuid("f9437233-8fed-4bc3-8cc6-f1bef7174b57");
            param.setNumberEnum(NumberEnum.FrequencyOffset);
            param.setMinValue(0.0);
            param.setMaxValue(2000.0);
            param.setValue(250.0);
            param.setDefaultValue(224.0);
            return param;
        }
    },

    FATSAT_FLIP_ANGLE("FATSAT_FLIP_ANGLE") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("FATSAT_FLIP_ANGLE");
            param.setDisplayedName("FatSat - TX Flip Angle");
            param.setDescription("Info: Flip angle of Fat-Sat pulse (spectrally-selective: 90°, binomial: 45°)");
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setUuid("ea3f43d7-9bb6-4790-8ec4-050dc9b7d188");
            param.setNumberEnum(NumberEnum.Angle);
            param.setMinValue(0.0);
            param.setMaxValue(360.0);
            param.setValue(0.0);
            param.setDefaultValue(90.0);
            return param;
        }
    },

    FATSAT_GRAD_APP_TIME("FATSAT_GRAD_APP_TIME") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("FATSAT_GRAD_APP_TIME");
            param.setDisplayedName("FatSat - Gradient Duration");
            param.setDescription("Top time of Fat-Sat spoiler gradient");
            param.setGroup(EnumGroup.Delay);
            param.setCategory(Category.Acquisition);
            param.setUuid("ea88c016-6bad-49f4-b7f3-abf471bde5d2");
            param.setNumberEnum(NumberEnum.Time);
            param.setMinValue(0.0);
            param.setMaxValue(1.0E9);
            param.setValue(1.0E-5);
            param.setDefaultValue(0.001);
            return param;
        }
    },

    FATSAT_OFFSET_FREQ("FATSAT_OFFSET_FREQ") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("FATSAT_OFFSET_FREQ");
            param.setDisplayedName("Fat-water Offset");
            param.setDescription("Separation between fat and water frequencies (at 3T ~ 420-448Hz ; at 1.5T ~ 210-224Hz)  : Default value = water frequency * 3.5 ");
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setUuid("76c8f75f-10e5-483c-8b59-d0fc0699cf9e");
            param.setNumberEnum(NumberEnum.FrequencyOffset);
            param.setMinValue(-10000.0);
            param.setMaxValue(10000.0);
            param.setValue(-440.0);
            param.setDefaultValue(-224.0);
            return param;
        }
    },

    FATSAT_TX_AMP("FATSAT_TX_AMP") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("FATSAT_TX_AMP");
            param.setDisplayedName("FatSat - TX Amplitude");
            param.setDescription("Fat-Sat Pulse amplitude");
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setUuid("04baafd6-a721-4f6e-b6b5-78eb1d697b66");
            param.setNumberEnum(NumberEnum.TxAmp);
            param.setMinValue(0.0);
            param.setMaxValue(100.0);
            param.setValue(0.0);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    FATSAT_TX_LENGTH("FATSAT_TX_LENGTH") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("FATSAT_TX_LENGTH");
            param.setDisplayedName("FatSat - TX Length");
            param.setDescription("Fat-Sat Pulse duration (input for binomial pulse)");
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setUuid("d48514af-8440-447e-be74-95ab71d2e566");
            param.setNumberEnum(NumberEnum.Time);
            param.setMinValue(0.0);
            param.setMaxValue(1.0E9);
            param.setValue(0.0058);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    FATSAT_TX_SHAPE("FATSAT_TX_SHAPE") {
        public Param build() {
            TextParam param = new TextParam();
            param.setName("FATSAT_TX_SHAPE");
            param.setDisplayedName("FatSat - TX Shape");
            param.setDescription("Fat-Sat Pulse shape");
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setUuid("1896f331-21ba-4e11-bb92-10444b094033");
            param.setValue("GAUSSIAN");
            param.setDefaultValue("HARD");
            param.setSuggestedValues(asList("HARD", "GAUSSIAN", "SINC3", "SINC5", "SLR_8_5152", "SLR_4_2576"));
            param.setRestrictedToSuggested(true);
            return param;
        }
    },

    FIELD_OF_VIEW("FIELD_OF_VIEW") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("FIELD_OF_VIEW");
            param.setDisplayedName("FOV Read");
            param.setDescription("Field Of View along the frequency encoding direction");
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setUuid("1cdfcfe1-b486-4fcd-8d1a-4022edc60fcc");
            param.setNumberEnum(NumberEnum.Length);
            param.setMinValue(0.001);
            param.setMaxValue(1.7976931348623157E308);
            param.setValue(0.045);
            param.setDefaultValue(0.6);
            return param;
        }
    },

    FIELD_OF_VIEW_3D("FIELD_OF_VIEW_3D") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("FIELD_OF_VIEW_3D");
            param.setDisplayedName("FOV Slice/3D");
            param.setDescription("Info: Field Of View coverage along the direction orthogonal to the phase and frequency encoding directions");
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setUuid("130d0e68-3133-48c4-a9f6-6f48dceed9b4");
            param.setNumberEnum(NumberEnum.Length);
            param.setMinValue(0.0);
            param.setMaxValue(1.0);
            param.setValue(0.014299999999999998);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    FIELD_OF_VIEW_PHASE("FIELD_OF_VIEW_PHASE") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("FIELD_OF_VIEW_PHASE");
            param.setDisplayedName("FOV Phase");
            param.setDescription("Field Of View along the phase encoding direction");
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setUuid("76f57b05-4c79-488a-999a-c7de21ab7d21");
            param.setNumberEnum(NumberEnum.Length);
            param.setMinValue(0.01);
            param.setMaxValue(10.0);
            param.setValue(0.045);
            param.setDefaultValue(0.1);
            return param;
        }
    },

    FLIP_ANGLE("FLIP_ANGLE") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("FLIP_ANGLE");
            param.setDisplayedName("Flip Angle");
            param.setDescription("Excitation flip angle (FA)");
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setUuid("40e1d82f-5c33-4ef8-9922-f29507b36b46");
            param.setNumberEnum(NumberEnum.Angle);
            param.setMinValue(-360.0);
            param.setMaxValue(360.0);
            param.setValue(90.0);
            param.setDefaultValue(90.0);
            return param;
        }
    },

    FOV_DOUBLED("FOV_DOUBLED") {
        public Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("FOV_DOUBLED");
            param.setDisplayedName("Frequency Oversampling");
            param.setDescription("Enable double the effective FOV and BW to avoid aliasing ");
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setUuid("5de6928b-d433-4a4c-b8a6-48dee3c9c230");
            param.setValue(false);
            param.setDefaultValue(false);
            return param;
        }
    },

    FOV_EFF("FOV_EFF") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("FOV_EFF");
            param.setDisplayedName("FOV Eff");
            param.setDescription("Info: Return the acquired FOV length");
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setUuid("f6e23c0c-eaed-411b-9301-7b2474806e4f");
            param.setNumberEnum(NumberEnum.Length);
            param.setMinValue(0.0);
            param.setMaxValue(1.7976931348623156E305);
            param.setValue(0.045);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    FOV_SQUARE("FOV_SQUARE") {
        public Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("FOV_SQUARE");
            param.setDisplayedName("Square FOV");
            param.setDescription("Enforce a square FOV (Phase FOV ratio = 100%)");
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setUuid("f10fa704-6c7d-46da-a1a9-8da05fffac82");
            param.setValue(false);
            param.setDefaultValue(false);
            return param;
        }
    },

    GRADIENT_AMP_CRUSHER("GRADIENT_AMP_CRUSHER") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("GRADIENT_AMP_CRUSHER");
            param.setDisplayedName("Gradient Amplitude Crusher");
            param.setDescription("Amplitude of the crusher gradient");
            param.setGroup(EnumGroup.Gradient);
            param.setCategory(Category.Acquisition);
            param.setUuid("6d35e878-0131-4c8b-a127-983833152648");
            param.setNumberEnum(NumberEnum.PERCENT);
            param.setMinValue(0.0);
            param.setMaxValue(100.0);
            param.setValue(30.0);
            param.setDefaultValue(8.0);
            return param;
        }
    },

    GRADIENT_AMP_CRUSHER_IR("GRADIENT_AMP_CRUSHER_IR") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("GRADIENT_AMP_CRUSHER_IR");
            param.setDisplayedName("Gradient Amplitude Crusher IR");
            param.setDescription("Amplitude of the crusher gradient after the Inversion pulse");
            param.setGroup(EnumGroup.Gradient);
            param.setCategory(Category.Acquisition);
            param.setUuid("639751c0-3bdb-4e2a-ac03-13f4267e890e");
            param.setNumberEnum(NumberEnum.PERCENT);
            param.setMinValue(0.0);
            param.setMaxValue(100.0);
            param.setValue(12.0);
            param.setDefaultValue(8.0);
            return param;
        }
    },

    GRADIENT_AMP_SPOILER("GRADIENT_AMP_SPOILER") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("GRADIENT_AMP_SPOILER");
            param.setDisplayedName("Gradient Amplitude Spoiler");
            param.setDescription("Amplitude of the spoiler gradient");
            param.setGroup(EnumGroup.Gradient);
            param.setCategory(Category.Acquisition);
            param.setUuid("62de2b3d-bba0-432d-bc5e-e740bf08b61e");
            param.setNumberEnum(NumberEnum.PERCENT);
            param.setMinValue(0.0);
            param.setMaxValue(100.0);
            param.setValue(20.0);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    GRADIENT_AREA_CRUSHER("GRADIENT_AREA_CRUSHER") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("GRADIENT_AREA_CRUSHER");
            param.setDisplayedName("Gradient Area Crusher");
            param.setDescription("Info: Crusher gradient area");
            param.setGroup(EnumGroup.Gradient);
            param.setCategory(Category.Acquisition);
            param.setUuid("f44414e7-33e7-4a17-8408-40dfdd4ed236");
            param.setNumberEnum(NumberEnum.Double);
            param.setMinValue(-1.7976931348623157E308);
            param.setMaxValue(1.7976931348623157E308);
            param.setValue(6.54802174422E-5);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    GRADIENT_AREA_CRUSHER_PI("GRADIENT_AREA_CRUSHER_PI") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("GRADIENT_AREA_CRUSHER_PI");
            param.setDisplayedName("Gradient Area Crusher Pi");
            param.setDescription("Info: Number of pi dephasing by the crusher");
            param.setGroup(EnumGroup.Gradient);
            param.setCategory(Category.Acquisition);
            param.setUuid("f0a7df1f-97c3-4410-97f0-4166b0e2c91c");
            param.setNumberEnum(NumberEnum.Double);
            param.setMinValue(-1.7976931348623157E308);
            param.setMaxValue(1.7976931348623157E308);
            param.setValue(2.230386041199391);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    GRADIENT_CRUSHER_END_TOP_TIME("GRADIENT_CRUSHER_END_TOP_TIME") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("GRADIENT_CRUSHER_END_TOP_TIME");
            param.setDisplayedName("Gradient Duration Crusher End");
            param.setDescription("Top time of the crusher gradient played at the end of scan");
            param.setGroup(EnumGroup.Delay);
            param.setCategory(Category.Acquisition);
            param.setUuid("8ab34d59-3bff-432d-bb01-7a0cef4b3dc0");
            param.setNumberEnum(NumberEnum.Time);
            param.setMinValue(0.0);
            param.setMaxValue(1.0E9);
            param.setValue(5.0E-4);
            param.setDefaultValue(3.0E-4);
            return param;
        }
    },

    GRADIENT_CRUSHER_IR_TOP_TIME("GRADIENT_CRUSHER_IR_TOP_TIME") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("GRADIENT_CRUSHER_IR_TOP_TIME");
            param.setDisplayedName("Gradient Duration Crusher IR");
            param.setDescription("Top time of the crusher gradient played after Inversion pulse");
            param.setGroup(EnumGroup.Delay);
            param.setCategory(Category.Acquisition);
            param.setUuid("ec8fae37-5a47-4662-b6ef-d83720c6c94a");
            param.setNumberEnum(NumberEnum.Time);
            param.setMinValue(0.0);
            param.setMaxValue(1.0E9);
            param.setValue(3.9999999999999996E-4);
            param.setDefaultValue(3.9999999999999996E-4);
            return param;
        }
    },

    GRADIENT_CRUSHER_READ_TOP_TIME("GRADIENT_CRUSHER_READ_TOP_TIME") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("GRADIENT_CRUSHER_READ_TOP_TIME");
            param.setDisplayedName("Gradient Duration Crusher Read");
            param.setDescription("Top time of the crusher gradient associated to the readout gradient ");
            param.setGroup(EnumGroup.Delay);
            param.setCategory(Category.Acquisition);
            param.setUuid("27dac4a0-5068-40d1-b845-b5e900742cc5");
            param.setNumberEnum(NumberEnum.Time);
            param.setMinValue(4.9999999999999996E-6);
            param.setMaxValue(1.0E9);
            param.setValue(4.9999999999999996E-6);
            param.setDefaultValue(4.9999999999999996E-6);
            return param;
        }
    },

    GRADIENT_CRUSHER_TOP_TIME("GRADIENT_CRUSHER_TOP_TIME") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("GRADIENT_CRUSHER_TOP_TIME");
            param.setDisplayedName("Gradient Duration Crushers");
            param.setDescription("Top time of the crusher gradients");
            param.setGroup(EnumGroup.Delay);
            param.setCategory(Category.Acquisition);
            param.setUuid("12f304a1-bbc8-4cda-b4d6-0b4942a358d2");
            param.setNumberEnum(NumberEnum.Time);
            param.setMinValue(0.0);
            param.setMaxValue(1.0E9);
            param.setValue(4.0E-4);
            param.setDefaultValue(3.9999999999999996E-4);
            return param;
        }
    },

    GRADIENT_ENABLE_CRUSHER_IR("GRADIENT_ENABLE_CRUSHER_IR") {
        public Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("GRADIENT_ENABLE_CRUSHER_IR");
            param.setDisplayedName("Gradient Crusher IR");
            param.setDescription("Enable IR gradient crusher");
            param.setGroup(EnumGroup.Gradient);
            param.setCategory(Category.Acquisition);
            param.setUuid("3263a29c-5426-4336-8c21-e80212198c45");
            param.setValue(true);
            param.setDefaultValue(true);
            return param;
        }
    },

    GRADIENT_ENABLE_PHASE("GRADIENT_ENABLE_PHASE") {
        public Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("GRADIENT_ENABLE_PHASE");
            param.setDisplayedName("Gradient Phase-2D");
            param.setDescription("Enable 2D phase encoding gradient");
            param.setLocked(true);
            param.setGroup(EnumGroup.Gradient);
            param.setCategory(Category.Acquisition);
            param.setUuid("7339d4f7-2142-4456-8383-eadab8618e40");
            param.setValue(true);
            param.setDefaultValue(true);
            return param;
        }
    },

    GRADIENT_ENABLE_PHASE_3D("GRADIENT_ENABLE_PHASE_3D") {
        public Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("GRADIENT_ENABLE_PHASE_3D");
            param.setDisplayedName("Gradient Phase-3D");
            param.setDescription("Enable 3D phase encoding gradient");
            param.setLocked(true);
            param.setGroup(EnumGroup.Gradient);
            param.setCategory(Category.Acquisition);
            param.setUuid("69478135-9c09-4da0-9752-ed269fe9c8b9");
            param.setValue(false);
            param.setDefaultValue(true);
            return param;
        }
    },

    GRADIENT_ENABLE_READ("GRADIENT_ENABLE_READ") {
        public Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("GRADIENT_ENABLE_READ");
            param.setDisplayedName("Gradient Read");
            param.setDescription("Enable frequency encoding gradient");
            param.setLocked(true);
            param.setGroup(EnumGroup.Gradient);
            param.setCategory(Category.Acquisition);
            param.setUuid("71614496-3c59-4ee2-8b4b-52b34b096c54");
            param.setValue(true);
            param.setDefaultValue(true);
            return param;
        }
    },

    GRADIENT_ENABLE_SLICE("GRADIENT_ENABLE_SLICE") {
        public Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("GRADIENT_ENABLE_SLICE");
            param.setDisplayedName("Gradient Slice");
            param.setDescription("Enable slice encoding gradient");
            param.setLocked(true);
            param.setGroup(EnumGroup.Gradient);
            param.setCategory(Category.Acquisition);
            param.setUuid("4657c39f-c6ed-4791-a0a9-c57f4c6226cd");
            param.setValue(true);
            param.setDefaultValue(true);
            return param;
        }
    },

    GRADIENT_ENABLE_SLICE_CRUSH("GRADIENT_ENABLE_SLICE_CRUSH") {
        public Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("GRADIENT_ENABLE_SLICE_CRUSH");
            param.setDisplayedName("Gradient Crusher Slice");
            param.setDescription("Enable slice encoding crusher gradients before & after the 180 RF pulse");
            param.setGroup(EnumGroup.Gradient);
            param.setCategory(Category.Acquisition);
            param.setUuid("d8acbad4-67a5-4b33-a03c-b6ded00612e6");
            param.setValue(true);
            param.setDefaultValue(true);
            return param;
        }
    },

    GRADIENT_ENABLE_SPOILER("GRADIENT_ENABLE_SPOILER") {
        public Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("GRADIENT_ENABLE_SPOILER");
            param.setDisplayedName("Gradient Spoilers");
            param.setDescription("Enable gradient spoiler in the three directions");
            param.setGroup(EnumGroup.Gradient);
            param.setCategory(Category.Acquisition);
            param.setUuid("76473f79-1973-4790-b9b3-dff081e9d07a");
            param.setValue(true);
            param.setDefaultValue(false);
            return param;
        }
    },

    GRADIENT_PHASE_APPLICATION_TIME("GRADIENT_PHASE_APPLICATION_TIME") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("GRADIENT_PHASE_APPLICATION_TIME");
            param.setDisplayedName("Gradient Duration Phase");
            param.setDescription("Top time of phase encoding gradient");
            param.setGroup(EnumGroup.Delay);
            param.setCategory(Category.Acquisition);
            param.setUuid("5818a8a4-a334-4273-8061-76096b2e8c50");
            param.setNumberEnum(NumberEnum.Time);
            param.setMinValue(0.0);
            param.setMaxValue(1.0);
            param.setValue(4.0E-4);
            param.setDefaultValue(5.0E-4);
            return param;
        }
    },

    GRADIENT_READ_OFFSET("GRADIENT_READ_OFFSET") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("GRADIENT_READ_OFFSET");
            param.setDisplayedName("Gradient Offset Read");
            param.setDescription("Amplitude added to the read prephasing gradient to have an offset ");
            param.setGroup(EnumGroup.Gradient);
            param.setCategory(Category.Acquisition);
            param.setUuid("850929df-4caa-48e9-be16-90f2cc620630");
            param.setNumberEnum(NumberEnum.GradAmp);
            param.setMinValue(-100.0);
            param.setMaxValue(100.0);
            param.setValue(0.15);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    GRADIENT_READ_PREPHASING_APPLICATION_TIME("GRADIENT_READ_PREPHASING_APPLICATION_TIME") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("GRADIENT_READ_PREPHASING_APPLICATION_TIME");
            param.setDisplayedName("Gradient Duration Prephasing Read");
            param.setDescription("Top time application read prephasing gradient");
            param.setGroup(EnumGroup.Delay);
            param.setCategory(Category.Acquisition);
            param.setUuid("f0a86fc0-e3ef-4205-a683-188942549a9f");
            param.setNumberEnum(NumberEnum.Time);
            param.setMinValue(0.0);
            param.setMaxValue(1.0);
            param.setValue(3.9999999999999996E-4);
            param.setDefaultValue(5.0E-4);
            return param;
        }
    },

    GRADIENT_RISE_TIME("GRADIENT_RISE_TIME") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("GRADIENT_RISE_TIME");
            param.setDisplayedName("Gradient Rise Time");
            param.setDescription("Rise time of the gradient");
            param.setLocked(true);
            param.setGroup(EnumGroup.Delay);
            param.setCategory(Category.Acquisition);
            param.setUuid("830aa1e6-7c6f-4eaa-884c-872a2e724101");
            param.setNumberEnum(NumberEnum.Time);
            param.setMinValue(0.0);
            param.setMaxValue(1.0);
            param.setValue(1.5E-4);
            param.setDefaultValue(1.9999999999999998E-4);
            return param;
        }
    },

    IMAGE_CONTRAST("IMAGE_CONTRAST") {
        public Param build() {
            TextParam param = new TextParam();
            param.setName("IMAGE_CONTRAST");
            param.setDisplayedName("Image Contrast");
            param.setDescription("Type of image contrast");
            param.setGroup(EnumGroup.Scan);
            param.setCategory(Category.Acquisition);
            param.setUuid("61227c09-5412-444e-b9b2-b7c415e04d00");
            param.setValue("Custom");
            param.setDefaultValue("T1-weighted");
            param.setSuggestedValues(asList("T1-weighted", "T2-weighted", "PD-weighted", "Custom"));
            return param;
        }
    },

    IMAGE_ORIENTATION_SUBJECT("IMAGE_ORIENTATION_SUBJECT") {
        public Param build() {
            ListNumberParam param = new ListNumberParam();
            param.setName("IMAGE_ORIENTATION_SUBJECT");
            param.setDisplayedName("Image Orientation");
            param.setDescription("Direction cosines of the first row and the first column with respect to the subject");
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setUuid("22212598-92a8-48c6-a776-a889e051df41");
            param.setMinValue(-1.7976931348623157E308);
            param.setMaxValue(1.7976931348623157E308);
            param.setNumberEnum(NumberEnum.Double);
            param.setValue(asListNumber(1.0, 0.0, 0.0, 0.0, -1.0, 0.0));
            param.setDefaultValue(asListNumber(1.0, 0.0, 0.0, 0.0, 1.0, 0.0));
            return param;
        }
    },

    INTERMEDIATE_FREQUENCY("INTERMEDIATE_FREQUENCY") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("INTERMEDIATE_FREQUENCY");
            param.setDisplayedName("INTERMEDIATE_FREQUENCY");
            param.setDescription("Info: Frequency from the Hardware used for the signal demodulation (ADC optimal frequency)");
            param.setLocked(true);
            param.setGroup(EnumGroup.Reception);
            param.setCategory(Category.Acquisition);
            param.setUuid("d8590d34-23a1-4356-81a1-76628b016c47");
            param.setNumberEnum(NumberEnum.Frequency);
            param.setMinValue(0.0);
            param.setMaxValue(3.0E9);
            param.setValue(1.251E7);
            param.setDefaultValue(1.251E7);
            return param;
        }
    },

    INVERSION_RECOVERY("INVERSION_RECOVERY") {
        public Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("INVERSION_RECOVERY");
            param.setDisplayedName("IR");
            param.setDescription("Enable inversion recovery block before acquisition");
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Acquisition);
            param.setUuid("705da903-6d50-4577-86f8-3648a652823e");
            param.setValue(false);
            param.setDefaultValue(false);
            return param;
        }
    },

    INVERSION_TIME("INVERSION_TIME") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("INVERSION_TIME");
            param.setDisplayedName("INVERSION_TIME");
            param.setDescription("Inversion time (only output parameter)");
            param.setLocked(true);
            param.setGroup(EnumGroup.Delay);
            param.setCategory(Category.Acquisition);
            param.setUuid("5d31bf4b-7adf-4ead-9206-e0d61cb0da94");
            param.setNumberEnum(NumberEnum.Time);
            param.setMinValue(0.0);
            param.setMaxValue(1.0E9);
            param.setValue(0.0);
            param.setDefaultValue(0.005);
            return param;
        }
    },

    INVERSION_TIME_MULTI("INVERSION_TIME_MULTI") {
        public Param build() {
            ListNumberParam param = new ListNumberParam();
            param.setName("INVERSION_TIME_MULTI");
            param.setDisplayedName("TI");
            param.setDescription("List of the Inversion Recovery Time  ");
            param.setGroup(EnumGroup.Delay);
            param.setCategory(Category.Acquisition);
            param.setUuid("ce41b720-3574-4365-a75f-ca48c2ed8fef");
            param.setMinValue(0.0);
            param.setMaxValue(1.0E9);
            param.setNumberEnum(NumberEnum.Time);
            param.setValue(asListNumber(0.0));
            return param;
        }
    },

    KS_CENTERED("KS_CENTERED") {
        public Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("KS_CENTERED");
            param.setDisplayedName("k-space Centered");
            param.setDescription("Center the k-space around k0(Checked) or go through k0 (Unchecked)");
            param.setGroup(EnumGroup.Gradient);
            param.setCategory(Category.Acquisition);
            param.setUuid("21620e1a-768a-462b-bbd5-0da6e82694d2");
            param.setValue(false);
            param.setDefaultValue(false);
            return param;
        }
    },

    KS_CENTER_MODE("KS_CENTER_MODE") {
        public Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("KS_CENTER_MODE");
            param.setDisplayedName("KS_CENTER_MODE");
            param.setDescription("Turn off the phase encoding gradient and acquire two scans only. Used for RG calibration setup ");
            param.setLocked(true);
            param.setCategory(Category.Acquisition);
            param.setUuid("881dea6a-104d-442c-b16c-11ae1ee798f0");
            param.setValue(false);
            param.setDefaultValue(false);
            return param;
        }
    },

    LAST_PUT("LAST_PUT") {
        public Param build() {
            ListNumberParam param = new ListNumberParam();
            param.setName("LAST_PUT");
            param.setDisplayedName("LAST_PUT");
            param.setDescription("LAST_PUT.description");
            param.setLocked(true);
            param.setGroup(EnumGroup.Reception);
            param.setCategory(Category.Acquisition);
            param.setUuid("547a732c-dbec-491c-9a0f-f9533df7eac2");
            param.setMinValue(-2147483648);
            param.setMaxValue(2147483647);
            param.setNumberEnum(NumberEnum.Integer);
            param.setValue(asListNumber(0, 0, 0));
            param.setDefaultValue(asListNumber(0, 0, 0));
            return param;
        }
    },

    LIM_PD_WEIGHTED("LIM_PD_WEIGHTED") {
        public Param build() {
            ListNumberParam param = new ListNumberParam();
            param.setName("LIM_PD_WEIGHTED");
            param.setDisplayedName("TE/TR Limits PDw");
            param.setDescription("TR limit to get Proton-Density Weighted imaging: TR inf. limit");
            param.setGroup(EnumGroup.Delay);
            param.setCategory(Category.Acquisition);
            param.setUuid("a9e02f27-6ec6-4694-a802-11123f62cc54");
            param.setMinValue(9.999999999999999E-5);
            param.setMaxValue(60.0);
            param.setNumberEnum(NumberEnum.Time);
            param.setValue(asListNumber(0.02, 2.0));
            param.setDefaultValue(asListNumber(0.02, 2.0));
            return param;
        }
    },

    LIM_T1_WEIGHTED("LIM_T1_WEIGHTED") {
        public Param build() {
            ListNumberParam param = new ListNumberParam();
            param.setName("LIM_T1_WEIGHTED");
            param.setDisplayedName("TE/TR Limits T1w");
            param.setDescription("TR limits to get T1 Weighted imaging: (TR inf. limit, TR sup. limit)");
            param.setGroup(EnumGroup.Delay);
            param.setCategory(Category.Acquisition);
            param.setUuid("3aeee6b9-2763-4a0d-bf65-c92275464c92");
            param.setMinValue(9.999999999999999E-5);
            param.setMaxValue(100.0);
            param.setNumberEnum(NumberEnum.Time);
            param.setValue(asListNumber(0.02, 0.4, 0.7000000000000001));
            param.setDefaultValue(asListNumber(0.02, 0.7));
            return param;
        }
    },

    LIM_T2_WEIGHTED("LIM_T2_WEIGHTED") {
        public Param build() {
            ListNumberParam param = new ListNumberParam();
            param.setName("LIM_T2_WEIGHTED");
            param.setDisplayedName("TE/TR Limits T2w");
            param.setDescription("TE/TR limits to get T2 Weighted imaging: (TE inf. limit, TR inf. limit)");
            param.setGroup(EnumGroup.Delay);
            param.setCategory(Category.Acquisition);
            param.setUuid("df69d43a-eb5d-46a7-ab77-a5ae0bcbd05f");
            param.setMinValue(0.0);
            param.setMaxValue(1.0E9);
            param.setNumberEnum(NumberEnum.Time);
            param.setValue(asListNumber(0.08, 2.0));
            param.setDefaultValue(asListNumber(0.08, 2.0));
            return param;
        }
    },

    MAGNETIC_FIELD_STRENGTH("MAGNETIC_FIELD_STRENGTH") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("MAGNETIC_FIELD_STRENGTH");
            param.setDisplayedName("B0 Strength");
            param.setDescription("Info: Magnetic field strength");
            param.setLockedToDefault(true);
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Acquisition);
            param.setUuid("a8bf93ef-b214-4213-bf65-80fc1ec50490");
            param.setNumberEnum(NumberEnum.Field);
            param.setMinValue(0.0);
            param.setMaxValue(100.0);
            param.setValue(1.4985997195057952);
            param.setDefaultValue(1.4985997195057952);
            return param;
        }
    },

    MANUFACTURER("MANUFACTURER") {
        public Param build() {
            TextParam param = new TextParam();
            param.setName("MANUFACTURER");
            param.setDisplayedName("Manufacturer");
            param.setDescription("Info: Manufacturer");
            param.setLockedToDefault(true);
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Miscellaneous);
            param.setUuid("94079ef7-aff0-4293-a550-d4814e48d1a9");
            param.setValue("Manufacturer");
            param.setDefaultValue("Manufacturer");
            return param;
        }
    },

    MIN_RISE_TIME_FACTOR("MIN_RISE_TIME_FACTOR") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("MIN_RISE_TIME_FACTOR");
            param.setDisplayedName("Min Rise Time Factor");
            param.setDescription("Safety parameter applied on maximum gradient slew rate. Fastest at 100%");
            param.setGroup(EnumGroup.Gradient);
            param.setCategory(Category.Acquisition);
            param.setUuid("2a7d27ce-d9af-466f-a911-511df0a886bf");
            param.setNumberEnum(NumberEnum.PERCENT);
            param.setMinValue(0.0);
            param.setMaxValue(100.0);
            param.setValue(90.0);
            param.setDefaultValue(90.0);
            return param;
        }
    },

    MODALITY("MODALITY") {
        public Param build() {
            TextParam param = new TextParam();
            param.setName("MODALITY");
            param.setDisplayedName("Modality");
            param.setDescription("Info: Acquisition modality");
            param.setLockedToDefault(true);
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Acquisition);
            param.setUuid("4529607f-20e6-4d24-bbe1-d80e7e23f84f");
            param.setValue("MRI");
            param.setDefaultValue("MRI");
            param.setSuggestedValues(asList("NMR", "MRI", "DEFAULT"));
            param.setRestrictedToSuggested(true);
            return param;
        }
    },

    MODEL_NAME("MODEL_NAME") {
        public Param build() {
            TextParam param = new TextParam();
            param.setName("MODEL_NAME");
            param.setDisplayedName("Model Name");
            param.setDescription("Info: Model name");
            param.setLockedToDefault(true);
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Miscellaneous);
            param.setUuid("42dd6655-89ce-4f1b-88f0-705a6d3cca05");
            param.setValue("Model name");
            param.setDefaultValue("Model name");
            return param;
        }
    },

    MULTISERIES_PARAMETER_NAME("MULTISERIES_PARAMETER_NAME") {
        public Param build() {
            TextParam param = new TextParam();
            param.setName("MULTISERIES_PARAMETER_NAME");
            param.setDisplayedName("Multiseries Param Name");
            param.setDescription("Info: Name of MultiSeries Parameter: TE (multiecho), TI (multi-IR), TD (multi-trigger) or phase shift (DIXON)");
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Acquisition);
            param.setUuid("17ee2ff8-4639-4dfb-9f4e-e42f7b2d1665");
            param.setValue("");
            param.setDefaultValue("");
            param.setSuggestedValues(asList("TE", "TI", "B-VALUE", "TRIGGER DELAY"));
            return param;
        }
    },

    MULTISERIES_PARAMETER_VALUE("MULTISERIES_PARAMETER_VALUE") {
        public Param build() {
            ListNumberParam param = new ListNumberParam();
            param.setName("MULTISERIES_PARAMETER_VALUE");
            param.setDisplayedName("Multiseries Param Values");
            param.setDescription("Info: List of values of the incremented parameter in multiseries mode (cf. Mulriseries Param Name)");
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Acquisition);
            param.setUuid("efa23d01-f0d0-4c91-a6e4-83ab1186f291");
            param.setMinValue(-1.7976931348623157E308);
            param.setMaxValue(1.7976931348623157E308);
            param.setNumberEnum(NumberEnum.Double);
            param.setDefaultValue(asListNumber(0.0));
            return param;
        }
    },

    MULTISLICE_PACKED("MULTISLICE_PACKED") {
        public Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("MULTISLICE_PACKED");
            param.setDisplayedName("Multislice Packed");
            param.setDescription("Acquire all interleaved multislices at the beginning of TR");
            param.setGroup(EnumGroup.Scan);
            param.setCategory(Category.Acquisition);
            param.setUuid("2d7a5fc6-e750-4d8b-b3a5-3789872d2cb9");
            param.setValue(false);
            param.setDefaultValue(false);
            return param;
        }
    },

    MULTI_PLANAR_EXCITATION("MULTI_PLANAR_EXCITATION") {
        public Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("MULTI_PLANAR_EXCITATION");
            param.setDisplayedName("2D-Imaging");
            param.setDescription("Slice selection (enable) or phase encoding in the third direction (disable)");
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Acquisition);
            param.setUuid("936757df-3c43-4675-aabc-447aea9678ba");
            param.setValue(true);
            param.setDefaultValue(false);
            return param;
        }
    },

    NUCLEUS_1("NUCLEUS_1") {
        public Param build() {
            TextParam param = new TextParam();
            param.setName("NUCLEUS_1");
            param.setDisplayedName("Nucleus 1");
            param.setDescription("Nucleus used for the first sequence channel");
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Acquisition);
            param.setUuid("00afe13c-6149-4e31-a3eb-df1828d34114");
            param.setValue("1H");
            param.setDefaultValue("1H");
            param.setSuggestedValues(asList("Y", "Other", "X", "1H", "2H", "3H", "3He", "6Li", "7Li", "9Be", "10B", "11B", "13C", "14N", "15N", "17O", "19F", "21Ne", "23Na", "25Mg", "27Al", "29Si", "31P", "33S", "35Cl", "37Cl", "39K", "40K", "41K", "43Ca", "45Sc", "47Ti", "49Ti", "50V", "51V", "53Cr", "55Mn", "57Fe", "59Co", "61Ni", "63Cu", "65Cu", "67Zn", "69Ga", "71Ga", "73Ge", "75As", "77Se", "79Br", "81Br", "83Kr", "85Rb", "87Rb", "87Sr", "89Y", "91Zr", "93Nb", "95Mo", "97Mo", "99Tc", "99Ru", "101Ru", "103Rh", "105Pd", "107Ag", "109Ag", "111Cd", "113Cd", "113In", "115Sn", "115In", "117Sn", "119Sn", "121Sb", "123Te", "123Sb", "125Te", "127I", "129Xe", "131Xe", "133Cs", "135Ba", "137Ba", "138La", "139La", "141Pr", "143Nd", "145Nd", "147Sm", "149Sm", "151Eu", "153Eu", "155Gd", "157Gd", "159Tb", "161Dy", "163Dy", "165Ho", "167Er", "169Tm", "171Yb", "173Yb", "175Lu", "176Lu", "177Hf", "179Hf", "181Ta", "183W", "185Re", "187Re", "187Os", "189Os", "191Ir", "193Ir", "195Pt", "197Au", "199Hg", "201Hg", "203Tl", "205Tl", "207Pb", "209Bi", "235U"));
            param.setRestrictedToSuggested(true);
            return param;
        }
    },

    NUCLEUS_2("NUCLEUS_2") {
        public Param build() {
            TextParam param = new TextParam();
            param.setName("NUCLEUS_2");
            param.setDisplayedName("Nucleus 2");
            param.setDescription("Nucleus used for the second sequence channel");
            param.setLocked(true);
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Acquisition);
            param.setUuid("06a82258-b0e9-4040-8d5a-a61340cedaae");
            param.setValue("1H");
            param.setDefaultValue("1H");
            param.setSuggestedValues(asList("Y", "Other", "X", "1H", "2H", "3H", "3He", "6Li", "7Li", "9Be", "10B", "11B", "13C", "14N", "15N", "17O", "19F", "21Ne", "23Na", "25Mg", "27Al", "29Si", "31P", "33S", "35Cl", "37Cl", "39K", "40K", "41K", "43Ca", "45Sc", "47Ti", "49Ti", "50V", "51V", "53Cr", "55Mn", "57Fe", "59Co", "61Ni", "63Cu", "65Cu", "67Zn", "69Ga", "71Ga", "73Ge", "75As", "77Se", "79Br", "81Br", "83Kr", "85Rb", "87Rb", "87Sr", "89Y", "91Zr", "93Nb", "95Mo", "97Mo", "99Tc", "99Ru", "101Ru", "103Rh", "105Pd", "107Ag", "109Ag", "111Cd", "113Cd", "113In", "115Sn", "115In", "117Sn", "119Sn", "121Sb", "123Te", "123Sb", "125Te", "127I", "129Xe", "131Xe", "133Cs", "135Ba", "137Ba", "138La", "139La", "141Pr", "143Nd", "145Nd", "147Sm", "149Sm", "151Eu", "153Eu", "155Gd", "157Gd", "159Tb", "161Dy", "163Dy", "165Ho", "167Er", "169Tm", "171Yb", "173Yb", "175Lu", "176Lu", "177Hf", "179Hf", "181Ta", "183W", "185Re", "187Re", "187Os", "189Os", "191Ir", "193Ir", "195Pt", "197Au", "199Hg", "201Hg", "203Tl", "205Tl", "207Pb", "209Bi", "235U"));
            param.setRestrictedToSuggested(true);
            return param;
        }
    },

    NUCLEUS_3("NUCLEUS_3") {
        public Param build() {
            TextParam param = new TextParam();
            param.setName("NUCLEUS_3");
            param.setDisplayedName("Nucleus 3");
            param.setDescription("Nucleus used for the third sequence channel");
            param.setLocked(true);
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Acquisition);
            param.setUuid("34f9ef8c-c182-41fe-8e22-7ffc70171eb5");
            param.setValue("1H");
            param.setDefaultValue("1H");
            param.setSuggestedValues(asList("Y", "Other", "X", "1H", "2H", "3H", "3He", "6Li", "7Li", "9Be", "10B", "11B", "13C", "14N", "15N", "17O", "19F", "21Ne", "23Na", "25Mg", "27Al", "29Si", "31P", "33S", "35Cl", "37Cl", "39K", "40K", "41K", "43Ca", "45Sc", "47Ti", "49Ti", "50V", "51V", "53Cr", "55Mn", "57Fe", "59Co", "61Ni", "63Cu", "65Cu", "67Zn", "69Ga", "71Ga", "73Ge", "75As", "77Se", "79Br", "81Br", "83Kr", "85Rb", "87Rb", "87Sr", "89Y", "91Zr", "93Nb", "95Mo", "97Mo", "99Tc", "99Ru", "101Ru", "103Rh", "105Pd", "107Ag", "109Ag", "111Cd", "113Cd", "113In", "115Sn", "115In", "117Sn", "119Sn", "121Sb", "123Te", "123Sb", "125Te", "127I", "129Xe", "131Xe", "133Cs", "135Ba", "137Ba", "138La", "139La", "141Pr", "143Nd", "145Nd", "147Sm", "149Sm", "151Eu", "153Eu", "155Gd", "157Gd", "159Tb", "161Dy", "163Dy", "165Ho", "167Er", "169Tm", "171Yb", "173Yb", "175Lu", "176Lu", "177Hf", "179Hf", "181Ta", "183W", "185Re", "187Re", "187Os", "189Os", "191Ir", "193Ir", "195Pt", "197Au", "199Hg", "201Hg", "203Tl", "205Tl", "207Pb", "209Bi", "235U"));
            param.setRestrictedToSuggested(true);
            return param;
        }
    },

    NUCLEUS_4("NUCLEUS_4") {
        public Param build() {
            TextParam param = new TextParam();
            param.setName("NUCLEUS_4");
            param.setDisplayedName("Nucleus 4");
            param.setDescription("Nucleus used for the fourth sequence channel");
            param.setLocked(true);
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Acquisition);
            param.setUuid("8eda72fc-7768-45b8-8c31-8137ccfbc56c");
            param.setValue("1H");
            param.setDefaultValue("1H");
            param.setSuggestedValues(asList("Y", "Other", "X", "1H", "2H", "3H", "3He", "6Li", "7Li", "9Be", "10B", "11B", "13C", "14N", "15N", "17O", "19F", "21Ne", "23Na", "25Mg", "27Al", "29Si", "31P", "33S", "35Cl", "37Cl", "39K", "40K", "41K", "43Ca", "45Sc", "47Ti", "49Ti", "50V", "51V", "53Cr", "55Mn", "57Fe", "59Co", "61Ni", "63Cu", "65Cu", "67Zn", "69Ga", "71Ga", "73Ge", "75As", "77Se", "79Br", "81Br", "83Kr", "85Rb", "87Rb", "87Sr", "89Y", "91Zr", "93Nb", "95Mo", "97Mo", "99Tc", "99Ru", "101Ru", "103Rh", "105Pd", "107Ag", "109Ag", "111Cd", "113Cd", "113In", "115Sn", "115In", "117Sn", "119Sn", "121Sb", "123Te", "123Sb", "125Te", "127I", "129Xe", "131Xe", "133Cs", "135Ba", "137Ba", "138La", "139La", "141Pr", "143Nd", "145Nd", "147Sm", "149Sm", "151Eu", "153Eu", "155Gd", "157Gd", "159Tb", "161Dy", "163Dy", "165Ho", "167Er", "169Tm", "171Yb", "173Yb", "175Lu", "176Lu", "177Hf", "179Hf", "181Ta", "183W", "185Re", "187Re", "187Os", "189Os", "191Ir", "193Ir", "195Pt", "197Au", "199Hg", "201Hg", "203Tl", "205Tl", "207Pb", "209Bi", "235U"));
            param.setRestrictedToSuggested(true);
            return param;
        }
    },

    NUMBER_OF_AVERAGES("NUMBER_OF_AVERAGES") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("NUMBER_OF_AVERAGES");
            param.setDisplayedName("Nex");
            param.setDescription("Number of averages");
            param.setGroup(EnumGroup.Scan);
            param.setCategory(Category.Acquisition);
            param.setUuid("00f3f22d-d24e-48e3-9fa3-a2c9de9efac6");
            param.setNumberEnum(NumberEnum.Scan);
            param.setMinValue(0);
            param.setMaxValue(65536);
            param.setValue(1);
            param.setDefaultValue(1);
            return param;
        }
    },

    NUMBER_OF_INTERLEAVED_SLICE("NUMBER_OF_INTERLEAVED_SLICE") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("NUMBER_OF_INTERLEAVED_SLICE");
            param.setDisplayedName("No. Slices per TR");
            param.setDescription("Info: Number of interleaved slice acquired during one TR");
            param.setGroup(EnumGroup.Scan);
            param.setCategory(Category.Acquisition);
            param.setUuid("bd68dc8e-dac9-4f19-9d5c-e524bcd98fee");
            param.setNumberEnum(NumberEnum.Scan);
            param.setMinValue(0);
            param.setMaxValue(1000);
            param.setValue(4);
            param.setDefaultValue(0);
            return param;
        }
    },

    NUMBER_OF_INTERLEAVED_SLICE_PACKS("NUMBER_OF_INTERLEAVED_SLICE_PACKS") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("NUMBER_OF_INTERLEAVED_SLICE_PACKS");
            param.setDisplayedName("No. Interleaved slice packs");
            param.setDescription("To split the slices in different packs acquired in one TR (No. Slices per TR = No. Slices / No. Interleaved Packs).  To reach lower TR, increase the number of packs (must be a divisor of the No. Slices).");
            param.setCategory(Category.Acquisition);
            param.setUuid("e7693207-cb6c-4a60-aa23-ff9d58b1eee4");
            param.setNumberEnum(NumberEnum.Double);
            param.setMinValue(-1.7976931348623157E308);
            param.setMaxValue(1.7976931348623157E308);
            param.setValue(4.0);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    NUMBER_OF_SHOOT_3D("NUMBER_OF_SHOOT_3D") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("NUMBER_OF_SHOOT_3D");
            param.setDisplayedName("NUMBER_OF_SHOOT_3D (temp)");
            param.setDescription("Info: Copy of No. Interleaved slice packs (temporary param, will be remmplaced) ; Total number of slice / Number of interleaved slice      ");
            param.setLocked(true);
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setUuid("78f27b89-a541-4428-b281-eb0a1e887761");
            param.setNumberEnum(NumberEnum.Scan);
            param.setMinValue(1);
            param.setMaxValue(65536);
            param.setValue(4);
            param.setDefaultValue(1);
            return param;
        }
    },

    OBSERVED_FREQUENCY("OBSERVED_FREQUENCY") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("OBSERVED_FREQUENCY");
            param.setDisplayedName("Observed Frequency");
            param.setDescription("Info: Frequency of the acquisition");
            param.setLocked(true);
            param.setGroup(EnumGroup.Reception);
            param.setCategory(Category.Acquisition);
            param.setUuid("5cd638e0-3d35-4bc9-bb27-a0a71bbba7c4");
            param.setNumberEnum(NumberEnum.Frequency);
            param.setMinValue(0.0);
            param.setMaxValue(3.0E9);
            param.setValue(6.3806597364338815E7);
            param.setDefaultValue(6.3E7);
            return param;
        }
    },

    OBSERVED_NUCLEUS("OBSERVED_NUCLEUS") {
        public Param build() {
            TextParam param = new TextParam();
            param.setName("OBSERVED_NUCLEUS");
            param.setDisplayedName("Observed Nucleus");
            param.setDescription("Info: Observed nucleus");
            param.setLocked(true);
            param.setGroup(EnumGroup.Reception);
            param.setCategory(Category.Acquisition);
            param.setUuid("8b0e796e-cbe5-4ea9-b102-536f2bb9d8a4");
            param.setValue("1H");
            param.setDefaultValue("1H");
            param.setSuggestedValues(asList("Other", "Y", "X", "3H", "1H", "19F", "3He", "205Tl", "203Tl", "31P", "7Li", "119Sn", "117Sn", "87Rb", "115Sn", "11B", "125Te", "141Pr", "71Ga", "65Cu", "129Xe", "81Br", "63Cu", "23Na", "51V", "123Te", "27Al", "13C", "79Br", "151Eu", "55Mn", "93Nb", "45Sc", "159Tb", "69Ga", "121Sb", "59Co", "187Re", "185Re", "99Tc", "113Cd", "115In", "113In", "195Pt", "165Ho", "111Cd", "207Pb", "127I", "29Si", "77Se", "199Hg", "171Yb", "75As", "209Bi", "2H", "6Li", "139La", "9Be", "17O", "138La", "133Cs", "123Sb", "181Ta", "175Lu", "137Ba", "153Eu", "10B", "15N", "50V", "135Ba", "35Cl", "85Rb", "91Zr", "61Ni", "169Tm", "131Xe", "37Cl", "176Lu", "21Ne", "189Os", "33S", "14N", "43Ca", "97Mo", "201Hg", "95Mo", "67Zn", "25Mg", "40K", "53Cr", "49Ti", "47Ti", "143Nd", "101Ru", "89Y", "173Yb", "163Dy", "39K", "109Ag", "99Ru", "105Pd", "87Sr", "147Sm", "183W", "107Ag", "157Gd", "177Hf", "83Kr", "73Ge", "149Sm", "161Dy", "145Nd", "57Fe", "103Rh", "155Gd", "167Er", "41K", "179Hf", "187Os", "193Ir", "235U", "197Au", "191Ir"));
            param.setRestrictedToSuggested(true);
            return param;
        }
    },

    OFFSET_FREQ_1("OFFSET_FREQ_1") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("OFFSET_FREQ_1");
            param.setDisplayedName("Frequency Offset 1");
            param.setDescription("Offset frequency of the first channel");
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setUuid("389f9a61-c55e-4089-aeac-bfbb79dffdd0");
            param.setNumberEnum(NumberEnum.FrequencyOffset);
            param.setMinValue(-1.5E9);
            param.setMaxValue(1.5E9);
            param.setValue(0.0);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    OFFSET_FREQ_2("OFFSET_FREQ_2") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("OFFSET_FREQ_2");
            param.setDisplayedName("Frequency Offset 2");
            param.setDescription("Offset frequency of the second channel");
            param.setLocked(true);
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setUuid("b39c6607-fea9-4c50-9057-23434faaa394");
            param.setNumberEnum(NumberEnum.FrequencyOffset);
            param.setMinValue(-1.5E9);
            param.setMaxValue(1.5E9);
            param.setValue(0.0);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    OFFSET_FREQ_3("OFFSET_FREQ_3") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("OFFSET_FREQ_3");
            param.setDisplayedName("Frequency Offset 3");
            param.setDescription("Offset frequency of the third channel");
            param.setLocked(true);
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setUuid("6cd41a75-f739-4c6b-96b7-59fc3e09c457");
            param.setNumberEnum(NumberEnum.FrequencyOffset);
            param.setMinValue(-1.5E9);
            param.setMaxValue(1.5E9);
            param.setValue(0.0);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    OFFSET_FREQ_4("OFFSET_FREQ_4") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("OFFSET_FREQ_4");
            param.setDisplayedName("Frequency Offset 4");
            param.setDescription("Offset frequency of the fourth channel");
            param.setLocked(true);
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setUuid("faab6f15-e886-47b2-adf8-67438a2c067e");
            param.setNumberEnum(NumberEnum.FrequencyOffset);
            param.setMinValue(-1.5E9);
            param.setMaxValue(1.5E9);
            param.setValue(0.0);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    OFF_CENTER_FIELD_OF_VIEW_1D("OFF_CENTER_FIELD_OF_VIEW_1D") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("OFF_CENTER_FIELD_OF_VIEW_1D");
            param.setDisplayedName("Location 1D");
            param.setDescription("Info: Off-center distance in Readout Direction");
            param.setLocked(true);
            param.setGroup(EnumGroup.Gradient);
            param.setCategory(Category.Acquisition);
            param.setUuid("af155ba1-ecc2-459f-beb3-2964c517076c");
            param.setNumberEnum(NumberEnum.Location);
            param.setMinValue(-1.7976931348623157E308);
            param.setMaxValue(1.7976931348623157E308);
            param.setValue(0.0);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    OFF_CENTER_FIELD_OF_VIEW_2D("OFF_CENTER_FIELD_OF_VIEW_2D") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("OFF_CENTER_FIELD_OF_VIEW_2D");
            param.setDisplayedName("Location 2D");
            param.setDescription("Info: Off-center distance in Phase Encoding Direction");
            param.setLocked(true);
            param.setGroup(EnumGroup.Gradient);
            param.setCategory(Category.Acquisition);
            param.setUuid("66f1765c-b8dc-498d-bcc7-e49312e70d7f");
            param.setNumberEnum(NumberEnum.Location);
            param.setMinValue(-1.7976931348623157E308);
            param.setMaxValue(1.7976931348623157E308);
            param.setValue(0.0);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    OFF_CENTER_FIELD_OF_VIEW_3D("OFF_CENTER_FIELD_OF_VIEW_3D") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("OFF_CENTER_FIELD_OF_VIEW_3D");
            param.setDisplayedName("Location 3D");
            param.setDescription("Info: Off-center distance in Slice Direction");
            param.setLocked(true);
            param.setGroup(EnumGroup.Gradient);
            param.setCategory(Category.Acquisition);
            param.setUuid("8daa0309-b171-497f-8691-e8c79adbb816");
            param.setNumberEnum(NumberEnum.Location);
            param.setMinValue(-1.7976931348623157E308);
            param.setMaxValue(1.7976931348623157E308);
            param.setValue(0.0);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    OFF_CENTER_FIELD_OF_VIEW_EFF("OFF_CENTER_FIELD_OF_VIEW_EFF") {
        public Param build() {
            ListNumberParam param = new ListNumberParam();
            param.setName("OFF_CENTER_FIELD_OF_VIEW_EFF");
            param.setDisplayedName("Location Eff");
            param.setDescription("Info: Off Center effective in 1D, 2D and 3D (read, phase, slice)");
            param.setLocked(true);
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setUuid("529e699e-ab03-4cfb-8313-0b84b6d5fdce");
            param.setMinValue(-1.7976931348623157E308);
            param.setMaxValue(1.7976931348623157E308);
            param.setNumberEnum(NumberEnum.Location);
            param.setValue(asListNumber(0.0, 0.0, 0.0));
            param.setDefaultValue(asListNumber(0.0, 0.0, 0.0));
            return param;
        }
    },

    OFF_CENTER_FIELD_OF_VIEW_X("OFF_CENTER_FIELD_OF_VIEW_X") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("OFF_CENTER_FIELD_OF_VIEW_X");
            param.setDisplayedName("Location X");
            param.setDescription("Location in the R/L direction");
            param.setGroup(EnumGroup.Gradient);
            param.setCategory(Category.Acquisition);
            param.setUuid("5d484bc3-cd4e-4012-879c-2d05dbd05a23");
            param.setNumberEnum(NumberEnum.Location);
            param.setMinValue(-1.7976931348623157E308);
            param.setMaxValue(1.7976931348623157E308);
            param.setValue(0.0);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    OFF_CENTER_FIELD_OF_VIEW_Y("OFF_CENTER_FIELD_OF_VIEW_Y") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("OFF_CENTER_FIELD_OF_VIEW_Y");
            param.setDisplayedName("Location Y");
            param.setDescription("Location in the A/P direction");
            param.setGroup(EnumGroup.Gradient);
            param.setCategory(Category.Acquisition);
            param.setUuid("f7f94779-0555-4d42-b520-5aefdf5df9a7");
            param.setNumberEnum(NumberEnum.Location);
            param.setMinValue(-1.7976931348623157E308);
            param.setMaxValue(1.7976931348623157E308);
            param.setValue(0.0);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    OFF_CENTER_FIELD_OF_VIEW_Z("OFF_CENTER_FIELD_OF_VIEW_Z") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("OFF_CENTER_FIELD_OF_VIEW_Z");
            param.setDisplayedName("Location Z");
            param.setDescription("Location in the I/S direction");
            param.setGroup(EnumGroup.Gradient);
            param.setCategory(Category.Acquisition);
            param.setUuid("c0d0cb7d-aa29-4b3d-b3d8-89335dfea8ce");
            param.setNumberEnum(NumberEnum.Location);
            param.setMinValue(-1.7976931348623157E308);
            param.setMaxValue(1.7976931348623157E308);
            param.setValue(0.0);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    ORIENTATION("ORIENTATION") {
        public Param build() {
            TextParam param = new TextParam();
            param.setName("ORIENTATION");
            param.setDisplayedName("Orientation");
            param.setDescription("Field of view orientation");
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setUuid("6f47d039-e086-4b53-a4ca-b20a2be12282");
            param.setValue("AXIAL");
            param.setDefaultValue("AXIAL");
            param.setSuggestedValues(asList("AXIAL", "CORONAL", "SAGITTAL", "OBLIQUE"));
            param.setRestrictedToSuggested(true);
            return param;
        }
    },

    PAROPT_PARAM("PAROPT_PARAM") {
        public Param build() {
            TextParam param = new TextParam();
            param.setName("PAROPT_PARAM");
            param.setDisplayedName("Parameter Optimised");
            param.setDescription("Name of the current optimised parameter");
            param.setLocked(true);
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Acquisition);
            param.setUuid("02b5d4dc-8222-4127-a46a-ff14600b20f0");
            param.setValue("");
            param.setDefaultValue("PULSE_LENGTH");
            return param;
        }
    },

    PHASE_0("PHASE_0") {
        public Param build() {
            ListNumberParam param = new ListNumberParam();
            param.setName("PHASE_0");
            param.setDisplayedName("PHASE_0");
            param.setDescription("PHASE_0.description");
            param.setLocked(true);
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Process);
            param.setUuid("69b342ab-a56e-4453-a11b-f0236adf1a69");
            param.setMinValue(-1.7976931348623157E308);
            param.setMaxValue(1.7976931348623157E308);
            param.setNumberEnum(NumberEnum.Double);
            return param;
        }
    },

    PHASE_1("PHASE_1") {
        public Param build() {
            ListNumberParam param = new ListNumberParam();
            param.setName("PHASE_1");
            param.setDisplayedName("PHASE_1");
            param.setDescription("PHASE_1.description");
            param.setLocked(true);
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Process);
            param.setUuid("b446d545-fa91-4d48-a2c9-12989445ba11");
            param.setMinValue(-1.7976931348623157E308);
            param.setMaxValue(1.7976931348623157E308);
            param.setNumberEnum(NumberEnum.Double);
            return param;
        }
    },

    PHASE_APPLIED("PHASE_APPLIED") {
        public Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("PHASE_APPLIED");
            param.setDisplayedName("PHASE_APPLIED");
            param.setDescription("PHASE_APPLIED");
            param.setLocked(true);
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Process);
            param.setUuid("fa5cda39-ca08-4dae-a01c-0bdc91dba1d0");
            param.setValue(false);
            param.setDefaultValue(false);
            return param;
        }
    },

    PHASE_CYCLING("PHASE_CYCLING") {
        public Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("PHASE_CYCLING");
            param.setDisplayedName("Phase Cycling");
            param.setDescription("Enable the phase cycling");
            param.setGroup(EnumGroup.Reception);
            param.setCategory(Category.Acquisition);
            param.setUuid("a3ce1bdd-7cf1-4dac-b186-9383a43d4106");
            param.setValue(true);
            param.setDefaultValue(true);
            return param;
        }
    },

    PHASE_FIELD_OF_VIEW_RATIO("PHASE_FIELD_OF_VIEW_RATIO") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("PHASE_FIELD_OF_VIEW_RATIO");
            param.setDisplayedName("Phase FOV Ratio");
            param.setDescription("Info: Ratio between the phase-encoded FOV and the frequency-encoded FOV in % ");
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setUuid("917bc526-abbb-4b57-a09b-8324e650bc3e");
            param.setNumberEnum(NumberEnum.PERCENT);
            param.setMinValue(0.0);
            param.setMaxValue(100.0);
            param.setValue(100.0);
            param.setDefaultValue(100.0);
            return param;
        }
    },

    PHASE_RESET("PHASE_RESET") {
        public Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("PHASE_RESET");
            param.setDisplayedName("PHASE_RESET");
            param.setDescription("Enable the Phase reset at the beginning of each scan");
            param.setLocked(true);
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setUuid("c4e3f6bc-53d1-4b5a-a733-58b52076690d");
            param.setValue(false);
            param.setDefaultValue(false);
            return param;
        }
    },

    PREPHASING_READ_GRADIENT_RATIO("PREPHASING_READ_GRADIENT_RATIO") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("PREPHASING_READ_GRADIENT_RATIO");
            param.setDisplayedName("Gradient Ratio Prephasing Read");
            param.setDescription("Prephasing reading gradient ratio");
            param.setGroup(EnumGroup.Gradient);
            param.setCategory(Category.Acquisition);
            param.setUuid("eb44d053-fdf9-4e09-8c0c-544d0bb085c4");
            param.setNumberEnum(NumberEnum.Double);
            param.setMinValue(-100.0);
            param.setMaxValue(100.0);
            param.setValue(0.5);
            param.setDefaultValue(0.5);
            return param;
        }
    },

    PROBES("PROBES") {
        public Param build() {
            ListTextParam param = new ListTextParam();
            param.setName("PROBES");
            param.setDisplayedName("Probes");
            param.setDescription("Name of probes used for the transmit and reception");
            param.setLocked(true);
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Acquisition);
            param.setUuid("9a7e21c2-f0c9-4e78-9484-de0a0d183231");
            return param;
        }
    },

    RECEIVER_COUNT("RECEIVER_COUNT") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("RECEIVER_COUNT");
            param.setDisplayedName("No. Receivers");
            param.setDescription("Number of reception channels");
            param.setGroup(EnumGroup.Reception);
            param.setCategory(Category.Acquisition);
            param.setUuid("7d9daff8-4c0f-4e6a-97be-76a2d1d7c1b1");
            param.setNumberEnum(NumberEnum.Integer);
            param.setMinValue(1);
            param.setMaxValue(32);
            param.setValue(1);
            param.setDefaultValue(1);
            return param;
        }
    },

    RECEIVER_GAIN("RECEIVER_GAIN") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("RECEIVER_GAIN");
            param.setDisplayedName("RG");
            param.setDescription("Receiver gain");
            param.setGroup(EnumGroup.Reception);
            param.setCategory(Category.Acquisition);
            param.setUuid("cca08ba6-4162-4212-b780-7748f962c1ef");
            param.setNumberEnum(NumberEnum.RxGain);
            param.setMinValue(0.0);
            param.setMaxValue(120.0);
            param.setValue(38.0);
            param.setDefaultValue(10.0);
            return param;
        }
    },

    REPETITION_TIME("REPETITION_TIME") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("REPETITION_TIME");
            param.setDisplayedName("TR");
            param.setDescription("Repetition Time");
            param.setGroup(EnumGroup.Delay);
            param.setCategory(Category.Acquisition);
            param.setUuid("e0f04363-aa2c-4f91-a9bf-5b0cc9a1c460");
            param.setNumberEnum(NumberEnum.Time);
            param.setMinValue(0.0);
            param.setMaxValue(1.0E9);
            param.setValue(0.6);
            param.setDefaultValue(0.2);
            return param;
        }
    },

    RESOLUTION_FREQUENCY("RESOLUTION_FREQUENCY") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("RESOLUTION_FREQUENCY");
            param.setDisplayedName("Voxel Size Read");
            param.setDescription("Info: True voxel size in the frequency encoding direction (FOV/acquisition_matrix_dimension_1D) ");
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setUuid("8f40e525-51b9-47c7-8277-85b30318b25f");
            param.setNumberEnum(NumberEnum.Length);
            param.setMinValue(0.0);
            param.setMaxValue(100.0);
            param.setValue(1.7578125E-4);
            param.setDefaultValue(0.01);
            return param;
        }
    },

    RESOLUTION_PHASE("RESOLUTION_PHASE") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("RESOLUTION_PHASE");
            param.setDisplayedName("Voxel Size Phase");
            param.setDescription("Info: True voxel in the phase encoding direction (FOV/acquisition_matrix_dimension_2D) ");
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setUuid("3cb0d1f1-eca8-4e9d-adbc-8b4e24917393");
            param.setNumberEnum(NumberEnum.Length);
            param.setMinValue(0.0);
            param.setMaxValue(100.0);
            param.setValue(2.34375E-4);
            param.setDefaultValue(5.0E-4);
            return param;
        }
    },

    RESOLUTION_SLICE("RESOLUTION_SLICE") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("RESOLUTION_SLICE");
            param.setDisplayedName("Voxel Size Slice/3D");
            param.setDescription("Info: True voxel size in the third direction");
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setUuid("bfbd729c-597a-49b2-a428-e67869a112ca");
            param.setNumberEnum(NumberEnum.Length);
            param.setMinValue(0.0);
            param.setMaxValue(100.0);
            param.setValue(7.999999999999999E-4);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    SATBAND_DISTANCE_FROM_FOV("SATBAND_DISTANCE_FROM_FOV") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("SATBAND_DISTANCE_FROM_FOV");
            param.setDisplayedName("SatBand - Distance from FOV");
            param.setDescription("Distance of the Saturation Band from the FOV ");
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setUuid("564f8095-656b-4033-b938-bb69239415b2");
            param.setNumberEnum(NumberEnum.Location);
            param.setMinValue(-0.1);
            param.setMaxValue(0.1);
            param.setValue(0.0);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    SATBAND_ENABLED("SATBAND_ENABLED") {
        public Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("SATBAND_ENABLED");
            param.setDisplayedName("Saturation Band");
            param.setDescription("Enable saturation band");
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Acquisition);
            param.setUuid("691de177-eb4c-4810-982f-eb5351ba790c");
            param.setValue(false);
            param.setDefaultValue(false);
            return param;
        }
    },

    SATBAND_GRAD_AMP_SPOILER("SATBAND_GRAD_AMP_SPOILER") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("SATBAND_GRAD_AMP_SPOILER");
            param.setDisplayedName("SatBand - Gradient Amplitude");
            param.setDescription("Amplitude of the spoiler gradient following the saturation pulse. The spoiler will futher eliminate the transverse magnetisation by creating a phase dispersion.");
            param.setGroup(EnumGroup.Gradient);
            param.setCategory(Category.Acquisition);
            param.setUuid("11c0304a-2516-4072-badd-ad8ff307cc14");
            param.setNumberEnum(NumberEnum.PERCENT);
            param.setMinValue(30.0);
            param.setMaxValue(100.0);
            param.setValue(40.0);
            param.setDefaultValue(40.0);
            return param;
        }
    },

    SATBAND_ORIENTATION("SATBAND_ORIENTATION") {
        public Param build() {
            TextParam param = new TextParam();
            param.setName("SATBAND_ORIENTATION");
            param.setDisplayedName("SatBand - Orientation");
            param.setDescription("Orientation of the Saturation Band");
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setUuid("068e05e0-a54e-446b-8f8d-9a925e3919d7");
            param.setValue("RIGHT");
            param.setDefaultValue("CRANIAL");
            param.setSuggestedValues(asList("CRANIAL", "CAUDAL", "CRANIAL AND CAUDAL", "ANTERIOR", "POSTERIOR", "ANTERIOR AND POSTERIOR", "RIGHT", "LEFT", "RIGHT AND LEFT", "ALL"));
            return param;
        }
    },

    SATBAND_T1("SATBAND_T1") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("SATBAND_T1");
            param.setDisplayedName("SatBand - Target T1");
            param.setDescription("T1 of the saturated tissue : the longitudinal magnetization will be nulled when the excitation pulse is applied.");
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Acquisition);
            param.setUuid("1c081c89-330d-4261-8472-164fcda3d8fc");
            param.setNumberEnum(NumberEnum.Millis);
            param.setMinValue(0.0);
            param.setMaxValue(1.0E9);
            param.setValue(500.0);
            param.setDefaultValue(500.0);
            return param;
        }
    },

    SATBAND_THICKNESS("SATBAND_THICKNESS") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("SATBAND_THICKNESS");
            param.setDisplayedName("SatBand - Thickness");
            param.setDescription("Thickness of the Saturation Band");
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setUuid("c79eade6-31be-4e9d-84c5-939fd4132253");
            param.setNumberEnum(NumberEnum.Length);
            param.setMinValue(0.001);
            param.setMaxValue(0.1);
            param.setValue(0.01);
            param.setDefaultValue(0.01);
            return param;
        }
    },

    SATBAND_TX_AMP("SATBAND_TX_AMP") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("SATBAND_TX_AMP");
            param.setDisplayedName("SatBand - TX Amplitude");
            param.setDescription("Info: Saturation Band Pulse amplitude");
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setUuid("745fb208-bc7f-4dc2-b0f2-f0283c97abda");
            param.setNumberEnum(NumberEnum.TxAmp);
            param.setMinValue(0.0);
            param.setMaxValue(100.0);
            param.setValue(0.0);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    SATBAND_TX_SHAPE("SATBAND_TX_SHAPE") {
        public Param build() {
            TextParam param = new TextParam();
            param.setName("SATBAND_TX_SHAPE");
            param.setDisplayedName("SatBand - TX Shape");
            param.setDescription("Saturation Band Pulse shape");
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setUuid("eef58108-698a-4e4e-8b9b-29ccf6246812");
            param.setValue("SINC3");
            param.setDefaultValue("HARD");
            param.setSuggestedValues(asList("HARD", "GAUSSIAN", "SINC3", "SINC5", "SLR_8_5152", "SLR_4_2576"));
            param.setRestrictedToSuggested(true);
            return param;
        }
    },

    SEQUENCE_NAME("SEQUENCE_NAME") {
        public Param build() {
            TextParam param = new TextParam();
            param.setName("SEQUENCE_NAME");
            param.setDisplayedName("Sequence Name");
            param.setDescription("Info: Name of the sequence");
            param.setLockedToDefault(true);
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Acquisition);
            param.setUuid("546ca65f-52c6-4a97-9dbf-b6c8f74781da");
            param.setValue("SPIN_ECHO");
            param.setDefaultValue("SPIN_ECHO");
            return param;
        }
    },

    SEQUENCE_TIME("SEQUENCE_TIME") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("SEQUENCE_TIME");
            param.setDisplayedName("Acquisition Time");
            param.setDescription("Info: Total acquisition time");
            param.setGroup(EnumGroup.Delay);
            param.setCategory(Category.Acquisition);
            param.setUuid("2678f9ad-773c-4e7d-b41c-85fc1e5d0e76");
            param.setNumberEnum(NumberEnum.Time);
            param.setMinValue(0.0);
            param.setMaxValue(1.0E9);
            param.setValue(39.600005);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    SEQUENCE_VERSION("SEQUENCE_VERSION") {
        public Param build() {
            TextParam param = new TextParam();
            param.setName("SEQUENCE_VERSION");
            param.setDisplayedName("Sequence version");
            param.setDescription("Info: Sequence version");
            param.setLocked(true);
            param.setGroup(EnumGroup.User);
            param.setCategory(Category.Acquisition);
            param.setUuid("9be72f51-162f-4a0e-8513-5bb35dfeb8d3");
            param.setValue("Version9.17");
            param.setDefaultValue("");
            return param;
        }
    },

    SEQ_DESCRIPTION("SEQ_DESCRIPTION") {
        public Param build() {
            TextParam param = new TextParam();
            param.setName("SEQ_DESCRIPTION");
            param.setDisplayedName("Sequence Description");
            param.setDescription("Info: Description of the sequence");
            param.setCategory(Category.Acquisition);
            param.setUuid("6e264e2e-5362-45c2-8b31-50d7b196547b");
            param.setValue("SE_2D_AXI_256x192x16_ETL=12");
            param.setDefaultValue("");
            return param;
        }
    },

    SETUP_MODE("SETUP_MODE") {
        public Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("SETUP_MODE");
            param.setDisplayedName("SETUP_MODE");
            param.setDescription("True during setup process / False for regular use for the sequence");
            param.setLocked(true);
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Acquisition);
            param.setUuid("930fec4e-4bed-4b6a-b8fd-e3678b8876e5");
            param.setValue(false);
            param.setDefaultValue(false);
            return param;
        }
    },

    SE_TYPE("SE_TYPE") {
        public Param build() {
            TextParam param = new TextParam();
            param.setName("SE_TYPE");
            param.setDisplayedName("Echo Train Type");
            param.setDescription("Choose between FSE and MultiEcho");
            param.setGroup(EnumGroup.Scan);
            param.setCategory(Category.Acquisition);
            param.setUuid("edb1c90d-6bfc-41c1-a85f-aa502b382be7");
            param.setValue("FSE");
            param.setDefaultValue("FSE");
            param.setSuggestedValues(asList("MultiEcho", "FSE", "OneShotFSE"));
            return param;
        }
    },

    SLICE_REFOCUSING_GRADIENT_RATIO("SLICE_REFOCUSING_GRADIENT_RATIO") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("SLICE_REFOCUSING_GRADIENT_RATIO");
            param.setDisplayedName("Gradient Ratio Prephasing Slice");
            param.setDescription("Ratio of the slice refocusing gradient");
            param.setGroup(EnumGroup.Gradient);
            param.setCategory(Category.Acquisition);
            param.setUuid("e2237934-f341-435d-81a2-79f0e93a70af");
            param.setNumberEnum(NumberEnum.Double);
            param.setMinValue(-100.0);
            param.setMaxValue(100.0);
            param.setValue(0.5);
            param.setDefaultValue(0.5);
            return param;
        }
    },

    SLICE_THICKNESS("SLICE_THICKNESS") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("SLICE_THICKNESS");
            param.setDisplayedName("Slice Thickness");
            param.setDescription("Slice thickness");
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setUuid("0e9d9151-f525-4328-a338-003174e30b81");
            param.setNumberEnum(NumberEnum.Length);
            param.setMinValue(5.0E-5);
            param.setMaxValue(1.7976931348623157E308);
            param.setValue(7.999999999999999E-4);
            param.setDefaultValue(0.005);
            return param;
        }
    },

    SLICE_THICKNESS_180_WIDER("SLICE_THICKNESS_180_WIDER") {
        public Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("SLICE_THICKNESS_180_WIDER");
            param.setDisplayedName("Slice Thickness 180 Wider");
            param.setDescription("Enable to slightly increase the slice thickness for the 180 RF pulse (Checked)");
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setUuid("9c9ea9bb-6d82-45a8-9066-aa12da749015");
            param.setValue(false);
            param.setDefaultValue(false);
            return param;
        }
    },

    SOFTWARE_VERSION("SOFTWARE_VERSION") {
        public Param build() {
            TextParam param = new TextParam();
            param.setName("SOFTWARE_VERSION");
            param.setDisplayedName("Software Version");
            param.setDescription("Info: Version of the software");
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Miscellaneous);
            param.setUuid("396e81ce-a7d1-44cb-a53b-890d18c69519");
            param.setValue("1.0199");
            param.setDefaultValue("Software version");
            return param;
        }
    },

    SPACING_BETWEEN_SLICE("SPACING_BETWEEN_SLICE") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("SPACING_BETWEEN_SLICE");
            param.setDisplayedName("Slice Spacing");
            param.setDescription("Gap Between Slice");
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setUuid("8202c321-e143-40e9-9ce8-b9d81d283ace");
            param.setNumberEnum(NumberEnum.Length);
            param.setMinValue(0.0);
            param.setMaxValue(1.7976931348623157E308);
            param.setValue(9.999999999999999E-5);
            param.setDefaultValue(5.0);
            return param;
        }
    },

    SPECTRAL_WIDTH("SPECTRAL_WIDTH") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("SPECTRAL_WIDTH");
            param.setDisplayedName("BW");
            param.setDescription("Receiver bandwidth (SW, BW, bandwidth )");
            param.setGroup(EnumGroup.Reception);
            param.setCategory(Category.Acquisition);
            param.setUuid("55befc5b-60a3-4ec1-8e06-b3a5d822a6f3");
            param.setNumberEnum(NumberEnum.SW);
            param.setMinValue(0.0);
            param.setMaxValue(1.0E8);
            param.setValue(45002.8801843318);
            param.setDefaultValue(12500.0);
            return param;
        }
    },

    SPECTRAL_WIDTH_OPT("SPECTRAL_WIDTH_OPT") {
        public Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("SPECTRAL_WIDTH_OPT");
            param.setDisplayedName("BW Option");
            param.setDescription("Use BW to calculate BW per pixel (Check) / Use  BW per pixel to calculate BW (Uncheck)");
            param.setGroup(EnumGroup.Reception);
            param.setCategory(Category.Acquisition);
            param.setUuid("b796c363-692b-47ec-9562-2d03f83cc64f");
            param.setValue(true);
            param.setDefaultValue(false);
            return param;
        }
    },

    SPECTRAL_WIDTH_PER_PIXEL("SPECTRAL_WIDTH_PER_PIXEL") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("SPECTRAL_WIDTH_PER_PIXEL");
            param.setDisplayedName("BW/px");
            param.setDescription("Bandwidth Width per pixel in Hz / Px");
            param.setGroup(EnumGroup.Delay);
            param.setCategory(Category.Acquisition);
            param.setUuid("81512c5d-1669-4d4c-ac00-3d460f314c7e");
            param.setNumberEnum(NumberEnum.FidRes);
            param.setMinValue(0.0);
            param.setMaxValue(1.7976931348623157E308);
            param.setValue(175.7925007200461);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    SQUARE_PIXEL("SQUARE_PIXEL") {
        public Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("SQUARE_PIXEL");
            param.setDisplayedName("Square Pixel");
            param.setDescription("Enable to have same pixel dimension in frequency and phase encoding direction, this will adjust Phase FOV");
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setUuid("93a55523-065b-4b66-bf8b-6e84cdd29fb8");
            param.setValue(false);
            param.setDefaultValue(false);
            return param;
        }
    },

    STATION_NAME("STATION_NAME") {
        public Param build() {
            TextParam param = new TextParam();
            param.setName("STATION_NAME");
            param.setDisplayedName("Station Name");
            param.setDescription("Info: Station name");
            param.setLockedToDefault(true);
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Miscellaneous);
            param.setUuid("3b70379c-c394-4244-8de6-0b90d2856cca");
            param.setValue("Station name");
            param.setDefaultValue("Station name");
            return param;
        }
    },

    SUBJECT_POSITION("SUBJECT_POSITION") {
        public Param build() {
            TextParam param = new TextParam();
            param.setName("SUBJECT_POSITION");
            param.setDisplayedName("Subject Position");
            param.setDescription("Subject position relative to the magnet");
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setUuid("5b26baeb-edeb-4a63-8510-8bfbd3c163dd");
            param.setValue("HeadFirstProne");
            param.setDefaultValue("FeetFirstProne");
            param.setSuggestedValues(asList("HeadFirstProne", "HeadFirstSupine", "FeetFirstProne", "FeetFirstSupine"));
            param.setRestrictedToSuggested(true);
            return param;
        }
    },

    SWITCH_READ_PHASE("SWITCH_READ_PHASE") {
        public Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("SWITCH_READ_PHASE");
            param.setDisplayedName("Switch Read/Phase");
            param.setDescription("Enable to switch the read and phase encoding gradient");
            param.setGroup(EnumGroup.Gradient);
            param.setCategory(Category.Acquisition);
            param.setUuid("9d2e14fa-83a3-490a-a011-6c13d0a52e78");
            param.setValue(false);
            param.setDefaultValue(false);
            return param;
        }
    },

    TOOL_FSE_TRAIN_1D("TOOL_FSE_TRAIN_1D") {
        public Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("TOOL_FSE_TRAIN_1D");
            param.setDisplayedName("TOOL_FSE_TRAIN_1D");
            param.setDescription("Debug tool to visualize the entire Train");
            param.setLocked(true);
            param.setCategory(Category.Acquisition);
            param.setUuid("c27cab2d-b099-4199-90e0-41832e047e60");
            param.setValue(false);
            param.setDefaultValue(false);
            return param;
        }
    },

    TRANSFORM_PLUGIN("TRANSFORM_PLUGIN") {
        public Param build() {
            TextParam param = new TextParam();
            param.setName("TRANSFORM_PLUGIN");
            param.setDisplayedName("Transform Plugin");
            param.setDescription("Transform the acquisition space to the k-space");
            param.setGroup(EnumGroup.Scan);
            param.setCategory(Category.Acquisition);
            param.setUuid("7481a704-fcc1-4e1d-a7a8-2271065f6cb2");
            param.setValue("Centered2DRot");
            param.setDefaultValue("Centered2DRot");
            param.setSuggestedValues(asList("Centered2DRot", "Bordered2D", "Sequential4D", "Sequential2D", "FSE_TRAIN_1D", "Sequential2DInterleaved"));
            return param;
        }
    },

    TRANSMIT_FREQ_1("TRANSMIT_FREQ_1") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("TRANSMIT_FREQ_1");
            param.setDisplayedName("Transmit Freq 1");
            param.setDescription("Transmit frequency of the first sequence channel");
            param.setLocked(true);
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setUuid("2b22b785-25cb-4a72-a298-4f57fd0971c8");
            param.setNumberEnum(NumberEnum.Frequency);
            param.setMinValue(0.0);
            param.setMaxValue(3.0E9);
            param.setValue(1.28E8);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    TRANSMIT_FREQ_2("TRANSMIT_FREQ_2") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("TRANSMIT_FREQ_2");
            param.setDisplayedName("Transmit Freq 2");
            param.setDescription("Transmit frequency of the second sequence channel");
            param.setLocked(true);
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setUuid("2d2fe821-0659-43c1-bd35-29e034c02569");
            param.setNumberEnum(NumberEnum.Frequency);
            param.setMinValue(0.0);
            param.setMaxValue(3.0E9);
            param.setValue(1.28E8);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    TRANSMIT_FREQ_3("TRANSMIT_FREQ_3") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("TRANSMIT_FREQ_3");
            param.setDisplayedName("Transmit Freq 3");
            param.setDescription("Transmit frequency of the third sequence channel");
            param.setLocked(true);
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setUuid("08e39df2-b834-4747-839b-f6fe7032c9b5");
            param.setNumberEnum(NumberEnum.Frequency);
            param.setMinValue(0.0);
            param.setMaxValue(3.0E9);
            param.setValue(1.28E8);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    TRANSMIT_FREQ_4("TRANSMIT_FREQ_4") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("TRANSMIT_FREQ_4");
            param.setDisplayedName("Transmit Freq 4");
            param.setDescription("Transmit frequency of the fourth sequence channel");
            param.setLocked(true);
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setUuid("76e41faf-ab0f-4045-ada8-4acb91c1ebf3");
            param.setNumberEnum(NumberEnum.Frequency);
            param.setMinValue(0.0);
            param.setMaxValue(3.0E9);
            param.setValue(1.28E8);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    TRIGGER_CHANEL("TRIGGER_CHANEL") {
        public Param build() {
            TextParam param = new TextParam();
            param.setName("TRIGGER_CHANEL");
            param.setDisplayedName("Trigger Channel");
            param.setDescription("Choose the channels that will be used to synchronise the acquisition with an external signal");
            param.setGroup(EnumGroup.Delay);
            param.setCategory(Category.Acquisition);
            param.setUuid("e21a6192-baa4-4a29-bf3b-67fb02df2d4c");
            param.setValue("Ext1_XOR_Ext2");
            param.setDefaultValue("Ext1_XOR_Ext2");
            param.setSuggestedValues(asList("Ext1", "Ext2", "Ext1_AND_Ext2", "Ext1_XOR_Ext2"));
            param.setRestrictedToSuggested(true);
            return param;
        }
    },

    TRIGGER_EXTERNAL("TRIGGER_EXTERNAL") {
        public Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("TRIGGER_EXTERNAL");
            param.setDisplayedName("Trigger External");
            param.setDescription("Enable to synchronize the acquisition with external trigger");
            param.setGroup(EnumGroup.Delay);
            param.setCategory(Category.Acquisition);
            param.setUuid("dc61db74-f795-487b-9687-fc060a0788b0");
            param.setValue(false);
            param.setDefaultValue(false);
            return param;
        }
    },

    TRIGGER_TIME("TRIGGER_TIME") {
        public Param build() {
            ListNumberParam param = new ListNumberParam();
            param.setName("TRIGGER_TIME");
            param.setDisplayedName("Trigger Delay");
            param.setDescription("Time to wait between reception of the trigger signal and sequence start");
            param.setGroup(EnumGroup.Delay);
            param.setCategory(Category.Acquisition);
            param.setUuid("8fce7c93-a109-41b7-995d-7aea8437eb59");
            param.setMinValue(0.0);
            param.setMaxValue(1.0E9);
            param.setNumberEnum(NumberEnum.Time);
            param.setValue(asListNumber(0.0));
            param.setDefaultValue(asListNumber(0.01));
            return param;
        }
    },

    TX_AMP_180("TX_AMP_180") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("TX_AMP_180");
            param.setDisplayedName("TX 180 Amplitude");
            param.setDescription("Info:180 RF pulse amplitude");
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setUuid("f7574528-1e88-4be6-81f7-1a568266b375");
            param.setNumberEnum(NumberEnum.TxAmp);
            param.setMinValue(0.0);
            param.setMaxValue(100.0);
            param.setValue(73.26663234331004);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    TX_AMP_90("TX_AMP_90") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("TX_AMP_90");
            param.setDisplayedName("TX 90 Amplitude");
            param.setDescription("Info: 90 RF pulse amplitude");
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setUuid("8fd48a7b-a586-409b-8123-bddf1f6cfa06");
            param.setNumberEnum(NumberEnum.TxAmp);
            param.setMinValue(0.0);
            param.setMaxValue(100.0);
            param.setValue(36.99733371871756);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    TX_AMP_ATT_AUTO("TX_AMP_ATT_AUTO") {
        public Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("TX_AMP_ATT_AUTO");
            param.setDisplayedName("TX Att/Amp Auto");
            param.setDescription("Enable to automatically set attenuation and amplitude for the RF according to the calibration");
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setUuid("a63f5b86-8cdc-4b99-ba23-ea72b83ed27a");
            param.setValue(true);
            param.setDefaultValue(true);
            return param;
        }
    },

    TX_ATT("TX_ATT") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("TX_ATT");
            param.setDisplayedName("TX Attenuation");
            param.setDescription("RF pulse attenuation");
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setUuid("cf40815b-90b1-4836-8e8c-d69fcd1875a1");
            param.setNumberEnum(NumberEnum.TxAtt);
            param.setMinValue(0);
            param.setMaxValue(63);
            param.setValue(14);
            param.setDefaultValue(36);
            return param;
        }
    },

    TX_BANDWIDTH_FACTOR("TX_BANDWIDTH_FACTOR") {
        public Param build() {
            ListNumberParam param = new ListNumberParam();
            param.setName("TX_BANDWIDTH_FACTOR");
            param.setDisplayedName("TX BW Factor");
            param.setDescription("Bandwidth factor of the RF pulse for 4 shapes: 1-HARD, 2-GAUSSIAN, 3-SINC3 and 4-SINC5");
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setUuid("db1a851b-6fe4-4abd-b310-ed1a4c7e01e1");
            param.setMinValue(0.0);
            param.setMaxValue(1.7976931348623157E308);
            param.setNumberEnum(NumberEnum.Double);
            param.setValue(asListNumber(0.75, 1.35, 2.55, 4.25));
            param.setDefaultValue(asListNumber(0.8, 1.45, 2.55, 3.9));
            return param;
        }
    },

    TX_BANDWIDTH_FACTOR_3D("TX_BANDWIDTH_FACTOR_3D") {
        public Param build() {
            ListNumberParam param = new ListNumberParam();
            param.setName("TX_BANDWIDTH_FACTOR_3D");
            param.setDisplayedName("TX BW Factor 3D");
            param.setDescription("Bandwidth factor 3D of the RF pulse for 4 shapes: 1-HARD, 2-GAUSSIAN, 3-SINC3 and 4-SINC5");
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setUuid("9811b2dd-1494-4021-be5e-65a5503b5a9d");
            param.setMinValue(-1.7976931348623157E308);
            param.setMaxValue(1.7976931348623157E308);
            param.setNumberEnum(NumberEnum.Double);
            param.setValue(asListNumber(1.9, 2.7, 5.1, 8.5));
            param.setDefaultValue(asListNumber(1.1, 3.2, 5.0, 7.3));
            return param;
        }
    },

    TX_LENGTH_180("TX_LENGTH_180") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("TX_LENGTH_180");
            param.setDisplayedName("TX 180 Length");
            param.setDescription("180 RF pulse duration");
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setUuid("427011fd-ba15-4b43-ba5d-1ad11fc060cd");
            param.setNumberEnum(NumberEnum.Time);
            param.setMinValue(0.0);
            param.setMaxValue(1.0E9);
            param.setValue(0.001);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    TX_LENGTH_90("TX_LENGTH_90") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("TX_LENGTH_90");
            param.setDisplayedName("TX 90 Length");
            param.setDescription("90 RF pulse duration");
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setUuid("4ab8d2c2-3f18-46c0-a844-0cb023cd478b");
            param.setNumberEnum(NumberEnum.Time);
            param.setMinValue(0.0);
            param.setMaxValue(1.0E9);
            param.setValue(0.001);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    TX_ROUTE("TX_ROUTE") {
        public Param build() {
            ListNumberParam param = new ListNumberParam();
            param.setName("TX_ROUTE");
            param.setDisplayedName("TX Route");
            param.setDescription("Info: Physical transmit channel");
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setUuid("e75ff917-22b7-4a64-b7c7-e219f6b89b03");
            param.setMinValue(-2147483648);
            param.setMaxValue(2147483647);
            param.setNumberEnum(NumberEnum.Integer);
            param.setValue(asListNumber(0));
            return param;
        }
    },

    TX_SHAPE_180("TX_SHAPE_180") {
        public Param build() {
            TextParam param = new TextParam();
            param.setName("TX_SHAPE_180");
            param.setDisplayedName("TX 180 Shape");
            param.setDescription("180 RF pulse shape");
            param.setCategory(Category.Acquisition);
            param.setUuid("e9f77ce3-cf20-4526-9753-b2ba9edcdcf3");
            param.setValue("SINC3");
            param.setDefaultValue("HARD");
            param.setSuggestedValues(asList("HARD", "GAUSSIAN", "SINC3", "SINC5", "SLR_8_5152", "SLR_4_2576"));
            return param;
        }
    },

    TX_SHAPE_90("TX_SHAPE_90") {
        public Param build() {
            TextParam param = new TextParam();
            param.setName("TX_SHAPE_90");
            param.setDisplayedName("TX 90 Shape");
            param.setDescription("90 RF pulse shape");
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setUuid("912d4871-63c8-456e-b27d-52a8850e2294");
            param.setValue("SINC3");
            param.setDefaultValue("HARD");
            param.setSuggestedValues(asList("HARD", "GAUSSIAN", "SINC3", "SINC5", "SLR_8_5152", "SLR_4_2576"));
            return param;
        }
    },

    USER_MATRIX_DIMENSION_1D("USER_MATRIX_DIMENSION_1D") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("USER_MATRIX_DIMENSION_1D");
            param.setDisplayedName("Matrix Size 1D Read");
            param.setDescription("Image size in readout direction (user matrix dimension 1D)");
            param.setGroup(EnumGroup.User);
            param.setCategory(Category.Acquisition);
            param.setUuid("1f8e3cbb-4313-4da7-9880-703c5196a87f");
            param.setNumberEnum(NumberEnum.Scan);
            param.setMinValue(1);
            param.setMaxValue(65536);
            param.setValue(256);
            param.setDefaultValue(1);
            return param;
        }
    },

    USER_MATRIX_DIMENSION_2D("USER_MATRIX_DIMENSION_2D") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("USER_MATRIX_DIMENSION_2D");
            param.setDisplayedName("Matrix Size 2D Phase");
            param.setDescription("Image size in phase encoding direction  (user matrix dimension 2D)");
            param.setGroup(EnumGroup.User);
            param.setCategory(Category.Acquisition);
            param.setUuid("4706a6c2-3dbd-435c-933d-fef79626f748");
            param.setNumberEnum(NumberEnum.Scan);
            param.setMinValue(1);
            param.setMaxValue(65536);
            param.setValue(256);
            param.setDefaultValue(1);
            return param;
        }
    },

    USER_MATRIX_DIMENSION_3D("USER_MATRIX_DIMENSION_3D") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("USER_MATRIX_DIMENSION_3D");
            param.setDisplayedName("Matrix Size 3D / No. Slices");
            param.setDescription("Image size in the third dimension: slice or 3D PE  (user matrix dimension 3D)");
            param.setGroup(EnumGroup.User);
            param.setCategory(Category.Acquisition);
            param.setUuid("2c53f185-ffc9-458e-b75f-3535dca1fd9d");
            param.setNumberEnum(NumberEnum.Scan);
            param.setMinValue(1);
            param.setMaxValue(65536);
            param.setValue(16);
            param.setDefaultValue(1);
            return param;
        }
    },

    USER_MATRIX_DIMENSION_4D("USER_MATRIX_DIMENSION_4D") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("USER_MATRIX_DIMENSION_4D");
            param.setDisplayedName("No. Data Sets");
            param.setDescription("Info: Number of image data set (user maxtrix 4D)");
            param.setGroup(EnumGroup.User);
            param.setCategory(Category.Acquisition);
            param.setUuid("a6d4cee3-cf9e-4d34-9eed-e28bfe273748");
            param.setNumberEnum(NumberEnum.Scan);
            param.setMinValue(1);
            param.setMaxValue(65536);
            param.setValue(1);
            param.setDefaultValue(1);
            return param;
        }
    },

    USER_PARTIAL_PHASE("USER_PARTIAL_PHASE") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("USER_PARTIAL_PHASE");
            param.setDisplayedName("Zero-filling Phase");
            param.setDescription(" Percent of the k-space that is acquired, the rest will be completed by zeros in the phase encoding direction (zero filling interpolation)");
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setUuid("4d509c87-88c5-49d3-af08-b2ef268e4928");
            param.setNumberEnum(NumberEnum.PERCENT);
            param.setMinValue(0.1);
            param.setMaxValue(100.0);
            param.setValue(75.0);
            param.setDefaultValue(100.0);
            return param;
        }
    },

    USER_PARTIAL_SLICE("USER_PARTIAL_SLICE") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("USER_PARTIAL_SLICE");
            param.setDisplayedName("Zero-filling 3D");
            param.setDescription("Percent of the k-space that is acquired, the rest will be completed by zeros in the third encoding direction (only for 3D acquisition mode)");
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setUuid("909acc92-8049-4c46-ad4a-a4b44cab81a3");
            param.setNumberEnum(NumberEnum.PERCENT);
            param.setMinValue(0.1);
            param.setMaxValue(100.0);
            param.setValue(100.0);
            param.setDefaultValue(100.0);
            return param;
        }
    },

    USER_ZERO_FILLING_2D("USER_ZERO_FILLING_2D") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("USER_ZERO_FILLING_2D");
            param.setDisplayedName("ZERO_FILING_2D");
            param.setDescription("Percentage of zero filling");
            param.setLocked(true);
            param.setGroup(EnumGroup.Scan);
            param.setCategory(Category.Acquisition);
            param.setUuid("9384e454-7c60-4c73-ae45-3fa4d1ba372b");
            param.setNumberEnum(NumberEnum.PERCENT);
            param.setMinValue(0.0);
            param.setMaxValue(100.0);
            param.setValue(25.0);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    USER_ZERO_FILLING_3D("USER_ZERO_FILLING_3D") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("USER_ZERO_FILLING_3D");
            param.setDisplayedName("ZERO_FILING_3D");
            param.setDescription("Percentage of zero filing");
            param.setLocked(true);
            param.setGroup(EnumGroup.Scan);
            param.setCategory(Category.Acquisition);
            param.setUuid("e83b3d0a-57c0-4619-a6b3-e06a35c6145a");
            param.setNumberEnum(NumberEnum.PERCENT);
            param.setMinValue(0.0);
            param.setMaxValue(100.0);
            param.setValue(0.0);
            param.setDefaultValue(0.0);
            return param;
        }
    };

    //--

    private final String name;

    private U(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    //--

    private static List<Number> asListNumber(Number ... numbers) {
        return asList(numbers);
    }
}
