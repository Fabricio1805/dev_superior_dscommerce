package com.devsuperior.dscommerce.application.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidationErrorDTO extends CustomErrorDTO {
    private List<FieldMessagedTO> errors = new ArrayList<>();

    public ValidationErrorDTO(Instant timeStamp, Integer statusCode, String error, String path) {
        super(timeStamp, statusCode, error, path);
    }

    public List<FieldMessagedTO> getErrors() {
        return errors;
    }

    public void addError(FieldMessagedTO error) {
        this.errors.removeIf(e -> e.getFieldName().equals(error.getFieldName()));
        
        this.errors.add(error);
    }

    
}
