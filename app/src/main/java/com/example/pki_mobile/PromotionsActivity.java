package com.example.pki_mobile;

import android.view.MenuItem;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.viewpager.widget.ViewPager;
import com.example.pki_mobile.adapters.PromotionsAdapter;
import com.example.pki_mobile.utility.Promotion;

public class PromotionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promotions);
        getSupportActionBar().setTitle(getString(R.string.promotions));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Set up the ViewPager with the promotions adapter
        ViewPager viewPager = findViewById(R.id.view_pager_promotions);
        viewPager.setAdapter(new PromotionsAdapter(this, Promotion.promotions));

        // Set up the next and previous buttons for the ViewPager
        Button previousButton = findViewById(R.id.previous_btn_promotions_activity);
        Button nextButton = findViewById(R.id.next_btn_promotions_activity);

        previousButton.setOnClickListener(v -> {
            int currentPosition = viewPager.getCurrentItem();
            if (currentPosition > 0) {
                viewPager.setCurrentItem(currentPosition - 1);
            }
        });

        nextButton.setOnClickListener(v -> {
            int currentPosition = viewPager.getCurrentItem();
            if (currentPosition < viewPager.getAdapter().getCount() - 1) {
                viewPager.setCurrentItem(currentPosition + 1);
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
