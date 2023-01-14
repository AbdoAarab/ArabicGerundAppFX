package com.massadirapplication.massadirappfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class Scene2Controller {

    private Stage stage;
    private Scene scene;
    private Parent root;

    protected NoojApply noojApply;

    protected static StringBuilder textToScan;
    protected static String scannedTabLabel;

    protected File fileToAnalyze;

    @FXML
    private Button addFileButton;

    @FXML
    private ImageView docxIcon;

    @FXML
    private VBox dragBox;


    @FXML
    private VBox dragDropOverlay;

    @FXML
    Label fileName;

    @FXML
    private FlowPane flowPane;

    @FXML
    private TabPane scene2TabPane;

    @FXML
    private Label leftStatus;

    @FXML
    private ImageView pdfIcon;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Label rightStatus;

    @FXML
    private Button scanFileButton;

    @FXML
    private Button scanTextButton;

    @FXML
    private TextArea textArea;

    @FXML
    private ImageView txtIcon;

    public Scene2Controller() {
        this.noojApply = new NoojApply();
    }

    @FXML
    void goToScene3() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Scene3.fxml"));
        root = fxmlLoader.load();
        scene = new Scene(root);
        stage = (Stage) scene2TabPane.getScene().getWindow();
        stage.setScene(scene);

        stage.show();
        stage.centerOnScreen();

    }

    @FXML
    void scanFile(ActionEvent event) throws Exception {
        textToScan = noojApply.analyzeFile(fileToAnalyze);
        scannedTabLabel = fileToAnalyze.getName().substring(0, 6).concat("...(" + noojApply.getFileExtension(fileToAnalyze) + ")");
        goToScene3();
    }

    @FXML
    void scanText(ActionEvent event) throws Exception {
        scannedTabLabel = "Scanned Text";

        textToScan = noojApply.analyzeText(textArea.getText());

        goToScene3();
    }


    @FXML
    void enableButton(MouseEvent event) {
        if (textArea.getText() != "") {
            scanTextButton.setVisible(true);
        } else {
            scanTextButton.setVisible(false);
        }
    }

    @FXML
    void handleFileOverEvent(DragEvent event) {

        if (event.getGestureSource() != dragBox
                && event.getDragboard().hasFiles()) {
            showDragBox();
            event.acceptTransferModes(TransferMode.COPY);
        }
        event.consume();

    }

    @FXML
    void handleFileDroppedEvent(DragEvent event) {
        Dragboard db = event.getDragboard();
        boolean success = false;
        if (db.hasFiles()) {
            handleFile(db.getFiles().get(0));
            success = true;
        }

        event.setDropCompleted(success);

        event.consume();
        hideDragBox();
    }

    @FXML
    void handleDragExitedEvent(DragEvent event) {
        hideDragBox();
        event.consume();

    }


    void hideDragBox() {
        flowPane.setVisible(true);
        fileName.setVisible(true);
        addFileButton.setVisible(true);
        scanFileButton.setVisible(true);

        dragDropOverlay.setVisible(false);
    }

    void showDragBox() {
        Stage window = (Stage) scene2TabPane.getScene().getWindow();
        window.toFront();

        flowPane.setVisible(false);
        fileName.setVisible(false);
        addFileButton.setVisible(false);
        scanFileButton.setVisible(false);

        dragDropOverlay.setVisible(true);
    }


    @FXML
    void browseFiles(ActionEvent event) {
        Stage stage = (Stage) addFileButton.getScene().getWindow();

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Documents (.txt) (.pdf) (.docx)", "*.txt", "*.pdf", "*.docx")
        );
        fileChooser.setTitle("Open Resource File");
        fileToAnalyze = fileChooser.showOpenDialog(stage);

        handleFile(fileToAnalyze);
    }

    void handleFile(File file) {
        fileToAnalyze = file;
        fileName.setAlignment(Pos.CENTER);
        if (file != null && checkFileExtension(file.getName())) {
            fileName.setWrapText(false);
            fileName.setTextFill(Paint.valueOf("blue"));
            fileName.setText(file.getName().toUpperCase());
            scanFileButton.setDisable(false);
            scanFileButton.setDefaultButton(true);
            handleIcons(file.getName());
        } else {
            fileName.setWrapText(true);
            fileName.setTextFill(Paint.valueOf("red"));
            fileName.setText("Non supported format! Please choose another file (.pdf) (.txt) (.docx)".toUpperCase());
            scanFileButton.setDisable(true);
            scanFileButton.setDefaultButton(false);
            handleIcons(null);
        }
    }

    boolean checkFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        String fileExtension = (dotIndex == -1) ? "" : fileName.substring(dotIndex + 1);
        return fileExtension.contains("txt") || fileExtension.contains("pdf") || fileExtension.contains("docx");
    }

    void handleIcons(String fileType) {

        if (fileType != null) {
            if (fileType.contains(".pdf")) {
                pdfIcon.setFitHeight(200);
                pdfIcon.setFitWidth(200);
                flowPane.getChildren().clear();
                flowPane.getChildren().add(pdfIcon);
            } else if (fileType.contains(".txt")) {
                txtIcon.setFitHeight(200);
                txtIcon.setFitWidth(200);
                flowPane.getChildren().clear();
                flowPane.getChildren().add(txtIcon);
            } else if (fileType.contains(".docx")) {
                docxIcon.setFitHeight(200);
                docxIcon.setFitWidth(200);
                flowPane.getChildren().clear();
                flowPane.getChildren().add(docxIcon);
            }
        } else {
            pdfIcon.setFitHeight(140);
            pdfIcon.setFitWidth(140);

            txtIcon.setFitHeight(140);
            txtIcon.setFitWidth(140);

            docxIcon.setFitHeight(140);
            docxIcon.setFitWidth(140);

            flowPane.getChildren().clear();
            flowPane.getChildren().addAll(pdfIcon, txtIcon, docxIcon);
        }
    }


}

