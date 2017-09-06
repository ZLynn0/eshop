package org.lanqiao.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.lanqiao.dao.LogDao;
import org.lanqiao.entity.Log;
import org.lanqiao.entity.PageInfo;
import org.lanqiao.util.DBUtil;

public class LogDaoImpl implements LogDao {

	@Override
	public PageInfo<Log> list(String logid,int pagesize, int pageindex) {
		PageInfo<Log> pageinfo=new PageInfo<Log>();
		List<Log> list=new ArrayList<Log>();
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			//1、拿到连接
			conn=DBUtil.getConnection();
			//2、根据Sql语句创建preparedstatement对象；
			String sql="select t2.* from (select t1.*,rownum rn from (select *from log where cid=?)t1 where rownum<=?)t2 where rn >=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1,logid);
			int endindex=pagesize*pageindex+pagesize;
			int startindex=pageindex*pagesize;
			ps.setInt(2, endindex);
			ps.setInt(3, startindex);
			//3、执行操作
			rs = ps.executeQuery();
			//4、取数据
			Log log=null;
			while(rs.next()){
				java.sql.Date sqlDate = rs.getDate("odate");
				SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd");
				String dateString = format.format(sqlDate);
				java.util.Date utilDate =	format.parse(dateString);
				log=new Log(rs.getString("logid"), rs.getString("uloginid"), rs.getString("options"),utilDate );
				list.add(log);
			}
			//给page对象赋值；
			pageinfo.setDatas(list);
			pageinfo.setFirstPage(pageindex==1);
			int totalnumber=this.totalSum(logid);
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
	public int totalSum(String logid) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int total = 0;
		try {
			// 1、拿到连接
			conn = DBUtil.getConnection();
			// 2、根据Sql语句创建preparedstatement对象；
			String sql = "select count(*)from log where logid=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, logid);
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
	public Log getLog(String logid) {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Log log=null;
		try {
			//1、拿到连接
			conn=DBUtil.getConnection();
			//2、根据Sql语句创建preparedstatement对象；
			String sql="select * from log where logid=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1,logid);

			//3、执行操作
			rs = ps.executeQuery();
			//4、取数据		
			while(rs.next()){
				java.sql.Date sqlDate = rs.getDate("odate");
				SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd");
				String dateString = format.format(sqlDate);
				java.util.Date utilDate =	format.parse(dateString);
				log=new Log(rs.getString("logid"), rs.getString("uloginid"), rs.getString("options"), utilDate);
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
		return log;
		
	}

	@Override
	public void removeLog(String logid) {
		Connection conn=null;
		PreparedStatement ps = null;
		try {
			//1、拿到连接
			conn=DBUtil.getConnection();
			//2、创建对象
			String sql="delete from log where logid=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, logid);
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
	public void insertLog(Log log) {
		Connection conn=null;
		PreparedStatement ps = null;
		try {
			//1、拿到连接
			conn=DBUtil.getConnection();
			//2、创建对象
			String sql="insert into log values(?,?,?,?)";
			ps=conn.prepareStatement(sql);
			ps.setString(1, log.getLogid());
			ps.setString(2, log.getUloginid());
			ps.setString(3, log.getOptions());
			java.sql.Date sqlDate =	new java.sql.Date(log.getOdate().getTime());
			ps.setDate(4,sqlDate);
			
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
