package com.human.jdbc251022.model;
import lombok.*;
import java.time.LocalDate;
@Getter @Setter @AllArgsConstructor @NoArgsConstructor


public class Fdcfault {
    private int faultno; // 오류번호
    private int logno; // 로그번호
    private String seqno; // 설비번호
    private String faultcode; // 오류코드
    private String faultmsg; // 오류메시지
    private LocalDate faulttime; // 오류 발생 시간

    public String toString() {
        return "오류번호: " + faultno + "\n" +
                "로그번호: " + logno + "\n" +
                "설비번호: " + seqno + "\n" +
                "오류코드: " + faultcode + "\n" +
                "오류메시지: " + faultmsg + "\n" +
                "오류 발생 시간: " + faulttime + "\n";
    }
}