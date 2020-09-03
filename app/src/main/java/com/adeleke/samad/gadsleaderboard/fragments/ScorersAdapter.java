package com.adeleke.samad.gadsleaderboard.fragments;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.adeleke.samad.gadsleaderboard.R;
import com.adeleke.samad.gadsleaderboard.models.Scorer;

import java.util.List;

public class ScorersAdapter extends RecyclerView.Adapter<ScorersAdapter.ScorersViewHolder> {
    private List<Scorer> scorers;

    public ScorersAdapter(List<Scorer> scorers) {
        this.scorers = scorers;
    }

    @NonNull
    @Override
    public ScorersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.scorer_item, parent, false);
        return new ScorersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScorersViewHolder holder, int position) {
        Scorer scorer = scorers.get(position);
        holder.bind(scorer);
    }

    @Override
    public int getItemCount() {
        return scorers.size();
    }

    public class ScorersViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvDetail;

        public ScorersViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_scorer_name);
            tvDetail = itemView.findViewById(R.id.tv_scorer_detail);
        }

        public void bind(Scorer scorer) {
            tvName.setText(scorer.getName());
            String detail = Integer.toString(scorer.getScore())
                    + " "
                    + itemView.getContext().getText(R.string.learning_hours)
                    + ", "
                    + scorer.getCountry();
            tvDetail.setText(detail);
        }

    }
}
