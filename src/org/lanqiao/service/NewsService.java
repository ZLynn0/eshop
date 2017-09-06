package org.lanqiao.service;

import java.util.List;

import org.lanqiao.entity.News;

public interface NewsService {
	public List<News> newsList();
	public News getNewsById(String id);
	public void removenews(String tid);
	public void updatenews(News news);
	public void insertnews(News news );
}
