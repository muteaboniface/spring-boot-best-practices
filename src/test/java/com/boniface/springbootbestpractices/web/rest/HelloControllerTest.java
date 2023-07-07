package com.boniface.springbootbestpractices.web.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HelloController.class)
public class HelloControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    void testSayHello() throws Exception {
        mockMvc.perform(get("/")) // sends a Http request
                .andExpectAll( // check all assertions at once
                        status().isOk(), // status code 200
                        content().string("Hello World"), // expect text 'Hello World'
                        content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN) // response to be of type text/plain
                );
    }

}