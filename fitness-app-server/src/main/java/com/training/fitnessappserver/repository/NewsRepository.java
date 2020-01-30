package com.training.fitnessappserver.repository;

import com.training.fitnessappserver.entity.motivation.News;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NewsRepository extends MongoRepository<News, String> {

}
