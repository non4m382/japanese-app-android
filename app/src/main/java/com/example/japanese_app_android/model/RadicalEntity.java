package com.example.japanese_app_android.model;

public class RadicalEntity {

    private Long id;

    private Integer numberOrder;

    private String radical;

    private String name;

    private String meaning;

    private Integer status;

    public RadicalEntity(Long id, Integer numberOrder, String radical, String name, String meaning, Integer status) {
        this.id = id;
        this.numberOrder = numberOrder;
        this.radical = radical;
        this.name = name;
        this.meaning = meaning;
        this.status = status;
    }

    public RadicalEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumberOrder() {
        return numberOrder;
    }

    public void setNumberOrder(Integer numberOrder) {
        this.numberOrder = numberOrder;
    }

    public String getRadical() {
        return radical;
    }

    public void setRadical(String radical) {
        this.radical = radical;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}

