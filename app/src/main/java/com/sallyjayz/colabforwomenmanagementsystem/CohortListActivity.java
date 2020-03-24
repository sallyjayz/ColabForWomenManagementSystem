package com.sallyjayz.colabforwomenmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sallyjayz.colabforwomenmanagementsystem.adapter.CohortAdapter;
import com.sallyjayz.colabforwomenmanagementsystem.model.Cohort;


public class CohortListActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private Cohort mCohort;
    private RecyclerView mRecyclerView;
    private CohortAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        mDatabase = FirebaseDatabase.getInstance().getReference("cohort");
//
//        mRecyclerView = findViewById(R.id.cohortRecyclerView);
//
//        initRecyclerView();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CohortListActivity.this, AddCohortActivity.class);
                startActivity(intent);

            }
        });

    }


    private void initRecyclerView() {
        mAdapter = new CohortAdapter();
        mRecyclerView.setAdapter(mAdapter);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(mRecyclerView);
    }

    @Override
    protected void onResume() {
        super.onResume();

        mDatabase = FirebaseDatabase.getInstance().getReference("cohort");
        mRecyclerView = findViewById(R.id.cohortRecyclerView);
        initRecyclerView();

    }

    ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

            int position = viewHolder.getAdapterPosition();
            Cohort deleteCohort = mAdapter.getCohortPosition(position);
            mAdapter.deleteItem(position);
            mDatabase.child(deleteCohort.getId()).removeValue();

        }
    };

   /* @Override
    public void onCohortClick(int position) {
        Intent intent = new Intent(this, AddCohort.class);
        intent.putExtra("selected_cohort", mCohorts.get(position));
        startActivity(intent);
    }*/
}
