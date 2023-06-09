package rs2d.sequence.spinecho;


import rs2d.spinlab.sequence.Sequence;
import rs2d.spinlab.sequence.element.TimeElement;

public enum Events {
    Start (0,"Time6"),
    TriggerDelay (1, S.Time_trigger_delay.name()),
    LoopMultiPlanarStart (2,S.Time_min_instruction.name()),

    IR (4,S.Time_tx_IR_length.name()),
    IRDelay (25,S.Time_TI_delay.name()),


    LoopSatBandStart (9,S.Time_min_instruction.name()),
    SatBandpulse (11,S.Time_tx_sb.name()),
    LoopSatBandEnd (16,S.Time_min_instruction.name()),

    FatSatPulseWep (18,S.Time_tx_fatsat_wep.name()),
    FatSatWepDelay (19,S.Time_wep_delay.name()),
    FatSatPulse (21,S.Time_tx_fatsat.name()),


    TX90 (27 ,S.Time_tx_90.name()),
    DelayTE1(32 ,S.Time_TE_delay1.name()),

    CrusherStart(36 ,S.Time_grad_ramp.name()),
    LoopStartEcho (40 ,S.Time_min_instruction.name()),
    TX180 (42,S.Time_tx_180.name()),
    DelayTE2(48 ,S.Time_TE_delay2.name()),
    Acq (53,S.Time_rx.name()),
    DelayTE3(58 ,S.Time_TE_delay3.name()),
    LoopEndEcho (62,S.Time_min_instruction.name()),

    DelayTR4(66 ,S.Time_TR_delay.name()),
    LoopMultiPlanarEnd (67 ,S.Time_min_instruction.name()),
    TimeFlush (69, S.Time_flush_delay.name());
    public final int ID;
    public final String shortcutName;

    Events(int id, String sname) {
        this.shortcutName = sname;
        ID = id;
    }

    static boolean  checkEventShortcut(Sequence sequence)throws Exception{
        Events[] interfaceFields = Events.values();
        for (Events f : interfaceFields) {

            if ( !f.shortcutName.equals(((TimeElement) sequence.getTimeChannel().get(f.ID)).getTime().getName()) ){

                String message = "PSD Event Error\n" +
                        " Shortcut of time ID#" + f.ID + " is not " + f.shortcutName+ "   but is "+((TimeElement) sequence.getTimeChannel().get(f.ID)).getTime().getName()
                        + " \n Check PSD Events and Events-Class";
                System.out.println(message);
                throw new Exception( message);
            }
        }
        return false;
    }
}

