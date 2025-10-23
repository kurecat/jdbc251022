package com.human.jdbc251022.model;
import lombok.*;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor

// ---- [4] 설비 공정 조회 ----
public class Proc {
    private int procno; // 공정번호
    private String seqno; // 공정순서
    private String procname; // 공정명

    public String toString() {
        return "공정번호: " + procno + "\n" +
                "공정순서: " + seqno + "\n" +
                "공정명: " + procname + "\n";
    }
}