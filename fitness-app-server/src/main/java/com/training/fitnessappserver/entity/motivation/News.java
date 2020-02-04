package com.training.fitnessappserver.entity.motivation;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@Document(collection = "news")
public class News {
    private String newsId;
    private String tag;
    private String description;
    private double timeToRead;
    private double score;
    private List<NewsItem> newsItems;

    public List<NewsItem> getPicturesUrl() {
        if(newsItems==null)
            newsItems=new ArrayList<>();
        return  newsItems;
    }

    public void setPicturesUrl(List<NewsItem> picturesUrl) {
        this.newsItems = picturesUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        News news = (News) o;
        return Double.compare(news.timeToRead, timeToRead) == 0 &&
                Double.compare(news.score, score) == 0 &&
                Objects.equals(newsId, news.newsId) &&
                Objects.equals(tag, news.tag) &&
                Objects.equals(description, news.description) &&
                Objects.equals(newsItems, news.newsItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(newsId, tag, description, timeToRead, score, newsItems);
    }
}
