package com.adeleke.samad.gadsleaderboard.fragments;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.adeleke.samad.gadsleaderboard.R;
import com.adeleke.samad.gadsleaderboard.models.Learner;

import java.util.List;

public class LearnersAdapter extends RecyclerView.Adapter<LearnersAdapter.LearnersViewHolder> {
    private List<Learner> learners;

    public LearnersAdapter(List<Learner> learners) {
        this.learners = learners;
    }

    @NonNull
    @Override
    public LearnersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.learner_item, parent, false);

        return new LearnersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LearnersViewHolder holder, int position) {
        Learner learner = learners.get(position);
        holder.bind(learner);
    }

    @Override
    public int getItemCount() {
        return learners.size();
    }

    public class LearnersViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvDetail;

        public LearnersViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_learner_name);
            tvDetail = itemView.findViewById(R.id.tv_learner_detail);
        }

        public void bind(Learner learner) {
            tvName.setText(learner.getName());
            String detail = Integer.toString(learner.getHours())
                    + " "
                    + itemView.getContext().getText(R.string.learning_hours)
                    + ", "
                    + learner.getCountry();
            tvDetail.setText(detail);
        }

    }
}
