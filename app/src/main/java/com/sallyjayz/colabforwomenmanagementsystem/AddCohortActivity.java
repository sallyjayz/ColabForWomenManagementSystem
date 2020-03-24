package com.sallyjayz.colabforwomenmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sallyjayz.colabforwomenmanagementsystem.model.Cohort;

public class AddCohortActivity extends AppCompatActivity {

    private static final String TAG = CohortListActivity.class.getSimpleName();

    private DatabaseReference mDatabase;
    private Cohort mCohort;
    private TextInputEditText cohortEditText;
    private TextInputEditText startEditText;
    private TextInputEditText endEditText;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cohort);

        mDatabase = FirebaseDatabase.getInstance().getReference("cohort");

        cohortEditText = findViewById(R.id.cohort1);
        startEditText = findViewById(R.id.startDate1);
        endEditText = findViewById(R.id.endDate1);
        saveButton = findViewById(R.id.saveButton);

        getIncoming();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mCohort.setCohort(cohortEditText.getText().toString());
                mCohort.setStartDate(startEditText.getText().toString());
                mCohort.setEndDate(startEditText.getText().toString());

                if (mCohort.getId() == null) {
                    createCohort();
                    clean();
                    backToMain();
                } else {
                    updateCohort();
                    backToMain();
                }
            }
        });

        toggleButton();
    }

    private void backToMain() {
        Intent intent = new Intent(AddCohortActivity.this, CohortListActivity.class);
        startActivity(intent);
    }

    private void createCohort() {
        mDatabase.push().setValue(mCohort);
    }

    private void updateCohort() {
        mDatabase.child(mCohort.getId()).setValue(mCohort);
    }

    private void deleteCohort() {
        mDatabase.child(mCohort.getId()).removeValue();
    }

    private void toggleButton() {
        if (mCohort.getId()==null) {
            saveButton.setText(R.string.save);
        } else {
            saveButton.setText(R.string.update);
        }
    }

    private void getIncoming() {
        Intent intent = getIntent();
        Cohort  mCohortInitialSave = intent.getParcelableExtra("selected_cohort");
        if (mCohortInitialSave == null) {
            mCohortInitialSave = new Cohort();
        }

        this.mCohort = mCohortInitialSave;
        cohortEditText.setText(mCohort.getCohort());
        startEditText.setText(mCohort.getStartDate());
        endEditText.setText(mCohort.getEndDate());
    }

    private void clean() {
        cohortEditText.setText("");
        startEditText.setText("");
        endEditText.setText("");
    }
}
