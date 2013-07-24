package th.co.aerothai.document.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import th.co.aerothai.document.customtype.LogType;

@Entity
@Table(name="DOCLOG")
@GenericGenerator(strategy="th.co.aerothai.document.utils.HibernateCurrentTimeIDGenerator", name="IDGENERATOR")
public class Log {
	
	@Id
	@Column(name="ID")
	@GeneratedValue(generator="IDGENERATOR")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="SUBCATEGORY_ID", referencedColumnName="ID")
	private SubCategory subCategory;
	
	@ManyToOne
	@JoinColumn(name="STAFFCODE", referencedColumnName="STAFFCODE")
	private PersonalInfo staff;
	
	@Column(name="TIME_STAMP")
	private Date timeStamp;
	
	@Column(name="DETAIL")
	private String detail;
	
	@Column(name="LOG_TYPE")
	@Enumerated(EnumType.STRING)
	private LogType logType;
	
	@Column(name="MD5")
	private String md5;
	
	public Log(){
		
	}
	
	public Log(SubCategory subCategory, PersonalInfo staff, Date timeStamp, String detail, LogType logType, String md5){
		this.subCategory = subCategory;
		this.staff = staff;
		this.timeStamp = timeStamp;
		this.detail = detail;
		this.logType = logType;
		this.md5 = md5;
		
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public PersonalInfo getStaff() {
		return staff;
	}
	public void setStaff(PersonalInfo staff) {
		this.staff = staff;
	}
	public Date getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public LogType getLogType() {
		return logType;
	}
	public void setLogType(LogType logType) {
		this.logType = logType;
	}

	public SubCategory getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(SubCategory subCategory) {
		this.subCategory = subCategory;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}
}
