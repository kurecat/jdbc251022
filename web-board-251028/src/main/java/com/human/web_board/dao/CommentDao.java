package com.human.web_board.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
@RequiredArgsConstructor
public class CommentDao {
    private final JdbcTemplate jdbc;

    // 댓글 등록

    // 댓글 가져오기 (게시글 ID를 통해서 가져 와야 함

    // 댓글 삭제

    // 댓글 수정

    // Mapper 메서드

}
