package com.human.jdbc251022.model;

// ---- 제품조회 ----
public class Prod {
    private int prodno; // 제품번호
    private String prodname; // 제품명
    private String spec; // 사양
    private String unit; // 단위

    public String toString() {
        return "제품번호: " + prodno + "\n" +
                "제품명: " + prodname + "\n" +
                "사양: " + spec + "\n" +
                "단위: " + unit + "\n";
    }
}
