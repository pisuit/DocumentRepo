package th.co.aerothai.document.pagehandler;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import th.co.aerothai.document.model.PersonalInfo;
import th.co.aerothai.document.utils.SessionUtils;

@ManagedBean(name="layoutHandler")
@RequestScoped
public class LayoutHandler {
	
	private String name;
	private PersonalInfo personalInfo;
	
	public boolean isLoggedIn(){
		PersonalInfo personalInfo = SessionUtils.getUserFromSession();
		if(personalInfo != null){
			return true;
		} else {
			return false;
		}
	}

	public String getName() {
		if(SessionUtils.getUserFromSession() != null){
			return SessionUtils.getUserFromSession().getTNAME()+" "+SessionUtils.getUserFromSession().getTSURNAME();
		}
		return null;
	}

	public PersonalInfo getPersonalInfo() {
		if(SessionUtils.getUserFromSession() != null){
			return SessionUtils.getUserFromSession();
		}
		return null;
	}
	
}
