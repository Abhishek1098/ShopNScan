package com.example.shopnscan;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.barcode.Barcode;

import org.w3c.dom.Text;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity{

    private ListView cart;
    private Button checkout, scan;
    private TextView itemTotal, priceTotal;
    private ArrayList<Item> items;
    private ArrayList<Item> database;
    private ItemUI itemUI;

    final private static int BARCODE_REQUEST_CODE = 9232;
    private int itemCount = 0;
    private double price = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cart = (ListView)findViewById(R.id.ui_main_cart);
        checkout = (Button)findViewById(R.id.ui_main_checkout);
        scan = (Button)findViewById(R.id.ui_main_scan);
        itemTotal = (TextView)findViewById(R.id.MainActivity_TextView_ItemTotal);
        priceTotal = (TextView)findViewById(R.id.MainActivity_TextView_TotalPrice);

        itemTotal.setText("Items: 0");
        priceTotal.setText("Price: $0.00");

        items = new ArrayList<>();
        database = new ArrayList<>();
            database.add(new Item("04904403", "Coca-Cola 20oz", 1.99, null));
            database.add(new Item("096619313952", "Kirkland Chewy Chocolate Chip Bar", 1.29, null));
            database.add(new Item("038000357213", "Nutri-Grain Blueberry Bar", 1.29, null));
            database.add(new Item("9780439027021", "Sea of Monsters Novel", 7.99, null));

        itemUI =  new ItemUI(this, R.layout.activity_item, items);
        cart.setAdapter(itemUI);

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.venmo");
                if (launchIntent != null) {
                    startActivity(launchIntent);//null pointer check in case package name was not found
                }
            }
        });

        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ScanActivity.class);
                startActivityForResult(intent,BARCODE_REQUEST_CODE);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==BARCODE_REQUEST_CODE){
            if(resultCode== CommonStatusCodes.SUCCESS){
                if(data!=null){
                    final Barcode barcode = data.getParcelableExtra("barcode");
                    for(Item item : database){
                        if(item.getIndicator().equals(barcode.displayValue)){
                            items.add(item);
                            itemUI.notifyDataSetChanged();
                            itemCount++;
                            itemTotal.setText("Items: "+itemCount);
                            price += item.getPrice();
                            priceTotal.setText("Price: $"+price);
                        }
                    }
                    //scanResult.setText("Barcode Value: "+barcode.displayValue);

                }else{
                    Toast.makeText(this, "No Barcode Found", Toast.LENGTH_SHORT).show();
                }
            }

        }else {

            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
