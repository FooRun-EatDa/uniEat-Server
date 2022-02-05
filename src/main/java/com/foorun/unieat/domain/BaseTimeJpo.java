package com.foorun.unieat.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeJpo {
    /**
     * 작성일시
     */
    @CreatedDate
    private LocalDateTime createdAt;

    /**
     * 수정일시
     */
    @LastModifiedDate
    private LocalDateTime updatedAt;
}
