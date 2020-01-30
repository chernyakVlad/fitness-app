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
    String newsId;
    String text;
    String description;
    double timing;
    double score;
    List<String> picturesUrl;

    public List<String> getPicturesUrl() {
        if(picturesUrl==null)
            picturesUrl=new ArrayList<>();
        return  picturesUrl;
    }

    public void setPicturesUrl(List<String> picturesUrl) {
        this.picturesUrl = picturesUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        News news = (News) o;
        return Double.compare(news.timing, timing) == 0 &&
                Double.compare(news.score, score) == 0 &&
                Objects.equals(newsId, news.newsId) &&
                Objects.equals(text, news.text) &&
                Objects.equals(description, news.description) &&
                Objects.equals(picturesUrl, news.picturesUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(newsId, text, description, timing, score, picturesUrl);
    }
}
