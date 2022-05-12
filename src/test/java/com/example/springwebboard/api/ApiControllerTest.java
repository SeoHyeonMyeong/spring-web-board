package com.example.springwebboard.api;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class ApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setupMockMvc() {
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(ApiController.class)
                .build();
    }
    @Test
    void hello() throws Exception {

        // given
        String expected = "hello world!";
        String url = "/api/hello";

        // when
        mockMvc.perform(
            MockMvcRequestBuilders.
            get(url))

        // then
            .andExpect(status().isOk())
            .andExpect(content().string(expected));
    }
}