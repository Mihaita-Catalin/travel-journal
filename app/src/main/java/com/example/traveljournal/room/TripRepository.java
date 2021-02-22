package com.example.traveljournal.room;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.traveljournal.models.Trip;

import java.util.List;

class TripRepository {

    private TripDao tripDao;
    private LiveData<List<Trip>> allTrips;

    TripRepository(Application application) {
        TripRoomDatabase db = TripRoomDatabase.getDatabase(application);
        tripDao = db.tripDao();
        allTrips = tripDao.getAllTrips();
    }

    LiveData<List<Trip>> getAllTrips() {
        return allTrips;
    }

    void insert(Trip trip) {
        TripRoomDatabase.databaseWriteExecutor.execute(() -> {
            tripDao.insert(trip);
        });
    }

    void updateTrip(Trip trip) {
        TripRoomDatabase.databaseWriteExecutor.execute(() -> {
            tripDao.updateTrip(trip);
        });
    }
}
