package com.example.demo.exception;

public class CustomFileNotFoudException extends ApiException {

    private String filename;

    public CustomFileNotFoudException(String filename) {
        super("Archivo no encontrado", "No existe ningun archivo con nombre " + filename + " en el S3 Bucket", 404);
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
