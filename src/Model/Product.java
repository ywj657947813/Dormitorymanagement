package Model;

public class Product {

	String pid;
	String pname;
	Double pprice;
	Integer ptotal;

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public Double getPprice() {
		return pprice;
	}

	public void setPprice(Double pprice) {
		this.pprice = pprice;
	}

	public Integer getPtotal() {
		return ptotal;
	}

	public void setPtotal(Integer ptotal) {
		this.ptotal = ptotal;
	}

}
