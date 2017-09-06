package org.lanqiao.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.lanqiao.dao.GoodsDao;
import org.lanqiao.entity.Goods;
import org.lanqiao.entity.PageInfo;
import org.lanqiao.util.DBUtil;

public class GoodsDaoImpl implements GoodsDao {

	@Override
	public PageInfo<Goods> list(String cid, int pagesize, int pageindex) {
		PageInfo<Goods> pageinfo=new PageInfo<Goods>();
		List<Goods> list=new ArrayList<Goods>();
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			//1、拿到连接
			conn=DBUtil.getConnection();
			//2、根据Sql语句创建preparedstatement对象；
			String sql="select t2.* from (select t1.*,rownum rn from (select *from t_goods where cid=?)t1 where rownum<=?)t2 where rn >=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1,cid);
			int endindex=pagesize*pageindex+pagesize;
			int startindex=pageindex*pagesize;
			ps.setInt(2, endindex);
			ps.setInt(3, startindex);
			//3、执行操作
			rs = ps.executeQuery();
			//4、取数据
			Goods goods=null;
			while(rs.next()){
				goods=new Goods(rs.getString("gid"), rs.getString("gtitle"), rs.getString("gauthor"), rs.getDouble("gsaleprice"), rs.getDouble("ginprice"), rs.getString("gdesc"), rs.getString("gimg"), rs.getInt("gclicks"), rs.getString("cid"), rs.getString("pid"));
				list.add(goods);
			}
			//给page对象赋值；
			pageinfo.setDatas(list);
			pageinfo.setFirstPage(pageindex==1);
			int totalnumber=this.tatalRecords(cid);
			int totalpage=totalnumber%pagesize==0 ?totalnumber/pagesize:totalnumber/pagesize+1;
			pageinfo.setLastPage(totalpage==pageindex);
			pageinfo.setPageIndex(pageindex);
			pageinfo.setPageSize(pagesize);
			pageinfo.setTotalNumber(totalnumber);
			pageinfo.setTotalPages(totalpage);
			
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
		return pageinfo;
	}


	@Override
	public int tatalRecords(String cid) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int total = 0;
		try {
			// 1、拿到连接
			conn = DBUtil.getConnection();
			// 2、根据Sql语句创建preparedstatement对象；
			String sql = "select count(*)from t_goods where cid=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, cid);
			// 3、执行操作
			rs = ps.executeQuery();
			// 4、取数据
			if (rs.next()) {
				total = rs.getInt(1);
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
		return total;
	}


	@Override
	public Goods get(String gid) {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Goods goods=null;
		try {
			//1、拿到连接
			conn=DBUtil.getConnection();
			//2、根据Sql语句创建preparedstatement对象；
			String sql="select * from t_goods where gid=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1,gid);

			//3、执行操作
			rs = ps.executeQuery();
			//4、取数据		
			while(rs.next()){
				goods=new Goods(rs.getString("gid"), rs.getString("gtitle"), rs.getString("gauthor"), rs.getDouble("gsaleprice"), rs.getDouble("ginprice"), rs.getString("gdesc"), rs.getString("gimg"), rs.getInt("gclicks"), rs.getString("cid"), rs.getString("pid"));
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
		return goods;
	}


	@Override
	public void remove(String gid) {
		Connection conn=null;
		PreparedStatement ps = null;
		try {
			//1、拿到连接
			conn=DBUtil.getConnection();
			//2、创建对象
			String sql="delete from t_goods where gid=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, gid);
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
	public void insert(Goods goods) {
		Connection conn=null;
		PreparedStatement ps = null;
		try {
			//1、拿到连接
			conn=DBUtil.getConnection();
			//2、创建对象
			String sql="insert into t_goods values(?,?,?,?,?,?,?,?,?,?)";
			ps=conn.prepareStatement(sql);
			ps.setString(1, goods.getGid());
			ps.setString(2, goods.getGtitle());
			ps.setString(3, goods.getGauthor());
			ps.setDouble(4, goods.getGsaleprice());
			ps.setDouble(5, goods.getGinprice());
			ps.setString(6, goods.getGdesc());
			ps.setString(7, goods.getGimg());
			ps.setInt(8, goods.getGclicks());
			ps.setString(9, goods.getCid());
			ps.setString(10, goods.getPid());
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
	public void update(Goods goods) {
		Connection conn=null;
		PreparedStatement ps = null;
		try {
			//1、拿到连接
			conn=DBUtil.getConnection();
			//2、创建对象
			String sql="update t_goods set gtitle=?,gauthor=?,gsaleprice=?,ginprice=?,gdesc=?,gimg=?,gclicks=?,cid=?,pid=? where gid=?";
			ps=conn.prepareStatement(sql);

			//3、执行操作
			ps.setString(1, goods.getGtitle());
			ps.setString(2, goods.getGauthor());
			ps.setDouble(3, goods.getGsaleprice());
			ps.setDouble(4, goods.getGinprice());
			ps.setString(5, goods.getGdesc());
			ps.setString(6, goods.getGimg());
			ps.setInt(7, goods.getGclicks());
			ps.setString(8, goods.getCid());
			ps.setString(9, goods.getPid());
			ps.setString(10, goods.getGid());
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
