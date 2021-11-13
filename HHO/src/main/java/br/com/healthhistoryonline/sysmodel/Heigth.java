package br.com.healthhistoryonline.sysmodel;
import java.util.Date;

public class Heigth {

	private int heigthCode;
	private float heigth;
	private Date inclusionDate;
	
	public Heigth(int heigthCode, int heigth, Date inclusionDate) {
		this.heigthCode = heigthCode;
		this.heigth = heigth;
		this.inclusionDate = inclusionDate;
	}

	public int getHeigthCode() {
		return heigthCode;
	}

	public float getHeigth() {
		return heigth;
	}

	public Date getInclusionDate() {
		return inclusionDate;
	}
}