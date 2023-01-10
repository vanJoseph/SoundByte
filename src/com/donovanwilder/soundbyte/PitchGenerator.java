package com.donovanwilder.soundbyte;

import javax.sound.sampled.*;
import java.util.ArrayList;
import java.util.List;
//https://www.thecodingforums.com/threads/frequency-tone-generator-in-java-tia-sal22.739012/

/**
 * This class is used to create sound byte buffers.
 *
 * @author Donovan J. Wilder
 */
public class PitchGenerator {
    public static float SAMPLE_RATE = 44100f;
//Todo add a way to change the sample rate

    /**
     * Creates a wave byte buffer.
     *
     * @param hz the frequency of the the pitch
     * @param msecs the duration the pitch should last
     * @param vol the volume at which the pitch should
     * @return the sound byte buffer of the
     */
    public byte[] createPitchWave(int hz, int msecs, double vol) {

        if (hz <= 0)
            throw new IllegalArgumentException("Frequency <= 0 hz");

        if (msecs <= 0)
            throw new IllegalArgumentException("Duration <= 0 msecs");

        if (vol > 1.0 || vol < 0.0)
            throw new IllegalArgumentException("Volume out of range 0.0 - 1.0");

        byte[] buf = new byte[(int) SAMPLE_RATE * msecs / 1000];

        for (int i = 0; i < buf.length; i++) {
            double angle = i / (SAMPLE_RATE / hz) * 2.0 * Math.PI;
            buf[i] = (byte) (Math.sin(angle) * 127.0 * vol); //modified
        }

        // shape the front and back 10ms of the wave form
        for (int i = 0; i < SAMPLE_RATE / 100.0 && i < buf.length / 2; i++) {
            buf[i] = (byte) (buf[i] * i / (SAMPLE_RATE / 100.0));
            buf[buf.length - 1 - i] =
                    (byte) (buf[buf.length - 1 - i] * i / (SAMPLE_RATE / 100.0));
        }
        return buf;
    }

    /**
     * Create the sound wave bytes by entering the Pitch.
     *
     * @param pitch the pitch at which the byte buffer should be
     * @param msecs the duration of the byte bufffer
     * @param vol the volume at which the sound should be played.
     * @return the sound byte buffer of the wave pitch
     */
    public byte[] createPitchWave(Pitch pitch, int msecs, double vol) {
        int fequency = (int)pitch.getFrequency();
        return createPitchWave(fequency, msecs, vol);

    }

    /**
     * Create a sound byte buffer of a sequence of pitches.
     *
     * @param msecs the duration of all the pitches
     * @param vol the volume it should be at
     * @param pitches the array of pitches
     * @return a sound byte buffer of the sequence of pitches.
     */
    public byte[] createPitchSequence(int msecs, double vol, Pitch... pitches) {

        if (pitches.equals(null)) {
            return null;
        }
        byte[] sequence=null;
        for (Pitch pitch : pitches) {
            byte[] newWave = createPitchWave(pitch, msecs, vol);
            sequence = (sequence==null) ? newWave : concatBuffers(sequence, newWave);
        }
        return sequence;
    }

    /**
     * Plays the sound buffer audio.
     *
     * @param soundwave the sound byte buffer to play
     * @throws LineUnavailableException
     */
    public void playSoundBuff(byte [] soundwave) throws LineUnavailableException {
        AudioFormat af = new AudioFormat(SAMPLE_RATE,8,1,true,false);
        SourceDataLine sdl = AudioSystem.getSourceDataLine(af);
        sdl.open(af);
        sdl.start();
        sdl.write(soundwave,0,soundwave.length);
        sdl.drain();
        sdl.close();
    }

    /**
     * Concatenates  two byte buffers together
     *
     * @param wave1 the first byte buffer
     * @param wave2 the second byte buffer
     * @return the 2 byte buffer concatenated
     */
    private byte[] concatBuffers(byte[] wave1, byte[] wave2) {
        byte[] c = new byte[wave1.length + wave2.length];
        System.arraycopy(wave1, 0, c, 0,wave1.length);
        System.arraycopy(wave2,0,c,wave1.length,wave2.length);
        return c;
    }
    public static void main(String[] args) throws LineUnavailableException {
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
        generator.playSoundBuff(seq);
    }
}
