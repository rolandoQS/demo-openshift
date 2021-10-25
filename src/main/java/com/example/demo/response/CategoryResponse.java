package com.example.demo.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;


public class CategoryResponse {
    private Long id;

    @JsonProperty
    private String type;

    @JsonProperty
    private boolean haslength;

    @JsonProperty
    private String length;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isHaslength() {
        return haslength;
    }

    public void setHaslength(boolean haslength) {
        this.haslength = haslength;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }
}
