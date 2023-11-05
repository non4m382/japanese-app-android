package com.example.japanese_app_android.model;

public class LessonEntity {
    private Integer id;
    private String name;
    private String content;

    public LessonEntity(Integer id, String name, String content) {
        this.id = id;
        this.name = name;
        this.content = content;
    }

    public LessonEntity() {

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
