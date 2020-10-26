package org.viper75.workout_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import org.viper75.workout_app.databinding.WorkoutItemBinding;

import java.util.Arrays;
import java.util.List;

public class WorkoutListAdapter extends RecyclerView.Adapter<WorkoutListAdapter.WorkoutItemViewHolder> {
    static class WorkoutItemViewHolder extends RecyclerView.ViewHolder {

        private final CardView root;
        private final TextView title;

        public WorkoutItemViewHolder(@NonNull WorkoutItemBinding workoutItemBinding) {
            super(workoutItemBinding.getRoot());
            title = workoutItemBinding.workoutItemTitle;
            root = workoutItemBinding.getRoot();
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int workoutId);
    }

    private LayoutInflater mInflater;
    private OnItemClickListener mOnItemClickListener;

    public WorkoutListAdapter (Context context) {
        mInflater = LayoutInflater.from(context);

        try {
            mOnItemClickListener = (OnItemClickListener) context;
        } catch (ClassCastException ex) {
            throw new ClassCastException(context.getClass().getSimpleName() + " must implement "
                    + OnItemClickListener.class.getSimpleName());
        }
    }

    @NonNull
    @Override
    public WorkoutItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new WorkoutItemViewHolder(WorkoutItemBinding
                .inflate(mInflater, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutItemViewHolder holder, int position) {
        final Workout workout = Workout.WORKOUTS.get(position);

        holder.title.setText(workout.getTitle());
        holder.root.setOnClickListener(v -> mOnItemClickListener.onItemClick(position));
    }

    @Override
    public int getItemCount() {
        return Workout.WORKOUTS.size();
    }
}
