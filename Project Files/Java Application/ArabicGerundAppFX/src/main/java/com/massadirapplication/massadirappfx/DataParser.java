package com.massadirapplication.massadirappfx;

import com.massadirapplication.massadirappfx.nooj.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class DataParser extends Scene3Controller {

    NOOJOBJ dataObj;
    ObservableList<String> verbTypes;
    ObservableList<String> roots;
    ObservableList<String> rootTypes;
    ObservableList<String> patterns;
    ObservableList<String> transitivities;

    List<VMSD> VMSD;
    List<MSDV> MSDV;

    List<ANV> ANV;

    public DataParser() throws Exception {
        this.dataObj = new XML2JAVA().getNoojObj();
        this.MSDV = this.dataObj.MSDV;
        this.VMSD = this.dataObj.VMSD;
        this.ANV = this.dataObj.ANV;

        this.verbTypes = FXCollections.observableArrayList();
        this.roots = FXCollections.observableArrayList();
        this.rootTypes = FXCollections.observableArrayList();
        this.patterns = FXCollections.observableArrayList();
        this.transitivities = FXCollections.observableArrayList();

        System.out.println(this);
    }

    public void parseMainData() {

        if (getDataObj().VMSD != null && !getDataObj().VMSD.isEmpty()) {
            for (VMSD tmp : getDataObj().VMSD) {

                getRootTypes().add(tmp.verb.rootInfo);
                getRoots().add(tmp.verb.root);
                getPatterns().add(tmp.verb.pattern);
                getVerbTypes().add(tmp.verb.type);
                getTransitivities().add(tmp.verb.transitivity);
            }
            System.out.println("VMSD: " + getDataObj().VMSD);
        } else {
            System.out.println("VMSD is null or empty");
        }
        if (getDataObj().MSDV != null && !getDataObj().MSDV.isEmpty()) {
            for (MSDV tmp : getDataObj().MSDV) {

                getRootTypes().add(tmp.verb.rootInfo);
                getRoots().add(tmp.verb.root);
                getPatterns().add(tmp.verb.pattern);
                getVerbTypes().add(tmp.verb.type);
                getTransitivities().add(tmp.verb.transitivity);
            }
            System.out.println("MSDV: " + getDataObj().MSDV);
        } else {
            System.out.println("MSDV is null or empty");
        }
        if (getDataObj().ANV != null && !getDataObj().ANV.isEmpty()) {

            for (ANV tmp : getDataObj().ANV) {

                getRootTypes().add(tmp.verb.rootInfo);
                getRoots().add(tmp.verb.root);
                getPatterns().add(tmp.verb.pattern);
                getVerbTypes().add(tmp.verb.type);
                getTransitivities().add(tmp.verb.transitivity);
            }
            System.out.println("ANV: " + getDataObj().ANV);
        } else {
            System.out.println("ANV is null or empty");
        }
    }

    public NOOJOBJ getDataObj() {
        return dataObj;
    }

    public ObservableList<String> getVerbTypes() {
        return verbTypes;
    }

    public void setVerbTypes(ObservableList<String> verbTypes) {
        this.verbTypes = verbTypes;
    }

    public ObservableList<String> getRoots() {
        return roots;
    }

    public void setRoots(ObservableList<String> roots) {
        this.roots = roots;
    }

    public ObservableList<String> getRootTypes() {
        return rootTypes;
    }

    public void setRootTypes(ObservableList<String> rootTypes) {
        this.rootTypes = rootTypes;
    }

    public ObservableList<String> getPatterns() {
        return patterns;
    }

    public void setPatterns(ObservableList<String> patterns) {
        this.patterns = patterns;
    }

    public ObservableList<String> getTransitivities() {
        return transitivities;
    }

    public void setTransitivities(ObservableList<String> transitivities) {
        this.transitivities = transitivities;
    }

}
