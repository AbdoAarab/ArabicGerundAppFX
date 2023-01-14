package com.massadirapplication.massadirappfx;

import com.massadirapplication.massadirappfx.nooj.ANV;
import com.massadirapplication.massadirappfx.nooj.MSDV;
import com.massadirapplication.massadirappfx.nooj.NOOJOBJ;
import com.massadirapplication.massadirappfx.nooj.VMSD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import org.controlsfx.control.CheckComboBox;
import org.controlsfx.control.textfield.CustomTextField;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Scene3Controller extends Scene2Controller {

    protected static int textTabCount;

    protected static boolean isTextTab = false;

    protected DataParser dataParser;
    @FXML
    private VBox dragBox;
    @FXML
    private Button addFileButton;


    @FXML
    private Button scanFileButton;

    @FXML
    private VBox dragDropOverlay;
    @FXML
    private FlowPane flowPane;
    @FXML
    private Label fileName;

    @FXML
    private Button fileOption;

    @FXML
    private GridPane gridPane;

    @FXML
    private TabPane newScanTabPane;

    @FXML
    private Tab scannedTab;

    @FXML
    private TextArea scannedTextArea;

    @FXML
    private TabPane scene3TabPane;

    @FXML
    private TextArea textArea;

    @FXML
    private CheckComboBox<String> patternBox;

    @FXML
    private CheckComboBox<String> rootBox;

    @FXML
    private CheckComboBox<String> rootTypeBox;

    @FXML
    private CheckComboBox<String> typeBox;

    @FXML
    private CheckComboBox<String> tranBox;

    @FXML
    private Button button_G2V;

    @FXML
    private Button button_V2G;

    @FXML
    private Button button_VP2NP;
    @FXML
    private CustomTextField gerundInputField_G2V;

    @FXML
    private CustomTextField gerundMainOutputField_V2G;

    @FXML
    private CustomTextField gerundOutputField_VP2NP;

    @FXML
    private CustomTextField patternOutputField_G2V;

    @FXML
    private CustomTextField patternOutputField_V2G;

    @FXML
    private CustomTextField patternOutputField_VP2NP;
    @FXML
    private CustomTextField rootOutputField_G2V;

    @FXML
    private CustomTextField rootOutputField_V2G;

    @FXML
    private CustomTextField rootOutputField_VP2NP;

    @FXML
    private CustomTextField rootTypeOutputField_G2V;

    @FXML
    private CustomTextField rootTypeOutputField_V2G;


    @FXML
    private TableView tableView;

    @FXML
    private CustomTextField tranOutputField_G2V;

    @FXML
    private CustomTextField tranOutputField_V2G;

    @FXML
    private CustomTextField tranOutputField_VP2NP;
    @FXML
    private CustomTextField verbInputField_V2G;

    @FXML
    private CustomTextField verbMainOutputField_G2V;

    @FXML
    private CustomTextField verbOutputField_G2V;

    @FXML
    private CustomTextField verbOutputField_V2G;

    @FXML
    private CustomTextField verbOutputField_VP2NP;

    @FXML
    private CustomTextField npOutputField_VP2NP;

    @FXML
    private CustomTextField vpInputField_VP2NP;


    public Scene3Controller() {
    }

    public void initialize() throws Exception {
        textTabCount = 0;
        scannedTab.setText(scannedTabLabel);
        scannedTextArea.setText(String.valueOf(textToScan));
        //Data Parsing
        dataParser = new DataParser();
        dataParser.parseMainData();

        handleCheckBox();
        handleTable();
    }

    void updateTable() {
        tableView.refresh();
    }

    @FXML
    void verbPhraseToNounPhrase(ActionEvent event) throws Exception {
        String inputVerbPhrase = vpInputField_VP2NP.getText();
        String an = "أن";

        noojApply.analyzeText(inputVerbPhrase);
        NOOJOBJ dataObject = new DataParser().getDataObj();

        if ((dataObject.ANV != null) && !(dataObject.ANV.isEmpty())) {
            for (ANV tmp : dataObject.ANV) {
                System.out.println("Searching Noun Phrase of " + inputVerbPhrase);
                if (inputVerbPhrase.contains(tmp.entry)) {
                    System.out.println("Noun Phrase Found!");

                    String outputNounPhrase = inputVerbPhrase.replaceFirst("\\b" + an + "\\b", tmp.msd.mamsd);
                    outputNounPhrase = outputNounPhrase.replaceFirst("\\b" + tmp.entry + "\\b", "");

                    npOutputField_VP2NP.setText(outputNounPhrase);
                    verbOutputField_VP2NP.setText(tmp.verb.lemma);
                    rootOutputField_VP2NP.setText(tmp.verb.root);
                    patternOutputField_VP2NP.setText(tmp.verb.pattern);
                    tranOutputField_VP2NP.setText(tmp.verb.transitivity);
                    gerundOutputField_VP2NP.setText(tmp.msd.mamsd);
                    break;
                } else {
                    npOutputField_VP2NP.setText("Noun Phrase Not Found!");
                    resetTextFields();
                    System.out.println("Data Object doesn't contain gerund!");

                }
            }

        } else {
            npOutputField_VP2NP.setText("Noun Phrase Not Found!");
            resetTextFields();
            System.out.println("Data Object is null or empty");
        }
    }

    @FXML
    void verbToGerund(ActionEvent event) throws Exception {
        String inputVerb = verbInputField_V2G.getText();

        noojApply.analyzeText(inputVerb);

        NOOJOBJ dataObject = new DataParser().getDataObj();

        if ((dataObject.VMSD != null) && !(dataObject.VMSD.isEmpty())) {
            for (VMSD tmp : dataObject.VMSD) {
                System.out.println("Searching gerund of " + inputVerb);
                if (inputVerb.contains(tmp.entry)) {
                    System.out.println("Gerund Found!");
                    gerundMainOutputField_V2G.setText(tmp.msd.omsd);
                    verbOutputField_V2G.setText(tmp.verb.lemma);
                    rootOutputField_V2G.setText(tmp.verb.root);
                    rootTypeOutputField_V2G.setText(tmp.verb.rootInfo);
                    patternOutputField_V2G.setText(tmp.verb.pattern);
                    tranOutputField_V2G.setText(tmp.verb.transitivity);
                    addItemToTable(tmp);
                    break;
                } else {
                    gerundMainOutputField_V2G.setText("Gerund Not Found!");
                    resetTextFields();
                    System.out.println("Data Object doesn't contain gerund!");

                }
            }

        } else {
            gerundMainOutputField_V2G.setText("Gerund Not Found!");
            resetTextFields();
            System.out.println("Data Object is null or empty");
        }

    }


    @FXML
    void gerundToVerb(ActionEvent event) throws Exception {
        String inputGerund = gerundInputField_G2V.getText();

        noojApply.analyzeText(inputGerund);

        NOOJOBJ dataObject = new DataParser().getDataObj();

        if ((dataObject.MSDV != null) && !(dataObject.MSDV.isEmpty())) {
            for (MSDV tmp : dataObject.MSDV) {
                System.out.println("Searching verb of " + inputGerund);
                if (inputGerund.contains(tmp.entry)) {
                    System.out.println("Verb Found!");
                    verbMainOutputField_G2V.setText(tmp.msd.omsd);
                    verbOutputField_G2V.setText(tmp.verb.lemma);
                    rootOutputField_G2V.setText(tmp.verb.root);
                    rootTypeOutputField_G2V.setText(tmp.verb.rootInfo);
                    patternOutputField_G2V.setText(tmp.verb.pattern);
                    tranOutputField_G2V.setText(tmp.verb.transitivity);
                    addItemToTable(tmp);
                    break;
                } else {
                    verbMainOutputField_G2V.setText("Gerund Not Found!");
                    resetTextFields();
                    System.out.println("Data Object doesn't contain verb!");
                }
            }

        } else {
            verbMainOutputField_G2V.setText("Verb Not Found!");
            resetTextFields();
            System.out.println("Data Object is null or empty");
        }

    }

    void resetTextFields() {
        verbOutputField_V2G.setText("-");
        rootOutputField_V2G.setText("-");
        rootTypeOutputField_V2G.setText("-");
        patternOutputField_V2G.setText("-");
        tranOutputField_V2G.setText("-");

        verbOutputField_G2V.setText("-");
        rootOutputField_G2V.setText("-");
        rootTypeOutputField_G2V.setText("-");
        patternOutputField_G2V.setText("-");
        tranOutputField_G2V.setText("-");

        verbOutputField_VP2NP.setText("-");
        rootOutputField_VP2NP.setText("-");
        patternOutputField_VP2NP.setText("-");
        tranOutputField_VP2NP.setText("-");
        gerundOutputField_VP2NP.setText("-");
    }

    void handleTable() {
        ObservableList<Map<String, Object>> items =
                FXCollections.<Map<String, Object>>observableArrayList();

        TableColumn<Map, String> verbCol = new TableColumn<>("الفعل");
        verbCol.setCellValueFactory(new MapValueFactory<>("verb"));
        TableColumn<Map, String> omsdCol = new TableColumn<>("مصدرالاصلي");
        omsdCol.setCellValueFactory(new MapValueFactory<>("omsd"));
        TableColumn<Map, String> hmsdCol = new TableColumn<>("مصدرالهيئة");
        hmsdCol.setCellValueFactory(new MapValueFactory<>("hmsd"));
        TableColumn<Map, String> mamsdCol = new TableColumn<>("مصدرالمرة");
        mamsdCol.setCellValueFactory(new MapValueFactory<>("mamsd"));
        TableColumn<Map, String> mimsdCol = new TableColumn<>("مصدرالميمي");
        mimsdCol.setCellValueFactory(new MapValueFactory<>("mimsd"));
        TableColumn<Map, String> smsdCol = new TableColumn<>("مصدرالصناعي");
        smsdCol.setCellValueFactory(new MapValueFactory<>("smsd"));

        tableView.getColumns().add(verbCol);
        tableView.getColumns().add(omsdCol);
        tableView.getColumns().add(hmsdCol);
        tableView.getColumns().add(mamsdCol);
        tableView.getColumns().add(mimsdCol);
        tableView.getColumns().add(smsdCol);

        NOOJOBJ dataObject = dataParser.getDataObj();
        if (dataObject.VMSD != null && !dataObject.VMSD.isEmpty()) {

            for (VMSD tmp : dataObject.VMSD) {
                Map<String, Object> item = new HashMap<>();
                item.put("verb", tmp.entry);
                item.put("omsd", tmp.msd.omsd);
                item.put("hmsd", tmp.msd.hmsd);
                item.put("mamsd", tmp.msd.mamsd);
                item.put("mimsd", tmp.msd.mimsd);
                item.put("smsd", tmp.msd.smsd);

                items.add(item);
            }
        } else {
            System.out.println("Scene3: VMSD is null or empty");
        }
        if (dataObject.MSDV != null && !dataObject.MSDV.isEmpty()) {

            for (MSDV tmp : dataObject.MSDV) {
                Map<String, Object> item = new HashMap<>();
                item.put("verb", tmp.verb.lemma);
                item.put("omsd", tmp.msd.omsd);
                item.put("hmsd", tmp.msd.hmsd);
                item.put("mamsd", tmp.msd.mamsd);
                item.put("mimsd", tmp.msd.mimsd);
                item.put("smsd", tmp.msd.smsd);

                items.add(item);
            }
        } else {
            System.out.println("Scene3: MSDV is null or empty");
        }
        if (!items.isEmpty())
            tableView.getItems().addAll(items);
        updateTable();
    }

    public void addItemToTable(VMSD tmp) {
        Map<String, Object> item = new HashMap<>();
        item.put("verb", tmp.verb.lemma);
        item.put("omsd", tmp.msd.omsd);
        item.put("hmsd", tmp.msd.hmsd);
        item.put("mamsd", tmp.msd.mamsd);
        item.put("mimsd", tmp.msd.mimsd);
        item.put("smsd", tmp.msd.smsd);

        tableView.getItems().add(item);
        updateTable();
    }

    public void addItemToTable(MSDV tmp) {
        Map<String, Object> item = new HashMap<>();
        item.put("verb", tmp.verb.lemma);
        item.put("omsd", tmp.msd.omsd);
        item.put("hmsd", tmp.msd.hmsd);
        item.put("mamsd", tmp.msd.mamsd);
        item.put("mimsd", tmp.msd.mimsd);
        item.put("smsd", tmp.msd.smsd);

        tableView.getItems().add(item);
        updateTable();
    }

    void handleCheckBox() {

        rootTypeBox.getItems().addAll(new HashSet<>(dataParser.getRootTypes()));
        rootBox.getItems().addAll(new HashSet<>(dataParser.getRoots()));
        patternBox.getItems().addAll(new HashSet<>(dataParser.getPatterns()));
        tranBox.getItems().addAll(new HashSet<>(dataParser.getTransitivities()));
        typeBox.getItems().addAll(new HashSet<>(dataParser.getVerbTypes()));

        rootTypeBox.getCheckModel().checkAll();
        rootBox.getCheckModel().checkAll();
        patternBox.getCheckModel().checkAll();
        tranBox.getCheckModel().checkAll();
        typeBox.getCheckModel().checkAll();
    }

    @FXML
    void newScanTab() throws IOException {
        textTabCount++;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("newTab.fxml"));

        Tab newScanTab = fxmlLoader.load();

        SingleSelectionModel<Tab> selectionModel = scene3TabPane.getSelectionModel();
        scene3TabPane.getTabs().add(selectionModel.getSelectedIndex(), newScanTab);
        selectionModel.clearAndSelect(selectionModel.getSelectedIndex() - 1);

    }

    @FXML
    void scanFile(ActionEvent event) throws IOException {
        isTextTab = false;
        textToScan = noojApply.analyzeFile(fileToAnalyze);
        scannedTabLabel = fileToAnalyze.getName().substring(0, 10).concat("...(" + noojApply.getFileExtension(fileToAnalyze) + ")");

        newScanTab();

        this.handleFile(null, true);
        textArea.clear();
        gridPane.setVisible(true);
    }

    void handleFile(File file, boolean reset) {
        super.handleFile(file);
        if (reset) {
            super.fileName.setTextFill(Paint.valueOf("blue"));
            super.fileName.setText("Drag your PDF, Word (.docx), or Text  (.txt) file here or");
        }
    }

    @FXML
    void scanText(ActionEvent event) throws IOException {
        isTextTab = true;
        textToScan = noojApply.analyzeText(textArea.getText());
        newScanTab();

        textArea.clear();
        gridPane.setVisible(true);
    }

    @FXML
    void goToFileScan(ActionEvent event) {
        SingleSelectionModel<Tab> selectionModel = newScanTabPane.getSelectionModel();
        selectionModel.select(0);
        textArea.clear();
        gridPane.setVisible(false);
    }

    @FXML
    void goToTextScan(ActionEvent event) {
        SingleSelectionModel<Tab> selectionModel = newScanTabPane.getSelectionModel();
        selectionModel.select(1);
        textArea.clear();
        gridPane.setVisible(false);
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

    @Override
    void hideDragBox() {
        flowPane.setVisible(true);
        fileName.setVisible(true);
        addFileButton.setVisible(true);
        scanFileButton.setVisible(true);

        dragDropOverlay.setVisible(false);
    }

    @Override
    void showDragBox() {
        Stage window = (Stage) scene3TabPane.getScene().getWindow();
        window.toFront();

        flowPane.setVisible(false);
        fileName.setVisible(false);
        addFileButton.setVisible(false);
        scanFileButton.setVisible(false);

        dragDropOverlay.setVisible(true);
    }
}
