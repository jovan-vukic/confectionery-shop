package com.example.pki_mobile.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.pki_mobile.CartActivity;
import com.example.pki_mobile.R;
import com.example.pki_mobile.utility.User;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    private final List<User.CartItem> cart;
    private final CartActivity parent;

    public CartAdapter(List<User.CartItem> cart, CartActivity parent) {
        this.cart = cart;
        this.parent = parent;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView quantity;
        ImageView image;
        TextView name;
        TextView price;
        ImageView removeCartItemButton;

        public ViewHolder(View itemView) {
            super(itemView);
            quantity = itemView.findViewById(R.id.quantity_cart_item);
            image = itemView.findViewById(R.id.image_cart_item);
            name = itemView.findViewById(R.id.name_cart_item);
            price = itemView.findViewById(R.id.price_cart_item);
            removeCartItemButton = itemView.findViewById(R.id.remove_btn_cart_item);
        }
    }

    @Override
    public @NotNull ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate the cart item layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // Get the cart item for the current position
        User.CartItem cartItem = cart.get(position);
        Context context = holder.itemView.getContext();

        // Set the cart item properties
        holder.name.setText(cartItem.getProduct().getName());
        holder.price.setText(context.getString(R.string.price_simple, cartItem.getPrice()));
        holder.quantity.setText(context.getString(R.string.quantity_value, cartItem.getQuantity()));
        holder.image.setImageResource(cartItem.getProduct().getImage());

        // Set the click listener for the remove button for the current cart item
        holder.removeCartItemButton.setOnClickListener(view -> {
            cart.remove(position);
            notifyItemRemoved(position);
            parent.renderOrderElements();
        });
    }

    @Override
    public int getItemCount() {
        return cart.size();
    }
}
