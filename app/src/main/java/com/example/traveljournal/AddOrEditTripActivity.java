package com.example.traveljournal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;

public class AddOrEditTripActivity extends AppCompatActivity {

    public static final String TRIP_TITLE = "TripTitle";
    public static final String DESTINATION = "Destination";
    public static final String TRIP_TYPE = "TripType";
    public static final String PRICE = "Price";
    public static final String RATING = "Rating";

    private EditText editTextTripTitle;
    private EditText editTextDestination;
    private RadioGroup radioGroupTripType;
    private RadioButton radioButtonSelected;
    private TextView textViewSeekBar;
    private SeekBar seekBarPrice;
    private Button buttonStartDate;
    private Button buttonEndDate;
    private RatingBar ratingBar;
    private Button buttonSaveTrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_or_edit_trip);

        editTextTripTitle = findViewById(R.id.editTextTripTitle);
        editTextDestination = findViewById(R.id.editTextDestination);
        radioGroupTripType = findViewById(R.id.radioGroupTripType);
        radioButtonSelected = findViewById(radioGroupTripType.getCheckedRadioButtonId());

        textViewSeekBar = findViewById(R.id.textViewSeekBar);
        seekBarPrice = findViewById(R.id.seekBarPrice);
        seekBarPrice.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textViewSeekBar.setText("Price (" + progress + " EUROS)");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        ratingBar = findViewById(R.id.ratingBar);

        buttonSaveTrip = findViewById(R.id.buttonSaveTrip);
        buttonSaveTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(editTextTripTitle.getText())) {
                    editTextTripTitle.setError("Enter a trip title");
                } else if (TextUtils.isEmpty(editTextDestination.getText())) {
                    editTextDestination.setError("Enter a destination");
                } else {
                    replyIntent.putExtra(TRIP_TITLE, editTextTripTitle.getText().toString());
                    replyIntent.putExtra(DESTINATION, editTextDestination.getText().toString());
                    replyIntent.putExtra(TRIP_TYPE, radioButtonSelected.getText().toString());
                    replyIntent.putExtra(PRICE, Integer.toString(seekBarPrice.getProgress()));
                    replyIntent.putExtra(RATING, Double.toString(ratingBar.getRating()));
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}