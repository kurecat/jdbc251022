package com.human.jdbc251022.model;
import lombok.*;
import java.time.LocalDateTime;
@Getter @Setter @AllArgsConstructor @NoArgsConstructor

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