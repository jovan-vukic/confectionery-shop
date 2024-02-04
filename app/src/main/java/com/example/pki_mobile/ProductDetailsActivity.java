package com.example.pki_mobile;

import android.content.Intent;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.pki_mobile.utility.Product;
import com.example.pki_mobile.utility.User;

public class ProductDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        int productID = getIntent().getIntExtra("productId", -1);
        if (productID == -1 || productID > Product.products.size()) {
            // Print error message & go back
            Toast.makeText(this, "Proizvod nije pronađen", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        Product product = Product.products.get(productID);

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
        Integer[] quantityValues = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Spinner quantitySpinner = findViewById(R.id.quantity_spinner_product_details);

        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, quantityValues);
        quantitySpinner.setAdapter(adapter);

        // Create add to cart button click listener
        Button addToCartButton = findViewById(R.id.add_to_cart_btn_product_details);
        addToCartButton.setOnClickListener(view -> {
            // Add product to cart with quantity selected
            int quantity = (Integer) quantitySpinner.getSelectedItem();
            // User.currentUser.addCartItem(product, quantity);

            // Show success message and go back to previous activity
            Toast.makeText(this, "Proizvod je dodat u korpu", Toast.LENGTH_LONG).show();
            finish();
        });
    }
}
