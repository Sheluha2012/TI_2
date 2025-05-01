package LFSR;

import javafx.stage.FileChooser;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FileHandler {
    public static List<Byte> readFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        fileChooser.getExtensionFilters().addAll();
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            try (InputStream is = new FileInputStream(file)) {
                byte[] bytes = is.readAllBytes();
                return IntStream.range(0, bytes.length).mapToObj(i -> bytes[i]).collect(Collectors.toList());
            } catch (FileNotFoundException e) {
                System.err.println("File not found");
                return null;
            } catch (IOException e) {
                System.err.println("I/O Error");
            }

        }
        return null;
    }

    public static void writeFile(List<Byte> plainBytesArray) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save File");
        fileChooser.getExtensionFilters().addAll();
        File file = fileChooser.showSaveDialog(null);
        if (file != null) {
            try (OutputStream os = new FileOutputStream(file)) {
                for (Byte b : plainBytesArray) {
                    os.write(b);
                }
            } catch (FileNotFoundException e) {
                System.err.println("File not found");
            } catch (IOException e) {
                System.err.println("I/O Error");
            }
        }
    }
}
