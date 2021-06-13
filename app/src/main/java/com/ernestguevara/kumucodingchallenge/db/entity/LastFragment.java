package com.ernestguevara.kumucodingchallenge.db.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Model for fragments
 * represents each row/column
 */

@Entity(tableName = "fragment_table")
public class LastFragment {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "fragment_id")
    private int id;

    @ColumnInfo(name = "fragment_name")
    private String fragmentName;

    @ColumnInfo(name = "item_id")
    private int item_id;

    public LastFragment() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFragmentName() {
        return fragmentName;
    }

    public void setFragmentName(String fragmentName) {
        this.fragmentName = fragmentName;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }
}
