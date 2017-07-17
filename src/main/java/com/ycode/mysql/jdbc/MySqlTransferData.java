package com.ycode.mysql.jdbc;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.ycode.model.TestData;

public class MySqlTransferData
{
	public static int insert(TestData student)
	{
		Connection conn = getConn();
		int i = 0;
		String sql = "insert into temp_test (Name,Age,Sex) values(?,?,?)";
		PreparedStatement pstmt;
		try
		{
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setString(1, student.getName());
			pstmt.setString(2, student.getAge());
			pstmt.setString(3, student.getSex());
			i = pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		}
		catch (SQLException e)
		{
			try
			{
				conn.rollback();
			}
			catch (SQLException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return i;
	}

	private static Connection getConn()
	{
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://rm-m5e546q6ls7f2tmd0.mysql.rds.aliyuncs.com:3306/lvpai_prod_test?autoReconnect=true&amp;useUnicode=true&amp;characterEncoding=utf-8&amp;generateSimpleParameterMetadata=true";
		String username = "lvpai_user";
		String password = "lvpai_pass5188";
		Connection conn = null;
		try
		{
			Class.forName(driver); //classLoader,加载对应驱动
			conn = (Connection) DriverManager.getConnection(url, username, password);
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return conn;
	}

	public static void batch(List<TestData> list)
	{
		Connection conn = getConn();
		PreparedStatement pstmt = null;
		String sql = "insert into temp_test (Name,Age,Sex) values(?,?,?)";
		try
		{
			for (TestData testData : list)
			{
				pstmt = (PreparedStatement) conn.prepareStatement(sql);
				pstmt.setString(1, testData.getName());
				pstmt.setString(2, testData.getAge());
				pstmt.setString(3, testData.getSex());
				pstmt.addBatch(sql);
			}
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		}
		catch (SQLException e)
		{
			try
			{
				conn.rollback();
			}
			catch (SQLException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
}
