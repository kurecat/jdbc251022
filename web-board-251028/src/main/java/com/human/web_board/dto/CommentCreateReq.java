package com.human.web_board.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CommentCreateReq {
    private Long postId;
    private String memberEmail;
    private String content;
}
