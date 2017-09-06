package org.lanqiao.service.impl;

import org.lanqiao.entity.PasswordAnswer;
import org.lanqiao.service.PasswordAnswerService;


public class PasswordAnswerServiceImpl implements PasswordAnswerService {
	org.lanqiao.dao.PasswordAnswerDao dao =new org.lanqiao.dao.impl.PasswordAnswerDaoImpl();
	@Override
	public void insertPasswordAnswer(PasswordAnswer passwordAnswer) {
		// TODO Auto-generated method stub
		dao.insert(passwordAnswer);

	}
	@Override
	public void updatePasswordAnswer(PasswordAnswer passwordAnswer) {
		dao.update(passwordAnswer);
		
	}
	@Override
	public PasswordAnswer getPwdById(String userid) {
		// TODO Auto-generated method stub
		return dao.getPwdById(userid);
	}

}
