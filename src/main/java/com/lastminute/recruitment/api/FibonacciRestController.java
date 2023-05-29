package com.lastminute.recruitment.api;

import com.lastminute.recruitment.domain.Fibonacci;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FibonacciRestController {
    private final Fibonacci fibonacci;

    public FibonacciRestController(Fibonacci fibonacci) {
        this.fibonacci = fibonacci;
    }
}
