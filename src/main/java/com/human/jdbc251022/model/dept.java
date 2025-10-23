package com.human.jdbc251022.model;
import lombok.*;
import java.time.LocalDateTime;
@Getter @Setter @AllArgsConstructor @NoArgsConstructor

public class dept {
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
                "직책: " + job + "\n" +
                "입사일: " + hiredate + "\n" +
                "상사: " + mgr + "\n";
    }
}

