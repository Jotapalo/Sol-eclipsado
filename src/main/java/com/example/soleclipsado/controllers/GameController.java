package com.example.soleclipsado.controllers;


import com.example.soleclipsado.models.SecretWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.text.Text;

public class GameController {

    //public Button[][] keyword;

    @FXML
    Text textSecretReveal;

    SecretWord secretWord;

    public void setSecretWord(SecretWord s){
        secretWord = s;
        textSecretReveal.setText(secretWord.getWord());
    }

    public char onActionKey(ActionEvent event){
        //
        Node node = (Node) event.getSource();
        String id = node.getId();
        node.setDisable(true);

        System.out.print("Button click " + id);
        return 0;
    }



}
