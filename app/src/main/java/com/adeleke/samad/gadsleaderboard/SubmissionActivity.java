package com.adeleke.samad.gadsleaderboard;

import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.adeleke.samad.gadsleaderboard.models.Submission;
import com.adeleke.samad.gadsleaderboard.network.ApiService;
import com.adeleke.samad.gadsleaderboard.network.RetrofitClient;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubmissionActivity extends AppCompatActivity {
    private final String TAG = getClass().getSimpleName();
    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submission);

        Toolbar toolbar = findViewById(R.id.toolbar_submission);
        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();
        assert ab != null;
        ab.setDisplayHomeAsUpEnabled(true);

        submitButton = findViewById(R.id.btn_project_submit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText etFirstName = findViewById(R.id.et_first_name);
                EditText etLastName = findViewById(R.id.et_last_name);
                EditText etEmail = findViewById(R.id.et_email);
                EditText etProjectLink = findViewById(R.id.et_project_link);

                String firstName = etFirstName.getText().toString().trim();
                String lastName = etLastName.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                String projectLink = etProjectLink.getText().toString().trim();

                if (!TextUtils.isEmpty(firstName) && !TextUtils.isEmpty(lastName)
                        && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(projectLink)) {
                    Submission submission = new Submission(firstName, lastName, email, projectLink);
                    showSurePopup(v, submission);

                } else {

                    Snackbar.make(v, getText(R.string.fill_all_fields), Snackbar.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void submitForm(View v, Submission submission) {

        ApiService service = RetrofitClient.getRetrofitSubmitInstance().create(ApiService.class);
        Call<Void> call = service.submitApp(submission.getEmail(),
                submission.getFirstName(),
                submission.getFirstName(),
                submission.getProjectLink());

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Log.i(TAG + " - submission ", "successful");
                    showSuccessfulPopUp(v);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e(TAG + " - submission_error", t.toString());
                showFailedPopUp(v);
            }
        });

    }

    private void showSurePopup(View view, Submission submission) {
        AlertDialog.Builder builder = new AlertDialog.Builder(SubmissionActivity.this);
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(view.getContext()).inflate(R.layout.popup_sure, viewGroup, false);
        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        alertDialog.show();

        MaterialButton btnConfirmSubmit = dialogView.findViewById(R.id.btn_confirm_submit);
        btnConfirmSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitForm(submitButton, submission);
            }
        });

        ImageButton btnCancelSubmit = dialogView.findViewById(R.id.btn_cancel);
        btnCancelSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
    }

    private void showFailedPopUp(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(SubmissionActivity.this);
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(view.getContext()).inflate(R.layout.popup_failed, viewGroup, false);
        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        alertDialog.show();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                alertDialog.dismiss();
            }

        }, 3000);

    }

    private void showSuccessfulPopUp(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(SubmissionActivity.this);
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(view.getContext()).inflate(R.layout.popup_successful, viewGroup, false);
        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        alertDialog.show();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                alertDialog.dismiss();
            }

        }, 3000);
    }


}
