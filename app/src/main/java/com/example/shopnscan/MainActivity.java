package com.example.shopnscan;

import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private ListView cartList;
    private Button checkoutButton, scanButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cartList = (ListView)findViewById(R.id.MainActivity_ListView_Cart);
        checkoutButton = (Button)findViewById(R.id.MainActivity_Button_Checkout);
        scanButton = (Button)findViewById(R.id.MainActivity_Button_Scan);
    }
}
