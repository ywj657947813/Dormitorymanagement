package Model;

/**
 * 物品单对象
 */
public class Order {

	private Integer oid;// 主键
	private String otime;// 时间
	private Double total; // 该物品清单的总金额
	private Integer state;// 1:已接受 (未领取)0：未接受 2.已完成
	private String descs;// 延时备注原因
	// 外键,维修员工号
	private String suid;
	// 订单项的外键:配置订单项的集合
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
