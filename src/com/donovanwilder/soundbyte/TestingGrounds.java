package com.donovanwilder.soundbyte;

import javax.sound.sampled.LineUnavailableException;

public class TestingGrounds {
    public static void main(String[] args) throws LineUnavailableException {
        TCPDatagram datagram = new TCPDatagram((short) 16, (short) 12, 10000, 52000, (byte) 45, true, false, true, false, true, false, (short) 564,
                (short) 345, (short) 345, 45000, new byte[]{(byte) 10, (byte) 23, (byte) 45, (byte) 678, (byte) 4321, (byte) 34});
        System.out.println(datagram.toString());
//        List<Byte> data = datagram.getDatagramList();
//
//
//        System.out.println("\n\n Datagram Bytes: \n");
//        for (int i = 0; i < data.size(); i++) {
//            System.out.println("byte " + i + ": " + data.get(i) + "\t" + Integer.toBinaryString(data.get(i)));
//        }
//
//        Byte[] data1 = datagram.getDatagramList().toArray(new Byte[]{});
//        System.out.println("\n\n Datagram Copy Bytes: \n");
//        for (int i = 0; i < data.size(); i++) {
//            System.out.println("byte " + i + ": " + data.get(i) + "\t" + Integer.toBinaryString(data.get(i)));
//        }
//        TCPDatagram datagramCopy = new TCPDatagram(TCPDatagram.convertTobyteArray(data1));
//        System.out.println("\n\n Datagram Copy:");
//        System.out.println(datagramCopy);

        Pitch[] pitches = new PitchByteTranslator(datagram.getDatagramList().toArray(new Byte[]{})).getPitchBuffer().toArray(new Pitch[]{});
        PitchGenerator pitchGenerator = new PitchGenerator();
        byte[] seq=pitchGenerator.createPitchSequence(50, .9, pitches);
        pitchGenerator.playSoundBuff(seq);
    }
}
