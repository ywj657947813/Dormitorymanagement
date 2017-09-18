package Model;

public class Leave {
 
	String id;
	String stuid;
	String firsttime;
	String lasttime;
	String name;
	String leavereason;
	String address;
	String studentphone;
	String parentsphone;
	String state;
	
	String depaId;
	String majorId;
	String gradeId;
	public Leave() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Leave(String stuid) {
		super();
		this.stuid = stuid;
	}

	public Leave(String stuid, String firsttime, String lasttime, String name,
			String leavereason, String address, String studentphone,
			String parentsphone,String state) {
		super();
		this.stuid = stuid;
		this.firsttime = firsttime;
		this.lasttime = lasttime;
		this.name = name;
		this.leavereason = leavereason;
		this.address = address;
		this.studentphone = studentphone;
		this.parentsphone = parentsphone;
		this.state=state;
	}

	public Leave(String stuid, String state, String depaId) {
		super();
		this.stuid = stuid;
		this.state = state;
		this.depaId = depaId;
	}

	
	public String getGradeId() {
		return gradeId;
	}

	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}

	public String getMajorId() {
		return majorId;
	}

	public void setMajorId(String majorId) {
		this.majorId = majorId;
	}

	public String getDepaId() {
		return depaId;
	}

	public void setDepaId(String depaId) {
		this.depaId = depaId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStuid() {
		return stuid;
	}
	public void setStuid(String stuid) {
		this.stuid = stuid;
	}
	public String getFirsttime() {
		return firsttime;
	}
	public void setFirsttime(String firsttime) {
		this.firsttime = firsttime;
	}
	public String getLasttime() {
		return lasttime;
	}
	public void setLasttime(String lasttime) {
		this.lasttime = lasttime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLeavereason() {
		return leavereason;
	}
	public void setLeavereason(String leavereason) {
		this.leavereason = leavereason;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getStudentphone() {
		return studentphone;
	}
	public void setStudentphone(String studentphone) {
		this.studentphone = studentphone;
	}
	public String getParentsphone() {
		return parentsphone;
	}
	public void setParentsphone(String parentsphone) {
		this.parentsphone = parentsphone;
	}
	
}
