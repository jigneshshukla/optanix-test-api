package com.optanix.test.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author jignesh
 * This class provides web integration tests for ApiController
 */
@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest
public class WebIntegrationTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private static Map<String, String> getCalculateWordsCountPerStatementRequest() {
        Map<String, String> data = new HashMap<>();
        data.put("testText", "Hello World! How are you all doing? This is WordCountService's unit testing.");
        return data;
    }

    private static Map<String, String> getTotalLetterCountRequest() {
        Map<String, String> data = new HashMap<>();
        data.put("testText", "Hello World!");
        return data;
    }

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    public void calculateWordsCountPerSentenceTest() throws Exception {
        Map<String, String> request = getCalculateWordsCountPerStatementRequest();
        performPost("/word_count_per_sentence", request).andExpect(status().isOk());
    }

    @Test
    public void calculateWordsCountPerSentenceTestWithBadRequest() throws Exception {
        performPost("/word_count_per_sentence", new HashMap<String, String>()).andExpect(status().isBadRequest());
    }

    @Test
    public void calculateTotalLetterCountTest() throws Exception {
        Map<String, String> request = getTotalLetterCountRequest();
        performPost("/total_letter_count", request).andExpect(status().isOk());
    }

    @Test
    public void calculateTotalLetterCountTestWithBadRequest() throws Exception {
        performPost("/total_letter_count", new HashMap<String, String>()).andExpect(status().isBadRequest());
    }

    private ResultActions performPost(String path, Object body) throws Exception {
        return mockMvc.perform(post(path)
                .content(objectMapper.writeValueAsString(body))
                .contentType(MediaType.APPLICATION_JSON_UTF8));
    }
}
