package com.example.shopnscan;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private ListView cart;
    private Button checkout, scan;
    private ArrayList<Item> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cart = (ListView)findViewById(R.id.ui_main_cart);
        checkout = (Button)findViewById(R.id.ui_main_checkout);
        scan = (Button)findViewById(R.id.ui_main_scan);

        items = new ArrayList<>(Arrays.asList(new Item(00, "name", 00, null)));

        ItemUI itemUI =  new ItemUI(this, R.layout.activity_item, items);
        cart.setAdapter(itemUI);

    }
}
