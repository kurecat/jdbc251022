package com.human.web_board.model;
import lombok.*;
import java.time.LocalDateTime;

// DB와 동일한 형태의 클래스 (Entity 역할)

@Getter              // 게터 메서드 자동 생성
@Setter              // 세터 메서드 자동 생성
@AllArgsConstructor  // 매개변수가 없는 기본생성자 자동 생성
@NoArgsConstructor   // 매개변수가 다 있는 생성자 자동 생성
@ToString            //
public class Member {
    private Long id;      // 자동 증가 값
    private String email; // 이메일
    private String pwd;   // 비밀번호
    private String name;
    private LocalDateTime redDate; // 가입일
}
