package Model;

public class Dorm {

	String id;
	String name;
	String dormno;
	String bedno;
	
	public Dorm() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Dorm(String id, String name, String dormno) {
		super();
		this.id = id;
		this.name = name;
		this.dormno = dormno;
	}

	public Dorm(String id, String name, String dormno, String bedno) {
		super();
		this.id = id;
		this.name = name;
		this.dormno = dormno;
		this.bedno = bedno;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDormno() {
		return dormno;
	}
	public void setDormno(String dormno) {
		this.dormno = dormno;
	}
	public String getBedno() {
		return bedno;
	}
	public void setBedno(String bedno) {
		this.bedno = bedno;
	}
	
}
