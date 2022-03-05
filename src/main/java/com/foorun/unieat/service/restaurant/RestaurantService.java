package com.foorun.unieat.service.restaurant;


import com.foorun.unieat.domain.member.dto.MemberLocation;
import com.foorun.unieat.domain.restaurant.dto.FilteringRestaurant;
import com.foorun.unieat.domain.restaurant.dto.Restaurant;
import com.foorun.unieat.domain.restaurant.dto.RestaurantSimple;
import com.foorun.unieat.domain.restaurant.repository.RestaurantQuerydslRepository;
import com.foorun.unieat.domain.restaurant.repository.RestaurantRepository;
import com.foorun.unieat.exception.UniEatNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.foorun.unieat.constant.ServiceConstant.NEAR_BY;

@Service
@RequiredArgsConstructor
public class RestaurantService   {

    private final RestaurantQuerydslRepository restaurantQuerydslRepository;
    private final RestaurantRepository restaurantRepository;

    @Transactional(readOnly = true)
    public List<RestaurantSimple> fetch(PageRequest pageRequest){
        //List 형식으로 불러온다
        return restaurantQuerydslRepository.findFetchJoin(pageRequest)
                .stream()
                .map(RestaurantSimple::of)
                .collect(Collectors.toList());

    }

    // 식당 상세정보
    @Transactional(readOnly = true)
    public Restaurant fetch(Long id){
        return Restaurant.of(
                restaurantQuerydslRepository.find(id)
                .orElseThrow(UniEatNotFoundException::new)
        );

    }


    //식당 필터 조회
    @Transactional(readOnly = true)
    public List<RestaurantSimple> fetchByFiltering(FilteringRestaurant filteringRestaurant,PageRequest pageRequest){

        return restaurantQuerydslRepository.findByFilter(filteringRestaurant,pageRequest)
                .stream()
                .map(RestaurantSimple::of)
                .collect(Collectors.toList());

    }

    //식당 검색
    @Transactional(readOnly = true)
    public List<RestaurantSimple> fetchBySearching(String keyWord,PageRequest pageRequest){
        return restaurantQuerydslRepository.findBySearch(keyWord,pageRequest)
                .stream()
                .map(RestaurantSimple::of)
                .collect(Collectors.toList());

    }


    //주변 맛집
    @Transactional(readOnly = true)
    public List<RestaurantSimple> fetchNearest(MemberLocation memberLocation){
        return restaurantRepository.findNearest(
                        memberLocation.getLatitude(),
                        memberLocation.getLongitude(),
                        NEAR_BY)
                .stream().map(RestaurantSimple::of)
                .collect(Collectors.toList());

    }




}
