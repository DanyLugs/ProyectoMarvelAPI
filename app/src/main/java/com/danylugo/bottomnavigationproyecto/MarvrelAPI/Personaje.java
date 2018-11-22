package com.danylugo.bottomnavigationproyecto.MarvrelAPI;

public class Personaje {
    public String name;
    public String description;
    public String imageCharacter;

    public Personaje(String name, String variantDescription, String description, String imageCharacter) {
        this.name = name;
        this.description = description;
        this.imageCharacter = imageCharacter;
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

    public String getImageCharacter() {
        return imageCharacter;
    }

    public void setImageCharacter(String imageCharacter) {
        this.imageCharacter = imageCharacter;
    }
}
