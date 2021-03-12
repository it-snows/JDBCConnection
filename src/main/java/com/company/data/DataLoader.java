package com.company.data;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


    public class DataLoader {

        private static final String connectionUrl = "jdbc:mysql://localhost:3306/population?serverTimezone=UTC";
        private static final String FILE_PATH = "src/main/java/com/company/data/county.csv";

        public List<County> readCounty() {
            List<County> counties = new ArrayList<>();
            try {
                Reader in = new FileReader(FILE_PATH);

                Iterable<CSVRecord> records = CSVFormat.DEFAULT
                        .withDelimiter(';')
                        .withHeader()
                        .parse(in);

                return StreamSupport.stream(records.spliterator(), false)
                        .map(c -> new County(
                                c.get("name"),
                                Integer.parseInt(c.get("id"))))
                        .collect(Collectors.toList());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return counties;
        }

        public void loadCSV() throws SQLException {
            var con = getConnection();

            var counties = readCounty();

            String sqlStatement = "INSERT INTO county (county_name,county_region_id) VALUES (?,?)";
            PreparedStatement statement = con.prepareStatement(sqlStatement);

            for (County county : counties) {
                statement.setString(1, county.getName());
                statement.setInt(2, county.getId());
                statement.executeUpdate();
            }
        }

        private Connection getConnection() throws SQLException {
            return DriverManager.getConnection(connectionUrl, "test", "test123");
        }
    }

