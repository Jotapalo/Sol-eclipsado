package com.example.soleclipsado.controllers;

import com.example.soleclipsado.models.SecretWord;
import com.example.soleclipsado.views.GameView;
import com.example.soleclipsado.views.SecretWordView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class SecretWordController {

    @FXML
    private TextField textFieldSecretWord;

    @FXML
    public void onActionPlayButton(ActionEvent actionEvent) throws IOException {
        if (validateText(textFieldSecretWord.getText())) {
            //Obtener la palabra ingresada y guardarla
            String word = textFieldSecretWord.getText();
            SecretWord secret = new SecretWord();
            secret.setWord(word.toUpperCase());

            //Obtener la instancia del GameView
            GameView gameView = GameView.getInstance();
            GameController gameController = gameView.getController();

            //Establecer la palabra secreta y crear los campos de las letras
            gameController.createCharFields(secret.getWord());

            //Muestra la ventana de juego
            gameView.show();

            //Oculta la ventana inicial
            SecretWordView wordView = SecretWordView.getInstance();
            wordView.hide();

            System.out.println("Texto Valido ");

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
