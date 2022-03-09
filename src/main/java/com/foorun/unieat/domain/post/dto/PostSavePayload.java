package com.foorun.unieat.domain.post.dto;

import com.foorun.unieat.domain.JsonSerializable;
import com.foorun.unieat.domain.Validatable;
import com.foorun.unieat.domain.common.PostType;
import com.foorun.unieat.domain.file.dto.FileDetail;
import com.foorun.unieat.domain.post.jpo.PostFileJpo;
import com.foorun.unieat.domain.post.jpo.PostJpo;
import com.foorun.unieat.exception.UniEatBadRequestException;
import com.foorun.unieat.util.IdentifyGenerator;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.springframework.util.StringUtils.hasText;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class PostSavePayload implements Validatable, JsonSerializable {
    @Builder.Default
    @ApiModelProperty(hidden = true)
    private Long id = IdentifyGenerator.number();

    @ApiModelProperty(notes = "게시글 제목", example = "번개 오마카세 모집 합니다!!", required = true, position = 0)
    private String title;

    @ApiModelProperty(notes = "게시글 카테고리", example = "lightning", required = true, position = 1)
    private PostType category;

    @ApiModelProperty(notes = "게시글 내용", example = "불금 저녁 오마카세 가실분 모집합니다. 연락주세용", position = 2)
    private String content;

    @ApiModelProperty(notes = "대표 이미지 ID", example = "5073596e-ac44-46d5-9a89-c29610df4ebd", position = 3)
    private String thumbnail;

    @ApiModelProperty(notes = "파일 목록", position = 4)
    private List<FileDetail> files;

    public PostJpo asJpo() {
        PostJpo postJpo = new PostJpo();
        BeanUtils.copyProperties(this, postJpo);
        return postJpo;
    }

    private List<PostFileJpo> streamAsList(PostJpo postJpo, Stream<FileDetail> stream) {
        return stream
                .map(fileDetail -> PostFileJpo.of(
                        postJpo, fileDetail.asJpo(), fileDetail.isThumbnail(), fileDetail.getSequence()))
                .collect(Collectors.toList());
    }

    public List<PostFileJpo> filterRemoveFilesAsJpo(PostJpo postJpo) {
        return streamAsList(postJpo, files.stream()
                .filter(FileDetail::isDelete));
    }

    public List<PostFileJpo> filterNewFilesAsJpo(PostJpo postJpo) {
        return streamAsList(postJpo, files.stream()
                .filter(FileDetail::isNew));
    }

    @Override
    public void validate() {
        if (!hasText(title) || category == null) {
            throw new UniEatBadRequestException();
        }
    }
}
