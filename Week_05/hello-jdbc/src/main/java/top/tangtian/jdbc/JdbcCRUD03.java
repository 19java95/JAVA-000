package top.tangtian.jdbc;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
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
        hikariConfig.setJdbcUrl("jdbc:mysql://120.79.218.62:3306/ceshi?characterEncoding=utf-8");//mysql
        hikariConfig.setUsername("root");
        hikariConfig.setPassword("root");
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
            System.out.println("============================");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        JdbcCRUD03.getAll();
    }
}
