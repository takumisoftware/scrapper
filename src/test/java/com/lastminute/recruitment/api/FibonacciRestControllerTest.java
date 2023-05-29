package com.lastminute.recruitment.api;

import com.lastminute.recruitment.domain.Fibonacci;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(FibonacciRestController.class)
class FibonacciRestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    Fibonacci fibonacci;
}