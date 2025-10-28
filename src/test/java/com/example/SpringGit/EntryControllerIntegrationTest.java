package com.example.SpringGit;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EntryControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void arithmetic_below10_triggers_retry_and_recover_real_flow() throws Exception {

        mockMvc.perform(get("/git/arithmetic/5"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Recovery: Invalid arithmetic value. value less than 10"));
        // Console will show retry attempts + @Recover from your ApiService
    }

}
