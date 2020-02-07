package com.training.fitnessappserver.entity.motivation;

import com.training.fitnessappserver.entity.motivation.news.NewsItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Document(collection = "motivation_item")
public class MotivationItem {
    @Id
    private String motivationItemId;
    private String description;

    private List<NewsItem> newsItems;
    private String tag;
    private double timeToRead;
    private double score;

    public List<NewsItem> getNewsItems() {
        if (newsItems == null) {
            this.newsItems = new ArrayList<>();
        }
        return newsItems;
    }


}
