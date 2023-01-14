package com.massadirapplication.massadirappfx;

import com.massadirapplication.massadirappfx.nooj.XML2JAVA;

import java.io.*;

public class NoojApply {

    private File textToAnalyze;
    private File dictionary;
    private File grammar;

    public static void main(String[] args) throws Exception {
        XML2JAVA tmp = new XML2JAVA();
    }

    public NoojApply() {
        this.dictionary = new File("C:\\noojapply\\bigdic.nod");
        this.grammar = new File("C:\\noojapply\\A_grammar.sft");
        this.textToAnalyze = new File("C:\\noojapply\\textToAnalyze.txt");
    }

    private void noojApplyCMD() throws IOException {
        String command = "noojapply ar result.ind";
        command += " " + getDictionary().getName();
        command += " " + getGrammar().getName();
        command += " " + getTextToAnalyze().getName();

        ProcessBuilder builder = new ProcessBuilder(
                "cmd.exe", "/c", "cd \"C:\\noojapply\" && " + command);
        builder.redirectErrorStream(true);
        Process p = builder.start();
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while (true) {
            line = r.readLine();
            if (line == null) {
                break;
            }
            System.out.println(line);
        }

    }


    public StringBuilder analyzeText(String text) throws IOException {
        File fold = getTextToAnalyze();
        fold.delete();

        File fnew = getTextToAnalyze();

        System.out.println(text);

        FileWriter f2 = new FileWriter(fnew, false);
        f2.write(text);
        f2.close();

        noojApplyCMD();
        return readFile(getTextToAnalyze());
    }

    public StringBuilder analyzeFile(File file) throws IOException {
        String fileType = getFileExtension(file);

        if (fileType.contains("pdf")) {
            pdfToText(file);
        } else if (fileType.contains("docx")) {
            docToText(file);
        } else if (fileType.contains("txt")) {
            analyzeText(readFile(file).toString());
        }

        noojApplyCMD();
        return readFile(getTextToAnalyze());
    }

    private void docToText(File doc) throws IOException {
        String command = "docto";
        command += " -F " + doc.getAbsolutePath();
        command += " -O " + getTextToAnalyze().getName();
        command += " -T wdFormatText -e 65001";

        ProcessBuilder builder = new ProcessBuilder(
                "cmd.exe", "/c", "cd \"C:\\noojapply\" && " + command);
        builder.redirectErrorStream(true);
        Process p = builder.start();
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while (true) {
            line = r.readLine();
            if (line == null) {
                break;
            }
            System.out.println(line);
        }
    }

    private void pdfToText(File pdf) throws IOException {
        String command = "pdfToText -nopgbrk -raw -enc UTF-8";
        command += " " + pdf.getAbsolutePath();
        command += " " + getTextToAnalyze().getName();

        ProcessBuilder builder = new ProcessBuilder(
                "cmd.exe", "/c", "cd \"C:\\noojapply\" && " + command);
        builder.redirectErrorStream(true);
        Process p = builder.start();
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while (true) {
            line = r.readLine();
            if (line == null) {
                break;
            }
            System.out.println(line);
        }
    }

    public StringBuilder readFile(File file) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(file.getAbsoluteFile()));
        StringBuilder sb = new StringBuilder();
        String line = br.readLine();
        while (line != null) {
            sb.append(line);
            sb.append(System.lineSeparator());
            line = br.readLine();
        }

        return sb;

    }

    String getFileExtension(File file) {
        String fileName = file.getName();
        int dotIndex = fileName.lastIndexOf('.');
        String fileExtension = (dotIndex == -1) ? "" : fileName.substring(dotIndex + 1);
        return fileExtension;
    }

    public File getTextToAnalyze() {
        return textToAnalyze;
    }

    public void setTextToAnalyze(File textToAnalyze) {
        this.textToAnalyze = textToAnalyze;
    }

    public File getDictionary() {
        return dictionary;
    }

    public void setDictionary(File dictionary) {
        this.dictionary = dictionary;
    }

    public File getGrammar() {
        return grammar;
    }

    public void setGrammar(File grammar) {
        this.grammar = grammar;
    }

}
