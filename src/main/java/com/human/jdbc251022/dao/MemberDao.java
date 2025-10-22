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

@Repository // Spring container Bean 등록
@Slf4j
@RequiredArgsConstructor // 생성자를 통해서 의존성 주입을 받음
public class MemberDao {
    private final JdbcTemplate jdbcTemplate;

    // 회원 전체 조회

    public List<Member> memberList() {
        String query = "SELECT * FROM member";
        return jdbcTemplate.query(query, new MemberRowMapper());
    }

    // 회원 등록
    public boolean insertMember(Member member) {
        int result = 0;
        String query = "INSERT INTO MEMBER(email, pwd, name) VALUES(?, ?, ?)";
        try {
            result = jdbcTemplate.update(query, member.getEmail(),
                    member.getPwd(), member.getName());
        } catch (Exception e) {
            log.error("회원 정보 추가 실패 : {}", e.getMessage());
        }
        return result > 0;
    }

    private static class MemberRowMapper implements RowMapper<Member> {
        @Override
        public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Member(
              rs.getString("email"),
              rs.getString("pwd"),
              rs.getString("name"),
              rs.getTimestamp("reg_date").toLocalDateTime()
            );
        }
    }
}
