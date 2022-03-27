package com.foorun.unieat.domain.file.jpo;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseFileJpo {
    /**
     * 대표 이미지 여부
     */
    private boolean thumbnail;

    /**
     * 정렬 순서
     */
    private int sequence;

    @Id
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "file_id")
    private FileJpo file;
}
