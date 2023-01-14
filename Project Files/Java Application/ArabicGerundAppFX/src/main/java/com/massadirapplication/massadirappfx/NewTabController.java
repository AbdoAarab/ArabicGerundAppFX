package com.massadirapplication.massadirappfx;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;

public class NewTabController extends Scene3Controller {

    @FXML
    private Tab newTab;

    @FXML
    private TextArea newTab_TextArea;


    public void initialize() throws Exception {
        if (isTextTab) {
            newTab.setText("Scanned Text " + textTabCount);
        } else {
            newTab.setText(scannedTabLabel);
        }
        newTab.setId("newTab" + textTabCount);
        newTab_TextArea.setId("newTab_TextArea" + textTabCount);
        newTab_TextArea.setText(String.valueOf(textToScan));
        //Data Parsing
        dataParser = new DataParser();
        dataParser.parseMainData();

        handleCheckBox();
        handleTable();

    }


}
