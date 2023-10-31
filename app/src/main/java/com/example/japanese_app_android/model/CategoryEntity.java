package com.example.japanese_app_android.model;

import com.google.gson.annotations.SerializedName;

public class CategoryEntity {
    @SerializedName("id")
    private Integer id;
    @SerializedName("name")
    private String name;
    //    private StatusEnum status;
    @SerializedName("status")
    private int status;

    public CategoryEntity(Integer id, String name, int status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    @Override
    public String toString() {
        return name;
    }

    public CategoryEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
