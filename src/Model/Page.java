package Model;

import java.util.List;

public class Page <T>{
	private int pc;//��ǰҳ��
//	private int tp;//��ҳ��
	private int tr;//�ܼ�¼��
	private int ps;//ÿҳ��¼��
	private List<T> beanList;//��ǰҳ����
	
	private String url; //����get�����ַ�������ݣ��Ա��ȡ��ѯ���������ʺź���ģ���
	                    //�Ա�������ҳ���ǲ�ѯ�������ᶪʧ
	
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
		//ͨ���ܼ�¼����ÿҳ��¼��������ó�
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
