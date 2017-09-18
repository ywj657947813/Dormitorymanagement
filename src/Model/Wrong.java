package Model;

public class Wrong {
 
	String id;
	String stuid;
	String wrongday;
	String name;
	String wrongreason;
	String result;
	
	String gradeid;
	String majorid;
	String depaid;
	String majorna;
	public Wrong() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Wrong(String stuid, String name,String wrongday, 
			String wrongreason, String result) {
		super();
		this.stuid = stuid;
		this.wrongday = wrongday;
		this.name = name;
		this.wrongreason = wrongreason;
		this.result = result;
	}

	
	public String getGradeid() {
		return gradeid;
	}

	public void setGradeid(String gradeid) {
		this.gradeid = gradeid;
	}

	public String getDepaid() {
		return depaid;
	}

	public void setDepaid(String depaid) {
		this.depaid = depaid;
	}

	public String getMajorid() {
		return majorid;
	}

	public void setMajorid(String majorid) {
		this.majorid = majorid;
	}

	public String getMajorna() {
		return majorna;
	}

	public void setMajorna(String majorna) {
		this.majorna = majorna;
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

	public String getWrongday() {
		return wrongday;
	}

	public void setWrongday(String wrongday) {
		this.wrongday = wrongday;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWrongreason() {
		return wrongreason;
	}

	public void setWrongreason(String wrongreason) {
		this.wrongreason = wrongreason;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
	
	
}
