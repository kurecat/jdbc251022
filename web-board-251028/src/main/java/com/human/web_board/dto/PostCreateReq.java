package com.human.web_board.dto;

import lombok.*;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
public class PostCreateReq {
    private String memberId;
    private String title;
    private String content;
}
