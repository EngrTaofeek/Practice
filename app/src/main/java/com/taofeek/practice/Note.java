package com.taofeek.practice;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity( tableName = "note_table")
public class Note {
    @PrimaryKey (autoGenerate = true)
    private int id;

    private String title,description;
    private int priority;

    public void setId(int id) {
        this.id = id;
    }

    public Note(String title, String description, int priority) {
        this.title = title;
        this.description = description;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }
}
