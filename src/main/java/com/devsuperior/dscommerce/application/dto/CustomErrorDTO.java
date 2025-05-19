package com.devsuperior.dscommerce.application.dto;

import java.time.Instant;

public class CustomErrorDTO {
    private Instant timeStamp;
    private Integer statusCode;
    private String error;
    private String path;
    
    public Instant getTimeStamp() {
        return timeStamp;
    }
    public void setTimeStamp(Instant timeStamp) {
        this.timeStamp = timeStamp;
    }
    public Integer getStatusCode() {
        return statusCode;
    }
    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }
    public String getError() {
        return error;
    }
    public void setError(String error) {
        this.error = error;
    }
    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }

    public CustomErrorDTO(Instant timeStamp, Integer statusCode, String error, String path) {
        this.timeStamp = timeStamp;
        this.statusCode = statusCode;
        this.error = error;
        this.path = path;
    }

    public CustomErrorDTO() {
    }
}
