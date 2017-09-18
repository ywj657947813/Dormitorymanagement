package Model;

public class Admin {

	String id;
	String pass;
 
	String typeid;//维修人员类型
	
	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getTypeid() {
		return typeid;
	}

	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

}
