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
    public String createUser(UserDTO dto) {
        String sign_up_message = "회원가입에 실패했습니다";

        System.out.println(dto.toString());

        boolean signCheck = false;

        String pwd = dto.getPassword();

        dto.setPassword(encoder.encode(pwd));

        signCheck = service.createUser(dto);

        //System.out.println(b);

        if(signCheck){
            sign_up_message = "OK";
        }

        return sign_up_message;
    }

    @PostMapping("/login")
    public UserDTO loginUser(UserDTO dto){


        String pwd = dto.getPassword();

        //System.out.println(dto.toString());

        UserDTO userdto = service.login(dto);

        boolean passwordCheck = encoder.matches(pwd , userdto.getPassword());

        //System.out.println(b);

        if(!passwordCheck){
            userdto = null;
        }

        userdto.setPassword(null);

        return userdto;
    }

    @PostMapping("/id")
    public String idcheck(String userId){
        String checkMsg = "no";


        //System.out.println(userId);

        UserDTO dto = service.idcheck(userId);


        if(dto != null){
            checkMsg = "YES";
        }

        return checkMsg;
    }

    @PostMapping("/changepassword")
    public String changePassword(String password, String newPassword){
        String changeMsg = "비밀번호 변경에 실패했습니다";

        return changeMsg;
    }
}
