package com.example.japanese_app_android.model;


public class KanjiEntity {

    private Integer id;
    private Integer lessonId;
    private String reading;

    private String writing;

    private String meaning;

    private String kunyomi;

    private String onyomi;

    public Integer getId() {
        return id;
    }

    public KanjiEntity(Integer id, Integer lessonId,String reading, String writing, String meaning, String kunyomi, String onyomi) {
        this.id = id;
        this.id = lessonId;
        this.reading = reading;
        this.writing = writing;
        this.meaning = meaning;
        this.kunyomi = kunyomi;
        this.onyomi = onyomi;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReading() {
        return reading;
    }

    public void setReading(String reading) {
        this.reading = reading;
    }

    public String getWriting() {
        return writing;
    }

    public void setWriting(String writing) {
        this.writing = writing;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getKunyomi() {
        return kunyomi;
    }

    public void setKunyomi(String kunyomi) {
        this.kunyomi = kunyomi;
    }

    public String getOnyomi() {
        return onyomi;
    }

    public void setOnyomi(String onyomi) {
        this.onyomi = onyomi;
    }
}
