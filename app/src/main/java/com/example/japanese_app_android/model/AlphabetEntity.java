package com.example.japanese_app_android.model;

import android.net.Uri;

import java.io.Serializable;

public class AlphabetEntity implements Serializable {

    private Integer id;
    private String hiragana;
    private String katakana;
    private String reading;
    private String sound;
    private String writingHiragana;
    private String writingKatakana;
    private Integer status;

    public AlphabetEntity(Integer id, String hiragana, String katakana, String reading, String sound, String writingHiragana, String writingKatakana, Integer status) {
        this.id = id;
        this.hiragana = hiragana;
        this.katakana = katakana;
        this.reading = reading;
        this.sound = sound;
        this.writingHiragana = writingHiragana;
        this.writingKatakana = writingKatakana;
        this.status = status;
    }

    public AlphabetEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHiragana() {
        return hiragana;
    }

    public void setHiragana(String hiragana) {
        this.hiragana = hiragana;
    }

    public String getKatakana() {
        return katakana;
    }

    public void setKatakana(String katakana) {
        this.katakana = katakana;
    }

    public String getReading() {
        return reading;
    }

    public void setReading(String reading) {
        this.reading = reading;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public String getWritingHiragana() {
        return writingHiragana;
    }

    public void setWritingHiragana(String writingHiragana) {
        this.writingHiragana = writingHiragana;
    }

    public String getWritingKatakana() {
        return writingKatakana;
    }

    public void setWritingKatakana(String writingKatakana) {
        this.writingKatakana = writingKatakana;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
