/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.entity_Score;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author ngnha
 */
public class DAOScore {

    public static List<entity_Score> addScore() {
        
        List<entity_Score> ScoreList = new ArrayList<>();
        java.sql.Connection conn = null;
        Statement st = null; //dt tĩnh
        try {
            String dbURL = "jdbc:mysql://localhost/sem2_da";
            String username = "root";
            String password = "";
            conn = DriverManager.getConnection(dbURL, username, password);
            if (conn != null) {
                System.out.println("Kết nối thành công");
            }
            String sql = "SELECT * FROM score ORDER BY Score DESC;";
            st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql); //gọi sql
            while (rs.next()) {
                entity_Score sc = new entity_Score(
                        rs.getInt("ID"),
                        rs.getString("Name"),
                        rs.getInt("Score"),
                        rs.getString("Times"),
                        rs.getString("Dates"));
                ScoreList.add(sc);
            }
            conn.close();
        } catch (Exception ex) {
            System.out.println("Lấy dữ liệu bảng thất bại!");
            System.out.println(ex.getMessage());
        } 
        return ScoreList;
    }
    
    public static List<entity_Score> addScoreMember() {
        List<entity_Score> ScoreList = new ArrayList<>();
        java.sql.Connection conn = null;
        Statement st = null;
        try {
            String dbURL = "jdbc:mysql://localhost/sem2_da";
            String username = "root";
            String password = "";
            conn = DriverManager.getConnection(dbURL, username, password);
            if (conn != null) {
                System.out.println("Kết nối thành công");
            }
            String sql = "SELECT ID,Name, MAX(Score) AS 'Score',Times,Dates\n"
                    + "FROM score\n"
                    + "GROUP BY Name\n"
                    + "ORDER BY Score DESC";
            st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                entity_Score sc = new entity_Score(
                        rs.getInt("ID"),
                        rs.getString("Name"),
                        rs.getInt("Score"),
                        rs.getString("Times"),
                        rs.getString("Dates"));
                ScoreList.add(sc);
            }
            conn.close();
        } catch (Exception ex) {
            System.out.println("Lấy dữ liệu bảng thất bại!");
            System.out.println(ex.getMessage());
        } 
        return ScoreList;
    }
    
    public static void Them(entity_Score score) {
        Connection conn = null;
        PreparedStatement st = null; //dt động, thay thêm tham số
        try {
            String dbURL = "jdbc:mysql://localhost/sem2_da";
            String username = "root";
            String password = "";
            conn = DriverManager.getConnection(dbURL, username, password);
            String sql = "INSERT INTO score (Name, Score,Times,Dates) VALUES (?, ?, ?, ?)";
            st = conn.prepareCall(sql);
            st.setString(1, score.getName());
            st.setInt(2, score.getScore());
            st.setString(3, String.valueOf(score.getTimes()));
            st.setString(4, String.valueOf(score.getDates()));
            st.execute();
            conn.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Thêm thông tin thất bại !");
            System.out.println(ex.getMessage());
        }
    }

    public static void Xoa(String ID) {
        Connection conn = null;
        PreparedStatement st = null;
        try {
            String dbURL = "jdbc:mysql://localhost/sem2_da";
            String username = "root";
            String password = "";
            conn = DriverManager.getConnection(dbURL, username, password);
            String sql = "delete from score where id=?";
            st = conn.prepareCall(sql);
            st.setString(1, ID);
            st.execute();
            conn.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
