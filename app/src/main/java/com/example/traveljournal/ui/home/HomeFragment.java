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
import com.example.traveljournal.TripAdapter;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        ArrayList<String> mock = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            mock.add("Hello");
        }

        RecyclerView recyclerViewMock = root.findViewById(R.id.recyclerViewTrips);
        recyclerViewMock.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyclerViewMock.setAdapter(new TripAdapter(getActivity(), mock));

        return root;
    }
}