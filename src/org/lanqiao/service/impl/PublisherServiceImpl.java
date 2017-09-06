package org.lanqiao.service.impl;

import java.util.List;

import org.lanqiao.entity.Publisher;
import org.lanqiao.service.PublisherService;

public class PublisherServiceImpl implements PublisherService{
	org.lanqiao.dao.PublisherDao dao=new org.lanqiao.dao.impl.PublisherDaoImpl();
	@Override
	public Publisher getPublisherById(String pid) {
		// TODO Auto-generated method stub
		return dao.get(pid);
	}
	@Override
	public void insertPublisher(Publisher publisher) {
		dao.insert(publisher);
		
	}
	@Override
	public void updatePublisher(Publisher publisher) {
		dao.update(publisher);
		
	}
	@Override
	public List<Publisher> publisherList() {
		// TODO Auto-generated method stub
		return dao.list();
	}
	@Override
	public void removePublisher(String pid) {
		dao.remove(pid);
		
	}

}
