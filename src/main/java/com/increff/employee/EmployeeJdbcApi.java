package com.increff.employee;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Logger;


public class EmployeeJdbcApi {

    private static final Logger logger = Logger.getLogger(String.valueOf(EmployeeJdbcApi.class));

    private Connection con;


    public EmployeeJdbcApi() throws IOException, ClassNotFoundException, SQLException {
        Properties props = new Properties();
        InputStream inStream = Files.newInputStream(Paths.get("employee.properties"));
        props.load(inStream);
        Class.forName(props.getProperty("jdbc.driver"));
        con = DriverManager.getConnection(
                props.getProperty("jdbc.url"), props.getProperty("jdbc.username"), props.getProperty("jdbc.password"));
    }


    public void insert() throws SQLException {
        logger.info("inserting employees");
        Statement stmt = con.createStatement();
        for (int i = 0; i < 3; i++) {
            int age = 20 + i;
            String name = "emp "  + i;
            stmt.executeUpdate("insert into employee values(" + i + ", '" + name + "', " + age + ")");
        }
        stmt.close();
    }

    public void delete() throws SQLException {
        logger.info("deleting all employees");
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from employee");
        ArrayList<Integer> idList = new ArrayList<Integer>();
        while (rs.next()) {
            idList.add(rs.getInt(1));
        }
        for(int i=0;i<idList.size();i++){
            stmt.executeUpdate("delete from employee where id =" + idList.get(i));
        }
        stmt.close();
    }

    public ResultSet select() throws SQLException {
        logger.info("selecting employees");
        Statement stmt = con.createStatement();
        return stmt.executeQuery("select * from employee");
/*
        while (rs.next()) {
            logger.info((rs.getInt(1) + " " + rs.getString(2) + " " + rs.getInt(3)));
        }
        stmt.close();
*/
    }
}