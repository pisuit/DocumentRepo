package th.co.aerothai.document.model;

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

import th.co.aerothai.document.customtype.DataStatus;

@Entity
@Table(name="DOCCATEGORYADMIN")
@GenericGenerator(strategy="th.co.aerothai.document.utils.HibernateCurrentTimeIDGenerator", name="IDGENERATOR")
public class CategoryAdmin {
	
	@Id
	@Column(name="ID")
	@GeneratedValue(generator="IDGENERATOR")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="SUBCATEGORY_ID", referencedColumnName="ID")
	private SubCategory subCategory;
	
	@ManyToOne
	@JoinColumn(name="MEMBER_ID", referencedColumnName="STAFFCODE")
	private PersonalInfo personalInfo;
	
	@Column(name="ISADMIN")
	private boolean isAdmin = false;
	
	@Column(name="DATA_STATUS")
	@Enumerated(EnumType.STRING)
	private DataStatus dataStatus = DataStatus.NORMAL;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public PersonalInfo getPersonalInfo() {
		return personalInfo;
	}
	public void setPersonalInfo(PersonalInfo personalInfo) {
		this.personalInfo = personalInfo;
	}
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public DataStatus getDataStatus() {
		return dataStatus;
	}
	public void setDataStatus(DataStatus dataStatus) {
		this.dataStatus = dataStatus;
	}
	@Override
	public boolean equals(Object obj){
		if((((CategoryAdmin)obj).getPersonalInfo().getSTAFFCODE().equals(personalInfo.getSTAFFCODE()))){
			return true;
		} else {
			return false;
		}
	}
	public SubCategory getSubCategory() {
		return subCategory;
	}
	public void setSubCategory(SubCategory subCategory) {
		this.subCategory = subCategory;
	}
}
