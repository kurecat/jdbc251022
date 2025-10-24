package com.human.jdbc251022.model;

import com.human.jdbc251022.dao.MemberDao;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
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
            System.out.println("[1]부서 [2]사원 [3]제품 [4]작업실적 [5]작업지시 [6]설비 및 공정 [7]재고상태 [8]출고기록 [9]종료 [0]뒤로가기");
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
                    regPerf();
                    break;
                case 5:
                    regWo();
                    break;
                case 6:
                    System.out.println("등록할 메뉴를 선택해 주세요");
                    System.out.println("[1]SEQ 설비 상세 등록 [2]PROC 설비 공정 등록 [3]뒤로가기");
                    System.out.print("입력: ");
                    int Seqc = sc.nextInt();

                    switch (Seqc) {
                        case 1:
                            regSeq();
                            break;
                        case 2:
                            regProc();
                            break;
                        case 3:return;
                        default:
                    }
                case 7:
                    regInv();
                    break;
                case 8:
                    regDeli();
                    break;
                case 9:System.exit(0);
                case 0:return;
            }
        }
    }

    public void checkTable() { // 조회
        while (true) {
            System.out.println("조회할 메뉴를 고르세요");
            System.out.println("[1]부서 [2]사원 [3]제품 [4]작업실적 [5]작업지시 [6]설비 및 공정 [7]재고상태 [8]출고기록 [9]종료 [0]뒤로가기");
            System.out.print("입력 : ");
            int c = sc.nextInt();
            switch (c) {
                case 1:
                    List<iDept> iDeptList = memberDao.iDeptList();
                    for (iDept iDept : iDeptList) System.out.print(iDept);
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
            System.out.println("[1]부서 [2]사원 [3]제품 [4]작업실적 [5]작업지시 [6]설비 및 공정 [7]재고상태 [8]출고기록 [9]종료 [0]뒤로가기");
            System.out.print("입력 : ");
            int c = sc.nextInt();

            switch (c){
                case 1:
                    upDept();
                    break;
                case 2:
                    upEmp();
                    break;
                case 3:
                    upProd();
                    break;
                case 4:

                case 5:

                case 6:

                case 7:

                case 8:
                    upDELI();
                    break;

                case 9:System.exit(0);
                case 0:return;
            }
        }

    }

    // 설비조회
    public void SeqTotalList() {
        System.out.println("조회할 설비 및 공정을 선택해 주세요");
        System.out.println("[1]설비상세(SEQ) [2]설비로그(FDCLOG) [3]설비이상감지이력(FDCFAULT) [4](설비공정)PROC");
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
                List<Proc> procList = memberDao.FrocList();
                for (Proc proc : procList) System.out.println(proc);
                break;
        }
    }

    public void cwr() {
        List<ComWorkOrder> comWorkOrders = memberDao.findComWorkOrder();
        for (ComWorkOrder comWorkOrder : comWorkOrders) System.out.println(comWorkOrder);
    }



    // - - - - - - - - - - - - - - - 등 록 - - - - - - - - - - - - - - -

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

        System.out.print("부서명: ");
        String deptname = sc.nextLine();

        iDept idetp = new iDept(deptno, deptname);
        boolean inSuccess = memberDao.insertDept(idetp);
        System.out.println("제품 등록 : " + (inSuccess ? "성공" : "실패"));
    }

    // 실적 등록
    public void regPerf() {
        System.out.println("======= 작업 실적 등록 =======");
        System.out.print("실적번호: ");
        int perfno = sc.nextInt();
        sc.nextLine(); // nextInt() 뒤의 엔터(\n) 소비

        System.out.print("공정번호: ");
        int procno = sc.nextInt();
        sc.nextLine(); // nextInt() 뒤의 엔터(\n) 소비

        System.out.print("사원번호: ");
        int empno = sc.nextInt();
        sc.nextLine(); // nextInt() 뒤의 엔터(\n) 소비

        System.out.print("작업지시번호: ");
        int wono = sc.nextInt();
        sc.nextLine(); // <-- [수정 1] '순번' 입력을 위해 엔터 소비

        System.out.print("순번: ");
        String seqno = sc.nextLine(); // 이제 정상 입력됨

        System.out.print("수량: ");
        int qty = sc.nextInt();
        sc.nextLine(); // nextInt() 뒤의 엔터(\n) 소비

        System.out.print("불량수량: ");
        int qtydefect = sc.nextInt();
        sc.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy");
        System.out.print("실적일자 (DD-MM-YY): ");
        String dateInput = sc.nextLine();
        LocalDate perfdate = LocalDate.parse(dateInput, formatter);

        System.out.print("불량율: ");
        double fara = sc.nextDouble();
        sc.nextLine(); // <-- [수정 5] '비고' 입력을 위해 엔터 소비

        System.out.print("비고: ");
        String note = sc.nextLine(); // 이제 정상 입력됨

        Perf perf = new Perf(perfno, procno, empno, wono, seqno, qty, qtydefect, perfdate, fara, note);

        boolean inSuccess = memberDao.insertPerf(perf);
        System.out.println("작업실적등록 : " + (inSuccess ? "성공" : "실패"));
    }

    // 설비 및 공정 등록 - [1] SEQ 설비상세등록
    public void regSeq() {
        System.out.println("======= 설비 상세 등록 =======");
        System.out.print("설비번호: ");
        String seqno = sc.nextLine();
        sc.nextLine();

        System.out.print("설비명: ");
        String seqname = sc.nextLine();
        sc.nextLine();

        System.out.print("공정순서: ");
        String seqor = sc.nextLine();
        sc.nextLine();

        System.out.print("비고: ");
        String note = sc.nextLine();

        Seq seq = new Seq(seqno, seqname, seqor, note);

        boolean inSuccess = memberDao.insertSeq(seq);
        System.out.println("설비상세등록 : " + (inSuccess ? "성공" : "실패"));
    }

    // 설비 및 공정 등록 - [4]PROC 설비 공정 등록
    public void regProc() {
        System.out.println("======= 설비 공정 등록 =======");
        System.out.print("공정번호: ");
        int procno = sc.nextInt();

        System.out.print("공정순서: ");
        String seqno = sc.nextLine();
        sc.nextLine();

        System.out.print("공정명: ");
        String procname = sc.nextLine();
        sc.nextLine();

        Proc proc = new Proc(procno, seqno, procname);

        boolean inSuccess = memberDao.insertProc(proc);
        System.out.println("설비 공정 등록 : " + (inSuccess ? "성공" : "실패"));
    }

    // 재고상태등록
    public void regInv() {
        System.out.println("======= 재고상태 등록 =======");
        System.out.print("재고번호: ");
        int invno = sc.nextInt();

        System.out.print("재고번호: ");
        int prodno = sc.nextInt();

        System.out.print("수량: ");
        int qty = sc.nextInt();

        System.out.print("위치: ");
        String location = sc.nextLine();
        sc.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
        System.out.print("수정일자 (DD/MM/YY): ");
        String dateInput = sc.nextLine();
        LocalDate update_date = LocalDate.parse(dateInput, formatter);

        Inv inv = new Inv(invno, prodno, qty, location, update_date);

        boolean inSuccess = memberDao.insertInv(inv);
        System.out.println("재고상태 등록 : " + (inSuccess ? "성공" : "실패"));
    }

    // 작업지시등록
    public void regWo() {
        System.out.println("======= 작업 지시 등록 =======");
        System.out.print("작업지시번호: ");
        int wono = sc.nextInt();
        sc.nextLine();

        System.out.print("제품번호: ");
        int prodno = sc.nextInt();
        sc.nextLine();

        System.out.print("공정번호: ");
        int procno = sc.nextInt();
        sc.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
        System.out.print("지시일자 (DD/MM/YY): ");
        String dateInput = sc.nextLine();
        LocalDate orderdate = LocalDate.parse(dateInput, formatter);

        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd/MM/yy");
        System.out.print("완료일자 (DD/MM/YY): ");
        String dateInput1 = sc.nextLine();
        LocalDate duedate = LocalDate.parse(dateInput1, formatter1);

        System.out.print("수량: ");
        int qty = sc.nextInt();
        sc.nextLine();

        System.out.print("비고: ");
        String note = sc.nextLine();

        Wo wo = new Wo(wono, prodno, procno, orderdate, duedate, qty, note);

        boolean inSuccess = memberDao.insertWo(wo);
        System.out.println("작업지시등록 : " + (inSuccess ? "성공" : "실패"));
    }

    // 출고기록등록
    public void regDeli() {
        System.out.println("======= 출고기록 등록 =======");
        System.out.print("출고번호: ");
        int delino = sc.nextInt();

        System.out.print("재고번호: ");
        int invno = sc.nextInt();

        System.out.print("출고수량: ");
        int deliqty = sc.nextInt();
        sc.nextLine();

        System.out.print("도착지: ");
        String loc = sc.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
        System.out.print("출고일자 (DD/MM/YY): ");
        String dateInput = sc.nextLine();
        LocalDate delidate = LocalDate.parse(dateInput, formatter);

        System.out.print("메모: ");
        String note = sc.nextLine();

        Deli deli = new Deli(delino, invno, deliqty, loc, delidate, note);
        boolean inSuccess = memberDao.insertDeli(deli);
        System.out.println("출고기록등록 : " + (inSuccess ? "성공" : "실패"));
    }


    // - - - - - - - - - - - - - - - 수 정 - - - - - - - - - - - - - - -

    // 부서정보 수정
    public void upDept(){
        System.out.println("======= 부서정보 수정 =======");
        System.out.println("수정할 부서의 번호를 입력하세요.");
        System.out.print("입력 : ");
        int c = sc.nextInt();
        sc.nextLine();

        iDept idept = new iDept();
        idept.setDeptno(c);

        boolean isSuccess = false;
        System.out.print("새로운 부서명을 입력해주세요: ");
        String newDeptname = sc.nextLine();
        idept.setDeptname(newDeptname);
        isSuccess = memberDao.updateDept(idept);
        System.out.println("부서 정보 수정 : " + (isSuccess ? "성공" : "실패"));
    }

    // 사원 수정 - - - - - ★ - - - - - ★ - - - - - ★ - - - - - ★ - - - - - 오류 해결 필요!!
    public void upEmp(){
        System.out.println("======= 사원정보 수정 =======");
        System.out.println("수정할 사원의 번호를 입력하세요.");
        System.out.print("입력 : ");
        int c = sc.nextInt();
        sc.nextLine();

        Emp emp = new Emp();
        emp.setEmpno(c);

        System.out.print("새로운 사원 이름을 입력해주세요: ");
        String newename = sc.nextLine();
        emp.setEname(newename);

        System.out.print("새로운 직책을 입력해주세요: ");
        String newjob = sc.nextLine();
        emp.setJob(newjob);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
        System.out.print("새로운 입사일을 입력해주세요(DD/MM/YY): ");
        String dateInput = sc.nextLine();
        LocalDate update_date = LocalDate.parse(dateInput, formatter);
        emp.setHiredate(update_date);

        boolean isSuccess = memberDao.updateEmp(emp);
        System.out.println("사원 정보 수정 : " + (isSuccess ? "성공" : "실패"));
    }

    // 제품 수정 - - - - - ★ - - - - - ★ - - - - - ★ - - - - - ★ - - - - -
    // 오류 !! SPCE(완제품 or 자재) UNIT(개 or T) 또 거꾸로 나오는 이슈 해결 필요
    public void upProd(){
        System.out.println("======= 제품정보 수정 =======");
        System.out.println("수정할 제품의 번호를 입력하세요.");
        System.out.print("입력 : ");
        int c = sc.nextInt();
        sc.nextLine();

        Prod prod = new Prod();
        prod.setProdno(c);

        System.out.print("새로운 제품 이름을 입력해주세요: ");
        String neweprodname = sc.nextLine();
        prod.setProdname(neweprodname);

        System.out.print("새로운 제품 사양(완제품 or 자재)을 입력해주세요: ");
        String newspce = sc.nextLine();
        prod.setSpce(newspce);

        System.out.print("새로운 제품 단위(개 or T)를 입력해주세요: ");
        String newunit = sc.nextLine();
        prod.setSpce(newunit);

        boolean isSuccess = memberDao.updateProd(prod);
        System.out.println("사원 정보 수정 : " + (isSuccess ? "성공" : "실패"));
    }


    // 출고정보 수정
    public void upDELI(){
        System.out.println("======= 출고정보 수정 =======");
        System.out.println("수정할 출고의 번호를 입력하세요.");
        System.out.print("입력 : ");
        int c = sc.nextInt();
        sc.nextLine();

        Deli deli = new Deli();
        deli.setDelino(c);

        System.out.print("새로운 수량을 입력해주세요: ");
        int newqty = sc.nextInt();
        sc.nextLine();
        deli.setQty(newqty);

        System.out.print("새로운 도착지를 입력해주세요: ");
        String newloc = sc.nextLine();
        deli.setLoc(newloc);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
        System.out.print("새로운 출고일자를 입력해주세요(DD/MM/YY): ");
        String dateInput = sc.nextLine();
        LocalDate update_date = LocalDate.parse(dateInput, formatter);
        deli.setDelidate(update_date);

        System.out.print("새로운 비고를 입력해주세요: ");
        String note = sc.nextLine();
        deli.setNote(note);

        boolean isSuccess = memberDao.updateDeli(deli);
        System.out.println("출고 정보 수정 : " + (isSuccess ? "성공" : "실패"));
    }
}