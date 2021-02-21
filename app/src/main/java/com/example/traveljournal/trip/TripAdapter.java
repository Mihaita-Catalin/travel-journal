package com.example.traveljournal.trip;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.traveljournal.R;

import java.util.List;

public class TripAdapter extends RecyclerView.Adapter<TripViewHolder> {

    private List<Trip> trips;
    private Context context;

    public TripAdapter(Context context, List<Trip> trips) {
        this.trips = trips;
        this.context = context;
    }

    @NonNull
    @Override
    public TripViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.trip_item, parent, false);
        return new TripViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TripViewHolder holder, int position) {
        Trip currentTrip = trips.get(position);
        holder.getImageViewTrip().setImageResource(R.drawable.ic_baseline_airplane);
        holder.getTextViewTitle().setText(currentTrip.getTitle());
        holder.getTextViewDestination().setText(currentTrip.getDestination());
        holder.getTextViewRating().setText(String.valueOf(currentTrip.getRating()));
        holder.getImageViewBookmarked().setImageResource(R.drawable.ic_baseline_not_bookmarked);
    }

    @Override
    public int getItemCount() {
        return trips.size();
    }
}
