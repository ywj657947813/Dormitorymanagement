package Model;

/**
 * ��Ʒ������
 */
public class Order {

	private Integer oid;// ����
	private String otime;// ʱ��
	private Double total; // ����Ʒ�嵥���ܽ��
	private Integer state;// 1:�ѽ��� (δ��ȡ)0��δ���� 2.�����
	private String descs;// ��ʱ��עԭ��
	// ���,ά��Ա����
	private String suid;
	// ����������:���ö�����ļ���
	private OrderItem orderItem;

	public String getDescs() {
		return descs;
	}

	public void setDescs(String descs) {
		this.descs = descs;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public String getSuid() {
		return suid;
	}

	public void setSuid(String suid) {
		this.suid = suid;
	}

	public OrderItem getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(OrderItem orderItem) {
		this.orderItem = orderItem;
	}

	public Integer getOid() {
		return oid;
	}

	public void setOid(Integer oid) {
		this.oid = oid;
	}

	public String getOtime() {
		return otime;
	}

	public void setOtime(String otime) {
		this.otime = otime;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

}
