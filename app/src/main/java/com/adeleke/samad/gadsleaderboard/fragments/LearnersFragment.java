package com.adeleke.samad.gadsleaderboard.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.adeleke.samad.gadsleaderboard.R;
import com.adeleke.samad.gadsleaderboard.models.Learner;
import com.adeleke.samad.gadsleaderboard.network.ApiService;
import com.adeleke.samad.gadsleaderboard.network.RetrofitClient;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LearnersFragment extends Fragment {
    private final String TAG = getClass().getSimpleName();
    private RecyclerView rvLearners;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_learners, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ProgressBar progressBar = view.findViewById(R.id.pb_learners);
        progressBar.setVisibility(View.VISIBLE);

        rvLearners = view.findViewById(R.id.rv_learners);
        ApiService service = RetrofitClient.getRetrofitListInstance().create(ApiService.class);
        Call<List<Learner>> call = service.getAllLearners();
        call.enqueue(new Callback<List<Learner>>() {
            @Override
            public void onResponse(Call<List<Learner>> call, Response<List<Learner>> response) {
                Log.e(TAG + " - recyclerView", "Successful");
                displayRecyclerView(response.body());
                progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<List<Learner>> call, Throwable t) {
                Log.e(TAG + " - recyclerView", t.toString());
                Snackbar.make(view, getText(R.string.error_message), Snackbar.LENGTH_SHORT).show();
            }
        });

        super.onViewCreated(view, savedInstanceState);
    }

    private void displayRecyclerView(List<Learner> learners) {
        LearnersAdapter learnersAdapter = new LearnersAdapter(learners);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvLearners.setLayoutManager(layoutManager);
        rvLearners.setAdapter(learnersAdapter);
    }
}
