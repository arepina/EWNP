package com.example.anast.ewnp;


public class WordIM {

    private int id;
    private String rus, eng;

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWordrus() {
        return this.rus;
    }

    public String getWordeng(){return this.eng;}

    public void setWordrus(String name) {
        this.rus = name;
    }

    public void setWordeng(String name) {
        this.eng = name;
    }

    @Override
    public String toString() {
        return rus + " - " + eng;
    }
}