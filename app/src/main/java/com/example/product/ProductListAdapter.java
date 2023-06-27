package com.example.product;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<DataModel> productList;

    public ProductListAdapter(Context context, ArrayList<DataModel> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_list_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DataModel productModel = productList.get(position);
        holder.list_title_tv.setText(productModel.title);
        holder.list_price_tv.setText(productModel.price);
        holder.list_category_tv.setText(productModel.category);
        holder.list_description_tv.setText(productModel.description);
        Glide.with(context).load(productModel.image).into(holder.profile_image);

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView list_title_tv,list_price_tv,list_description_tv,list_category_tv;
        ImageView profile_image;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            list_title_tv = itemView.findViewById(R.id.list_title_tv);
            list_price_tv = itemView.findViewById(R.id.list_price_tv);
            list_description_tv = itemView.findViewById(R.id.list_description_tv);
            list_category_tv = itemView.findViewById(R.id.list_category_tv);
            profile_image = itemView.findViewById(R.id.profile_image);
        }
    }
}
