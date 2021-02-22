package com.example.traveljournal.ui.share;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.traveljournal.R;

public class ShareFragment extends Fragment {

    private Button buttonShare;
    private static final String GITHUB_URL = "Travel Journal Android App : https://github.com/Mihaita-Catalin/travel-journal.git";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_share, container, false);

        buttonShare = root.findViewById(R.id.buttonShare);
        buttonShare.setOnClickListener(v -> {
            Intent i = new Intent();
            i.setAction(Intent.ACTION_SEND);
            i.putExtra(Intent.EXTRA_TEXT, GITHUB_URL);
            i.setType("text/*");

            Intent shareIntent = Intent.createChooser(i, null);
            startActivity(shareIntent);
        });

        return root;
    }
}