package com.example.soleclipsado;

import com.example.soleclipsado.views.SecretWordView;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

      SecretWordView secretWordView = SecretWordView.getInstance();
      secretWordView.show();

    }
}
