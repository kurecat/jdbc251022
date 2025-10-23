package com.human.jdbc251022.model;
import java.time.LocalDateTime;

public class Fdclog {
    private int logno; // 로그번호
    private String seqno; // 설비번호
    private String paramno; // 파라미터번호
    private double param_value; // 파라미터 값
    private LocalDateTime log_time; // 로그 시간

    public String toString() {
        return "로그번호: " + logno + "\n" +
                "설비번호: " + seqno + "\n" +
                "파라미터번호: " + paramno + "\n" +
                "파라미터 값: " + param_value + "\n" +
                "로그 시간: " + log_time + "\n";
    }
}