package com.training.fitnessappserver.entity.plan;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
@Document(collection = "activity")
public class Activity  {
    @Id
    private String activityId;
    private String planId;
    private String name;
    private String description;
    private boolean isCompleted;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalDateTime start;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalDateTime end;

    public Activity() {
    }

    public Activity(String name, String description, boolean isCompleted, LocalDateTime start, LocalDateTime end) {

        this.name = name;
        this.description = description;
        this.isCompleted = isCompleted;
        this.start = start;
        this.end = end;
    }
    public Activity(String name, String description, boolean isCompleted) {

        this.name = name;
        this.description = description;
        this.isCompleted = isCompleted;
    }


}


