package org.lanqiao.service.impl;

import org.lanqiao.dao.LogDao;
import org.lanqiao.dao.impl.LogDaoImpl;
import org.lanqiao.entity.Log;
import org.lanqiao.entity.PageInfo;
import org.lanqiao.service.LogService;

public class LogServiceImpl implements LogService{
	LogDao dao=new LogDaoImpl();

	@Override
	public PageInfo<Log> logList(String logid, int pagesize, int pageindex) {
		// TODO Auto-generated method stub
		return dao.list(logid, pagesize, pageindex);
	}

	@Override
	public Log getLogBylogid(String logid) {
		// TODO Auto-generated method stub
		return dao.getLog(logid);
	}

	@Override
	public void removeLog(String logid) {
		dao.removeLog(logid);
		
	}

	@Override
	public void insertLog(Log log) {
		dao.insertLog(log);
		
	}

}
