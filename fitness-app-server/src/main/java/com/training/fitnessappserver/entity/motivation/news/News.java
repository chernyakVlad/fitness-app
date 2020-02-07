package com.training.fitnessappserver.entity.motivation.news;

import com.training.fitnessappserver.entity.enums.MotivationItemType;
import com.training.fitnessappserver.entity.motivation.MotivationItem;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@NoArgsConstructor
public class News extends MotivationItem {
    private String description;
    private double timeToRead;
    private double score;
    private List<NewsItem> newsItems;

    public News(String description, double timeToRead, double score) {
        this.description = description;
        this.timeToRead = timeToRead;
        this.score = score;
        this.setMotivationItemType(MotivationItemType.NEWS);
    }

    public News(String description, double timeToRead, double score, List<NewsItem> newsItems) {
        this.description = description;
        this.timeToRead = timeToRead;
        this.score = score;
        this.newsItems = newsItems;
        this.setMotivationItemType(MotivationItemType.NEWS);
    }

    public News(String tag, String description, double timeToRead, double score, List<NewsItem> newsItems) {
        super(tag);
        this.description = description;
        this.timeToRead = timeToRead;
        this.score = score;
        this.newsItems = newsItems;
        this.setMotivationItemType(MotivationItemType.NEWS);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        News news = (News) o;
        return Double.compare(news.timeToRead, timeToRead) == 0 &&
                Double.compare(news.score, score) == 0 &&
                Objects.equals(description, news.description) &&
                Objects.equals(newsItems, news.newsItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, timeToRead, score, newsItems);
    }

}
