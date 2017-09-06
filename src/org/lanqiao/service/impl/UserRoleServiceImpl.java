package org.lanqiao.service.impl;

import java.util.List;

import org.lanqiao.entity.UserRole;
import org.lanqiao.service.UserRoleService;

public class UserRoleServiceImpl implements UserRoleService {
	private org.lanqiao.dao.UserRoleDao dao=new org.lanqiao.dao.impl.UserRoleDaoImpl();
	@Override
	public void insertUserRole(UserRole userRole) {
		dao.insert(userRole);

	}

	@Override
	public void updateUserRole(UserRole userRole) {
		dao.updateRole(userRole);

	}

	@Override
	public List<UserRole> userRoleList() {
		// TODO Auto-generated method stub
		return dao.list();
	}

	@Override
	public void removeUserRole(String uroleid) {
		dao.remove(uroleid);

	}

	@Override
	public UserRole getUserRoleByUroleid(String uroleid) {
		// TODO Auto-generated method stub
		return dao.getUserRoleByUroleid(uroleid);
	}

}
