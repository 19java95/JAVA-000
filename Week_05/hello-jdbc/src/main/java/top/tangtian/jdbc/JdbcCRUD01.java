package top.tangtian.jdbc;


import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

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
        String url = "jdbc:mysql://120.79.218.62:3306/examination_mall?characterEncoding=utf-8";
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
        String sql = "INSERT INTO `order` (order_no, user_id, shipping_id,payment,payment_type," +
                "postage,status,payment_time,send_time,end_time,close_time,create_time,update_time)" +
                " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setInt(1,1111111111);
            pstmt.setInt(2, 0);
            pstmt.setString(3,null);
            pstmt.setBigDecimal(4,  new BigDecimal(0.03));
            pstmt.setInt(5, 1);
            pstmt.setInt(6, 110);
            pstmt.setInt(7, 0);
            pstmt.setDate(8, new Date(System.currentTimeMillis()));
            pstmt.setDate(9, new Date(System.currentTimeMillis()));
            pstmt.setDate(10, new Date(System.currentTimeMillis()));
            pstmt.setDate(11, new Date(System.currentTimeMillis()));
            pstmt.setDate(12, new Date(System.currentTimeMillis()));
            pstmt.setDate(13, new Date(System.currentTimeMillis()));
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
        String sql = "update `order` set age='40' where id= '2' ";
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
        String sql = "select * from `order`";
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
        String sql = "delete from `order` where id = " + id;
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
    private static Integer getOne(Object id) {
        Connection conn = getConn();
        String sql = "select * from `order` where `order_no` = " + id;
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
    public static void main(String[] args) {
        long start_time = System.currentTimeMillis();
        //System.out.println(JdbcCRUD01.insert());
        JdbcCRUD01.getOne(1111111111);
        System.out.println(System.currentTimeMillis() - start_time + "ms");
    }


}
