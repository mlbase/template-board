package com.sweetk.excercise.Controller;

import com.sweetk.excercise.DTO.UserDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {



    @PostMapping("/sign-up")
    public String createUser(UserDTO dto) {
        String msg = "회원가입에 실패했습니다";


        return msg;
    }

    @PostMapping("/login")
    public String loginUser(UserDTO dto){
        String msg = "회원가입에 실패했습니다";

        return msg;
    }
}
