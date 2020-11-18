package top.tangtian.jdbc;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author tangtian
 * @version 1.0
 * @className Connection
 * @description
 * @date 2020/11/18 7:10 AM
 **/
public class JdbcCRUD01 {
    private static Connection getConn(){
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://120.79.218.62:3306/ceshi?characterEncoding=utf-8";
        String username = "root";
        String password = "root";
        Connection connection = null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url,username,password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }


    private static int insert() {
        Connection conn = getConn();
        int i = 0;;
        String sql = "INSERT INTO user_info (name, gender, age) VALUES (?,?,?)";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setString(1, "张三");
            pstmt.setString(2, "男");
            pstmt.setString(3, "12");
            i = pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    private static int update() {
        Connection conn = getConn();
        int i = 0;
        String sql = "update user_info set age='40' where id='2' ";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            i = pstmt.executeUpdate();
            System.out.println("resutl: " + i);
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    private static Integer getAll() {
        Connection conn = getConn();
        String sql = "select * from user_info";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement)conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            int col = rs.getMetaData().getColumnCount();
            System.out.println("============================");
            while (rs.next()) {
                for (int i = 1; i <= col; i++) {
                    System.out.print(rs.getString(i) + "\t");
                    if ((i == 2) && (rs.getString(i).length() < 8)) {
                        System.out.print("\t");
                    }
                }
                System.out.println("");
            }
            System.out.println("============================");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static int delete(int id) {
        Connection conn = getConn();
        int i = 0;
        String sql = "delete from user_info where id = " + id;
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            i = pstmt.executeUpdate();
            System.out.println("DeleteResult: " + i);
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    public static void main(String[] args) {
        JdbcCRUD01.insert();
        JdbcCRUD01.update();
        JdbcCRUD01.getAll();
        JdbcCRUD01.delete(1);
    }

}
