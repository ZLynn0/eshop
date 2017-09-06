package org.lanqiao.dao;

import java.util.List;

import org.lanqiao.entity.UserRole;

public interface UserRoleDao {
	public void updateRole(UserRole userRole);
	public List<UserRole> list();
	public void remove(String uroleid);
	public void insert(UserRole userRole);
	public UserRole getUserRoleByUroleid(String uroleid);
}
