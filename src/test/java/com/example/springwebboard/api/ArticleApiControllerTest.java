package com.example.springwebboard.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class ArticleApiControllerTest {

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    public void setupMockMvc() {
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(ArticleApiController.class)
                .build();
    }

    @Test
    void index() throws Exception {
        // given
        String expected = "";
        String url = "/api/articles";

        // when
        mockMvc.perform(
            MockMvcRequestBuilders.
            get(url))

            // then
            .andExpect(status().isOk())
            .andExpect(content().string(expected));
    }

    @Test
    void detail() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}