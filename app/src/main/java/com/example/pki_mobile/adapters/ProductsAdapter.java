package com.example.pki_mobile.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.pki_mobile.ProductDetailsActivity;
import com.example.pki_mobile.R;
import com.example.pki_mobile.utility.Product;
import org.jetbrains.annotations.NotNull;

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
            title = itemView.findViewById(R.id.name_product_item);
            price = itemView.findViewById(R.id.price_product_item);
            image = itemView.findViewById(R.id.image_product_item);
        }
    }

    @Override
    public @NotNull ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate the product item layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);

        // Set up the click listener for the product item
        view.setOnClickListener(v -> {
            // Start the ProductDetailsActivity with the product ID
            Intent intent = new Intent(parent.getContext(), ProductDetailsActivity.class);
            intent.putExtra("productId", viewType);
            parent.getContext().startActivity(intent);
        });

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // Get the product for the current position
        Product product = products.get(position);

        // Set the product name and price to the corresponding TextView
        holder.title.setText(product.getName());
        holder.price.setText(holder.price.getContext().getString(R.string.price_simple, product.getPrice()));

        // Set the product image to the corresponding ImageView
        holder.image.setImageResource(product.getImage());
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    @Override
    public int getItemViewType(int position) {
        return products.get(position).getId();
    }
}
