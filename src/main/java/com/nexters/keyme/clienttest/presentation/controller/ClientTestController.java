package com.nexters.keyme.clienttest.presentation.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "클라이언트 테스트 API", description = "테스트를 위한 초기화 API")
@RestController
@RequestMapping("/clienttest")
@RequiredArgsConstructor
public class ClientTestController {

    @GetMapping
    public void hi() { }
}
