package com.ernestguevara.kumucodingchallenge.db.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Model for user profile
 * represents each row/column
 */

@Entity(tableName = "profile_table")
public class UserProfile {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "user_id")
    private int userId;

    @ColumnInfo(name = "enable_explicit")
    private boolean enableExplicit;

    @ColumnInfo(name = "last_visit")
    private String lastVisit;

    @ColumnInfo(name = "country_code")
    private String countryCode;


    public UserProfile() {

    }

    @NonNull
    public int getUserId() {
        return userId;
    }

    public void setUserId(@NonNull int userId) {
        this.userId = userId;
    }

    public boolean isEnableExplicit() {
        return enableExplicit;
    }

    public void setEnableExplicit(boolean enableExplicit) {
        this.enableExplicit = enableExplicit;
    }

    public String getLastVisit() {
        return lastVisit;
    }

    public void setLastVisit(String lastVisit) {
        this.lastVisit = lastVisit;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

}
