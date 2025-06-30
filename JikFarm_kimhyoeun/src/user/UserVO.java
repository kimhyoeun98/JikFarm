package user;

import java.io.Serializable;
import java.util.Date;

public class UserVO implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	
	private int userNo;
	private String id;
	private String password;
	private String userName;
	private String mobile;
	private String email;
	private String address;
	private Date regDate;
	
	public UserVO(int userNo, String id, String password, String userName, String mobile, String email, String address, Date regDate) {
		this.userNo = userNo;
		this.id = id;
		this.password = password;
		this.userName = userName;
		this.mobile = mobile;
		this.email = email;
		this.address = address;
		this.regDate = regDate;
	}

	public UserVO(String id, String password, String userName) {
		this.id = id;
		this.password = password;
		this.userName = userName;
	}
	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}


	@Override
	public String toString() {
		String output = "[" + userNo + ", " + id + ", " + password + ", " + userName + ", " + regDate;
		if (mobile != null) {
			output += "(" + mobile + ", " + email + ", " + address +  ")";
		}
		output += "]";
		
		return output;
				
	}
	
	
	

	

}
