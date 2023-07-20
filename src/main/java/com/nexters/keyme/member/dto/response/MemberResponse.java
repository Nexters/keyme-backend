package com.nexters.keyme.member.dto.response;

import com.nexters.keyme.model.member.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@Builder
public class MemberResponse {
    @NotNull
    private Long id;

    private TokenResponse token;

    public MemberResponse(MemberEntity member) {
        this.id = member.getId();
    }
}
