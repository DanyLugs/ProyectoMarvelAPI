package com.danylugo.bottomnavigationproyecto.Model;

public class Secondary {

    private String name;
    private String image;

    public Secondary(String sName, String sImage) {
        this.name = sName;
        this.image = sImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String sName) {
        this.name = sName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String sImage) {
        this.image = sImage;
    }
}
