module com.example.soleclipsado {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.soleclipsado to javafx.fxml;
    opens com.example.soleclipsado.controllers to javafx.fxml;
    opens com.example.soleclipsado.images to javafx.fxml;
    exports com.example.soleclipsado;
}