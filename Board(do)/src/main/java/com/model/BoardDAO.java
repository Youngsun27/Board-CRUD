package com.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.DButil.JDBCUtil;

// @Repository �뼱�끂�뀒�씠�뀡 �젣嫄�: �닚�닔 JDBC 諛⑹떇(Spring Bean �븘�떂)
// DispatcherServlet�뿉�꽌 new BoardDAO()濡� 吏곸젒 �깮�꽦�븯�뿬 �궗�슜
public class BoardDAO {

    private Connection        con  = null;
    private PreparedStatement stmt = null;
    private ResultSet         rs   = null;

    private final String BOARD_INSERT =
        "insert into board(seq, title, writer, content) " +
        "values((select nvl(max(seq), 0)+1 from board), ?, ?, ?)";

    private final String BOARD_UPDATE =
        "update board set title=?, content=? where seq=?";

    private final String BOARD_DELETE =
        "delete from board where seq=?";

    private final String BOARD_GET =
        "select * from board where seq=?";

    private final String BOARD_LIST =
        "select * from board order by seq desc";


    public void insertBoard(BoardVO vo) {
        try {
            con  = JDBCUtil.getConnection();
            stmt = con.prepareStatement(BOARD_INSERT);
            stmt.setString(1, vo.getTitle());
            stmt.setString(2, vo.getWriter());
            stmt.setString(3, vo.getContent());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt, con);
        }
    }

    public void updateBoard(BoardVO vo) {
        try {
            con  = JDBCUtil.getConnection();
            stmt = con.prepareStatement(BOARD_UPDATE);
            stmt.setString(1, vo.getTitle());
            stmt.setString(2, vo.getContent());
            stmt.setInt(3, vo.getSeq());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt, con);
        }
    }

    public void deleteBoard(BoardVO vo) {
        try {
            con  = JDBCUtil.getConnection();
            stmt = con.prepareStatement(BOARD_DELETE);
            stmt.setInt(1, vo.getSeq());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt, con);
        }
    }

    public BoardVO getBoard(BoardVO vo) {
        BoardVO board = null;
        try {
            con  = JDBCUtil.getConnection();
            stmt = con.prepareStatement(BOARD_GET);
            stmt.setInt(1, vo.getSeq());
            rs   = stmt.executeQuery();

            if (rs.next()) {
                board = new BoardVO();
                board.setSeq(rs.getInt("seq"));
                board.setTitle(rs.getString("title"));
                board.setWriter(rs.getString("writer"));
                board.setContent(rs.getString("content"));
                board.setRegDate(rs.getDate("regdate"));
                board.setCnt(rs.getInt("cnt"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, stmt, con);
        }
        return board;
    }

    public List<BoardVO> getBoardList(BoardVO vo) {
    	List<BoardVO> boardList = new ArrayList<BoardVO>();

        try {
            con  = JDBCUtil.getConnection();
            stmt = con.prepareStatement(BOARD_LIST);
            rs   = stmt.executeQuery();

            while (rs.next()) {
                BoardVO board = new BoardVO();
                board.setSeq(rs.getInt("seq"));
                board.setTitle(rs.getString("title"));
                board.setWriter(rs.getString("writer"));
                board.setContent(rs.getString("content"));
                board.setRegDate(rs.getDate("regdate"));
                board.setCnt(rs.getInt("cnt"));
                boardList.add(board);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, stmt, con);
        }
        return boardList;
    }
}
