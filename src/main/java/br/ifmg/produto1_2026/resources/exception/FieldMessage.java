package br.ifmg.produto1_2026.resources.exception;

public class FieldMessage {

    private String message;
    private String fild;

    public FieldMessage() {
    }

    public FieldMessage(String message, String fild) {
        this.message = message;
        this.fild = fild;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFild() {
        return fild;
    }

    public void setFild(String fild) {
        this.fild = fild;
    }
}
