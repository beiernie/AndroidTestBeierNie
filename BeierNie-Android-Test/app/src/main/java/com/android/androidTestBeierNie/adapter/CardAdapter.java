package com.android.androidTestBeierNie.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.androidTestBeierNie.R;
import com.android.androidTestBeierNie.model.Product;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ProductHolder> {

    private Context context;
    private ArrayList<Product> products;

    public CardAdapter(Context context, ArrayList<Product> products) {
        this.context = context;
        this.products = products;
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_card, parent, false);
        return new ProductHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull final ProductHolder holder, int position) {
        Product product = products.get(position);
        holder.setDetails(product);
        Glide.with(context)
                .load(products.get(position).getImageUrl())
                .into(new SimpleTarget<GlideDrawable>() {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        holder.product_image.setImageDrawable(resource);
                    }
                });

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    class ProductHolder extends RecyclerView.ViewHolder {

        private TextView product_name, product_price,product_origin;
        private ImageView product_image;

        ProductHolder(View itemView) {
            super(itemView);
            product_name = itemView.findViewById(R.id.product_name);
            product_price = itemView.findViewById(R.id.product_price);
            product_origin = itemView.findViewById(R.id.product_origin);
            product_image = itemView.findViewById(R.id.product_image);
        }

        void setDetails(Product product) {
            product_name.setText(product.getTitle());
            product_price.setText(product.getUnitPrice());
            product_origin.setText(product.getOrigin());

        }
    }
}