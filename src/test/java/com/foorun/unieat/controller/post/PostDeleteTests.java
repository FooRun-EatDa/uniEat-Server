package com.foorun.unieat.controller.post;

import com.foorun.unieat.controller.ControllerTest;
import com.foorun.unieat.service.post.PostService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("게시글 삭제")
public class PostDeleteTests extends ControllerTest {
    @MockBean
    private PostService postService;

    @DisplayName("단건 삭제")
    @ParameterizedTest
    @ValueSource(longs = 1)
    void remove(Long id) throws Exception {
        mockMvc.perform(delete("/post/{id}", id)
                        .headers(HttpHeaders.EMPTY))
                .andDo(print())
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("code").value("200"));
    }
}
