package rs2d.spinlab.api;

import rs2d.commons.misc.DoubleUtil;
import rs2d.spinlab.instrument.Instrument;
import rs2d.spinlab.instrument.InstrumentTxChannel;
import rs2d.spinlab.instrument.util.CurvePoint;
import rs2d.spinlab.instrument.util.TxMath;

/**
 * Interface with the method used to compute the pulse power. To be used in Java-based sequence to avoid direct internal class dependencies.
 * Version compliant with 2021.06.2
 */
public class PowerComputation {

    private PowerComputation() {
        throw new IllegalAccessError("Utility class shouldn't be instantiated!");
    }

    /**
     * @param solventName Solvent name for the calibration.
     * @param nucName Nucleus name for the calibration.
     * @return The calibration hard pulse 90 for this solvent and this nucleus. X is width in seconds and Y is power in Watt.
     */
    private static CurvePoint getHardPulse90(String solventName, String nucName) throws IllegalArgumentException {
        return Instrument.instance().getTransmitProbe()
            .getPower(solventName).getMap().get(nucName).getHardPulse90();
    }

    /**
     * @param solventName Solvent name for the calibration.
     * @param nucName Nucleus name for the calibration.
     * @return The power in Watt of the calibrated hard pulse 90 for this solvent and this nucleus.
     */
    public static double getHardPulse90Power(String solventName, String nucName) throws IllegalArgumentException {
        return getHardPulse90(solventName, nucName).y;
    }

    /**
     * @param nucName Nucleus name for the calibration.
     * @return The power in Watt of the calibrated hard pulse 90 for this nucleus and the default solvent.
     */
    public static double getHardPulse90Power(String nucName) throws IllegalArgumentException {
        return getHardPulse90(null, nucName).y;
    }

    /**
     * @param solventName Solvent name for the calibration.
     * @param nucName Nucleus name for the calibration.
     * @return The width in seconds of the calibrated hard pulse 90 for this solvent and this nucleus.
     */
    public static double getHardPulse90Width(String solventName, String nucName) throws IllegalArgumentException {
        return getHardPulse90(solventName, nucName).x;
    }

    /**
     * @param nucName Nucleus name for the calibration.
     * @return The width in seconds of the calibrated hard pulse 90 for this nucleus and the defaut solvent.
     */
    public static double getHardPulse90Width(String nucName) throws IllegalArgumentException {
        return getHardPulse90(null, nucName).x;
    }

    /**
     * @param solventName Solvent name for the calibration.
     * @param nucName Nucleus name for the calibration.
     * @param newPower The new power in Watt of the calibrated hard pulse 90 for this solvent and this nucleus.
     */
    public static void setHardPulse90Power(String solventName, String nucName, double newPower) throws IllegalArgumentException {
        getHardPulse90(solventName, nucName).y = DoubleUtil.bounded(newPower, 0, Hardware.getMaxRfPowerPulsed(nucName));
    }

    /**
     * @param nucName Nucleus name for the calibration.
     * @param newPower The new power in Watt of the calibrated hard pulse 90 for this nucleus and the default solvent.
     */
    public static void setHardPulse90Power(String nucName, double newPower) throws IllegalArgumentException {
        getHardPulse90(null, nucName).y = DoubleUtil.bounded(newPower, 0, Hardware.getMaxRfPowerPulsed(nucName));
    }

    /**
     * @param solventName Solvent name for the calibration.
     * @param nucName Nucleus name for the calibration.
     * @param newWidth The new width in seconds of the calibrated hard pulse 90 for this solvent and this nucleus.
     */
    public static void setHardPulse90Width(String solventName, String nucName, double newWidth) throws IllegalArgumentException {
        getHardPulse90(solventName, nucName).x = DoubleUtil.bounded(newWidth, 0., Double.MAX_VALUE);
    }

    /**
     * @param nucName Nucleus name for the calibration.
     * @param newWidth The new width in seconds of the calibrated hard pulse 90 for this nucleus and the default solvent.
     */
    public static void setHardPulse90Width(String nucName, double newWidth) throws IllegalArgumentException {
        getHardPulse90(null, nucName).x = DoubleUtil.bounded(newWidth, 0., Double.MAX_VALUE);
    }

    /**
     * @param solventName Solvent name for the calibration.
     * @param nucName Nucleus name for the calibration.
     * @return The calibration hard pulse 180 for this solvent and this nucleus. X is width in seconds and Y is power in Watt.
     */
    private static CurvePoint getHardPulse180(String solventName, String nucName) throws IllegalArgumentException {
        return Instrument.instance().getTransmitProbe()
            .getPower(solventName).getMap().get(nucName).getHardPulse180();
    }

    /**
     * @param solventName Solvent name for the calibration.
     * @param nucName Nucleus name for the calibration.
     * @return The power in Watt of the calibrated hard pulse 180 for this solvent and this nucleus.
     */
    public static double getHardPulse180Power(String solventName, String nucName) throws IllegalArgumentException {
        return getHardPulse180(solventName, nucName).y;
    }

    /**
     * @param nucName Nucleus name for the calibration.
     * @return The power in Watt of the calibrated hard pulse 180 for this nucleus and the default solvent.
     */
    public static double getHardPulse180Power(String nucName) throws IllegalArgumentException {
        return getHardPulse180(null, nucName).y;
    }

    /**
     * @param solventName Solvent name for the calibration.
     * @param nucName Nucleus name for the calibration.
     * @return The width in seconds of the calibrated hard pulse 180 for this solvent and this nucleus.
     */
    public static double getHardPulse180Width(String solventName, String nucName) throws IllegalArgumentException {
        return getHardPulse180(solventName, nucName).x;
    }

    /**
     * @param nucName Nucleus name for the calibration.
     * @return The width in seconds of the calibrated hard pulse 180 for this nucleus and the default solvent.
     */
    public static double getHardPulse180Width(String nucName) throws IllegalArgumentException {
        return getHardPulse180(null, nucName).x;
    }

    /**
     * @param solventName Solvent name for the calibration.
     * @param nucName Nucleus name for the calibration.
     * @param newPower The new power in Watt of the calibrated hard pulse 180 for this solvent and this nucleus.
     */
    public static void setHardPulse180Power(String solventName, String nucName, double newPower) throws IllegalArgumentException {
        getHardPulse180(solventName, nucName).y = DoubleUtil.bounded(newPower, 0, Hardware.getMaxRfPowerPulsed(nucName));
    }

    /**
     * @param nucName Nucleus name for the calibration.
     * @param newPower The new power in Watt of the calibrated hard pulse 180 for this nucleus and the defaut solvent.
     */
    public static void setHardPulse180Power(String nucName, double newPower) throws IllegalArgumentException {
        getHardPulse180(null, nucName).y = DoubleUtil.bounded(newPower, 0, Hardware.getMaxRfPowerPulsed(nucName));
    }

    /**
     * @param solventName Solvent name for the calibration.
     * @param nucName Nucleus name for the calibration.
     * @param newWidth The new width in seconds of the calibrated hard pulse 180 for this solvent and this nucleus.
     */
    public static void setHardPulse180Width(String solventName, String nucName, double newWidth) throws IllegalArgumentException {
        getHardPulse180(solventName, nucName).x = DoubleUtil.bounded(newWidth, 0., Double.MAX_VALUE);
    }

    /**
     * @param nucName Nucleus name for the calibration.
     * @param newWidth The new width in seconds of the calibrated hard pulse 180 this nucleus and the default solvent.
     */
    public static void setHardPulse180Width(String nucName, double newWidth) throws IllegalArgumentException {
        getHardPulse180(null, nucName).x = DoubleUtil.bounded(newWidth, 0., Double.MAX_VALUE);
    }

    /**
     * @param indexInstrumentTxChannel Index of the Instrument Tx channel to use.
     * @param frequency Frequency in Hz.
     * @param txAmp Tx amplitude in percentage.
     * @param txAtt Tx attenuation in dB.
     * @return The power in Watt if we pulse with these inputs on this instrument Tx channel, potential correction taken into account.
     * @throws IllegalStateException If Cameleon Tx channel is missing, or if RF amplifier is present and does not have any calibrations in this
     *     instrument Tx channel.
     */
    public static double getPower(int indexInstrumentTxChannel, double frequency, double txAmp, int txAtt) throws IllegalStateException {
        InstrumentTxChannel instrTxChannel = Instrument.instance().getTxChannels().get(indexInstrumentTxChannel);
        return TxMath.getPowerWatt(txAmp, txAtt, frequency, instrTxChannel);
    }

    /**
     * @param indexInstrumentTxChannel Index of the Instrument Tx channel to use.
     * @param powerWatt Target power in Watt.
     * @param freq Frequency in Hz.
     * @param txAtt Tx attenuation in dB.
     * @return The Tx amplitude in percentage that enables to reach that power.
     */
    public static double getTxAmplitude(int indexInstrumentTxChannel, double powerWatt, double freq, int txAtt) {
        InstrumentTxChannel instrTxChannel = Instrument.instance().getTxChannels().get(indexInstrumentTxChannel);
        return TxMath.getTxAmpFor(powerWatt, instrTxChannel, txAtt, freq);
    }

    /**
     * @param indexInstrumentTxChannel Index of the instrument Tx channel used.
     * @param powerWatt Target power in Watt.
     * @param freq Frequency in Hz.
     * @param txAmp Tx amplitude in percentage.
     * @return The highest attenuation in dB that lets the RF amplifier of this Tx channel output a power over the required one, given this frequency
     *     and this amplitude.
     */
    public static int getTxAttenuation(int indexInstrumentTxChannel, double powerWatt, double freq, double txAmp) {
        InstrumentTxChannel instrTxChannel = Instrument.instance().getTxChannels().get(indexInstrumentTxChannel);
        // Future version returns an integer, in 2021.06, the double was only return with old calibrations.
        // To be compliant with what is done in the driver we use the ceil and convert to int.
        return (int) Math.ceil(TxMath.getTxAttFor(powerWatt, instrTxChannel, txAmp, freq));
    }
}
