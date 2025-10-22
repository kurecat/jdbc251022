package com.human.jdbc251022.model;
// 회원 정보를 DB에 넣고 빼기 위함.

import lombok.*;

import java.time.LocalDateTime;

//게터, 세터, toString, 매개변수가 다 있는 생성자, 매개변수가 없는데 생성자를 자동으로 만들어줌.
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Member {
    private String email;
    private String pwd;
    private String name;
    private LocalDateTime regDate;

    public String toString() {
        return  "===== 회원 정보 =====" + "\n" +
                "이메일: " + email + "\n" +
                "비밀번호: " + pwd + "\n" +
                "이름: " + name + "\n" +
                "입사일 " + regDate;
    }
}
