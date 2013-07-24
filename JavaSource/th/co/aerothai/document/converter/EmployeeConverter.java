package th.co.aerothai.document.converter;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import th.co.aerothai.document.model.PersonalInfo;
import th.co.aerothai.document.session.EmployeeList;

@FacesConverter(value="employeeConverter")
public class EmployeeConverter implements Converter {
	private List<PersonalInfo> employeeList = new ArrayList<PersonalInfo>();
	
	public EmployeeConverter(){	
		if(employeeList != null) employeeList.clear();
	
		FacesContext context = FacesContext.getCurrentInstance();
		EmployeeList empList  =  (EmployeeList) context.getApplication().evaluateExpressionGet(context, "#{employeeList}", EmployeeList.class);
		employeeList.addAll(empList.getEmployeeList());
	}
	
	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent component, String submitedValue) {
		// TODO Auto-generated method stub
		if(submitedValue.trim().equals("")){
			return null;
		} else {
			for(PersonalInfo personalInfo : employeeList){
				if((personalInfo.toString()).contains(submitedValue)){
					return personalInfo;
				}
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		// TODO Auto-generated method stub
		if(value == null || value.equals("")){
			return "";
		} else {
			return String.valueOf(((PersonalInfo)value).toString());
		}
	}

}
