package com.example.soleclipsado.controllers;


import com.example.soleclipsado.models.CharField;
import com.example.soleclipsado.models.SecretWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class GameController {

    //public Button[][] keyword;

    @FXML
    Text textSecretReveal;

    @FXML
    HBox HboxCharFields;

    @FXML
    Label LabelRemainingAids;

    @FXML
    Button ButtonHelp;

    SecretWord secretWord;

    //Permite establecer el texto de ayuda para pruebas que muestra la palabra secreta
    public void setSecretWord(SecretWord s){
        secretWord = s;
        textSecretReveal.setText(secretWord.getWord());
    }

    public void onActionKey(ActionEvent event){
        // Desactiva la tecla que ha sido presionada por el usuario
        Node node = (Node) event.getSource();
        String id = node.getId().toUpperCase();
        node.setDisable(true);

        System.out.print("Button click " + id.toUpperCase());

        //Recorre los CharField existentes buscando si la letra presionada en el evento coincide con alguna de la palabra
        for (Node n : HboxCharFields.getChildren()) {
            if (n instanceof CharField charField) {
                charField.validateInputCharacter(id.charAt(0));
            }
        }

    }

    //Crea la lista de CharFields
    public void createCharFields(String word) {
        HboxCharFields.getChildren().clear();

        //Adiciona a cada CharField su caracter correspondiente de forma oculta
        for (int i = 0; i < word.length(); i++) {
            CharField charField = new CharField(word.charAt(i));
            charField.setFont(Font.font("Arial Bold", 19));
            HboxCharFields.getChildren().add(charField);
        }
    }

    //Evento de presionar el boton de ayuda
    @FXML
    public void onActionHelpButton(ActionEvent event){
        System.out.println("Pressed help button");

        /* Hay que agregar la logica que permite revelar un campo de forma aleatoria de la palabra
        * para esto se puede escanear que letras ya estan reveladas, y comparar cuales quedan por revelar
        * usando la palabra secreta como referencia*/

        int counter = Integer.parseInt(LabelRemainingAids.getText());
        int newCounter = counter - 1;

        if (counter > 0) {
            LabelRemainingAids.setText(String.valueOf(newCounter));
        }

        if (newCounter == 0){
            ButtonHelp.setDisable(true);
        }
    }

}
