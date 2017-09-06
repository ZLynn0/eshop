package org.lanqiao.dao;

import java.util.List;

import org.lanqiao.entity.News;


public interface NewsDao {
	public List<News> list();
	public News get(String id);
	public void removeNews(String tid);
	public void updateNews(News news);
	public void insertNews(News news);

}
