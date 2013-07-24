package th.co.aerothai.document.manager;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.apache.commons.io.IOUtils;

import th.co.aerothai.document.customtype.LogType;
import th.co.aerothai.document.customtype.PositionType;
import th.co.aerothai.document.dao.GeneralDAO;
import th.co.aerothai.document.dao.OrgDAO;
import th.co.aerothai.document.model.Category;
import th.co.aerothai.document.model.CategoryAdmin;
import th.co.aerothai.document.model.Document;
import th.co.aerothai.document.model.Log;
import th.co.aerothai.document.model.PersonalInfo;
import th.co.aerothai.document.model.SubCategory;
import th.co.aerothai.document.utils.CharacterUtils;
import th.co.aerothai.document.utils.SVNUtils;
import th.co.aerothai.document.utils.SessionUtils;

@ManagedBean(name="mobile")
@ViewScoped
public class MobileManager {
	private List<Category> categoryList = new ArrayList<Category>();
	private List<Document> documentList = new ArrayList<Document>();
	private List<String> viewList = new ArrayList<String>();
	private String currentView;
	private String updatedView = ":rootform:root_box";
	private SubCategory selectedSubCategory;
	private String currentLocation;
	
	public List<Category> getCategoryList() {
		return categoryList;
	}
	public void setCategoryList(List<Category> categoryList) {
		this.categoryList = categoryList;
	}
	public List<Document> getDocumentList() {
		return documentList;
	}
	public void setDocumentList(List<Document> documentList) {
		this.documentList = documentList;
	}
	public String getUpdatedView() {
		return updatedView;
	}
	public void setUpdatedView(String updatedView) {
		this.updatedView = updatedView;
	}
	public List<String> getViewList() {
		return viewList;
	}
	public void setViewList(List<String> viewList) {
		this.viewList = viewList;
	}
	public String getCurrentLocation() {
		return currentLocation;
	}
	public void setCurrentLocation(String currentLocation) {
		this.currentLocation = currentLocation;
	}
	public String getCurrentView() {
		return currentView;
	}
	public void setCurrentView(String currentView) {
		this.currentView = currentView;
	}
	
	public MobileManager(){
		createCategoryList();
		createViewList();
	}
	
	public boolean checkRestriction(Document doc){
		if(doc.getRootFolder().isDepartmentAuthorization() == true){
			List<String> deptList = new ArrayList<String>();
			if(SessionUtils.getUserFromSession().getEmployeeAsList().get(0).getDEPFINANCE().equals("ฝจ.")){
		
				deptList = nodeRecursion(OrgDAO.getNodeIDForStaff(SessionUtils.getUserFromSession().getSTAFFCODE()).toString(), deptList);
				deptList.add(OrgDAO.getDepartmentForNodeID(OrgDAO.getNodeIDForStaff(SessionUtils.getUserFromSession().getSTAFFCODE()).toString()).toString());
	
				if(deptList.contains(doc.getRootFolder().getDepartment())){
					return true;
				} else {
					return false;
				}
			} else {
				if(doc.getRootFolder().getDepartment().equals(SessionUtils.getUserFromSession().getEmployeeAsList().get(0).getDEPFINANCE())){
					return true;
				} else {
					return false;
				}
			}		
		} else if(doc.getRootFolder().isPositionRestriction() == true){
			if(SessionUtils.getUserFromSession().getEmployeeAsList().get(0).getProfession().getProfession().contains("รักษาการ") && doc.getRootFolder().getPositionType().equals(PositionType.DIRECTOR)){
				return true;
			} else if(SessionUtils.getUserFromSession().getEmployeeAsList().get(0).getProfession().getLeveln() >= Integer.parseInt(doc.getRootFolder().getPositionType().getID())){
				return true;
			} else {
				return false;
			}
		} else {
			return true;
		}
	}
	
	private List<String> nodeRecursion(String masterID, List<String> deptList){
		List<Object[]> nodes = OrgDAO.getNodeIDForMaster(masterID);
		if(nodes.size() != 0){
			for(Object[] o : nodes){
				deptList.add(o[1].toString());
				nodeRecursion(o[0].toString(), deptList);
			}
		}
		return deptList;
	}
	
	private void createCategoryList(){
		if(categoryList != null) categoryList.clear();
		
		List<Category> catList = GeneralDAO.getCategoryList_M();
		for(Category cat : catList){
			if(cat.getSubCategoryList() != null && cat.getSubCategoryList().size() != 0){
				cat.setSubCategoryList(GeneralDAO.getSubCategoryList(cat));
				categoryList.add(cat);
			}
		}
	}
	
	public String createRootDocument(SubCategory sub){
		if(documentList != null) documentList.clear();
		
		documentList.addAll(GeneralDAO.getRootDocumentsForSubCategoryDESC(sub));
		selectedSubCategory = sub;
		currentLocation = sub.getDescription();
		return "pm:root_view";
	}
	

	public void createViewList(){
		if(viewList != null) viewList.clear();
		
		List<Long> list = GeneralDAO.getViewList();
		for(Long doc : list){
			viewList.add(doc.toString());
		}
	}
	
	public String viewTraverse(Document doc){
		if(documentList != null) documentList.clear();
		
		documentList.addAll(GeneralDAO.getChildDocument(doc));
		currentView = "document_view"+doc.getId().toString();
		if(doc.getRootFolder() == null){
			updatedView = ":rootform:root_box";
		} else {
			updatedView = ":documentform"+doc.getId().toString()+":document_box"+doc.getId();
		}
		currentLocation = doc.getFileName();
		return "pm:document_view"+doc.getId().toString();
	}
	
	public String viewBackward(){
		String subbed = currentView.substring(13);
		Document root = GeneralDAO.getRootDocuments(Long.valueOf(subbed));
		
		if(documentList != null) documentList.clear();
		if(root.getRootFolder() == null){
			documentList.addAll(GeneralDAO.getRootDocumentsForSubCategoryDESC(selectedSubCategory));
		} else {
			Document rootOfRoot = GeneralDAO.getDocument(root.getRootFolder());
			List<Document> docList = GeneralDAO.getChildDocument(rootOfRoot);
			if(docList != null && docList.size() != 0){
				documentList.addAll(docList);
			}
		}		
		currentLocation = root.getFileName();
		if(root.getRootFolder() == null){
			currentView = "root_view";

			return "pm:root_view";
		} else {
			currentView = "document_view"+root.getRootFolder().getId().toString();

			return "pm:"+currentView;
		}
	}
	
	public void download(Document doc) throws IOException{
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		InputStream is = null;
		byte[] data;
		try {
			is = SVNUtils.downloadFile(doc.getStoredName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		data = IOUtils.toByteArray(is);
		
		ec.setResponseContentType(doc.getContentType());
		ec.setResponseContentLength(data.length);
		ec.setResponseCharacterEncoding("UTF-8");
		ec.setResponseHeader("Content-Disposition", "attachment;charset=utf-8; filename=\""+CharacterUtils.Unicode2ASCII(doc.getFileName()) + "\""); 
		
		try {
			OutputStream out = ec.getResponseOutputStream();
			out.write(data);

			fc.responseComplete();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public boolean isMember(){
		for(CategoryAdmin mem : selectedSubCategory.getAdmins()){
			if(SessionUtils.getUserFromSession() != null && SessionUtils.getUserFromSession().staffEquals(mem.getPersonalInfo())){
				return true;
			}
		}
		return false;
	}
	
	public boolean isLoggedOn(){
		PersonalInfo userSession = SessionUtils.getUserFromSession();
		if(userSession != null){
			return true;
		} else {
			return false;
		}
	}
	
	public String logout(){
		GeneralDAO.saveLog(new Log(null, SessionUtils.getUserFromSession(), Calendar.getInstance().getTime(), "Logged Out", LogType.ACCESS, null));		
		
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("userSession");
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();	 
		return "main_m?faces-redirect=true";
	}
}
