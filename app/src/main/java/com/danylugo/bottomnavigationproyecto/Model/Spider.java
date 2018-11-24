package com.danylugo.bottomnavigationproyecto.Model;

import android.graphics.drawable.Drawable;

import java.io.Serializable;
import java.util.List;

public class Spider implements Serializable {

    private String name;
    private String description;
    private String status;
    private Drawable image;
    private Drawable imageCard;
    private List<Secondary> enemies;
    private List<Secondary> allies;

    public Spider(String name, String description, String status, Drawable image, Drawable imageCard, List<Secondary> enemies, List<Secondary> allies) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.image = image;
        this.imageCard = imageCard;
        this.enemies = enemies;
        this.allies = allies;
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

    public Drawable getImageCard() {
        return imageCard;
    }

    /*public void setImageCard(Drawable imageCard) {
        this.imageCard = imageCard;
    }*/

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
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