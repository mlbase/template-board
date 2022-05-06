package com.sweetk.excercise.Controller;

import com.sweetk.excercise.DTO.UserDTO;
import com.sweetk.excercise.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@Api(tags = "{회원관리 API}")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService service;

    @Autowired
    PasswordEncoder encoder;

    @ApiOperation(value = "회원가입", notes = "회원가입 상태메세지 stirng으로 응답")
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

    @ApiOperation(value = "로그인", notes = "로그인")
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

    @ApiOperation(value = "session-key", notes = "session key 발급")
    @PostMapping("/session-key")
    public String getSessionKey(UserDTO dto){


        String pwd = dto.getPassword();

        //System.out.println(dto.toString());

        UserDTO userdto = service.login(dto);

        boolean passwordCheck = encoder.matches(pwd , userdto.getPassword());

        //System.out.println(b);

        String session_secrete_key = "7JWE7IS47IWY7JWI7I2o7ISc64W465Oc66Gc7Zi47Iqk7YyF7ZW07" +
                "ISc7JWU7Zi47ZmU7ZW07JW865CY64Sk";

        if(!passwordCheck){
            session_secrete_key = "";
        }



        return session_secrete_key;
    }

    @ApiOperation(value = "idcheck", notes = "회원가입시 id 중복 체크")
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

    @ApiOperation(value = "passwordChange", notes = "비밀번호 변경하는 api")
    @PostMapping("/changepassword")
    public String changePassword(String password, String newPassword){
        String changeMsg = "비밀번호 변경에 실패했습니다";

        return changeMsg;
    }
}
