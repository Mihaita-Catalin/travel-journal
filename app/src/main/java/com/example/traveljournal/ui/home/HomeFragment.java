package com.example.traveljournal.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.traveljournal.R;
import com.example.traveljournal.trip.Trip;
import com.example.traveljournal.trip.TripAdapter;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        ArrayList<Trip> trips = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            Trip trip = new Trip(getResources().getString(R.string.mock_text), getResources().getString(R.string.mock_text), 4.5, false);
            trips.add(trip);
        }

        RecyclerView recyclerViewTrips = root.findViewById(R.id.recyclerViewTrips);
        recyclerViewTrips.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewTrips.setAdapter(new TripAdapter(getActivity(), trips));

        return root;
    }
}