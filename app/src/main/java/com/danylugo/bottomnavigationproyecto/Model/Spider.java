package com.danylugo.bottomnavigationproyecto.Model;

import java.io.Serializable;

public class Spider implements Serializable {

    private int id;
    private String name;
    private String description;
    private String thumnail;
    private int comics;

    public Spider(int id, int comics, String name, String description, String thumnail) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.thumnail = thumnail;
        this.comics = comics;
    }

    public int getComics() { return comics; }

    public void setComics(int comics) { this.comics = comics; }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumnail() {
        return thumnail;
    }

    public void setThumnail(String thumnail) {
        this.thumnail = thumnail;
    }
}