package com.training.fitnessappserver.entity.motivation;

import com.training.fitnessappserver.entity.enums.GoalType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Document(collection = "motivation")
public class Motivation {
    String motivationId;
    GoalType goalType;
    String userId;
    @DBRef
    List<MotivationItem> news;

    public Motivation(String userId, GoalType goalType) {
        this.goalType = goalType;
        this.userId = userId;
        this.news = new ArrayList<>();
    }

    public List<MotivationItem> getMotivationItem() {
        if (this.news == null) {
            news = new ArrayList<>();
        }
        return news;
    }
}
