package Model;

/**
 * 物品项对象
 * 
 * 
 */
public class OrderItem {
	private Integer otid; // 主键
	private Integer count; // 数量
	private Integer dormid; // 用于的宿舍id
	private Double subtotal;// 总价
	// 商品外键:pid
	private Integer pid;
	// 订单外键:oid
	private Integer oid;

	// 设置名字，显示在页面
	private String dname; // 宿舍名称
	private String pname; // 物品名称

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
