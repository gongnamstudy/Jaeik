package com.samsung.tech.gongnam.dao;

import com.samsung.tech.gongnam.dao.connection.ConnectionMaker;
import com.samsung.tech.gongnam.dao.template.AddStatement;
import com.samsung.tech.gongnam.dao.template.DeleteAllStatement;
import com.samsung.tech.gongnam.dao.template.StatementStrategy;
import com.samsung.tech.gongnam.domain.User;
import org.springframework.dao.EmptyResultDataAccessException;

import java.sql.*;

public class UserDao {

    private ConnectionMaker connectionMaker;

    public UserDao(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    public void add(User user) throws ClassNotFoundException, SQLException {
        StatementStrategy strategy = new AddStatement(user);
        jdbcContextWithStatementStrategy(strategy);
    }

    public User get(String id) throws ClassNotFoundException, SQLException {

        Connection conn = connectionMaker.makeConnection();

        PreparedStatement ps = conn.prepareStatement(
                "SELECT * FROM users WHERE id = ?");
        ps.setString(1, id);

        User user = null;
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            user = new User();
            user.setId(rs.getString("id"));
            user.setName(rs.getString("name"));
            user.setPassword(rs.getString("password"));
        }

        rs.close();
        ps.close();
        conn.close();

        if (user == null) throw new EmptyResultDataAccessException(1);
        return user;
    }

    public void deleteAll() throws SQLException, ClassNotFoundException {
        StatementStrategy strategy = new DeleteAllStatement();
        jdbcContextWithStatementStrategy(strategy);
    }

    public int getCount() throws SQLException, ClassNotFoundException {

        Connection conn = connectionMaker.makeConnection();

        PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM users");

        ResultSet rs = ps.executeQuery();
        rs.next();

        int count = rs.getInt(1);

        rs.close();
        ps.close();
        conn.close();

        return count;
    }

    public void jdbcContextWithStatementStrategy(StatementStrategy stmt) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = connectionMaker.makeConnection();

            ps = stmt.makePreparedStatement(conn);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }
    }
}


