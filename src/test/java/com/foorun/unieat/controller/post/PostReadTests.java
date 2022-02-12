package com.foorun.unieat.controller.post;

import com.foorun.unieat.controller.ControllerTest;
import com.foorun.unieat.service.post.PostService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("게시글 조회")
public class PostReadTests extends ControllerTest {
    @MockBean
    private PostService postService;

    @DisplayName("목록(여러건) 조회")
    @Test
    void read() throws Exception {
        mockMvc.perform(get("/post")
                        .param("page", "0")
                        .param("offset", "20")
                        .headers(HttpHeaders.EMPTY))
                .andDo(print())
                .andExpect(status().is(HttpStatus.OK.value()));
    }

    @DisplayName("상세(단건) 조회")
    @ParameterizedTest
    @ValueSource(longs = { 1, 2, 3 })
    void read(Long id) throws Exception {
        mockMvc.perform(get("/post/{id}", id)
                        .headers(HttpHeaders.EMPTY))
                .andDo(print())
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("code").value("200"));
    }
}
