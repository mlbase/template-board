package com.sweetk.excercise.Controller;

import com.sweetk.excercise.DTO.UserDTO;
import com.sweetk.excercise.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService service;

    @Autowired
    PasswordEncoder encoder;

    @PostMapping("/sign-up")
    public String createUser(@RequestBody UserDTO dto) {
        String s = "회원가입에 실패했습니다";

        System.out.println(dto.toString());

        boolean b = false;

        String pwd = dto.getPassword();

        dto.setPassword(encoder.encode(pwd));

        b = service.createUser(dto);

        System.out.println(b);

        if(b){
            s = "회원가입에 성공했습니다";
        }

        return s;
    }

    @PostMapping("/login")
    public UserDTO loginUser(@RequestBody UserDTO dto){


        String pwd = dto.getPassword();

        //System.out.println(pwd);

        UserDTO userdto = service.login(dto);

        boolean b = encoder.matches(pwd , dto.getPassword());

        System.out.println(b);

        if(!b){
            userdto = null;
        }

        return userdto;
    }

    @PostMapping("/changepassword")
    public String changePassword(String password, String newPassword){
        String s = "비밀번호 변경에 실패했습니다";

        return s;
    }
}
