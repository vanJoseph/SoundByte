package com.donovanwilder.soundbyte;

import be.tarsos.dsp.AudioDispatcher;
import be.tarsos.dsp.AudioEvent;
import be.tarsos.dsp.io.jvm.AudioDispatcherFactory;
import be.tarsos.dsp.io.jvm.JVMAudioInputStream;
import be.tarsos.dsp.pitch.PitchDetectionHandler;
import be.tarsos.dsp.pitch.PitchDetectionResult;
import be.tarsos.dsp.pitch.PitchProcessor;

import javax.sound.sampled.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class uses the Tarsos DSP library to detect frequencies from a sound buffer or from a microphone. Make sure the sample rate is the same from the generator and the Pitch detector or you migh get false results
 *
 * @author Donovan J. Wilder
 */
public class PitchDetector implements PitchDetectionHandler {

    private AudioDispatcher dispatcher;
    private Mixer currentMixer;

    private PitchProcessor.PitchEstimationAlgorithm algo;

    public static void println(String message) {
        System.out.println(message);

    }

    /**
     * Construct a PitchDetector object that process audio from a buffer.
     *
     * @param audioBuffer a buffer of sound
     * @throws LineUnavailableException
     * @throws UnsupportedAudioFileException
     */
    public PitchDetector(byte[] audioBuffer) throws UnsupportedAudioFileException {
        println("Starting Pitch Detector");
        algo = PitchProcessor.PitchEstimationAlgorithm.YIN;
        processAudioBuffer(audioBuffer);
    }

    private List<PitchModel> pitchList;
    public PitchDetector(List<PitchModel> pitchList) throws UnsupportedAudioFileException, LineUnavailableException{
        this.pitchList=pitchList;
        Mixer.Info[] mixerInfos=AudioSystem.getMixerInfo();
        algo = PitchProcessor.PitchEstimationAlgorithm.YIN;
        Mixer mixer = AudioSystem.getMixer(mixerInfos[0]);
        processFromMic(mixer);

    }

    //todo add way to enter the sample rate

    /**
     * Processes  frequencies from an audio buffer.
     * @param audioBuffer
     * @throws LineUnavailableException
     * @throws UnsupportedAudioFileException
     */
    private void processAudioBuffer(byte[] audioBuffer) throws UnsupportedAudioFileException {

        if(dispatcher!= null){
            dispatcher.stop();
        }

        float sampleRate = 44100;
        int bufferSize = 512;
        int overlap = 0;
        println("Started listening with");

        final AudioFormat format = new AudioFormat(sampleRate, 16, 1, true,
                true);
        // create a new dispatcher
        dispatcher = AudioDispatcherFactory.fromByteArray(audioBuffer,format,bufferSize,overlap);

        // add a processor
        dispatcher.addAudioProcessor(new PitchProcessor(algo, sampleRate, bufferSize, this));

        new Thread(dispatcher,"Audio dispatching").start();
    }

    private Thread micProcessThread;
    /**
     * Detect the frequencies from the microphone.
     *
     * @param mixer the mic that you want to use
     * @throws LineUnavailableException
     */
    private void processFromMic(Mixer mixer) throws LineUnavailableException {

        if(dispatcher!= null){
            dispatcher.stop();
        }
        currentMixer = mixer;

        float sampleRate = 44100;
        int bufferSize = 512;


        int overlap = 0;
        println("Started listening with"+ mixer.getMixerInfo().getName() + "\n");

        final AudioFormat format = new AudioFormat(sampleRate, 16, 1, true,
                true);
        final DataLine.Info dataLineInfo = new DataLine.Info(
                TargetDataLine.class, format);
        TargetDataLine line;
        line = (TargetDataLine) mixer.getLine(dataLineInfo);
        final int numberOfSamples = bufferSize;
        line.open(format, numberOfSamples);
        line.start();
        final AudioInputStream stream = new AudioInputStream(line);

        JVMAudioInputStream audioStream = new JVMAudioInputStream(stream);
        // create a new dispatcher
        dispatcher = new AudioDispatcher(audioStream, bufferSize,
                overlap);

        // add a processor
        dispatcher.addAudioProcessor(new PitchProcessor(algo, sampleRate, bufferSize, this));

        micProcessThread=new Thread(dispatcher,"Audio dispatching");
        micProcessThread.start();
    }

    public static void main(String... strings) throws UnsupportedAudioFileException, LineUnavailableException {
        PitchGenerator generator = new PitchGenerator();
        List<Pitch> pitchList = new ArrayList<Pitch>();
        pitchList.add(Pitch.fromName("E4"));
        pitchList.add(Pitch.fromName("Ef4"));
        pitchList.add(Pitch.fromName("E4"));
        pitchList.add(Pitch.fromName("Ef4"));
        pitchList.add(Pitch.fromName("E4"));
        pitchList.add(Pitch.fromName("B3"));
        pitchList.add(Pitch.fromName("D4"));
        pitchList.add(Pitch.fromName("C4"));
        pitchList.add(Pitch.fromName("A3"));

        byte[] seq=generator.createPitchSequence(500,0.9, pitchList.toArray(new Pitch[pitchList.size()])) ;
        List<PitchModel> pitchModels = new ArrayList<>();
        PitchDetector pitchDetector = new PitchDetector(pitchModels);


        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("4. exit");
            int c = sc.nextInt();
            if (c == 4)





                break;
        }
        pitchDetector.dispatcher.stop();
        System.out.println("The dispatcher is stopped.");
        for (PitchModel model : pitchModels) {
            println(model.toString());
        }
        System.out.print("Done");
    }


    /**
     * if the object was constructed with a List it will record the PitchDetectionResults into the pitchList List.
     * @param pitchDetectionResult
     * @param audioEvent
     */
    @Override
    public void handlePitch(PitchDetectionResult pitchDetectionResult, AudioEvent audioEvent) {
        if(pitchDetectionResult.getPitch() != -1){
            double timeStamp = audioEvent.getTimeStamp();
            float pitch = pitchDetectionResult.getPitch();
            float probability = pitchDetectionResult.getProbability();
            double rms = audioEvent.getRMS() * 100;
            if (pitchList != null) {
                pitchList.add(new PitchModel(timeStamp, pitch, probability));
        }
            //String message = String.format("Pitch detected at %.2fs: %.2fHz ( %.2f probability, RMS: %.5f )\n", timeStamp,pitch,probability,rms);
            String message = String.format("Pitch detected at %.2fs: %s (%.2f probability)\n", timeStamp, Pitch.fromFrequency(pitch).getName(), probability);
            println(message);
        }
    }
}
