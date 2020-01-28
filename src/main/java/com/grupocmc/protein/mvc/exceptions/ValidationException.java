package com.grupocmc.protein.mvc.exceptions;

public class ValidationException extends RuntimeException {


    private String message;
    private String[] replacers;


    public ValidationException(String message) {
        super(message);
        this.message = message;
    }

    public ValidationException(String message,String[] replacers) {
        super(message);
        this.message = message;
        this.replacers = replacers;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String[] getReplacers() {
        return replacers;
    }

    public void setReplacers(String[] replacers) {
        this.replacers = replacers;
    }
}
