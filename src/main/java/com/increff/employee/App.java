package com.increff.employee;

import java.io.IOException;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
        HibernateUtil.configure();
        EmployeeHibernateApi api = new EmployeeHibernateApi();

        api.deleteAll();

        for(int i = 0;i<5;i++) {
            EmployeePojo p = new EmployeePojo();
            p.setAge(i+20);
            p.setId(i);
            p.setName("username" + i);
            api.insert(p);
        }

        api.printAll();

    }
}
