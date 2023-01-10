package com.donovanwilder.soundbyte;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a Utility class that helps with the translation of pitch to bytes and the translation of bytes to pitch.
 *
 * @author Donovan J. Wilder
 */
public class PitchByteTranslator {
    List<Pitch> pitchBuffer = new ArrayList<>();

    /**
     * Returns the pitch list of the translated bytes from the constructur.
     * @return  list of bytes translated from the constructor arguments
     */
    public List<Pitch> getPitchBuffer() {
        return pitchBuffer;
    }

    /**
     * Translate a series of bytes into a list of pitches. You can access it by invoking the getPitchBufferMethod.
     * @param datagram The byte array to translate into a pitch List.
     */
    public PitchByteTranslator(Byte[] datagram) {
        for (byte b : datagram) {
            Pitch[] pitches=translateByteToPitch(b);
            pitchBuffer.add(pitches[0]);
            pitchBuffer.add(pitches[1]);
        }
    }

    /**
     * Translate a byte into 2 pitchex. It returns 2 pitches because the pitches represents in hexadecimal 4 bits of the byte.
     * @param byteO The byte to be translated
     * @return The byte as 2 hexadecimal based pitches.
     */
    public Pitch[] translateByteToPitch(byte byteO) {
        Pitch[] pitches = new Pitch[2];
        byte byte0 = (byte) ((byteO >>> 4) & 15);
        byte byte1 = (byte) ((byteO) & 15);
        return new Pitch[]{translateHexToPitch(byte0), translateHexToPitch(byte1)};
    }

    /**
     * Translate the last for bytes of the byte and turn it into its pitch equivalent.
     * @param byteO The byte to be translated (only its last for bits)
     * @return The Pitch representation of the bytes last for bits.
     */
    public Pitch translateHexToPitch(byte byteO) {
        switch (byteO) {
            case 0:
                return Pitch.fromKeyNumber(48);
            case 1:
                return Pitch.fromKeyNumber(47);
            case 2:
                return Pitch.fromKeyNumber(46);
            case 3:
                return Pitch.fromKeyNumber(45);
            case 4:
                return Pitch.fromKeyNumber(44);
            case 5:
                return Pitch.fromKeyNumber(43);
            case 6:
                return Pitch.fromKeyNumber(42);
            case 7:
                return Pitch.fromKeyNumber(41);
            case 8:
                return Pitch.fromKeyNumber(40);
            case 9:
                return Pitch.fromKeyNumber(39);
            case 10:
                return Pitch.fromKeyNumber(38);
            case 11:
                return Pitch.fromKeyNumber(37);
            case 12:
                return Pitch.fromKeyNumber(36);
            case 13:
                return Pitch.fromKeyNumber(35);
            case 14:
                return Pitch.fromKeyNumber(34);
            case 15:
                return Pitch.fromKeyNumber(33);

        }
        return null;
    }
}
