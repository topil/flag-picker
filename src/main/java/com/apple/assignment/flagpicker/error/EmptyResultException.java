package com.apple.assignment.flagpicker.error;

public class EmptyResultException extends Exception {

    private String inputParam;

    public EmptyResultException(String inputParam){
        super("No Matched result.");
        this.inputParam = inputParam;
    }

    public String getInputParam() {
        return inputParam;
    }

    public void setInputParam(String inputParam) {
        this.inputParam = inputParam;
    }
}
