package org.lanqiao.dao;

import org.lanqiao.entity.PasswordAnswer;

public interface PasswordAnswerDao {
	public  void insert(PasswordAnswer passwordAnswer);
	public void update(PasswordAnswer answerpPasswordAnswer);
	public PasswordAnswer getPwdById(String userid);
}
