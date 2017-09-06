package org.lanqiao.dao;

import java.util.List;

import org.lanqiao.entity.User;
public interface UserDao {
	public void insert(User user);
	public User getUserByLoginId(String loginid);
	public User getUserByUserid(String userid);
	public void update(User user);
	public List<User> list();
	public void remove(String userid);
}
