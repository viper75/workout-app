package org.viper75.workout_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import org.viper75.workout_app.databinding.WorkoutDetailsBinding;

import java.util.Objects;

public class WorkoutDetails extends AppCompatActivity {

    public static final String WORKOUT_ID = "workoutId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WorkoutDetailsBinding workoutDetailsBinding = WorkoutDetailsBinding.inflate(getLayoutInflater());
        setContentView(workoutDetailsBinding.getRoot());

        int workoutId = (int) Objects.requireNonNull(getIntent().getExtras()).get(WORKOUT_ID);
        Log.i("WorkoutDetails", workoutId + "");

        WorkoutDetailsFragment fragment = (WorkoutDetailsFragment) getSupportFragmentManager()
                .findFragmentById(R.id.workout_details_fragment);

        assert fragment != null;
        fragment.setWorkoutId(workoutId);
    }
}