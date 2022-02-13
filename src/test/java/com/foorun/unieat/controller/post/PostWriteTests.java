package com.foorun.unieat.controller.post;

import com.foorun.unieat.controller.ControllerTest;
import com.foorun.unieat.domain.member.jpo.MemberJpo;
import com.foorun.unieat.domain.post.dto.Post;
import com.foorun.unieat.service.post.PostService;
import com.foorun.unieat.util.MockUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("게시글 작성")
public class PostWriteTests extends ControllerTest {
    @MockBean
    private PostService postService;

    @DisplayName("단건 생성")
    @Test
    void write() throws Exception {
        Post post = MockUtil.createMock(Post.class);

        when(postService.ensureMember(anyLong()))
                .thenReturn(mock(MemberJpo.class));

        mockMvc.perform(post("/post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .headers(HttpHeaders.EMPTY)
                        .content(post.asJson()))
                .andDo(print())
                .andExpect(status().is(HttpStatus.CREATED.value()))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("code").value("201"));
    }
}
