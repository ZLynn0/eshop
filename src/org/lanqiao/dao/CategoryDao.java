package org.lanqiao.dao;

import java.util.List;
import org.lanqiao.entity.Category;

public interface CategoryDao {
	public List<Category> list();
	public Category get(String cid);
	public void updateCategory(Category category);
	public void remove(String cid);
	public void insert(Category category);

}
