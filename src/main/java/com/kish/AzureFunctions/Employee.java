package com.kish.AzureFunctions;

import java.io.Serializable;

public class Employee implements Serializable{

	private int id=0;
	
	private String ename=null;
	
	private String ssn=null;
	
	private String email=null;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
