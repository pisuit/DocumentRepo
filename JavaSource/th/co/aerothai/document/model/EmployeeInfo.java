package th.co.aerothai.document.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name="L00910")
public class EmployeeInfo implements Serializable {
	
	@Id
	@Column(name="SN")
	private Long SN;
	
	@ManyToOne
	@JoinColumn(name="LINKP", referencedColumnName="SN")
	private PersonalInfo personalInfo;
	
	@Column(name="SALARY")
	private String SALARY;
	
	@Column(name="LICENSE")
	private String LICENSE;
	
	@Column(name="ALLOWANCE")
	private String ALLOWANCE;
	
	@Column(name="MANAGE")
	private String MANAGE;
	
	@Column(name="DEPFINANCE")
	private String DEPFINANCE;
	
	@Column(name="LOCATION")
	private String LOCATION;
	
	@Column(name="POSITIONX")
	private String POSITIONX;
	
	@Column(name="DEPPERSON")
	private String DEPPERSON;
	
	@Column(name="FLOCATION")
	private String FLOCATION;
	
	@Column(name="ACTING")
	private String ACTING;
	
	@Column(name="COMPETEN")
	private String COMPETEN;
	
	@Column(name="MAXSALARY")
	private String MAXSALARY;
	
	@Column(name="BSALARY")
	private String BSALARY;
	
	@Column(name="MORDER")
	private int MORDER;
	
	@ManyToOne
	@JoinColumn(name="POSITIONX", referencedColumnName="PROFESSION", insertable=false, updatable=false)
	private Profession profession;
	
	public Long getSN() {
		return SN;
	}
	public void setSN(Long sN) {
		SN = sN;
	}
	public String getSALARY() {
		return SALARY;
	}
	public void setSALARY(String sALARY) {
		SALARY = sALARY;
	}
	public String getLICENSE() {
		return LICENSE;
	}
	public void setLICENSE(String lICENSE) {
		LICENSE = lICENSE;
	}
	public String getALLOWANCE() {
		return ALLOWANCE;
	}
	public void setALLOWANCE(String aLLOWANCE) {
		ALLOWANCE = aLLOWANCE;
	}
	public String getMANAGE() {
		return MANAGE;
	}
	public void setMANAGE(String mANAGE) {
		MANAGE = mANAGE;
	}
	public String getDEPFINANCE() {
		return DEPFINANCE;
	}
	public void setDEPFINANCE(String dEPFINANCE) {
		DEPFINANCE = dEPFINANCE;
	}
	public String getLOCATION() {
		return LOCATION;
	}
	public void setLOCATION(String lOCATION) {
		LOCATION = lOCATION;
	}
	public String getPOSITIONX() {
		return POSITIONX;
	}
	public void setPOSITIONX(String pOSITIONX) {
		POSITIONX = pOSITIONX;
	}
	public Profession getProfession() {
		return profession;
	}
	public void setProfession(Profession profession) {
		this.profession = profession;
	}
	public String getDEPPERSON() {
		return DEPPERSON;
	}
	public void setDEPPERSON(String dEPPERSON) {
		DEPPERSON = dEPPERSON;
	}
	public String getFLOCATION() {
		return FLOCATION;
	}
	public void setFLOCATION(String fLOCATION) {
		FLOCATION = fLOCATION;
	}
	public String getACTING() {
		return ACTING;
	}
	public void setACTING(String aCTING) {
		ACTING = aCTING;
	}
	public String getCOMPETEN() {
		return COMPETEN;
	}
	public void setCOMPETEN(String cOMPETEN) {
		COMPETEN = cOMPETEN;
	}
	public String getMAXSALARY() {
		return MAXSALARY;
	}
	public void setMAXSALARY(String mAXSALARY) {
		MAXSALARY = mAXSALARY;
	}
	public String getBSALARY() {
		return BSALARY;
	}
	public void setBSALARY(String bSALARY) {
		BSALARY = bSALARY;
	}
	public int getMORDER() {
		return MORDER;
	}
	public void setMORDER(int mORDER) {
		MORDER = mORDER;
	}
	public PersonalInfo getPersonalInfo() {
		return personalInfo;
	}
	public void setPersonalInfo(PersonalInfo personalInfo) {
		this.personalInfo = personalInfo;
	}
	
}
