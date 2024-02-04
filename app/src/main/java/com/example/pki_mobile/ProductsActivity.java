package com.example.pki_mobile;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.pki_mobile.utility.Product;

public class ProductsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Set up the activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        // Set up the RecyclerView for the products
        RecyclerView recyclerView = findViewById(R.id.products_recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(new ProductsAdapter(Product.products));
    }
}
