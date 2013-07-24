package th.co.aerothai.document.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="G00104")
public class Profession implements Serializable{
	
	@Id
	@Column(name="sn")
	private int sn;
	
	@Column(name="linkc")
	private int linkc;
	
	@Column(name="profession")
	private String profession;
	
	@Column(name="leveln")
	private int leveln;
	
	@Column(name="tceiling")
	private String tceiling;
	
	@Column(name="lceiling")
	private String lceiling;
	
	@Column(name="eposition")
	private String eposition;
	
	@Column(name="status")
	private String status;
	
	@Column(name="tshort")
	private String tshort;
	
	@Column(name="eshort")
	private String eshort;
	
	public int getSn() {
		return sn;
	}
	public void setSn(int sn) {
		this.sn = sn;
	}
	public int getLinkc() {
		return linkc;
	}
	public void setLinkc(int linkc) {
		this.linkc = linkc;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public int getLeveln() {
		return leveln;
	}
	public void setLeveln(int leveln) {
		this.leveln = leveln;
	}
	public String getTceiling() {
		return tceiling;
	}
	public void setTceiling(String tceiling) {
		this.tceiling = tceiling;
	}
	public String getLceiling() {
		return lceiling;
	}
	public void setLceiling(String lceiling) {
		this.lceiling = lceiling;
	}
	public String getEposition() {
		return eposition;
	}
	public void setEposition(String eposition) {
		this.eposition = eposition;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTshort() {
		return tshort;
	}
	public void setTshort(String tshort) {
		this.tshort = tshort;
	}
	public String getEshort() {
		return eshort;
	}
	public void setEshort(String eshort) {
		this.eshort = eshort;
	}
}
