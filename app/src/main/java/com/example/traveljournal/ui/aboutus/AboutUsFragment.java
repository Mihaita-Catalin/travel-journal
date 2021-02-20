package com.example.traveljournal.ui.aboutus;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.traveljournal.R;

public class AboutUsFragment extends Fragment {

    private com.example.traveljournal.ui.aboutus.AboutUsViewModel aboutUsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        aboutUsViewModel =
                new ViewModelProvider(this).get(com.example.traveljournal.ui.aboutus.AboutUsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_about_us, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        aboutUsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}