package com.sweetk.excercise.DAO;


import com.sweetk.excercise.DTO.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserDAO {

    Boolean createUser(UserDTO dto);

    UserDTO login(UserDTO dto);

    Boolean changePassword(String password);

    UserDTO idcheck(String userId);

    UserDTO findseq(String username);
}
