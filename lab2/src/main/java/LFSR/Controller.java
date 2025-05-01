package LFSR;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.List;

public class Controller {

    @FXML
    private TextArea plainText;
    @FXML
    private TextArea keyText;
    @FXML
    private TextArea cipherText;
    @FXML
    private TextField startConditionField;
    @FXML
    private Label labelLength;
    @FXML
    private Button cipherButton;

    private List<Byte> plainBytes;
    private List<Byte> keyBytes;
    private List<Byte> cipherBytes;
    public final static int REG_LENGTH = 29;

    @FXML
    public void handleCipher() {
        if (!isKeyValid(startConditionField.getText())) {
            return;
        }
        if (plainBytes == null || plainBytes.isEmpty()) {
            return;
        }

        KeyLSFR.keyBitsArray = Builder.buildBinaryToBitArray(startConditionField.getText());
        keyBytes = new ArrayList<>();
        cipherBytes = new ArrayList<>();
        for (int i = 0; i < plainBytes.size(); i++) {
            byte next = KeyLSFR.generateByteKey();
            addByte(next, i);
            cipherBytes.add((byte) (next ^ plainBytes.get(i)));
        }


        keyText.setText(Builder.buildKeyString(keyBytes));
        cipherText.setText(Builder.buildStringToBinary(cipherBytes));
    }

    @FXML
    public void handleOpenFile() {
        plainBytes = FileHandler.readFile();
        if (plainBytes != null) {
            cipherText.setText("");
            plainText.setText(Builder.buildStringToBinary(plainBytes));
            cipherButton.setDisable(false);
        }
    }

    @FXML
    public void handleSaveFile() {
        FileHandler.writeFile(cipherBytes);
    }

    private boolean isKeyValid(String key) {
        if (key.length() != REG_LENGTH) {
            return false;
        }
        for (char c : key.toCharArray()) {
            if (c != '0' && c != '1') {
                return false;
            }
        }
        return true;
    }

    public void handleFieldEdit() {
        labelLength.setText(startConditionField.getText().length() + "/29 бит начального состояния:");
    }

    private void addByte(byte nextByte, int position) {
        if (plainBytes.size() < 2 * 10 || position < 10
                || position >= plainBytes.size() - 10) {
            keyBytes.add(nextByte);
        }
    }
}