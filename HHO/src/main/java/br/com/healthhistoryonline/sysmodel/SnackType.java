package br.com.healthhistoryonline.sysmodel;

public class SnackType {

	private int snackTypeCode;
	private String snack;
	
	public SnackType(int snackCode) {
		this.snackTypeCode = snackCode;
	}
	
	public int getSnackTypeCode() {
		return snackTypeCode;
	}
	public String getSnack() {
		return snack;
	}
	
	public void setSnack(String snack) {
		this.snack = snack;
	}
}