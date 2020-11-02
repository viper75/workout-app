package org.viper75.workout_app;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.viper75.workout_app.databinding.StopwatchFragmentBinding;

import java.util.Objects;

public class StopwatchFragment extends Fragment {

    private final String SECONDS_STATE = "seconds";
    private final String RUNNING_STATE = "running";
    private final String WAS_RUNNING_STATE = "wasRunning";

    private int seconds = 0;
    private boolean running;
    private boolean wasRunning;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt(SECONDS_STATE);
            running = savedInstanceState.getBoolean(RUNNING_STATE);
            wasRunning = savedInstanceState.getBoolean(WAS_RUNNING_STATE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        StopwatchFragmentBinding stopwatchFragmentBinding = StopwatchFragmentBinding
                .inflate(inflater, container, false);

        Button start = stopwatchFragmentBinding.startTimer;
        start.setOnClickListener((v) -> startTimer());

        Button stop = stopwatchFragmentBinding.stopTimer;
        stop.setOnClickListener((v) -> stopTimer());

        Button reset = stopwatchFragmentBinding.resetTimer;
        reset.setOnClickListener((v) -> resetTimer());

        runTimer(stopwatchFragmentBinding.timer);

        return stopwatchFragmentBinding.getRoot();
    }

    @Override
    public void onPause() {
        super.onPause();
        wasRunning = running;
        running = false;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (wasRunning) {
            running = true;
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(SECONDS_STATE, seconds);
        outState.putBoolean(RUNNING_STATE, running);
        outState.putBoolean(WAS_RUNNING_STATE, wasRunning);
    }

    private void startTimer() {
        running = true;
    }

    private void stopTimer() {
        running = false;
    }

    private void resetTimer() {
        running = false;
        seconds = 0;
    }

    private void runTimer(TextView timer) {
        final Handler handler = new Handler(Objects.requireNonNull(getActivity()).getMainLooper());

        handler.post(new Runnable() {
            @Override
            public void run() {
                int hrs = seconds / 3600;
                int min = (seconds % 3600) / 60;
                int sec = seconds % 60;

                @SuppressLint("DefaultLocale")
                String time = String.format("%d:%02d:%02d", hrs, min, sec);

                timer.setText(time);

                if (running) {
                    seconds++;
                }

                handler.postDelayed(this, 1000);
            }
        });
    }
}