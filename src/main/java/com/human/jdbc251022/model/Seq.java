package com.human.jdbc251022.model;


// ---- 설비 및 공정 조회 ----
// ---- [1] 설비상세조회 ----
public class Seq {
    private String seqno; // 설비번호
    private String seqname; // 설비명
    private String seqor; // 공정순서
    private String note; // 비고

    public String toString() {
        return "설비번호: " + seqno + "\n" +
                "설비명: " + seqname + "\n" +
                "공정순서: " + seqor + "\n" +
                "비고: " + note + "\n";
    }
}
