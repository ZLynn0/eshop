package org.lanqiao.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.lanqiao.dao.UserDao;
import org.lanqiao.entity.User;
import org.lanqiao.util.DBUtil;

public class UserDaoImpl implements UserDao {

	public void insert(User user) {
		Connection conn=null;
		PreparedStatement ps = null;
		try {
			//1、拿到连接
			conn=DBUtil.getConnection();
			//2、创建对象
			String sql="insert into t_user values(?,?,?,?,?,?,?,?,?)";
			ps=conn.prepareStatement(sql);
			ps.setString(1, user.getUserid());
			ps.setString(2, user.getUemail());
			ps.setString(3, user.getUloginid());
			ps.setString(4, user.getUpassword());
			ps.setString(5, user.getUsex());
			ps.setString(6, user.getUaddress());
			ps.setString(7, user.getUtel());
			ps.setString(8, user.getUstateid());
			ps.setString(9, user.getUroleid());
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

	@Override
	public User getUserByLoginId(String loginid) {
		User user=null;
		Connection conn=null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		try {
			//1、拿到连接
			conn=DBUtil.getConnection();
			//2、创建对象
			String sql="select * from t_user where uloginid=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, loginid);
			//3、执行操作
			rs=ps.executeQuery();
			//4、取数据
			if (rs.next()) {
				user=new User(rs.getString("userid"), rs.getString("uemail"), rs.getString("uloginid"), rs.getString("upassword"), rs.getString("usex"), rs.getString("uaddress"), rs.getString("utel"), rs.getString("ustateid"), rs.getString("uroleid"));
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
		return user;
	}

	@Override
	public void update(User user) {
		Connection conn=null;
		PreparedStatement ps = null;
		try {
			//1、拿到连接
			conn=DBUtil.getConnection();
			//2、创建对象
			String sql="update t_user set uemail=?, uloginid=?,upassword=?,usex=?,uaddress=?,utel=?,ustateid=?,uroleid=? where userid=?";
			ps=conn.prepareStatement(sql);

			//3、执行操作
			ps.setString(1, user.getUemail());
			ps.setString(2, user.getUloginid());
			ps.setString(3, user.getUpassword());
			ps.setString(4, user.getUsex());
			ps.setString(5, user.getUaddress());
			ps.setString(6, user.getUtel());
			ps.setString(7, user.getUstateid());
			ps.setString(8, user.getUroleid());
			ps.setString(9, user.getUserid());
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
	public List<User> list() {
		List<User> list = new ArrayList<User>();
		User user=null;
		Connection conn=null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		try {
			//1、拿到连接
			conn=DBUtil.getConnection();
			//2、创建对象
			String sql="select * from t_user";
			ps=conn.prepareStatement(sql);
			//3、执行操作
			rs=ps.executeQuery();
			//4、取数据
			while (rs.next()) {
				user=new User(rs.getString("userid"), rs.getString("uemail"), rs.getString("uloginid"), rs.getString("upassword"), rs.getString("usex"), rs.getString("uaddress"), rs.getString("utel"), rs.getString("ustateid"), rs.getString("uroleid"));
				list.add(user);
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
	public void remove(String userid) {
		Connection conn=null;
		PreparedStatement ps = null;
		try {
			//1、拿到连接
			conn=DBUtil.getConnection();
			//2、创建对象
			String sql="delete from t_user where userid=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, userid);
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
	public User getUserByUserid(String userid) {
		// TODO Auto-generated method stub
		User user=null;
		Connection conn=null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		try {
			//1、拿到连接
			conn=DBUtil.getConnection();
			//2、创建对象
			String sql="select * from t_user where userid=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, userid);
			//3、执行操作
			rs=ps.executeQuery();
			//4、取数据
			if (rs.next()) {
				user=new User(rs.getString("userid"), rs.getString("uemail"), rs.getString("uloginid"), rs.getString("upassword"), rs.getString("usex"), rs.getString("uaddress"), rs.getString("utel"), rs.getString("ustateid"), rs.getString("uroleid"));
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
		return user;

	}

}
