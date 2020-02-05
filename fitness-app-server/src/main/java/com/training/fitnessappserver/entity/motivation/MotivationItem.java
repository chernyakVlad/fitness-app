package com.training.fitnessappserver.entity.motivation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "motivation_item")
public class MotivationItem {
    @Id
    private String motivationItemId;
    private String tag;
}
