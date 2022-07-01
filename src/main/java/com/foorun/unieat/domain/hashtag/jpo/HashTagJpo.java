package com.foorun.unieat.domain.hashtag.jpo;

import com.foorun.unieat.domain.BaseTimeJpo;
import lombok.*;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "hashtag")
public class HashTagJpo extends BaseTimeJpo {


    @Id
    @Column(name = "hashtag_id")
    private Long id;

    /**
     * 해시 태그 내용
     */
    private String content;

    @OneToMany(mappedBy = "hashTag")
    private List<HashTagRestaurantJpo> hashTagRestaurants = new ArrayList<>();



}
