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
public class JdbcCRUD02 {
    /**
     * 建立连接
     * @return
     */
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

    /**
     * 释放连接
     * @param rs
     * @param statement
     * @param conn
     */
    public static void release(ResultSet rs, PreparedStatement statement,
                               Connection conn) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

    }

    /**
     * 正确提交事务的小案例
     * @throws SQLException
     */
    public static void transaction() {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            conn = getConn();
            conn.setAutoCommit(false);  //开启事务，相当于start transaction命令

            String sql = "INSERT INTO user_info (name, gender, age) VALUES (?,?,?)";
            st = conn.prepareStatement(sql);
            for(int i=1;i<108;i++){  //i=1000  2000
                st.setString(1, "张三"+ i);
                st.setString(2, "男");
                st.setString(3, "12" + i);
                st.addBatch();
                if(i%10==0){
                    st.executeBatch();
                    st.clearBatch();
                }
            }
            st.executeBatch();     //执行SQL语句
            String sql2 = "INSERT INTO user_info (name, gender, age) VALUES (?,?,?)";
            st = conn.prepareStatement(sql2);
            st.setString(1, "张三");
            st.setString(2, "男");
            st.setString(3, "12" );
            st.executeUpdate();    //执行SQL语句

            conn.commit();   //提交事务
        }catch (SQLException e){
            e.printStackTrace();
        }finally{
            release(rs, st, conn);
        }
    }

    /**
     * 事物回滚
     * @throws SQLException
     */
    public void transactionCallBack() throws SQLException {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            conn = getConn();
            conn.setAutoCommit(false);  //开启事务，相当于start transaction命令

            String sql1 = "update account set money=money-100 where name='a'";
            st = conn.prepareStatement(sql1);
            st.executeUpdate();

            int x = 1/0;      //在此处模拟事务处理过程中出错

            String sql2 = "update account set money=money+100 where name='b'";
            st = conn.prepareStatement(sql2);
            st.executeUpdate();

            conn.commit();   //提交事务
        }finally{
            release(rs, st, conn);
        }
    }

    public static void main(String[] args) {
        JdbcCRUD02.transaction();
    }
}
