package top.tangtian.jdbc;


import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
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
        String url = "jdbc:mysql://120.79.218.62:3306/test?characterEncoding=utf-8";
        String username = "root";
        String password = "123456";
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
        Long stime = System.currentTimeMillis();
        try{
            conn = getConn();
            conn.setAutoCommit(false);  //开启事务，相当于start transaction命令
           // INSERT INTO `test`.`order` (`id`, `order_no`, `user_id`, `payment`, `payment_type`, `postage`, `status`, `payment_time`, `send_time`, `end_time`, `close_time`, `create_time`, `update_time`)
            // //VALUES ('12', '11', '1', '12.00', '1', '833', '1', '2020-11-30 10:49:31', '2020-11-30 10:49:31', '2020-11-30 10:49:31', '2020-11-30 10:49:31', '2020-11-30 10:49:31', '2020-11-30 10:49:31');
            String sql = "INSERT INTO order1 (order_no, user_id,payment,payment_type,postage,status,payment_time,send_time,end_time,close_time,create_time,update_time" +
                    ") VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
            st = conn.prepareStatement(sql);
           Date date =  new Date(System.currentTimeMillis());
            BigDecimal a = new BigDecimal(12.00);
            for(int i=1;i<=1000;i++){  //i=1000  2000
                st.setInt(1, i);
                st.setInt(2, 1);
                st.setBigDecimal(3,a);
                st.setInt(4, 1);
                st.setInt(5, 833);
                st.setInt(6, 1);
                st.setDate(7,date);
                st.setDate(8,date);
                st.setDate(9,date);
                st.setDate(10,date);
                st.setDate(11,date);
                st.setDate(12,date);


                st.addBatch();
//                if(i%1000==0){
//                    st.executeBatch();
//                    st.clearBatch();
//                }
            }
            st.executeBatch();     //执行SQL语句
            conn.commit();   //提交事务
        }catch (SQLException e){
            e.printStackTrace();
        }finally{
            release(rs, st, conn);
        }
        System.out.println(System.currentTimeMillis() - stime);
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
