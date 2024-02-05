package com.example.pki_mobile;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.pki_mobile.adapters.CartAdapter;
import com.example.pki_mobile.utility.User;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        getSupportActionBar().setTitle(getString(R.string.cart));

        // Set up the RecyclerView for the products
        RecyclerView recyclerView = findViewById(R.id.cart_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<User.CartItem> cart = User.currentUser != null && User.currentUser.getCart() != null ? User.currentUser.getCart() : new ArrayList<>();
        recyclerView.setAdapter(new CartAdapter(cart, this));

        // Set up the order button and total price label
        renderOrderElements();
    }

    public void renderOrderElements() {
        // Set up the order button
        Button orderButton = findViewById(R.id.order_btn_cart_activity);
        orderButton.setEnabled(User.currentUser != null && User.currentUser.getCart() != null && !User.currentUser.getCart().isEmpty());
        orderButton.setOnClickListener(view -> placeOrder());

        // Set up the total price label
        TextView totalPrice = findViewById(R.id.total_cart_activity);
        int total = User.currentUser != null && User.currentUser.getCart() != null
                ? User.currentUser.getCart().stream().mapToInt(User.CartItem::getPrice).sum()
                : 0;
        totalPrice.setText(getString(R.string.total_price, total));
    }

    private void placeOrder() {
        // Place the order and clear the cart
        if (User.currentUser != null && User.currentUser.getCart() != null) {
            // Place the order and simulate notification to the user
            User.currentUser.addOrder(User.currentUser.getCart());
            User.currentUser.addNotification(getString(R.string.order_placed));

            // Clear the cart
            User.currentUser.getCart().clear();
            Toast.makeText(this, getString(R.string.order_success), Toast.LENGTH_LONG).show();
            finish();
        }
    }
}
