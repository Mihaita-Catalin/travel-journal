package com.example.traveljournal.recyclerView;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.traveljournal.R;

public class TripViewHolder extends RecyclerView.ViewHolder {

    private final ImageView imageViewTrip;
    private final TextView textViewTitle;
    private final TextView textViewDestination;
    private final TextView textViewRating;
    private final ImageView imageViewBookmarked;
    private final LinearLayout linearLayoutTrip;

    public TripViewHolder(@NonNull View itemView) {
        super(itemView);

        imageViewTrip = itemView.findViewById(R.id.imageViewTrip);
        textViewTitle = itemView.findViewById(R.id.textViewTitle);
        textViewDestination = itemView.findViewById(R.id.textViewDestination);
        textViewRating = itemView.findViewById(R.id.textViewRating);
        imageViewBookmarked = itemView.findViewById(R.id.imageViewBookmarked);
        linearLayoutTrip = itemView.findViewById(R.id.linearLayoutTrip);
    }

    public ImageView getImageViewTrip() {
        return imageViewTrip;
    }

    public TextView getTextViewTitle() {
        return textViewTitle;
    }

    public TextView getTextViewDestination() {
        return textViewDestination;
    }

    public TextView getTextViewRating() {
        return textViewRating;
    }

    public ImageView getImageViewBookmarked() {
        return imageViewBookmarked;
    }

    public LinearLayout getLinearLayoutTrip() {
        return linearLayoutTrip;
    }
}
