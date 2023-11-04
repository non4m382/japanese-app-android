package com.example.japanese_app_android.model;

public class KanjiLesson {
    private Integer id;
    private String name;
    private String content;
    public KanjiLesson (Integer id, String name,String content){
        this.id=id;
        this.name=name;
        this.content=content;
    }
    public KanjiLesson(){

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
