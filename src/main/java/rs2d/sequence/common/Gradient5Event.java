package rs2d.sequence.common;

import rs2d.spinlab.data.transformPlugin.TransformPlugin;

import rs2d.spinlab.instrument.util.GradientMath;
import rs2d.spinlab.sequence.Sequence;
import rs2d.spinlab.sequence.table.Shape;
import rs2d.spinlab.sequence.table.Table;
import rs2d.spinlab.sequence.table.Utility;
import rs2d.spinlab.sequenceGenerator.GeneratorSequenceParamEnum;
import rs2d.spinlab.tools.table.Order;
import rs2d.spinlab.tools.utility.Nucleus;

/**
 * Class Gradient: small Common Version 1.3S
 */
public class Gradient5Event extends Gradient {

    protected Table flatTimeTable1 = null;
    protected Table flatTimeTable3 = null;

    public Gradient5Event(Table amplitudeTab, Table flat_TimeTab1, Table flat_TimeTab2, Table flat_TimeTab3, Shape shapeUpTab, Shape shapeDownTab, Table rampTimeUpTab, Table rampTimeDownTab) {
        super(amplitudeTab, flat_TimeTab2, shapeUpTab, shapeDownTab, rampTimeUpTab, rampTimeDownTab);
        flatTimeTable1 = flat_TimeTab1;
        flatTimeTable3 = flat_TimeTab3;
        init();
    }

    public static Gradient5Event createGradient(Sequence sequence, GeneratorSequenceParamEnum amplitudeTab, GeneratorSequenceParamEnum flat_TimeTab1, GeneratorSequenceParamEnum flat_TimeTab2, GeneratorSequenceParamEnum flat_TimeTab3, GeneratorSequenceParamEnum shapeUpTab, GeneratorSequenceParamEnum shapeDownTab, GeneratorSequenceParamEnum rampTimeTab) {
        return new Gradient5Event(sequence.getPublicTable(amplitudeTab.name()), sequence.getPublicTable(flat_TimeTab1.name()), sequence.getPublicTable(flat_TimeTab2.name()), sequence.getPublicTable(flat_TimeTab3.name()), (Shape) sequence.getPublicTable(shapeUpTab.name()),
                (Shape) sequence.getPublicTable(shapeDownTab.name()), sequence.getPublicTable(rampTimeTab.name()), sequence.getPublicTable(rampTimeTab.name()));
    }

    public static Gradient5Event createGradient(Sequence sequence, GeneratorSequenceParamEnum amplitudeTab, GeneratorSequenceParamEnum flat_TimeTab1, GeneratorSequenceParamEnum flat_TimeTab2, GeneratorSequenceParamEnum flat_TimeTab3, GeneratorSequenceParamEnum shapeUpTab, GeneratorSequenceParamEnum shapeDownTab, GeneratorSequenceParamEnum rampTimeUpTab, GeneratorSequenceParamEnum rampTimeDownTab) {
        return new Gradient5Event(sequence.getPublicTable(amplitudeTab.name()), sequence.getPublicTable(flat_TimeTab1.name()), sequence.getPublicTable(flat_TimeTab2.name()), sequence.getPublicTable(flat_TimeTab3.name()), (Shape) sequence.getPublicTable(shapeUpTab.name()),
                (Shape) sequence.getPublicTable(shapeDownTab.name()), sequence.getPublicTable(rampTimeUpTab.name()), sequence.getPublicTable(rampTimeDownTab.name()));
    }


    public Gradient5Event(Table amplitudeTab, Table flat_TimeTab1, Table flat_TimeTab2, Table flat_TimeTab3, Shape shapeUpTab, Shape shapeDownTab, Table rampTimeUpTab, Table rampTimeDownTab, Nucleus nucleus) {
        super(amplitudeTab, flat_TimeTab2, shapeUpTab, shapeDownTab, rampTimeUpTab, rampTimeDownTab, nucleus);
        flatTimeTable1 = flat_TimeTab1;
        flatTimeTable3 = flat_TimeTab3;
        init();
    }

    public static Gradient5Event createGradient(Sequence sequence, GeneratorSequenceParamEnum amplitudeTab, GeneratorSequenceParamEnum flat_TimeTab1, GeneratorSequenceParamEnum flat_TimeTab2, GeneratorSequenceParamEnum flat_TimeTab3, GeneratorSequenceParamEnum shapeUpTab, GeneratorSequenceParamEnum shapeDownTab, GeneratorSequenceParamEnum rampTimeTab, Nucleus nucleus) {
        return new Gradient5Event(sequence.getPublicTable(amplitudeTab.name()), sequence.getPublicTable(flat_TimeTab1.name()), sequence.getPublicTable(flat_TimeTab2.name()), sequence.getPublicTable(flat_TimeTab3.name()), (Shape) sequence.getPublicTable(shapeUpTab.name()),
                (Shape) sequence.getPublicTable(shapeDownTab.name()), sequence.getPublicTable(rampTimeTab.name()), sequence.getPublicTable(rampTimeTab.name()), nucleus);
    }

    public static Gradient5Event createGradient(Sequence sequence, GeneratorSequenceParamEnum amplitudeTab, GeneratorSequenceParamEnum flat_TimeTab1, GeneratorSequenceParamEnum flat_TimeTab2, GeneratorSequenceParamEnum flat_TimeTab3, GeneratorSequenceParamEnum shapeUpTab, GeneratorSequenceParamEnum shapeDownTab, GeneratorSequenceParamEnum rampTimeUpTab, GeneratorSequenceParamEnum rampTimeDownTab, Nucleus nucleus) {
        return new Gradient5Event(sequence.getPublicTable(amplitudeTab.name()), sequence.getPublicTable(flat_TimeTab1.name()), sequence.getPublicTable(flat_TimeTab2.name()), sequence.getPublicTable(flat_TimeTab3.name()), (Shape) sequence.getPublicTable(shapeUpTab.name()),
                (Shape) sequence.getPublicTable(shapeDownTab.name()), sequence.getPublicTable(rampTimeUpTab.name()), sequence.getPublicTable(rampTimeDownTab.name()), nucleus);
    }


    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    //                  general  methodes
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    protected void init() {
        computeShapeRiseTime();
        prepareEquivalentTime();
    }


    /**
     * Calculate equivalentTime of a rectangular gradient with same Area and amplitude
     *
     *//*
     */
    @Override
    public void prepareEquivalentTime() {
        if (Double.isNaN(grad_shape_rise_time)) {
            computeShapeRiseTime();
        }
        if (flatTimeTable1 != null) {
            minTopTime = flatTimeTable.get(0).doubleValue() + flatTimeTable1.get(0).doubleValue() + flatTimeTable3.get(0).doubleValue();
            equivalentTime = (minTopTime + grad_shape_rise_time);
        }
    }

    /*
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    //     RO             Readout  methods             RO
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    */
    @Override
    public double[] getEquivalentTimeFlat(Table time, double ratio) {
        ratio = Math.min(1.0, Math.max(0.0, ratio));
        double[] equivalentTime = new double[time.size()];
        for (int t = 0; t < time.size(); t++) {
            equivalentTime[t] = time.get(t).doubleValue() * ratio + flatTimeTable1.get(0).doubleValue();
        }
        System.out.println("   getEquivalentTimeFlat Grad 5  ");
        return equivalentTime;
    }

    /**
     * calculate readout Gradient Amplitude
     *
     * @param spectralWidth
     * @param fov
     * @return testSpectralWidth : false if the Spectralwidth need to be nicreased(call getSpectralWidth() )
     *//*
     */
    @Override
    public boolean calculateReadoutGradient(double spectralWidth, double fov) throws Exception {
        boolean testSpectralWidth = super.calculateReadoutGradient(spectralWidth, fov);
        calculateStaticArea();
        return testSpectralWidth;
    }


}