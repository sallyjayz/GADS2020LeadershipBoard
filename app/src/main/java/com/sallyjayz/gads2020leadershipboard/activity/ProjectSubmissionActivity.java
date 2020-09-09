package com.sallyjayz.gads2020leadershipboard.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sallyjayz.gads2020leadershipboard.R;
import com.sallyjayz.gads2020leadershipboard.client.SubmissionClient;
import com.sallyjayz.gads2020leadershipboard.api.SubmitAPI;
import com.sallyjayz.gads2020leadershipboard.dialog.ConfirmDialogFragment;
import com.sallyjayz.gads2020leadershipboard.dialog.ConfirmDialogListener;
import com.sallyjayz.gads2020leadershipboard.dialog.SuccessDialogFragment;
import com.sallyjayz.gads2020leadershipboard.dialog.UnsuccessfulDialogFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProjectSubmissionActivity extends AppCompatActivity
        implements View.OnClickListener, ConfirmDialogListener {

    private Toolbar mToolbar;
    private EditText mFirstName, mLastName, mEmailAddress, mGithubLink;
    private Button mSubmitButton;
    private SubmitAPI mSubmitAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_submission);

        mToolbar = findViewById(R.id.toolbar);
        mFirstName = findViewById(R.id.first_name);
        mLastName = findViewById(R.id.last_name);
        mEmailAddress = findViewById(R.id.email_address);
        mGithubLink = findViewById(R.id.github_link);
        mSubmitButton = findViewById(R.id.submit);

        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("");
        }

        /*setSupportActionBar(mToolbar);
        ActionBar actionBar = this.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("");
        }*//*

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });*/

        mSubmitButton.setOnClickListener(this);
        mSubmitAPI = SubmissionClient.getClient().create(SubmitAPI.class);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.submit) {
//            submitProject();
            showConfirmDialog();
        }

    }

    private void showConfirmDialog() {
        ConfirmDialogFragment confirmFragment = new ConfirmDialogFragment();
        confirmFragment.show(getSupportFragmentManager(), "confirmation dialog");
    }

    private void showSuccessDialog() {
        SuccessDialogFragment successFragment = new SuccessDialogFragment();
        successFragment.show(getSupportFragmentManager(), "success dialog");
    }

    private void showUnsuccessfulDialog() {
        UnsuccessfulDialogFragment unsuccessfulFragment = new UnsuccessfulDialogFragment();
        unsuccessfulFragment.show(getSupportFragmentManager(), "unsuccessful dialog");
    }

    @Override
    public void submitProject() {
        String firstName = mFirstName.getText().toString().trim();
        String lastName = mLastName.getText().toString().trim();
        String email = mEmailAddress.getText().toString().trim();
        String gitLink = mGithubLink.getText().toString().trim();

        createSubmission(firstName, lastName, email, gitLink);
    }

    private void createSubmission(String firstName, String lastName, String email, String gitLink) {

        Call<Void> callSubmit = mSubmitAPI
                .createSubmitProject(firstName, lastName, email, gitLink);

        callSubmit.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()) {
                    showUnsuccessfulDialog();
                    return;
                }

                clearFields();
                showSuccessDialog();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                showUnsuccessfulDialog();
            }
        });

    }

    public void clearFields() {
        mFirstName.setText("");
        mLastName.setText("");
        mEmailAddress.setText("");
        mGithubLink.setText("");
    }
}