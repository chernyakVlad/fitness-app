package com.training.fitnessappserver.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    private List<Activity> activities;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plan plan = (Plan) o;
        return Objects.equals(planId, plan.planId) &&
                Objects.equals(userId, plan.userId) &&
                Objects.equals(date, plan.date) &&
                Objects.equals(activities, plan.activities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(planId, userId, date, activities);
    }

    public List<Activity> getActivities() {
        if(this.activities==null){
            activities=new ArrayList<>();
        }
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }


}
