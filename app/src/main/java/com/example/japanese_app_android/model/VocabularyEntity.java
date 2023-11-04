package com.example.japanese_app_android.model;

public class VocabularyEntity {
    private Integer id;

    private String kanji;

    private String meaning;

    private String name;

    private String sound;

    private String example;

    public VocabularyEntity(Integer id, String kanji, String meaning, String name, String sound, String example) {
        this.id = id;
        this.kanji = kanji;
        this.meaning = meaning;
        this.name = name;
        this.sound = sound;
        this.example = example;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKanji() {
        return kanji;
    }

    public void setKanji(String kanji) {
        this.kanji = kanji;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }
}
