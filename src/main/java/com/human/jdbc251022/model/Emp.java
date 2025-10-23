package com.human.jdbc251022.model;
// 회원 정보를 DB에 넣고 빼기 위함.

import lombok.*;

import java.time.LocalDateTime;

//게터, 세터, toString, 매개변수가 다 있는 생성자, 매개변수가 없는데 생성자를 자동으로 만들어줌.
@Getter @Setter @AllArgsConstructor @NoArgsConstructor

public class Emp {
    private int empno;
    private int deptno;
    private String ename;
    private String job;
    private LocalDateTime hiredate;
    private int mgr;

    public String toString() {
        return "사원번호: " + empno + "\n" +
                "부서번호: " + deptno + "\n" +
                "이름: " + ename + "\n" +
                "직책: " + job  + "\n" +
                "입사일: " + hiredate + "\n" +
                "상사: " + mgr + "\n";

    }

}