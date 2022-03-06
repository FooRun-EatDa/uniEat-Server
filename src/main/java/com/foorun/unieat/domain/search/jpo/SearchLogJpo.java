package com.foorun.unieat.domain.search.jpo;


import com.foorun.unieat.domain.BaseTimeJpo;
import com.foorun.unieat.domain.member.jpo.MemberJpo;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Entity
@Table(name = "search_log")
public class SearchLogJpo extends BaseTimeJpo {

    @Id
    @GeneratedValue
    private Long id;


    private String searchText;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private MemberJpo memberJpo;



}
