//-- generated code, will be overwritten at each recompilation

package rs2d.sequence.spinecho;

import rs2d.spinlab.tools.param.*;
import rs2d.spinlab.tools.table.*;
import rs2d.spinlab.tools.role.RoleEnum;
import rs2d.spinlab.sequenceGenerator.GeneratorParamEnum;

import static java.util.Arrays.asList;

public enum SpinEchoParams implements GeneratorParamEnum {
    ACCU_DIM {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("ACCU_DIM");
            param.setDisplayedName("ACCU_DIM.name");
            param.setDescription("ACCU_DIM.description");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Scan);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Integer);
            param.setMinValue(0);
            param.setMaxValue(3);
            param.setValue(1);
            param.setDefaultValue(1);
            return param;
        }
    },

    ACQUISITION_MATRIX_DIMENSION_1D {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("ACQUISITION_MATRIX_DIMENSION_1D");
            param.setDisplayedName("Acquisition 1D");
            param.setDescription("The acquisition size of the first dimension");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Scan);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Scan);
            param.setMinValue(0);
            param.setMaxValue(65536);
            param.setValue(256);
            param.setDefaultValue(128);
            return param;
        }
    },

    ACQUISITION_MATRIX_DIMENSION_2D {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("ACQUISITION_MATRIX_DIMENSION_2D");
            param.setDisplayedName("Acquisition 2D");
            param.setDescription("The acquisition size of the second dimension");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Scan);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Scan);
            param.setMinValue(0);
            param.setMaxValue(65536);
            param.setValue(128);
            param.setDefaultValue(128);
            return param;
        }
    },

    ACQUISITION_MATRIX_DIMENSION_3D {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("ACQUISITION_MATRIX_DIMENSION_3D");
            param.setDisplayedName("Acquisition 3D");
            param.setDescription("The acquisition size of the third dimension");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Scan);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Scan);
            param.setMinValue(0);
            param.setMaxValue(65536);
            param.setValue(12);
            param.setDefaultValue(1);
            return param;
        }
    },

    ACQUISITION_MATRIX_DIMENSION_4D {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("ACQUISITION_MATRIX_DIMENSION_4D");
            param.setDisplayedName("Acquisition 4D");
            param.setDescription("The acquisition size of the fourth dimension");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Scan);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Scan);
            param.setMinValue(0);
            param.setMaxValue(65536);
            param.setValue(1);
            param.setDefaultValue(1);
            return param;
        }
    },

    ACQUISITION_MODE {
        Param build() {
            ListTextParam param = new ListTextParam();
            param.setName("ACQUISITION_MODE");
            param.setDisplayedName("ACQUISITION_MODE");
            param.setLocked(true);
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Acquisition);
            param.setValue(asList("COMPLEX", "REAL", "REAL", "REAL"));
            param.setDefaultValue(asList("COMPLEX", "REAL", "REAL", "REAL"));
            return param;
        }
    },

    ACQUISITION_TIME_OFFSET {
        Param build() {
            ListNumberParam param = new ListNumberParam();
            param.setName("ACQUISITION_TIME_OFFSET");
            param.setDisplayedName("ACQUISITION_TIME_OFFSET");
            param.setDescription("Relative acquisition start times in Dynamic or MultiSeries scans");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Delay);
            param.setCategory(Category.Acquisition);
            param.setMinValue(0.0);
            param.setMaxValue(1.0E9);
            param.setNumberEnum(NumberEnum.Time);
            param.setValue(asList(0.0));
            param.setDefaultValue(asList(0.0));
            return param;
        }
    },

    ACQUISITION_TIME_PER_SCAN {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("ACQUISITION_TIME_PER_SCAN");
            param.setDisplayedName("Acq Time");
            param.setDescription("The acquisition time per scan");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Reception);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Time);
            param.setMinValue(0.0);
            param.setMaxValue(1.0E9);
            param.setValue(0.005111808000000001);
            param.setDefaultValue(1.0);
            return param;
        }
    },

    BASE_FREQ_1 {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("BASE_FREQ_1");
            param.setDisplayedName("Base Freq 1");
            param.setDescription("The base frequency of the first sequence channel");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Frequency);
            param.setMinValue(0.0);
            param.setMaxValue(3.0E9);
            param.setValue(1.2818100000000001E8);
            param.setDefaultValue(1.27552944E8);
            return param;
        }
    },

    BASE_FREQ_2 {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("BASE_FREQ_2");
            param.setDisplayedName("Base Freq 2");
            param.setDescription("The base frequency of the second sequence channel");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Frequency);
            param.setMinValue(0.0);
            param.setMaxValue(3.0E9);
            param.setValue(0.0);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    BASE_FREQ_3 {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("BASE_FREQ_3");
            param.setDisplayedName("Base Freq 3");
            param.setDescription("The base frequency of the third sequence channel");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Frequency);
            param.setMinValue(0.0);
            param.setMaxValue(3.0E9);
            param.setValue(0.0);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    BASE_FREQ_4 {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("BASE_FREQ_4");
            param.setDisplayedName("Base Freq 4");
            param.setDescription("The base frequency of the fourth sequence channel");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Frequency);
            param.setMinValue(0.0);
            param.setMaxValue(3.0E9);
            param.setValue(0.0);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    DATA_REPRESENTATION {
        Param build() {
            ListTextParam param = new ListTextParam();
            param.setName("DATA_REPRESENTATION");
            param.setDisplayedName("DATA_REPRESENTATION");
            param.setLocked(true);
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Miscellaneous);
            param.setValue(asList("COMPLEX", "REAL", "REAL", "REAL"));
            param.setDefaultValue(asList("COMPLEX", "REAL", "REAL", "REAL"));
            return param;
        }
    },

    DIGITAL_FILTER_REMOVED {
        Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("DIGITAL_FILTER_REMOVED");
            param.setDisplayedName("Digital filter removed");
            param.setDescription("Data shift due to the digital filter are removed");
            param.setLockedToDefault(true);
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Reception);
            param.setCategory(Category.Acquisition);
            param.setValue(true);
            param.setDefaultValue(true);
            return param;
        }
    },

    DIGITAL_FILTER_SHIFT {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("DIGITAL_FILTER_SHIFT");
            param.setDisplayedName("Digital filter shift");
            param.setDescription("Data shift due to the digital filter");
            param.setLockedToDefault(true);
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Reception);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Integer);
            param.setMinValue(-2147483648);
            param.setMaxValue(2147483647);
            param.setValue(20);
            param.setDefaultValue(20);
            return param;
        }
    },

    DUMMY_SCAN {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("DUMMY_SCAN");
            param.setDisplayedName("DS");
            param.setDescription("Dummy Scan");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Scan);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Scan);
            param.setMinValue(0);
            param.setMaxValue(65536);
            param.setValue(2);
            param.setDefaultValue(128);
            return param;
        }
    },

    DYN_NUMBER_OF_ACQUISITION {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("DYN_NUMBER_OF_ACQUISITION");
            param.setDisplayedName("DYN_NUMBER_OF_ACQUISITION");
            param.setDescription("Number of dynamic acquisition");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Scan);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Scan);
            param.setMinValue(1);
            param.setMaxValue(65536);
            param.setValue(5);
            param.setDefaultValue(1);
            return param;
        }
    },

    DYN_TIME_BTW_FRAMES {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("DYN_TIME_BTW_FRAMES");
            param.setDisplayedName("TIME_BETWEEN_FRAMES");
            param.setDescription("Time between frame for dynamic acquisitions");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Delay);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Time);
            param.setMinValue(0.0);
            param.setMaxValue(1.0E9);
            param.setValue(0.001);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    DYNAMIC_MIN_TIME {
        Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("DYNAMIC_MIN_TIME");
            param.setDisplayedName("DYNAMIC_MIN_TIME");
            param.setDescription("Execute the dynamic scan without delay between repetitions");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Delay);
            param.setCategory(Category.Acquisition);
            param.setValue(true);
            param.setDefaultValue(false);
            return param;
        }
    },

    DYNAMIC_SEQUENCE {
        Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("DYNAMIC_SEQUENCE");
            param.setDisplayedName("DYNAMIC_SEQUENCE");
            param.setDescription("Sequence used for dynamic acquisitions");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Acquisition);
            param.setValue(false);
            param.setDefaultValue(false);
            return param;
        }
    },

    ECHO_EFFECTIVE {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("ECHO_EFFECTIVE");
            param.setDisplayedName("ECHO_EFFECTIVE");
            param.setDescription("");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Scan);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Integer);
            param.setMinValue(1);
            param.setMaxValue(2147483647);
            param.setValue(4);
            param.setDefaultValue(1);
            return param;
        }
    },

    ECHO_SPACING {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("ECHO_SPACING");
            param.setDisplayedName("ECHO_SPACING");
            param.setDescription("Time between consecutive echoes when ETL is greater than 1");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Delay);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Time);
            param.setMinValue(0.0);
            param.setMaxValue(100.0);
            param.setValue(0.009000000000000001);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    ECHO_TIME {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("ECHO_TIME");
            param.setDisplayedName("TE");
            param.setDescription("The echo time ( TE ) ");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Delay);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Time);
            param.setMinValue(0.0);
            param.setMaxValue(1.0E9);
            param.setValue(0.036000000000000004);
            param.setDefaultValue(0.005);
            return param;
        }
    },

    ECHO_TIME_EFFECTIVE {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("ECHO_TIME_EFFECTIVE");
            param.setDisplayedName("EFFECTIVE_ECHO_TIME");
            param.setDescription("Effective echo time in Fast Spin Echo sequence");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Delay);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Time);
            param.setMinValue(0.001);
            param.setMaxValue(10.0);
            param.setValue(0.036000000000000004);
            param.setDefaultValue(0.01);
            return param;
        }
    },

    ECHO_TRAIN_LENGTH {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("ECHO_TRAIN_LENGTH");
            param.setDisplayedName("ETL");
            param.setDescription("The echo train lentgth");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Delay);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Scan);
            param.setMinValue(0);
            param.setMaxValue(65536);
            param.setValue(4);
            param.setDefaultValue(1);
            return param;
        }
    },

    FIELD_OF_VIEW {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("FIELD_OF_VIEW");
            param.setDisplayedName("FOV");
            param.setDescription("The field of view");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Length);
            param.setMinValue(0.001);
            param.setMaxValue(1.7976931348623157E308);
            param.setValue(0.08);
            param.setDefaultValue(0.6);
            return param;
        }
    },

    FIELD_OF_VIEW_3D {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("FIELD_OF_VIEW_3D");
            param.setDisplayedName("FIELD_OF_VIEW_3D");
            param.setDescription("Total coverage in the third dim.");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Length);
            param.setMinValue(0.0);
            param.setMaxValue(1.0);
            param.setValue(0.0142);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    FIELD_OF_VIEW_PHASE {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("FIELD_OF_VIEW_PHASE");
            param.setDisplayedName("FIELD_OF_VIEW_PHASE");
            param.setDescription("");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Length);
            param.setMinValue(0.01);
            param.setMaxValue(10.0);
            param.setValue(0.04);
            param.setDefaultValue(0.1);
            return param;
        }
    },

    FLIP_ANGLE {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("FLIP_ANGLE");
            param.setDisplayedName("Flip angle");
            param.setDescription("The flip angle for the excitation (FA)");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Angle);
            param.setMinValue(-360.0);
            param.setMaxValue(360.0);
            param.setValue(90.0);
            param.setDefaultValue(90.0);
            return param;
        }
    },

    FOV_DOUBLED {
        Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("FOV_DOUBLED");
            param.setDisplayedName("FOV_DOUBLED");
            param.setDescription("Double FOV and BW to avoid aliazing");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setValue(false);
            param.setDefaultValue(false);
            return param;
        }
    },

    FOV_EFF {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("FOV_EFF");
            param.setDisplayedName("FOV_EFF");
            param.setDescription("Show the acquierd FOV when FOV doubled");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Length);
            param.setMinValue(0.0);
            param.setMaxValue(1.7976931348623156E305);
            param.setValue(0.08);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    FOV_RATIO_PHASE {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("FOV_RATIO_PHASE");
            param.setDisplayedName("Phase FOV");
            param.setDescription("The fov ratio in the phase direction");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.PERCENT);
            param.setMinValue(0.0);
            param.setMaxValue(100.0);
            param.setValue(50.0);
            param.setDefaultValue(100.0);
            return param;
        }
    },

    FOV_SQUARE {
        Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("FOV_SQUARE");
            param.setDisplayedName("FOV_SQUARE");
            param.setDescription("Force equal FOV in frequency and phase encoding directions");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setValue(false);
            param.setDefaultValue(false);
            return param;
        }
    },

    GRADIENT_AMP_CRUSHER {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("GRADIENT_AMP_CRUSHER");
            param.setDisplayedName("GRADIENT_AMP_CRUSHER");
            param.setDescription("magnitude of the crusher gradient");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Gradient);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.PERCENT);
            param.setMinValue(0.0);
            param.setMaxValue(100.0);
            param.setValue(30.0);
            param.setDefaultValue(8.0);
            return param;
        }
    },

    GRADIENT_AMP_CRUSHER_IR {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("GRADIENT_AMP_CRUSHER_IR");
            param.setDisplayedName("GRADIENT_AMP_CRUSHER_IR");
            param.setDescription("the magnitude of the crusher gradient after the Inversion pulse");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Gradient);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.PERCENT);
            param.setMinValue(0.0);
            param.setMaxValue(100.0);
            param.setValue(12.0);
            param.setDefaultValue(8.0);
            return param;
        }
    },

    GRADIENT_AMP_SPOILER {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("GRADIENT_AMP_SPOILER");
            param.setDisplayedName("GRADIENT_AMP_SPOILER");
            param.setDescription("amplitud of the spoiler gradient");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Gradient);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.PERCENT);
            param.setMinValue(0.0);
            param.setMaxValue(100.0);
            param.setValue(20.0);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    GRADIENT_AREA_CRUSHER {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("GRADIENT_AREA_CRUSHER");
            param.setDisplayedName("GRADIENT_AREA_CRUSHER");
            param.setDescription("For info: Area of the crusher gradient!");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Gradient);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Double);
            param.setMinValue(-1.7976931348623157E308);
            param.setMaxValue(1.7976931348623157E308);
            param.setValue(5.567078717420658E-5);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    GRADIENT_CRUSHER_IR_TOP_TIME {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("GRADIENT_CRUSHER_IR_TOP_TIME");
            param.setDisplayedName("GRADIENT_CRUSHER_IR_TOP_TIME");
            param.setDescription("");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Delay);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Time);
            param.setMinValue(0.0);
            param.setMaxValue(1.0E9);
            param.setValue(3.9999999999999996E-4);
            param.setDefaultValue(3.9999999999999996E-4);
            return param;
        }
    },

    GRADIENT_CRUSHER_TOP_TIME {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("GRADIENT_CRUSHER_TOP_TIME");
            param.setDisplayedName("GRADIENT_CRUSHER_TOP_TIME");
            param.setDescription("");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Delay);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Time);
            param.setMinValue(0.0);
            param.setMaxValue(1.0E9);
            param.setValue(4.0E-4);
            param.setDefaultValue(3.9999999999999996E-4);
            return param;
        }
    },

    GRADIENT_ENABLE_CRUSHER_IR {
        Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("GRADIENT_ENABLE_CRUSHER_IR");
            param.setDisplayedName("GRADIENT_ENABLE_CRUSHER_IR");
            param.setDescription("Enable the IR gradient crusher");
            param.setRoles(new RoleEnum[] {RoleEnum.SequenceDesigner});
            param.setGroup(EnumGroup.Gradient);
            param.setCategory(Category.Acquisition);
            param.setValue(true);
            param.setDefaultValue(true);
            return param;
        }
    },

    GRADIENT_ENABLE_PHASE {
        Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("GRADIENT_ENABLE_PHASE");
            param.setDisplayedName("GRADIENT_ENABLE_PHASE");
            param.setDescription("enable the phase encoding gradient");
            param.setRoles(new RoleEnum[] {RoleEnum.SequenceDesigner});
            param.setGroup(EnumGroup.Gradient);
            param.setCategory(Category.Acquisition);
            param.setValue(true);
            param.setDefaultValue(true);
            return param;
        }
    },

    GRADIENT_ENABLE_PHASE_3D {
        Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("GRADIENT_ENABLE_PHASE_3D");
            param.setDisplayedName("GRADIENT_ENABLE_PHASE_3D");
            param.setDescription("");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Gradient);
            param.setCategory(Category.Acquisition);
            param.setValue(false);
            param.setDefaultValue(true);
            return param;
        }
    },

    GRADIENT_ENABLE_READ {
        Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("GRADIENT_ENABLE_READ");
            param.setDisplayedName("GRADIENT_ENABLE_READ");
            param.setDescription("enable the read encoding gradient");
            param.setRoles(new RoleEnum[] {RoleEnum.SequenceDesigner});
            param.setGroup(EnumGroup.Gradient);
            param.setCategory(Category.Acquisition);
            param.setValue(true);
            param.setDefaultValue(true);
            return param;
        }
    },

    GRADIENT_ENABLE_SLICE {
        Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("GRADIENT_ENABLE_SLICE");
            param.setDisplayedName("GRADIENT_ENABLE_SLICE");
            param.setDescription("enable the slice encoding gradient");
            param.setRoles(new RoleEnum[] {RoleEnum.SequenceDesigner});
            param.setGroup(EnumGroup.Gradient);
            param.setCategory(Category.Acquisition);
            param.setValue(true);
            param.setDefaultValue(true);
            return param;
        }
    },

    GRADIENT_ENABLE_SLICE_CRUSH {
        Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("GRADIENT_ENABLE_SLICE_CRUSH");
            param.setDisplayedName("GRADIENT_ENABLE_SLICE_CRUSH");
            param.setDescription("enables the slice encoding gradient crusher before & after the 180 rf pulse");
            param.setRoles(new RoleEnum[] {RoleEnum.SequenceDesigner});
            param.setGroup(EnumGroup.Gradient);
            param.setCategory(Category.Acquisition);
            param.setValue(true);
            param.setDefaultValue(true);
            return param;
        }
    },

    GRADIENT_ENABLE_SPOILER {
        Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("GRADIENT_ENABLE_SPOILER");
            param.setDisplayedName("GRADIENT_ENABLE_SPOILER");
            param.setDescription("Enable gradient spoiler on the three direction");
            param.setRoles(new RoleEnum[] {RoleEnum.SequenceDesigner});
            param.setGroup(EnumGroup.Gradient);
            param.setCategory(Category.Acquisition);
            param.setValue(true);
            param.setDefaultValue(false);
            return param;
        }
    },

    GRADIENT_PHASE_APPLICATION_TIME {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("GRADIENT_PHASE_APPLICATION_TIME");
            param.setDisplayedName("GRADIENT_PHASE_APPLICATION_TIME");
            param.setDescription("the application time of the phase encoding gradient");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Delay);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Time);
            param.setMinValue(0.0);
            param.setMaxValue(1.0);
            param.setValue(4.0E-4);
            param.setDefaultValue(5.0E-4);
            return param;
        }
    },

    GRADIENT_READ_PREPHASING_APPLICATION_TIME {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("GRADIENT_READ_PREPHASING_APPLICATION_TIME");
            param.setDisplayedName("GRADIENT_READ_PREPHASING_APPLICATION_TIME");
            param.setDescription("The gradient application time");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Delay);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Time);
            param.setMinValue(0.0);
            param.setMaxValue(1.0);
            param.setValue(3.9999999999999996E-4);
            param.setDefaultValue(5.0E-4);
            return param;
        }
    },

    GRADIENT_RISE_TIME {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("GRADIENT_RISE_TIME");
            param.setDisplayedName("GRADIENT_RISE_TIME");
            param.setDescription("The rise time of the gradient");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Delay);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Time);
            param.setMinValue(0.0);
            param.setMaxValue(1.0);
            param.setValue(1.5E-4);
            param.setDefaultValue(1.9999999999999998E-4);
            return param;
        }
    },

    HARDWARE_A0 {
        Param build() {
            ListNumberParam param = new ListNumberParam();
            param.setName("HARDWARE_A0");
            param.setDisplayedName("HARDWARE_A0");
            param.setDescription("");
            param.setLocked(true);
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setCategory(Category.Acquisition);
            param.setMinValue(-2.147483648E9);
            param.setMaxValue(2.147483647E9);
            param.setNumberEnum(NumberEnum.PERCENT);
            return param;
        }
    },

    HARDWARE_DC {
        Param build() {
            ListNumberParam param = new ListNumberParam();
            param.setName("HARDWARE_DC");
            param.setDisplayedName("HARDWARE_DC");
            param.setDescription("");
            param.setLocked(true);
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setCategory(Category.Acquisition);
            param.setMinValue(-2.147483648E9);
            param.setMaxValue(2.147483647E9);
            param.setNumberEnum(NumberEnum.PERCENT);
            return param;
        }
    },

    HARDWARE_PREEMPHASIS_A {
        Param build() {
            ListNumberParam param = new ListNumberParam();
            param.setName("HARDWARE_PREEMPHASIS_A");
            param.setDisplayedName("HARDWARE_PREEMPHASIS_A");
            param.setDescription("");
            param.setLocked(true);
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setMinValue(-2.147483648E9);
            param.setMaxValue(2.147483647E9);
            param.setNumberEnum(NumberEnum.PERCENT);
            return param;
        }
    },

    HARDWARE_PREEMPHASIS_T {
        Param build() {
            ListNumberParam param = new ListNumberParam();
            param.setName("HARDWARE_PREEMPHASIS_T");
            param.setDisplayedName("HARDWARE_PREEMPHASIS_T");
            param.setDescription("");
            param.setLocked(true);
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setMinValue(0.0);
            param.setMaxValue(1.0E9);
            param.setNumberEnum(NumberEnum.Time);
            return param;
        }
    },

    HARDWARE_SHIM {
        Param build() {
            ListNumberParam param = new ListNumberParam();
            param.setName("HARDWARE_SHIM");
            param.setDisplayedName("HARDWARE_SHIM");
            param.setDescription("");
            param.setLocked(true);
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setCategory(Category.Acquisition);
            param.setMinValue(-2.147483648E9);
            param.setMaxValue(2.147483647E9);
            param.setNumberEnum(NumberEnum.PERCENT);
            return param;
        }
    },

    HARDWARE_SHIM_LABEL {
        Param build() {
            TextParam param = new TextParam();
            param.setName("HARDWARE_SHIM_LABEL");
            param.setDisplayedName("HARDWARE_SHIM_LABEL");
            param.setDescription("");
            param.setLocked(true);
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setCategory(Category.Acquisition);
            param.setValue("NotConnected");
            param.setDefaultValue("");
            return param;
        }
    },

    IMAGE_CONTRAST {
        Param build() {
            TextParam param = new TextParam();
            param.setName("IMAGE_CONTRAST");
            param.setDisplayedName("IMAGE_CONTRAST");
            param.setDescription("Type of image contrast");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Scan);
            param.setCategory(Category.Acquisition);
            param.setValue("Custom");
            param.setDefaultValue("T1-weighted");
            param.setSuggestedValues(asList("T1-weighted", "T2-weighted", "PD-weighted", "Custom"));
            return param;
        }
    },

    IMAGE_ORIENTATION_SUBJECT {
        Param build() {
            ListNumberParam param = new ListNumberParam();
            param.setName("IMAGE_ORIENTATION_SUBJECT");
            param.setDisplayedName("IMAGE_ORIENTATION_SUBJECT");
            param.setDescription("Direction cosines of the first row and the first column with respect to the subject");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setMinValue(-1.7976931348623157E308);
            param.setMaxValue(1.7976931348623157E308);
            param.setNumberEnum(NumberEnum.Double);
            param.setValue(asList(0.0, 0.0, -1.0, 0.0, -1.0, 0.0));
            param.setDefaultValue(asList(1.0, 0.0, 0.0, 0.0, 1.0, 0.0));
            return param;
        }
    },

    IMAGE_POSITION_SUBJECT {
        Param build() {
            ListNumberParam param = new ListNumberParam();
            param.setName("IMAGE_POSITION_SUBJECT");
            param.setDisplayedName("IMAGE_POSITION_SUBJECT");
            param.setDescription("x, y, and z coordinates of the upper left hand corner of the image");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setMinValue(-1.7976931348623157E308);
            param.setMaxValue(1.7976931348623157E308);
            param.setNumberEnum(NumberEnum.Location);
            param.setValue(asList(0.0, 0.0, 0.0));
            param.setDefaultValue(asList(0.0, 0.0, 0.0));
            return param;
        }
    },

    INTERMEDIATE_FREQUENCY {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("INTERMEDIATE_FREQUENCY");
            param.setDisplayedName("INTERMEDIATE_FREQUENCY.name");
            param.setDescription("INTERMEDIATE_FREQUENCY.description");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Reception);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Frequency);
            param.setMinValue(0.0);
            param.setMaxValue(3.0E9);
            param.setValue(2.051E7);
            param.setDefaultValue(2.051E7);
            return param;
        }
    },

    INVERSION_RECOVERY {
        Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("INVERSION_RECOVERY");
            param.setDisplayedName("IR");
            param.setDescription("Use inversion recovery block before acquisition");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Acquisition);
            param.setValue(false);
            param.setDefaultValue(false);
            return param;
        }
    },

    INVERSION_TIME {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("INVERSION_TIME");
            param.setDisplayedName("INVERSION_TIME");
            param.setDescription("The inversion time");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Delay);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Time);
            param.setMinValue(0.0);
            param.setMaxValue(1.0E9);
            param.setValue(0.0);
            param.setDefaultValue(0.005);
            return param;
        }
    },

    INVERSION_TIME_MULTI {
        Param build() {
            ListNumberParam param = new ListNumberParam();
            param.setName("INVERSION_TIME_MULTI");
            param.setDisplayedName("INVERSION_TIME");
            param.setDescription("List of the Inversion Recovery time   ( TI ) ");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Delay);
            param.setCategory(Category.Acquisition);
            param.setMinValue(0.0);
            param.setMaxValue(1.0E9);
            param.setNumberEnum(NumberEnum.Time);
            param.setValue(asList(0.0));
            return param;
        }
    },

    KS_CENTER_MODE {
        Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("KS_CENTER_MODE");
            param.setDisplayedName("KS_CENTER_MODE");
            param.setDescription("Turn off the PE gradient and acquieres two single scan only");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setCategory(Category.Acquisition);
            param.setValue(false);
            param.setDefaultValue(false);
            return param;
        }
    },

    KS_CENTERED {
        Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("KS_CENTERED");
            param.setDisplayedName("KS_CENTERED");
            param.setDescription("Center the k-space around k0(Checked) or go through k0 (Unchecked)");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Gradient);
            param.setCategory(Category.Acquisition);
            param.setValue(false);
            param.setDefaultValue(false);
            return param;
        }
    },

    LAST_PUT {
        Param build() {
            ListNumberParam param = new ListNumberParam();
            param.setName("LAST_PUT");
            param.setDisplayedName("LAST_PUT");
            param.setDescription("LAST_PUT.description");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Reception);
            param.setCategory(Category.Acquisition);
            param.setMinValue(-2147483648);
            param.setMaxValue(2147483647);
            param.setNumberEnum(NumberEnum.Integer);
            param.setValue(asList(0, 0, 0));
            param.setDefaultValue(asList(0, 0, 0));
            return param;
        }
    },

    LIM_PD_WEIGHTED {
        Param build() {
            ListNumberParam param = new ListNumberParam();
            param.setName("LIM_PD_WEIGHTED");
            param.setDisplayedName("Lim_PD_Weighted");
            param.setDescription("TE/TR limits to get Proton-Density Weighted imaging: TE (sup limit) / TR(inf limit)");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Delay);
            param.setCategory(Category.Acquisition);
            param.setMinValue(9.999999999999999E-5);
            param.setMaxValue(60.0);
            param.setNumberEnum(NumberEnum.Time);
            param.setValue(asList(0.02, 2.0));
            param.setDefaultValue(asList(0.02, 2.0));
            return param;
        }
    },

    LIM_T1_WEIGHTED {
        Param build() {
            ListNumberParam param = new ListNumberParam();
            param.setName("LIM_T1_WEIGHTED");
            param.setDisplayedName("LIM_T1_WEIGHTED");
            param.setDescription("TE/TR limits to get T1 Weighted imaging: TE (sup. limit) / TR(sup. limit)");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Delay);
            param.setCategory(Category.Acquisition);
            param.setMinValue(9.999999999999999E-5);
            param.setMaxValue(100.0);
            param.setNumberEnum(NumberEnum.Time);
            param.setValue(asList(0.02, 0.4, 0.7000000000000001));
            param.setDefaultValue(asList(0.02, 0.7));
            return param;
        }
    },

    LIM_T2_WEIGHTED {
        Param build() {
            ListNumberParam param = new ListNumberParam();
            param.setName("LIM_T2_WEIGHTED");
            param.setDisplayedName("LIM_T2_WEIGHTED");
            param.setDescription("TE/TR limits to get T2 Weighted imaging: TE (inf limit) / TR(inf limit)");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Delay);
            param.setCategory(Category.Acquisition);
            param.setMinValue(0.0);
            param.setMaxValue(1.0E9);
            param.setNumberEnum(NumberEnum.Time);
            param.setValue(asList(0.08, 2.0));
            param.setDefaultValue(asList(0.08, 2.0));
            return param;
        }
    },

    MAGNETIC_FIELD_STRENGTH {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("MAGNETIC_FIELD_STRENGTH");
            param.setDisplayedName("B0");
            param.setDescription("The magnetic field tregth");
            param.setLockedToDefault(true);
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Field);
            param.setMinValue(0.0);
            param.setMaxValue(100.0);
            param.setValue(3.0);
            param.setDefaultValue(3.0);
            return param;
        }
    },

    MANUFACTURER {
        Param build() {
            TextParam param = new TextParam();
            param.setName("MANUFACTURER");
            param.setDisplayedName("MANUFACTURER");
            param.setDescription("Manufacturer");
            param.setLockedToDefault(true);
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Miscellaneous);
            param.setValue("Manufacturer");
            param.setDefaultValue("Manufacturer");
            return param;
        }
    },

    MIN_RISE_TIME_FACTOR {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("MIN_RISE_TIME_FACTOR");
            param.setDisplayedName("MIN_RISE_TIME_FACTOR");
            param.setDescription("Safety parameter applied on maximum gradient slew rate. Fastest at 100%");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Gradient);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.PERCENT);
            param.setMinValue(0.0);
            param.setMaxValue(100.0);
            param.setValue(90.0);
            param.setDefaultValue(90.0);
            return param;
        }
    },

    MODALITY {
        Param build() {
            TextParam param = new TextParam();
            param.setName("MODALITY");
            param.setDisplayedName("Modality");
            param.setDescription("The modality for the acquisition");
            param.setLockedToDefault(true);
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Acquisition);
            param.setValue("MRI");
            param.setDefaultValue("MRI");
            param.setSuggestedValues(asList("NMR", "MRI", "DEFAULT"));
            param.setRestrictedToSuggested(true);
            return param;
        }
    },

    MODEL_NAME {
        Param build() {
            TextParam param = new TextParam();
            param.setName("MODEL_NAME");
            param.setDisplayedName("MODEL_NAME");
            param.setDescription("Model name");
            param.setLockedToDefault(true);
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Miscellaneous);
            param.setValue("Model name");
            param.setDefaultValue("Model name");
            return param;
        }
    },

    MULTI_PLANAR_EXCITATION {
        Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("MULTI_PLANAR_EXCITATION");
            param.setDisplayedName("Multi planar");
            param.setDescription("Multi planar excitation");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Acquisition);
            param.setValue(true);
            param.setDefaultValue(false);
            return param;
        }
    },

    MULTISERIES_PARAMETER_NAME {
        Param build() {
            TextParam param = new TextParam();
            param.setName("MULTISERIES_PARAMETER_NAME");
            param.setDisplayedName("MULTISERIES_PARAMETER_NAME");
            param.setDescription("Name of MultiSeries Parameter");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Acquisition);
            param.setValue("");
            param.setDefaultValue("");
            param.setSuggestedValues(asList("TE", "TI", "B-VALUE", "TRIGGER DELAY"));
            return param;
        }
    },

    MULTISERIES_PARAMETER_VALUE {
        Param build() {
            ListNumberParam param = new ListNumberParam();
            param.setName("MULTISERIES_PARAMETER_VALUE");
            param.setDisplayedName("MULTISERIES_PARAMETER_VALUE");
            param.setDescription("Values of MultiSeries Parameter");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Acquisition);
            param.setMinValue(-1.7976931348623157E308);
            param.setMaxValue(1.7976931348623157E308);
            param.setNumberEnum(NumberEnum.Double);
            param.setDefaultValue(asList(0.0));
            return param;
        }
    },

    NUCLEUS_1 {
        Param build() {
            TextParam param = new TextParam();
            param.setName("NUCLEUS_1");
            param.setDisplayedName("Nucleus 1");
            param.setDescription("The nucleus used for the first sequence channel");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Acquisition);
            param.setValue("1H");
            param.setDefaultValue("1H");
            param.setSuggestedValues(asList("Other", "Y", "X", "3H", "1H", "19F", "3He", "205Tl", "203Tl", "31P", "7Li", "119Sn", "117Sn", "87Rb", "115Sn", "11B", "125Te", "141Pr", "71Ga", "65Cu", "129Xe", "81Br", "63Cu", "23Na", "51V", "123Te", "27Al", "13C", "79Br", "151Eu", "55Mn", "93Nb", "45Sc", "159Tb", "69Ga", "121Sb", "59Co", "187Re", "185Re", "99Tc", "113Cd", "115In", "113In", "195Pt", "165Ho", "111Cd", "207Pb", "127I", "29Si", "77Se", "199Hg", "171Yb", "75As", "209Bi", "2H", "6Li", "139La", "9Be", "17O", "138La", "133Cs", "123Sb", "181Ta", "175Lu", "137Ba", "153Eu", "10B", "15N", "50V", "135Ba", "35Cl", "85Rb", "91Zr", "61Ni", "169Tm", "131Xe", "37Cl", "176Lu", "21Ne", "189Os", "33S", "14N", "43Ca", "97Mo", "201Hg", "95Mo", "67Zn", "25Mg", "40K", "53Cr", "49Ti", "47Ti", "143Nd", "101Ru", "89Y", "173Yb", "163Dy", "39K", "109Ag", "99Ru", "105Pd", "87Sr", "147Sm", "183W", "107Ag", "157Gd", "177Hf", "83Kr", "73Ge", "149Sm", "161Dy", "145Nd", "57Fe", "103Rh", "155Gd", "167Er", "41K", "179Hf", "187Os", "193Ir", "235U", "197Au", "191Ir"));
            param.setRestrictedToSuggested(true);
            return param;
        }
    },

    NUCLEUS_2 {
        Param build() {
            TextParam param = new TextParam();
            param.setName("NUCLEUS_2");
            param.setDisplayedName("Nucleus 2");
            param.setDescription("The nucleus used for the second sequence channel");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Acquisition);
            param.setValue("1H");
            param.setDefaultValue("1H");
            param.setSuggestedValues(asList("Other", "Y", "X", "3H", "1H", "19F", "3He", "205Tl", "203Tl", "31P", "7Li", "119Sn", "117Sn", "87Rb", "115Sn", "11B", "125Te", "141Pr", "71Ga", "65Cu", "129Xe", "81Br", "63Cu", "23Na", "51V", "123Te", "27Al", "13C", "79Br", "151Eu", "55Mn", "93Nb", "45Sc", "159Tb", "69Ga", "121Sb", "59Co", "187Re", "185Re", "99Tc", "113Cd", "115In", "113In", "195Pt", "165Ho", "111Cd", "207Pb", "127I", "29Si", "77Se", "199Hg", "171Yb", "75As", "209Bi", "2H", "6Li", "139La", "9Be", "17O", "138La", "133Cs", "123Sb", "181Ta", "175Lu", "137Ba", "153Eu", "10B", "15N", "50V", "135Ba", "35Cl", "85Rb", "91Zr", "61Ni", "169Tm", "131Xe", "37Cl", "176Lu", "21Ne", "189Os", "33S", "14N", "43Ca", "97Mo", "201Hg", "95Mo", "67Zn", "25Mg", "40K", "53Cr", "49Ti", "47Ti", "143Nd", "101Ru", "89Y", "173Yb", "163Dy", "39K", "109Ag", "99Ru", "105Pd", "87Sr", "147Sm", "183W", "107Ag", "157Gd", "177Hf", "83Kr", "73Ge", "149Sm", "161Dy", "145Nd", "57Fe", "103Rh", "155Gd", "167Er", "41K", "179Hf", "187Os", "193Ir", "235U", "197Au", "191Ir"));
            param.setRestrictedToSuggested(true);
            return param;
        }
    },

    NUCLEUS_3 {
        Param build() {
            TextParam param = new TextParam();
            param.setName("NUCLEUS_3");
            param.setDisplayedName("Nucleus 3");
            param.setDescription("The nucleus used for the third sequence channel");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Acquisition);
            param.setValue("1H");
            param.setDefaultValue("1H");
            param.setSuggestedValues(asList("Other", "Y", "X", "3H", "1H", "19F", "3He", "205Tl", "203Tl", "31P", "7Li", "119Sn", "117Sn", "87Rb", "115Sn", "11B", "125Te", "141Pr", "71Ga", "65Cu", "129Xe", "81Br", "63Cu", "23Na", "51V", "123Te", "27Al", "13C", "79Br", "151Eu", "55Mn", "93Nb", "45Sc", "159Tb", "69Ga", "121Sb", "59Co", "187Re", "185Re", "99Tc", "113Cd", "115In", "113In", "195Pt", "165Ho", "111Cd", "207Pb", "127I", "29Si", "77Se", "199Hg", "171Yb", "75As", "209Bi", "2H", "6Li", "139La", "9Be", "17O", "138La", "133Cs", "123Sb", "181Ta", "175Lu", "137Ba", "153Eu", "10B", "15N", "50V", "135Ba", "35Cl", "85Rb", "91Zr", "61Ni", "169Tm", "131Xe", "37Cl", "176Lu", "21Ne", "189Os", "33S", "14N", "43Ca", "97Mo", "201Hg", "95Mo", "67Zn", "25Mg", "40K", "53Cr", "49Ti", "47Ti", "143Nd", "101Ru", "89Y", "173Yb", "163Dy", "39K", "109Ag", "99Ru", "105Pd", "87Sr", "147Sm", "183W", "107Ag", "157Gd", "177Hf", "83Kr", "73Ge", "149Sm", "161Dy", "145Nd", "57Fe", "103Rh", "155Gd", "167Er", "41K", "179Hf", "187Os", "193Ir", "235U", "197Au", "191Ir"));
            param.setRestrictedToSuggested(true);
            return param;
        }
    },

    NUCLEUS_4 {
        Param build() {
            TextParam param = new TextParam();
            param.setName("NUCLEUS_4");
            param.setDisplayedName("Nucleus 4");
            param.setDescription("The nucleus used for the fourth sequence channel");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Acquisition);
            param.setValue("1H");
            param.setDefaultValue("1H");
            param.setSuggestedValues(asList("Other", "Y", "X", "3H", "1H", "19F", "3He", "205Tl", "203Tl", "31P", "7Li", "119Sn", "117Sn", "87Rb", "115Sn", "11B", "125Te", "141Pr", "71Ga", "65Cu", "129Xe", "81Br", "63Cu", "23Na", "51V", "123Te", "27Al", "13C", "79Br", "151Eu", "55Mn", "93Nb", "45Sc", "159Tb", "69Ga", "121Sb", "59Co", "187Re", "185Re", "99Tc", "113Cd", "115In", "113In", "195Pt", "165Ho", "111Cd", "207Pb", "127I", "29Si", "77Se", "199Hg", "171Yb", "75As", "209Bi", "2H", "6Li", "139La", "9Be", "17O", "138La", "133Cs", "123Sb", "181Ta", "175Lu", "137Ba", "153Eu", "10B", "15N", "50V", "135Ba", "35Cl", "85Rb", "91Zr", "61Ni", "169Tm", "131Xe", "37Cl", "176Lu", "21Ne", "189Os", "33S", "14N", "43Ca", "97Mo", "201Hg", "95Mo", "67Zn", "25Mg", "40K", "53Cr", "49Ti", "47Ti", "143Nd", "101Ru", "89Y", "173Yb", "163Dy", "39K", "109Ag", "99Ru", "105Pd", "87Sr", "147Sm", "183W", "107Ag", "157Gd", "177Hf", "83Kr", "73Ge", "149Sm", "161Dy", "145Nd", "57Fe", "103Rh", "155Gd", "167Er", "41K", "179Hf", "187Os", "193Ir", "235U", "197Au", "191Ir"));
            param.setRestrictedToSuggested(true);
            return param;
        }
    },

    NUMBER_OF_AVERAGES {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("NUMBER_OF_AVERAGES");
            param.setDisplayedName("NEX");
            param.setDescription("The number of average");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Scan);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Scan);
            param.setMinValue(0);
            param.setMaxValue(65536);
            param.setValue(8);
            param.setDefaultValue(1);
            return param;
        }
    },

    NUMBER_OF_INTERLEAVED_SLICE {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("NUMBER_OF_INTERLEAVED_SLICE");
            param.setDisplayedName("NUMBER_OF_INTERLEAVED_SLICE");
            param.setDescription("");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Scan);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Scan);
            param.setMinValue(0);
            param.setMaxValue(1000);
            param.setValue(12);
            param.setDefaultValue(0);
            return param;
        }
    },

    NUMBER_OF_SHOOT_3D {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("NUMBER_OF_SHOOT_3D");
            param.setDisplayedName("NUMBER_OF_SHOOT_3D");
            param.setDescription("the number of scan 3D for transform 3D");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Scan);
            param.setMinValue(1);
            param.setMaxValue(65536);
            param.setValue(1);
            param.setDefaultValue(1);
            return param;
        }
    },

    OBSERVED_FREQUENCY {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("OBSERVED_FREQUENCY");
            param.setDisplayedName("Observed frequency");
            param.setDescription("The frequency of the acquisition");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Reception);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Frequency);
            param.setMinValue(0.0);
            param.setMaxValue(3.0E9);
            param.setValue(1.2818100000000001E8);
            param.setDefaultValue(6.3E7);
            return param;
        }
    },

    OBSERVED_NUCLEUS {
        Param build() {
            TextParam param = new TextParam();
            param.setName("OBSERVED_NUCLEUS");
            param.setDisplayedName("Observed Nucleus");
            param.setDescription("The observed nucleus");
            param.setRoles(new RoleEnum[] {RoleEnum.SequenceDesigner});
            param.setGroup(EnumGroup.Reception);
            param.setCategory(Category.Acquisition);
            param.setValue("1H");
            param.setDefaultValue("1H");
            param.setSuggestedValues(asList("Other", "Y", "X", "3H", "1H", "19F", "3He", "205Tl", "203Tl", "31P", "7Li", "119Sn", "117Sn", "87Rb", "115Sn", "11B", "125Te", "141Pr", "71Ga", "65Cu", "129Xe", "81Br", "63Cu", "23Na", "51V", "123Te", "27Al", "13C", "79Br", "151Eu", "55Mn", "93Nb", "45Sc", "159Tb", "69Ga", "121Sb", "59Co", "187Re", "185Re", "99Tc", "113Cd", "115In", "113In", "195Pt", "165Ho", "111Cd", "207Pb", "127I", "29Si", "77Se", "199Hg", "171Yb", "75As", "209Bi", "2H", "6Li", "139La", "9Be", "17O", "138La", "133Cs", "123Sb", "181Ta", "175Lu", "137Ba", "153Eu", "10B", "15N", "50V", "135Ba", "35Cl", "85Rb", "91Zr", "61Ni", "169Tm", "131Xe", "37Cl", "176Lu", "21Ne", "189Os", "33S", "14N", "43Ca", "97Mo", "201Hg", "95Mo", "67Zn", "25Mg", "40K", "53Cr", "49Ti", "47Ti", "143Nd", "101Ru", "89Y", "173Yb", "163Dy", "39K", "109Ag", "99Ru", "105Pd", "87Sr", "147Sm", "183W", "107Ag", "157Gd", "177Hf", "83Kr", "73Ge", "149Sm", "161Dy", "145Nd", "57Fe", "103Rh", "155Gd", "167Er", "41K", "179Hf", "187Os", "193Ir", "235U", "197Au", "191Ir"));
            param.setRestrictedToSuggested(true);
            return param;
        }
    },

    OFF_CENTER_FIELD_OF_VIEW_1D {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("OFF_CENTER_FIELD_OF_VIEW_1D");
            param.setDisplayedName("Location 1D");
            param.setDescription("Offset in Readout Direction");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Gradient);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Location);
            param.setMinValue(-1.7976931348623157E308);
            param.setMaxValue(1.7976931348623157E308);
            param.setValue(0.0);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    OFF_CENTER_FIELD_OF_VIEW_2D {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("OFF_CENTER_FIELD_OF_VIEW_2D");
            param.setDisplayedName("Location 2D");
            param.setDescription("Offset in Phase Encoding Direction");
            param.setLocked(true);
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Gradient);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Location);
            param.setMinValue(-1.7976931348623157E308);
            param.setMaxValue(1.7976931348623157E308);
            param.setValue(0.0);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    OFF_CENTER_FIELD_OF_VIEW_3D {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("OFF_CENTER_FIELD_OF_VIEW_3D");
            param.setDisplayedName("Location Z");
            param.setDescription("Offset in the slice direction");
            param.setLocked(true);
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Gradient);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Location);
            param.setMinValue(-1.7976931348623157E308);
            param.setMaxValue(1.7976931348623157E308);
            param.setValue(-0.0);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    OFF_CENTER_FIELD_OF_VIEW_EFF {
        Param build() {
            ListNumberParam param = new ListNumberParam();
            param.setName("OFF_CENTER_FIELD_OF_VIEW_EFF");
            param.setDisplayedName("OFF_CENTER_FIELD_OF_VIEW_EFF");
            param.setDescription("Offcenter effective in 1D 2D and 3D (read phase slice)");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setMinValue(-1.7976931348623157E308);
            param.setMaxValue(1.7976931348623157E308);
            param.setNumberEnum(NumberEnum.Location);
            param.setValue(asList(0.0, 0.0, 0.0));
            param.setDefaultValue(asList(0.0, 0.0, 0.0));
            return param;
        }
    },

    OFF_CENTER_FIELD_OF_VIEW_X {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("OFF_CENTER_FIELD_OF_VIEW_X");
            param.setDisplayedName("Location X");
            param.setDescription("Location in the R/L direction");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Gradient);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Location);
            param.setMinValue(-1.7976931348623157E308);
            param.setMaxValue(1.7976931348623157E308);
            param.setValue(0.0);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    OFF_CENTER_FIELD_OF_VIEW_Y {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("OFF_CENTER_FIELD_OF_VIEW_Y");
            param.setDisplayedName("Location Y");
            param.setDescription("Location in the A/P direction");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Gradient);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Location);
            param.setMinValue(-1.7976931348623157E308);
            param.setMaxValue(1.7976931348623157E308);
            param.setValue(0.0);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    OFF_CENTER_FIELD_OF_VIEW_Z {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("OFF_CENTER_FIELD_OF_VIEW_Z");
            param.setDisplayedName("Location Z");
            param.setDescription("Location in the I/S direction");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Gradient);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Location);
            param.setMinValue(-1.7976931348623157E308);
            param.setMaxValue(1.7976931348623157E308);
            param.setValue(0.0);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    OFFSET_FREQ_1 {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("OFFSET_FREQ_1");
            param.setDisplayedName("Offset 1");
            param.setDescription("The offset frequency of the first sequence channel");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.FrequencyOffset);
            param.setMinValue(-1.5E9);
            param.setMaxValue(1.5E9);
            param.setValue(0.0);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    OFFSET_FREQ_2 {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("OFFSET_FREQ_2");
            param.setDisplayedName("Offset 2");
            param.setDescription("The offset frequency of the second sequence channel");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.FrequencyOffset);
            param.setMinValue(-1.5E9);
            param.setMaxValue(1.5E9);
            param.setValue(0.0);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    OFFSET_FREQ_3 {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("OFFSET_FREQ_3");
            param.setDisplayedName("Offset 3");
            param.setDescription("The offset frequency of the third sequence channel");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.FrequencyOffset);
            param.setMinValue(-1.5E9);
            param.setMaxValue(1.5E9);
            param.setValue(0.0);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    OFFSET_FREQ_4 {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("OFFSET_FREQ_4");
            param.setDisplayedName("Offset 4");
            param.setDescription("The offset frequency of the fourth sequence channel");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.FrequencyOffset);
            param.setMinValue(-1.5E9);
            param.setMaxValue(1.5E9);
            param.setValue(0.0);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    ORIENTATION {
        Param build() {
            TextParam param = new TextParam();
            param.setName("ORIENTATION");
            param.setDisplayedName("Orientation");
            param.setDescription("Field of view orientation");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setValue("SAGITTAL");
            param.setDefaultValue("AXIAL");
            param.setSuggestedValues(asList("AXIAL", "CORONAL", "SAGITTAL", "OBLIQUE"));
            param.setRestrictedToSuggested(true);
            return param;
        }
    },

    PAROPT_PARAM {
        Param build() {
            TextParam param = new TextParam();
            param.setName("PAROPT_PARAM");
            param.setDisplayedName("Parameter optimised");
            param.setDescription("Name of the current optimised parameter");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Acquisition);
            param.setValue("");
            param.setDefaultValue("PULSE_LENGTH");
            return param;
        }
    },

    PHASE_0 {
        Param build() {
            ListNumberParam param = new ListNumberParam();
            param.setName("PHASE_0");
            param.setDisplayedName("PHASE_0");
            param.setDescription("PHASE_0.description");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Process);
            param.setMinValue(-1.7976931348623157E308);
            param.setMaxValue(1.7976931348623157E308);
            param.setNumberEnum(NumberEnum.Double);
            return param;
        }
    },

    PHASE_1 {
        Param build() {
            ListNumberParam param = new ListNumberParam();
            param.setName("PHASE_1");
            param.setDisplayedName("PHASE_1");
            param.setDescription("PHASE_1.description");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Process);
            param.setMinValue(-1.7976931348623157E308);
            param.setMaxValue(1.7976931348623157E308);
            param.setNumberEnum(NumberEnum.Double);
            return param;
        }
    },

    PHASE_APPLIED {
        Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("PHASE_APPLIED");
            param.setDisplayedName("PHASE_APPLIED");
            param.setDescription("PHASE_APPLIED");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Process);
            param.setValue(false);
            param.setDefaultValue(false);
            return param;
        }
    },

    PHASE_CYCLING {
        Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("PHASE_CYCLING");
            param.setDisplayedName("PHASE_CYCLING");
            param.setDescription("Enable the phase cycling in the sequence");
            param.setRoles(new RoleEnum[] {RoleEnum.SequenceDesigner});
            param.setGroup(EnumGroup.Reception);
            param.setCategory(Category.Acquisition);
            param.setValue(true);
            param.setDefaultValue(true);
            return param;
        }
    },

    PHASE_FIELD_OF_VIEW_RATIO {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("PHASE_FIELD_OF_VIEW_RATIO");
            param.setDisplayedName("Phase FOV");
            param.setDescription("The fov ratio in the phase direction");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.PERCENT);
            param.setMinValue(0.0);
            param.setMaxValue(100.0);
            param.setValue(50.0);
            param.setDefaultValue(100.0);
            return param;
        }
    },

    PHASE_RESET {
        Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("PHASE_RESET");
            param.setDisplayedName("PHASE_RESET");
            param.setDescription("");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setValue(false);
            param.setDefaultValue(false);
            return param;
        }
    },

    PREPHASING_READ_GRADIENT_RATIO {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("PREPHASING_READ_GRADIENT_RATIO");
            param.setDisplayedName("Prephasing Read gradient ratio");
            param.setDescription("The prephasing reading gradient ratio");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Gradient);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Double);
            param.setMinValue(-100.0);
            param.setMaxValue(100.0);
            param.setValue(0.5);
            param.setDefaultValue(0.5);
            return param;
        }
    },

    PROBE {
        Param build() {
            TextParam param = new TextParam();
            param.setName("PROBE");
            param.setDisplayedName("Probe");
            param.setDescription("The probe used for the mr acquisition");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Acquisition);
            param.setValue("");
            param.setDefaultValue("");
            return param;
        }
    },

    PROBES {
        Param build() {
            ListTextParam param = new ListTextParam();
            param.setName("PROBES");
            param.setDisplayedName("Probes");
            param.setDescription("The probes used for the acquisition");
            param.setLocked(true);
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Acquisition);
            return param;
        }
    },

    RECEIVER_COUNT {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("RECEIVER_COUNT");
            param.setDisplayedName("Receiver Count");
            param.setDescription("The number of receivers");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Reception);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Integer);
            param.setMinValue(1);
            param.setMaxValue(32);
            param.setValue(1);
            param.setDefaultValue(1);
            return param;
        }
    },

    RECEIVER_GAIN {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("RECEIVER_GAIN");
            param.setDisplayedName("RG");
            param.setDescription("The receiver gain");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Reception);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.RxGain);
            param.setMinValue(0.0);
            param.setMaxValue(120.0);
            param.setValue(42.0);
            param.setDefaultValue(10.0);
            return param;
        }
    },

    REPETITION_TIME {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("REPETITION_TIME");
            param.setDisplayedName("TR");
            param.setDescription("The repetition time  ( TR ) ");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Delay);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Time);
            param.setMinValue(0.0);
            param.setMaxValue(1.0E9);
            param.setValue(0.522);
            param.setDefaultValue(0.2);
            return param;
        }
    },

    RESOLUTION_FREQUENCY {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("RESOLUTION_FREQUENCY");
            param.setDisplayedName("RESOLUTION_FREQUENCY");
            param.setDescription("Pixel true dimension in the frequency encoding direction (FOV / acquisition_matrix_dimension_1D) ");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Length);
            param.setMinValue(0.0);
            param.setMaxValue(100.0);
            param.setValue(3.125E-4);
            param.setDefaultValue(0.01);
            return param;
        }
    },

    RESOLUTION_PHASE {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("RESOLUTION_PHASE");
            param.setDisplayedName("RESOLUTION_PHASE");
            param.setDescription("Pixe truel dimension in the phase encoding direction (FOV / acquisition_matrix_dimension_2D) ");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Length);
            param.setMinValue(0.0);
            param.setMaxValue(100.0);
            param.setValue(3.125E-4);
            param.setDefaultValue(5.0E-4);
            return param;
        }
    },

    RESOLUTION_SLICE {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("RESOLUTION_SLICE");
            param.setDisplayedName("RESOLUTION_SLICE");
            param.setDescription("Pixe truel dimension in the third direction");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Length);
            param.setMinValue(0.0);
            param.setMaxValue(100.0);
            param.setValue(0.001);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    SE_TYPE {
        Param build() {
            TextParam param = new TextParam();
            param.setName("SE_TYPE");
            param.setDisplayedName("SE_TYPE");
            param.setDescription("chose between FSE and MultiEcho");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Scan);
            param.setCategory(Category.Acquisition);
            param.setValue("FSE");
            param.setDefaultValue("FSE");
            param.setSuggestedValues(asList("MultiEcho"));
            return param;
        }
    },

    SEQ_DESCRIPTION {
        Param build() {
            TextParam param = new TextParam();
            param.setName("SEQ_DESCRIPTION");
            param.setDisplayedName("SEQ_DESCRIPTION");
            param.setDescription("");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setCategory(Category.Acquisition);
            param.setValue("SE_2D_SAG_256x128x12_ETL=4");
            param.setDefaultValue("");
            return param;
        }
    },

    SEQUENCE_NAME {
        Param build() {
            TextParam param = new TextParam();
            param.setName("SEQUENCE_NAME");
            param.setDisplayedName("Seq");
            param.setDescription("The name of the sequence");
            param.setLockedToDefault(true);
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Acquisition);
            param.setValue("SPIN_ECHO");
            param.setDefaultValue("SPIN_ECHO");
            return param;
        }
    },

    SEQUENCE_TIME {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("SEQUENCE_TIME");
            param.setDisplayedName("SEQUENCE_TIME");
            param.setDescription("");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Delay);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Time);
            param.setMinValue(0.0);
            param.setMaxValue(1.0E9);
            param.setValue(134.700576);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    SEQUENCE_VERSION {
        Param build() {
            TextParam param = new TextParam();
            param.setName("SEQUENCE_VERSION");
            param.setDisplayedName("SEQUENCE_VERSION");
            param.setDescription("");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.User);
            param.setCategory(Category.Acquisition);
            param.setValue("Version7.12");
            param.setDefaultValue("");
            return param;
        }
    },

    SETUP_MODE {
        Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("SETUP_MODE");
            param.setDisplayedName("Setup");
            param.setDescription("True during setup process");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Acquisition);
            param.setValue(false);
            param.setDefaultValue(false);
            return param;
        }
    },

    SLICE_REFOCUSING_GRADIENT_RATIO {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("SLICE_REFOCUSING_GRADIENT_RATIO");
            param.setDisplayedName("Ratio of the refocusing gradient");
            param.setDescription("The ratio of the slice refocusing gradient");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Gradient);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Double);
            param.setMinValue(-100.0);
            param.setMaxValue(100.0);
            param.setValue(0.5);
            param.setDefaultValue(0.5);
            return param;
        }
    },

    SLICE_THICKNESS {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("SLICE_THICKNESS");
            param.setDisplayedName("Slice Thickness");
            param.setDescription("slice thickness");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Length);
            param.setMinValue(5.0E-5);
            param.setMaxValue(1.7976931348623157E308);
            param.setValue(0.001);
            param.setDefaultValue(0.005);
            return param;
        }
    },

    SLICE_THICKNESS_180_WIDER {
        Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("SLICE_THICKNESS_180_WIDER");
            param.setDisplayedName("SLICE_THICKNESS_180_WIDER");
            param.setDescription("slightly increase the slice thickness for the 180deg pulse");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setValue(false);
            param.setDefaultValue(false);
            return param;
        }
    },

    SOFTWARE_VERSION {
        Param build() {
            TextParam param = new TextParam();
            param.setName("SOFTWARE_VERSION");
            param.setDisplayedName("SOFTWARE_VERSION");
            param.setDescription("The version of the software");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Miscellaneous);
            param.setValue("1.0199");
            param.setDefaultValue("Software version");
            return param;
        }
    },

    SPACING_BETWEEN_SLICE {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("SPACING_BETWEEN_SLICE");
            param.setDisplayedName("Slice Spacing");
            param.setDescription("Spacing betwin slice");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Length);
            param.setMinValue(0.0);
            param.setMaxValue(1.7976931348623157E308);
            param.setValue(1.9999999999999998E-4);
            param.setDefaultValue(5.0);
            return param;
        }
    },

    SPECTRAL_WIDTH {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("SPECTRAL_WIDTH");
            param.setDisplayedName("SW");
            param.setDescription("The spectral width of the reception");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Reception);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.SW);
            param.setMinValue(0.0);
            param.setMaxValue(1.0E8);
            param.setValue(50080.128205128196);
            param.setDefaultValue(12500.0);
            return param;
        }
    },

    SPECTRAL_WIDTH_OPT {
        Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("SPECTRAL_WIDTH_OPT");
            param.setDisplayedName("SPECTRAL_WIDTH_OPT");
            param.setDescription("Use SW to calculate SW_PER_PIXEL (true) Use SW_PER_PIXEL to calculate SW (false)");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Reception);
            param.setCategory(Category.Acquisition);
            param.setValue(true);
            param.setDefaultValue(false);
            return param;
        }
    },

    SPECTRAL_WIDTH_PER_PIXEL {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("SPECTRAL_WIDTH_PER_PIXEL");
            param.setDisplayedName("SPECTRAL_WIDTH_PER_PIXEL");
            param.setDescription("Spectral Width per pixel in Hz / Pix");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Delay);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.SW);
            param.setMinValue(0.0);
            param.setMaxValue(1.0E8);
            param.setValue(195.62550080128202);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    SQUARE_PIXEL {
        Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("SQUARE_PIXEL");
            param.setDisplayedName("SQUARE_PIXEL");
            param.setDescription("Same pixel dimension in frequency and phase encoding direction, this will change Phase FOV");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setValue(false);
            param.setDefaultValue(false);
            return param;
        }
    },

    STATION_NAME {
        Param build() {
            TextParam param = new TextParam();
            param.setName("STATION_NAME");
            param.setDisplayedName("STATION_NAME");
            param.setDescription("Station name");
            param.setLockedToDefault(true);
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Miscellaneous);
            param.setValue("Station name");
            param.setDefaultValue("Station name");
            return param;
        }
    },

    SUBJECT_POSITION {
        Param build() {
            TextParam param = new TextParam();
            param.setName("SUBJECT_POSITION");
            param.setDisplayedName("SUBJECT_POSITION");
            param.setDescription("Subject position relative to the magnet.");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setValue("HeadFirstProne");
            param.setDefaultValue("FeetFirstProne");
            param.setSuggestedValues(asList("HeadFirstProne", "HeadFirstSupine", "FeetFirstProne", "FeetFirstSupine"));
            param.setRestrictedToSuggested(true);
            return param;
        }
    },

    SWITCH_READ_PHASE {
        Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("SWITCH_READ_PHASE");
            param.setDisplayedName("Switch Read/Phase");
            param.setDescription("Switch the read and phase encoding gradient");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Gradient);
            param.setCategory(Category.Acquisition);
            param.setValue(false);
            param.setDefaultValue(false);
            return param;
        }
    },

    TRANSFORM_PLUGIN {
        Param build() {
            TextParam param = new TextParam();
            param.setName("TRANSFORM_PLUGIN");
            param.setDisplayedName("Transform plugin");
            param.setDescription("Transform the acquisition space to the k space");
            param.setRoles(new RoleEnum[] {RoleEnum.SequenceDesigner});
            param.setGroup(EnumGroup.Scan);
            param.setCategory(Category.Acquisition);
            param.setValue("Centered2D");
            param.setDefaultValue("Centered2D");
            param.setSuggestedValues(asList("Centered2D", "Centered2DRot", "Bordered2D", "Sequential4D", "Sequential2D"));
            return param;
        }
    },

    TRIGGER_EXTERNAL {
        Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("TRIGGER_EXTERNAL");
            param.setDisplayedName("TRIGGER_EXTERNAL");
            param.setDescription("To synchronize the acquisition with the external trigger ");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Delay);
            param.setCategory(Category.Acquisition);
            param.setValue(false);
            param.setDefaultValue(false);
            return param;
        }
    },

    TRIGGER_TIME {
        Param build() {
            ListNumberParam param = new ListNumberParam();
            param.setName("TRIGGER_TIME");
            param.setDisplayedName("TRIGGER_TIME");
            param.setDescription("TRIGGER_TIME.description");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Delay);
            param.setCategory(Category.Acquisition);
            param.setMinValue(0.0);
            param.setMaxValue(1.0E9);
            param.setNumberEnum(NumberEnum.Time);
            param.setValue(asList(0.0));
            param.setDefaultValue(asList(0.01));
            return param;
        }
    },

    TX_AMP_180 {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("TX_AMP_180");
            param.setDisplayedName("TX_AMP_180");
            param.setDescription("The magnitude of the RF pulse 180");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.TxAmp);
            param.setMinValue(0.0);
            param.setMaxValue(100.0);
            param.setValue(86.70053947826925);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    TX_AMP_90 {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("TX_AMP_90");
            param.setDisplayedName("TX_AMP");
            param.setDescription("Amplitude of the transmitter");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.TxAmp);
            param.setMinValue(0.0);
            param.setMaxValue(100.0);
            param.setValue(44.25189688320475);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    TX_AMP_ATT_AUTO {
        Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("TX_AMP_ATT_AUTO");
            param.setDisplayedName("TX_AMP_ATT_AUTO");
            param.setDescription("Automatically calculate and set ATT and AMP for the RF according to the calibrate. ");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setValue(true);
            param.setDefaultValue(true);
            return param;
        }
    },

    TX_ATT {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("TX_ATT");
            param.setDisplayedName("TX_ATT");
            param.setDescription("The emission attenuation");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.TxAtt);
            param.setMinValue(0.0);
            param.setMaxValue(63.0);
            param.setValue(18.0);
            param.setDefaultValue(36.0);
            return param;
        }
    },

    TX_BANDWIDTH_FACTOR {
        Param build() {
            ListNumberParam param = new ListNumberParam();
            param.setName("TX_BANDWIDTH_FACTOR");
            param.setDisplayedName("TX_BANDWIDTH_FACTOR");
            param.setDescription("Bandwidth factor of the RF pulse for 4 shapes: 1-HARD, 2-GAUSSIAN, 3-SINC3 and 4-SINC5");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setMinValue(0.0);
            param.setMaxValue(1.7976931348623157E308);
            param.setNumberEnum(NumberEnum.Double);
            param.setValue(asList(0.75, 1.35, 2.55, 4.25));
            param.setDefaultValue(asList(0.8, 1.45, 2.55, 3.9));
            return param;
        }
    },

    TX_BANDWIDTH_FACTOR_3D {
        Param build() {
            ListNumberParam param = new ListNumberParam();
            param.setName("TX_BANDWIDTH_FACTOR_3D");
            param.setDisplayedName("TX_BANDWIDTH_FACTOR_3D");
            param.setDescription("");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setMinValue(-1.7976931348623157E308);
            param.setMaxValue(1.7976931348623157E308);
            param.setNumberEnum(NumberEnum.Double);
            param.setValue(asList(1.9, 2.7, 5.1, 8.5));
            param.setDefaultValue(asList(1.1, 3.2, 5.0, 7.3));
            return param;
        }
    },

    TX_LENGTH_180 {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("TX_LENGTH_180");
            param.setDisplayedName("TX_LENGTH_180");
            param.setDescription("The duration of the 180 RF pulse");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Time);
            param.setMinValue(0.0);
            param.setMaxValue(1.0E9);
            param.setValue(0.001);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    TX_LENGTH_90 {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("TX_LENGTH_90");
            param.setDisplayedName("TX_LENGTH_90");
            param.setDescription("length of RF pulse");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Time);
            param.setMinValue(0.0);
            param.setMaxValue(1.0E9);
            param.setValue(0.001);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    TX_ROUTE {
        Param build() {
            ListNumberParam param = new ListNumberParam();
            param.setName("TX_ROUTE");
            param.setDisplayedName("TX_ROUTE");
            param.setDescription("LogCh->PhysCh");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setMinValue(-2147483648);
            param.setMaxValue(2147483647);
            param.setNumberEnum(NumberEnum.Integer);
            param.setValue(asList(0));
            return param;
        }
    },

    TX_SHAPE_180 {
        Param build() {
            TextParam param = new TextParam();
            param.setName("TX_SHAPE_180");
            param.setDisplayedName("TX_SHAPE_180");
            param.setDescription("the shape of the RF pulse");
            param.setRoles(new RoleEnum[] {RoleEnum.Manager, RoleEnum.SequenceDesigner});
            param.setCategory(Category.Acquisition);
            param.setValue("SINC3");
            param.setDefaultValue("HARD");
            param.setSuggestedValues(asList("HARD", "GAUSSIAN", "SINC3", "SINC5"));
            return param;
        }
    },

    TX_SHAPE_90 {
        Param build() {
            TextParam param = new TextParam();
            param.setName("TX_SHAPE_90");
            param.setDisplayedName("TX_SHAPE_90");
            param.setDescription("the shape of the rf pulse");
            param.setRoles(new RoleEnum[] {RoleEnum.Manager, RoleEnum.SequenceDesigner});
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setValue("SINC3");
            param.setDefaultValue("HARD");
            param.setSuggestedValues(asList("HARD", "GAUSSIAN", "SINC3", "SINC5"));
            return param;
        }
    },

    USER_MATRIX_DIMENSION_1D {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("USER_MATRIX_DIMENSION_1D");
            param.setDisplayedName("USER_ACQUISITION_MATRIX_DIMENSION_1D");
            param.setDescription("");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.User);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Scan);
            param.setMinValue(1);
            param.setMaxValue(65536);
            param.setValue(256);
            param.setDefaultValue(1);
            return param;
        }
    },

    USER_MATRIX_DIMENSION_2D {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("USER_MATRIX_DIMENSION_2D");
            param.setDisplayedName("USER_ACQUISITION_MATRIX_DIMENSION_2D");
            param.setDescription("");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.User);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Scan);
            param.setMinValue(1);
            param.setMaxValue(65536);
            param.setValue(128);
            param.setDefaultValue(1);
            return param;
        }
    },

    USER_MATRIX_DIMENSION_3D {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("USER_MATRIX_DIMENSION_3D");
            param.setDisplayedName("USER_ACQUISITION_MATRIX_DIMENSION_3D");
            param.setDescription("");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.User);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Scan);
            param.setMinValue(1);
            param.setMaxValue(65536);
            param.setValue(12);
            param.setDefaultValue(1);
            return param;
        }
    },

    USER_MATRIX_DIMENSION_4D {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("USER_MATRIX_DIMENSION_4D");
            param.setDisplayedName("USER_ACQUISITION_MATRIX_DIMENSION_4D");
            param.setDescription("");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.User);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Scan);
            param.setMinValue(1);
            param.setMaxValue(65536);
            param.setValue(1);
            param.setDefaultValue(1);
            return param;
        }
    },

    USER_PARTIAL_PHASE {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("USER_PARTIAL_PHASE");
            param.setDisplayedName("PARTIAL_PHASE");
            param.setDescription("Partial Fourier acquisition in the phase encoding direction");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.PERCENT);
            param.setMinValue(0.1);
            param.setMaxValue(100.0);
            param.setValue(100.0);
            param.setDefaultValue(100.0);
            return param;
        }
    },

    USER_PARTIAL_SLICE {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("USER_PARTIAL_SLICE");
            param.setDisplayedName("PARTIAL_SLICE");
            param.setDescription("Partial Fourier acquisition in the 3D encoding direction");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.PERCENT);
            param.setMinValue(0.1);
            param.setMaxValue(100.0);
            param.setValue(100.0);
            param.setDefaultValue(100.0);
            return param;
        }
    },

    USER_TMP_PARAM_BOOL_1 {
        Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("USER_TMP_PARAM_BOOL_1");
            param.setDisplayedName("USER_TMP_PARAM_BOOL_1");
            param.setDescription("");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setCategory(Category.Acquisition);
            param.setValue(false);
            param.setDefaultValue(false);
            return param;
        }
    },

    USER_TMP_PARAM_BOOL_2 {
        Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("USER_TMP_PARAM_BOOL_2");
            param.setDisplayedName("USER_TMP_PARAM_BOOL_2");
            param.setDescription("");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setCategory(Category.Acquisition);
            param.setValue(false);
            param.setDefaultValue(false);
            return param;
        }
    },

    USER_TMP_PARAM_LIST_1 {
        Param build() {
            ListNumberParam param = new ListNumberParam();
            param.setName("USER_TMP_PARAM_LIST_1");
            param.setDisplayedName("USER_TMP_PARAM_LIST_1");
            param.setDescription("");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setCategory(Category.Acquisition);
            param.setMinValue(-1.7976931348623157E308);
            param.setMaxValue(1.7976931348623157E308);
            param.setNumberEnum(NumberEnum.Double);
            return param;
        }
    },

    USER_TMP_PARAM_LIST_2 {
        Param build() {
            ListNumberParam param = new ListNumberParam();
            param.setName("USER_TMP_PARAM_LIST_2");
            param.setDisplayedName("USER_TMP_PARAM_LIST_2");
            param.setDescription("");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setCategory(Category.Acquisition);
            param.setMinValue(-1.7976931348623157E308);
            param.setMaxValue(1.7976931348623157E308);
            param.setNumberEnum(NumberEnum.Double);
            return param;
        }
    },

    USER_TMP_PARAM_NUM_1 {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("USER_TMP_PARAM_NUM_1");
            param.setDisplayedName("USER_TMP_PARAM_NUM_1");
            param.setDescription("");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Double);
            param.setMinValue(-1.7976931348623157E308);
            param.setMaxValue(1.7976931348623157E308);
            param.setValue(0.0);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    USER_TMP_PARAM_NUM_2 {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("USER_TMP_PARAM_NUM_2");
            param.setDisplayedName("USER_TMP_PARAM_NUM_2");
            param.setDescription("");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Double);
            param.setMinValue(-1.7976931348623157E308);
            param.setMaxValue(1.7976931348623157E308);
            param.setValue(0.0);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    USER_TMP_PARAM_STR_1 {
        Param build() {
            TextParam param = new TextParam();
            param.setName("USER_TMP_PARAM_STR_1");
            param.setDisplayedName("USER_TMP_PARAM_STR_1");
            param.setDescription("");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setCategory(Category.Acquisition);
            param.setValue("");
            param.setDefaultValue("");
            return param;
        }
    },

    USER_TMP_PARAM_STR_2 {
        Param build() {
            TextParam param = new TextParam();
            param.setName("USER_TMP_PARAM_STR_2");
            param.setDisplayedName("USER_TMP_PARAM_STR_2");
            param.setDescription("");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setCategory(Category.Acquisition);
            param.setValue("");
            param.setDefaultValue("");
            return param;
        }
    },

    USER_ZERO_FILLING_2D {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("USER_ZERO_FILLING_2D");
            param.setDisplayedName("ZERO_FILING_2D");
            param.setDescription("Percentage of zero filling");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Scan);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.PERCENT);
            param.setMinValue(0.0);
            param.setMaxValue(100.0);
            param.setValue(0.0);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    USER_ZERO_FILLING_3D {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("USER_ZERO_FILLING_3D");
            param.setDisplayedName("ZERO_FILING_3D");
            param.setDescription("Percentage of zero filing");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Scan);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.PERCENT);
            param.setMinValue(0.0);
            param.setMaxValue(100.0);
            param.setValue(0.0);
            param.setDefaultValue(0.0);
            return param;
        }
    };

    abstract Param build();
}