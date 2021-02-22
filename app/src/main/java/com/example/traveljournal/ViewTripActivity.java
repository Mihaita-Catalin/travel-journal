package com.example.traveljournal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.traveljournal.retrofit.GetData;
import com.example.traveljournal.retrofit.RetrofitObj;
import com.example.traveljournal.retrofit.WeatherResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ViewTripActivity extends AppCompatActivity {

    private ImageView imageViewTripImage;
    private TextView textViewLocation;
    private TextView textViewTripName;
    private TextView textViewTripType;
    private RatingBar tripRating;
    private TextView textViewStartDate;
    private TextView textViewEndDate;
    private TextView textViewPrice;
    private TextView textViewWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_trip);

        imageViewTripImage = findViewById(R.id.imageViewTripImage);
        textViewLocation = findViewById(R.id.textViewLocation);
        textViewTripName = findViewById(R.id.textViewTripName);
        textViewTripType = findViewById(R.id.textViewTripType);
        tripRating = findViewById(R.id.tripRating);
        tripRating.setIsIndicator(true);
        textViewStartDate = findViewById(R.id.textViewStartDate);
        textViewEndDate = findViewById(R.id.textViewEndDate);
        textViewPrice = findViewById(R.id.textViewPrice);
        textViewWeather = findViewById(R.id.textViewWeather);

        populateViews();
        getCurrentData();
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
            textViewPrice.setText(extras.getString(AddOrEditTripActivity.PRICE) + " EUROS");
        }
    }

    void getCurrentData() {
        GetData service = RetrofitObj.getRetrofitInstance().create(GetData.class);
        Call<WeatherResponse> call = service.getCurrentWeatherData(textViewLocation.getText().toString(), RetrofitObj.AppId);
        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(@NonNull Call<WeatherResponse> call, @NonNull Response<WeatherResponse> response) {
                if (response.code() == 200) {
                    WeatherResponse weatherResponse = response.body();
                    assert weatherResponse != null;

                    String stringBuilder = "Description : " +
                            weatherResponse.weather.get(0).description +
                            "\n" +
                            "Temperature : " +
                            weatherResponse.main.temp +
                            "\n" +
                            "Humidity : " +
                            weatherResponse.main.humidity +
                            "\n" +
                            "Speed of wind : " +
                            weatherResponse.wind.speed;

                    textViewWeather.setText(stringBuilder);
                }
            }

            @Override
            public void onFailure(@NonNull Call<WeatherResponse> call, @NonNull Throwable t) {
                textViewWeather.setText(t.getMessage());
            }
        });
    }
}