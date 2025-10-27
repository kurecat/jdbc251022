package com.human.jdbc251022.model;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Component
@Slf4j
public class TotalInput {
    private final JdbcTemplate jdbcTemplate;
    private final Scanner sc = new Scanner(System.in);

    public TotalInput(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 부서, 사원 Input
    public void empDeptTotalInput() {
        while (true) {
            System.out.println("[1]사원관리 [2]부서관리 [0]뒤로가기");
            System.out.print("입럭 : ");
            int empdeptc = sc.nextInt();
            sc.nextLine();
            switch (empdeptc) {
                case 1:
                    empMenu();
                    break;
                case 2:
                    deptMenu();
                    break;
                case 0:
                    return;
            }
        }
    }

    private void empMenu() {
        while (true) {
            System.out.println("[1]사원 정보 조회 [2]사원 정보 등록 [3]사원 정보 수정 [4]사원 삭제 [9]종료 [0]뒤로가기");
            System.out.print("입럭 : ");
            int empc = sc.nextInt();
            sc.nextLine();

            switch (empc) {
                case 1:
                    empc();
                    break;
                case 2:
                    regEmp();
                    break;
                case 3:
                    upEmp();
                    break;
                case 4:
                    // TODO: 4. 사원 삭제 로직 구현
                    System.out.println("사원 삭제 기능 (구현 필요)");
                    break; // ⬅️ 9번으로 넘어가지 않게 break 추가 (기존 코드 버그 수정)
                case 9:
                    System.out.println("프로그램을 종료합니다.");
                    System.exit(0); // 프로그램 즉시 종료
                    break;
                case 0:
                    return; // "뒤로가기" (handleEmployeeMenu 메서드를 종료하고 메인 메뉴로 복귀)
            }
        }
    }

    private void deptMenu() {
        while (true) {
            System.out.println("[1]부서 정보 조회 [2]부서 정보 등록 [3]부서 정보 수정 [0]뒤로가기");
            System.out.print("입럭 : ");
            int deptc = sc.nextInt();
            sc.nextLine();

            switch (deptc) {
                case 1:
                    deptc();
                    break;
                case 2:
                    regDept();
                    break;
                case 3:
                    upDept();
                    break;
                case 0:
                    return;
            }
        }
    }

    //====================사원============================
    // 사원 조회
    public void empc() {
        List<Emp> memberList = EmpList();
        for (Emp emp : memberList) System.out.println(emp);
    }

    public List<Emp> EmpList() {
        String query = "SELECT * FROM MES_EMP_TABLE";
        return jdbcTemplate.query(query, new EmpRowMapper());
    }

    private static class EmpRowMapper implements RowMapper<Emp> {
        @Override
        public Emp mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Emp(
                    rs.getInt("EMPNO"),
                    rs.getInt("DEPTNO"),
                    rs.getString("ENAME"),
                    rs.getString("JOB"),
                    rs.getTimestamp("HIREDATE").toLocalDateTime().toLocalDate(),
                    rs.getInt("MGR")
            );
        }
    }

    // 사원 수정 input
    public void upEmp() {
        System.out.println("======= 사원정보 수정 =======");
        System.out.println("수정할 사원의 번호를 입력하세요.");
        System.out.print("입력 : ");
        int c = sc.nextInt();
        sc.nextLine();

        Emp emp = new Emp();
        emp.setEmpno(c);

        System.out.print("변경할 사원 이름을 입력해주세요: ");
        String newename = sc.nextLine();
        emp.setEname(newename);

        System.out.print("변경할 직책을 입력해주세요: ");
        String newjob = sc.nextLine();
        emp.setJob(newjob);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
        System.out.print("변경할 입사일을 입력해주세요(DD/MM/YY): ");
        String dateInput = sc.nextLine();
        LocalDate update_date = LocalDate.parse(dateInput, formatter);
        emp.setHiredate(update_date);

        boolean isSuccess = updateEmp(emp);
        System.out.println("사원 정보 수정 : " + (isSuccess ? "성공" : "실패"));
    }

    // 사원 수정 코드
    public boolean updateEmp(Emp emp) {
        String sql = "UPDATE MES_EMP_TABLE SET " +
                "ename = ?, job = ?, hiredate = ?" +
                "WHERE empno = ?";
        int result = 0;
        try {
            result = jdbcTemplate.update(sql,
                    emp.getEname(),     // 2. (for ename = ?)
                    emp.getJob(),       // 3. (for job = ?)
                    emp.getHiredate(),  // 4. (for hiredate = ?)
                    emp.getEmpno()      // 6. (for WHERE empno = ?)
            );
        } catch (Exception e) {
            log.error("사원정보 수정 실패 : {}", e.getMessage());
        }
        return result > 0;
    }

    // 사원 등록 input
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

        Emp emp = new Emp(empno, deptno, ename, job, mgr);

        boolean inSuccess = insertEmp(emp);
        System.out.println("사원 등록 : " + (inSuccess ? "성공" : "실패"));
    }

    // 등록 코드
    public boolean insertEmp(Emp emp) {
        int result = 0;
        String query = "INSERT INTO MES_EMP_TABLE(EMPNO, DEPTNO, ENAME, \"JOB\", HIREDATE, MGR) VALUES(?, ?, ?, ?, SYSDATE, ?)";
        try {
            result = jdbcTemplate.update(query, emp.getEmpno(), emp.getDeptno(), emp.getEmpno(), emp.getJob(), emp.getMgr());
        } catch (Exception e) {
            log.error("사원 정보 등록 실패 : {}", e.getMessage());
        }
        return result > 0;
    }
    //===================================================

    //====================부서============================
    // 부서 조회
    public void deptc() {
        List<iDept> iDeptList = iDeptList();
        for (iDept iDept : iDeptList) System.out.print(iDept);
    }

    public List<iDept> iDeptList() {
        String query = "SELECT * FROM MES_DEPT_TABLE";
        return jdbcTemplate.query(query, new ideptRowMapper());
    }

    private static class ideptRowMapper implements RowMapper<iDept> {
        @Override
        public iDept mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new iDept(
                    rs.getInt("DEPTNO"),
                    rs.getString("DEPTNAME")
            );
        }
    }

    // 부서 수정 input
    public void upDept() {
        System.out.println("======= 부서정보 수정 =======");
        System.out.println("수정할 부서의 번호를 입력하세요.");
        System.out.print("입력 : ");
        int c = sc.nextInt();
        sc.nextLine();

        iDept idept = new iDept();
        idept.setDeptno(c);

        boolean isSuccess = false;
        System.out.print("새로운 부서이름을 입력해주세요: ");
        String newDeptname = sc.nextLine();
        idept.setDeptname(newDeptname);
        isSuccess = updateDept(idept);
        System.out.println("부서 정보 수정 : " + (isSuccess ? "성공" : "실패"));
    }

    //부서 수정 코드
    public boolean updateDept(iDept idept) {
        String sql = "UPDATE MES_DEPT_TABLE SET deptname = ? WHERE DEPTNO = ?";
        int result = 0;

        try {
            result = jdbcTemplate.update(sql,
                    idept.getDeptname(),
                    idept.getDeptno()
            );
        } catch (Exception e) {
            log.error("부서정보 수정 실패 : {}", e.getMessage());
        }
        return result > 0;
    }

    // 부서 등록 input
    public void regDept() {
        System.out.println("======= 부서 등록 =======");
        System.out.print("부서번호(4자리수): ");
        int deptno = sc.nextInt();
        sc.nextLine();

        System.out.print("부서이름: ");
        String deptname = sc.nextLine();

        iDept idetp = new iDept(deptno, deptname);
        boolean inSuccess = insertDept(idetp);
        System.out.println("제품 등록 : " + (inSuccess ? "성공" : "실패"));
    }

    // 부서 등록 코드
    public boolean insertDept(iDept idept) {
        int result = 0;
        String query = "INSERT INTO MES_DEPT_TABLE(DEPTNO, DEPTNAME) VALUES(?, ?)";
        try {
            result = jdbcTemplate.update(query, idept.getDeptno(), idept.getDeptname());
        } catch (Exception e) {
            log.error("부서 정보 등록 실패");
        }
        return result > 0;
    }
    //===================================================

    // 제품, 재고 Total Input
    public void prodInvTotalInput() {
        while (true) {
            System.out.println("[1]제품 관리 [2]재고 관리 [0]뒤로가기");
            System.out.print("입럭 : ");
            int prodInvC = sc.nextInt();
            sc.nextLine();

            switch (prodInvC) {
                case 1:
                    prodMenu();
                    break;
                case 2:
                    invMenu();
                    break;
                case 0:
                    return;
            }
        }
    }

    private void prodMenu() {
        while (true) {
            System.out.println("[1]제품 정보 조회 [2]제품 정보 등록 [3]제품 정보 수정 [9]종료 [0]뒤로가기");
            System.out.print("입럭 : ");
            int prodc = sc.nextInt(); //
            sc.nextLine();

            switch (prodc) {
                case 1:
                    prodc();
                    break;
                case 2:
                    regProd();
                    break;
                case 3:
                    upProd();
                    break;
                case 9:
                    System.out.println("프로그램을 종료합니다.");
                    System.exit(0);
                    break;
                case 0:
                    return;
            }
        }
    }

    private void invMenu() {
        while (true) {
            System.out.println("[1]재고 정보 조회 [2]재고 정보 수정 [9]종료 [0]뒤로가기");
            System.out.print("입럭 : ");
            int invc = sc.nextInt();
            sc.nextLine();

            switch (invc) {
                case 1:
                    invc();
                    break;
                case 2:
                    upInv();
                    break;
                case 9:
                    System.exit(0);
                case 0:
                    return; // "뒤로가기" (handleInventoryMenu 메서드를 종료하고 메인 메뉴로 복귀)
            }
        }
    }

    //====================제품============================
    public void prodc() {
        List<Prod> prodList = ProdList();
        for (Prod prod : prodList) System.out.print(prod);
    }

    public List<Prod> ProdList() {
        String query = "SELECT * FROM MES_PROD_TABLE";
        return jdbcTemplate.query(query, new ProdRowMapper());
    }

    private static class ProdRowMapper implements RowMapper<Prod> {
        @Override
        public Prod mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Prod(
                    rs.getInt("PRODNO"),
                    rs.getString("PRODNAME"),
                    rs.getString("SPCE"),
                    rs.getString("UNIT")
            );
        }
    }

    // 제품 등록 코드
    public boolean insertProd(Prod prod) {
        int result = 0;
        String query = "INSERT INTO MES_PROD_TABLE(PRODNO, PRODNAME, SPCE, UNIT) VALUES(?, ?, ?, ?)";
        try {
            result = jdbcTemplate.update(query, prod.getProdno(), prod.getProdname(), prod.getSpce(), prod.getUnit());
        } catch (Exception e) {
            log.error("회원 정보 추가 실패 : {}", e.getMessage());
        }
        return result > 0;
    }

    // 제품 등록 input
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

        boolean inSuccess = insertProd(prod);
        System.out.println("제품 등록 : " + (inSuccess ? "성공" : "실패"));
    }

    // 제품 수정 코드
    public boolean updateProd(Prod prod) {
        String sql = "UPDATE MES_PROD_TABLE SET PRODNAME = ?, SPCE = ?, UNIT = ? WHERE PRODNO = ?";

        int result = 0;
        try {
            result = jdbcTemplate.update(sql,
                    prod.getProdname(),
                    prod.getSpce(),
                    prod.getUnit(),
                    prod.getProdno()
            );
        } catch (Exception e) {
            log.error("제품정보 수정 실패 : {}", e.getMessage());
        }
        return result > 0;
    }

    // 제품 수정 input
    public void upProd() {
        System.out.println("======= 제품정보 수정 =======");
        System.out.println("수정할 제품의 번호를 입력하세요.");
        System.out.print("입력 : ");
        int c = sc.nextInt();
        sc.nextLine();

        Prod prod = new Prod();
        prod.setProdno(c);

        System.out.print("변경할 제품 이름을 입력해주세요: ");
        String newprodname = sc.nextLine();
        prod.setProdname(newprodname);

        System.out.print("변경할 제품 사양(완제품 or 자재)을 입력해주세요: ");
        String newspce = sc.nextLine();
        prod.setSpce(newspce);

        System.out.print("변경할 제품 단위(개 or T)를 입력해주세요: ");
        String newunit = sc.nextLine();
        prod.setUnit(newunit);

        boolean isSuccess = updateProd(prod);
        System.out.println("제품 정보 수정 : " + (isSuccess ? "성공" : "실패"));
    }
    //===================================================

    // 재고상태 조회
    public void invc() {
        List<Inv> invList = InvList();
        for (Inv inv : invList) System.out.println(inv);
    }

    public List<Inv> InvList() {
        String query = "SELECT * FROM MES_INV_TABLE";
        return jdbcTemplate.query(query, new InvRowMapper());
    }

    private static class InvRowMapper implements RowMapper<Inv> {
        @Override
        public Inv mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Inv(
                    rs.getInt("INVNO"),
                    rs.getInt("PRODNO"),
                    rs.getInt("QTY"),
                    rs.getString("LOCATION"),
                    rs.getTimestamp("UPDATE_DATE").toLocalDateTime().toLocalDate()
            );
        }
    }

    // 재고상태 등록 코드
    public boolean insertInv(Inv inv) {
        int result = 0;
        String query = "INSERT INTO MES_INV_TABLE(INVNO, PRODNO, QTY, LOCATION, UPDATE_DATE) VALUES(?, ?, ?, ?, ?)";
        try {
            result = jdbcTemplate.update(query, inv.getInvno(), inv.getProdno(), inv.getQty(), inv.getLocation(), inv.getUpdate_date());
        } catch (Exception e) {
            log.error("재고상태 등록 실패 : {}", e.getMessage());
        }
        return result > 0;
    }

    // 재고상태 등록 input
    public void regInv() {
        System.out.println("======= 제품 입고 =======");
        System.out.print("재고번호: ");
        int invno = sc.nextInt();

        System.out.print("제펌번호: ");
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

        boolean inSuccess = insertInv(inv);
        System.out.println("재고상태 등록 : " + (inSuccess ? "성공" : "실패"));
    }

    // 재고상태 수정 코드
    public boolean updateInv(Inv inv) {
        String sql = "UPDATE MES_INV_TABLE SET QTY = ?, LOCATION = ? WHERE INVNO = ?";

        int result = 0;
        try {
            result = jdbcTemplate.update(sql,
                    inv.getQty(),
                    inv.getLocation()
            );
        } catch (Exception e) {
            log.error("재고상태 수정 실패 : {}", e.getMessage());
        }
        return result > 0;
    }

    // 재고상태 수정 input
    public void upInv() {
        System.out.println("======= 재고정보 수정 =======");
        System.out.println("수정할 재고의 번호를 입력하세요.");
        System.out.print("입력 : ");
        int c = sc.nextInt();
        sc.nextLine();

        Inv inv = new Inv();
        inv.setInvno(c);

        System.out.print("변경할 재고의 수량을 입력하세요: ");
        int newQty = sc.nextInt();
        inv.setQty(newQty);
        sc.nextLine();

        System.out.print("변경할 위치를 입력하세요: ");
        String newloc = sc.nextLine();
        inv.setLocation(newloc);

        boolean isSuccess = updateInv(inv);
        System.out.println("제품 정보 수정 : " + (isSuccess ? "성공" : "실패"));
    }


    //====================작업관리===========================
    public void woPerfTotalInput() {
        while (true) {
            System.out.println("[1]작업 지시 관리 [2]작업 실적 관리 [9]종료 [0]뒤로가기");
            System.out.print("입럭 : ");
            int prodInvC = sc.nextInt();
            sc.nextLine();

            switch (prodInvC) {
                case 1:
                    woMenu();
                    break;
                case 2:
                    perfMenu();
                    break;
                case 9:
                    System.out.println("프로그램을 종료합니다.");
                    System.exit(0);
                case 0:
                    return;
            }
        }
    }

    private void woMenu() {
        while (true) {
            System.out.println("[1]작업 지시 조회 [2]작업 지시 등록 [3]작업 지시 수정 [4]작업 지시 삭제 [9]종료 [0]뒤로가기");
            System.out.print("입럭 : ");
            int woc = sc.nextInt(); //
            sc.nextLine();

            switch (woc) {
                case 1:
                    woc();
                    break;
                case 2:
                    regWo();
                    break;
                case 3:
                    upWo();
                    break;
                case 9:
                    System.out.println("프로그램을 종료합니다.");
                    System.exit(0);
                    break;
                case 0:
                    return;
            }
        }
    }

    private void perfMenu() {
        while (true) {
            System.out.println("[1]작업 실적 조회 [2]작업 실적 등록 [3]작업 실적 수정 [9]종료 [0]뒤로가기");
            System.out.print("입럭 : ");
            int perfc = sc.nextInt();
            sc.nextLine();

            switch (perfc) {
                case 1:
                    perfc();
                    break;
                case 2:
                    regPerf();
                    break;
                case 3:
                    upPerf();
                    break;
                case 9:
                    System.out.println("프로그램을 종료합니다.");
                    System.exit(0);
                case 0:
                    return;
            }
        }
    }

    // 작업 지시 조회
    public void woc() {
        List<Wo> woList = WoList();
        for (Wo wo : woList) System.out.println(wo);
    }

    public List<Wo> WoList() {
        String query = "SELECT * FROM MES_WO_TABLE";
        return jdbcTemplate.query(query, new WoRowMapper());
    }

    private static class WoRowMapper implements RowMapper<Wo> {
        @Override
        public Wo mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Wo(
                    rs.getInt("WONO"),
                    rs.getInt("PRODNO"),
                    rs.getInt("PROCNO"),
                    rs.getTimestamp("ORDERDATE").toLocalDateTime().toLocalDate(),
                    rs.getTimestamp("DUEDATE").toLocalDateTime().toLocalDate(),
                    rs.getInt("QTY"),
                    rs.getString("NOTE")
            );
        }
    }

    // 작업 지시 등록 코드
    public boolean insertWo(Wo wo) {
        int result = 0;
        String query = "INSERT INTO MES_WO_TABLE(WONO, PRODNO, PROCNO, ORDERDATE, DUEDATE, QTY, NOTE) VALUES(?, ?, ?, ?, ?, ?, ?)";
        try {
            result = jdbcTemplate.update(query, wo.getWono(), wo.getProdno(), wo.getProcno(), wo.getOrderdate(), wo.getDuedate(), wo.getQty(), wo.getNote());
        } catch (Exception e) {
            log.error("작업 지시 등록 실패 : {}", e.getMessage());
        }
        return result > 0;
    }

    // 작업 지시 등록 input
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

        boolean inSuccess = insertWo(wo);
        System.out.println("작업지시등록 : " + (inSuccess ? "성공" : "실패"));
    }

    // 작업 지시 수정 코드
    public boolean updateWo(Wo wo) {
        String sql = "UPDATE MES_WO_TABLE SET orderdate = ?, duedate = ?, qty = ?, note = ? WHERE wono = ?";
        int result = 0;
        try {
            result = jdbcTemplate.update(sql,
                    wo.getOrderdate(),
                    wo.getDuedate(),
                    wo.getQty(),
                    wo.getNote(),
                    wo.getWono());
        } catch (Exception e) {
            log.error("작업지시 수정 실패 : {}", e.getMessage());
        }
        return result > 0;
    }

    // 작업 지시 수정 input
    public void upWo() {
        System.out.println("======= 작업지시 수정 =======");
        System.out.println("수정할 작업지시의 번호를 입력하세요.");
        System.out.print("입력 : ");
        int c = sc.nextInt();
        sc.nextLine();

        Wo wo = new Wo();
        wo.setWono(c);

        DateTimeFormatter orderDateFormatter = DateTimeFormatter.ofPattern("dd/MM/yy");
        System.out.print("변경할 작업지시의 지시일자를 입력하세요(DD/MM/YY): ");
        String order = sc.nextLine();
        LocalDate orderDate = LocalDate.parse(order, orderDateFormatter);
        wo.setOrderdate(orderDate);

        DateTimeFormatter dueDateFormatter = DateTimeFormatter.ofPattern("dd/MM/yy");
        System.out.print("변경할 작업지시의 완료일자를 입력하세요(DD/MM/YY): ");
        String due = sc.nextLine();
        LocalDate dueDate = LocalDate.parse(due, dueDateFormatter);
        wo.setOrderdate(dueDate);

        System.out.print("변경할 작업지시의 수량을 입력하세요: ");
        int newQty = sc.nextInt();
        wo.setQty(newQty);
        sc.nextLine();

        System.out.println("변경할 작업지시의 메모를 입력하세요: ");
        String newNote = sc.nextLine();
        wo.setNote(newNote);

        boolean isSuccess = updateWo(wo);
        System.out.println("제품 정보 수정 : " + (isSuccess ? "성공" : "실패"));
    }


    // 작업 실적 조회
    public void perfc() {
        List<Perf> perfList = PerfList();
        for (Perf perf : perfList) System.out.println(perf);
    }

    public List<Perf> PerfList() {
        String query = "SELECT * FROM MES_PERF_TABLE";
        return jdbcTemplate.query(query, new PerfRowMapper());
    }

    private static class PerfRowMapper implements RowMapper<Perf> {
        @Override
        public Perf mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Perf(
                    rs.getInt("PERFNO"),
                    rs.getInt("PROCNO"),
                    rs.getInt("EMPNO"),
                    rs.getInt("WONO"),
                    rs.getString("SEQNO"),
                    rs.getInt("QTY"),
                    rs.getInt("QTYDEFECT"),
                    rs.getTimestamp("PERFDATE").toLocalDateTime().toLocalDate(),
                    rs.getDouble("FARA"),
                    rs.getString("NOTE")
            );
        }
    }

    // 작업 실적 등록 코드
    public boolean insertPerf(Perf perf) {
        int result = 0;
        String query = "INSERT INTO MES_PERF_TABLE(PERFNO, PROCNO, EMPNO, WONO, SEQNO, QTY, QTYDEFECT, PERFDATE, FARA, NOTE) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            result = jdbcTemplate.update(query, perf.getPerfno(), perf.getProcno(), perf.getEmpno(), perf.getWono(), perf.getSeqno(), perf.getQty(), perf.getQtydefect(), perf.getPerfdate(), perf.getFara(), perf.getNote());
        } catch (Exception e) {
            log.error("작업 실적 등록 실패 : {}", e.getMessage());
        }
        return result > 0;
    }

    // 작업 실적 등록 input
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

        boolean inSuccess = insertPerf(perf);
        System.out.println("작업실적등록 : " + (inSuccess ? "성공" : "실패"));
    }

    // 작업 실적 수정 코드
    public boolean updatePerf(Perf perf) {
        String sql = "UPDATE MES_PERF_TABLE SET QTY = ?, QTYDEFECT = ?, PERFDATE = ?, FARA = ?, NOTE = ? WHERE PERFNO = ?";
        int result = 0;
        try {
            result = jdbcTemplate.update(sql,
                    perf.getQty(),
                    perf.getQtydefect(),
                    perf.getPerfdate(),
                    perf.getFara(),
                    perf.getNote(),
                    perf.getPerfno()
            );
        } catch (Exception e) {
            log.error("작업실적 수정 실패 : {}", e.getMessage());
        }
        return result > 0;
    }

    // 작업 실적 수정 input
    public void upPerf() {
        System.out.println("======= 작업실적 수정 =======");
        System.out.println("수정할 작업실적의 번호를 입력하세요.");
        System.out.print("입력 : ");
        int c = sc.nextInt();
        sc.nextLine();

        Perf perf = new Perf();
        perf.setPerfno(c);

        System.out.print("변경할 작업실적의 완제품 수량을 입력하세요: ");
        int newQty = sc.nextInt();
        perf.setQty(newQty);
        sc.nextLine();

        System.out.print("변경할 작업실적의 불량품 수량을 입력하세요: ");
        int newQtyDefect = sc.nextInt();
        perf.setQtydefect(newQtyDefect);
        sc.nextLine();

        double fara = 0.0;
        int totalQty = newQty + newQtyDefect;
        if (totalQty > 0) {
            fara = (double) newQtyDefect / totalQty;
        }

        perf.setFara(fara);
        System.out.println("▶ 자동 계산된 불량률(FARA): " + fara);

        DateTimeFormatter perfDateFormatter = DateTimeFormatter.ofPattern("dd/MM/yy");
        System.out.print("변경할 작업실적의 등록일자를 입력하세요(DD/MM/YY): ");
        String perfDate = sc.nextLine();
        LocalDate Date = LocalDate.parse(perfDate, perfDateFormatter);
        perf.setPerfdate(Date);


        System.out.println("변경할 작업실적의 메모를 입력하세요: ");
        String newNote = sc.nextLine();
        perf.setNote(newNote);

        boolean isSuccess = updatePerf(perf);
        System.out.println("작업 실적 수정 : " + (isSuccess ? "성공" : "실패"));
    }
    //===================================================

    public void seqFdcLogFdcFaultCProcTotalInput() {
        while (true) {
            System.out.println("[1]설비 관리 [2]설비 로그 관리 [3]설비 이상 감지 [4]설비 공정 [9]종료 [0]뒤로가기");
            System.out.print("입럭 : ");
            int seqFdcLogFdcFaultCProcC = sc.nextInt();
            sc.nextLine();

            switch (seqFdcLogFdcFaultCProcC) {
                case 1:
                    seqMenu();
                    break;
                case 2:
                    FdcLogMenu();
                    break;
                case 3:
                    FdcFaultMenu();
                    break;
                case 4:
                    ProcNoMenu();
                    break;
                case 9:
                    System.out.println("프로그램을 종료합니다.");
                    System.exit(0);
                case 0:
                    return;
            }
        }
    }

    private void seqMenu() {
        while (true) {
            System.out.println("[1]설비 조회 [2]설비 등록 [3]설비 수정 [9]종료 [0]뒤로가기");
            System.out.print("입럭 : ");
            int woc = sc.nextInt(); //
            sc.nextLine();

            switch (woc) {
                case 1:
                    seqc();
                    break;
                case 2:
                    regSeq();
                    break;
                case 3:
                    upSeq();
                    break;
                case 9:
                    System.out.println("프로그램을 종료합니다.");
                    System.exit(0);
                    break;
                case 0:
                    return;
            }
        }
    }

    private void FdcLogMenu() {
        while (true) {
            System.out.println("[1]설비 로그 조회 [9]종료 [0]뒤로가기");
            System.out.print("입럭 : ");
            int FdcLogc = sc.nextInt();
            sc.nextLine();

            switch (FdcLogc) {
                case 1:
                    fdcLogc();
                    break;
                case 9:
                    System.out.println("프로그램을 종료합니다.");
                    System.exit(0);
                case 0:
                    return;
            }
        }
    }

    private void FdcFaultMenu() {
        while (true) {
            System.out.println("[1]설비 이상 감지 조회 [9]종료 [0]뒤로가기");
            System.out.print("입럭 : ");
            int FdcFaultc = sc.nextInt();
            sc.nextLine();

            switch (FdcFaultc) {
                case 1:
                    fdcfaultc();
                    break;
                case 9:
                    System.out.println("프로그램을 종료합니다.");
                    System.exit(0);
                case 0:
                    return;
            }
        }
    }

    private void ProcNoMenu() {
        while (true) {
            System.out.println("[1]설비 이상 감지 조회 [9]종료 [0]뒤로가기");
            System.out.print("입럭 : ");
            int ProcNoc = sc.nextInt();
            sc.nextLine();

            switch (ProcNoc) {
                case 1:
                    procc();
                    break;
                case 9:
                    System.out.println("프로그램을 종료합니다.");
                    System.exit(0);
                case 0:
                    return;
            }
        }
    }


    // 설비 및 공정 조회 - [1] 설비 조회
    public void seqc() {
        List<Seq> seqList = SeqList();
        for (Seq seq : seqList) System.out.println(seq);
    }

    public List<Seq> SeqList() {
        String query = "SELECT * FROM MES_SEQ_TABLE";
        return jdbcTemplate.query(query, new SeqRowMapper());
    }

    private static class SeqRowMapper implements RowMapper<Seq> {
        @Override
        public Seq mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Seq(
                    rs.getString("SEQNO"),
                    rs.getString("SEQNAME"),
                    rs.getString("SEQOR"),
                    rs.getString("NOTE")
            );
        }
    }

    // 설비 및 공정 등록 - [1] SEQ 설비 등록 코드
    public boolean insertSeq(Seq seq) {
        int result = 0;
        String query = "INSERT INTO MES_SEQ_TABLE(SEQNO, SEQNAME, SEQOR, NOTE) VALUES(?, ?, ?, ?)";
        try {
            result = jdbcTemplate.update(query, seq.getSeqno(), seq.getSeqname(), seq.getSeqor(), seq.getNote());
        } catch (Exception e) {
            log.error("설비 상세 등록 실패 : {}", e.getMessage());
        }
        return result > 0;
    }

    // 설비 및 공정 등록 - [1] SEQ 설비 등록 input
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

        boolean inSuccess = insertSeq(seq);
        System.out.println("설비상세등록 : " + (inSuccess ? "성공" : "실패"));
    }

    // 설비 및 공정 수정 [1] 설비수정 코드
    public boolean updateSeq(Seq seq) {
        String sql = "UPDATE MES_SEQ_TABLE SET NOTE = ? WHERE SEQNO = ?";
        int result = 0;
        try {
            result = jdbcTemplate.update(sql,
                    seq.getNote(),
                    seq.getSeqno()
            );
        } catch (Exception e) {
            log.error("설비수정 실패 : {}", e.getMessage());
        }
        return result > 0;
    }

    // 설비 및 공정 수정 [1] - 설비 수정 input
    public void upSeq() {
        System.out.println("======= 설비관리 수정 =======");
        System.out.println("수정할 작업지시의 번호를 입력하세요.");
        System.out.print("입력 : ");
        String c = sc.nextLine();

        Seq seq = new Seq();
        seq.setSeqno(c);

        System.out.println("변경할 작업지시의 메모를 입력하세요: ");
        String newNote = sc.nextLine();
        seq.setNote(newNote);

        boolean isSuccess = updateSeq(seq);
        System.out.println("제품 정보 수정 : " + (isSuccess ? "성공" : "실패"));
    }

    // 설비 및 공정 조회 - [2] 설비로그조회
    public void fdcLogc() {
        List<Fdclog> fdclogList = FdclogList();
        for (Fdclog fdclog : fdclogList) System.out.println(fdclog);
    }

    public List<Fdclog> FdclogList() {
        String query = "SELECT * FROM MES_FDCLOG_TABLE";
        return jdbcTemplate.query(query, new FdclogRowMapper());
    }

    private static class FdclogRowMapper implements RowMapper<Fdclog> {
        @Override
        public Fdclog mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Fdclog(
                    rs.getInt("LOGNO"),
                    rs.getString("SEQNO"),
                    rs.getString("PARAMNO"),
                    rs.getDouble("PARAMVALUE"),
                    rs.getTimestamp("LOGTIME").toLocalDateTime().toLocalDate()
            );
        }
    }

    // 설비 및 공정 조회 - [3] 설비이상감지이력조회
    public void fdcfaultc() {
        List<Fdcfault> fdcfaultList = FdcfaultList();
        for (Fdcfault fdcfault : fdcfaultList) System.out.println(fdcfault);
    }

    public List<Fdcfault> FdcfaultList() {
        String query = "SELECT * FROM MES_FDCFAULT_TABLE";
        return jdbcTemplate.query(query, new FdcfaultRowMapper());
    }

    private static class FdcfaultRowMapper implements RowMapper<Fdcfault> {
        @Override
        public Fdcfault mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Fdcfault(
                    rs.getInt("FAULTNO"),
                    rs.getInt("LOGNO"),
                    rs.getString("SEQNO"),
                    rs.getString("FAULTCODE"),
                    rs.getString("FAULTMSG"),
                    rs.getTimestamp("FAULTTIME").toLocalDateTime().toLocalDate()
            );
        }
    }

    // 설비 및 공정 조회 - [4] 설비 공정 조회
    public void procc() {
        List<Proc> procList = procList();
        for (Proc proc : procList) System.out.println(proc);
    }
    public List<Proc> procList() {
        String query = "SELECT * FROM MES_PROC_TABLE";
        return jdbcTemplate.query(query, new FrocRowMapper());
    }
    private static class FrocRowMapper implements RowMapper<Proc> {
        @Override
        public Proc mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Proc(
                    rs.getInt("PROCNO"),
                    rs.getString("SEQNO"),
                    rs.getString("PROCNAME")
            );
        }
    }






    public void invDeliTotalInput() {
        while (true) {
            System.out.println("[1]입고 관리 [2]출고 관리 [9]종료 [0]뒤로가기");
            System.out.print("입럭 : ");
            int invc = sc.nextInt();
            sc.nextLine();

            switch (invc) {
                case 1:
                    inv4Menu();
                    break;
                case 2:
                    deliMenu();
                    break;
                case 9:
                    System.out.println("프로그램을 종료합니다.");
                    System.exit(0);
                case 0:
                    return;
            }
        }
    }
    private void inv4Menu() {
        while (true) {
            System.out.println("[1]제품 입고 [2]재고 조회 [9]종료 [0]뒤로가기");
            System.out.print("입럭 : ");
            int woc = sc.nextInt(); //
            sc.nextLine();

            switch (woc) {
                case 1:
                    regInv();
                    break;
                case 2:
                    invc();
                    break;
                case 9:
                    System.out.println("프로그램을 종료합니다.");
                    System.exit(0);
                    break;
                case 0:
                    return;
            }
        }
    }
    private void deliMenu() {
        while (true) {
            System.out.println("[1]제품 출고 등록 [2]제품 출고 조회 [3]제품 출고 삭제 [9]종료 [0]뒤로가기");
            System.out.print("입럭 : ");
            int woc = sc.nextInt(); //
            sc.nextLine();

            switch (woc) {
                case 1:
                    regDeli();
                    break;
                case 2:
                    delic();
                    break;
                case 3:

                    break;
                case 9:
                    System.out.println("프로그램을 종료합니다.");
                    System.exit(0);
                    break;
                case 0:
                    return;
            }
        }
    }

    // 출고기록조회
    public void delic() {
        List<Deli> deliList = deliList();
        for (Deli deli : deliList) System.out.println(deli);
    }
    public List<Deli> deliList() {
        String query = "SELECT * FROM MES_DELI_TABLE";
        return jdbcTemplate.query(query, new DeliRowMapper());
    }
    private static class DeliRowMapper implements RowMapper<Deli> {
        @Override
        public Deli mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Deli(
                    rs.getInt("DELINO"),
                    rs.getInt("INVNO"),
                    rs.getInt("DELIQTY"),
                    rs.getString("LOC"),
                    rs.getTimestamp("DELIDATE").toLocalDateTime().toLocalDate(),
                    rs.getString("NOTE")
            );
        }
    }
    // 출고기록등록 코드
    public boolean insertDeli(Deli deli) {
        int result = 0;
        String query = "INSERT INTO MES_DELI_TABLE(DELINO, INVNO, DELIQTY, LOC, DELIDATE, NOTE) VALUES(?, ?, ?, ?, ?, ?)";
        try {
            result = jdbcTemplate.update(query, deli.getDelino(), deli.getInvno(), deli.getQty(), deli.getLoc(), deli.getDelidate(), deli.getNote());
        } catch (Exception e) {
            log.error("출고 기록 등록 실패 : {}", e.getMessage());
        }
        return result > 0;
    }
    // 출고기록등록 input
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
        boolean inSuccess = insertDeli(deli);
        System.out.println("출고기록등록 : " + (inSuccess ? "성공" : "실패"));
    }


    // 완료된 작업 찾기 쿼리문
    public List<ComWorkOrder> findComWorkOrder() {
        String query = """
                SELECT
                    W.WONO AS "작업지시번호",
                    P.PRODNAME AS "제품명",
                    W.QTY AS "목표수량",
                    TO_CHAR(W.ORDERDATE, 'YY-MM-DD') AS "지시일",
                    TO_CHAR(W.DUEDATE, 'YY-MM-DD') AS "완료예정일"
                FROM
                    MES_WO_TABLE W
                JOIN
                    MES_PROD_TABLE P ON W.PRODNO = P.PRODNO
                WHERE
                    W.NOTE LIKE '%달성%'
                ORDER BY W.WONO
                """;

        return jdbcTemplate.query(query, new CompletedWorkOrderRowMapper());
    }
    private static class CompletedWorkOrderRowMapper implements RowMapper<ComWorkOrder> {
        @Override
        public ComWorkOrder mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new ComWorkOrder(
                    rs.getString("작업지시번호"),  // SELECT AS "작업지시번호"
                    rs.getString("제품명"),       // SELECT AS "제품명"
                    rs.getInt("목표수량"),         // SELECT AS "목표수량"
                    rs.getString("지시일"),         // SELECT AS "지시일"
                    rs.getString("완료예정일")      // SELECT AS "완료예정일"
            );
        }
    }
    public void cwr() {
        List<ComWorkOrder> comWorkOrders = findComWorkOrder();
        for (ComWorkOrder comWorkOrder : comWorkOrders) System.out.println(comWorkOrder);
    }



    // 불량률 5퍼이상 조회
    public void perfdateTotalInput() {
        while (true) {
            System.out.println("[1]불량률 5% 이상 조회 [9]종료 [0]뒤로가기");
            System.out.print("입럭 : ");
            int perfdatec = sc.nextInt();
            sc.nextLine();

            switch (perfdatec) {
                case 1:
                    perfdatec();
                    break;
                case 9:
                    System.out.println("프로그램을 종료합니다.");
                    System.exit(0);
                case 0:
                    return;
            }
        }
    }
    public void perfdatec() {
        List<PerfData> perfDataList = perfDataList();
        for (PerfData perfData : perfDataList) System.out.println(perfData);
    }
    public List<PerfData> perfDataList() {
        String query = "SELECT " +
                "    TO_CHAR(P.PERFDATE, 'YYYY-MM-DD') AS \"작업일\", " +
                "    E.ENAME AS \"작업자\", " +
                "    E.EMPNO AS \"사원번호\", " +
                "    P.SEQNO AS \"설비번호\", " +
                "    PR.PRODNAME AS \"제품명\", " +
                "    P.QTY AS \"양품\", " +
                "    P.QTYDEFECT AS \"불량\", " +
                "    TO_CHAR(P.FARA, 'FM999999990.00') || '%' AS \"불량률\" " +
                "FROM " +
                "    MES_PERF_TABLE P " +
                "JOIN " +
                "    MES_EMP_TABLE E ON P.EMPNO = E.EMPNO " +
                "JOIN " +
                "    MES_WO_TABLE W ON P.WONO = W.WONO " +
                "JOIN " +
                "    MES_PROD_TABLE PR ON W.PRODNO = PR.PRODNO " +
                "WHERE " +
                "    P.FARA > 5";

        return jdbcTemplate.query(query, new PerfDataRowMapper());
    }
    private static class PerfDataRowMapper implements RowMapper<PerfData> {
        @Override
        public PerfData mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new PerfData(
                    rs.getDate("작업일").toLocalDate(),
                    rs.getString("작업자"),
                    rs.getInt("사원번호"),
                    rs.getString("설비번호"),
                    rs.getString("제품명"),
                    rs.getInt("양품"),
                    rs.getInt("불량"),
                    rs.getString("불량률")
            );
        }
    }
}






