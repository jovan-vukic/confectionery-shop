package com.example.pki_mobile;

import android.view.MenuItem;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.pki_mobile.adapters.CommentsAdapter;
import com.example.pki_mobile.utility.Product;
import com.example.pki_mobile.utility.User;

import java.util.ArrayList;
import java.util.List;

public class NotificationsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        getSupportActionBar().setTitle(getString(R.string.notifications));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Create comments to display from notifications
        List<Product.Comment> comments = new ArrayList<>();
        for (User.Notification n : User.currentUser.getNotifications()) {
            comments.add(new Product.Comment(-1, n.getComment(), n.getDate()));
        }

        // Set up the RecyclerView for the notifications
        RecyclerView recyclerView = findViewById(R.id.notifications_recycler_view);

        if (comments.isEmpty()) {
            recyclerView.setVisibility(View.GONE);
            findViewById(R.id.header_layout_notifications).setVisibility(View.GONE);
            findViewById(R.id.message_notifications_activity).setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            findViewById(R.id.header_layout_notifications).setVisibility(View.VISIBLE);
            findViewById(R.id.message_notifications_activity).setVisibility(View.GONE);

            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(new CommentsAdapter(comments));
        }
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
