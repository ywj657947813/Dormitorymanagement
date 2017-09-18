package Model;

import java.util.List;

public class Page <T>{
	private int pc;//当前页码
//	private int tp;//总页数
	private int tr;//总记录数
	private int ps;//每页记录数
	private List<T> beanList;//当前页数据
	
	private String url; //保存get请求地址栏的数据，以便截取查询的条件（问号后面的），
	                    //以便点击其他页数是查询条件不会丢失
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getPc() {
		return pc;
	}
	public void setPc(int pc) {
		this.pc = pc;
	}
	public int getTp() {
		//通过总记录数跟每页记录数来计算得出
		int tp = tr / ps;
		return tr%ps==0 ? tp:tp+1;
	}
	
	public int getTr() {
		return tr;
	}
	public void setTr(int tr) {
		this.tr = tr;
	}
	public int getPs() {
		return ps;
	}
	public void setPs(int ps) {
		this.ps = ps;
	}
	public List<T> getBeanList() {
		return beanList;
	}
	public void setBeanList(List<T> beanList) {
		this.beanList = beanList;
	} ;//
	
}
