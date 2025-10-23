package com.human.jdbc251022.model;


import java.time.LocalDateTime;

// ---- 작업지시조회 ----
public class Wo {
    private int wono; // 작업지시번호
    private int prodno; // 제품번호
    private int prodcno; // 공정번호
    private LocalDateTime orderdate; // 지시일자
    private LocalDateTime duedate; // 완료 예정일
    private int qty; // 수량
    private String note; // 비고

    public String toString() {
        return "작업지시번호: " + wono + "\n" +
                "제품번호: " + prodno + "\n" +
                "공정번호: " + prodcno + "\n" +
                "지시일자: " + orderdate + "\n" +
                "완료 예정일: " + duedate + "\n" +
                "수량: " + qty + "\n" +
                "비고: " + note + "\n";
    }
}
