package com.hs.vo;

public class RegistrationVO {

	private String userName;
	private String password;
	private String address;
	private String profession;
	private int age;
	private long mobile;

	@Override
	public String toString() {
		return "RegistrationVO [userName=" + userName + ", password=" + password + ", address=" + address
				+ ", profession=" + profession + ", age=" + age + ", mobile=" + mobile + "]";
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public long getMobile() {
		return mobile;
	}

	public void setMobile(long mobile) {
		this.mobile = mobile;
	}

}