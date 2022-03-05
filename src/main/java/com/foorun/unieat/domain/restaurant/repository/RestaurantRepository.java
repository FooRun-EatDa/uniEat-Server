package com.foorun.unieat.domain.restaurant.repository;

import com.foorun.unieat.domain.restaurant.jpo.RestaurantJpo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<RestaurantJpo,Long> {



    /**
     * 주변 맛집 찾기
     * nearby : 주변 반경 몇 킬로미터까지 조회 할지
     */
    @Query(
            "SELECT R FROM RestaurantJpo R " +
            "WHERE (6371 * acos( cos(radians(:source_lat) )" +
            "* cos(radians(R.latitude))" +
            "* cos(radians(R.longitude) - radians(:source_long) )" +
            "+ sin(radians(:source_lat) )" +
            "* sin(radians(R.latitude)) ) )  < :NEAR_BY "
    )
    public List<RestaurantJpo> findNearest(@Param("source_lat")float latitude,
                                           @Param("source_lon")float longitude,
                                           @Param("NEAR_BY")int nearby);
}
