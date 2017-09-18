package Model;

public class Room {

	String id;
	String roomno;
	String dormno;
	
	public Room() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Room(String id, String roomno, String dormno) {
		super();
		this.id = id;
		this.roomno = roomno;
		this.dormno = dormno;
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
	public String getDormno() {
		return dormno;
	}
	public void setDormno(String dormno) {
		this.dormno = dormno;
	}
	
}
