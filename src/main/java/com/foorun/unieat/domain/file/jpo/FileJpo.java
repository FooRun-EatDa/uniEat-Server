package com.foorun.unieat.domain.file.jpo;

import com.foorun.unieat.domain.JsonSerializable;
import com.foorun.unieat.domain.common.StatusType;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Entity
@Table(name = "file")
@EntityListeners(AuditingEntityListener.class)
public class FileJpo implements JsonSerializable, Persistable<String> {
    @Id
    @Column(name = "file_id")
    private String id;
    private String name;
    private String format;
    private String path;
    private Integer width;
    private Integer height;
    private long bytes;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private StatusType status = StatusType.ACTIVE;

    @CreatedDate
    private LocalDateTime createdAt;

    @Override
    public boolean isNew() {
        return true;
    }
}
