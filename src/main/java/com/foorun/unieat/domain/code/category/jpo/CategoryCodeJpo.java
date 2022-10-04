package com.foorun.unieat.domain.code.category.jpo;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Entity
@Table(name = "category_code")
public class CategoryCodeJpo {
    @Id
    @Column(name = "category_id")
    private Long id;
    private String name;
}
