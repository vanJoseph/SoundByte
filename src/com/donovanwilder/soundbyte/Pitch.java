package com.donovanwilder.soundbyte;


/**
 * This class contains the pitch frequency for the 88 keyes on the piano
 *
 * @author Donovan J. Wilder
 */
public enum Pitch {
    C8(88, 4186.009d, "C8", ""),
    B7(87, 3951.066d, "B7", ""),
    As7(86, 3729.310d, "As7", "Bf7"),
    A7(85, 3520.000d, "A7", ""),
    Gs7(84, 3322.438d, "Gs7", "Af7"),
    G7(83, 3135.963d, "G7", ""),
    Fs7(82, 2959.955d, "Fs7", "Gf7"),
    F7(81, 2793.826d, "F7", ""),
    E7(80, 2637.020d, "E7", ""),
    Ds7(79, 2489.016d, "Ds7", "Ef7"),
    D7(78, 2349.318d, "D7", ""),
    Cs7(77, 2217.461d, "Cs7", "Df7"),
    C7(76, 2093.005d, "C7", ""),
    B6(75, 1975.533d, "B6", ""),
    As6(74, 1864.655d, "As6", "Bf6"),
    A6(73, 1760.000d, "A6", ""),
    Gs6(72, 1661.219d, "Gs6", "Af6"),
    G6(71, 1567.982d, "G6", ""),
    Fs6(70, 1479.978d, "Fs6", "Gf6"),
    F6(69, 1396.913d, "F6", ""),
    E6(68, 1318.510d, "E6", ""),
    Ds6(67, 1244.508d, "Ds6", "Ef6"),
    D6(66, 1174.659d, "D6", ""),
    Cs6(65, 1108.731d, "Cs6", "Df6"),
    C6(64, 1046.502d, "C6", ""),
    B5(63, 987.7666d, "B5", ""),
    As5(62, 932.3275d, "As5", "Bf5"),
    A5(61, 880.0000d, "A5", ""),
    Gs5(60, 830.6094d, "Gs5", "Af5"),
    G5(59, 783.9909d, "G5", ""),
    Fs5(58, 739.9888d, "Fs5", "Gf5"),
    F5(57, 698.4565d, "F5", ""),
    E5(56, 659.2551d, "E5", ""),
    Ds5(55, 622.2540d, "Ds5", "Ef5"),
    D5(54, 587.3295d, "D5", ""),
    Cs5(53, 554.3653d, "Cs5", "Df5"),
    C5(52, 523.2511d, "C5", ""),
    B4(51, 493.8833d, "B4", ""),
    As4(50, 466.1638d, "As4", "Bf4"),
    A4(49, 440.0000d, "A4", ""),
    Gs4(48, 415.3047d, "Gs4", "Af4"),
    G4(47, 391.9954d, "G4", ""),
    Fs4(46, 369.9944d, "Fs4", "Gf4"),
    F4(45, 349.2282d, "F4", ""),
    E4(44, 329.6276d, "E4", ""),
    Ds4(43, 311.1270d, "Ds4", "Ef4"),
    D4(42, 293.6648d, "D4", ""),
    Cs4(41, 277.1826d, "Cs4", "Df4"),
    C4(40, 261.6256d, "C4", ""),
    B3(39, 246.9417d, "B3", ""),
    As3(38, 233.0819d, "As3", "Bf3"),
    A3(37, 220.0000d, "A3", ""),
    Gs3(36, 207.6523d, "Gs3", "Af3"),
    G3(35, 195.9977d, "G3", ""),
    Fs3(34, 184.9972d, "Fs3", "Gf3"),
    F3(33, 174.6141d, "F3", ""),
    E3(32, 164.8138d, "E3", ""),
    Ds3(31, 155.5635d, "Ds3", "Ef3"),
    D3(30, 146.8324d, "D3", ""),
    Cs3(29, 138.5913d, "Cs3", "Df3"),
    C3(28, 130.8128d, "C3", ""),
    B2(27, 123.4708d, "B2", ""),
    As2(26, 116.5409d, "As2", "Bf2"),
    A2(25, 110.0000d, "A2", ""),
    Gs2(24, 103.8262d, "Gs2", "Af2"),
    G2(23, 97.99886d, "G2", ""),
    Fs2(22, 92.49861d, "Fs2", "Gf2"),
    F2(21, 87.30706d, "F2", ""),
    E2(20, 82.40689d, "E2", ""),
    Ds2(19, 77.78175d, "Ds2", "Ef2"),
    D2(18, 73.41619d, "D2", ""),
    Cs2(17, 69.29566d, "Cs2", "Df2"),
    C2(16, 65.40639d, "C2", ""),
    B1(15, 61.73541d, "B1", ""),
    As1(14, 58.27047d, "As1", "Bf1"),
    A1(13, 55.00000d, "A1", ""),
    Gs1(12, 51.91309d, "Gs1", "Af1"),
    G1(11, 48.99943d, "G1", ""),
    Fs1(10, 46.24930d, "Fs1", "Gf1"),
    F1(9, 43.65353d, "F1", ""),
    E1(8, 41.20344d, "E1", ""),
    Ds1(7, 38.89087d, "Ds1", "Ef1"),
    D1(6, 36.70810d, "D1", ""),
    Cs1(5, 34.64783d, "Cs1", "Df1"),
    C1(4, 32.70320d, "C1", ""),
    B0(3, 30.86771d, "B0", ""),
    As0(2, 29.13524d, "As0", "Bf0"),
    A0(1, 27.50000d, "A0", "");

    /**
     *
     * @return returns the Key number grom a piano as the pitch
     */
    public int getKeyNumber() {
        return keyNumber;
    }

    /**
     *
     * @return returns the frequency of the pitch
     */
    public double getFrequency() {
        return frequency;
    }

    /**
     *
     * @return returns the name of the pitch if it has 2 names it returns the one thats sharp
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return returns the second name of the pitch if it has 2 names  it returns the one thats flat if itha no flats it returns ""
     */
    public String getSecondName() {
        return secondName;
    }

    private final int keyNumber;
    private final double frequency;
    private final String name;
    private final String secondName;

    private Pitch(int keyNumber, double frequency, String name, String secondName) {
        this.keyNumber = keyNumber;
        this.frequency = frequency;
        this.name = name;
        this.secondName = secondName;
    }

    /**
     *
     * @return returns  the names  key number and frequency of a string
     */
    @Override
    public String toString() {
        String names= name;
        if (!secondName.equals("")) {
            names= names+ ", " + secondName;
        }
        return "names: "+ names+ "\tkeyNumber: "+keyNumber+ "\tfrequency: "+ frequency;
    }

    /**
     * Return a Pitch from the Key number
     * @param keyNumber The key number from a piano
     * @return a Pitch
     */
    public static Pitch fromKeyNumber(int keyNumber) {
        switch (keyNumber) {
            case 88:
                return Pitch.C8;
            case 87:
                return Pitch.B7;
            case 86:
                return Pitch.As7;
            case 85:
                return Pitch.A7;
            case 84:
                return Pitch.Gs7;
            case 83:
                return Pitch.G7;
            case 82:
                return Pitch.Fs7;
            case 81:
                return Pitch.F7;
            case 80:
                return Pitch.E7;
            case 79:
                return Pitch.Ds7;
            case 78:
                return Pitch.D7;
            case 77:
                return Pitch.Cs7;
            case 76:
                return Pitch.C7;
            case 75:
                return Pitch.B6;
            case 74:
                return Pitch.As6;
            case 73:
                return Pitch.A6;
            case 72:
                return Pitch.Gs6;
            case 71:
                return Pitch.G6;
            case 70:
                return Pitch.Fs6;
            case 69:
                return Pitch.F6;
            case 68:
                return Pitch.E6;
            case 67:
                return Pitch.Ds6;
            case 66:
                return Pitch.D6;
            case 65:
                return Pitch.Cs6;
            case 64:
                return Pitch.C6;
            case 63:
                return Pitch.B5;
            case 62:
                return Pitch.As5;
            case 61:
                return Pitch.A5;
            case 60:
                return Pitch.Gs5;
            case 59:
                return Pitch.G5;
            case 58:
                return Pitch.Fs5;
            case 57:
                return Pitch.F5;
            case 56:
                return Pitch.E5;
            case 55:
                return Pitch.Ds5;
            case 54:
                return Pitch.D5;
            case 53:
                return Pitch.Cs5;
            case 52:
                return Pitch.C5;
            case 51:
                return Pitch.B4;
            case 50:
                return Pitch.As4;
            case 49:
                return Pitch.A4;
            case 48:
                return Pitch.Gs4;
            case 47:
                return Pitch.G4;
            case 46:
                return Pitch.Fs4;
            case 45:
                return Pitch.F4;
            case 44:
                return Pitch.E4;
            case 43:
                return Pitch.Ds4;
            case 42:
                return Pitch.D4;
            case 41:
                return Pitch.Cs4;
            case 40:
                return Pitch.C4;
            case 39:
                return Pitch.B3;
            case 38:
                return Pitch.As3;
            case 37:
                return Pitch.A3;
            case 36:
                return Pitch.Gs3;
            case 35:
                return Pitch.G3;
            case 34:
                return Pitch.Fs3;
            case 33:
                return Pitch.F3;
            case 32:
                return Pitch.E3;
            case 31:
                return Pitch.Ds3;
            case 30:
                return Pitch.D3;
            case 29:
                return Pitch.Cs3;
            case 28:
                return Pitch.C3;
            case 27:
                return Pitch.B2;
            case 26:
                return Pitch.As2;
            case 25:
                return Pitch.A2;
            case 24:
                return Pitch.Gs2;
            case 23:
                return Pitch.G2;
            case 22:
                return Pitch.Fs2;
            case 21:
                return Pitch.F2;
            case 20:
                return Pitch.E2;
            case 19:
                return Pitch.Ds2;
            case 18:
                return Pitch.D2;
            case 17:
                return Pitch.Cs2;
            case 16:
                return Pitch.C2;
            case 15:
                return Pitch.B1;
            case 14:
                return Pitch.As1;
            case 13:
                return Pitch.A1;
            case 12:
                return Pitch.Gs1;
            case 11:
                return Pitch.G1;
            case 10:
                return Pitch.Fs1;
            case 9:
                return Pitch.F1;
            case 8:
                return Pitch.E1;
            case 7:
                return Pitch.Ds1;
            case 6:
                return Pitch.D1;
            case 5:
                return Pitch.Cs1;
            case 4:
                return Pitch.C1;
            case 3:
                return Pitch.B0;
            case 2:
                return Pitch.As0;
            case 1:
                return Pitch.A0;
            default:
                throw new RuntimeException("Invalid Value: " + keyNumber);
        }

    }

    /**
     * Return a Pitch from the frequency
     * @param frequency The frequency of a pitch
     * @return a Pitch
     */
    public static Pitch fromFrequency(double frequency) {
        for (Pitch pitch : Pitch.values()) {
            if (frequency >= pitch.frequency) {
                return pitch;
            }
        }
        return null;
    }

    /**
     * Return a Pitch from the name whether it is the first or second name.
     * @param name the name of the pitch
     * @return a Pitch
     */
    public static Pitch fromName(String name) {
        switch (name) {
            case "C8":
                return Pitch.C8;
            case "B7":
                return Pitch.B7;
            case "As7":
                return Pitch.As7;
            case "A7":
                return Pitch.A7;
            case "Gs7":
                return Pitch.Gs7;
            case "G7":
                return Pitch.G7;
            case "Fs7":
                return Pitch.Fs7;
            case "F7":
                return Pitch.F7;
            case "E7":
                return Pitch.E7;
            case "Ds7":
                return Pitch.Ds7;
            case "D7":
                return Pitch.D7;
            case "Cs7":
                return Pitch.Cs7;
            case "C7":
                return Pitch.C7;
            case "B6":
                return Pitch.B6;
            case "As6":
                return Pitch.As6;
            case "A6":
                return Pitch.A6;
            case "Gs6":
                return Pitch.Gs6;
            case "G6":
                return Pitch.G6;
            case "Fs6":
                return Pitch.Fs6;
            case "F6":
                return Pitch.F6;
            case "E6":
                return Pitch.E6;
            case "Ds6":
                return Pitch.Ds6;
            case "D6":
                return Pitch.D6;
            case "Cs6":
                return Pitch.Cs6;
            case "C6":
                return Pitch.C6;
            case "B5":
                return Pitch.B5;
            case "As5":
                return Pitch.As5;
            case "A5":
                return Pitch.A5;
            case "Gs5":
                return Pitch.Gs5;
            case "G5":
                return Pitch.G5;
            case "Fs5":
                return Pitch.Fs5;
            case "F5":
                return Pitch.F5;
            case "E5":
                return Pitch.E5;
            case "Ds5":
                return Pitch.Ds5;
            case "D5":
                return Pitch.D5;
            case "Cs5":
                return Pitch.Cs5;
            case "C5":
                return Pitch.C5;
            case "B4":
                return Pitch.B4;
            case "As4":
                return Pitch.As4;
            case "A4":
                return Pitch.A4;
            case "Gs4":
                return Pitch.Gs4;
            case "G4":
                return Pitch.G4;
            case "Fs4":
                return Pitch.Fs4;
            case "F4":
                return Pitch.F4;
            case "E4":
                return Pitch.E4;
            case "Ds4":
                return Pitch.Ds4;
            case "D4":
                return Pitch.D4;
            case "Cs4":
                return Pitch.Cs4;
            case "C4":
                return Pitch.C4;
            case "B3":
                return Pitch.B3;
            case "As3":
                return Pitch.As3;
            case "A3":
                return Pitch.A3;
            case "Gs3":
                return Pitch.Gs3;
            case "G3":
                return Pitch.G3;
            case "Fs3":
                return Pitch.Fs3;
            case "F3":
                return Pitch.F3;
            case "E3":
                return Pitch.E3;
            case "Ds3":
                return Pitch.Ds3;
            case "D3":
                return Pitch.D3;
            case "Cs3":
                return Pitch.Cs3;
            case "C3":
                return Pitch.C3;
            case "B2":
                return Pitch.B2;
            case "As2":
                return Pitch.As2;
            case "A2":
                return Pitch.A2;
            case "Gs2":
                return Pitch.Gs2;
            case "G2":
                return Pitch.G2;
            case "Fs2":
                return Pitch.Fs2;
            case "F2":
                return Pitch.F2;
            case "E2":
                return Pitch.E2;
            case "Ds2":
                return Pitch.Ds2;
            case "D2":
                return Pitch.D2;
            case "Cs2":
                return Pitch.Cs2;
            case "C2":
                return Pitch.C2;
            case "B1":
                return Pitch.B1;
            case "As1":
                return Pitch.As1;
            case "A1":
                return Pitch.A1;
            case "Gs1":
                return Pitch.Gs1;
            case "G1":
                return Pitch.G1;
            case "Fs1":
                return Pitch.Fs1;
            case "F1":
                return Pitch.F1;
            case "E1":
                return Pitch.E1;
            case "Ds1":
                return Pitch.Ds1;
            case "D1":
                return Pitch.D1;
            case "Cs1":
                return Pitch.Cs1;
            case "C1":
                return Pitch.C1;
            case "B0":
                return Pitch.B0;
            case "As0":
                return Pitch.As0;
            case "A0":
                return Pitch.A0;
            case "Bf7":
                return Pitch.As7;

            case "Af7":
                return Pitch.Gs7;

            case "Gf7":
                return Pitch.Fs7;


            case "Ef7":
                return Pitch.Ds7;

            case "Df7":
                return Pitch.Cs7;


            case "Bf6":
                return Pitch.As6;

            case "Af6":
                return Pitch.Gs6;

            case "Gf6":
                return Pitch.Fs6;


            case "Ef6":
                return Pitch.Ds6;

            case "Df6":
                return Pitch.Cs6;


            case "Bf5":
                return Pitch.As5;

            case "Af5":
                return Pitch.Gs5;

            case "Gf5":
                return Pitch.Fs5;


            case "Ef5":
                return Pitch.Ds5;

            case "Df5":
                return Pitch.Cs5;


            case "Bf4":
                return Pitch.As4;

            case "Af4":
                return Pitch.Gs4;

            case "Gf4":
                return Pitch.Fs4;


            case "Ef4":
                return Pitch.Ds4;

            case "Df4":
                return Pitch.Cs4;


            case "Bf3":
                return Pitch.As3;

            case "Af3":
                return Pitch.Gs3;

            case "Gf3":
                return Pitch.Fs3;


            case "Ef3":
                return Pitch.Ds3;

            case "Df3":
                return Pitch.Cs3;


            case "Bf2":
                return Pitch.As2;

            case "Af2":
                return Pitch.Gs2;

            case "Gf2":
                return Pitch.Fs2;


            case "Ef2":
                return Pitch.Ds2;

            case "Df2":
                return Pitch.Cs2;


            case "Bf1":
                return Pitch.As1;

            case "Af1":
                return Pitch.Gs1;

            case "Gf1":
                return Pitch.Fs1;


            case "Ef1":
                return Pitch.Ds1;

            case "Df1":
                return Pitch.Cs1;


            case "Bf0":
                return Pitch.As0;
            default:
                throw new RuntimeException("Invalid Value: " + name);
        }

    }

    public static void main(String args[]) {
        System.out.println(Pitch.fromFrequency(261.63));

    }
}
