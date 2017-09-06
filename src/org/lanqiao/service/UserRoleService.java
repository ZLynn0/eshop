package org.lanqiao.service;

import java.util.List;
import org.lanqiao.entity.UserRole;

public interface UserRoleService {
	public void insertUserRole(UserRole  userRole);
	public void updateUserRole(UserRole userRole);
	public List<UserRole> userRoleList();
	public void removeUserRole(String uroleid);
	public UserRole getUserRoleByUroleid(String uroleid);
}
