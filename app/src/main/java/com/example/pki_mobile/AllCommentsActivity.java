package com.example.pki_mobile;

import android.view.MenuItem;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.pki_mobile.adapters.CommentsAdapter;
import com.example.pki_mobile.adapters.ProductsAdapter;
import com.example.pki_mobile.utility.Product;

public class AllCommentsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_comments);
        getSupportActionBar().setTitle(getString(R.string.comments));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Get product with given ID
        int productID = getIntent().getIntExtra("productId", -1);
        if (productID == -1 || productID > Product.products.size()) {
            // Print error message & go back
            Toast.makeText(this, R.string.invalid_product, Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        Product product = Product.products.get(productID - 1);

        // Set up the RecyclerView for the comments
        RecyclerView recyclerView = findViewById(R.id.comments_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new CommentsAdapter(product.getComments()));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // If user presses back button in action bar, go back to previous activity
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
