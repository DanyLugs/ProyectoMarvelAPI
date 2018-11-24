package com.danylugo.bottomnavigationproyecto.Model;

import android.graphics.drawable.Drawable;

public class Secondary {

    private String sName;
    private int sImage;

    public Secondary(String sName, int sImage) {
        this.sName = sName;
        this.sImage = sImage;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public int getsImage() {
        return sImage;
    }

    public void setsImage(int sImage) {
        this.sImage = sImage;
    }
}
