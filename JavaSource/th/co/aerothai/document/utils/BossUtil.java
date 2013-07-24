package th.co.aerothai.document.utils;

import java.util.HashSet;
import java.util.Set;

import th.co.aerothai.document.model.EmployeeInfo;
import th.co.aerothai.document.model.PersonalInfo;

public abstract class BossUtil {
	public static PersonalInfo getBoss(){
		Set<EmployeeInfo> empList = new HashSet<EmployeeInfo>();
		EmployeeInfo emp = new EmployeeInfo();
		emp.setDEPFINANCE("½¨.");
		emp.setSN(Long.valueOf(1));
		empList.add(emp);
		
		PersonalInfo person = new PersonalInfo();
		person.setEmployeeInfos(empList);
		person.setSN(Long.valueOf(1));
		person.setSTAFFCODE("999999");
		person.setTNAME(PropertiesUtils.getBossProperties().getString("name"));
		person.setTSURNAME(PropertiesUtils.getBossProperties().getString("surename"));
	
		return person;
	}
}
