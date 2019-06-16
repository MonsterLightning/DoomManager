package cn.hx.dao;
import java.sql.*;

public class DBConnect {
	Connection conn = null;
	PreparedStatement ps = null;
	Statement stmt=null;
	ResultSet rs = null;

	// 建立数据库连接
	public DBConnect() {
		try {
			//1.加载驱动
//     		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Class.forName("com.mysql.cj.jdbc.Driver");

			//2.获得连接
//			conn=DriverManager.getConnection(
//					"jdbc:sqlserver://localhost:1433;databasename=mybbs", "sa",
//					"123");
			conn=DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/dormmanager?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=Asia/Shanghai", "root",
					"root");

			//3.建立Statement对象(Statement、PreparedStatement)
			stmt=conn.createStatement();
		} catch (SQLException e) {
			System.out.println(e.getMessage() + "路径错误！");

		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage() + "驱动错误！");
		}
	}

	//返回连接
	public Connection getConnection() {
		return conn;
	}

	// 返回PreparedStatement对象
	public PreparedStatement getPS(String sql) throws SQLException {
		//3.建立PreparedStatement对象
		ps=conn.prepareStatement(sql);

		// conn.commit();
		return ps;
	}

	// 返回查询后的结果集
	public ResultSet executeQuery(String sql) {
		try {
			//4.执行sql语句
			rs=stmt.executeQuery(sql);
			//5.处理结果集
//			while(rs.next()){
//				rs.getString("username");
//				rs.getString(2);
//				rs.getInt(3);
//			}

			return rs;
		} catch (SQLException e) {
			System.out.println("DBConnect.executeQuery():ERROR"+e.getMessage());
		}
		return rs;
	}

	// 返回更新影响的记录数
	public int executeUpdate(String sql) {
		int i = 0;
		try {
			//4.执行sql语句
			i=stmt.executeUpdate(sql);
			return i;
		} catch (SQLException e) {
			System.out.println("DBConnect.executeUpdate():ERROR"+e.getMessage());
		}
		return i;
	}

	// 释放数据库连接资源
	public void free() {

		try {
			if(rs!=null) rs.close();
			if(stmt!=null)stmt.close();
			if(conn!=null)conn.close();
		} catch (SQLException e) {
			System.out.println("DBConnect.free():ERROR"+e.getMessage());
		}


	}
}
