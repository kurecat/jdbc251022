package com.human.jdbc251022.model;

import com.human.jdbc251022.dao.MemberDao;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;
@Component

public class Input {
    private final Scanner sc;
    private final MemberDao memberDao;

    public Input(MemberDao memberDao) {
        this.memberDao = memberDao;
        this.sc = new Scanner(System.in); // Scanner는 여기서 직접 생성
    }

    public void insertTable() {
        while (true){
            System.out.println("등록할 메뉴를 고르세요");
            System.out.println("[1]부서 [2]사원 [3]제품 [4]작업실적 [5]작업지시 [6]설비 및 공정 조회 [7]재고상태 [8]출고기록 [9]종료 [0]뒤로가기");
            System.out.print("입력 : ");
            int c = sc.nextInt();
            sc.nextLine();
            switch (c) {
                case 1:
                    regDept();
                    break;
                case 2:
                    regEmp();
                    break;
                case 3:
                    regProd();
                    break;
                case 4:

                case 5:

                case 6:

                case 7:

                case 8:

                case 9:System.exit(0);
                case 0:return;
            }
        }
    }

    public void checkTable() { // 조회
        while (true) {
            System.out.println("조회할 메뉴를 고르세요");
            System.out.println("[1]부서 [2]사원 [3]제품 [4]작업실적 [5]작업지시 [6]설비 및 공정 조회 [7]재고상태 [8]출고기록 [9]종료 [0]뒤로가기");
            System.out.print("입력 : ");
            int c = sc.nextInt();
            switch (c) {
                case 1:
                    DeptTotalList();
                    break;
                case 2: // 사원
                    List<Emp> memberList = memberDao.EmpList();
                    for (Emp emp : memberList) System.out.println(emp);
                    break;
                case 3: // 제품
                    List<Prod> prodList = memberDao.ProdList();
                    for (Prod prod : prodList) System.out.println(prod);
                    break;
                case 4: // 작업실적조회
                    List<Perf> perfList = memberDao.PerfList();
                    for (Perf perf : perfList) System.out.println(perf);
                    break;
                case 5: // 작업지시조회
                    List<Wo> woList = memberDao.WoList();
                    for (Wo wo : woList) System.out.println(wo);
                    break;
                case 6: // 설비 및 공정 조회
                    SeqTotalList();
                    break;
                case 7: // 재고상태 조회
                    List<Inv> invList = memberDao.InvList();
                    for (Inv inv : invList) System.out.println(inv);
                    break;
                case 8: // 출고기록
                    List<Deli> deliList = memberDao.DeliList();
                    for (Deli deli : deliList) System.out.println(deli);
                    break;
                case 9:
                    System.exit(0);
                case 0:
                    return;
                default:
                    System.out.println("메뉴를 다시 선택해주세요.");
            }
        }
    }

    public void updateTable() {
        while (true){
            System.out.println("수정할 메뉴를 고르세요");
            System.out.println("[1]부서 [2]사원 [3]제품 [4]작업실적 [5]작업지시 [6]설비 및 공정 조회 [7]재고상태 [8]출고기록 [9]종료 [0]뒤로가기");
            System.out.print("입력 : ");
            int c = sc.nextInt();

            switch (c){
                case 1:

                case 2:

                case 3:

                case 4:

                case 5:

                case 6:

                case 7:

                case 8:

                case 9:System.exit(0);
                case 0:return;
            }
        }

    }

    // 설비조회
    public void SeqTotalList() {
        System.out.println("조회할 설비 및 공정을 선택해 주세요");
        System.out.println("[1]SEQ [2]FDCLOG [3]FDCFAULT [4]FROC");
        System.out.print("입력 : ");
        int c = sc.nextInt();

        switch (c) {
            case 1:
                List<Seq> seqList = memberDao.SeqList();
                for (Seq seq : seqList) System.out.println(seq);
                break;
            case 2:
                List<Fdclog> fdclogList = memberDao.FdclogList();
                for (Fdclog fdclog : fdclogList) System.out.println(fdclog);
                break;
            case 3:
                List<Fdcfault> fdcfaultList = memberDao.FdcfaultList();
                for (Fdcfault fdcfault : fdcfaultList) System.out.println(fdcfault);
                break;
            case 4:
                List<Froc> frocList = memberDao.FrocList();
                for (Froc froc : frocList) System.out.println(froc);
                break;
        }
    }

    // 부서조회
    public void DeptTotalList() {
        System.out.println("조회할 부서를 선택해 주세요");
        System.out.println("[1]부서전체조회 [2]총괄관리부 [3]생산관리부 [4]품질관리부 [5]설비관리부 [6]자재관리부 [7]물류관리부 [8]뒤로가기");
        System.out.print("입력 : ");
        int deptC = sc.nextInt();
        switch (deptC) {
            case 1:
                List<iDept> iDeptList = memberDao.iDeptList();
                for (iDept iDept : iDeptList) System.out.print(iDept);
                break;
            case 2:
                List<Dept> deptList1 = memberDao.DeptList1();
                for (Dept dept : deptList1) System.out.println(dept);
                break;
            case 3:
                List<Dept> deptList2 = memberDao.DeptList2();
                for (Dept dept : deptList2) System.out.println(dept);
                break;
            case 4:
                List<Dept> deptList3 = memberDao.DeptList3();
                for (Dept dept : deptList3) System.out.println(dept);
                break;
            case 5:
                List<Dept> deptList4 = memberDao.DeptList4();
                for (Dept dept : deptList4) System.out.println(dept);
                break;
            case 6:
                List<Dept> deptList5 = memberDao.DeptList5();
                for (Dept dept : deptList5) System.out.println(dept);
                break;
            case 7:
                List<Dept> deptList6 = memberDao.DeptList6();
                for (Dept dept : deptList6) System.out.println(dept);
                break;
            case 8:
                return;
            default:
                System.out.println("다시 선택해 주세요");
        }
    }

    // 사원 등록
    public void regEmp() {
        System.out.println("======= 사원 등록 =======");
        System.out.print("사원번호(4자리수): ");
        int empno = sc.nextInt();

        System.out.print("부서번호(4자리수): ");
        int deptno = sc.nextInt();
        sc.nextLine();

        System.out.print("사원명: ");
        String ename = sc.nextLine();

        System.out.print("직책: ");
        String job = sc.nextLine();

        System.out.print("상사번호: ");
        int mgr = sc.nextInt();
        sc.nextLine();

        Emp emp = new Emp(empno ,deptno, ename, job, mgr);

        boolean inSuccess =  memberDao.insertEmp(emp);
        System.out.println("사원 등록 : " + (inSuccess ? "성공" : "실패"));
    }

    // 제품 등록
    public void regProd() {
        System.out.println("======= 제품 등록 =======");
        System.out.print("제품번호(3자리수): ");
        int prodno = sc.nextInt();
        sc.nextLine();

        System.out.print("제품명: ");
        String prodname = sc.nextLine();

        System.out.print("단위(개 or T): ");
        String unit = sc.nextLine();

        System.out.print("사양(완제품 or 자재): ");
        String spce = sc.nextLine();


        Prod prod = new Prod(prodno, prodname, spce, unit);

        boolean inSuccess =  memberDao.insertProd(prod);
        System.out.println("제품 등록 : " + (inSuccess ? "성공" : "실패"));
    }

    // 부서 등록
    public void regDept() {
        System.out.println("======= 부서 등록 =======");
        System.out.print("부서번호(4자리수): ");
        int deptno = sc.nextInt();
        sc.nextLine();

        System.out.print("부서이름: ");
        String deptname = sc.nextLine();

        iDept idetp = new iDept(deptno, deptname);
        boolean inSuccess = memberDao.insertDept(idetp);
        System.out.println("제품 등록 : " + (inSuccess ? "성공" : "실패"));
    }

    public void regPerf() {
        System.out.println("======= 작업실적 등록 =======");
        System.out.print("부서번호(4자리수): ");
    }
}