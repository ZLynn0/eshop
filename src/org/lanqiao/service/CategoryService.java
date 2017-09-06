package org.lanqiao.service;

import java.util.List;

import org.lanqiao.entity.Category;


public interface CategoryService {
	public List<Category> categoryList();
	public Category getCategoryById(String cid);
	public void removeCategory(String cid);
	public void insertCategory(Category  category);
	public void updateCategory(Category category);
}
