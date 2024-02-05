package com.example.pki_mobile.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.pki_mobile.ProductDetailsActivity;
import com.example.pki_mobile.R;
import com.example.pki_mobile.utility.Product;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.ViewHolder> {
    private final List<Product.Comment> comments;

    public CommentsAdapter(List<Product.Comment> comments) {
        this.comments = comments;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView date;
        private final TextView comment;

        public ViewHolder(View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.date_comment_item);
            comment = itemView.findViewById(R.id.comment_comment_item);
        }
    }

    @Override
    public @NotNull CommentsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate the comment layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_item, parent, false);
        return new CommentsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CommentsAdapter.ViewHolder holder, int position) {
        // Get the comment for the current position
        Product.Comment comment = comments.get(position);

        // Set the comment date and text
        String date = comment.getDate().getDayOfMonth()
                + "/" + comment.getDate().getMonthValue()
                + "/" + comment.getDate().getYear();

        holder.date.setText(date);
        holder.comment.setText(comment.getComment());
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }
}
