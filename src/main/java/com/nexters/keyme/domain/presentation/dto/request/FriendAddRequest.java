package com.nexters.keyme.domain.presentation.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FriendAddRequest {
    @ApiModelProperty(value="문제 ID", example = "22")
    private String friendCode;
}
