package top.tangtian.jdbc;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
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
public class JdbcCRUD03 {
    /**
     * 建立连接
     * @return
     */
    private static Connection getConn(){
        //配置文件
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:mysql://120.79.218.62:3306/test?characterEncoding=utf-8");//mysql
        hikariConfig.setUsername("root");
        hikariConfig.setPassword("123456");
        hikariConfig.setMaximumPoolSize(5);
        hikariConfig.addDataSourceProperty("cachePrepStmts", "true");
        hikariConfig.addDataSourceProperty("prepStmtCacheSize", "250");
        hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        HikariDataSource ds = new HikariDataSource(hikariConfig);
        Connection connection = null;
        try {
            connection = ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }


    /**
     * 正确提交事务的小案例
     * @throws SQLException
     */
    public static void transaction() {
        Long stime = System.currentTimeMillis();
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            conn = getConn();
            conn.setAutoCommit(false);  //开启事务，相当于start transaction命令

            String sql = "INSERT INTO order1 (order_no, user_id,payment,payment_type,postage,status,payment_time,send_time,end_time,close_time,create_time,update_time" +
                    ") VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
            st = conn.prepareStatement(sql);
            Date date =  new Date(System.currentTimeMillis());
            BigDecimal a = new BigDecimal(12.00);
            for(int i=1;i<1000;i++){  //i=1000  2000
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
//                if(i%10==0){
//                    st.executeBatch();
//                    st.clearBatch();
//                }
            }
            st.executeBatch();     //执行SQL语句

            conn.commit();   //提交事务
        }catch (SQLException e){
            e.printStackTrace();
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
        }catch (SQLException e){
            e.printStackTrace();
        }
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
            System.out.println("===========t=================");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        JdbcCRUD03.transaction();
    }
}
