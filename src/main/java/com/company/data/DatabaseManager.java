package com.company.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {

    private static final String connectionUrl = "jdbc:mysql://localhost:3306/population?serverTimezone=UTC";

    public List<Region> getRegions() {

        List<Region> items = new ArrayList<>();
        try {
            var con = getConnection();
            var stmt = con.createStatement();
            var rs = stmt.executeQuery("select * from region");

            while (rs.next()) {

                var region = new Region(rs.getInt("region_id"),
                        rs.getString("region_name"));

                items.add(region);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return items;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(connectionUrl, "test", "test123");
    }
}
