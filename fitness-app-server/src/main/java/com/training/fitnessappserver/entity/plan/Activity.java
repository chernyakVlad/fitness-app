package com.training.fitnessappserver.entity.plan;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Data
@Document(collection = "activity")
public class Activity {
    @Id
    private String activityId;
    private String name;
    private String description;
    private boolean isCompleted;
    private LocalDateTime start;
    private LocalDateTime end;
    private long timeToComplete;

    public long getTimeToComplete() {
        if(start!=null&&end!=null) {
            return ChronoUnit.MINUTES.between(start, end);
        }else
            return 0;
    }

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


