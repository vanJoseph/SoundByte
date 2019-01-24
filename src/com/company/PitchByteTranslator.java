package com.company;

import java.util.ArrayList;
import java.util.List;

public class PitchByteTranslator {
    List<Pitch> pitchBuffer = new ArrayList<>();

    public List<Pitch> getPitchBuffer() {
        return pitchBuffer;
    }

    public PitchByteTranslator(Byte[] datagram) {
        for (byte b : datagram) {
            Pitch[] pitches=translateByteToPitch(b);
            pitchBuffer.add(pitches[0]);
            pitchBuffer.add(pitches[1]);
        }
    }

    public Pitch[] translateByteToPitch(byte byteO) {
        Pitch[] pitches = new Pitch[2];
        byte byte0 = (byte) ((byteO >>> 4) & 15);
        byte byte1 = (byte) ((byteO) & 15);
        return new Pitch[]{translateHexToPitch(byte0), translateHexToPitch(byte1)};
    }

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
