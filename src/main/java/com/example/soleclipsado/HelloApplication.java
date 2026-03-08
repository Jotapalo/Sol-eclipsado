package com.example.soleclipsado;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // El stage viene como parametro cuando se invoca el metodo start

        // El fmxloader carga la informacion del .fxml para que cuando se cree una instancia de clase Scene tenga
        // cargado el formulario.

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("secret-word-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 500);
        stage.setTitle("Sol Eclipsado");
        stage.setScene(scene);
        stage.show();
    }
}
