/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author lotso
 */
public class Koneksi {
    private static Connection con;
    public static Connection getCon(){
        if(con == null){
            try {
                String url = "jdbc:mysql://localhost/mvc_pizza";
                String user= "root";
                String pass = "";
                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
                con = DriverManager.getConnection(url,user,pass);
                JOptionPane.showMessageDialog(null, "Koneksi berhasil");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Koneksi tidak berhasil");
            }
        }
        return con;
    }
}
