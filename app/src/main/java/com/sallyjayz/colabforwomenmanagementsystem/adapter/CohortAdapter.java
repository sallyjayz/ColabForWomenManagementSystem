package com.sallyjayz.colabforwomenmanagementsystem.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sallyjayz.colabforwomenmanagementsystem.AddCohortActivity;
import com.sallyjayz.colabforwomenmanagementsystem.R;
import com.sallyjayz.colabforwomenmanagementsystem.model.Cohort;

import java.util.ArrayList;
import java.util.List;

public class CohortAdapter extends RecyclerView.Adapter<CohortAdapter.CohortViewHolder> {

    private DatabaseReference mDatabase;
    private List<Cohort> mCohorts;
    private ChildEventListener mChildEventListener;

    public CohortAdapter() {
        mDatabase = FirebaseDatabase.getInstance().getReference("cohort");

        final List<Cohort> mCohort = new ArrayList<>();
        this.mCohorts = mCohort;

        mChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Cohort cohort = dataSnapshot.getValue(Cohort.class);
                cohort.setId(dataSnapshot.getKey());
                mCohorts.add(cohort);
                notifyItemInserted(mCohorts.size()-1);
//                notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        mDatabase.addChildEventListener(mChildEventListener);
    }

    @NonNull
    @Override
    public CohortAdapter.CohortViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cohort_list, parent, false);
        return new CohortViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CohortAdapter.CohortViewHolder holder, int position) {
        Cohort cohort = mCohorts.get(position);

        holder.cohortName.setText(cohort.getCohort());
    }

    public Cohort getCohortPosition(int position) {
        return mCohorts.get(position);
    }

    public void deleteItem(int position){
        mCohorts.remove(position);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mCohorts.size();
    }


    public class CohortViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        MaterialTextView cohortName;
//        ShapeableImageView deleteCohort;

        public CohortViewHolder(@NonNull View itemView) {
            super(itemView);

            cohortName = itemView.findViewById(R.id.cohortName);
//            deleteCohort = itemView.findViewById(R.id.cohortDelete);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Cohort selectedCohort = mCohorts.get(position);
            Intent intent = new Intent(v.getContext(), AddCohortActivity.class);
            intent.putExtra("selected_cohort", selectedCohort);
            v.getContext().startActivity(intent);
        }

    }

}
