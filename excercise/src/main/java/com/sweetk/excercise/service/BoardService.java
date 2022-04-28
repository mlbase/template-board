package com.sweetk.excercise.service;

import com.sweetk.excercise.DAO.BoardDAO;
import com.sweetk.excercise.DTO.BoardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public class BoardService implements iboardService{

    @Autowired
    BoardDAO dao;

    @Override
    public boolean createBoard(BoardDTO dto) {
        return dao.createBoard(dto);
    }

    @Override
    public List<BoardDTO> BoardList(int PageNumber) {
        return dao.BoardList(PageNumber);
    }

    @Override
    public List<BoardDTO> BoardSearchList(int PageNumber, String search, String username) {
        return dao.BoardSearchList(PageNumber, search, username);
    }

    @Override
    public boolean deleteBoard(BoardDTO dto) {
        return dao.deleteBoard(dto);
    }

    @Override
    public boolean updateBoard(BoardDTO dto) {
        return dao.updateBoard(dto);
    }

    @Override
    public List<BoardDTO> BoardnosortList() {
        return dao.BoardnosortList();
    }

    @Override
    public BoardDTO BoardDetail(int seq) {
        return dao.BoardDetail(seq);
    }

    @Override
    public boolean BoardRead(int seq) {
        return dao.BoardRead(seq);
    }

    @Override
    public Integer totalNumber() {
        return dao.totalNumber();
    }
}
