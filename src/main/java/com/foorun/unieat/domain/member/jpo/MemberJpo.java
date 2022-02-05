package com.foorun.unieat.domain.member.jpo;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Entity
@Table(name = "member")
public class MemberJpo {
    @Id
    @Column(name = "member_id")
    private Long id;
    private String name;
}
