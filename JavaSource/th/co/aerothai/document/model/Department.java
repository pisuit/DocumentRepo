package th.co.aerothai.document.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="G00101")
public class Department implements Serializable{
	
	@Id
	@Column(name="SN")
	private Long SN;
	
	@Column(name="LINK")
	private Long LINK;
	
	@Column(name="TDEPS")
	private String TDEPS;
	
	@Column(name="TDEPL")
	private String TDEPL;
	
	@Column(name="EDEPS")
	private String EDEPS;
	
	@Column(name="EDEPL")
	private String EDEPL;
	
	@Column(name="LOGIN")
	private String LOGIN;
	
	@Column(name="PFLAG")
	private String PFLAG;
	
	@Column(name="FFLAG")
	private String FFLAG;
	
	public Long getSN() {
		return SN;
	}
	public void setSN(Long sN) {
		SN = sN;
	}
	public Long getLINK() {
		return LINK;
	}
	public void setLINK(Long lINK) {
		LINK = lINK;
	}
	public String getTDEPS() {
		return TDEPS;
	}
	public void setTDEPS(String tDEPS) {
		TDEPS = tDEPS;
	}
	public String getTDEPL() {
		return TDEPL;
	}
	public void setTDEPL(String tDEPL) {
		TDEPL = tDEPL;
	}
	public String getEDEPS() {
		return EDEPS;
	}
	public void setEDEPS(String eDEPS) {
		EDEPS = eDEPS;
	}
	public String getEDEPL() {
		return EDEPL;
	}
	public void setEDEPL(String eDEPL) {
		EDEPL = eDEPL;
	}
	public String getLOGIN() {
		return LOGIN;
	}
	public void setLOGIN(String lOGIN) {
		LOGIN = lOGIN;
	}
	public String getPFLAG() {
		return PFLAG;
	}
	public void setPFLAG(String pFLAG) {
		PFLAG = pFLAG;
	}
	public String getFFLAG() {
		return FFLAG;
	}
	public void setFFLAG(String fFLAG) {
		FFLAG = fFLAG;
	}
	
}
