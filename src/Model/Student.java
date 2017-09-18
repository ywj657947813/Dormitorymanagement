package Model;

public class Student {

	String id;
	String name;
	String majorid;
	String depaid;
	String gradeid;
	String sex;
	String dormno;
	String roomno;
	String phone;
	String bedno;
	String pass;
	
	String dormName;
	String depaName;
	String majorName;
	String gradeName;
	
	
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Student(String id, String name, String dormno,String depaid,String majorid) {
		super();
		this.id = id;
		this.name = name;
		this.dormno = dormno;
		this.depaid = depaid;
		this.majorid = majorid;
	}

	public Student(String id,String pass, String name, String depaid, String majorid, String sex,
			String dormno,String roomno,String phone, String bedno) {
		super();
		this.id = id;
		this.pass = pass;
		this.name = name;
		this.depaid = depaid;
		this.majorid = majorid;
		this.sex = sex;
		this.dormno = dormno;
		this.roomno = roomno;
		this.phone = phone;
		this.bedno = bedno;
	}

	
	public String getGradeid() {
		return gradeid;
	}

	public void setGradeid(String gradeid) {
		this.gradeid = gradeid;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getDormName() {
		return dormName;
	}

	public void setDormName(String dormName) {
		this.dormName = dormName;
	}

	public String getDepaName() {
		return depaName;
	}

	public void setDepaName(String depaName) {
		this.depaName = depaName;
	}

	public String getMajorName() {
		return majorName;
	}

	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}

	public String getRoomno() {
		return roomno;
	}

	public void setRoomno(String roomno) {
		this.roomno = roomno;
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

	public String getMajorid() {
		return majorid;
	}

	public void setMajorid(String majorid) {
		this.majorid = majorid;
	}

	public String getDepaid() {
		return depaid;
	}

	public void setDepaid(String depaid) {
		this.depaid = depaid;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getDormno() {
		return dormno;
	}

	public void setDormno(String dormno) {
		this.dormno = dormno;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getBedno() {
		return bedno;
	}

	public void setBedno(String bedno) {
		this.bedno = bedno;
	}


	
}
