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

    // Total
    public void empdepttotalinput(){
        while (true) {
            System.out.println("[1]사원관리 [2]부서관리 [0]뒤로가기"); // [0]뒤로가기 추가
            System.out.print("입럭 : ");
            int empdeptc = sc.nextInt();
            sc.nextLine();
            if (empdeptc == 0) {
                break;
            }
            switch (empdeptc) {
                case 1:
                    while (true) {
                        System.out.println("[1]사원 정보 조회 [2]사원 정보 등록 [3]사원 정보 수정 [0]뒤로가기");
                        System.out.print("입럭 : ");
                        int empc = sc.nextInt();
                        sc.nextLine();

                        if (empc == 0) {
                            break;
                        }

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
                        }
                    }
                    break;
                case 2:
                    while (true) {
                        System.out.println("[1]부서 정보 조회 [2]부서 정보 등록 [3]부서 정보 수정 [0]뒤로가기");
                        System.out.print("입럭 : ");
                        int deptc = sc.nextInt();
                        sc.nextLine(); //
                        if (deptc == 0) {
                            break;
                        }

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
                        }
                    }
                    break;
            }
        }
    }










    // 사원 =======================================================
    // 사원 조회
    public void empc(){
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
    public void upEmp(){
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

        Emp emp = new Emp(empno ,deptno, ename, job, mgr);

        boolean inSuccess =  insertEmp(emp);
        System.out.println("사원 등록 : " + (inSuccess ? "성공" : "실패"));
    }
    // 등록
    public boolean insertEmp(Emp emp) {
        int result = 0;
        String query = "INSERT INTO MES_EMP_TABLE(EMPNO, DEPTNO, ENAME, \"JOB\", HIREDATE, MGR) VALUES(?, ?, ?, ?, SYSDATE, ?)";
        try {
            result = jdbcTemplate.update(query,emp.getEmpno(), emp.getDeptno(), emp.getEmpno(), emp.getJob(), emp.getMgr());
        } catch (Exception e) {
            log.error("사원 정보 등록 실패 : {}", e.getMessage());
        }
        return result > 0;
    }
    //=============================================================

    // 부서 =======================================================
    // 부서조회
    public void deptc(){
        List<iDept> iDeptList = iDeptList();
        for (iDept iDept : iDeptList) System.out.print(iDept);
    }
    public List<iDept> iDeptList(){
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
    public void upDept(){
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
    public boolean insertDept(iDept idept){
        int result = 0;
        String query = "INSERT INTO MES_DEPT_TABLE(DEPTNO, DEPTNAME) VALUES(?, ?)";
        try {
            result = jdbcTemplate.update(query, idept.getDeptno(), idept.getDeptname());
        } catch (Exception e) {
            log.error("부서 정보 등록 실패");
        }
        return result > 0;
    }
//  =======================================================
}
