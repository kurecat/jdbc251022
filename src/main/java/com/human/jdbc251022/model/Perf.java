package com.human.jdbc251022.model;

import java.time.LocalDateTime;

// ---- 작업실적조회 ----
public class Perf {
    private int perfno; // 실적번호
    private int procno; // 공정번호
    private int empno; // 사원번호
    private int wono; // 작업지시번호
    private String seqno; // 순번
    private int qty; // 수량
    private int qtydefect; // 불량수량
    private LocalDateTime perfdate; // 실적일자
    private double fara; // 불량율
    private String note; // 비고

    public String toString() {
        return "실적번호: " + perfno + "\n" +
                "공정번호: " + procno + "\n" +
                "사원번호: " + empno + "\n" +
                "작업지시번호: " + wono + "\n" +
                "순번: " + seqno + "\n" +
                "수량: " + qty + "\n" +
                "불량수량: " + qtydefect + "\n" +
                "실적일자: " + perfdate + "\n" +
                "불량율: " + fara + "\n" +
                "비고: " + note + "\n";
    }
}
