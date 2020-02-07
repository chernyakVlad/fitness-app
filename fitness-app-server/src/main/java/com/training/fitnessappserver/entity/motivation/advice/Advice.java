package com.training.fitnessappserver.entity.motivation.advice;

import com.training.fitnessappserver.entity.enums.MotivationItemType;
import com.training.fitnessappserver.entity.motivation.MotivationItem;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Advice extends MotivationItem {
    private String adviceText;

    public Advice(String adviceText) {
        this.adviceText = adviceText;
        this.setMotivationItemType(MotivationItemType.ADVICE);
    }

    public Advice(String tag, String adviceText) {
        super(tag);
        this.adviceText = adviceText;
        this.setMotivationItemType(MotivationItemType.ADVICE);
    }
}
