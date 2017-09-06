package org.lanqiao.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.lanqiao.dao.PasswordAnswerDao;
import org.lanqiao.entity.PasswordAnswer;
import org.lanqiao.util.DBUtil;

public class PasswordAnswerDaoImpl implements PasswordAnswerDao {

	@Override
	public void insert(PasswordAnswer passwordAnswer) {
		Connection conn=null;
		PreparedStatement ps = null;
		try {
			//1、拿到连接
			conn=DBUtil.getConnection();
			//2、创建对象
			String sql="insert into t_passwordanswer values(?,?,?,?,?)";
			ps=conn.prepareStatement(sql);
			ps.setString(1,passwordAnswer.getAnswerid());
			ps.setString(2,passwordAnswer.getAquestion());
			ps.setString(3, passwordAnswer.getAnswer());
			ps.setString(4, passwordAnswer.getEmail());
			ps.setString(5, passwordAnswer.getUserid());
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
	public void update(PasswordAnswer answerPasswordAnswer) {
		Connection conn=null;
		PreparedStatement ps = null;
		try {
			//1、拿到连接
			conn=DBUtil.getConnection();
			//2、创建对象
			String sql="update t_passwordanswer set aquestion=?, answer=?,email=?,userid=? where answerid=?";
			ps=conn.prepareStatement(sql);

			//3、执行操作
			ps.setString(1, answerPasswordAnswer.getAquestion());
			ps.setString(2, answerPasswordAnswer.getAnswer());
			ps.setString(3, answerPasswordAnswer.getEmail());
			ps.setString(4, answerPasswordAnswer.getUserid());
			ps.setString(5, answerPasswordAnswer.getAnswerid());
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
	public PasswordAnswer getPwdById(String userid) {
		
		// TODO Auto-generated method stub
		PasswordAnswer passwordAnswer=null;
		Connection conn=null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		try {
			//1、拿到连接
			conn=DBUtil.getConnection();
			//2、创建对象
			String sql="select * from t_passwordanswer where userid=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, userid);
			//3、执行操作
			rs=ps.executeQuery();
			//4、取数据
			if (rs.next()) {
				passwordAnswer=new PasswordAnswer(rs.getString("answerid"), rs.getString("aquestion"), rs.getString("answer"), rs.getString("email"), rs.getString("userid"));
			}
			
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
		return passwordAnswer;
		
	}
	}

