//-- generated code, will be overwritten at each recompilation

package rs2d.sequence.spinecho;


public interface SpinEchoSequenceParams {

    //-- public params

    /**
     * Group: Gradient, Type: BooleanParam
     */
    String Grad_enable_crush_IR = "Grad_enable_crush_IR";

    /**
     * Group: Gradient, Type: BooleanParam
     */
    String Grad_enable_IR = "Grad_enable_IR";

    /**
     * Group: Gradient, Type: BooleanParam
     */
    String Grad_enable_phase_2D = "Grad_enable_phase_2D";

    /**
     * Group: Gradient, Type: BooleanParam
     */
    String Grad_enable_phase_3D = "Grad_enable_phase_3D";

    /**
     * Group: Gradient, Type: BooleanParam
     */
    String Grad_enable_phase_bis = "Grad_enable_phase_bis";

    /**
     * Group: Gradient, Type: BooleanParam
     */
    String Grad_enable_read = "Grad_enable_read";

    /**
     * Group: Gradient, Type: BooleanParam
     */
    String Grad_enable_read_prep_1 = "Grad_enable_read_prep_1";

    /**
     * Group: Gradient, Type: BooleanParam
     */
    String Grad_enable_read_prep_2 = "Grad_enable_read_prep_2";

    /**
     * Group: Gradient, Type: BooleanParam
     */
    String Grad_enable_slice = "Grad_enable_slice";

    /**
     * Group: Gradient, Type: BooleanParam
     */
    String Grad_enable_Slice_180 = "Grad_enable_Slice_180";

    /**
     * Group: Gradient, Type: BooleanParam
     */
    String Grad_enable_slice_crush = "Grad_enable_slice_crush";

    /**
     * Group: Gradient, Type: BooleanParam
     */
    String Grad_enable_spoiler = "Grad_enable_spoiler";

    /**
     * Group: Gradient, Type: NumberParam - Angle (°)
     */
    String Gradient_Angle_Phi = "Gradient Angle Phi";

    /**
     * Group: Gradient, Type: NumberParam - Angle (°)
     */
    String Gradient_Angle_Psi = "Gradient Angle Psi";

    /**
     * Group: Gradient, Type: NumberParam - Angle (°)
     */
    String Gradient_Angle_Theta = "Gradient Angle Theta";

    /**
     * Group: Gradient, Type: EnumParam
     */
    String Gradient_axe_phase = "Gradient_axe_phase";

    /**
     * Group: Gradient, Type: EnumParam
     */
    String Gradient_axe_read = "Gradient_axe_read";

    /**
     * Group: Reception, Type: NumberParam - Frequency (Hz)
     */
    String Intermediate_frequency = "Intermediate_frequency";

    /**
     * Group: Dimension, Type: NumberParam - Scan
     */
    String Nb_1d = "Nb_1d";

    /**
     * Group: Dimension, Type: NumberParam - Scan
     */
    String Nb_2d = "Nb_2d";

    /**
     * Group: Dimension, Type: NumberParam - Scan
     */
    String Nb_3d = "Nb_3d";

    /**
     * Group: Dimension, Type: NumberParam - Scan
     */
    String Nb_4d = "Nb_4d";

    /**
     * Group: Dimension, Type: NumberParam - Integer
     */
    String Nb_echo = "Nb_echo";

    /**
     * Group: Dimension, Type: NumberParam - Integer
     */
    String Nb_interleaved_slice = "Nb_interleaved_slice";

    /**
     * Group: Dimension, Type: NumberParam - AcquPoint (pts)
     */
    String Nb_point = "Nb_point";

    /**
     * Group: Transmit, Type: BooleanParam
     */
    String Phase_reset = "Phase_reset";

    /**
     * Group: Dimension, Type: NumberParam - Scan
     */
    String Pre_scan = "Pre_scan";

    /**
     * Group: Reception, Type: NumberParam - RxGain (dB)
     */
    String Rx_gain = "Rx_gain";

    /**
     * Group: Reception, Type: NumberParam - SW (Hz)
     */
    String Spectral_width = "Spectral_width";

    /**
     * Group: Delay, Type: EnumParam
     */
    String Synchro_trigger = "Synchro_trigger";

    /**
     * Group: Transmit, Type: NumberParam - TxAtt (dB)
     */
    String Tx_att = "Tx_att";

    /**
     * Group: Transmit, Type: BooleanParam
     */
    String Tx_enable_IR = "Tx_enable_IR";

    /**
     * Group: Transmit, Type: NumberParam - Frequency (Hz)
     */
    String Tx_frequency = "Tx_frequency";

    /**
     * Group: Miscellaneous, Type: TextParam
     */
    String Tx_nucleus = "Tx_nucleus";


    //-- public tables

    /**
     * Group: Reception, Order: 4D+LoopB - FreqOffset (Hz)
     */
    String FreqOffset_rx_1D_3Dcomp = "FreqOffset_rx_1D_3Dcomp";

    /**
     * Group: Reception, Order: 4D+LoopB - FreqOffset (Hz)
     */
    String FreqOffset_rx_1D_3Dprep = "FreqOffset_rx_1D_3Dprep";

    /**
     * Group: Transmit, Order: 3D+Loop - FreqOffset (Hz)
     */
    String FreqOffset_tx_prep_180 = "FreqOffset_tx_prep_180";

    /**
     * Group: Transmit, Order: 3D+Loop - FreqOffset (Hz)
     */
    String FreqOffset_tx_prep_90 = "FreqOffset_tx_prep_90";

    /**
     * Group: Transmit, Order: 4D+Loop - FreqOffset (Hz)
     */
    String FreqOffset_tx_prep_IR = "FreqOffset_tx_prep_IR";

    /**
     * Group: Transmit, Order: 4D+Loop - FreqOffset (Hz)
     */
    String Frequency_offset_init = "Frequency_offset_init";

    /**
     * Group: Gradient, Order: 4D+Loop - GradAmp (%)
     */
    String Grad_amp_crusher_IR = "Grad_amp_crusher_IR";

    /**
     * Group: Gradient, Order: 2D+LoopB - GradAmp (%)
     */
    String Grad_amp_phase_2D_comp = "Grad_amp_phase_2D_comp";

    /**
     * Group: Gradient, Order: 2D+LoopB - GradAmp (%)
     */
    String Grad_amp_phase_2D_prep = "Grad_amp_phase_2D_prep";

    /**
     * Group: Gradient, Order: 4D+Loop - GradAmp (%)
     */
    String Grad_amp_phase_3D_comp = "Grad_amp_phase_3D_comp";

    /**
     * Group: Gradient, Order: 4D+Loop - GradAmp (%)
     */
    String Grad_amp_phase_3D_prep = "Grad_amp_phase_3D_prep";

    /**
     * Group: Gradient, Order: 4D+Loop - GradAmp (%)
     */
    String Grad_amp_read_prep = "Grad_amp_read_prep";

    /**
     * Group: Gradient, Order: 4D+Loop - GradAmp (%)
     */
    String Grad_amp_read_read = "Grad_amp_read_read";

    /**
     * Group: Gradient, Order: 4D+Loop - GradAmp (%)
     */
    String Grad_amp_slice_180 = "Grad_amp_slice_180";

    /**
     * Group: Gradient, Order: 4D+Loop - GradAmp (%)
     */
    String Grad_amp_slice_90 = "Grad_amp_slice_90";

    /**
     * Group: Gradient, Order: 4D+Loop - GradAmp (%)
     */
    String Grad_amp_slice_crush = "Grad_amp_slice_crush";

    /**
     * Group: Gradient, Order: 4D+Loop - GradAmp (%)
     */
    String Grad_amp_slice_refoc = "Grad_amp_slice_refoc";

    /**
     * Group: Gradient, Order: 4D+Loop - GradAmp (%)
     */
    String Grad_amp_spoiler_slice = "Grad_amp_spoiler_slice";

    /**
     * Group: Gradient, Order: 1D - GradShape (%)
     */
    String Grad_shape_rise_down = "Grad_shape_rise_down";

    /**
     * Group: Gradient, Order: 1D - GradShape (%)
     */
    String Grad_shape_rise_up = "Grad_shape_rise_up";

    /**
     * Group: Reception, Order: 4D+LoopB - FreqOffset (Hz)
     */
    String Rx_freq_offset = "Rx_freq_offset";

    /**
     * Group: Reception, Order: 1D - Phase (°)
     */
    String Rx_phase = "Rx_phase";

    /**
     * Group: Delay, Order: 4D - Time (s)
     */
    String Time_btw_dyn_frames = "Time_btw_dyn_frames";

    /**
     * Group: Delay, Order: 4D+Loop - Time (s)
     */
    String Time_flush_delay = "Time_flush_delay";

    /**
     * Group: Delay, Order: 4D+Loop - Time (s)
     */
    String Time_grad_crusher_top = "Time_grad_crusher_top";

    /**
     * Group: Delay, Order: 4D+Loop - Time (s)
     */
    String Time_grad_IR_crusher_ramp = "Time_grad_IR_crusher_ramp";

    /**
     * Group: Delay, Order: 4D+Loop - Time (s)
     */
    String Time_grad_IR_crusher_top = "Time_grad_IR_crusher_top";

    /**
     * Group: Delay, Order: 4D+Loop - Time (s)
     */
    String Time_grad_IR_ramp = "Time_grad_IR_ramp";

    /**
     * Group: Delay, Order: 4D+Loop - Time (s)
     */
    String Time_grad_phase_top = "Time_grad_phase_top";

    /**
     * Group: Delay, Order: 4D+Loop - Time (s)
     */
    String Time_grad_ramp = "Time_grad_ramp";

    /**
     * Group: Delay, Order: 4D+Loop - Time (s)
     */
    String Time_grad_ramp_phase = "Time_grad_ramp_phase";

    /**
     * Group: Delay, Order: 4D+Loop - Time (s)
     */
    String Time_grad_ramp_read_2 = "Time_grad_ramp_read_2";

    /**
     * Group: Delay, Order: 4D+Loop - Time (s)
     */
    String Time_grad_read_crusher = "Time_grad_read_crusher";

    /**
     * Group: Delay, Order: 4D+Loop - Time (s)
     */
    String Time_grad_read_prep_top = "Time_grad_read_prep_top";

    /**
     * Group: Delay, Order: 4D+Loop - Time (s)
     */
    String Time_grad_read_prep_top_2 = "Time_grad_read_prep_top_2";

    /**
     * Group: Delay, Order: 4D+Loop - Time (s)
     */
    String Time_last_delay = "Time_last_delay";

    /**
     * Group: Delay, Order: 4D+Loop - Time (s)
     */
    String Time_min_instruction = "Time_min_instruction";

    /**
     * Group: Delay, Order: 4D+Loop - Time (s)
     */
    String Time_rx = "Time_rx";

    /**
     * Group: Delay, Order: 4D+Loop - Time (s)
     */
    String Time_TE_delay1 = "Time_TE_delay1";

    /**
     * Group: Delay, Order: 4D+Loop - Time (s)
     */
    String Time_TE_delay2 = "Time_TE_delay2";

    /**
     * Group: Delay, Order: 4D+Loop - Time (s)
     */
    String Time_TE_delay3 = "Time_TE_delay3";

    /**
     * Group: Delay, Order: 4D - Time (s)
     */
    String Time_TI_delay = "Time_TI_delay";

    /**
     * Group: Delay, Order: 4D - Time (s)
     */
    String Time_TR_delay = "Time_TR_delay";

    /**
     * Group: Delay, Order: 4D - Time (s)
     */
    String Time_trigger_delay = "Time_trigger_delay";

    /**
     * Group: Delay, Order: 1D - Time (s)
     */
    String Time_tx_180 = "Time_tx_180";

    /**
     * Group: Delay, Order: 1D - Time (s)
     */
    String Time_tx_90 = "Time_tx_90";

    /**
     * Group: Delay, Order: 4D+Loop - Time (s)
     */
    String Time_tx_IR_length = "Time_tx_IR_length";

    /**
     * Group: Transmit, Order: 4D+Loop - TxAmp (%)
     */
    String Tx_amp_180 = "Tx_amp_180";

    /**
     * Group: Transmit, Order: 4D+Loop - TxAmp (%)
     */
    String Tx_amp_90 = "Tx_amp_90";

    /**
     * Group: Transmit, Order: 3D+Loop - FreqOffset (Hz)
     */
    String Tx_freq_offset_180 = "Tx_freq_offset_180";

    /**
     * Group: Transmit, Order: 3D+Loop - FreqOffset (Hz)
     */
    String Tx_freq_offset_90 = "Tx_freq_offset_90";

    /**
     * Group: Transmit, Order: 4D+Loop - FreqOffset (Hz)
     */
    String Tx_freq_offset_IR = "Tx_freq_offset_IR";

    /**
     * Group: Transmit, Order: 4D+Loop - Phase (°)
     */
    String Tx_phase_180 = "Tx_phase_180";

    /**
     * Group: Transmit, Order: 1D - Phase (°)
     */
    String Tx_phase_90 = "Tx_phase_90";

    /**
     * Group: Transmit, Order: 1D - TxShape (%)
     */
    String Tx_shape_180 = "Tx_shape_180";

    /**
     * Group: Transmit, Order: 1D - TxShape (%)
     */
    String Tx_shape_90 = "Tx_shape_90";

    /**
     * Group: Transmit, Order: 1D - Phase (°)
     */
    String Tx_shape_phase_180 = "Tx_shape_phase_180";

    /**
     * Group: Transmit, Order: 1D - Phase (°)
     */
    String Tx_shape_phase_90 = "Tx_shape_phase_90";

}