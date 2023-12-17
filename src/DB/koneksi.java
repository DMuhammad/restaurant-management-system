package DB;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Dzikri
 */
public class koneksi {
    public static Connection connection;
    
    public static Connection koneksi() {
        connection = null;
        try {
            String mySql = "jdbc:mysql://localhost:3306/mystic_grills-db";
            String username = "root";
            String password = "";
            connection = DriverManager.getConnection(mySql, username, password);
        } catch (SQLException e) {
        }
        return connection;
    }
}
