package LFSR;

public class KeyLSFR {
    public static byte[] keyBitsArray;
    public static final int BYTE_SIZE = 8;
    public static final int[] POSITIONS = {29, 2};

    public KeyLSFR(byte[] keyBitsArray) {
        KeyLSFR.keyBitsArray = keyBitsArray;
    }

    private static byte generateNextBit() {
        byte next = keyBitsArray[POSITIONS[0] - 1];
        for (int i = 1; i < POSITIONS.length; i++) {
            next ^= keyBitsArray[POSITIONS[i] - 1];
        }
        return next;
    }

    public static byte generateByteKey() {
        byte result = 0;
        for (int i = 0; i < BYTE_SIZE; i++) {
            byte next = generateNextBit();
            result <<= 1;
            result += (keyBitsArray[keyBitsArray.length - 1]);
            for (int j = keyBitsArray.length - 1; j > 0; j--) {
                keyBitsArray[j] = keyBitsArray[j - 1];
            }
            keyBitsArray[0] = next;
        }
        return result;
    }
}