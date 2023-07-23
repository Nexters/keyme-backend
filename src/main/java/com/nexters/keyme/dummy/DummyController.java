package com.nexters.keyme.dummy;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.nexters.keyme.common.config.SwaggerConfig.SWAGGER_AUTHORIZATION_SCHEME;

@RequiredArgsConstructor
@RestController
@RequestMapping("/hello")
@Api(tags = "테스트", description = "")
public class DummyController {
    
    private final DummyService dummyService;

    @GetMapping
    @ApiOperation(value = "(테스트용)")
    public String helloKeyme() {
        return "Hello keyme";
    }

    @GetMapping("/auth")
    @ApiOperation(value = "(테스트용) 인증필요")
    @SecurityRequirement(name = SWAGGER_AUTHORIZATION_SCHEME)
    public String helloAuthKeyme() {
        return "Hello Authenticated keyme!";
    }
}
