package org.viper75.workout_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import org.viper75.workout_app.databinding.WorkoutListBinding;

public class WorkoutList extends AppCompatActivity implements WorkoutListAdapter.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WorkoutListBinding workoutListBinding = WorkoutListBinding.inflate(getLayoutInflater());
        setContentView(workoutListBinding.getRoot());
    }

    @Override
    public void onItemClick(int workoutId) {
        Log.i("WorkoutList", workoutId + "");
        View fragmentContainer = findViewById(R.id.fragment_container);

        if (fragmentContainer != null) {
            WorkoutDetailsFragment workoutDetailsFragment = new WorkoutDetailsFragment();
            workoutDetailsFragment.setWorkoutId(workoutId);

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, workoutDetailsFragment);
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            transaction.addToBackStack(null);
            transaction.commit();
        } else {
            Intent intent = new Intent(this, WorkoutDetails.class);
            intent.putExtra(WorkoutDetails.WORKOUT_ID, workoutId);
            startActivity(intent);
        }
    }
}