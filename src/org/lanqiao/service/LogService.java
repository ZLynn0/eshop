package org.lanqiao.service;


import org.lanqiao.entity.Log;
import org.lanqiao.entity.PageInfo;

public interface LogService {
	public PageInfo<Log> logList(String logid,int pagesize,int pageindex);
	public Log getLogBylogid(String logid);
	public void removeLog(String logid);
	public void insertLog(Log log);

}
