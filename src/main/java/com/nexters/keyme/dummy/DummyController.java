package com.nexters.keyme.dummy;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class DummyController {
    
    private final DummyService dummyService;

    @GetMapping("/hello")
    @ApiOperation(value = "테스트용 컨트롤러(인증 불필요)", notes = "인증처리 없이 접근할 수 있는 엔드포인트")
    public String helloKeyme() {
        return "Hello keyme!";
    }

    @GetMapping("/auth")
    @ApiOperation(value = "Hello Auth API")
    public String helloAuthKeyme() {
        return "Hello Authenticated keyme!";
    }
}
