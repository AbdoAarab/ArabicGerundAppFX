package com.massadirapplication.massadirappfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Scene1Controller {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button fileOption;

    @FXML
    private Button textOption;

    public void initialize() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Scene2.fxml"));
        root = fxmlLoader.load();
        scene = new Scene(root);

    }

    @FXML
    void goToFileScan(ActionEvent event) {
        stage = (Stage) fileOption.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        stage.centerOnScreen();
    }

    @FXML
    void goToTextScan(ActionEvent event) {
        TabPane tabPane = (TabPane) scene.lookup("#scene2TabPane");
        SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
        selectionModel.select(1);

        stage = (Stage) textOption.getScene().getWindow();
        stage.setScene(scene);

        stage.show();
        stage.centerOnScreen();
    }

}
