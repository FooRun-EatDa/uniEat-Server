package com.foorun.unieat.domain.post.jpo;

import com.foorun.unieat.domain.JsonSerializable;
import com.foorun.unieat.domain.file.jpo.FileJpo;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Entity
@Table(name = "post_file")
@IdClass(PostFileIdJpo.class)
public class PostFileJpo implements JsonSerializable {
    @Id
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private PostJpo post;

    @Id
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "file_id")
    private FileJpo file;

    /**
     * 대표 이미지 여부
     */
    private boolean thumbnail;

    /**
     * 정렬 순서
     */
    private int sequence;

    public static PostFileJpo of(PostJpo post, FileJpo file, boolean thumbnail, int sequence) {
        return PostFileJpo.builder()
                .post(post)
                .file(file)
                .thumbnail(thumbnail)
                .sequence(sequence)
                .build();
    }
}
