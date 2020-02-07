package com.training.fitnessappserver.entity.motivation;

import com.training.fitnessappserver.entity.enums.MotivationItemType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "motivation_item")
public class MotivationItem {
    @Id
    private String motivationItemId;
    private String tag;
    private MotivationItemType motivationItemType;

    public MotivationItem(String tag) {
        this.tag = tag;
    }
}
