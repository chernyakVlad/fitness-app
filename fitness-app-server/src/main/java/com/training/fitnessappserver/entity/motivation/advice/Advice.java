package com.training.fitnessappserver.entity.motivation.advice;

import com.training.fitnessappserver.entity.motivation.MotivationItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Advice extends MotivationItem {
    private String adviceText;
}
