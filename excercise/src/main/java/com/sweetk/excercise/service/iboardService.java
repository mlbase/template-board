package com.sweetk.excercise.service;

import com.sweetk.excercise.DTO.BoardDTO;

import java.util.List;

public interface iboardService {
    boolean createBoard(BoardDTO dto);

    List<BoardDTO> BoardList(int PageNumber);

    List<BoardDTO> BoardSearchList(int PageNumber, String search, String username);

    boolean deleteBoard(BoardDTO dto);

    boolean updateBoard(BoardDTO dto);
}
