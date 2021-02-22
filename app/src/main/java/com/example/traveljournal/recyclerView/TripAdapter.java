package com.example.traveljournal.recyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.traveljournal.R;
import com.example.traveljournal.models.Trip;

import java.util.List;

public class TripAdapter extends RecyclerView.Adapter<TripViewHolder> {

    private final LayoutInflater inflater;
    private List<Trip> trips;

    public TripAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public TripViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.trip_item, parent, false);
        return new TripViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TripViewHolder holder, int position) {
        Trip currentTrip = trips.get(position);
        holder.getImageViewTrip().setImageResource(R.drawable.ic_baseline_airplane);
        holder.getTextViewTitle().setText(currentTrip.getTitle());
        holder.getTextViewDestination().setText(currentTrip.getDestination());
        holder.getTextViewRating().setText(String.valueOf(currentTrip.getRating()));
        if (currentTrip.isFavorite()) {
            holder.getImageViewBookmarked().setImageResource(R.drawable.ic_baseline_bookmarked);
        } else {
            holder.getImageViewBookmarked().setImageResource(R.drawable.ic_baseline_not_bookmarked);
        }
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (trips != null) {
            return trips.size();
        } else {
            return 0;
        }
    }
}
