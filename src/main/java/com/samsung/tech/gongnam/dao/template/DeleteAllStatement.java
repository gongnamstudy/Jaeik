package com.samsung.tech.gongnam.dao.template;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteAllStatement implements StatementStrategy {

    @Override
    public PreparedStatement makePreparedStatement(Connection conn) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("DELETE FROM users");
        return ps;
    }
}
