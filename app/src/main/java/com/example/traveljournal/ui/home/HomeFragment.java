package com.example.traveljournal.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.traveljournal.AddOrEditTripActivity;
import com.example.traveljournal.R;
import com.example.traveljournal.ViewTripActivity;
import com.example.traveljournal.models.Trip;
import com.example.traveljournal.recyclerView.RecyclerItemClickListener;
import com.example.traveljournal.recyclerView.TripAdapter;
import com.example.traveljournal.room.TripViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.app.Activity.RESULT_OK;

public class HomeFragment extends Fragment {

    public static final int NEW_TRIP_ACTIVITY_REQUEST_CODE = 1;

    private TripViewModel tripViewModel;
    private TripAdapter tripAdapter;
    private Trip trip;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        FloatingActionButton fab = root.findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Intent i = new Intent(getActivity(), AddOrEditTripActivity.class);
            startActivityForResult(i, NEW_TRIP_ACTIVITY_REQUEST_CODE);
        });

        RecyclerView recyclerViewTrips = root.findViewById(R.id.recyclerViewTrips);
        tripAdapter = new TripAdapter(getActivity());
        recyclerViewTrips.setAdapter(tripAdapter);
        recyclerViewTrips.setLayoutManager(new LinearLayoutManager(getActivity()));

        tripViewModel = new ViewModelProvider(this).get(TripViewModel.class);
        tripViewModel.getAllTrips().observe(getActivity(), trips -> {
            tripAdapter.setTrips(trips);
        });

        recyclerViewTrips.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), recyclerViewTrips,
                        new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Intent i = new Intent(getActivity(), ViewTripActivity.class);
                        tripViewModel.getAllTrips().observe(getActivity(), trips -> {
                            i.putExtra(AddOrEditTripActivity.TRIP_TITLE, trips.get(position)
                                    .getTitle());
                            i.putExtra(AddOrEditTripActivity.DESTINATION, trips.get(position)
                                    .getDestination());
                            i.putExtra(AddOrEditTripActivity.TRIP_TYPE, trips.get(position)
                                    .getType());
                            i.putExtra(AddOrEditTripActivity.PRICE, Integer.toString(trips
                                    .get(position).getPrice()));
                            i.putExtra(AddOrEditTripActivity.START_DATE, trips.get(position)
                                    .getStartDate().toString());
                            i.putExtra(AddOrEditTripActivity.END_DATE, trips.get(position)
                                    .getEndDate().toString());
                            i.putExtra(AddOrEditTripActivity.RATING, Double.toString(trips
                                    .get(position).getRating()));
                        });
                        startActivity(i);
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        Intent i = new Intent(getActivity(), AddOrEditTripActivity.class);
                        tripViewModel.getAllTrips().observe(getActivity(), trips -> {
                            trip = trips.get(position);
                            i.putExtra(AddOrEditTripActivity.TRIP_TITLE, trips.get(position)
                                    .getTitle());
                            i.putExtra(AddOrEditTripActivity.DESTINATION, trips.get(position)
                                    .getDestination());
                            i.putExtra(AddOrEditTripActivity.TRIP_TYPE, trips.get(position)
                                    .getType());
                            i.putExtra(AddOrEditTripActivity.PRICE, Integer.toString(trips
                                    .get(position)
                                    .getPrice() / AddOrEditTripActivity.MULTIPLICATION_FACTOR));
                            i.putExtra(AddOrEditTripActivity.START_DATE, trips.get(position)
                                    .getStartDate().toString());
                            i.putExtra(AddOrEditTripActivity.END_DATE, trips.get(position)
                                    .getEndDate().toString());
                            i.putExtra(AddOrEditTripActivity.RATING, Double.toString(trips
                                    .get(position).getRating()));
                            i.putExtra(AddOrEditTripActivity.FAVORITE, trips.get(position)
                                    .isFavorite());
                        });
                        startActivityForResult(i, NEW_TRIP_ACTIVITY_REQUEST_CODE);
                    }
                })
        );

        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_TRIP_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            String title = data.getStringExtra(AddOrEditTripActivity.TRIP_TITLE);
            String destination = data.getStringExtra(AddOrEditTripActivity.DESTINATION);
            String type = data.getStringExtra(AddOrEditTripActivity.TRIP_TYPE);
            int price = Integer.parseInt(data.getStringExtra(AddOrEditTripActivity.PRICE));
            String startDateString = data.getStringExtra(AddOrEditTripActivity.START_DATE);
            String endDateString = data.getStringExtra(AddOrEditTripActivity.END_DATE);
            double rating = Double.parseDouble(data.getStringExtra(AddOrEditTripActivity.RATING));
            boolean isFavorite = data.getBooleanExtra(AddOrEditTripActivity.FAVORITE,
                    false);

            SimpleDateFormat format = new SimpleDateFormat("d/M/y");
            Date startDate = null;
            Date endDate = null;
            try {
                startDate = format.parse(startDateString);
                endDate = format.parse(endDateString);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if (data.getStringExtra(AddOrEditTripActivity.WAS) != null) {
                trip.setTitle(title);
                trip.setDestination(destination);
                trip.setType(type);
                trip.setPrice(price);
                trip.setStartDate(startDate);
                trip.setEndDate(endDate);
                trip.setRating(rating);
                trip.setFavorite(isFavorite);
                tripViewModel.updateTrip(trip);
            } else {
                Trip trip = new Trip(title, destination, type, price, startDate, endDate, rating,
                        isFavorite);
                tripViewModel.insert(trip);
            }
        }
    }
}