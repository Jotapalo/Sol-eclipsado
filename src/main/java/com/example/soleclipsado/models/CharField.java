package com.example.soleclipsado.models;

import javafx.scene.control.TextField;

public class CharField extends TextField {

    public char secret_char;

    public CharField(char letter) {
        super();
        this.secret_char = letter;
    }


    public void setText(char c) {
        setText(String.valueOf(c));
    }


    //Valida que si el caracter que se le envio a comprobar coincide con su caracter oculto entonces se muestra
    public void validateInputCharacter(char c) {
        if (secret_char == c) {
            setText(secret_char);
            setDisable(true);
        }
    }
}
