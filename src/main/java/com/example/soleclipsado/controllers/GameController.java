package com.example.soleclipsado.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class GameController {

    @FXML
    private TextField textFieldSecretWord;

    @FXML
    public void onMouseClickedPlayButton(MouseEvent mouseEvent) throws IOException {

        if (validateText(textFieldSecretWord.getText())) {
            System.out.println("Texto Valido");
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/soleclipsado/main-view.fxml"));
            Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            System.out.println("Texto Invalido");
        }

        System.out.println("Clicked on play button");
    }

    // Uso de expresiones regulares para validar que un texto no contenga espacios o caracteres diferentes del alfabeto
    // español
    public static boolean validateText(String texto) {
        return texto.matches("^[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ]+$");
    }
}
