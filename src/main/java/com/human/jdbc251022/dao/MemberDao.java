package com.human.jdbc251022.dao;

import com.human.jdbc251022.model.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

@Repository // Spring container Bean 등록
@Slf4j
@RequiredArgsConstructor // 생성자를 통해서 의존성 주입을 받음
public class MemberDao {
    private final JdbcTemplate jdbcTemplate;

    // 회원 전체 조회
    public List<Member> memberList() {
        String query = "SELECT * FROM MES_EMP_TABLE";
        return jdbcTemplate.query(query, new MemberRowMapper());
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

    private static class MemberRowMapper implements RowMapper<Member> {
        @Override
        public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Member(
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
