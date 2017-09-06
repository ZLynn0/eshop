package org.lanqiao.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.lanqiao.dao.PublisherDao;
import org.lanqiao.entity.Publisher;
import org.lanqiao.util.DBUtil;

public class PublisherDaoImpl implements PublisherDao {

	@Override
	public Publisher get(String pid) {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Publisher pub =null;
		try {
			//1、拿到连接
			conn=DBUtil.getConnection();
			//2、根据Sql语句创建preparedstatement对象；
			String sql="select * from t_publisher where pid=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1,pid);
			//3、执行操作
			rs = ps.executeQuery();
			//4、取数据
			if(rs.next()){
				pub=new Publisher(rs.getString("pid"), rs.getString("pname"));
			}		
		} catch (Exception e) {
			// TODO: handle exception
		}
		finally{
			//5关闭对象
		try {
			if(rs!=null)	rs.close();
			if(ps!=null)	ps.close();
			if(conn!=null)	conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		// TODO Auto-generated method stub
		return pub;
	}

	@Override
	public void update(Publisher publisher) {
		Connection conn=null;
		PreparedStatement ps = null;
		try {
			//1、拿到连接
			conn=DBUtil.getConnection();
			//2、创建对象
			String sql="update t_publisher set pname=? where pid=?";
			ps=conn.prepareStatement(sql);

			//3、执行操作			
			ps.setString(1, publisher.getPname());
			ps.setString(2, publisher.getPid());
			ps.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			//5关闭对象
			try {
				if(ps!=null)	ps.close();
				if(conn!=null)	conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public List<Publisher> list() {
		List<Publisher> list = new ArrayList<Publisher>();
		Publisher publisher=null;
		Connection conn=null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		try {
			//1、拿到连接
			conn=DBUtil.getConnection();
			//2、创建对象
			String sql="select * from t_publisher";
			ps=conn.prepareStatement(sql);
			//3、执行操作
			rs=ps.executeQuery();
			//4、取数据
			while (rs.next()) {
				publisher=new Publisher(rs.getString("pid"), rs.getString("pname"));
				list.add(publisher);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			//5关闭对象
			try {
				if (rs!=null) {
					rs.close();
				}
				if(ps!=null)	ps.close();
				if(conn!=null)	conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// TODO Auto-generated method stub
		return list;
	}

	@Override
	public void remove(String pid) {
		Connection conn=null;
		PreparedStatement ps = null;
		try {
			//1、拿到连接
			conn=DBUtil.getConnection();
			//2、创建对象
			String sql="delete from t_publisher where pid=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, pid);
			//3、执行操作
			ps.executeQuery();		
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			//5关闭对象
			try {
				if(ps!=null)	ps.close();
				if(conn!=null)	conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void insert(Publisher publisher) {
		Connection conn=null;
		PreparedStatement ps = null;
		try {
			//1、拿到连接
			conn=DBUtil.getConnection();
			//2、创建对象
			String sql="insert into t_publisher values(?,?)";
			ps=conn.prepareStatement(sql);
			ps.setString(1, publisher.getPid());
			ps.setString(2, publisher.getPname());

			//3、执行操作
			ps.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			//5关闭对象
			try {
				if(ps!=null)	ps.close();
				if(conn!=null)	conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	}

