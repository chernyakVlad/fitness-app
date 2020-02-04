package com.training.fitnessappserver.entity.motivation;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@Document(collection = "motivation")
public class Motivation {
    String motivationId;
    String goalId;
    String userId;
    @DBRef
    List<News> news;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Motivation that = (Motivation) o;
        return Objects.equals(motivationId, that.motivationId) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(news, that.news);
    }

    @Override
    public int hashCode() {
        return Objects.hash(motivationId, userId, news);
    }

    public List<News> getNews() {
        if (this.news == null) {
            news = new ArrayList<>();
        }
        return news;
    }

    public void setNews(List<News> news) {
        this.news = news;
    }
}
