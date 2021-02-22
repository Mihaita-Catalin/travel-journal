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
                new RecyclerItemClickListener(getActivity(), recyclerViewTrips, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Intent i = new Intent(getActivity(), ViewTripActivity.class);
                        tripViewModel.getAllTrips().observe(getActivity(), trips -> {
                            i.putExtra(AddOrEditTripActivity.TRIP_TITLE, trips.get(position).getTitle());
                            i.putExtra(AddOrEditTripActivity.DESTINATION, trips.get(position).getDestination());
                            i.putExtra(AddOrEditTripActivity.TRIP_TYPE, trips.get(position).getType());
                            i.putExtra(AddOrEditTripActivity.PRICE, Integer.toString(trips.get(position).getPrice()));
                            i.putExtra(AddOrEditTripActivity.START_DATE, trips.get(position).getStartDate().toString());
                            i.putExtra(AddOrEditTripActivity.END_DATE, trips.get(position).getEndDate().toString());
                            i.putExtra(AddOrEditTripActivity.RATING, Double.toString(trips.get(position).getRating()));
                        });
                        startActivity(i);
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        Intent i = new Intent(getActivity(), AddOrEditTripActivity.class);
                        final String[] title = new String[1];
                        final String[] destination = new String[1];
                        final String[] type = new String[1];
                        final int[] price = new int[1];
                        final String[] startDateString = new String[1];
                        final String[] endDateString = new String[1];
                        final double[] rating = new double[1];
                        tripViewModel.getAllTrips().observe(getActivity(), trips -> {
                            title[0] = trips.get(position).getTitle();
                            destination[0] = trips.get(position).getDestination();
                            type[0] = trips.get(position).getType();
                            price[0] = trips.get(position).getPrice() / AddOrEditTripActivity.MULTIPLICATION_FACTOR;
                            startDateString[0] = trips.get(position).getStartDate().toString();
                            endDateString[0] = trips.get(position).getEndDate().toString();
                            rating[0] = trips.get(position).getRating();
                        });
                        i.putExtra(AddOrEditTripActivity.TRIP_TITLE, title[0]);
                        i.putExtra(AddOrEditTripActivity.DESTINATION, destination[0]);
                        i.putExtra(AddOrEditTripActivity.TRIP_TYPE, type[0]);
                        i.putExtra(AddOrEditTripActivity.PRICE, Integer.toString(price[0]));
                        i.putExtra(AddOrEditTripActivity.START_DATE, startDateString[0]);
                        i.putExtra(AddOrEditTripActivity.END_DATE, endDateString[0]);
                        i.putExtra(AddOrEditTripActivity.RATING, Double.toString(rating[0]));
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

            SimpleDateFormat format = new SimpleDateFormat("d/M/y");
            Date startDate = null;
            Date endDate = null;
            try {
                startDate = format.parse(startDateString);
                endDate = format.parse(endDateString);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Trip trip = new Trip(title, destination, type, price, startDate, endDate, rating,
                    false);
            tripViewModel.insert(trip);
        }
    }
}