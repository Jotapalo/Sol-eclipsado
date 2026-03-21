package com.example.soleclipsado.controllers;


import com.example.soleclipsado.models.CharField;
import com.example.soleclipsado.models.SecretWord;
import static com.example.soleclipsado.models.StringTools.removeDiacritics;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.*;
import java.util.stream.Collectors;

public class GameController {
    @FXML
    GridPane GridPaneKeyWord;

    @FXML
    HBox HboxCharFields;

    @FXML
    Label LabelRemainingAids;

    @FXML
    Button ButtonHelp;

    SecretWord secretWord;

    int failures = 0;
    List<Character> wordsFound = new ArrayList<Character>();

    Set<Character> missingLetters;

    /*Metodo que hace referencia a la instancia de secretWord al gameController, y carga la primera lista con las
    palabras que faltan*/
    public void setSecretWord(SecretWord s){
        secretWord = s;
        missingLetters = stringToCharSet(removeDiacritics(secretWord.getWord()));
    }

    //Event Handler de las letras a presionar
    public void onActionKey(ActionEvent event){
        // Desactiva la tecla que ha sido presionada por el usuario
        Node node = (Node) event.getSource();
        String id = node.getId().toUpperCase();
        node.setDisable(true);

        System.out.print("Button click " + id.toUpperCase());

        //Recorre los CharField existentes buscando si la letra presionada en el evento coincide con alguna de la palabra
        boolean valid = false;
        for (Node n : HboxCharFields.getChildren()) {
            if (n instanceof CharField charField) {
                if (charField.validateInputCharacter(id.charAt(0))) { //Evalua si la tecla es valida
                    valid = true;
                    if (!wordsFound.contains(id.charAt(0))) { //Utilizado para no agregar mas de una vez la misma letra
                        wordsFound.add(id.charAt(0)); //Agrega la letra a lista de palabras encontradas
                        missingLetters.removeAll(listToCharSet(wordsFound)); //Operacion de diferencia, actualiza la lista de letras restantes
                    }
                }
            }
        }


        if  (!valid) { // Si la letra no se encuentra en las letras que faltan entonces el usuario cometio un error
            failures++;//Aumentar el contador de errores
            node.setStyle("-fx-background-color: red;");
        } else {
            node.setStyle("-fx-background-color: green;");
        }

        if (missingLetters.isEmpty()) { // cuando no faltan mas letras es por que el usuario gano.
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("¡Victoria!");
            alert.setHeaderText("¡GANASTE!");
            alert.setContentText("¡Felicidades!");
            alert.showAndWait();

            Stage stage = (Stage) GridPaneKeyWord.getScene().getWindow();
            stage.close();
        }
        else if (failures == 5) { //Si todavia quedan letras cubre los 5 errores entonces el usuario pierde
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("¡Derrota!");
            alert.setHeaderText("¡PERDISTE!");
            alert.setContentText("La palabra era: " + secretWord.getWord());
            alert.showAndWait();

            Stage stage = (Stage) GridPaneKeyWord.getScene().getWindow();
            stage.close();
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


    //Convierte un String en un objeto tipo Set
    private Set<Character> stringToCharSet(String str) {
        return str.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toSet());
    }

    // Convierte una Lista de caracteres en un objeto tipo set
    private Set<Character> listToCharSet(List<Character> list) {
        return new HashSet<>(list);
    }

    //Evento de presionar el boton de ayuda
    @FXML
    public void onActionHelpButton(ActionEvent event){
        System.out.println("Pressed help button");

        int counter = Integer.parseInt(LabelRemainingAids.getText());
        int newCounter = counter - 1;

        if (counter > 0) {
            LabelRemainingAids.setText(String.valueOf(newCounter));
        }

        if (newCounter == 0){
            ButtonHelp.setDisable(true);
        }

        missingLetters.removeAll(listToCharSet(wordsFound));

        //Escoge una letra aleatoria de las que faltan por descubrir
        char randomLetter = new ArrayList<>(missingLetters).get(new Random().nextInt(missingLetters.size()));

        // Busca el boton asociado a la letra random que se filtro, simula el click en este boton que activa el onActionKey
        for (Node node : GridPaneKeyWord.getChildren()) {
            if (node instanceof Button) {
                Button btn = (Button) node;
                if (btn.getText().equalsIgnoreCase(String.valueOf(randomLetter))) {
                    btn.fire();
                    break;
                }
            }
        }
    }

}
