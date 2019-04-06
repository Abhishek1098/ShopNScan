package com.example.shopnscan;

import android.graphics.Bitmap;

public class Item {

    private int indicator, price;
    private String name;
    private Bitmap image;

    public Item(int indicator, String name, int price, Bitmap image) {
        this.indicator = indicator;
        this.name = name;
        this.price = price;
        this.image = image;
    }

    public int getIndicator() {
        return indicator;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public Bitmap getImage() {
        return image;
    }
}

