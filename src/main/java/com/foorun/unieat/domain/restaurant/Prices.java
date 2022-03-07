package com.foorun.unieat.domain.restaurant;

import com.foorun.unieat.exception.UniEatNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

import static com.foorun.unieat.constant.ServiceConstant.INFINITY_NUM;

@RequiredArgsConstructor
@Getter
public enum Prices {

    NONE(0,0,"선택하지 않음",0),
    LEVEL_ONE(0,10000,"10,000원이하",1),
    LEVEL_TWO(10000,15000,"10,000원이하",2),
    LEVEL_THREE(15000,20000,"10,000원이하",3),
    LEVEL_FOUR(20000,30000,"10,000원이하",4),
    LEVEL_FIVE(30000,INFINITY_NUM,"10,000원이하",5);


    private final int lowerBound;
    private final int upperBound;
    private final String desc;
    private final int level; //해당 가격 분류 코드


    /**
     *
     * level 정수 값을 가격으로 반환
     *
     */
    public static Prices getPricesByLevel(int level){
        return Arrays.stream(values())
                .filter(e -> e.level == level)
                .findFirst().orElseThrow(UniEatNotFoundException::new);
    }





}
