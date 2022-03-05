package com.foorun.unieat.service.restaurant;


import com.foorun.unieat.domain.member.dto.MemberLocation;
import com.foorun.unieat.domain.member.dto.MemberUserDetails;
import com.foorun.unieat.domain.member.jpo.MemberJpo;
import com.foorun.unieat.domain.member.repository.MemberRepository;
import com.foorun.unieat.domain.restaurant.dto.FilteringRestaurant;
import com.foorun.unieat.domain.restaurant.dto.Restaurant;
import com.foorun.unieat.domain.restaurant.dto.RestaurantSimple;
import com.foorun.unieat.domain.restaurant.repository.RestaurantQuerydslRepository;
import com.foorun.unieat.domain.restaurant.repository.RestaurantRepository;
import com.foorun.unieat.domain.search.dto.SearchLogDto;
import com.foorun.unieat.domain.search.jpo.SearchLogJpo;
import com.foorun.unieat.domain.search.respository.SearchLogRepository;
import com.foorun.unieat.exception.UniEatNotFoundException;
import com.foorun.unieat.util.JwtProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.foorun.unieat.constant.ServiceConstant.NEAR_BY;

@Service
@RequiredArgsConstructor
@Slf4j
public class RestaurantService   {

    private final RestaurantQuerydslRepository restaurantQuerydslRepository;
    private final RestaurantRepository restaurantRepository;
    private final SearchLogRepository searchLogRepository;
    private final MemberRepository memberRepository;

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
    public List<RestaurantSimple> fetchBySearching(String searchText,PageRequest pageRequest){
        saveSearchText(searchText);
        return restaurantQuerydslRepository.findBySearch(searchText,pageRequest)
                .stream()
                .map(RestaurantSimple::of)
                .collect(Collectors.toList());

    }

    //검색 로그 저장
    @Transactional
    public void saveSearchText(String searchText){
        try {
            MemberUserDetails userDetails = (MemberUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            SearchLogJpo searchLogJpo  = SearchLogDto.builder().searchText(searchText).build().asJpo();
            memberRepository.findByEmail(userDetails.getEmail()).ifPresent(
                    user->{
                            searchLogJpo.setMemberJpo(user);
                            searchLogRepository.save(searchLogJpo);
                    }
            );
        }
        catch (RuntimeException e){
            log.info("인증되지 않은 유저.");
            throw new RuntimeException();
        }




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
