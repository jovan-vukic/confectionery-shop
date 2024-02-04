package com.example.pki_mobile;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.pki_mobile.utility.User;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Get references to views in activity
        EditText username = findViewById(R.id.username_login_activity);
        EditText password = findViewById(R.id.password_login_activity);
        Button btnLogin = findViewById(R.id.login_button_login_activity);

        // Set on-click listener for login button
        btnLogin.setOnClickListener(view -> {
            String usernameString = username.getText().toString();
            String passwordString = password.getText().toString();

            // Check if username and password are correct and set current user
            for (User user : User.users) {
                if (user.getUsername().equals(usernameString) && user.getPassword().equals(passwordString)) {
                    User.currentUser = user;
                    Toast.makeText(LoginActivity.this, "Uspešno ste ulogovani", Toast.LENGTH_SHORT).show();
                    finish();
                    return;
                }
            }

            // If username and password are incorrect show error
            Toast.makeText(LoginActivity.this, "Pogrešno korisničko ime ili šifra", Toast.LENGTH_LONG).show();
        });
    }
}
