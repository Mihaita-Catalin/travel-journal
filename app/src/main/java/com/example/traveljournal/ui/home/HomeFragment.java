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
import com.example.traveljournal.models.Trip;
import com.example.traveljournal.recyclerView.TripAdapter;
import com.example.traveljournal.room.TripViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static android.app.Activity.RESULT_OK;

public class HomeFragment extends Fragment {

    public static final int NEW_TRIP_ACTIVITY_REQUEST_CODE = 1;

    private TripViewModel tripViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        FloatingActionButton fab = root.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), AddOrEditTripActivity.class);
                startActivityForResult(i, HomeFragment.NEW_TRIP_ACTIVITY_REQUEST_CODE);
            }
        });

        RecyclerView recyclerViewTrips = root.findViewById(R.id.recyclerViewTrips);
        final TripAdapter tripAdapter = new TripAdapter(getActivity());
        recyclerViewTrips.setAdapter(tripAdapter);
        recyclerViewTrips.setLayoutManager(new LinearLayoutManager(getActivity()));

        tripViewModel = new ViewModelProvider(this).get(TripViewModel.class);
        tripViewModel.getAllTrips().observe(getActivity(), trips -> {
            tripAdapter.setTrips(trips);
        });

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
            double rating = Double.parseDouble(data.getStringExtra(AddOrEditTripActivity.RATING));
            Trip trip = new Trip(title, destination, type, price, rating, false);
            tripViewModel.insert(trip);
        }
    }
}