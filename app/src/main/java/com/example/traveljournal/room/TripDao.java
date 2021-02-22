package com.example.traveljournal.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.TypeConverters;
import androidx.room.Update;

import com.example.traveljournal.models.Trip;

import java.util.List;

@Dao
@TypeConverters(DateConverter.class)
public interface TripDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Trip trip);

    @Query("SELECT * from trips_table")
    LiveData<List<Trip>> getAllTrips();

    @Update
    void updateTrip(Trip trip);
}
