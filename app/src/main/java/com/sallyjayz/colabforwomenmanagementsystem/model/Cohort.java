package com.sallyjayz.colabforwomenmanagementsystem.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Cohort implements Parcelable {
    private String id;
    private String cohort;
    private String startDate;
    private String endDate;

    public Cohort() {

    }

    public Cohort(String cohort, String startDate, String endDate) {
        this.setId(id);
        this.setCohort(cohort);
        this.setStartDate(startDate);
        this.setEndDate(endDate);
    }

    protected Cohort(Parcel in) {
        id = in.readString();
        cohort = in.readString();
        startDate = in.readString();
        endDate = in.readString();
    }

    public static final Creator<Cohort> CREATOR = new Creator<Cohort>() {
        @Override
        public Cohort createFromParcel(Parcel in) {
            return new Cohort(in);
        }

        @Override
        public Cohort[] newArray(int size) {
            return new Cohort[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCohort() {
        return cohort;
    }

    public void setCohort(String cohort) {
        this.cohort = cohort;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Cohort{" +
                "id='" + id + '\'' +
                ", cohort='" + cohort + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(cohort);
        dest.writeString(startDate);
        dest.writeString(endDate);
    }
}
