package com.danylugo.bottomnavigationproyecto.Model;

public class Secondary {

    private String name;
    private int image;

    public Secondary(String sName, int sImage) {
        this.name = sName;
        this.image = sImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String sName) {
        this.name = sName;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int sImage) {
        this.image = sImage;
    }
}
