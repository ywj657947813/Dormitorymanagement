package Model;

/**
 * ��Ʒ�����
 * 
 * 
 */
public class OrderItem {
	private Integer otid; // ����
	private Integer count; // ����
	private Integer dormid; // ���ڵ�����id
	private Double subtotal;// �ܼ�
	// ��Ʒ���:pid
	private Integer pid;
	// �������:oid
	private Integer oid;

	// �������֣���ʾ��ҳ��
	private String dname; // ��������
	private String pname; // ��Ʒ����

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public Integer getOid() {
		return oid;
	}

	public void setOid(Integer oid) {
		this.oid = oid;
	}

	public Integer getOtid() {
		return otid;
	}

	public void setOtid(Integer otid) {
		this.otid = otid;
	}

	public Integer getDormid() {
		return dormid;
	}

	public void setDormid(Integer dormid) {
		this.dormid = dormid;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

}
