package com.human.web_board.dao;

import com.human.web_board.dto.PostCreateReq;
import com.human.web_board.dto.PostRes;
import com.human.web_board.model.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostDao {
    private final JdbcTemplate jdbc;

    // 게시글 등록
    public Long save(PostCreateReq p){
        String sql = "INSERT INTO post(id, member_id, title, content) VALUES (seq_post.NEXTVAL, ?, ?, ?)";
        jdbc.update(sql, p.getMemberId(), p.getTitle(), p.getContent());
        return jdbc.queryForObject("SELECT seq_post.CURRVAL FROM dual", Long.class);
    }


    // 게시글 목록 보기
    public List<PostRes> findAll() {
        String sql =
                """
                SELECT P.ID AS "게시글번호", M.EMAIL AS "작성자", P.TITLE AS "제목", P.CREATE_AT AS "작성일"
                FROM MEMBER M, POST P
                WHERE M.ID = P.ID
                ORDER BY P.ID DESC
                """;
        return jdbc.query(sql, new PostDao.PostResRowMapper());
    }

    // id로 게시글 가져오기
    public PostRes findById(Long id) {
        String sql =
                """
                SELECT P.ID AS "게시글번호", M.EMAIL AS "작성자", P.TITLE AS "제목",  P.CREATE_AT AS "작성일"
                FROM MEMBER M, POST P\s
                WHERE M.ID = P.ID
                AND P.ID = ?
                ORDER BY P.ID DESC;
                """;
        List<PostRes> list = jdbc.query(sql, new PostDao.PostResRowMapper(), id);
        return list.isEmpty() ? null : list.get(0);
    }

    // 게시글 수정
    public boolean update(PostCreateReq p , Long id){
        String sql = "UPDATE post SET title = ?, content = ? WHERE id = ?";
        return jdbc.update(sql, p.getTitle(),p.getContent(), id) > 0;
    }

    // 게시글 삭제
    public boolean delete(Long id){
        String sql = "DELETE FROM post WHERE id = ?";
        int rowsAffected = jdbc.update(sql, id);
        return rowsAffected > 0;
    }

    // Mapper 메서드 생성
    static class PostResRowMapper implements RowMapper<PostRes> {
        @Override
        public PostRes mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new PostRes(
                    rs.getLong("id"),
                    rs.getLong("memberId"),
                    rs.getString("memberEmail"),
                    rs.getString("title"),
                    rs.getString("content"),
                    rs.getTimestamp("createAt").toLocalDateTime()
            );
        }
    }
}
