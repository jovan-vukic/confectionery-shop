package com.example.pki_mobile;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.pki_mobile.adapters.ProductsAdapter;
import com.example.pki_mobile.utility.Product;
import com.example.pki_mobile.utility.User;

public class ProductsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Set up the activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        getSupportActionBar().setTitle(R.string.products);

        // Set up the RecyclerView for the products
        RecyclerView recyclerView = findViewById(R.id.products_recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(new ProductsAdapter(Product.products));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Add menu to the action bar if it is present
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.cart_menu) {
            if (User.currentUser == null) {
                // User is not logged in so redirect to login page
                Toast.makeText(this, getString(R.string.login_required), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
            } else {
                // User is logged in so redirect to cart
                Intent intent = new Intent(this, CartActivity.class);
                startActivity(intent);
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
