package LFSR;

import java.util.List;

public class Builder {

    public static String buildStringToBinary(List<Byte> bytes) {
        StringBuilder stringBuilder = new StringBuilder();
        if (bytes.size() < 10 * 2) {
            for (byte aByte : bytes) {
                stringBuilder.append(String.format("%8s",
                        Integer.toBinaryString(aByte & 0xFF)).replace(' ', '0')).append("  ");
            }
        } else {
            stringBuilder.append("Первые 10 байт:\n");
            for (int i = 0; i < 10; i++) {
                stringBuilder.append(String.format("%8s",
                        Integer.toBinaryString(bytes.get(i) & 0xFF)).replace(' ', '0')).append("  ");
            }
            stringBuilder.append("\nПоследние 10 байт:\n");
            for (int i = bytes.size() - 10; i < bytes.size(); i++) {
                stringBuilder.append(String.format("%8s",
                        Integer.toBinaryString(bytes.get(i) & 0xFF)).replace(' ', '0')).append("  ");
            }
        }
        return stringBuilder.toString();
    }

    public static String buildKeyString(List<Byte> bytes) {
        if (bytes.size() < 10 * 2) {
            return buildStringToBinary(bytes);
        }
        return "Первые 10 байт:\n" +
                buildStringToBinary(bytes.subList(0, 10)) +
                "\nПоследние 10 байт:\n" +
                buildStringToBinary(bytes.subList(10, 20));
    }

    public static byte[] buildBinaryToBitArray(String binary) {
        byte[] bits = new byte[binary.length()];
        for (int i = 0; i < bits.length; i++) {
            bits[i] = (byte) (binary.charAt(i) == '0' ? 0 : 1);
        }
        return bits;
    }
}
