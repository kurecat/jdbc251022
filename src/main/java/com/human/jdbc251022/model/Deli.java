package com.human.jdbc251022.model;
import lombok.*;
import java.time.LocalDateTime;
@Getter @Setter @AllArgsConstructor @NoArgsConstructor


public class Deli {
    private int delino; // 출고번호
    private int invno; // 재고번호
    private int deliqty; // 출고수량
    private String loc; // 도착지
    private LocalDateTime delidate; // 출고일자
    private String note; // 비고

    public String toString() {
        return "출고번호: " + delino + "\n" +
                "재고번호: " + invno + "\n" +
                "출고수량: " + deliqty + "\n" +
                "도착지: " + loc + "\n" +
                "출고일자: " + delidate + "\n" +
                "비고: " + note + "\n";
    }
}
