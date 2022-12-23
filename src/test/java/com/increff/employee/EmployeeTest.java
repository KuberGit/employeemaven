package com.increff.employee;

import org.junit.Test;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;


public class EmployeeTest {
    @Test
    public void sayHello() throws SQLException, IOException, ClassNotFoundException {
        EmployeeJdbcApi api = new EmployeeJdbcApi();
        api.delete();
        api.insert();
        ResultSet rs = api.select();
        int i = 0;
        while(rs.next()) {
            i++;
        }
        assertEquals(3,i);
    }
}
