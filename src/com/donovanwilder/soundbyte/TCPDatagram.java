package com.donovanwilder.soundbyte;

import java.util.ArrayList;
import java.util.List;


/**
 * This Structures a Datagram that should be sent over the SoundByte transmission.
 *
 * @author Donovan J. Wilder
 * @version 1.0
 * @since 01-17-2019
 */
public class TCPDatagram {
    List<Byte> datagramList = new ArrayList<>();
    private short sourceAddress;
    private short destAddress;
    private int sequenceNumber;
    private int ackNumber;
    private byte dataOffset;
    private boolean urgBit;
    private boolean ackBit;
    private boolean pshBit;
    private boolean rstBit;
    private boolean synBit;
    private boolean finBit;
    private short window;
    private short checksum;
    private short urgentPointer;
    private int options;
    private byte[] data;

    /**
     * Constructs a datagram from the individual components.
     * @param sourceAddress - The source address.
     * @param destAddress   - The destination address.
     * @param sequenceNumber - The sequence number of the first data octet in this segment (except
     *     when SYN is present). If SYN is present the sequence number is the
     *     initial sequence number (ISN) and the first data octet is ISN+1.
     * @param ackNumber - If the ACK control bit is set this field contains the value of the
     *     next sequence number the sender of the segment is expecting to
     *     receive.  Once a connection is established this is always sent.
     * @param dataOffset - The number of 32 bit words in the TCP Header.  This indicates where
     *     the data begins.  The TCP header (even one including options) is an
     *     integral number of 32 bits long.
     * @param urgBit - Urgent Pointer field significant
     * @param ackBit - Acknowledgment field significant
     * @param pshBit - Push Function
     * @param rstBit - Reset the connection
     * @param synBit - Synchronize sequence numbers
     * @param finBit - No more data from sender
     * @param window - The number of data octets beginning with the one indicated in the
     *     acknowledgment field which the sender of this segment is willing to
     *     accept.
     * @param checksum - The checksum field is the 16 bit one's complement of the one's
     *     complement sum of all 16 bit words in the header and text.  If a
     *     segment contains an odd number of header and text octets to be
     *     checksummed, the last octet is padded on the right with zeros to
     *     form a 16 bit word for checksum purposes.  The pad is not
     *     transmitted as part of the segment.  While computing the checksum,
     *     the checksum field itself is replaced with zeros.
     *
     *     The checksum also covers a 96 bit pseudo header conceptuallyprefixed to the TCP header.  This pseudo header contains the Source
     *     Address, the Destination Address, the Protocol, and TCP length.
     *     This gives the TCP protection against misrouted segments.  This
     *     information is carried in the Internet Protocol and is transferred
     *     across the TCP/Network interface in the arguments or results of
     *     calls by the TCP on the IP.
     *
     *                      +--------+--------+--------+--------+
     *                      |           Source Address          |
     *                      +--------+--------+--------+--------+
     *                      |         Destination Address       |
     *                      +--------+--------+--------+--------+
     *                      |  zero  |  PTCL  |    TCP Length   |
     *                      +--------+--------+--------+--------+
     *
     *       The TCP Length is the TCP header length plus the data length in
     *       octets (this is not an explicitly transmitted quantity, but is
     *       computed), and it does not count the 12 octets of the pseudo
     *       header.
     * @param urgentPointer - This field communicates the current value of the urgent pointer as a
     *     positive offset from the sequence number in this segment.  The
     *     urgent pointer points to the sequence number of the octet following
     *     the urgent data.  This field is only be interpreted in segments with
     *     the URG control bit set.
     * @param options - Options may occupy space at the end of the TCP header and are a
     *     multiple of 8 bits in length.  All options are included in the
     *     checksum.  An option may begin on any octet boundary.  There are two
     *     cases for the format of an option:
     *
     *       Case 1:  A single octet of option-kind.
     *
     *       Case 2:  An octet of option-kind, an octet of option-length, and
     *                the actual option-data octets.
     *
     *     The option-length counts the two octets of option-kind and
     *     option-length as well as the option-data octets.
     *
     *     Note that the list of options may be shorter than the data offset
     *     field might imply.  The content of the header beyond the
     *     End-of-Option option must be header padding (i.e., zero).
     *
     *     A TCP must implement all options.
     *                Currently defined options include (kind indicated in octal):
     *
     *       Kind     Length    Meaning
     *       ----     ------    -------
     *        0         -       End of option list.
     *        1         -       No-Operation.
     *        2         4       Maximum Segment Size.
     *
     *
     *     Specific Option Definitions
     *
     *       End of Option List
     *
     *         +--------+
     *         |00000000|
     *         +--------+
     *          Kind=0
     *
     *         This option code indicates the end of the option list.  This
     *         might not coincide with the end of the TCP header according to
     *         the Data Offset field.  This is used at the end of all options,
     *         not the end of each option, and need only be used if the end of
     *         the options would not otherwise coincide with the end of the TCP
     *         header.
     *
     *       No-Operation
     *
     *         +--------+
     *         |00000001|
     *         +--------+
     *          Kind=1
     *
     *         This option code may be used between options, for example, to
     *         align the beginning of a subsequent option on a word boundary.
     *         There is no guarantee that senders will use this option, so
     *         receivers must be prepared to process options even if they do
     *         not begin on a word boundary.
     *
     *       Maximum Segment Size
     *
     *         +--------+--------+---------+--------+
     *         |00000010|00000100|   max seg size   |
     *         +--------+--------+---------+--------+
     *          Kind=2   Length=4
     *                Maximum Segment Size Option Data:  16 bits
     *
     *           If this option is present, then it communicates the maximum
     *           receive segment size at the TCP which sends this segment.
     *           This field must only be sent in the initial connection request
     *           (i.e., in segments with the SYN control bit set).  If this
     *           option is not used, any segment size is allowed.
     *
     * @param data - The data that will be sent.
     */
    public TCPDatagram(short sourceAddress, short destAddress,
                       int sequenceNumber, int ackNumber, byte dataOffset,
                       boolean urgBit, boolean ackBit, boolean pshBit,
                       boolean rstBit, boolean synBit, boolean finBit,
                       short window, short checksum, short urgentPointer,
                       int options, byte[] data) {
        setSourceAddress(sourceAddress);
        setDestAddress(destAddress);
        setSequenceNumber(sequenceNumber);
        setAckNumber(ackNumber);
        setDataOffset(dataOffset);
        setUrgBit(urgBit);
        setAckBit(ackBit);
        setPshBit(pshBit);
        setRstBit(rstBit);
        setSynBit(synBit);
        setFinBit(finBit);
        setWindow(window);
        setChecksum(checksum);
        setUrgentPointer(urgentPointer);
        setOptions(options);
        setData(data);
        compileGetHeader();
    }


    public TCPDatagram(byte[] datagram) {
        addBytes(datagram);
        scrubDatagram();

    }
    public static byte[]convertTobyteArray(Byte[] byteArray){
        byte[] buffer = new byte[byteArray.length];

        for (int i = 0; i < buffer.length; i++) {
            buffer[i]=byteArray[i];
        }

        return buffer;

    }

    public TCPDatagram() {

    }
//ToDo need a method to create padding so that it ends on a  32-bit boundary

    public static void main(String[] args) {
//        int number = 2_003_406_666;
//        print("The number is:  " + number);
//        print("The byte value is:  " + Integer.toBinaryString(number));
//        byte[] bytesArray = intToByteArray(number);
//        print("The byte array is:");
//        for (byte bin : bytesArray) {
//            print(Integer.toBinaryString(bin));

        print("Int to bytes:");
        short number= -127 ;
        byte[] bytes = shortToByteArray(number);
        int index=0;
        print("Number: " + number);
        for(byte b: bytes){
            print(index++ + ": " +Integer.toBinaryString(b));
        }

        print("\n\nBytes to Int:");
        print("Number is: "+ bytesToShort(bytes));
        print("Binary is: " + Integer.toBinaryString(bytesToShort(bytes)));
    }

    private static void print(String message) {
        System.out.println(message);
    }

    public static final byte[] intToByteArray(int value) {
        return new byte[]{
                (byte) (value >>> 24),
                (byte) (value >>> 16),
                (byte) (value >>> 8),
                (byte) value};
    }

    public static final byte[] shortToByteArray(short value) {
        return new byte[]{
                (byte) (value >>> 8),
                (byte) value};
    }

    public static int bytesToInt(byte[] bytes) {
        int int00 = bytes[0]&255;
        int int01 = bytes[1]&255;
        int int02 = bytes[2]&255;
        int int03 = bytes[3]&255;

        return (int00<<24)|(int01<<16)|(int02<<8)|(int03);
    }

    public static short bytesToShort(byte[] bytes) {

        int int00 = bytes[0]&255;
        int int01 = bytes[1]&255;
          return (short)((int00<<8)|(int01));
    }

    private void scrubData() {

        datagramList.get(24);
        byte[] buffer= new byte[datagramList.size()-(25)];
        for (int i = 0; i < buffer.length; i++) {
            buffer[i]=datagramList.get(i+24);
        }
        setData(buffer);
    }

    private void scrubOptions() {
        byte[] buffer = new byte[4];
        buffer[0] = datagramList.get(20);
        buffer[1] = datagramList.get(21);
        buffer[2] = datagramList.get(22);
        buffer[3] = datagramList.get(23);
        setOptions( bytesToInt(buffer));
    }

    public List<Byte> getDatagramList() {
        return datagramList;
    }

    public short getSourceAddress() {
        return sourceAddress;
    }

    public void setSourceAddress(short sourceAddress) {
        this.sourceAddress = sourceAddress;
    }

    public short getDestAddress() {
        return destAddress;
    }

    public void setDestAddress(short destAddress) {
        this.destAddress = destAddress;
    }

    public int getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(int sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public int getAckNumber() {
        return ackNumber;
    }

    public void setAckNumber(int ackNumber) {
        this.ackNumber = ackNumber;
    }

    public byte getDataOffset() {
        return dataOffset;
    }

    public void setDataOffset(byte dataOffset) {

        this.dataOffset = (byte) (dataOffset);
    }

    public short getWindow() {
        return window;
    }

    public void setWindow(short window) {
        this.window = window;
    }

    public short getChecksum() {
        return checksum;
    }

    public void setChecksum(short checksum) {
        this.checksum = checksum;
    }

    public short getUrgentPointer() {
        return urgentPointer;
    }

    public void setUrgentPointer(short urgentPointer) {
        this.urgentPointer = urgentPointer;
    }

    public int getOptions() {
        return options;
    }

    public void setOptions(int options) {
        this.options = options;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public byte[] scrubDatagramBytes() {
        compileGetHeader();
        int size = datagramList.size();
        byte[] out = new byte[size];
        for (int i = 0; i < size; i++) {
            out[i] = datagramList.get(i);
        }
        return out;
    }
    private void scrubDatagram() {
        scrubSourceAddress();
        scrubDestAddress();
        scrubSequenceNumber();
        scrubAckNumber();
        scrubDataOffset();
        scrubControlBits();
        scrubWindow();
        scrubChecksum();
        scrubUrgentPointer();
        scrubOptions();
        scrubData();
    }
    private void addBytes(byte[] bytes) {
        for (byte singleByte : bytes) {
            datagramList.add(singleByte);
        }
    }

    private void addSourceAddress(short scrubOrGet) {
        byte[] sourceAddressBytes = shortToByteArray(scrubOrGet);
        addBytes(sourceAddressBytes);
    }

    private void addDestAddress(short scrubOrGet) {
        byte[] destAddressBytes = shortToByteArray(scrubOrGet);
        addBytes(destAddressBytes);
    }

    private void addSequenceNumber(int scrubOrGet) {
        byte[] sequenceNuberBytes = intToByteArray(scrubOrGet);
        addBytes(sequenceNuberBytes);
    }

    private void addAckNumber(int scrubOrGet) {
        byte[] ackNumberBytes = intToByteArray(scrubOrGet);
        addBytes(ackNumberBytes);
    }

    private void addDataOffset(byte scrubOrGet) {
        byte dataOffset = (byte) (scrubOrGet << 2);
        datagramList.add(dataOffset);


    }

    private void addControlBits() {
        byte controlBits = 0b00000000;
        if (isUrgBit())
            controlBits |= 0b00100000;
        if (isAckBit())
            controlBits |= 0b00010000;
        if (isPshBit())
            controlBits |= 0b00001000;
        if (isRstBit())
            controlBits |= 0b00000100;
        if (isSynBit())
            controlBits |= 0b00000010;
        if (isFinBit())
            controlBits |= 0b00000001;
        datagramList.add(controlBits);
    }

    private void compileGetHeader() {
        addSourceAddress(getSourceAddress());
        addDestAddress(getDestAddress());
        addSequenceNumber(getSequenceNumber());
        addAckNumber(getAckNumber());
        addDataOffset(getDataOffset());
        addControlBits();
        addWindow(getWindow());
        addChecksum(getChecksum());
        addUrgentPointer(getUrgentPointer());
        addOptions(getOptions());
        addData(getData());
    }

    private void addWindow(short scrubOrGet) {
        byte[] windowBytes = shortToByteArray(scrubOrGet);
        addBytes(windowBytes);
    }

    private void addChecksum(short scrubOrGet) {
        byte[] checksumBytes = shortToByteArray(scrubOrGet);
        addBytes(checksumBytes);
    }

    private void addUrgentPointer(short scrubOrGet) {
        byte[] urgentPointerBytes = shortToByteArray(scrubOrGet);
        addBytes(urgentPointerBytes);
    }

    private void addOptions(int scrubOrGet) {
        byte[] optionsBytes = intToByteArray(scrubOrGet);
        addBytes(optionsBytes);
    }

    private void addData(byte[] scrubOrGet) {
        addBytes(scrubOrGet);
    }

    private void scrubDatagramList() {
        scrubSourceAddress();
        scrubDestAddress();
        scrubSequenceNumber();
        scrubAckNumber();
        scrubDataOffset();
        scrubControlBits();
        scrubWindow();
        scrubChecksum();
        scrubUrgentPointer();
        scrubOptions();
        scrubData();
    }

    private void scrubSourceAddress() {

        byte[] buffer = new byte[2];
        buffer[0]=datagramList.get(0);
        buffer[1]=datagramList.get(1);

        setSourceAddress(bytesToShort(buffer));


    }

    private void scrubControlBits() {
        byte buffer = datagramList.get(13);
        setFinBit(((buffer % 2) == 0 )? false : true);
        setSynBit((((buffer>>>1) % 2) == 0) ? false : true);
        setRstBit((((buffer>>>2)% 2 )== 0 )? false : true);
        setPshBit((((buffer>>>3) % 2 )== 0 )? false : true);
        setAckBit((((buffer>>>4) % 2 )== 0) ? false : true);
        setUrgBit((((buffer>>>5) % 2) == 0 )? false : true);
    }

    private void scrubDestAddress() {
        byte[] buffer = new byte[2];
        buffer[0] = datagramList.get(2);
        buffer[1] = datagramList.get(3);

        setDestAddress(bytesToShort(buffer));
    }

    private void scrubSequenceNumber() {
        byte[] buffer = new byte[4];
        buffer[0] = datagramList.get(4);
        buffer[1] = datagramList.get(5);
        buffer[2] = datagramList.get(6);
        buffer[3] = datagramList.get(7);
        setSequenceNumber(bytesToInt(buffer));
    }

    private void scrubAckNumber() {
        byte[] buffer = new byte[4];
        buffer[0] = datagramList.get(8);
        buffer[1] = datagramList.get(9);
        buffer[2] = datagramList.get(10);
        buffer[3] = datagramList.get(11);
        setAckNumber(bytesToInt(buffer));
    }

    private void scrubDataOffset() {
        byte buffer = datagramList.get(12);
        setDataOffset((byte)((buffer>>>2)&63));
    }

    public boolean isUrgBit() {
        return urgBit;
    }

    public void setUrgBit(boolean urgBit) {
        this.urgBit = urgBit;
    }

    public boolean isAckBit() {
        return ackBit;
    }

    public void setAckBit(boolean ackBit) {
        this.ackBit = ackBit;
    }

    public boolean isPshBit() {
        return pshBit;
    }

    public void setPshBit(boolean pshBit) {
        this.pshBit = pshBit;
    }

    public boolean isRstBit() {
        return rstBit;
    }

    public void setRstBit(boolean rstBit) {
        this.rstBit = rstBit;
    }

    public boolean isSynBit() {
        return synBit;
    }

    public void setSynBit(boolean synBit) {
        this.synBit = synBit;
    }

    public boolean isFinBit() {
        return finBit;
    }

    public void setFinBit(boolean finBit) {
        this.finBit = finBit;
    }

    private void scrubWindow() {
        byte[] buffer = new byte[2];
        buffer[0] = datagramList.get(14);
        buffer[1] = datagramList.get(15);
        setWindow(bytesToShort(buffer));
    }

    private void scrubChecksum() {
        byte[] buffer = new byte[2];
        buffer[0] = datagramList.get(16);
        buffer[1] = datagramList.get(17);
        setChecksum(bytesToShort(buffer));
    }

    private void scrubUrgentPointer() {
        byte[] buffer = new byte[2];
        buffer[0] = datagramList.get(18);
        buffer[1] = datagramList.get(19);
        setUrgentPointer(bytesToShort(buffer));
    }

    @Override
    public String toString() {
        String message= "Source Address: "+ getSourceAddress()+ "\n"+
                        "Destination Address: "+ getDestAddress()+"\n"+
                        "Sequence Number: " + getSequenceNumber()+"\n"+
                        "Ack Number: " + getAckNumber()+"\n"+
                        "Data Offset: "+ getDataOffset() +"\n"+
                        "URG: " + isUrgBit()+"\n"+
                        "ACK: " + isAckBit()+"\n"+
                        "PSH: " + isPshBit()+"\n"+
                        "RST: " + isRstBit()+"\n"+
                        "SYN: " + isSynBit()+"\n"+
                        "FIN: " + isFinBit()+"\n"+
                        "Window: " + getWindow()+"\n"+
                        "Checksum: "+ getChecksum()+"\n"+
                        "Urgent Pointer: " + getUrgentPointer()+"\n"+
                        "Options: "+ getOptions()+"\n"+
                        "Data: " + getData() + "\n";
        return message;

    }
}
