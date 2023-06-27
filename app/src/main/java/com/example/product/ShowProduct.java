package com.example.product;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class ShowProduct extends AppCompatActivity {

    ArrayList<DataModel> productList = new ArrayList<>();
    RecyclerView recyclerView;
    ProductListAdapter productListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_product);


        Bundle bundle = getIntent().getExtras();
        DataModel product = new DataModel(bundle.getString("title"),bundle.getString("price"),
                bundle.getString("description"),bundle.getString("image"),bundle.getString("category"));

        productList.add(product);
        recyclerView = findViewById(R.id.product_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.hasFixedSize();
        productListAdapter = new ProductListAdapter(this,productList);
        recyclerView.setAdapter(productListAdapter);
    }
}