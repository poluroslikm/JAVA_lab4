package org.example.models;

public class InputArgs {
    private String inputFile;
    private String outputFile;

    public InputArgs(String inputFile, String outputFile) {
        this.inputFile = inputFile;
        this.outputFile = outputFile;
    }

    public String getInputFile() {
        return inputFile;
    }

    public String getOutputFile() {
        return outputFile;
    }

    @Override
    public String toString() {
        return "InputArgs{" +
                "inputFile='" + inputFile + '\'' +
                ", outputFile='" + outputFile + '\'' +
                '}';
    }
}