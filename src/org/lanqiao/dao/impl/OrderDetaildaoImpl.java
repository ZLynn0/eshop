package org.lanqiao.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.lanqiao.dao.OrderDetailDao;
import org.lanqiao.entity.OrderDetail;
import org.lanqiao.util.DBUtil;

public class OrderDetaildaoImpl implements OrderDetailDao {

	@Override
	public void insertOrderDetail(OrderDetail detail) {
		Connection conn=null;
		PreparedStatement ps = null;
		try {
			//1、拿到连接
			conn=DBUtil.getConnection();
			//2、创建对象
			String sql="insert into t_orderdetail values(?,?,?,?,?)";
			ps=conn.prepareStatement(sql);
			ps.setString(1, detail.getOrderdetailid());
			ps.setString(2,detail.getGtitle());			
			ps.setDouble(3, detail.getGsaleprice());
			ps.setInt(4, detail.getGnumber());
			ps.setString(5, detail.getOrderid());
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
	public List<OrderDetail> list(String orderid) {

		List<OrderDetail> list=new ArrayList<OrderDetail>();
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			//1、拿到连接
			conn=DBUtil.getConnection();
			//2、根据Sql语句创建preparedstatement对象；
			String sql="select * from t_orderdetail where orderid=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, orderid);
			//3、执行操作
			rs = ps.executeQuery();
			//4、取数据
			OrderDetail orderdetail=null;
			while (rs.next()) {
				orderdetail=new OrderDetail(rs.getString("ORDERDETAILID"), rs.getString("GTITLE"), rs.getDouble("GSALPRICE"), rs.getInt("GNUMBER"),  rs.getString("orderid"));
				list.add(orderdetail);
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

}
