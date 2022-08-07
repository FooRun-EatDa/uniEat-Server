package com.foorun.unieat.domain.review;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class StarScoreCount {

    private Map<String,Long> starCount;

    public StarScoreCount(){
        this.starCount = new HashMap<>();
        for (StarScore value : StarScore.values()) {
            this.starCount.put(value.getName(), 0L);

        }
    }

    public void put(String key, Long value){
        this.starCount.put(key,value);
    }

    public Long get(String key){
        return this.starCount.get(key);
    }
}
