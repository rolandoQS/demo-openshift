package com.example.demo.response;

import com.example.demo.models.Category;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;


public class ProductResponse {

    private Long id;

    @JsonProperty
    private String name;

    @JsonProperty
    private String type;

    @JsonProperty
    private Timestamp release_date;

    @JsonProperty
    private Timestamp insert_date;

    @JsonProperty
    private Integer views;

    @JsonProperty
    private String abbreviation;

    @JsonProperty
    private Category category;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Timestamp getRelease_date() {
        return release_date;
    }

    public void setRelease_date(Timestamp release_date) {
        this.release_date = release_date;
    }

    public Timestamp getInsert_date() {
        return insert_date;
    }

    public void setInsert_date(Timestamp insert_date) {
        this.insert_date = insert_date;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
