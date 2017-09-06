package org.lanqiao.dao;

import java.util.List;
import org.lanqiao.entity.Publisher;

public interface PublisherDao {
	public Publisher get(String pid);
	public void update(Publisher publisher);
	public List<Publisher> list();
	public void remove(String pid);
	public void insert(Publisher publisher);
}
