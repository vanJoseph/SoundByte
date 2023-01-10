package com.donovanwilder.soundbyte;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundRecorder {
    final long RECORD_TIME;
    File wavFile;
    AudioFileFormat.Type fileType = AudioFileFormat.Type.WAVE;
    TargetDataLine line;

    public SoundRecorder(long duration, File destination) {
        RECORD_TIME = duration;
        wavFile = destination;
    }


    public void startRecording() {
        final SoundRecorder recorder = new SoundRecorder(RECORD_TIME, wavFile);

        Thread stopper = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(RECORD_TIME);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                recorder.finish();
            }
        });

        stopper.start();
        recorder.start();
    }

    public static void main(String[] args) {


    }
    private AudioFormat getAudioFormat() {
        float sampleRate = 16000;
        int sampleSizeInBits = 8;
        int channels = 2;
        boolean signed = true;
        boolean bigEndian = true;
        AudioFormat format = new AudioFormat(sampleRate, sampleSizeInBits, channels, signed, bigEndian);
        return format;
    }

    private void start() {
        try {
            AudioFormat format = getAudioFormat();
            DataLine.Info info = new DataLine.Info(TargetDataLine.class, getAudioFormat());

            if (!AudioSystem.isLineSupported(info)) {
                println("Line not supported");
                System.exit(0);
            }
            line = (TargetDataLine) AudioSystem.getLine(info);
            line.open(format);
            line.start();
            println("Start capturing...");
            AudioInputStream ais = new AudioInputStream(line);
            println("Start recording...");
            AudioSystem.write(ais, fileType, wavFile);
        } catch (LineUnavailableException ex) {
            ex.printStackTrace();

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private void finish() {
        line.stop();
        line.close();
        println("Finished");
    }

    public static void println(String message) {
        System.out.println(message);
    }
}
