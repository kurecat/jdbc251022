package com.human.jdbc251022.model;
import lombok.*;
@Getter @Setter @AllArgsConstructor @NoArgsConstructor

public class iDept {
    private int deptno;
    private String deptname;

    public String toString() {
        return "부서번호: " + deptno +  " / 부서이름: " + deptname + "\n";
    }

}