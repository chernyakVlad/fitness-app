package com.training.fitnessappserver.entity.motivation;

import com.training.fitnessappserver.entity.enums.GoalType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Document(collection = "motivation")
public class Motivation {
    @Id
    String motivationId;
    GoalType goalType;
    String userId;
    @DBRef
    List<MotivationItem> motivationItems;

    public Motivation(String userId, GoalType goalType) {
        this.goalType = goalType;
        this.userId = userId;
        this.motivationItems = new ArrayList<>();
    }

    public List<MotivationItem> getMotivationItem() {
        if (this.motivationItems == null) {
            motivationItems = new ArrayList<>();
        }
        return motivationItems;
    }

    public Motivation(GoalType goalType, String userId) {
        this.goalType = goalType;
        this.userId = userId;
        this.motivationItems = new ArrayList<>();
    }
}
