package com.training.fitnessappserver.entity.plan;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

@Data
@NoArgsConstructor
@Document(collection = "plan")
public class Plan {
    @Id
    private String planId;
    private String userId;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;
    @DBRef
    private SortedSet<Activity> activities;

    public Plan(String userId, LocalDate date) {
        this.activities = new TreeSet<>();
        this.userId = userId;
        this.date = date;
    }

    public Set<Activity> getActivities() {
        if (this.activities == null) {
            activities = new TreeSet<>();
        }
        return activities;
    }

    public void setActivities(SortedSet<Activity> activities) {
        if (activities == null) {
            this.activities = new TreeSet<>();
        } else {
            this.activities = activities;
        }
    }
}
