package rs2d.spinlab.api;

import java.io.IOException;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import rs2d.commons.log.Log;
import rs2d.spinlab.exception.ConfigurationException;
import rs2d.spinlab.hardware.compiler.SequenceCompilerInterface;
import rs2d.spinlab.hardware.compiler.exception.CompilerException;
import rs2d.spinlab.hardware.compiler.exception.CompilerNotFoundException;
import rs2d.spinlab.hardware.controller.gradient.GradientHandler;
import rs2d.spinlab.hardware.controller.shim.ShimHandler;
import rs2d.spinlab.hardware.devices.DeviceHandler;
import rs2d.spinlab.hardware.devices.DeviceManager;
import rs2d.spinlab.hardware.devices.ParamHandler;
import rs2d.spinlab.instrument.Instrument;
import rs2d.spinlab.instrument.probe.Probe;
import rs2d.spinlab.instrument.probe.ProbeChannelPower;
import rs2d.spinlab.instrument.util.TxMath;
import rs2d.spinlab.tools.linearity.CameleonRxCalibrationData;
import rs2d.spinlab.tools.param.AdjustableParam;
import rs2d.spinlab.tools.param.NumberEnum;
import rs2d.spinlab.tools.param.NumberParam;
import rs2d.spinlab.tools.utility.Nucleus;
import rs2d.spinlab.tools.utility.RoundEnum;

/**
 * Hardware access methods to be used in Plugin or Java-based sequence developments to avoid direct internal class dependencies.
 * Version compliant with 2021.06.2
 */
public class Hardware {

    public static final int DEFAULT_SHIM_STEP = 1;

    private Hardware() {
        throw new IllegalAccessError("Utility class shouldn't be instantiated!");
    }

    // UTILITY

    private static Nucleus getNucleusByName(String nucleusName) {
        Nucleus nucleus = Nucleus.getNucleusForName(nucleusName);
        if (nucleus == null) {
            // Throw directly an exception with a meaningful message to avoid NPE later.
            throw new IllegalArgumentException(String.format("Cannot find nucleus %s!", nucleusName));
        }
        return nucleus;
    }

    // COMPILER

    /**
     * @return The current sequence compiler.
     * @throws CompilerNotFoundException If no compiler is found.
     */
    private static SequenceCompilerInterface getSequenceCompiler() throws CompilerNotFoundException {
        return DeviceManager.getInstance().getCompilerLoader().getCompiler().getSequenceCompiler();
    }

    /**
     * @return Version of the current sequence compiler.
     * @throws CompilerNotFoundException If no compiler is found.
     */
    public static double getCompilerVersion() throws CompilerNotFoundException {
        return getSequenceCompiler().getVersionID();
    }

    /**
     * @param spectralWidth Spectral width in Hz.
     * @return Nearest spectral width given compiler properties, or directly the spectral width entered if no sequence compiler is found.
     */
    public static double getNearestSpectralWidth(double spectralWidth) {
        try {
            return getSequenceCompiler().getNearestSW(spectralWidth);
        } catch (CompilerNotFoundException e) {
            Log.warning(Hardware.class, "Cannot find a compiler to compute nearest spectral width, the given spectral width is returned.");
            return spectralWidth;
        }
    }

    /**
     * @param spectralWidth Spectral width in Hz.
     * @return Floor spectral width given compiler properties, or directly the spectral width entered if no sequence compiler is found.
     */
    public static double getFloorSpectralWidth(double spectralWidth) {
        try {
            return getSequenceCompiler().getSW(spectralWidth, RoundEnum.Inferior);
        } catch (CompilerNotFoundException e) {
            Log.warning(Hardware.class, "Cannot find a compiler to compute floor spectral width, the given spectral width is returned.");
            return spectralWidth;
        }
    }

    /**
     * @param spectralWidth Spectral width in Hz.
     * @return Ceil spectral width given compiler properties, or directly the spectral width entered if no sequence compiler is found.
     */
    public static double getCeilSpectralWidth(double spectralWidth) {
        try {
            return getSequenceCompiler().getSW(spectralWidth, RoundEnum.Superior);
        } catch (CompilerNotFoundException e) {
            Log.warning(Hardware.class, "Cannot find a compiler to compute ceil spectral width, the given spectral width is returned.");
            return spectralWidth;
        }
    }

    /**
     * @return Minimal Spectral Width in Hz.
     * @throws CompilerNotFoundException If compiler not found.
     */
    public static double getMinSpectralWidth() throws CompilerNotFoundException {
        return getSequenceCompiler().getMinSW();
    }

    /**
     * @return Maximal Spectral Width in Hz.
     * @throws CompilerNotFoundException If compiler not found.
     */
    public static double getMaxSpectralWidth() throws CompilerNotFoundException {
        return getSequenceCompiler().getMaxSW();
    }

    /**
     * @return Base spectral width frequency in Hz, half the acquisition clock frequency.
     * @throws CompilerNotFoundException If compiler not found.
     */
    public static double getSpectralWidthBaseFrequency() throws CompilerNotFoundException {
        return getSequenceCompiler().getAcquClk() / 2.;
    }

    /**
     * @return Clock frequency of the sequencer in Hz.
     * @throws CompilerNotFoundException If no compiler is found.
     */
    public static double getSystemClock() throws CompilerNotFoundException {
        return getSequenceCompiler().getSystemClk();
    }

    /**
     * @return Gradient sampling frequency in Hz, frequency for gradient sequencer.
     * @throws CompilerNotFoundException If no compiler is found.
     */
    public static double getGradientSamplingFrequency() throws CompilerNotFoundException {
        return getSequenceCompiler().getGradientClk();
    }

    /**
     * @return Tx sampling frequency in Hz, frequency of the direct digital synthetizer.
     * @throws CompilerNotFoundException If no compiler is found.
     */
    public static double getTxSamplingFrequency() throws CompilerNotFoundException {
        return getSequenceCompiler().getTxClk();
    }

    /**
     * @return Acquisition sampling frequency in Hz, frequency of the analog-to-digital converter.
     * @throws CompilerNotFoundException If no compiler is found.
     */
    public static double getAcquisitionSamplingFrequency() throws CompilerNotFoundException {
        return getSequenceCompiler().getAcquClk();
    }

    /**
     * @return Transfer time per data point in s.
     * @throws CompilerNotFoundException If no compiler is found.
     */
    public static double getTransferTimePerDataPt() throws CompilerNotFoundException {
        return getSequenceCompiler().getTransfertTimePerDataPt();
    }

    // POWER LIMIT

    /**
     * @param nucleusName Nucleus name.
     * @return Maximal pulsed power in Watt.
     */
    public static double getMaxRfPowerPulsed(String nucleusName) {
        Probe probe = Instrument.instance().getTransmitProbe();
        ProbeChannelPower probeChannelPower = probe.getDefaultPower().getMap().get(nucleusName);
        return probeChannelPower.getMaxRfPowerPulsed();
    }

    /**
     * @param nucleusName Nucleus name.
     * @return Maximal power in Watt for pulse on a long duration. If an instruction exceeds this power for a long time it may raise a power check
     *     exception on compilation to prevent damage.
     */
    public static double getMaxRfPowerCW(String nucleusName) {
        Probe probe = Instrument.instance().getTransmitProbe();
        ProbeChannelPower probeChannelPower = probe.getDefaultPower().getMap().get(nucleusName);
        return probeChannelPower.getMaxRfPowerCW();
    }

    // INSTRUMENT PROPERTIES ACCESS

    /**
     * @return Name of the selected probe.
     */
    public static String getCurrentProbeName() {
        return Instrument.instance().getCurrentProbe();
    }

    /**
     * @param nucleus A nucleus.
     * @return Number of receivers defined for this nucleus.
     * @throws ConfigurationException In case of configuration error.
     */
    public static int getReceiverCount(Nucleus nucleus) throws ConfigurationException {
        return Instrument.instance().getObservableRxs(nucleus).size();
    }

    /**
     * @param nucleusName Nucleus name.
     * @return Number of receivers defined for this nucleus.
     * @throws ConfigurationException In case of configuration error.
     */
    public static int getReceiverCount(String nucleusName) throws ConfigurationException {
        return getReceiverCount(getNucleusByName(nucleusName));
    }

    /**
     * @return Default LO attenuation in dB.
     */
    public static int getLoAttenuation() {
        return Instrument.instance().getLoAttenuation();
    }

    /**
     * @param nucleus Nucleus to be observed.
     * @return First calibrated LO attenuation in dB that can be found in the Instrument Rx channels that can observe this nucleus.
     *     If none is found the default LO attenuation is returned.
     * @throws IllegalArgumentException If the calibration doesn't exist.
     */
    public static int getLoAttenuation(Nucleus nucleus) {
        try {
            Instrument instrument = Instrument.instance();
            return Instrument.instance().getObservableRxs(nucleus).stream()
                .map(instrRxIndex -> instrument.getRxChannels().get(instrRxIndex).getCameleonRxChannel().getCalibrationSet())
                .map(calibrationSet -> calibrationSet.findCalibrationData(nucleus.getFrequency(instrument.getProtonFrequency())))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(CameleonRxCalibrationData::getLoAtt)
                .map(Double::intValue)
                .findFirst().orElseGet(Hardware::getLoAttenuation);
        } catch (ConfigurationException e) {
            Log.error(Hardware.class, "Cannot get calibrated LO attenuation for nucleus %s!", e, nucleus);
        }
        return getLoAttenuation();
    }

    /**
     * @param nucleusName Name of nucleus to be observed.
     * @return First calibrated LO attenuation in dB that can be found in the Instrument Rx channels that can observe this nucleus.
     *     If none is found the default LO attenuation is returned.
     * @throws IllegalArgumentException If the calibration doesn't exist.
     */
    public static int getLoAttenuation(String nucleusName) {
        return getLoAttenuation(getNucleusByName(nucleusName));
    }

    /**
     * @return Default Intermediate Frequency of the Instrument in Hz.
     */
    public static double getIntermediateFrequency() {
        return Instrument.instance().getIfFrequency();
    }

    /**
     * @return Current magnet field strenght in Tesla.
     */
    public static double getMagnetFieldStrength() {
        return Instrument.instance().getDevices().getMagnet().getField();
    }

    /**
     * @param newField New magnet field strenght in Tesla.
     */
    public static void setMagnetFieldStrength(double newField) {
        Instrument.instance().getDevices().getMagnet().setField(newField);
    }

    /**
     * @return Proton frequency in Hz on the current Magnet, considered in H2O solvent.
     */
    public static double getProtonFrequency() {
        return Instrument.instance().getProtonFrequency();
    }

    /**
     * @param newProtonFrequency New proton frequency in Hz for the current Magnet, considered in H2O solvent.
     */
    public static void setProtonFrequency(double newProtonFrequency) {
        Instrument.instance().getDevices().getMagnet().setProtonFrequency(newProtonFrequency);
    }

    /**
     * @return Number of points removed on acquisition start.
     */
    public static int getNbAcquisitionDeadPoints() {
        return Instrument.instance().getDevices().getCameleon().getAcquDeadPointCount();
    }

    /**
     * @return True if dead points are removed on acquisition.
     */
    public static boolean isRemoveAcquisitionDeadPoints() {
        return Instrument.instance().getDevices().getCameleon().isRemoveAcquDeadPoint();
    }

    /**
     * @param nucleus A nucleus, will be used to find which Tx channel is used for Tune.
     * @return The Tx attenuation to use when tuning for this nucleus.
     * @throws ConfigurationException In case of configuration error.
     */
    public static int getTuneAttenuation(Nucleus nucleus) throws ConfigurationException {
        int instrTxIndex = TxMath.getInstrumentTxChannelIndexForNucleus(nucleus);
        return Instrument.instance().getTxChannels().get(instrTxIndex).getTuneTxAtt();
    }

    /**
     * @param nucleusName A nucleus name, will be used to find which Tx channel is used for Tune.
     * @return The Tx attenuation to use when tuning for this nucleus.
     * @throws ConfigurationException In case of configuration error.
     */
    public static int getTuneAttenuation(String nucleusName) throws ConfigurationException {
        return getTuneAttenuation(getNucleusByName(nucleusName));
    }

    // DELAY

    /**
     * @param instrumentTxIndex Index of the instrument Tx channel.
     * @return The blanking delay in second of the RF Amplifier channel associated to this Instrument Tx channel.
     */
    public static double getRfAmplifierChannelBlankingDelay(int instrumentTxIndex) throws ConfigurationException {
        if (instrumentTxIndex < 0 || instrumentTxIndex >= Instrument.instance().getTxChannels().size()) {
            throw new IllegalArgumentException("Cannot find the corresponding instrument Tx channel, wrong index!");
        }
        if (Instrument.instance().getTxChannels().get(instrumentTxIndex).getRfAmpChannel() == null) {
            throw new ConfigurationException(String.format("No RF Amplifier associated to the Instrument Tx channel %d!", instrumentTxIndex));
        }
        return Instrument.instance().getTxChannels().get(instrumentTxIndex).getRfAmpChannel().getBlankingDelay();
    }

    /**
     * @param nucleusName Name of a nucleus.
     * @return The blanking delay in second of the RF Amplifier channel of the Instrument Tx channel associated to this nucleus.
     */
    public static double getRfAmplifierChannelBlankingDelay(String nucleusName) throws ConfigurationException {
        return getRfAmplifierChannelBlankingDelay(getNucleusByName(nucleusName));
    }

    /**
     * @param nucleus A nucleus.
     * @return The blanking delay in second of the RF Amplifier channel of the Instrument Tx channel associated to this nucleus.
     */
    public static double getRfAmplifierChannelBlankingDelay(Nucleus nucleus) throws ConfigurationException {
        return getRfAmplifierChannelBlankingDelay(TxMath.getInstrumentTxChannelIndexForNucleus(nucleus));
    }

    /**
     * @return Gradient delay in s.
     */
    public static double getGradientDelay() {
        return Instrument.instance().getGradDelay();
    }

    /**
     * @param newDelay New gradient delay in s.
     */
    public static void setGradientDelay(double newDelay) {
        Instrument.instance().setGradDelay(newDelay);
    }

    /**
     * @return Rx/Tx delay in s.
     */
    public static double getRxTxDelay() {
        return Instrument.instance().getRxTxDelay();
    }

    /**
     * @param newDelay New Rx/Tx delay in s.
     */
    public static void getRxTxDelay(double newDelay) {
        Instrument.instance().setRxTxDelay(newDelay);
    }

    /**
     * @return Difference between Rx/Tx delay and Gradient delay in s.
     */
    public static double getGradientRxTxDelay() {
        return getRxTxDelay() - getGradientDelay();
    }

    // DEVICES

    // GRADIENT

    /**
     * @return True is a Gradient device is connected.
     */
    public static boolean isGradientConnected() {
        return DeviceManager.getInstance().getGradientHandler().map(DeviceHandler::isConnected).orElse(false);
    }

    /**
     * @return True if a Gradient device is present and supports BO compensation.
     */
    public static boolean isGradientBOCompensationEnabled() {
        return DeviceManager.getInstance().getGradientHandler().map(GradientHandler::isB0compEnabled).orElse(false);
    }

    /**
     * @return Value of B0 amplitude in %.
     */
    public static double getGradientB0Amplitude() {
        return DeviceManager.getInstance().getGradientHandler().map(GradientHandler::getB0compAmp).map(NumberParam::doubleValue).orElse(0.);
    }

    /**
     * @return Value of B0 phase in degree.
     */
    public static double getGradientB0Phase() {
        return DeviceManager.getInstance().getGradientHandler().map(GradientHandler::getB0compPhase).map(NumberParam::doubleValue).orElse(0.);
    }

    /**
     * @return Set of all gradient parameters' names.
     */
    public static Set<String> getAllGradientParameters() {
        return DeviceManager.getInstance().getGradientHandler().map(ParamHandler::getAll).orElse(new TreeSet<>());
    }

    /**
     * @return The Gradient handler.
     * @throws IllegalAccessError If the handler cannot be found.
     */
    private static GradientHandler getGradientHandler() {
        return DeviceManager.getInstance().getGradientHandler()
            .orElseThrow(() -> new IllegalAccessError("Cannot found Gradient Handler!"));
    }

    /**
     * @param paramName Parameter name.
     * @return Value of the given parameter read from the hardware.
     * @throws IllegalAccessError If the handler cannot be found.
     */
    public static Number readGradientParameter(String paramName) {
        try {
            return getGradientHandler().read(paramName).getValue();
        } catch (CompilerException | IOException e) {
            Log.error(Hardware.class, "Error while reading Gradient Handler value %s!", e, paramName);
        }
        return null;
    }

    /**
     * @param paramName Parameter name.
     * @param value New value to put in the given shim.
     * @throws IllegalAccessError If the handler cannot be found.
     */
    public static boolean writeGradientParameter(String paramName, int value) {
        try {
            return getGradientHandler().write(new AdjustableParam(paramName, value, NumberEnum.Integer, 1));
        } catch (CompilerException | IOException e) {
            Log.error(Hardware.class, "Error while writing Gradient Handler value %d, in %s!", e, value, paramName);
        }
        return false;
    }

    // SHIM

    /**
     * @return True is a Shim device is connected.
     */
    public static boolean isShimHandlerConnected() {
        return DeviceManager.getInstance().getShimHandler().map(DeviceHandler::isConnected).orElse(false);
    }

    /**
     * @return Set of all shim parameters' names.
     */
    public static Set<String> getAllShimParameters() {
        return DeviceManager.getInstance().getShimHandler().map(ParamHandler::getAll).orElse(new TreeSet<>());
    }

    /**
     * @return The Shim handler.
     * @throws IllegalAccessError If the handler cannot be found.
     */
    private static ShimHandler getShimHandler() {
        return DeviceManager.getInstance().getShimHandler()
            .orElseThrow(() -> new IllegalAccessError("Cannot found Shim Handler!"));
    }

    /**
     * @param paramName Parameter name.
     * @return Value of the given parameter read from the hardware.
     * @throws IllegalAccessError If the handler cannot be found.
     */
    public static Number readShimParameter(String paramName) {
        try {
            return getShimHandler().read(paramName).getValue();
        } catch (IOException e) {
            Log.error(Hardware.class, "Error while reading Shim Handler value %s!", e, paramName);
        }
        return null;
    }

    /**
     * @param paramName Parameter name.
     * @param value New value to put in the given shim.
     * @return True if the value has been written. False if not, check logs to know what happens in such cases.
     * @throws IllegalAccessError If the handler cannot be found.
     */
    public static boolean writeShimParameter(String paramName, int value) {
        try {
            return getShimHandler().write(new AdjustableParam(paramName, value, NumberEnum.Integer, DEFAULT_SHIM_STEP));
        } catch (IOException e) {
            Log.error(Hardware.class, "Error while writing Shim Handler value %d in %s!", e, value, paramName);
        }
        return false;
    }
}
