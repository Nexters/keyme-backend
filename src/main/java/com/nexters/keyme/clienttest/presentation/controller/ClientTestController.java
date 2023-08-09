package com.nexters.keyme.clienttest.presentation.controller;

import com.nexters.keyme.auth.domain.internaldto.UserInfo;
import com.nexters.keyme.clienttest.application.ClientTestService;
import com.nexters.keyme.common.annotation.RequestUser;
import com.nexters.keyme.common.dto.response.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.nexters.keyme.common.config.SwaggerConfig.SWAGGER_AUTHORIZATION_SCHEME;

@Api(tags = "클라이언트 테스트 API", description = "테스트를 위한 초기화 API")
@RestController
@RequestMapping("/clienttest")
@RequiredArgsConstructor
public class ClientTestController {
    private final ClientTestService clientTestService;

    @GetMapping
    public void hi() { }

    @DeleteMapping("/member")
    @ApiOperation(value = "멤버 정보 삭제")
    @SecurityRequirement(name = SWAGGER_AUTHORIZATION_SCHEME)
    public ResponseEntity<ApiResponse> deleteMember(@RequestUser UserInfo userInfo) {
        clientTestService.clearMember(userInfo.getMemberId());
        return ResponseEntity.ok(new ApiResponse("SUCCESS", "멤버가 삭제되었습니다.", null));
    }
}
