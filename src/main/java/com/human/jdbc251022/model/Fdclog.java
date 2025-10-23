package com.human.jdbc251022.model;
import lombok.*;
import java.time.LocalDate;
@Getter @Setter @AllArgsConstructor @NoArgsConstructor


public class Fdclog {
    private int logno; // 로그번호
    private String seqno; // 설비번호
    private String paramno; // 파라미터번호
    private double paramvalue; // 파라미터 값
    private LocalDate logtime; // 로그 시간

    public String toString() {
        return "로그번호: " + logno + "\n" +
                "설비번호: " + seqno + "\n" +
                "파라미터번호: " + paramno + "\n" +
                "파라미터 값: " + paramvalue + "\n" +
                "로그 시간: " + logtime + "\n";
    }
}