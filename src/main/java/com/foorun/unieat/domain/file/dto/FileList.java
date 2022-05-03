package com.foorun.unieat.domain.file.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.foorun.unieat.domain.post.jpo.PostFileJpo;
import com.foorun.unieat.util.FileUtil;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FileList {
    @ApiModelProperty(notes = "파일 고유 ID", example = "5073596e-ac44-46d5-9a89-c29610df4ebd", required = true)
    private String id;

    @ApiModelProperty(notes = "파일 경로", example = "images/5073596e-ac44-46d5-9a89-c29610df4ebd.jpeg")
    private String path;

    @ApiModelProperty(notes = "정렬 순서(0부터 시작)", example = "0", required = true)
    private int sequence;

    @Builder.Default
    @ApiModelProperty(notes = "대표 이미지 여부", example = "false")
    private boolean thumbnail = false;

    @Builder.Default
    @JsonIgnore
    private boolean delete = false;

    public static FileList of(PostFileJpo postFileJpo) {
        return FileList.builder()
                .id(postFileJpo.getFile().getId())
                .path(String.format("%s/%s", FileUtil.getAmazonS3BaseURL(), postFileJpo.getFile().getPath()))
                .sequence(postFileJpo.getSequence())
                .thumbnail(postFileJpo.isThumbnail())
                .build();
    }
}
