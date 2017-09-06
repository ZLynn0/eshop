package org.lanqiao.entity;

public class KeyWord {
	private String keyword;
	private String keydesc;
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getKeydesc() {
		return keydesc;
	}
	public void setKeydesc(String keydesc) {
		this.keydesc = keydesc;
	}
	public KeyWord(String keyword, String keydesc) {
		super();
		this.keyword = keyword;
		this.keydesc = keydesc;
	}
	public KeyWord() {
		super();
	}
	@Override
	public String toString() {
		return "KeyWord [keyword=" + keyword + ", keydesc=" + keydesc + "]";
	}
	

}
