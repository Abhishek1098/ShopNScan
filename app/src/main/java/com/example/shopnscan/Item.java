package com.example.shopnscan;

import android.graphics.Bitmap;

public class Item {

    private String indicator;
    private double price;
    private String name;
    private Bitmap image;

    public Item(String indicator, String name, double price, Bitmap image) {
        this.indicator = indicator;
        this.name = name;
        this.price = price;
        this.image = image;
    }

    public String getIndicator() {
        return indicator;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public Bitmap getImage() {
        return image;
    }
}

