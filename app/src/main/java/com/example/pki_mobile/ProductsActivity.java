package com.example.pki_mobile;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
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
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

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

        // Set up the spinner for the product types
        Spinner typeSpinner = findViewById(R.id.type_spinner_product_activity);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.product_types,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(adapter);

        // Set up the listener for the spinner to update the RecyclerView when a new type is selected
        typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedType = (String) parent.getItemAtPosition(position);
                updateRecyclerView(selectedType);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        // Set up the RecyclerView for the products
        RecyclerView recyclerView = findViewById(R.id.products_recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
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
        } else if (item.getItemId() == R.id.notifications_user_option) {
            // Redirect to Notifications page
            Intent intent = new Intent(this, NotificationsActivity.class);
            startActivity(intent);
            return true;
        } else if (item.getItemId() == R.id.edit_details_user_option || item.getItemId() == R.id.change_password_user_option) {
            // Redirect to Edit Details page
            Intent intent = new Intent(this, UserDetailsActivity.class);
            startActivity(intent);
            return true;
        } else if (item.getItemId() == R.id.logout_user_option) {
            // User is logged in so logout
            User.currentUser = null;
            invalidateOptionsMenu();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void updateRecyclerView(String selectedType) {
        // Filter the products based on the selected type
        List<Product> filteredProducts = getProducts(selectedType);

        // Update the RecyclerView with the filtered products
        RecyclerView recyclerView = findViewById(R.id.products_recycler_view);
        recyclerView.setAdapter(new ProductsAdapter(filteredProducts));
    }

    @NotNull
    private static List<Product> getProducts(String selectedType) {
        List<Product> filteredProducts = new ArrayList<>();
        for (Product product : Product.products) {
            if (product.getType().equalsIgnoreCase(selectedType)) {
                filteredProducts.add(product);
            }
        }
        return filteredProducts;
    }
}
