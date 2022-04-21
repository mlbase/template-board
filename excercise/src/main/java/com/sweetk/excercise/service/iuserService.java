package com.sweetk.excercise.service;

import com.sweetk.excercise.DTO.UserDTO;

public interface iuserService {

    Boolean createUser(UserDTO dto);

    UserDTO login(UserDTO dto);

    Boolean changePassword(String password);
}
