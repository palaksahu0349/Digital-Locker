package com.digitallocker.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.digitalloker.DBConnection;
public class DocumentDAO {
    // Upload document
    public boolean upload(String username, String name, String path) {
        try {
            Connection con = DBConnection.getConnection();
            String sql =
                    "INSERT INTO documents(username, doc_name, file_path) VALUES(?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, name);
            ps.setString(3, path);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    // Get documents
    public ArrayList<String[]> getDocuments(String username) {
        ArrayList<String[]> list = new ArrayList<>();
        try {
            Connection con = DBConnection.getConnection();
            String sql =
                    "SELECT doc_name, file_path FROM documents WHERE username=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String[] row = new String[2];
                row[0] = rs.getString("doc_name");
                row[1] = rs.getString("file_path");
                list.add(row);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    // Delete document
    public boolean delete(String path) {
        try {
            Connection con = DBConnection.getConnection();
            String sql =
                    "DELETE FROM documents WHERE file_path=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, path);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;}
    }
    //  Search documents
    public ArrayList<String[]> searchDocuments(String username, String keyword) {
        ArrayList<String[]> list = new ArrayList<>();
        try {
            Connection con = DBConnection.getConnection();
            String sql =
                    "SELECT doc_name, file_path FROM documents WHERE username=? AND doc_name LIKE ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String[] row = new String[2];
                row[0] = rs.getString("doc_name");
                row[1] = rs.getString("file_path");
                list.add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}