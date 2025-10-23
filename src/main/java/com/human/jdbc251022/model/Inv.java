package com.human.jdbc251022.model;
import lombok.*;
import java.time.LocalDateTime;
@Getter @Setter @AllArgsConstructor @NoArgsConstructor


// ---- 재고상태조회 ----
public class Inv {
    private int invno; // 재고번호
    private int prodno; // 제품번호
    private int qty; // 수량
    private String location; // 위치
    private LocalDateTime updatedate; // 수정일자

    public String toString() {
        return "재고번호: " + invno + "\n" +
                "제품번호: " + prodno + "\n" +
                "수량: " + qty + "\n" +
                "위치: " + location + "\n" +
                "수정일자: " + updatedate + "\n";
    }
}