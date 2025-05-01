module LSFR {
    requires javafx.controls;
    requires javafx.fxml;


    opens LFSR to javafx.fxml;
    exports LFSR;
}