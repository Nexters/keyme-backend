package com.nexters.keyme.dummy;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DummyController {
    @GetMapping("/")
    public String helloKeyme() {
        return "Hello keyme!";
    }
}
