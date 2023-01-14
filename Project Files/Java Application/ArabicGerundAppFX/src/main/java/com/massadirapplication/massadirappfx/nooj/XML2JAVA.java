package com.massadirapplication.massadirappfx.nooj;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class XML2JAVA {

    public NOOJOBJ noojObj;


    public NOOJOBJ getNoojObj() {
        return noojObj;
    }

    public XML2JAVA() throws Exception {
        JAXBContext jaxbContext = JAXBContext.newInstance(NOOJOBJ.class);

        File file = getFormatedResults();

        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        noojObj = (NOOJOBJ) jaxbUnmarshaller.unmarshal(file);
        System.out.println(noojObj);
    }

    public File getFormatedResults() throws Exception {
        File resultsFile = new File("C:\\noojapply\\result.ind");

        String resultsContent = fileToString(resultsFile);

        resultsContent = resultsContent.replaceAll("[\\d,*]", "");
        resultsContent = resultsContent.replaceAll("\\b*UNDEFINED*\\b", "");
        try (PrintWriter writer = new PrintWriter(resultsFile.getAbsolutePath())) {
            writer.append(resultsContent);
            writer.flush();
        }

        return ind2XML(resultsFile);
    }

    public File ind2XML(File file) throws Exception {
        File indFile = new File("C:\\noojapply\\result.xml");
        indFile.delete();

        File xmlFile = file;

        String source = "<NOOJOBJ>" + fileToString(file) + "</NOOJOBJ>";
        System.out.println(source);

        FileWriter f2 = new FileWriter(changeExtension(xmlFile, ".xml"), false);
        f2.write(source);
        f2.close();

        xmlFile = new File("C:\\noojapply\\result.xml");

        return xmlFile;
    }

    public static File changeExtension(File f, String newExtension) {
        int i = f.getName().lastIndexOf('.');
        String name = f.getName().substring(0, i);
        return new File(f.getParent(), name + newExtension);
    }

    public String fileToString(File file) throws Exception {
        String input = null;
        Scanner sc = new Scanner(file);
        StringBuilder sb = new StringBuilder();
        while (sc.hasNextLine()) {
            input = sc.nextLine();
            sb.append(input);
        }
        return sb.toString();
    }

}