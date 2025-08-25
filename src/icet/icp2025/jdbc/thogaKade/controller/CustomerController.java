/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package icet.icp2025.jdbc.thogaKade.controller;

import icet.icp2025.jdbc.thogaKade.db.DBConnection;
import icet.icp2025.jdbc.thogaKade.model.Customer;
import java.util.ArrayList;
import java.sql.*;

/**
 *
 * @author pc
 */
public class CustomerController {

    public static boolean addCustomer(Customer customer) throws ClassNotFoundException, SQLException {
        String SQL = "Insert into Customer Values(?,?,?,?)";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement stm = connection.prepareStatement(SQL);
        stm.setObject(1, customer.getId());
        stm.setObject(2, customer.getName());
        stm.setObject(3, customer.getAddress());
        stm.setObject(4, customer.getSalary());
        int res = stm.executeUpdate();
        return res > 0;
    }

    public static Customer searchCustomer(String id) throws ClassNotFoundException, SQLException {
        String SQL = "Select * From Customer where id='" + id + "'";
        Connection connection = DBConnection.getInstance().getConnection();
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery(SQL);
        if (rst.next()) {
            String name = rst.getString("name");
            String address = rst.getString("address");
            double salary = rst.getDouble("Salary");
            return new Customer(id, name, address, salary);
        }
        return null;
    }

    public static boolean deleteCustomer(String id) throws ClassNotFoundException, SQLException {
        String SQL = "Delete From Customer where id='" + id + "'";
        Statement stm = DBConnection.getInstance().getConnection().createStatement();
        return stm.executeUpdate(SQL) > 0;
    }

    public static boolean updateCustomer(Customer customer) throws ClassNotFoundException, SQLException {
        String SQL = "Update Customer set name=?, address=?, salary=? where id=?";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement stm = connection.prepareStatement(SQL);
        stm.setObject(4, customer.getId());
        stm.setObject(1, customer.getName());
        stm.setObject(2, customer.getAddress());
        stm.setObject(3, customer.getSalary());
        return stm.executeUpdate() > 0;
    }

    public static ArrayList<Customer> getAllCustomers() throws ClassNotFoundException, SQLException {
        String SQL = "Select * From Customer";
        Connection connection = DBConnection.getInstance().getConnection();
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery(SQL);
        ArrayList<Customer> customerList = new ArrayList<>();
        while (rst.next()) {
            customerList.add(new Customer(rst.getString("id"), rst.getString("name"), rst.getString("address"), rst.getDouble("salary")));
        }
        return customerList;
    }
}
