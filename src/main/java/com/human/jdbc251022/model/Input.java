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
        System.out.println("[1]총괄관리부 [2]생산관리부 [3]품질관리부 [4]설비관리부 [5]자재관리부 [6]물류관리부");
        System.out.print("입력 : ");
        int deptC = sc.nextInt();
        switch (deptC) {
            case 1: // 총괄관리부
                List<Dept> deptList1 = memberDao.DeptList1();
                for (Dept dept : deptList1) System.out.println(dept);
                break;
            case 2: // 생산관리부
                List<Dept> deptList2 = memberDao.DeptList2();
                for (Dept dept : deptList2) System.out.println(dept);
                break;
            case 3: // 품질관리부
                List<Dept> deptList3 = memberDao.DeptList3();
                for (Dept dept : deptList3) System.out.println(dept);
                break;
            case 4: // 설비관리부
                List<Dept> deptList4 = memberDao.DeptList4();
                for (Dept dept : deptList4) System.out.println(dept);
                break;
            case 5: // 자재관리부
                List<Dept> deptList5 = memberDao.DeptList5();
                for (Dept dept : deptList5) System.out.println(dept);
                break;
            case 6: // 물류관리부
                List<Dept> deptList6 = memberDao.DeptList6();
                for (Dept dept : deptList6) System.out.println(dept);
                break;
            default:
                System.out.println("다시 선택해 주세요");
        }
    }

    // 사원 등록
    public void regEmp() {
        System.out.println("======= 사원 등록 =======");
        System.out.print("부서번호(4자리수): ");
        int deptno = sc.nextInt();
        sc.nextLine();

        System.out.print("사원명: ");
        String ename = sc.nextLine();

        System.out.print("직책: ");
        String job = sc.nextLine();

//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        LocalDate hiredate = null;
//        while (hiredate == null) { // 올바른 형식이 입력될 때까지 반복
//            System.out.print("입사일 (DD/MM/YY): ");
//            String dateString = sc.nextLine(); // 1. 날짜를 문자열로 입력받음
//
//            try {
//                // 2. 입력받은 문자열을 LocalDate 객체로 변환(파싱)
//                hiredate = LocalDate.parse(dateString, formatter);
//            } catch (DateTimeParseException e) {
//                System.out.println("날짜 형식이 잘못되었습니다. dd-MM-yy 형식으로 다시 입력해주세요.");
//            }
//        }

        System.out.print("상사번호: ");
        int mgr = sc.nextInt();
        sc.nextLine();

        Emp emp = new Emp(deptno, ename, job, mgr);

//        boolean inSuccess =  memberDao.insertEmp(emp);
        memberDao.insertEmp(emp);
        System.out.println(5);
//        System.out.println("사원 등록 : " + (inSuccess ? "성공" : "실패"));
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
}

//    public void regMember() { // 회원 등록
//        Member member = new Member();
//        System.out.println("======= 회원 등록 =======");
//        System.out.print("이메일 : ");
//        String email = sc.nextLine();
//
//        System.out.print("비밀번호 : ");
//        String pwd = sc.nextLine();
//
//        System.out.print("이름 : ");
//        String name = sc.nextLine();
//        new Member(email, pwd, name, null);
//
//        boolean inSuccess =  memberDao.insertMember(member);
//        System.out.println("회원 가입 : " + (inSuccess ? "성공" : "실패"));
//    }

//    public void processUpdateMember(){ // 회원 정보 수정
//        System.out.println("======= 회원 정보 수정 =======");
//        System.out.println("회원님의 이메일을 입력해 주세요");
//        System.out.print("입력 : ");
//        String email = sc.nextLine();
//
//
//        System.out.println("수정하실 정보를 선택해 주세요 [1]비밀번호 [2]이름");
//        System.out.print("입력 : ");
//        int choice = sc.nextInt();
//        sc.nextLine();
//
//        Member member = new Member();
//        member.setEmail(email);
//
//        boolean isSuccess = false;
//
//        switch (choice){
//            case 1:
//                System.out.print("새 비밀번호 입력 : ");
//                String newPwd = sc.nextLine();
//                member.setPwd(newPwd);
//                isSuccess = memberDao.updatePwdMember(member);
//                break;
//            case 2:
//                System.out.print("새 이름 입력 : ");
//                String newName = sc.nextLine();
//                member.setName(newName);
//                isSuccess = memberDao.updateNameMember(member);
//                break;
//            default:
//                System.out.println("잘못된 선택입니다. 수정을 취소합니다.");
//                return;
//        }
//
//        System.out.println("회원 정보 수정 : " + (isSuccess ? "성공" : "실패"));
//    }