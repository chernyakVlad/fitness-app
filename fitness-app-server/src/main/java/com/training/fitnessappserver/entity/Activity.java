package com.training.fitnessappserver.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Document(collection = "activity")
public class Activity {
    private String userId;
    private String name;
    private String description;
    private boolean isCompleted;
    private LocalDate date;
    private LocalDateTime start;
    private LocalDateTime end;

    public Activity(String name, String description, boolean isCompleted, LocalDate date) {
        this.name = name;
        this.description = description;
        this.isCompleted = isCompleted;
        this.date = date;
    }
}


