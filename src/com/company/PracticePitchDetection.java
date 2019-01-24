package com.company;

import be.tarsos.dsp.AudioDispatcher;
import be.tarsos.dsp.AudioEvent;
import be.tarsos.dsp.io.jvm.AudioDispatcherFactory;
import be.tarsos.dsp.io.jvm.JVMAudioInputStream;
import be.tarsos.dsp.pitch.PitchDetectionHandler;
import be.tarsos.dsp.pitch.PitchDetectionResult;
import be.tarsos.dsp.pitch.PitchProcessor;

import javax.sound.sampled.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PracticePitchDetection implements PitchDetectionHandler {
    private AudioDispatcher  dispatcher;    //Plays a file and send a float array to the registered implementor
                                            //This is used to feed FFT's, Pitch detectors, audio players, etc
    private Mixer currentMixer;             //Contains the current mixer

    private PitchProcessor.PitchEstimationAlgorithm algo;   //This is the type of algorithm that will be used to determine the pitch

    public PracticePitchDetection(byte[]audioBuffer) throws UnsupportedAudioFileException {
        Mixer.Info[] mixers= AudioSystem.getMixerInfo();    //Collects all of the Mixers that are on the system
        currentMixer = AudioSystem.getMixer(mixers[0]);     //Sets the mixer that will be used with the program
        algo=PitchProcessor.PitchEstimationAlgorithm.YIN;   //Chooses the YIN algorithm
        processAudioBuffer(audioBuffer);                    //Start processing the audio buffer

    }
    private void processAudioBuffer(byte[]audioBuffer) throws UnsupportedAudioFileException {
        if (dispatcher != null) {   //If the dispatcher thread  is currently running it stops it
            dispatcher.stop();
        }
        //Variable to set up the AudioFormat
        float sampleRate=8000;
        int bufferSize=1024;
        int overlap=0;
        final AudioFormat audioFormat = new AudioFormat(sampleRate, 16, 1, true, true);

//        //Setting up the bytes as an input stream
//        InputStream inputStream = new ByteArrayInputStream(audioBuffer);
//        AudioInputStream audioInputStream = new AudioInputStream(inputStream, audioFormat, bufferSize);
//        JVMAudioInputStream jvmAudioInputStream = new JVMAudioInputStream(audioInputStream);



        //create a new dispatcher
        dispatcher = AudioDispatcherFactory.fromByteArray(audioBuffer, audioFormat, bufferSize, overlap);

        //add a processor
        dispatcher.addAudioProcessor(new PitchProcessor(algo,sampleRate,bufferSize,this ));
        dispatcher.run();
    }

    @Override
    public void handlePitch(PitchDetectionResult pitchDetectionResult, AudioEvent audioEvent) {
//        System.out.println("handdling pitch:\n\tpitcDetectionResults: "+ pitchDetectionResult.toString() +"\n\t\taudioevent: "+ audioEvent.toString());
        if(pitchDetectionResult.getPitch() != -1){
            double timeStamp = audioEvent.getTimeStamp();
            float pitch = pitchDetectionResult.getPitch();
            float probability = pitchDetectionResult.getProbability();
            double rms = audioEvent.getRMS() * 100;
            String message = String.format("Pitch detected at %.2fs: %.2fHz ( %.2f probability, RMS: %.5f\t name: %s)\n", timeStamp,pitch,probability,rms, Pitch.fromFrequency(pitch).getName());
            System.out.println(message);
        }
    }
    public static void main(String... strings) throws LineUnavailableException, UnsupportedAudioFileException {
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

        byte[] seq=generator.createPitchSequence(500,0.5, pitchList.toArray(new Pitch[pitchList.size()])) ;
        PracticePitchDetection pitchDetector = new PracticePitchDetection(seq);
//        println("Printing mixers:");
//        int i=0;
//        for (Mixer.Info info : mixers) {
//            println("Name["+ i++ + "]:"+info.getName() +"\n\t"
//            + "Description: "+info.getDescription()+ "\n\t"
//            + "Vendor: "+ info.getVendor()+ "\n\t"
//            + "Version: "+info.getVersion()+"\n");
//        }
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("4. exit");
            int c = sc.nextInt();
            if (c == 4)
                break;
        }
        pitchDetector.dispatcher.stop();

        System.out.print("Done");


    }
}
