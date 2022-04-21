package com.sweetk.excercise.Controller;

import com.sweetk.excercise.DTO.BoardDTO;
import com.sweetk.excercise.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Board")
public class BoardController {

    @Autowired
    BoardService service;

    @PostMapping("/create")
    public String createBoard(BoardDTO dto){
        String s = "게시판 작성에 실패했습니다";

        boolean b = false;

        b = service.createBoard(dto);

        if(b){
            s = "게시판 작성에 성공했습니다";
        }

        return s;
    }

    @GetMapping("/list")
    public List<BoardDTO> BoardList(int Pagenumber){

        List<BoardDTO> list = service.BoardList(Pagenumber);

        return list;
    }

    @GetMapping("/list2")
    public  List<BoardDTO> SearchBoardList(int Pagenumber, String search, String username){

        List<BoardDTO> list = service.BoardSearchList(Pagenumber, search, username);

        return list;
    }

    @DeleteMapping("/delete")
    public String deleteBoard(BoardDTO dto){
        String s = "삭제에 실패했습니다";

        boolean b = false;
        b = service.deleteBoard(dto);

        if(b){
            s = "삭제에 성공했습니다";
        }

        return s;
    }

    @PatchMapping("/update")
    public String updateBoard(BoardDTO dto){
        String s = "업데이트에 실패했습니다";

        boolean b = false;
        b = service.updateBoard(dto);
        if(b) {
            s = "업데이트에 성공했습니다";
        }

        return s;
    }
}
