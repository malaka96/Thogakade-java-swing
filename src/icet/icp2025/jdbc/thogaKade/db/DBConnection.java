/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package icet.icp2025.jdbc.thogaKade.db;
import java.sql.*;


/**
 *
 * @author pc
 */
public class DBConnection {
   private Connection connection;  
   private static DBConnection dBConnection;
   private DBConnection() throws ClassNotFoundException, SQLException{
       Class.forName("com.mysql.cj.jdbc.Driver");
       connection=DriverManager.getConnection("jdbc:mysql://localhost/ThogaKade", "root", "1234");
   }
   public static DBConnection getInstance() throws ClassNotFoundException, SQLException{
       if(dBConnection==null){
           dBConnection=new DBConnection();
       }
       return dBConnection;
   }
   public Connection getConnection(){
       return connection;
   }
}
