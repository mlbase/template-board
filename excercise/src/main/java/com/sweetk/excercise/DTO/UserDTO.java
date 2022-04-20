package com.sweetk.excercise.DTO;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@ToString
@NoArgsConstructor
@Setter
@Getter
public class UserDTO {

    private int seq;
    private String userId;
    private String password;
    private String userName;
    private String nickname;
    private String email;
    private Date regDate;
    private String authorityName;

}
