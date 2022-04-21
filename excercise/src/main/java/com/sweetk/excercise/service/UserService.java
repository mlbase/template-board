package com.sweetk.excercise.service;

import com.sweetk.excercise.DAO.UserDAO;
import com.sweetk.excercise.DTO.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class UserService implements iuserService{

    @Autowired
    UserDAO dao;

    @Override
    public Boolean createUser(UserDTO dto) {
        return dao.createUser(dto);
    }

    @Override
    public UserDTO login(UserDTO dto) {
        return dao.login(dto);
    }

    @Override
    public Boolean changePassword(String password) {
        return dao.changePassword(password);
    }
}
