package com.example.soleclipsado.views;

import com.example.soleclipsado.controllers.SecretWordController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SecretWordView extends Stage {

    private SecretWordController controller;

    public SecretWordView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/soleclipsado/secret-word-view.fxml"));
        Parent root = fxmlLoader.load();

        controller = fxmlLoader.getController();

        Scene scene = new Scene(root);
        setTitle("Sol Eclipsado");
        setScene(scene);
        setResizable(false);

    }

    public static class SecretWordViewHolder {
        private static SecretWordView INSTANCE = null;
    }

    public static SecretWordView getInstance() throws IOException {
        if (SecretWordViewHolder.INSTANCE == null){
            SecretWordViewHolder.INSTANCE = new SecretWordView();
        }
        return SecretWordViewHolder.INSTANCE;
    }
}
