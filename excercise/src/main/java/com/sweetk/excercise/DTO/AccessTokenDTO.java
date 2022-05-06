package com.sweetk.excercise.DTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class AccessTokenDTO {

    @AllArgsConstructor
    @Getter
    @Builder
    public static class TokenInfo{
        @ApiModelProperty(value = "토큰 타입")
        private String grantType;
        @ApiModelProperty(value = "Access Token")
        private String accessToken;
        @ApiModelProperty(value = "Refresh Token")
        private String refreshToken;
        @ApiModelProperty(value = "Refresh Token 만료시간")
        private Long refeshTokenExpirationTime;
    }
}
