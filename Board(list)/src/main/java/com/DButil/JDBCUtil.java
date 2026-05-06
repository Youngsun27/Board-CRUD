package com.DButil;

import java.sql.*;

// DB 연결/해제를 한 곳에서 관리 → 모든 DAO가 여기서 가져다 씀
public class JDBCUtil {

    // DB 연결 객체 반환
    public static Connection getConnection() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver"); // 드라이버 로드
            return DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:orcl",      // DB 주소
                "scott", "tiger"                              // 계정/비번
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // SELECT 없는 경우 (INSERT/UPDATE/DELETE): stmt + con 닫기
    public static void close(PreparedStatement stmt, Connection con) {
        if (stmt != null) try { stmt.close(); } catch (Exception e) { e.printStackTrace(); }
        if (con  != null) try { con.close();  } catch (Exception e) { e.printStackTrace(); }
    }

    // SELECT 있는 경우 (getBoard, getBoardList): rs + stmt + con 닫기
    public static void close(ResultSet rs, PreparedStatement stmt, Connection con) {
        if (rs   != null) try { rs.close();   } catch (Exception e) { e.printStackTrace(); }
        if (stmt != null) try { stmt.close(); } catch (Exception e) { e.printStackTrace(); }
        if (con  != null) try { con.close();  } catch (Exception e) { e.printStackTrace(); }
    }
}