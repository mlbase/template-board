package com.sweetk.excercise.Controller;

import com.sweetk.excercise.DTO.BoardDTO;
import com.sweetk.excercise.DTO.UserDTO;
import com.sweetk.excercise.service.BoardService;
import com.sweetk.excercise.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/Board")
public class BoardController {

    @Autowired
    BoardService service;
    UserService user;
    Logger logger = LoggerFactory.getLogger(BoardController.class);

    @PostMapping("/create")
    public String createBoard(BoardDTO dto){

        //System.out.println(dto.toString());



        String creationMsg = "게시판 작성에 실패했습니다";

        boolean creat_check = false;

        creat_check = service.createBoard(dto);

        if(creat_check){
            creationMsg = "게시판 작성에 성공했습니다";
        }
        logger.info("BoardController.createBoard");

        return creationMsg;
    }

    @GetMapping("/listNosort")
    public List<BoardDTO> BoardNoSortList(){

        List<BoardDTO> list = service.BoardnosortList();

        return list;
    }

    @GetMapping("/list")
    public List<BoardDTO> BoardList(Integer Pagenumber){

        //System.out.println(Pagenumber);

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
        String deleteMsg = "삭제에 실패했습니다";

        boolean deleteCheck = false;
        deleteCheck = service.deleteBoard(dto);

        if(deleteCheck){
            deleteMsg = "삭제에 성공했습니다";
        }

        return deleteMsg;
    }

    @PatchMapping("/update")
    public String updateBoard(BoardDTO dto){
        String updateMsg = "업데이트에 실패했습니다";

        boolean updateCheck = false;
        updateCheck = service.updateBoard(dto);
        if(updateCheck) {
            updateMsg = "업데이트에 성공했습니다";

        }
        logger.info(updateMsg);

        return updateMsg;
    }

    @GetMapping("/detail")
    public BoardDTO boarddetail(int seq){
        BoardDTO dto = service.BoardDetail(seq);

        boolean countCheck = false;
        countCheck = service.BoardRead(seq);
        if (!countCheck){
            logger.info("조회수 증가 실패");
        }
        logger.info("BoardController.boarddetail");
        return dto;
    }

    @GetMapping("/count")
    public Integer BoardCount(){
        int BoardCount = service.totalNumber();

        logger.info("BoardController 게시판 총 갯수: "+BoardCount);

        return BoardCount;
    }

}
