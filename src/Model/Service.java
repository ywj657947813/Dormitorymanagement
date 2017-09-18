package Model;

public class Service {

	String id;
	String stuid;
	String dormno;
	String dormName;
	String roomno;
	String servicetype;
	String servicereason;
	String name;
	String phone;
	String time;
	String state;
	
	
	public Service() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Service(String dormno, String state, String servicetype) {
		super();
		this.dormno = dormno;
		this.servicetype = servicetype;
		this.state = state;
	}



	public Service(String stuid, String dormno,String roomno, String servicetype,
			String servicereason, String name, String phone, String time,
			String state) {
		super();
		this.stuid = stuid;
		this.dormno = dormno;
		this.roomno = roomno;
		this.servicetype = servicetype;
		this.servicereason = servicereason;
		this.name = name;
		this.phone = phone;
		this.time = time;
		this.state = state;
	}

	public String getDormName() {
		return dormName;
	}



	public void setDormName(String dormName) {
		this.dormName = dormName;
	}



	public String getTime() {
		return time;
	}



	public void setTime(String time) {
		this.time = time;
	}



	public String getRoomno() {
		return roomno;
	}



	public void setRoomno(String roomno) {
		this.roomno = roomno;
	}



	public String getState() {
		return state;
	}



	public void setState(String state) {
		this.state = state;
	}



	public String getStuid() {
		return stuid;
	}

	public void setStuid(String stuid) {
		this.stuid = stuid;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDormno() {
		return dormno;
	}
	public void setDormno(String dormno) {
		this.dormno = dormno;
	}
	public String getServicetype() {
		return servicetype;
	}
	public void setServicetype(String servicetype) {
		this.servicetype = servicetype;
	}
	public String getServicereason() {
		return servicereason;
	}
	public void setServicereason(String servicereason) {
		this.servicereason = servicereason;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
