package com.foorun.unieat.domain.file.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.foorun.unieat.domain.file.jpo.BaseFileJpo;
import com.foorun.unieat.domain.file.jpo.FileJpo;
import com.foorun.unieat.util.FileUtil;
import com.foorun.unieat.util.MultipartUtil;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

import static org.springframework.util.StringUtils.hasText;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FileDetail {
    @ApiModelProperty(notes = "파일 고유 ID", example = "5073596e-ac44-46d5-9a89-c29610df4ebd", required = true, position = 0)
    private String id;

    @ApiModelProperty(notes = "파일 경로", example = "images/5073596e-ac44-46d5-9a89-c29610df4ebd.jpeg", required = true, position = 1)
    private String path;

    @ApiModelProperty(notes = "파일 URL 경로", example = "https://{hostname}/images/5073596e-ac44-46d5-9a89-c29610df4ebd.jpeg", hidden = true, position = 2)
    private String url;

    @ApiModelProperty(notes = "파일명", example = "풍경이미지", position = 2)
    private String name;

    @ApiModelProperty(notes = "파일 확장자", example = "jpeg", position = 3)
    private String format;

    @ApiModelProperty(notes = "파일 용량", example = "146162", position = 4)
    private long bytes;

    @ApiModelProperty(notes = "이미지 가로 길이", example = "1400", position = 5)
    private Integer width;

    @ApiModelProperty(notes = "이미지 세로 길이", example = "700", position = 6)
    private Integer height;

    @ApiModelProperty(notes = "정렬 순서(0부터 시작)", example = "0", required = true, position = 7)
    private int sequence;

    @Builder.Default
    @ApiModelProperty(notes = "대표 이미지 여부", example = "false", position = 8)
    private boolean thumbnail = false;

    @Builder.Default
    @ApiModelProperty(notes = "삭제된 파일인지 여부", example = "false", position = 9)
    private boolean delete = false;

    @Builder.Default
    @ApiModelProperty(notes = "새로 생성된 파일인지 여부", example = "false", position = 9)
    private boolean newly = false;

    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    /**
     * 새로 추가된 파일인지 여부
     * @return 새로 추가됨 ?
     */
    public boolean isNew() {
        return hasText(name);
    }

    public static FileDetail multipartOf(MultipartFile multipartFile) {
        final String fileId = MultipartUtil.createFileId();
        final String format = MultipartUtil.getFormat(multipartFile.getContentType());
        return FileDetail.builder()
                .id(fileId)
                .name(multipartFile.getOriginalFilename())
                .format(format)
                .url(String.format("%s/%s", FileUtil.getAmazonS3BaseURL(), MultipartUtil.createPath(fileId, format)))
                .path(MultipartUtil.createPath(fileId, format))
                .bytes(multipartFile.getSize())
                .build();
    }

    public static FileDetail of(BaseFileJpo baseFileJpo) {
        return FileDetail.builder()
                .id(baseFileJpo.getFile().getId())
                .url(String.format("%s/%s", FileUtil.getAmazonS3BaseURL(), baseFileJpo.getFile().getPath()))
                .path(baseFileJpo.getFile().getPath())
                .sequence(baseFileJpo.getSequence())
                .thumbnail(baseFileJpo.isThumbnail())
                .build();
    }

    public FileJpo asJpo() {
        return FileJpo.builder()
                .id(id)
                .name(name)
                .format(format)
                .path(path)
                .width(width)
                .height(height)
                .bytes(bytes)
                .build();
    }
}
