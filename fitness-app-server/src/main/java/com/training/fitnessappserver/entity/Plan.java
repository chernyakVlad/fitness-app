package com.training.fitnessappserver.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@Document(collection = "plan")
public class Plan {
    @Id
    private String planId;
    private String userId;
    private LocalDate date;
    @DBRef
    private List<Activity> activities;

}
