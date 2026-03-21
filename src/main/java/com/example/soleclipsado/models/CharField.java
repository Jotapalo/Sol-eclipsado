package com.example.soleclipsado.models;

import javafx.geometry.Insets;
import javafx.scene.control.Label;

import static com.example.soleclipsado.models.StringTools.removeDiacritics;

public class CharField extends Label {

    public char secret_char;

    public CharField(char letter) {
        super();
        this.secret_char = letter;
        //Definir dimensiones del label
        setPrefHeight(35);
        setPrefWidth(35);
        setMinHeight(35);
        setMinWidth(35);

        setStyle("-fx-border-color: black; -fx-border-width: 2;");

        setPadding(new Insets(0, 0, 0, 9));

    }


    public void setText(char c) {
        setText(String.valueOf(c));
    }


    //Valida que si el caracter que se le envio a comprobar coincide con su caracter oculto entonces se muestra
    public boolean validateInputCharacter(char c) {
        char normalizedSecretChar = removeDiacritics(String.valueOf(secret_char))
                .toUpperCase().charAt(0);// Normaliza el caracter convirtiendolo de su variante con acento a su base

        if (normalizedSecretChar == Character.toUpperCase(c)) { //Compara, en caso de coincidir con alguna de sus variantes retorna True
            setText(secret_char);
            return true;
        }
        return false; //Caso contrario retorna False
    }
}
