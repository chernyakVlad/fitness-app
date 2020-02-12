package com.training.fitnessappserver.entity.motivation.news;

import com.training.fitnessappserver.entity.enums.ItemType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NewsItem {
    private ItemType itemType;
    private String item;

}
