package com.training.fitnessappserver.entity.plan;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

@Data
@Document(collection = "activity")
public class Activity implements Comparable<Activity> {


    @Id
    private String activityId;
    private String name;
    private String description;
    private boolean completed;
    private LocalTime start;
    private LocalTime end;
    private long timeToComplete;

    public Activity() {
    }

    public Activity(String name, String description, boolean completed, LocalTime start, LocalTime end) {

        this.name = name;
        this.description = description;
        this.completed = completed;
        this.start = start;
        this.end = end;
    }

    public Activity(String name, String description, boolean completed) {

        this.name = name;
        this.description = description;
        this.completed = completed;
    }

    public long getTimeToComplete() {
        if (start != null && end != null) {
            return ChronoUnit.MINUTES.between(start, end);
        } else
            return 0;
    }

    @Override
    public int compareTo(Activity o) {
        return this.start.compareTo(o.start);
    }

}


