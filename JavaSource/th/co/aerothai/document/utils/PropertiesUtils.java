package th.co.aerothai.document.utils;

import java.util.ResourceBundle;

public class PropertiesUtils {

	public static ResourceBundle getSVNProperties() {
		ResourceBundle props = ResourceBundle.getBundle("SVN");
		return props;
	}
	
	public static ResourceBundle getAdminProperties(){
		ResourceBundle props = ResourceBundle.getBundle("Admin");
		return props;
	}
	
	public static ResourceBundle getBossProperties(){
		ResourceBundle props = ResourceBundle.getBundle("Boss");
		return props;
	}
}
