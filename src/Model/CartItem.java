package Model;

/**
 * ���������
 * 
 * 
 */
public class CartItem {
	private String id;// �������� ͬ����Ʒ���ڲ�ͬ����¥
	private Product product; // ����������Ʒ��Ϣ
	private int count; // ����ĳ����Ʒ����
	private double subtotal; // ����ĳ����ƷС��
	private String dormid; // ����Ʒ�����ĸ�����
	private String dname; // ��������

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

	// С���Զ������.
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
