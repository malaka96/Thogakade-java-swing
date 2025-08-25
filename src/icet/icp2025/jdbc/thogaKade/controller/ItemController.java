/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package icet.icp2025.jdbc.thogaKade.controller;

import icet.icp2025.jdbc.thogaKade.db.DBConnection;
import java.sql.*;
import icet.icp2025.jdbc.thogaKade.model.Item;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author USER
 */
public class ItemController {

    public static boolean addItem(Item item) throws ClassNotFoundException, SQLException {

        String SQL = "Insert into item Values(?,?,?,?)";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement stm = connection.prepareStatement(SQL);
        stm.setObject(1, item.getCode());
        stm.setObject(2, item.getDescription());
        stm.setObject(3, item.getUnitPrice());
        stm.setObject(4, item.getQty());
        int res = stm.executeUpdate();

        return res > 0;

    }

    public static Item searchItem(String code) throws ClassNotFoundException, SQLException {
        String SQL = "select * from item where code = ?";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement stm = connection.prepareStatement(SQL);
        stm.setObject(1, code);
        ResultSet result = stm.executeQuery();
        if (result.next()) {
            return new Item(result.getString("code"), result.getString("description"), result.getDouble("unitPrice"), result.getInt("qtyOnHand"));
        }
        return null;
    }
    
    public static boolean deleteItem(String code) throws ClassNotFoundException, SQLException{
        String SQL = "delete from item where code = ?";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement stm = connection.prepareStatement(SQL);
        stm.setObject(1, code);
        int res = stm.executeUpdate();
        return res>0;
    }
    
    public static ArrayList<Item> getAllItems() throws ClassNotFoundException, SQLException{
        String SQL = "select * from item";
        Connection connection = DBConnection.getInstance().getConnection();
        Statement stm = connection.createStatement();
        ResultSet results = stm.executeQuery(SQL);
        ArrayList<Item> dataList = new ArrayList<>();
        while(results.next()){
            dataList.add(new Item(results.getString("code"),results.getString("description"),results.getDouble("unitPrice"),results.getInt("qtyOnHand")));
        }
        return dataList;
    }

}
