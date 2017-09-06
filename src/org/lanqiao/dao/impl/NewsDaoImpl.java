package org.lanqiao.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.lanqiao.dao.NewsDao;
import org.lanqiao.entity.News;
import org.lanqiao.util.DBUtil;

public class NewsDaoImpl implements NewsDao {

	@Override
	public List<News> list() {
		List<News> list=new ArrayList<News>();
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try {
			//1、拿到连接
			conn=DBUtil.getConnection();
			//2、根据Sql语句创建preparedstatement对象；
			String sql="select *from t_news";
			ps=conn.prepareStatement(sql);
			//3、执行操作
			rs = ps.executeQuery();
			//4、取数据
			News news=null;
			while(rs.next()){
				java.sql.Date sqlDate = rs.getDate("tpubdate");
				SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd");
				String dateString = format.format(sqlDate);
				java.util.Date utilDate =	format.parse(dateString);
				news=new News(rs.getString("tid"), rs.getString("title"), rs.getString("tcontent"), utilDate);
				list.add(news);
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
	public News get(String id) {
		News  news=null;
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try {
			//1、拿到连接
			conn=DBUtil.getConnection();
			//2、根据Sql语句创建preparedstatement对象；
			String sql="select *from t_news where tid=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, id);
			//3、执行操作
			rs = ps.executeQuery();
			//4、取数据
			if(rs.next()){
				news=new News(rs.getString("tid"), rs.getString("title"), rs.getString("tcontent"), rs.getDate("tpubdate"));
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
		return news;
	}

	@Override
	public void removeNews(String tid) {
		Connection conn=null;
		PreparedStatement ps = null;
		try {
			//1、拿到连接
			conn=DBUtil.getConnection();
			//2、创建对象
			String sql="delete from t_news where tid=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, tid);
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
	public void updateNews(News news) {
		Connection conn=null;
		PreparedStatement ps = null;
		try {
			//1、拿到连接
			conn=DBUtil.getConnection();
			//2、创建对象
			String sql="update t_news set title=?,tcontent=?,tpubdate=? where tid=?";
			ps=conn.prepareStatement(sql);

			//3、执行操作
			ps.setString(1,news.getTitle());
			ps.setString(2, news.getTcontent());
			java.sql.Date sqlDate =	new java.sql.Date(news.getTpubdate().getTime());
			ps.setDate(3,sqlDate);
			ps.setString(4, news.getTid());


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
	public void insertNews(News news) {
		Connection conn=null;
		PreparedStatement ps = null;
		try {
			//1、拿到连接
			conn=DBUtil.getConnection();
			//2、创建对象/
			String sql="insert into t_news values(?,?,?,?)";
			ps=conn.prepareStatement(sql);
			ps.setString(1, news.getTid());
			ps.setString(2, news.getTitle());
			ps.setString(3, news.getTcontent());
			java.sql.Date sqlDate =	new java.sql.Date(news.getTpubdate().getTime());
			ps.setDate(4,sqlDate);
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

}
