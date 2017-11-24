package rs2d.sequence.spinecho;

public interface Events {
    int Start = 0;
    int TriggerDelay = 1;
    int LoopMultiPlanarStart = 2;
    int IR = 4;
    int IRDelay = 9;

    int TX90 = 11;
    int Delay1 = 16;

    int LoopStartEcho = 24;
    int TX180 = 26;
    int Delay2 = 32;
    int Delay3 = 42;
    int Acq = 37;
    int LoopEndEcho = 46;

    int Delay4 = 50;
    int LoopMultiPlanarEnd = 51;
}