package com.sweetk.excercise.DTO;


import lombok.*;

import java.util.Date;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BoardDTO {

    private int seq;
    private int userSeq;
    private String title;
    private String content;
    private int readcount;
    private String username;
    private String imgSource;
    private Date regDate;




}
