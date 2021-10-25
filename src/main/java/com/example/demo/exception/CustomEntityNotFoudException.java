package com.example.demo.exception;

public class CustomEntityNotFoudException extends ApiException{

    private String entity;
    private String field;
    private String value;

    public CustomEntityNotFoudException(String entity, String field, String value) {
        super(entity+" no encontrado/a", "No existe ningun "+entity+" con "+field+" = "+value, 404);
        this.entity = entity;
        this.value = value;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}
