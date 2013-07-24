package th.co.aerothai.document.utils;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import th.co.aerothai.document.model.PersonalInfo;

public class SessionUtils {
	
	public static PersonalInfo getUserFromSession(){
		PersonalInfo personalInfo = (PersonalInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userSession");
//		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
//		HttpSession session = request.getSession();
//		UserSession userSession = (UserSession) session.getAttribute("userSession");
		return personalInfo;
	}
	
	public static void putUserToSession(PersonalInfo person, HttpSession session){	
		session.setAttribute("userSession", person);
	}
}
