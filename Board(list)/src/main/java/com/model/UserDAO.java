package com.model;

import java.sql.*;
import org.springframework.stereotype.Repository;
import com.DButil.JDBCUtil;

@Repository
public class UserDAO {

    private Connection        con  = null;
    private PreparedStatement stmt = null;
    private ResultSet         rs   = null;

    private final String USER_GET =
        "select * from users where id=? and password=?";

    public UserVO getUser(UserVO vo) {
        UserVO user = null;
        try {
            con  = JDBCUtil.getConnection();
            stmt = con.prepareStatement(USER_GET);
            stmt.setString(1, vo.getId());
            stmt.setString(2, vo.getPassword());
            rs   = stmt.executeQuery();

            if (rs.next()) {
                user = new UserVO();
                user.setId(rs.getString("id"));
                user.setPassword(rs.getString("password"));
                user.setName(rs.getString("name"));
                user.setRole(rs.getString("role"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, stmt, con);
        }
        return user;
    }
}
