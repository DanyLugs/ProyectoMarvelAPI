package com.danylugo.bottomnavigationproyecto.Model;

import android.graphics.drawable.Drawable;

import java.io.Serializable;
import java.util.List;

public class Spider implements Serializable {

    private int id;
    private String name;
    private String description;
    private String thumnail;
    private int imageCard;
    private List<Secondary> enemies;
    private List<Secondary> allies;

    public Spider(int id, String name, String description, String thumnail, int imageCard, List<Secondary> enemies, List<Secondary> allies) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.thumnail = thumnail;
        this.imageCard = imageCard;
        this.enemies = enemies;
        this.allies = allies;
    }

    public int getId() {
        return imageCard;
    }

    public void setId(int imageCard) {
        this.imageCard = imageCard;
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

    public int getImageCard() {
        return imageCard;
    }

    public void setImageCard(int imageCard) {
        this.imageCard = imageCard;
    }

    public List<Secondary> getEnemies() {
        return enemies;
    }

    public void setEnemies(List<Secondary> enemies) {
        this.enemies = enemies;
    }

    public List<Secondary> getAllies() {
        return allies;
    }

    public void setAllies(List<Secondary> allies) {
        this.allies = allies;
    }
}