package br.com.healthhistoryonline.sysmodel;

public class SnackType {

	private int snackTypeCode;
	private String snack;
	
	public SnackType(int snackCode, String snack) {
		this.snackTypeCode = snackCode;
		this.snack = snack;
	}
	
	public int getSnackTypeCode() {
		return snackTypeCode;
	}
	public String getSnack() {
		return snack;
	}
}