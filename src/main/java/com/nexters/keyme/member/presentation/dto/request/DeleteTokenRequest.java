package com.nexters.keyme.member.presentation.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class DeleteTokenRequest {
    @ApiModelProperty(name = "FCM 토큰", example = "(token)")
    private String token;
}
