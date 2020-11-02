package org.viper75.workout_app;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.viper75.workout_app.databinding.WorkoutDetailsFragmentBinding;

public class WorkoutDetailsFragment extends Fragment {

    private static final String SAVED_WORKOUT_ID = "savedWorkoutId";

    private WorkoutDetailsFragmentBinding workoutDetailsFragmentBinding;
    private int workoutId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            StopwatchFragment fragment = new StopwatchFragment();
            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
            transaction.replace(R.id.stopwatch_fragment, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        } else {
            workoutId = savedInstanceState.getInt(SAVED_WORKOUT_ID);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        workoutDetailsFragmentBinding = WorkoutDetailsFragmentBinding
                .inflate(inflater, container, false);

        return workoutDetailsFragmentBinding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();

        Workout workout = Workout.WORKOUTS.get(workoutId);

        workoutDetailsFragmentBinding.workoutTitle.setText(workout.getTitle());
        workoutDetailsFragmentBinding.workoutDescription.setText(workout.getDescription());
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(SAVED_WORKOUT_ID, workoutId);
    }

    public void setWorkoutId(int workoutId) {
        this.workoutId = workoutId;
    }
}