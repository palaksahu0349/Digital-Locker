package com.digitallocker.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.digitalloker.DBConnection;
public class UserDAO {
    // REGISTER 
    public boolean register(String username,String password) {
        try {
            Connection con = DBConnection.getConnection();
            String sql =
                    "INSERT INTO users(username, password) VALUES(?, ?)";
            PreparedStatement ps =con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    //  LOGIN 
    public boolean login(String username,String password) {
        try {
            Connection con =DBConnection.getConnection();
            String sql =
                    "SELECT * FROM users WHERE username=? AND password=?";
            PreparedStatement ps =con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs =ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}