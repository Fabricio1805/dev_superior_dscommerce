package com.devsuperior.dscommerce.application.dto;

public class FieldMessagedTO {
    private String fieldName;
    private String message;
    
    public String getFieldName() {
        return fieldName;
    }
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public FieldMessagedTO(String fieldName, String message) {
        this.fieldName = fieldName;
        this.message = message;
    }

    public FieldMessagedTO() { }
    
}
