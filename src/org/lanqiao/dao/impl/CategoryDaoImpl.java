package org.lanqiao.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.lanqiao.dao.CategoryDao;
import org.lanqiao.entity.Category;
import org.lanqiao.util.DBUtil;

public class CategoryDaoImpl implements CategoryDao {

	@Override
	public List<Category> list() {
		List<Category> list=new ArrayList<Category>();
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try {
			//1、拿到连接
			conn=DBUtil.getConnection();
			//2、根据Sql语句创建preparedstatement对象；
			String sql="select *from t_category";
			ps=conn.prepareStatement(sql);
			//3、执行操作
			rs = ps.executeQuery();
			//4、取数据
			Category cate=null;
			while(rs.next()){
				cate=new Category(rs.getString("cid"), rs.getString("cname"));
				list.add(cate);
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
		return list;
	}

	@Override
	public Category get(String cid) {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Category cate=null;
		try {
			//1、拿到连接
			conn=DBUtil.getConnection();
			//2、根据Sql语句创建preparedstatement对象；
			String sql="select *from t_category where cid=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1,cid);
			//3、执行操作
			rs = ps.executeQuery();
			//4、取数据
			if(rs.next()){
				cate=new Category(rs.getString("cid"), rs.getString("cname"));
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
		return cate;
	}

	@Override
	public void updateCategory(Category category) {
		Connection conn=null;
		PreparedStatement ps = null;
		try {
			//1、拿到连接
			conn=DBUtil.getConnection();
			//2、创建对象
			String sql="update t_category set cname=? where cid=?";
			ps=conn.prepareStatement(sql);

			//3、执行操作			
			ps.setString(1, category.getCname());
			ps.setString(2, category.getCid());
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
	public void remove(String cid) {
		Connection conn=null;
		PreparedStatement ps = null;
		try {
			//1、拿到连接
			conn=DBUtil.getConnection();
			//2、创建对象
			String sql="delete from t_category where cid=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, cid);
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
	public void insert(Category category) {
		Connection conn=null;
		PreparedStatement ps = null;
		try {
			//1、拿到连接
			conn=DBUtil.getConnection();
			//2、创建对象
			String sql="insert into t_category values(?,?)";
			ps=conn.prepareStatement(sql);
			ps.setString(1, category.getCid());
			ps.setString(2, category.getCname());

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
