package org.lanqiao.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.lanqiao.dao.UserRoleDao;
import org.lanqiao.entity.UserRole;
import org.lanqiao.util.DBUtil;

public class UserRoleDaoImpl implements UserRoleDao {

	@Override
	public void updateRole(UserRole userRole) {
		Connection conn=null;
		PreparedStatement ps = null;
		try {
			//1、拿到连接
			conn=DBUtil.getConnection();
			//2、创建对象
			String sql="update t_userrole set urolename=? where uroleid=?";
			ps=conn.prepareStatement(sql);

			//3、执行操作			
			ps.setString(1, userRole.getUrolename());
			ps.setString(2, userRole.getUroleid());
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
	public List<UserRole> list() {
		List<UserRole> list = new ArrayList<UserRole>();
		UserRole userRole=null;
		Connection conn=null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		try {
			//1、拿到连接
			conn=DBUtil.getConnection();
			//2、创建对象
			String sql="select * from t_userrole";
			ps=conn.prepareStatement(sql);
			//3、执行操作
			rs=ps.executeQuery();
			//4、取数据
			while (rs.next()) {
				userRole=new UserRole(rs.getString("uroleid"), rs.getString("urolename"));
				list.add(userRole);
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
	public void remove(String uroleid) {
		Connection conn=null;
		PreparedStatement ps = null;
		try {
			//1、拿到连接
			conn=DBUtil.getConnection();
			//2、创建对象
			String sql="delete from t_userrole where uroleid=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, uroleid);
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
	public void insert(UserRole userRole) {
		Connection conn=null;
		PreparedStatement ps = null;
		try {
			//1、拿到连接
			conn=DBUtil.getConnection();
			//2、创建对象
			String sql="insert into t_userrole values(?,?)";
			ps=conn.prepareStatement(sql);
			ps.setString(1, userRole.getUroleid());
			ps.setString(2, userRole.getUrolename());

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
	public UserRole getUserRoleByUroleid(String uroleid) {
		UserRole userRole=null;
		Connection conn=null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		try {
			//1、拿到连接
			conn=DBUtil.getConnection();
			//2、创建对象
			String sql="select * from t_userrole where uroleid=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, uroleid);
			//3、执行操作
			rs=ps.executeQuery();
			//4、取数据
			if (rs.next()) {
				userRole=new UserRole(rs.getString("uroleid"), rs.getString("urolename"));
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
		return userRole;
	}

}
