//-- generated code, will be overwritten at each recompilation

package rs2d.sequence.spinecho;

import rs2d.spinlab.tools.param.*;
import rs2d.spinlab.tools.table.*;
import rs2d.spinlab.tools.role.RoleEnum;
import rs2d.spinlab.sequenceGenerator.GeneratorSequenceParamEnum;

import java.util.List;
import static java.util.Arrays.asList;

public enum S implements GeneratorSequenceParamEnum {
    Ext_trig_source("Ext_trig_source"),
    FIR_coef2("FIR coef2"),
    Grad_enable_crush_IR("Grad_enable_crush_IR"),
    Grad_enable_IR("Grad_enable_IR"),
    Grad_enable_phase_2D("Grad_enable_phase_2D"),
    Grad_enable_phase_3D("Grad_enable_phase_3D"),
    Grad_enable_phase_bis("Grad_enable_phase_bis"),
    Grad_enable_read("Grad_enable_read"),
    Grad_enable_read_prep_1("Grad_enable_read_prep_1"),
    Grad_enable_read_prep_2("Grad_enable_read_prep_2"),
    Grad_enable_slice("Grad_enable_slice"),
    Grad_enable_Slice_180("Grad_enable_Slice_180"),
    Grad_enable_slice_crush("Grad_enable_slice_crush"),
    Grad_enable_spoiler("Grad_enable_spoiler"),
    Gradient_Angle_Phi("Gradient Angle Phi"),
    Gradient_Angle_Psi("Gradient Angle Psi"),
    Gradient_Angle_Theta("Gradient Angle Theta"),
    Gradient_axe_phase("Gradient_axe_phase"),
    Gradient_axe_read("Gradient_axe_read"),
    Intermediate_frequency("Intermediate_frequency"),
    Nb_1d("Nb_1d"),
    Nb_2d("Nb_2d"),
    Nb_3d("Nb_3d"),
    Nb_4d("Nb_4d"),
    Nb_echo("Nb_echo"),
    Nb_interleaved_slice("Nb_interleaved_slice"),
    Nb_point("Nb_point"),
    Phase_reset("Phase_reset"),
    Pre_scan("Pre_scan"),
    Rx_gain("Rx_gain"),
    Spectral_width("Spectral_width"),
    Synchro_trigger("Synchro_trigger"),
    Tx_att("Tx_att"),
    Tx_enable_IR("Tx_enable_IR"),
    Tx_frequency("Tx_frequency"),
    Tx_nucleus("Tx_nucleus"),
    FreqOffset_rx_1D_3Dcomp("FreqOffset_rx_1D_3Dcomp"),
    FreqOffset_rx_1D_3Dprep("FreqOffset_rx_1D_3Dprep"),
    FreqOffset_tx_prep_180("FreqOffset_tx_prep_180"),
    FreqOffset_tx_prep_90("FreqOffset_tx_prep_90"),
    FreqOffset_tx_prep_IR("FreqOffset_tx_prep_IR"),
    Frequency_offset_init("Frequency_offset_init"),
    Grad_amp_crusher_IR("Grad_amp_crusher_IR"),
    Grad_amp_phase_2D_comp("Grad_amp_phase_2D_comp"),
    Grad_amp_phase_2D_prep("Grad_amp_phase_2D_prep"),
    Grad_amp_phase_3D_comp("Grad_amp_phase_3D_comp"),
    Grad_amp_phase_3D_prep("Grad_amp_phase_3D_prep"),
    Grad_amp_read_prep("Grad_amp_read_prep"),
    Grad_amp_read_read("Grad_amp_read_read"),
    Grad_amp_slice_180("Grad_amp_slice_180"),
    Grad_amp_slice_90("Grad_amp_slice_90"),
    Grad_amp_slice_crush("Grad_amp_slice_crush"),
    Grad_amp_slice_refoc("Grad_amp_slice_refoc"),
    Grad_amp_spoiler_slice("Grad_amp_spoiler_slice"),
    Grad_shape_rise_down("Grad_shape_rise_down"),
    Grad_shape_rise_up("Grad_shape_rise_up"),
    Rx_freq_offset("Rx_freq_offset"),
    Rx_phase("Rx_phase"),
    Time_btw_dyn_frames("Time_btw_dyn_frames"),
    Time_flush_delay("Time_flush_delay"),
    Time_grad_crusher_top("Time_grad_crusher_top"),
    Time_grad_crusher_top2("Time_grad_crusher_top2"),
    Time_grad_IR_crusher_ramp("Time_grad_IR_crusher_ramp"),
    Time_grad_IR_crusher_top("Time_grad_IR_crusher_top"),
    Time_grad_IR_ramp("Time_grad_IR_ramp"),
    Time_grad_phase_top("Time_grad_phase_top"),
    Time_grad_ramp("Time_grad_ramp"),
    Time_grad_ramp_phase("Time_grad_ramp_phase"),
    Time_grad_ramp_read_2("Time_grad_ramp_read_2"),
    Time_grad_read_crusher("Time_grad_read_crusher"),
    Time_grad_read_prep_top("Time_grad_read_prep_top"),
    Time_grad_read_prep_top_2("Time_grad_read_prep_top_2"),
    Time_last_delay("Time_last_delay"),
    Time_min_instruction("Time_min_instruction"),
    Time_rx("Time_rx"),
    Time_TE_delay1("Time_TE_delay1"),
    Time_TE_delay2("Time_TE_delay2"),
    Time_TE_delay3("Time_TE_delay3"),
    Time_TI_delay("Time_TI_delay"),
    Time_TR_delay("Time_TR_delay"),
    Time_trigger_delay("Time_trigger_delay"),
    Time_tx_180("Time_tx_180"),
    Time_tx_90("Time_tx_90"),
    Time_tx_IR_length("Time_tx_IR_length"),
    Tx_amp_180("Tx_amp_180"),
    Tx_amp_90("Tx_amp_90"),
    Tx_freq_offset_180("Tx_freq_offset_180"),
    Tx_freq_offset_90("Tx_freq_offset_90"),
    Tx_freq_offset_IR("Tx_freq_offset_IR"),
    Tx_phase_180("Tx_phase_180"),
    Tx_phase_90("Tx_phase_90"),
    Tx_shape_180("Tx_shape_180"),
    Tx_shape_90("Tx_shape_90"),
    Tx_shape_phase_180("Tx_shape_phase_180"),
    Tx_shape_phase_90("Tx_shape_phase_90");

    //--

    private final String name;

    private S(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}