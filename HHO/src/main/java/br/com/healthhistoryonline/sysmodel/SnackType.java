package br.com.healthhistoryonline.sysmodel;

public class SnackType {

	private int snackCode;
	private String snack;
	
	public SnackType(int snackCode, String snack) {
		this.snackCode = snackCode;
		this.snack = snack;
	}
	
	public int getSnackCode() {
		return snackCode;
	}
	public String getSnack() {
		return snack;
	}
}