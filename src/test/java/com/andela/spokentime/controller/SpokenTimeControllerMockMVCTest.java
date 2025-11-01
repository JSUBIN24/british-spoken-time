package com.andela.spokentime.controller;

import com.andela.spokentime.dto.SpokenTimeResponse;
import com.andela.spokentime.service.SpokenTimeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(SpokenTimeController.class)
class SpokenTimeControllerMockMVCTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private SpokenTimeService spokenTimeService;

    @Test
    void testGetSpokenTime() throws Exception {

        when(spokenTimeService.getSpokenTime(anyString(), anyString()))
                .thenReturn(new SpokenTimeResponse("noon"));

        String requestBody = """
                {
                    "time": "12:00",
                    "locale": "british"
                }
                """;

        mockMvc.perform(post("/spoken-time")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.spokenTime").value("noon"));

    }


}