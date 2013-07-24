package th.co.aerothai.document.customtype;

public enum LogType {
	UPLOAD("UPLOAD","UPLOAD"),
	DOWNLOAD("DOWNLOAD","DOWNLOAD"),
	CREATE("CREATE","CREATE"),
	DELETE("DELETE","DELETE"),
	EDIT("EDIT","EDIT"),
	ACCESS("ACCESS","ACCESS");
	
	private String id;
	private String value;
	
	LogType(String aID, String aValue){
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
	
	public static LogType find(String aID){
		for (LogType type : LogType.values()) {
			if (type.id.equals(aID)){
				return type;
			}
		}
		return null;
	}
}
