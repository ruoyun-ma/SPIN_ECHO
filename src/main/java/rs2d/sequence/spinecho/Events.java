package rs2d.sequence.spinecho;


import rs2d.spinlab.sequence.Sequence;
import rs2d.spinlab.sequence.element.TimeElement;

public enum Events {
    Start (0,"Time6"),
    TriggerDelay (1, S.Time_trigger_delay.name()),
    LoopMultiPlanarStart (2,S.Time_min_instruction.name()),
    IR (4,S.Time_tx_IR_length.name()),
    IRDelay (9,S.Time_TI_delay.name()),

    TX90 (11,S.Time_tx_90.name()),
    Delay1 (16,S.Time_TE_delay1.name()),

    LoopStartEcho (24,S.Time_min_instruction.name()),
    TX180 (26,S.Time_tx_180.name()),
    Delay2 (32,S.Time_TE_delay2.name()),
    Delay3 (42,S.Time_TE_delay3.name()),
    Acq (37,S.Time_rx.name()),
    LoopEndEcho (46,S.Time_min_instruction.name()),

    Delay4 (50,S.Time_TR_delay.name()),
    LoopMultiPlanarEnd (51,S.Time_min_instruction.name());

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

