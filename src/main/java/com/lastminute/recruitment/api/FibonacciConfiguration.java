package com.lastminute.recruitment.api;

import com.lastminute.recruitment.domain.Fibonacci;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FibonacciConfiguration {

    @Bean
    public Fibonacci fibonacci() {
        return new Fibonacci();
    }
}
