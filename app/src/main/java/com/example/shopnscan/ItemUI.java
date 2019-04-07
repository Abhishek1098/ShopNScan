package com.example.shopnscan;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ItemUI extends ArrayAdapter<Item> {

    private Context context;
    private List<Item> items;

    public ItemUI(Context context, int resource, List<Item> items) {
        super(context, resource, items);
        this.context = context;
        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.activity_item, null);

        TextView viewName = view.findViewById(R.id.ui_item_name);
        TextView viewPrice = view.findViewById(R.id.ui_item_price);

        String name = items.get(position).getName();
        String price = "$" + items.get(position).getPrice();
        Bitmap image = items.get(position).getImage();

        viewName.setText(name);
        viewPrice.setText(price);

        return view;
    }
}
