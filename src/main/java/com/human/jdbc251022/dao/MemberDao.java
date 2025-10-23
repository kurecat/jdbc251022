package com.human.jdbc251022.dao;

import com.human.jdbc251022.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository // Spring container Bean 등록
@Slf4j
@RequiredArgsConstructor // 생성자를 통해서 의존성 주입을 받음
public class MemberDao {
    private final JdbcTemplate jdbcTemplate;

    // 사원 조회
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

    // 전체부서조회
    public List<iDept> iDeptList(){
        String query = "SELECT * FROM MES_DEPT_TABLE";
        return jdbcTemplate.query(query, new ideptRowMapper());
    }
    // 부서조회 - 총괄관리부
    public List<Dept> DeptList1() {
        String query = "SELECT * FROM MES_EMP_TABLE WHERE DEPTNO = 1001";
        return jdbcTemplate.query(query, new deptRowMapper());
    }
    // 부서조회 - 생산관리부
    public List<Dept> DeptList2() {
        String query = "SELECT * FROM MES_EMP_TABLE WHERE DEPTNO = 2002";
        return jdbcTemplate.query(query, new deptRowMapper());
    }
    // 부서조회 - 품질관리부
    public List<Dept> DeptList3() {
        String query = "SELECT * FROM MES_EMP_TABLE WHERE DEPTNO = 3003";
        return jdbcTemplate.query(query, new deptRowMapper());
    }
    // 부서조회 - 설비관리부
    public List<Dept> DeptList4() {
        String query = "SELECT * FROM MES_EMP_TABLE WHERE DEPTNO = 4004";
        return jdbcTemplate.query(query, new deptRowMapper());
    }
    // 부서조회 - 자재관리부
    public List<Dept> DeptList5() {
        String query = "SELECT * FROM MES_EMP_TABLE WHERE DEPTNO = 5005";
        return jdbcTemplate.query(query, new deptRowMapper());
    }
    // 부서조회 - 물류관리부
    public List<Dept> DeptList6() {
        String query = "SELECT * FROM MES_EMP_TABLE WHERE DEPTNO = 6006";
        return jdbcTemplate.query(query, new deptRowMapper());
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

    private static class deptRowMapper implements RowMapper<Dept> {
        @Override
        public Dept mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Dept(
                    rs.getInt("EMPNO"),
                    rs.getInt("DEPTNO"),
                    rs.getString("ENAME"),
                    rs.getString("JOB"),
                    rs.getTimestamp("HIREDATE").toLocalDateTime().toLocalDate(),
                    rs.getInt("MGR")
            );
        }
    }

    // 제품 조회
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

    // 작업실적조회
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

    // 작업지시조회
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

    // 설비 및 공정 조회 - [1] 설비상세조회
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
    // 설비 및 공정 조회 - [2] 설비로그조회
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
    public List<Proc> FrocList() {
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

    // 재고상태조회
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

    // 출고기록조회
    public List<Deli> DeliList() {
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

    // 부서 등록
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

    // 사원 등록
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

    //제품 등록
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

    // 작업실적등록
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

    // 작업지시등록
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

    // 설비 및 공정 등록 - [1] SEQ 설비상세등록
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

    // 설비 및 공정 등록 - [4] PROC 설비 공정 등록
    public boolean insertProc(Proc proc) {
        int result = 0;
        String query = "INSERT INTO MES_PROC_TABLE(PROCNO, SEQNO, PROCNAME) VALUES(?, ?, ?)";
        try {
            result = jdbcTemplate.update(query, proc.getProcno(), proc.getSeqno(), proc.getProcname());
        } catch (Exception e) {
            log.error("설비 공정 등록 실패 : {}", e.getMessage());
        }
        return result > 0;
    }

    // 출고기록등록 DELI
    public boolean insertDeli(Deli deli) {
        int result = 0;
        String query = "INSERT INTO MES_DELI_TABLE(DELINO, INVNO, DELIQTY, LOC, DELIDATE, NOTE) VALUES(?, ?, ?, ?, ?, ?)";
        try {
            result = jdbcTemplate.update(query, deli.getDelino(), deli.getInvno(), deli.getDeliqty(), deli.getLoc(), deli.getDelidate(), deli.getNote());
        } catch (Exception e) {
            log.error("출고 기록 등록 실패 : {}", e.getMessage());
        }
        return result > 0;
    }

    // 재고상태등록
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



































    // 작업 실적 수정
    public boolean updatePerf(Perf perf) {

        String sql = "UPDATE MES_PERF_TABLE SET " +
                "PROCNO = ?, EMPNO = ?, WONO = ?, SEQNO = ?, QTY = ?, " +
                "QTYDEFECT = ?, PERFDATE = ?, FARA = ?, NOTE = ? " +
                "WHERE PERFNO = ?";

        int result = 0;
        try {
            // update 결과를 result 변수에 저장
            result = jdbcTemplate.update(sql,
                    // SQL ? 순서와 파라미터 순서 일치 (총 10개)
                    perf.getProcno(),     // 1. (for PROCNO = ?)
                    perf.getEmpno(),      // 2. (for EMPNO = ?)
                    perf.getWono(),       // 3. (for WONO = ?)
                    perf.getSeqno(),      // 4. (for SEQNO = ?)
                    perf.getQty(),        // 5. (for QTY = ?)
                    perf.getQtydefect(),  // 6. (for QTYDEFECT = ?)
                    perf.getPerfdate(),   // 7. (for PERFDATE = ?)
                    perf.getFara(),       // 8. (for FARA = ?)
                    perf.getNote(),       // 9. (for NOTE = ?)
                    perf.getPerfno()      // 10. (for WHERE PERFNO = ?)
            );
        } catch (Exception e) {
            log.error("작업실적 수정 실패 (perfno: {}): {}", perf.getPerfno(), e.getMessage());
            // e.printStackTrace();
        }

        return result > 0;
    }































}

