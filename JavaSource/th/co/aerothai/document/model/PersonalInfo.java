package th.co.aerothai.document.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="L00901")
public class PersonalInfo implements Serializable{
	
	@Id
	@Column(name="SN")
	private Long SN;
	
	@Column(name="TNAME")
	private String TNAME;
	
	@Column(name="TSURNAME")
	private String TSURNAME;
	
	@Column(name="TITLE")
	private String TITLE;
	
	@Column(name="STAFFCODE")
	private String STAFFCODE;
	
	@Column(name="NATION")
	private String NATION;
	
	@Column(name="RELIGION")
	private String RELIGION;
	
	@Column(name="STATUS")
	private String STATUS;
	
	@Column(name="ENAME")
	private String ENAME;
	
	@Column(name="ESURNAME")
	private String ESURNAME;
	
	@Column(name="PREFIX")
	private String PREFIX;
	
	@Column(name="FLAG")
	private String FLAG;
	
	@Column(name="TMIDDLE")
	private String TMIDDLE;
	
	@Column(name="EMIDDLE")
	private String EMIDDLE;
	
	@Column(name="FLAGN")
	private String FLAGN;
	
	@Column(name="EFFECTIVE")
	private String EFFECTIVE;
	
	@Column(name="DOCUMENT")
	private String DOCUMENT;
	
	@Column(name="EFORNAME")
	private String EFORNAME;
	
	@Column(name="REVERSE")
	private String REVERSE;
	
	@Column(name="ACTION")
	private String ACTION;
	
	@OneToMany(mappedBy="personalInfo")
	private Set<EmployeeInfo> employeeInfos;
	
	@Transient
	private List<EmployeeInfo> employeeAsList;
	
	public Long getSN() {
		return SN;
	}
	public void setSN(Long sN) {
		SN = sN;
	}
	public String getTNAME() {
		return TNAME;
	}
	public void setTNAME(String tNAME) {
		TNAME = tNAME;
	}
	public String getTSURNAME() {
		return TSURNAME;
	}
	public void setTSURNAME(String tSURNAME) {
		TSURNAME = tSURNAME;
	}
	public String getTITLE() {
		return TITLE;
	}
	public void setTITLE(String tITLE) {
		TITLE = tITLE;
	}
	public String getSTAFFCODE() {
		return STAFFCODE;
	}
	public void setSTAFFCODE(String sTAFFCODE) {
		STAFFCODE = sTAFFCODE;
	}
	public String getNATION() {
		return NATION;
	}
	public void setNATION(String nATION) {
		NATION = nATION;
	}
	public String getRELIGION() {
		return RELIGION;
	}
	public void setRELIGION(String rELIGION) {
		RELIGION = rELIGION;
	}
	public String getSTATUS() {
		return STATUS;
	}
	public void setSTATUS(String sTATUS) {
		STATUS = sTATUS;
	}
	public String getENAME() {
		return ENAME;
	}
	public void setENAME(String eNAME) {
		ENAME = eNAME;
	}
	public String getESURNAME() {
		return ESURNAME;
	}
	public void setESURNAME(String eSURNAME) {
		ESURNAME = eSURNAME;
	}
	public String getPREFIX() {
		return PREFIX;
	}
	public void setPREFIX(String pREFIX) {
		PREFIX = pREFIX;
	}
	public String getFLAG() {
		return FLAG;
	}
	public void setFLAG(String fLAG) {
		FLAG = fLAG;
	}
	public String getTMIDDLE() {
		return TMIDDLE;
	}
	public void setTMIDDLE(String tMIDDLE) {
		TMIDDLE = tMIDDLE;
	}
	public String getEMIDDLE() {
		return EMIDDLE;
	}
	public void setEMIDDLE(String eMIDDLE) {
		EMIDDLE = eMIDDLE;
	}
	public String getFLAGN() {
		return FLAGN;
	}
	public void setFLAGN(String fLAGN) {
		FLAGN = fLAGN;
	}
	public String getEFFECTIVE() {
		return EFFECTIVE;
	}
	public void setEFFECTIVE(String eFFECTIVE) {
		EFFECTIVE = eFFECTIVE;
	}
	public String getDOCUMENT() {
		return DOCUMENT;
	}
	public void setDOCUMENT(String dOCUMENT) {
		DOCUMENT = dOCUMENT;
	}
	public String getEFORNAME() {
		return EFORNAME;
	}
	public void setEFORNAME(String eFORNAME) {
		EFORNAME = eFORNAME;
	}
	public String getREVERSE() {
		return REVERSE;
	}
	public void setREVERSE(String rEVERSE) {
		REVERSE = rEVERSE;
	}
	public String getACTION() {
		return ACTION;
	}
	public void setACTION(String aCTION) {
		ACTION = aCTION;
	}
	
	public String toString(){
		return TNAME+" "+TSURNAME;
	}
	public Set<EmployeeInfo> getEmployeeInfos() {
		return employeeInfos;
	}
	public void setEmployeeInfos(Set<EmployeeInfo> employeeInfos) {
		this.employeeInfos = employeeInfos;
	}
	public List<EmployeeInfo> getEmployeeAsList() {
		return new ArrayList<EmployeeInfo>(employeeInfos);
	}
	
	public boolean staffEquals(Object obj){
		if((((PersonalInfo)obj).getSTAFFCODE().equals(STAFFCODE))){
			return true;
		} else {
			return false;
		}
	}
}	
