package com.example.pki_mobile;

import android.content.Intent;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.pki_mobile.utility.Product;
import com.example.pki_mobile.utility.User;

import java.time.LocalDateTime;

public class AddCommentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_comment);
        getSupportActionBar().setTitle(getString(R.string.add_comment));
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

        // Set confirm button click listener
        Button confirmButton = findViewById(R.id.confirm_btn_add_comment_activity);
        confirmButton.setOnClickListener(v -> {
            String commentString = ((EditText) findViewById(R.id.comment_add_comment_activity)).getText().toString();

            // Check if comment is not empty and add it to product
            if (!commentString.isEmpty()) {
                // Add comment to product
                int userId = User.currentUser.getId();
                product.addComment(userId, commentString);

                // Print success message
                Toast.makeText(this, R.string.comment_added, Toast.LENGTH_SHORT).show();
                finish();
            } else {
                // Print error message
                Toast.makeText(this, R.string.empty_comment, Toast.LENGTH_SHORT).show();
            }
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
