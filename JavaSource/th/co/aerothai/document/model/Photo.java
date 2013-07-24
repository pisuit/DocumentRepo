package th.co.aerothai.document.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="L00906")
public class Photo {
	
	@Column(name="PHOTO")
	private byte[] photo;
	
	@Id
	@Column(name="STAFFCODE")
	private String staffCode;
	
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	public String getStaffCode() {
		return staffCode;
	}
	public void setStaffCode(String staffCode) {
		this.staffCode = staffCode;
	}
}
