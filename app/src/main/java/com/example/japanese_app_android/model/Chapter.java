package com.example.japanese_app_android.model;

public class Chapter {
    private int ava_id;

    private String title;

    private String des;

    public Chapter(int ava_id, String title, String des) {
        this.ava_id = ava_id;
        this.title = title;
        this.des = des;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public int getAva_id() {
        return ava_id;
    }

    public void setAva_id(int ava_id) {
        this.ava_id = ava_id;
    }
}
