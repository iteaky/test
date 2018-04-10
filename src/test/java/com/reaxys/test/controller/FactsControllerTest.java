package com.reaxys.test.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reaxys.test.service.Implimentations.FactsServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
public class FactsControllerTest {

    @InjectMocks
    FactsController factsController;
    @Mock
    private FactsServiceImpl service;
    private ObjectMapper mapper;
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(factsController)
                .setControllerAdvice(new GlobalExceptionController())
                .build();
        when(service.getFacts()).thenReturn(Collections.singletonList("GOOD"));
    }

    @Test
    public void getFacts() throws Exception {
        mockMvc.perform(get("/fact")).andExpect(status().isOk());
    }

    @Test
    public void getFactsWithOptions() throws Exception {
        String json = mapper.writeValueAsString(Arrays.asList("search", "display", "presented"));
        mockMvc.perform(post("/fact").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk());
    }

    @Test
    public void getFactsWithOptions_MalformedJSON() throws Exception {
        String json = "error! ! ";
        mockMvc.perform(post("/fact").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isBadRequest());
    }
}