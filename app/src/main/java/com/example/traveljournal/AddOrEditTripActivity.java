package com.example.traveljournal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.traveljournal.utils.CustomDatePickerFragment;

public class AddOrEditTripActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    public static final String TRIP_TITLE = "TripTitle";
    public static final String DESTINATION = "Destination";
    public static final String TRIP_TYPE = "TripType";
    public static final String PRICE = "Price";
    public static final String START_DATE = "StartDate";
    public static final String END_DATE = "EndDate";
    public static final String RATING = "Rating";
    public static final String WAS = "Was";
    public static final String FAVORITE = "Favorite";

    public static final int MULTIPLICATION_FACTOR = 10;

    private EditText editTextTripTitle;
    private EditText editTextDestination;
    private RadioGroup radioGroupTripType;
    private RadioButton radioButtonSelected;
    private TextView textViewSeekBar;
    private SeekBar seekBarPrice;
    private Button buttonStartDate;
    private Button buttonEndDate;
    private RatingBar ratingBar;
    private Button buttonMarkFavorite;
    private Button buttonSaveTrip;

    private boolean currentDatePick;
    private String startDateString;
    private String endDateString;
    private boolean wasAlready = false;
    private boolean isFavorite = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_or_edit_trip);

        editTextTripTitle = findViewById(R.id.editTextTripTitle);
        editTextDestination = findViewById(R.id.editTextDestination);
        radioGroupTripType = findViewById(R.id.radioGroupTripType);

        textViewSeekBar = findViewById(R.id.textViewSeekBar);
        seekBarPrice = findViewById(R.id.seekBarPrice);
        seekBarPrice.setMax(300);
        seekBarPrice.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textViewSeekBar.setText("Price (" + progress * MULTIPLICATION_FACTOR + " EUROS)");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        buttonStartDate = findViewById(R.id.buttonStartDate);
        startDateString = getString(R.string.date_format);
        buttonStartDate.setOnClickListener(v -> {
            currentDatePick = false;
            DialogFragment newFragment = new CustomDatePickerFragment();
            newFragment.show(getSupportFragmentManager(), "view");
        });

        buttonEndDate = findViewById(R.id.buttonEndDate);
        endDateString = getString(R.string.date_format);
        buttonEndDate.setOnClickListener(v -> {
            currentDatePick = true;
            DialogFragment newFragment = new CustomDatePickerFragment();
            newFragment.show(getSupportFragmentManager(), "view");
        });

        ratingBar = findViewById(R.id.ratingBar);

        buttonMarkFavorite = findViewById(R.id.buttonMakeFavorite);
        buttonMarkFavorite.setOnClickListener(v -> {
            isFavorite = !isFavorite;
            if (isFavorite) {
                buttonMarkFavorite.setText(R.string.mark_off);
            } else {
                buttonMarkFavorite.setText(getString(R.string.mark_as_favorite));
            }
        });

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            wasAlready = true;
            editTextTripTitle.setText(extras.getString(TRIP_TITLE));
            editTextDestination.setText(extras.getString(DESTINATION));
            if (extras.getString(TRIP_TYPE).equals(getString(R.string.city_break))) {
                radioGroupTripType.check(R.id.radioButtonCityBreak);
            } else if (extras.getString(TRIP_TYPE).equals(getString(R.string.seaside))) {
                radioGroupTripType.check(R.id.radioButtonSeaside);
            } else if (extras.getString(TRIP_TYPE).equals(getString(R.string.mountains))) {
                radioGroupTripType.check(R.id.radioButtonMountains);
            }
            seekBarPrice.setProgress(Integer.parseInt(extras.getString(PRICE)));
            textViewSeekBar.setText("Price (" + seekBarPrice.getProgress() *
                    MULTIPLICATION_FACTOR + " EUROS)");
            startDateString = extras.getString(START_DATE);
            buttonStartDate.setHint(startDateString);
            endDateString = extras.getString(END_DATE);
            buttonEndDate.setHint(endDateString);
            ratingBar.setRating((float) Double.parseDouble(extras.getString(RATING)));
            if (extras.getBoolean(FAVORITE, false)) {
                isFavorite = true;
                buttonMarkFavorite.setText(getString(R.string.mark_off));
            }
        }


        buttonSaveTrip = findViewById(R.id.buttonSaveTrip);
        buttonSaveTrip.setOnClickListener(v -> {
            Intent replyIntent = new Intent();
            if (TextUtils.isEmpty(editTextTripTitle.getText())) {
                editTextTripTitle.setError(getString(R.string.enter_a_trip_title_error));
            } else if (TextUtils.isEmpty(editTextDestination.getText())) {
                editTextDestination.setError(getString(R.string.enter_a_destination_error));
            } else if (startDateString.contains(" ") || endDateString.contains(" ")) {
                Toast.makeText(this, getString(R.string.select_date), Toast.LENGTH_SHORT).show();
            } else if (startDateString.contains("DD") || endDateString.contains("DD")) {
                Toast.makeText(this, getString(R.string.select_date), Toast.LENGTH_SHORT).show();
            } else {
                if (wasAlready) {
                    replyIntent.putExtra(WAS, getString(R.string.was_string));
                }
                replyIntent.putExtra(TRIP_TITLE, editTextTripTitle.getText().toString());
                replyIntent.putExtra(DESTINATION, editTextDestination.getText().toString());
                radioButtonSelected = findViewById(radioGroupTripType.getCheckedRadioButtonId());
                replyIntent.putExtra(TRIP_TYPE, radioButtonSelected.getText().toString());
                replyIntent.putExtra(PRICE, Integer.toString(seekBarPrice.getProgress() *
                        MULTIPLICATION_FACTOR));
                replyIntent.putExtra(START_DATE, startDateString);
                replyIntent.putExtra(END_DATE, endDateString);
                replyIntent.putExtra(RATING, Double.toString(ratingBar.getRating()));
                replyIntent.putExtra(FAVORITE, isFavorite);
                setResult(RESULT_OK, replyIntent);
                finish();
            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        if (!currentDatePick) {
            startDateString = dayOfMonth + "/" + (month + 1) + "/" + year;
            buttonStartDate.setHint(startDateString);
        } else {
            endDateString = dayOfMonth + "/"  + (month + 1) + "/" + year;
            buttonEndDate.setHint(endDateString);
        }
    }
}