package com.example.pki_mobile;

import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.pki_mobile.utility.User;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().setTitle(getString(R.string.register));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Get references to the views
        EditText name = findViewById(R.id.name_register_activity);
        EditText lastName = findViewById(R.id.last_name_register_activity);
        EditText phone = findViewById(R.id.phone_register_activity);
        EditText address = findViewById(R.id.address_register_activity);
        EditText username = findViewById(R.id.username_register_activity);
        EditText password = findViewById(R.id.password_register_activity);
        boolean isBuyer = ((RadioButton) findViewById(R.id.radio_buyer_register_activity)).isChecked();
        Button confirmButton = findViewById(R.id.confirm_btn_register_activity);

        // Confirm button click listener
        confirmButton.setOnClickListener(v -> {
            // Create a new user
            User.users.add(new User(
                    name.getText().toString(),
                    lastName.getText().toString(),
                    phone.getText().toString(),
                    address.getText().toString(),
                    isBuyer ? "kupac" : "zaposleni",
                    username.getText().toString(),
                    password.getText().toString()
            ));

            // Show a toast
            Toast.makeText(this, "User registered successfully!", Toast.LENGTH_SHORT).show();
            finish();
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
