package org.lanqiao.service.impl;

import org.lanqiao.entity.Goods;
import org.lanqiao.entity.PageInfo;
import org.lanqiao.service.GoodService;

public class GoodsServiceImpl implements GoodService {
	private org.lanqiao.dao.GoodsDao dao=new org.lanqiao.dao.impl.GoodsDaoImpl();
	public GoodsServiceImpl(){
		dao=new org.lanqiao.dao.impl.GoodsDaoImpl();
	}

	@Override
	public PageInfo<Goods> goodsList(String cid, int pagesize, int pageindex) {
		// TODO Auto-generated method stub
		return dao.list(cid, pagesize, pageindex);
	}

	@Override
	public Goods getGoodsBygid(String gid) {
		// TODO Auto-generated method stub
		return dao.get(gid);
	}

	@Override
	public void removeGoods(String gid) {
		// TODO Auto-generated method stub
		dao.remove(gid);
	}

	@Override
	public void insertGoods(Goods goods) {
		dao.insert(goods);
		
	}

	@Override
	public void updateGoods(Goods goods) {
		dao.update(goods);
		
	}

}
