package com.training.fitnessappserver.entity.motivation;

import com.training.fitnessappserver.entity.enums.ItemType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsItem {
    private ItemType itemType;
    private String item;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewsItem newsItem = (NewsItem) o;
        return itemType == newsItem.itemType &&
                Objects.equals(item, newsItem.item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemType, item);
    }

}
