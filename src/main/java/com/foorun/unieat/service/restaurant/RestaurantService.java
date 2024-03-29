
package com.foorun.unieat.service.restaurant;

import com.foorun.unieat.domain.bookmark.repository.BookmarkQuerydslRepository;
import com.foorun.unieat.domain.bookmark.repository.BookmarkRepository;
import com.foorun.unieat.domain.event.EventQuerydslRepository;
import com.foorun.unieat.domain.event.EventRespository;
import com.foorun.unieat.domain.event.dto.Event;
import com.foorun.unieat.domain.hashtag.repository.HashTagRestaurantQuerydslRepository;
import com.foorun.unieat.domain.member.dto.MemberLocation;
import com.foorun.unieat.domain.member.dto.MemberUserDetails;
import com.foorun.unieat.domain.member.jpo.MemberJpo;
import com.foorun.unieat.domain.member.repository.MemberRepository;
import com.foorun.unieat.domain.restaurant.dto.FilteringRestaurant;
import com.foorun.unieat.domain.restaurant.dto.Restaurant;
import com.foorun.unieat.domain.restaurant.dto.RestaurantSimple;
import com.foorun.unieat.domain.restaurant.repository.RestaurantMapper;
import com.foorun.unieat.domain.restaurant.repository.RestaurantQuerydslRepository;
import com.foorun.unieat.domain.restaurant.repository.RestaurantRepository;
import com.foorun.unieat.domain.search.dto.SearchLog;
import com.foorun.unieat.domain.search.jpo.SearchLogJpo;
import com.foorun.unieat.domain.search.respository.SearchLogQueryRepository;
import com.foorun.unieat.domain.search.respository.SearchLogRepository;
import com.foorun.unieat.exception.UniEatBadRequestException;
import com.foorun.unieat.exception.UniEatLogicalException;
import com.foorun.unieat.exception.UniEatNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.foorun.unieat.constant.ServiceConstant.NEAR_BY;
import static com.foorun.unieat.constant.ServiceConstant.PAGING_SIZE;

@Service
@RequiredArgsConstructor
@Slf4j
public class RestaurantService   {

    private final RestaurantQuerydslRepository restaurantQuerydslRepository;
    private final RestaurantMapper restaurantMapper;
    private final SearchLogRepository searchLogRepository;
    private final MemberRepository memberRepository;
    private final HashTagRestaurantQuerydslRepository hashTagRestaurantQuerydslRepositoryRepository;
    private final EventQuerydslRepository eventQuerydslRepository;

    private final BookmarkQuerydslRepository bookmarkQuerydslRepository;




    @Transactional(readOnly = true)
    public List<Restaurant> fetch(){
        //List 형식으로 불러온다
        return restaurantQuerydslRepository.find()
                .stream()
                .map(Restaurant::of)
                .collect(Collectors.toList());

    }

    @Transactional(readOnly = true)
    public List<Restaurant> fetch(PageRequest pageRequest) {
        MemberUserDetails userDetails = (MemberUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(userDetails == null){
            return restaurantQuerydslRepository.findFetchJoin(pageRequest)
                    .stream()
                    .map(Restaurant::of)
                    .collect(Collectors.toList());

        }
        //List 형식으로 불러온다
        return restaurantQuerydslRepository.findFetchJoin(pageRequest)
                .stream()
                .map(Restaurant::of)
                .map(r->addIsLikedValue(r,userDetails))
                .collect(Collectors.toList());

    }

    /**
     * 식당 상세 정보
     * 회원일 경우, 해당 식당 좋아요 여부도 반환해야함
     */
    @Transactional(readOnly = true)
    public Restaurant fetch(Long id){
        MemberUserDetails userDetails = (MemberUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Restaurant restaurant = Restaurant.of(
                restaurantQuerydslRepository.find(id)
                        .orElseThrow(UniEatNotFoundException::new));

        if (userDetails != null) {
            if (bookmarkQuerydslRepository.isBookmarkedByMember(restaurant.getId(), userDetails.getId()) == true) {
                restaurant.setLiked(true);
            } else restaurant.setLiked(false);

        }
        return restaurant;


    }
    //식당 필터 조회
    @Transactional(readOnly = true)
    public List<Restaurant> fetchByFiltering(FilteringRestaurant filteringRestaurant,PageRequest pageRequest){

        MemberUserDetails userDetails = (MemberUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(userDetails == null){
            return restaurantQuerydslRepository.findByFilter(filteringRestaurant,pageRequest)
                    .stream()
                    .map(Restaurant::of)
                    .collect(Collectors.toList());
        }

        else return restaurantQuerydslRepository.findByFilter(filteringRestaurant,pageRequest)
                .stream()
                .map(Restaurant::of)
                .map(r -> addIsLikedValue(r,userDetails))
                .collect(Collectors.toList());

    }

    //식당 검색
    @Transactional
    public List<Restaurant> fetchBySearching(String searchText,PageRequest pageRequest){
        MemberUserDetails userDetails = (MemberUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(userDetails == null){
            return restaurantQuerydslRepository.findBySearch(searchText,pageRequest)
                    .stream()
                    .map(Restaurant::of)
                    .collect(Collectors.toList());
        }


        saveSearchText(searchText);
        return restaurantQuerydslRepository.findBySearch(searchText,pageRequest)
                .stream()
                .map(Restaurant::of)
                .map(r-> addIsLikedValue(r,userDetails))
                .collect(Collectors.toList());

    }

    //검색 로그 저장
    //시큐리티 컨텍스트로부터 인증된 유저 정보 가져와 검색 로그 추가에 사용, 중복 저장 안됨
    @Transactional(propagation = Propagation.NESTED)
    public void saveSearchText(String searchText) {
        try {
            MemberUserDetails userDetails = (MemberUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            MemberJpo member = memberRepository.findByEmail(userDetails.getEmail()).orElseThrow(UniEatNotFoundException::new);

            //검색어 중복 저장 안되도록 함
            if(searchLogRepository.findSearchLogJpoByMemberIdAndSearchText(member.getId(), searchText) != null){
                return;
            }
            SearchLogJpo searchLogJpo = SearchLog.builder().searchText(searchText).build().asJpo();
            searchLogJpo.setMember(member);


            searchLogRepository.save(searchLogJpo);
        } catch (NullPointerException nullPointerException) {
            log.info("멤버 찾을 수 없음 ");
            throw new UniEatNotFoundException();

        }
    }


        /**
         * TODO: 검색어를 유효한 검색어(Search-Hit 성공)과 무효한 검색(No Search-Hit)로 나누고
         *       유효한 검색만을 로그로 남김과 동시에 검색 로그에 저장한다
         *       유효한 검색어에 대한 정의 필요 ..
         */
        //검색 히스토리 조회
        @Transactional(readOnly = true)
        public List<SearchLog> fetchSearchLog (Long memberId){
            return Optional.ofNullable(memberRepository.findById(memberId))
                    .map(member -> searchLogRepository.findSearchLogJpoByMemberId(member.get().getId(), PageRequest.of(0, PAGING_SIZE)))
                    .orElseThrow(UniEatBadRequestException::new)
                    .stream().map(SearchLog::of).collect(Collectors.toList());

        }



        @Transactional
        public void deleteSearchLog(Long memberId,String delText){
                Optional.of(searchLogRepository.deleteSearchLogJpoByMemberId(memberId, delText))
                        .orElseThrow(UniEatLogicalException::new);

        }


    /**
     *      주변 맛집 (1차 배포용으로 어드민 페이지의 탑 50에 등록된 주변 맛집들이 보여진다)
     * @param memberLocation
     * @param memberUserDetails
     * @return RestaurantSimple
     */
        @Transactional(readOnly = true)
        public List<RestaurantSimple> fetchNearest (MemberLocation memberLocation,MemberUserDetails memberUserDetails){
            List<RestaurantSimple> restaurantSimples = restaurantMapper.findNearest(memberLocation.getLatitude(), memberLocation.getLongitude(), NEAR_BY);
            //hashtag 넣기
            return restaurantSimples.stream().map(r -> {
                        r.setHashTags(hashTagRestaurantQuerydslRepositoryRepository.getHashTagContentByRestaurantId(r.getId()));
                        return r;
                    }).map(r -> addIsLikedValue(r, memberUserDetails))
                    .map(r->addIsUniEatSelected(r))
                    .collect(Collectors.toList());

        }


        private RestaurantSimple addIsLikedValue (RestaurantSimple restaurant, MemberUserDetails
        memberUserDetails){
            //좋아요한 식당이라면
            if (bookmarkQuerydslRepository.isBookmarkedByMember(restaurant.getId(), memberUserDetails.getId())) {
                restaurant.setLiked(true);
            } else restaurant.setLiked(false);

            return restaurant;
        }


        private Restaurant addIsLikedValue (Restaurant restaurant, MemberUserDetails
                memberUserDetails){
            //좋아요한 식당이라면
            if (bookmarkQuerydslRepository.isBookmarkedByMember(restaurant.getId(), memberUserDetails.getId())) {
                restaurant.setLiked(true);
            } else restaurant.setLiked(false);

            return restaurant;
        }


    /**
     *  해당 맛집이 유니잇 맛집인지 확인하여 여부를 반환하도록함
     * @param restaurant
     * @return restaurant
     */
        private Restaurant addIsUniEatSelected(Restaurant restaurant){
            if(eventQuerydslRepository.isEventRestaurant(restaurant.getId())){
                restaurant.setIsUniEatSelected(true);
            }else restaurant.setIsUniEatSelected(false);

            return restaurant;
        }

        private RestaurantSimple addIsUniEatSelected(RestaurantSimple restaurantSimple){
            if(eventQuerydslRepository.isEventRestaurant(restaurantSimple.getId())){
                restaurantSimple.setIsUniEatSelected(true);
            }else restaurantSimple.setIsUniEatSelected(false);

            return restaurantSimple;
        }

        //지도에 표시되는 맛집(top 50)
        @Transactional(readOnly = true)
        public List<Restaurant> fetchMap (MemberLocation memberLocation,MemberUserDetails memberUserDetails){
            //TODO: 사용자 위치에 따른 지역별 맛집 탑 50을 보여주도록 해야함
            return restaurantQuerydslRepository.fetchTopRestaurant()
                    .stream()
                    .map(Restaurant::of)
                    .map(r-> addIsLikedValue(r,memberUserDetails))
                    .map(r-> addIsUniEatSelected(r))
                    .collect(Collectors.toList());
        }





}
