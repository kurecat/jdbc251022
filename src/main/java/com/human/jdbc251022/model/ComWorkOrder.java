package com.human.jdbc251022.model;
import lombok.*;
@Getter @Setter @AllArgsConstructor @NoArgsConstructor

public class ComWorkOrder {
        private String wono;       // 작업지시번호
        private String prodName;   // 제품명
        private int qty;           // 목표수량
        private String orderDate;  // 지시일 (YY-MM-DD 형식의 문자열)
        private String dueDate;    // 완료예정일 (YY-MM-DD 형식의 문자열)


    public String toString() {
        return wono + "\n" +
                prodName + "\n" +
                qty + "\n" +
                orderDate + "\n" +
                dueDate + "\n";
    }
}
