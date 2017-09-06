package org.lanqiao.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.lanqiao.dao.OrderDao;
import org.lanqiao.entity.Order;
import org.lanqiao.util.DBUtil;

public class OrderDaoImpl implements OrderDao {
	
	@Override
	public void insert(Order order) {
		Connection conn=null;
		PreparedStatement ps = null;
		try {
			//1、拿到连接
			conn=DBUtil.getConnection();
			//2、创建对象/
			String sql="insert into t_order values(?,?,?,?,?)";
			ps=conn.prepareStatement(sql);
			ps.setString(1, order.getOrderid());
			ps.setString(2, order.getGid());
			ps.setString(3, order.getUserid());
			ps.setDouble(4, order.getTotalprice());
			java.sql.Date sqlDate =	new java.sql.Date(order.getOrderDate().getTime());
			ps.setDate(5,sqlDate);
			ps.executeUpdate();

			//3、执行操作
			
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
	public List<Order> list(String userid) {
		// TODO Auto-generated method stub
		List<Order> list=new ArrayList<Order>();
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			//1、拿到连接
			conn=DBUtil.getConnection();
			//2、根据Sql语句创建preparedstatement对象；
			String sql="select * from t_order where userid=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, userid);
			//3、执行操作
			rs = ps.executeQuery();
			//4、取数据
			Order order=null;
			while(rs.next()){
				
				order=new Order(rs.getString("orderid"),rs.getString("gid") , rs.getString("userid"), rs.getDouble("totalprice"),  rs.getDate("orderDate"));
				list.add(order);
			}
		}catch (Exception e) {
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
		return list;
	}

	@Override
	public List<Order> list() {
		// TODO Auto-generated method stub
		List<Order> list=new ArrayList<Order>();
		Order order=null;
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			//1、拿到连接
			conn=DBUtil.getConnection();
			//2、根据Sql语句创建preparedstatement对象；
			String sql="select orderid,gid,userid,totalprice,orderdate from t_order";
			ps=conn.prepareStatement(sql);
			//3、执行操作
			rs = ps.executeQuery();
			//4、取数据
			while(rs.next()){
				java.sql.Date sqlDate = rs.getDate("orderDate");
				SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd");
				String dateString = format.format(sqlDate);
				java.util.Date utilDate =	format.parse(dateString);
				order=new Order(rs.getString("orderid"),rs.getString("gid") , rs.getString("userid"), rs.getDouble("totalprice"),utilDate);
				list.add(order);
			}
		}catch (Exception e) {
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
		return list;
	}

	@Override
	public void removeorder(String orderid) {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement ps = null;
		try {
			//1、拿到连接
			conn=DBUtil.getConnection();
			//2、创建对象
			String sql="delete from t_order where orderid=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, orderid);
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
	public void updateorder(Order order) {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement ps = null;
		try {
			//1、拿到连接
			conn=DBUtil.getConnection();
			//2、创建对象
			String sql="update t_order set gid=?,userid=?,totalprice=?,orderdate=? where orderid=?";
			ps=conn.prepareStatement(sql);

			//3、执行操作
			ps.setString(1, order.getGid());
			ps.setString(2, order.getUserid());
			ps.setDouble(3, order.getTotalprice());
			java.sql.Date sqlDate =	new java.sql.Date(order.getOrderDate().getTime());
			ps.setDate(4,sqlDate);
//			SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd");
//			String dateString = format.format(order.getOrderDate());
//			java.sql.Date sqlDate =	java.sql.Date.valueOf(dateString);
//			ps.setDate(4,sqlDate);
			ps.setString(5, order.getOrderid());


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
	public Order getOrderByOrderid(String orderid) {
		Order order=null;
		Connection conn=null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		try {
			//1、拿到连接
			conn=DBUtil.getConnection();
			//2、创建对象
			String sql="select * from t_order where orderid=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, orderid);
			//3、执行操作
			rs=ps.executeQuery();
			//4、取数据
			if (rs.next()) {
				order=new Order(rs.getString("orderid"), rs.getString("gid"), rs.getString("userid"), rs.getDouble("totalprice"), rs.getDate("orderDate"));
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
		return order;
	}
	}
