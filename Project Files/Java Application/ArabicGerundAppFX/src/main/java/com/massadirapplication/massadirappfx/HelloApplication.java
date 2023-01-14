package com.massadirapplication.massadirappfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Scene1.fxml"));

        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root, 800, 600);

        stage.setTitle("Massadir App");

        stage.setScene(scene);
        stage.show();
        stage.centerOnScreen();
    }

}
