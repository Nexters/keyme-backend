package com.nexters.keyme.dummy;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.nexters.keyme.config.SwaggerConfig.SWAGGER_AUTHORIZATION_SCHEME;

@RequiredArgsConstructor
@RestController
@RequestMapping("/hello")
@Slf4j
@Api(tags = "테스트", description = "")
public class DummyController {
    
    private final DummyService dummyService;

    @GetMapping
    @ApiOperation(value = "(테스트용)")
    public String helloKeyme() {
        log.info("test tset");
        return "Hello keyme";
    }

    @GetMapping("/error")
    @ApiOperation(value = "(테스트용)")
    public String helloError() {
        log.error("error");
        return "Hello Error";
    }

    @GetMapping("/auth")
    @ApiOperation(value = "(테스트용) 인증필요")
    @SecurityRequirement(name = SWAGGER_AUTHORIZATION_SCHEME)
    public String helloAuthKeyme() {
        return "Hello Authenticated keyme!";
    }
}
