package com.example.traveljournal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class ViewTripActivity extends AppCompatActivity {

    private ImageView imageViewTripImage;
    private TextView textViewLocation;
    private TextView textViewTripName;
    private TextView textViewTripType;
    private RatingBar tripRating;
    private TextView textViewStartDate;
    private TextView textViewEndDate;
    private TextView textViewPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_trip);

        imageViewTripImage = findViewById(R.id.imageViewTripImage);
        textViewLocation = findViewById(R.id.textViewLocation);
        textViewTripName = findViewById(R.id.textViewTripName);
        textViewTripType = findViewById(R.id.textViewTripType);
        tripRating = findViewById(R.id.tripRating);
        textViewStartDate = findViewById(R.id.textViewStartDate);
        textViewEndDate = findViewById(R.id.textViewEndDate);
        textViewPrice = findViewById(R.id.textViewPrice);

        populateViews();
    }

    private void populateViews() {
        imageViewTripImage.setImageResource(R.drawable.ic_baseline_airplane);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            textViewLocation.setText(extras.getString(AddOrEditTripActivity.DESTINATION));
            textViewTripName.setText(extras.getString(AddOrEditTripActivity.TRIP_TITLE));
            textViewTripType.setText(extras.getString(AddOrEditTripActivity.TRIP_TYPE));
            tripRating.setRating((float) Double.parseDouble(extras.getString(AddOrEditTripActivity.RATING)));
            String [] startDateArray = extras.getString(AddOrEditTripActivity.START_DATE).split(" ");
            String [] endDateArray = extras.getString(AddOrEditTripActivity.END_DATE).split(" ");
            textViewStartDate.setText(startDateArray[2] + " " + startDateArray[1] + " " + startDateArray[5]);
            textViewEndDate.setText(endDateArray[2] + " " + endDateArray[1] + " " + endDateArray[5]);
            textViewPrice.setText(extras.getString(AddOrEditTripActivity.PRICE));
        }
    }
}