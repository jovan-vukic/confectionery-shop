package com.example.pki_mobile;

import android.content.Intent;
import android.view.MenuItem;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.pki_mobile.utility.Product;
import com.example.pki_mobile.utility.User;

public class ProductDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        getSupportActionBar().setTitle(R.string.product_details);
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

        // Set title and description
        TextView title = findViewById(R.id.title_product_details);
        title.setText(product.getName());

        TextView description = findViewById(R.id.description_product_details);
        description.setText(product.getDescription());

        // Set image and price
        ImageView image = findViewById(R.id.image_product_details);
        image.setImageResource(product.getImage());

        TextView price = findViewById(R.id.price_product_details);
        price.setText(getString(R.string.price_complex, product.getPrice()));

        // Set adapter to quantity spinner with quantity values
        Integer[] quantityValues = new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Spinner quantitySpinner = findViewById(R.id.quantity_spinner_product_details);

        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, quantityValues);
        quantitySpinner.setAdapter(adapter);

        // Create add to cart button click listener
        Button addToCartButton = findViewById(R.id.add_to_cart_btn_product_details);
        addToCartButton.setOnClickListener(view -> {
            if (User.currentUser == null) {
                // Redirect to home page if user is not logged in
                Intent intent = new Intent(this, ProductsActivity.class);

                // Show error message
                Toast.makeText(this, R.string.login_required, Toast.LENGTH_LONG).show();
                startActivity(intent);
            } else {
                // Add product to cart with quantity selected
                int quantity = (Integer) quantitySpinner.getSelectedItem();
                User.currentUser.addCartItem(product, quantity);

                // Show success message and go back to previous activity
                Toast.makeText(this, R.string.added_to_cart, Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        // Create all comments button click listener
        Button allCommentsButton = findViewById(R.id.all_comments_btn_product_details);
        allCommentsButton.setOnClickListener(v -> {
            // Start the AllCommentsActivity with the product ID
            Intent intent = new Intent(ProductDetailsActivity.this, AllCommentsActivity.class);
            intent.putExtra("productId", productID);
            startActivity(intent);
        });

        // Create add comment button click listener
        Button addCommentButton = findViewById(R.id.add_comment_btn_product_details);
        addCommentButton.setOnClickListener(v -> {
            // Start the AddCommentActivity with the product ID
            if (User.currentUser == null) {
                // Redirect to home page if user is not logged in
                Intent intent = new Intent(this, ProductsActivity.class);

                // Show error message
                Toast.makeText(this, R.string.login_required, Toast.LENGTH_LONG).show();
                startActivity(intent);
            }

            Intent intent = new Intent(ProductDetailsActivity.this, AddCommentActivity.class);
            intent.putExtra("productId", productID);
            startActivity(intent);
        });
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
