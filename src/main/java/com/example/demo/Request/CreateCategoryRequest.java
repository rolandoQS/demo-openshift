package com.example.demo.Request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Optional;

@Getter
@Setter
public class CreateCategoryRequest implements Serializable {


    private String type;

    private boolean haslength;

    private Long length;

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

    public Long getLength() {
        return length;
    }

    public void setLength(Long length) {
        this.length = length;
    }
}
