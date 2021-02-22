package com.example.traveljournal.ui.contact;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import mehdi.sakout.aboutpage.AboutPage;

public class ContactFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return new AboutPage(getActivity())
                .isRTL(false)
                .enableDarkMode(false)
                .addEmail("pavel.mihaita.catalin2000@gmail.com")
                .addFacebook("Pavel.Mihaita.Catalin")
                .addGitHub("Mihaita-Catalin")
                .addInstagram("pavelmihaita")
                .create();
    }
}