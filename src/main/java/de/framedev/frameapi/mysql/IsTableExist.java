 package de.framedev.frameapi.mysql;
 
 import java.sql.ResultSet;
 import java.sql.SQLException;
 import java.sql.Statement;
 
 
 
 
 
 
 
 
 public class IsTableExist
 {
   public static boolean isExist(String table) {
     MYSQL.connect();
     try {
       Statement statement = MYSQL.getConnection().createStatement();
       ResultSet rs = statement.executeQuery("SHOW TABLES LIKE '" + table + "'");
       if (rs.next()) {
         return true;
       }
       return false;
     
     }
     catch (SQLException e) {
       e.printStackTrace();
       
       return false;
     } 
   }
 }


