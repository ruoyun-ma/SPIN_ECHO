package rs2d.sequence.spinecho;


import rs2d.spinlab.sequence.Sequence;
import rs2d.spinlab.sequence.element.TimeElement;

public enum Events {
    Start (0,"Time6"),
    TriggerDelay (1, S.Time_trigger_delay.name()),
    LoopMultiPlanarStart (2,S.Time_min_instruction.name()),

    IR (4,S.Time_tx_IR_length.name()),
    IRDelay (22,S.Time_TI_delay.name()),


    LoopSatBandStart (9,S.Time_min_instruction.name()),
    SatBandpulse (11,S.Time_tx_sb.name()),
    LoopSatBandEnd (16,S.Time_min_instruction.name()),

    FatSatPulse (18,S.Time_tx_fatsat.name()),


    TX90 (24,S.Time_tx_90.name()),
    Delay1 (16+13,S.Time_TE_delay1.name()),

    LoopStartEcho (24+13,S.Time_min_instruction.name()),
    TX180 (39,S.Time_tx_180.name()),
    Delay2 (45,S.Time_TE_delay2.name()),
    Acq (50,S.Time_rx.name()),
    Delay3 (55,S.Time_TE_delay3.name()),
    LoopEndEcho (59,S.Time_min_instruction.name()),

    Delay4 (63,S.Time_TR_delay.name()),
    LoopMultiPlanarEnd (64,S.Time_min_instruction.name());

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

