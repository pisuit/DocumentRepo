package th.co.aerothai.document.ldap;
/*
 * Created on 19 ¡.¾. 2550
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
/**
 * @author manop
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class LDAPUser {
	private String firstName;
	private String lastName;
	private String employeeCode;
	private String department;
	private String location;
	/**
	 * @return Returns the department.
	 */
	public String getDepartment() {
		return department;
	}
	/**
	 * @param department The department to set.
	 */
	public void setDepartment(String department) {
		this.department = department;
	}
	/**
	 * @return Returns the location.
	 */
	public String getLocation() {
		return location;
	}
	/**
	 * @param location The location to set.
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	/**
	 * @return Returns the employeeCode.
	 */
	public String getEmployeeCode() {
		return employeeCode;
	}
	/**
	 * @param employeeCode The employeeCode to set.
	 */
	public void setEmployeeCode(String employeeCode) {
		String temp = employeeCode;
		for (int i = 0;i < employeeCode.length(); i++) {
			if (temp.charAt(i) != '0') {
				temp = employeeCode.substring(i,employeeCode.length());
				break;
			}
		}
		this.employeeCode = temp;
	}
	/**
	 * @return Returns the firstName.
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName The firstName to set.
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return Returns the lastName.
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName The lastName to set.
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return firstName+" "+lastName+ " "+employeeCode+" "+department+" "+location;
	}
}
