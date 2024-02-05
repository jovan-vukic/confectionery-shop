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
        Button changePasswordButton = findViewById(R.id.change_password_btn_user_details_activity);

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
            finish();
        });

        // Change password button click listener
        changePasswordButton.setOnClickListener(v -> {
            if (User.currentUser != null) {
                EditText password = findViewById(R.id.password_user_details_activity);
                EditText newPassword = findViewById(R.id.new_password_user_details_activity);
                EditText repeatedPassword = findViewById(R.id.repeated_password_user_details_activity);

                String passwordText = password.getText().toString();
                String newPasswordText = newPassword.getText().toString();
                String repeatedPasswordText = repeatedPassword.getText().toString();

                if (passwordText.equals(User.currentUser.getPassword())
                        && newPasswordText.equals(repeatedPasswordText)
                        && !newPasswordText.isEmpty()
                        && !newPasswordText.equals(passwordText)) {
                    User.currentUser.setPassword(newPasswordText);
                    Toast.makeText(this, getString(R.string.password_changed), Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(this, getString(R.string.password_not_changed), Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, getString(R.string.user_not_found), Toast.LENGTH_SHORT).show();
            }
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
