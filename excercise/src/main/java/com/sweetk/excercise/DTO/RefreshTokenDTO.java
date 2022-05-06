package com.sweetk.excercise.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class RefreshTokenDTO {
    private String key;
    private String value;

    @Builder
    public void tokenbuilder(String key, String value){
        this.key = key;
        this.value = value;

    }
}
