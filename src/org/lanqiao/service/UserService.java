package org.lanqiao.service;

import java.util.List;

import org.lanqiao.entity.User;

public interface UserService {
	public void insertUser(User  user);
	//1、拿到登录是否成功；2、用户信息
	public User login(String loginid,String password);
	public User getUserByLoginId(String loginid);
	public User getUserByUserid(String userid);
	public void updateUser(User user);
	public List<User> userList();
	public void removeUser(String userid);
}
