package com.human.jdbc251022.dao;

import com.human.jdbc251022.model.Emp;
import com.human.jdbc251022.model.dept;
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
                    rs.getTimestamp("HIREDATE").toLocalDateTime(),
                    rs.getInt("MGR")
            );
        }
    }

    // 부서조회 - 총괄관리부
    public List<dept> DeptList1() {
        String query = "SELECT * FROM MES_EMP_TABLE WHERE DEPTNO = 1001";
        return jdbcTemplate.query(query, new Emp1RowMapper());
    }

    private static class Emp1RowMapper implements RowMapper<dept> {
        @Override
        public dept mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new dept(
                    rs.getInt("EMPNO"),
                    rs.getInt("DEPTNO"),
                    rs.getString("ENAME"),
                    rs.getString("JOB"),
                    rs.getTimestamp("HIREDATE").toLocalDateTime(),
                    rs.getInt("MGR")
            );
        }
    }
}


// 회원 등록
//    public boolean insertMember(Member member) {
//        int result = 0;
//        String query = "INSERT INTO member(email, pwd, name) VALUES(?, ?, ?)";
//        try {
//            result = jdbcTemplate.update(query, member.getEmail(),
//                    member.getPwd(), member.getName());
//        } catch (Exception e) {
//            log.error("회원 정보 추가 실패 : {}", e.getMessage());
//        }
//        return result > 0;
//    }
// 회원 삭제
//    public boolean deleteMember(Member member) {
//        int result = 0;
//        String query = "DELETE FROM member WHERE EMAIL = ?";
//        try {
//            result = jdbcTemplate.update(query, member.getEmail());
//        } catch (Exception e) {
//            log.error("회원 정보 삭제 실패 : {}", e.getMessage());
//        }
//        return result > 0;
//    }

//    // 이름 수정
//    public boolean updateNameMember(Member member) {
//        int result = 0;
//        String query = "UPDATE member SET NAME = ? WHERE EMAIL = ?";
//        try {
//            result = jdbcTemplate.update(query, member.getName() , member.getEmail());
//        } catch (Exception e){
//            log.error("이름 수정 실패 : {}", e.getMessage());
//        }
//        return result > 0;
//    }

// 비밀번호 수정
//    public boolean updatePwdMember(Member member) {
//        int result = 0;
//        String query = "UPDATE member SET PWD = ? WHERE EMAIL = ?";
//        try {
//            result = jdbcTemplate.update(query, member.getPwd(), member.getEmail());
//        } catch (Exception e){
//            log.error("비밀번호 수정 실패 : {}", e.getMessage());
//        }
//        return result > 0;
//    }
