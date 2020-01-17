package com.training.fitnessappserver.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Document(collection = "day_planner")
public class DayPlanner {
    @Id
    private String id;
    private String userId;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;
    @DBRef
    private List<Activity> dayActivities= new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DayPlanner that = (DayPlanner) o;
        return Objects.equals(date, that.date) &&
                Objects.equals(dayActivities, that.dayActivities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, dayActivities);
    }
}
