package com.example.pki_mobile;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.pki_mobile.utility.User;

public class UserDetailsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        getSupportActionBar().setTitle(getString(R.string.user_details));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Get references to the views
        EditText name = findViewById(R.id.name_user_details_activity);
        EditText lastName = findViewById(R.id.last_name_user_details_activity);
        EditText phone = findViewById(R.id.phone_user_details_activity);
        EditText address = findViewById(R.id.address_user_details_activity);
        Button confirmButton = findViewById(R.id.confirm_btn_user_details_activity);

        if (User.currentUser != null) {
            // Set the values of the views
            name.setText(User.currentUser.getName());
            lastName.setText(User.currentUser.getLastName());
            phone.setText(User.currentUser.getPhone());
            address.setText(User.currentUser.getAddress());
        }

        // Confirm button click listener
        confirmButton.setOnClickListener(v -> {
            if (User.currentUser != null) {
                User.currentUser.setName(name.getText().toString());
                User.currentUser.setLastName(lastName.getText().toString());
                User.currentUser.setPhone(phone.getText().toString());
                User.currentUser.setAddress(address.getText().toString());
            }
            Toast.makeText(this, getString(R.string.user_details_updated), Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
