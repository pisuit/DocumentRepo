package th.co.aerothai.document.customtype;

public enum PositionType {
	DIRECTOR("13","ระดับผู้อำนวยการ"),
	SENIOR_DIRECTOR("14","ระดับผู้อำนวยการฝ่าย"),
	VICE_PRESIDENT("15","ระดับผู้อำนวยการใหญ่"),
	EXECUTIVE_VICE_PRESIDENT("16","ระดับรองกรรมการผู้อำนวยการใหญ่");
	
	private String id;
	private String value;
	
	PositionType(String aID, String aValue){
		id = aID;
		value = aValue;
	}
	
	public String getID(){
		return id;
	}
	
	public String getValue(){
		return value;
	}

	public String toString() {
		return value;
	}
	
	public static PositionType find(String aID){
		for (PositionType status : PositionType.values()) {
			if (status.id.equals(aID)){
				return status;
			}
		}
		return null;
	}
}
