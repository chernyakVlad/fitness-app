package com.training.fitnessappserver.entity;

import com.training.fitnessappserver.entity.enums.GoalType;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "goal")
public class Goal {
    @Id
    private String id;

    private String userId;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private double measureFrom;

    private double measureTo;

    private GoalType type;
}
