package org.lanqiao.service;

import java.util.List;

import org.lanqiao.entity.Publisher;


public interface PublisherService {
	public Publisher getPublisherById(String pid);
	public void insertPublisher(Publisher  publisher);
	public void updatePublisher(Publisher publisher);
	public List<Publisher> publisherList();
	public void removePublisher(String pid);
}
