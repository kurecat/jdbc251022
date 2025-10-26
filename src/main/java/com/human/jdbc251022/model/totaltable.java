package com.human.jdbc251022.model;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
public class totaltable {
}

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
class Dept {
    private int empno;
    private int deptno;
    private String ename;
    private String job;
    private LocalDate hiredate;
    private int mgr;

    public String toString() {
        return "사원번호: " + empno + "\n" +
                "부서번호: " + deptno + "\n" +
                "이름: " + ename + "\n" +
                "직책: " + job + "\n" +
                "입사일: " + hiredate + "\n" +
                "상사: " + mgr + "\n";
    }
}

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
class Deli {
    private int delino; // 출고번호
    private int invno; // 재고번호
    private int qty; // 출고수량
    private String loc; // 도착지
    private LocalDate delidate; // 출고일자
    private String note; // 비고

    public String toString() {
        return "출고번호: " + delino + "\n" +
                "재고번호: " + invno + "\n" +
                "출고수량: " + qty + "\n" +
                "도착지: " + loc + "\n" +
                "출고일자: " + delidate + "\n" +
                "비고: " + note + "\n";
    }
}

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
class Emp {
    private int empno;
    private int deptno;
    private String ename;
    private String job;
    private LocalDate hiredate;
    private int mgr;

    public Emp(int empno, int deptno, String ename, String job, int mgr) {
        this.empno = empno;
        this.deptno = deptno;
        this.ename = ename;
        this.job = job;
        this.mgr = mgr;
    }

    public String toString() {
        return "사원번호: " + empno + "\n" +
                "부서번호: " + deptno + "\n" +
                "이름: " + ename + "\n" +
                "직책: " + job  + "\n" +
                "입사일: " + hiredate + "\n" +
                "상사: " + mgr + "\n";

    }

}

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
class Fdcfault {
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

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
class Fdclog {
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

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
class Inv {
    private int invno; // 재고번호
    private int prodno; // 제품번호
    private int qty; // 수량
    private String location; // 위치
    private LocalDate update_date; // 수정일자

    public Inv(int invno, LocalDate update_date) {
        this.invno = invno;
        this.update_date = update_date;
    }



    public String toString() {
        return "재고번호: " + invno + "\n" +
                "제품번호: " + prodno + "\n" +
                "수량: " + qty + "\n" +
                "위치: " + location + "\n" +
                "수정일자: " + update_date + "\n";
    }
}

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
class Perf {
    private int perfno; // 실적번호
    private int procno; // 공정번호
    private int empno; // 사원번호
    private int wono; // 작업지시번호
    private String seqno; // 순번
    private int qty; // 수량
    private int qtydefect; // 불량수량
    private LocalDate perfdate; // 실적일자
    private double fara; // 불량율
    private String note; // 비고

    public String toString() {
        return "실적번호: " + perfno + "\n" +
                "공정번호: " + procno + "\n" +
                "사원번호: " + empno + "\n" +
                "작업지시번호: " + wono + "\n" +
                "순번: " + seqno + "\n" +
                "수량: " + qty + "\n" +
                "불량수량: " + qtydefect + "\n" +
                "실적일자: " + perfdate + "\n" +
                "불량율: " + fara + "\n" +
                "비고: " + note + "\n";
    }
}

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
class Proc {
    private int procno; // 공정번호
    private String seqno; // 공정순서
    private String procname; // 공정명

    public String toString() {
        return "공정번호: " + procno + "\n" +
                "공정순서: " + seqno + "\n" +
                "공정명: " + procname + "\n";
    }
}

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
class Prod {
    private int prodno; // 제품번호
    private String prodname; // 제품명
    private String unit; // 단위
    private String spce; // 사양

    public String toString() {
        return "제품번호: " + prodno + "\n" +
                "제품명: " + prodname + "\n" +
                "사양: " + spce + "\n" +
                "단위: " + unit;
    }
}

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
class Seq {
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

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
class Wo {
    private int wono; // 작업지시번호
    private int prodno; // 제품번호
    private int procno; // 공정번호
    private LocalDate orderdate; // 지시일자
    private LocalDate duedate; // 완료 예정일
    private int qty; // 수량
    private String note; // 비고

    public String toString() {
        return "작업지시번호: " + wono + "\n" +
                "제품번호: " + prodno + "\n" +
                "공정번호: " + prodno + "\n" +
                "지시일자: " + orderdate + "\n" +
                "완료 예정일: " + duedate + "\n" +
                "수량: " + qty + "\n" +
                "비고: " + note + "\n";
    }
}

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
class iDept {
    private int deptno;
    private String deptname;

    public String toString() {
        return "부서번호: " + deptno +  " / 부서이름: " + deptname + "\n";
    }
}

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
class ComWorkOrder {
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
