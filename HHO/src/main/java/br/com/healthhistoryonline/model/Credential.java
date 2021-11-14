package br.com.healthhistoryonline.model;

public class Credential {

	private String userName;
	private String mailAddress;
	private String password;
	
	public Credential(String userName, String mailAddress, String password) {
		this.userName = userName;
		this.mailAddress = mailAddress;
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public String getPassword() {
		return password;
	}
}