package com.nexters.keyme.member.presentation.dto.response;

import com.nexters.keyme.auth.dto.response.TokenResponse;
import com.nexters.keyme.member.domain.model.MemberEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@Builder
public class MemberResponse {
    @NotNull
    @ApiModelProperty(value="유저 고유 ID")
    private Long id;

    private TokenResponse token;

    public MemberResponse(MemberEntity member) {
        this.id = member.getId();
    }
}
