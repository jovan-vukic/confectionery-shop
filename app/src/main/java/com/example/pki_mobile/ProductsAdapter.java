package com.example.pki_mobile;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {
    private final List<Product> products;

    public ProductsAdapter(List<Product> products) {
        this.products = products;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView price;
        ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
            image = itemView.findViewById(R.id.img);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate the product item layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // Get the product for the current position
        Product product = products.get(position);

        // Set the product name and price to the corresponding TextView
        holder.title.setText(product.getName());
        holder.price.setText(holder.price.getContext().getString(R.string.price, product.getPrice()));

        // Set the product image to the corresponding ImageView
        holder.image.setImageResource(product.getImage());
    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}
