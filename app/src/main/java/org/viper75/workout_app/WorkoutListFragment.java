package org.viper75.workout_app;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.viper75.workout_app.databinding.WorkoutListFragmentBinding;

public class WorkoutListFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        WorkoutListFragmentBinding workoutListFragmentBinding = WorkoutListFragmentBinding
                .inflate(inflater, container, false);

        WorkoutListAdapter workoutListAdapter = new WorkoutListAdapter(getActivity());

        workoutListFragmentBinding.workoutListRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        workoutListFragmentBinding.workoutListRv.setAdapter(workoutListAdapter);
        return workoutListFragmentBinding.getRoot();
    }
}