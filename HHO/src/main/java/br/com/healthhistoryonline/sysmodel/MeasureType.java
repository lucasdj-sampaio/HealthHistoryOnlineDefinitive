package br.com.healthhistoryonline.sysmodel;

public class MeasureType {
	
	private int measureCode;
	private String measure;
	
	public MeasureType(int measeureCode, String measure) {
		this.measureCode = measeureCode;
		this.measure = measure;
	}
	
	public int getMeasureCode() {
		return measureCode;
	}
	public String getMeasure() {
		return measure;
	}
}