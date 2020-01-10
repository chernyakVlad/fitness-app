package com.training.fitnessappserver.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;

@Document(collection = "exercise")
public class Activity {
    private String name;
    private String description;
    private boolean isCompleted;
    private LocalDate date;

    public Activity(String name, String description, boolean isCompleted, LocalDate date) {
        this.name = name;
        this.description = description;
        this.isCompleted = isCompleted;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
