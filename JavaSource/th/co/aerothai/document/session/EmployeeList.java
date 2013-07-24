package th.co.aerothai.document.session;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang3.StringUtils;

import th.co.aerothai.document.dao.GeneralDAO;
import th.co.aerothai.document.model.PersonalInfo;
import th.co.aerothai.document.utils.BossUtil;

@ManagedBean(name="employeeList")
@SessionScoped
public class EmployeeList {
	private List<PersonalInfo> employeeList = new ArrayList<PersonalInfo>();
	
	public List<PersonalInfo> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(List<PersonalInfo> employeeList) {
		this.employeeList = employeeList;
	}
	
	public EmployeeList(){
		
	}
	
	public void createEmployeeList(){
		if(employeeList != null) employeeList.clear();
		
		employeeList.addAll(GeneralDAO.getEmployeeList());
		employeeList.add(BossUtil.getBoss());
	}
	
	public List<PersonalInfo> employeeCompleteMethod(String input) {
		List<PersonalInfo> output = new ArrayList<PersonalInfo>();
		
		for(PersonalInfo person : employeeList){
			if(StringUtils.contains(person.toString(), input)){
				output.add(person);
			}
		}
		return output;
	}
}
