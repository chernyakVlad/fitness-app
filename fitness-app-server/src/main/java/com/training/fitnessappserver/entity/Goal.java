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

    private String description;

    private String measuring;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private int measureFrom;

    private int measureTo;

    private String measureLabel;

    private GoalType type;
}
