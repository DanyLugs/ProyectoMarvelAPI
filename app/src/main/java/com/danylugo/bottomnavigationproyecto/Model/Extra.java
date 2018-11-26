package com.danylugo.bottomnavigationproyecto.Model;

public class Extra {

    private int id;
    private String name;
    private String thumnail;

    public Extra(int id, String name, String thumnail) {
        this.id = id;
        this.name = name;
        this.thumnail = thumnail;
    }

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

    public String getThumnail() {
        return thumnail;
    }

    public void setThumnail(String thumnail) {
        this.thumnail = thumnail;
    }
}
