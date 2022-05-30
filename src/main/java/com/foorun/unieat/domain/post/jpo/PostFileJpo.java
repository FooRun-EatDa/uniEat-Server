package com.foorun.unieat.domain.post.jpo;

import com.foorun.unieat.domain.JsonSerializable;
import com.foorun.unieat.domain.file.jpo.BaseFileJpo;
import com.foorun.unieat.domain.file.jpo.FileJpo;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "post_file")
@IdClass(PostFileIdJpo.class)
public class PostFileJpo extends BaseFileJpo implements JsonSerializable {
    @Id
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private PostJpo post;

    @Builder
    protected PostFileJpo(boolean thumbnail, int sequence, PostJpo post, FileJpo file) {
        super(thumbnail, sequence, file);
        this.post = post;
    }

    public static PostFileJpo of(PostJpo post, FileJpo file, boolean thumbnail, int sequence) {
        return PostFileJpo.builder()
                .post(post)
                .file(file)
                .thumbnail(thumbnail)
                .sequence(sequence)
                .build();
    }
}
