package th.co.aerothai.document.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import th.co.aerothai.document.customtype.DataStatus;
import th.co.aerothai.document.customtype.PositionType;

@Entity
@Table(name="DOCDOCUMENT")
@GenericGenerator(strategy="th.co.aerothai.document.utils.HibernateCurrentTimeIDGenerator", name="IDGENERATOR")
public class Document {
		
	@Id
	@Column(name="ID")
	@GeneratedValue(generator="IDGENERATOR")
	private Long id;
	
	@Column(name="FILE_NAME")
	private String fileName;
	
	@Column(name="STORED_NAME")
	private String storedName;
	
	@Column(name="CONTENT_TYPE")
	private String contentType;
	
	@ManyToOne
	@JoinColumn(name="SUBCATEGORY_ID", referencedColumnName="ID")
	private SubCategory subCategory;
	
	@Column(name="UPLOADTIME")
	private Date uploadTime;
	
	@Column(name="FILE_SIZE")
	private Long fileSize;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@ManyToOne
	@JoinColumn(name="ROOT_FOLDER_ID", referencedColumnName="ID")
	private Document rootFolder;
	
	@Column(name="ISFOLDER")
	private boolean isFolder;
	
	@Column(name="DATA_STATUS")
	@Enumerated(EnumType.STRING)
	private DataStatus dataStatus = DataStatus.NORMAL;
	
	@OneToMany(mappedBy="rootFolder")
	private List<Document> childFolder;
	
	@Column(name="ISDEPTAUTHORIZATION")
	private boolean isDepartmentAuthorization = false;
	
	@Column(name="DEPARTMENT")
	private String department;
	
	@Column(name="ISPOSITIONRESTRICTION")
	private boolean isPositionRestriction = false;
	
	@Column(name="POSITION")
	@Enumerated(EnumType.STRING)
	private PositionType positionType;
	
	@Column(name="MD5")
	private String md5;
	
	@Transient
	private String fileType;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getStoredName() {
		return storedName;
	}

	public void setStoredName(String storedName) {
		this.storedName = storedName;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Document getRootFolder() {
		return rootFolder;
	}

	public void setRootFolder(Document rootFolder) {
		this.rootFolder = rootFolder;
	}

	public boolean isFolder() {
		return isFolder;
	}

	public void setFolder(boolean isFolder) {
		this.isFolder = isFolder;
	}

	public DataStatus getDataStatus() {
		return dataStatus;
	}

	public void setDataStatus(DataStatus dataStatus) {
		this.dataStatus = dataStatus;
	}

	public List<Document> getChildFolder() {
		return childFolder;
	}

	public void setChildFolder(List<Document> childFolder) {
		this.childFolder = childFolder;
	}

	public String getFileType() {
		if(contentType != null){
			String[] splitString = contentType.split("/");
			if(splitString[0].equals("application")){
				return splitString[1];
			} else {
				return contentType;
			}
		} else {
			return contentType;
		}
	}

	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	public SubCategory getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(SubCategory subCategory) {
		this.subCategory = subCategory;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public boolean isDepartmentAuthorization() {
		return isDepartmentAuthorization;
	}

	public void setDepartmentAuthorization(boolean isDepartmentAuthorization) {
		this.isDepartmentAuthorization = isDepartmentAuthorization;
	}

	public boolean isPositionRestriction() {
		return isPositionRestriction;
	}

	public void setPositionRestriction(boolean isPositionRestriction) {
		this.isPositionRestriction = isPositionRestriction;
	}

	public PositionType getPositionType() {
		return positionType;
	}

	public void setPositionType(PositionType positionType) {
		this.positionType = positionType;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}
}
