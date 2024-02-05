package com.example.pki_mobile.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import com.example.pki_mobile.PromotionsActivity;
import com.example.pki_mobile.R;
import com.example.pki_mobile.utility.Promotion;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PromotionsAdapter extends PagerAdapter {
    private final PromotionsActivity context;
    private final List<Promotion> promotions;

    public PromotionsAdapter(PromotionsActivity context, List<Promotion> promotions) {
        this.context = context;
        this.promotions = promotions;
    }

    // Methods
    @Override
    public @NotNull Object instantiateItem(@NonNull ViewGroup container, int position) {
        Promotion promotion = promotions.get(position);

        // Inflate the layout for the promotion item
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.promotion_item, container, false);

        // Set the promotion image
        ImageView promotionImage = view.findViewById(R.id.image_promotion_item);
        promotionImage.setImageResource(promotion.getImage());

        // Set the promotion title
        TextView promotionTitle = view.findViewById(R.id.title_promotion_item);
        promotionTitle.setText(promotion.getName());

        // Set the promotion description
        TextView promotionDescription = view.findViewById(R.id.description_promotion_item);
        promotionDescription.setText(promotion.getDescription());

        // Add the view to the container
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull @NotNull ViewGroup container, int position, @NonNull @NotNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NotNull Object object) {
        return view == object;
    }

    @Override
    public int getCount() {
        return promotions.size();
    }

    // Getters and setters
    public PromotionsActivity getContext() {
        return context;
    }

    public List<Promotion> getPromotions() {
        return promotions;
    }
}
