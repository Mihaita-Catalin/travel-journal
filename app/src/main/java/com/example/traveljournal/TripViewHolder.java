package com.example.traveljournal;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TripViewHolder extends RecyclerView.ViewHolder {

    private final TextView textViewMock;
    private final LinearLayout linearLayoutTrip;

    public TripViewHolder(@NonNull View itemView) {
        super(itemView);

        textViewMock = itemView.findViewById(R.id.mockTextView);
        linearLayoutTrip = itemView.findViewById(R.id.linearLayoutTrip);
    }

    public TextView getTextViewMock() {
        return textViewMock;
    }

    public LinearLayout getLinearLayoutTrip() {
        return linearLayoutTrip;
    }
}
