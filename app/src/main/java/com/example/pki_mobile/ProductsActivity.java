package com.example.pki_mobile;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.pki_mobile.adapters.ProductsAdapter;
import com.example.pki_mobile.utility.Product;
import com.example.pki_mobile.utility.User;

public class ProductsActivity extends AppCompatActivity {
    private final ActivityResultLauncher<Intent> loginLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK) {
                    // User is logged in so update menu items
                    invalidateOptionsMenu();
                }
            }
    );

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
        getMenuInflater().inflate(R.menu.menu, menu);

        // Check if user is logged in or not and update the menu accordingly
        if (User.currentUser == null) {
            menu.findItem(R.id.login_option_menu).setVisible(true);
            menu.findItem(R.id.user_option_menu).setVisible(false);
            menu.findItem(R.id.cart_option_menu).setVisible(false);
        } else {
            menu.findItem(R.id.login_option_menu).setVisible(false);
            menu.findItem(R.id.user_option_menu).setVisible(true);
            menu.findItem(R.id.cart_option_menu).setVisible(true);
        }

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.cart_option_menu) {
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
        } else if (item.getItemId() == R.id.login_option_menu) {
            // User is not logged in so redirect to login page
            Intent intent = new Intent(this, LoginActivity.class);
            loginLauncher.launch(intent);
            return true;
        } else if (item.getItemId() == R.id.about_us_option_menu) {
            // Redirect to About Us page
            Intent intent = new Intent(this, AboutUsActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
