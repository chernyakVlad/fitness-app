package com.training.fitnessappserver.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
@Document(collection = "activity")
public class Activity {
    @Id
    private String activityId;
    private String planId;
    private String name;
    private String description;
    private boolean isCompleted;
    private LocalDateTime start;
    private LocalDateTime end;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Activity activity = (Activity) o;
        return isCompleted == activity.isCompleted &&
                Objects.equals(activityId, activity.activityId) &&
                Objects.equals(planId, activity.planId) &&
                Objects.equals(name, activity.name) &&
                Objects.equals(description, activity.description) &&
                Objects.equals(start, activity.start) &&
                Objects.equals(end, activity.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(activityId, planId, name, description, isCompleted, start, end);
    }

    public Activity(String name, String description, boolean isCompleted, LocalDateTime start, LocalDateTime end) {

        this.name = name;
        this.description = description;
        this.isCompleted = isCompleted;
        this.start = start;
        this.end = end;
    }
}


