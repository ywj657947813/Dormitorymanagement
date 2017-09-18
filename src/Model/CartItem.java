package Model;

/**
 * 购物项对象
 * 
 * 
 */
public class CartItem {
	private String id;// 用于区分 同件物品用于不同宿舍楼
	private Product product; // 购物项中商品信息
	private int count; // 购买某种商品数量
	private double subtotal; // 购买某种商品小计
	private String dormid; // 该物品用于哪个宿舍
	private String dname; // 宿舍名称

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	// 小计自动计算的.
	public double getSubtotal() {
		return count * product.getPprice();
	}

	public String getDormid() {
		return dormid;
	}

	public void setDormid(String dormid) {
		this.dormid = dormid;
	}

}
