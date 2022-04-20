package com.sweetk.excercise.DAO;

import com.sweetk.excercise.DTO.BoardDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BoardDAO {

    boolean createBoard(BoardDTO dto);

    List<BoardDTO> BoardList(int PageNumber);
}
