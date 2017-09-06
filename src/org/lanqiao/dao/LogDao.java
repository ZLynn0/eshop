package org.lanqiao.dao;

import org.lanqiao.entity.Log;
import org.lanqiao.entity.PageInfo;

public interface LogDao {
	public PageInfo<Log> list(String logid,int pagesize,int pageindex);
	public int totalSum(String logid);
	public Log getLog(String logid);
	public void removeLog(String logid);
	public void insertLog(Log log);

}
