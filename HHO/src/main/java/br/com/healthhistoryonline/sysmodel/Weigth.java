package br.com.healthhistoryonline.sysmodel;

import java.util.Date;

public class Weigth {

	private int weigthCode;
	private float weigth;
	private Date inclusionDate;
	
	public Weigth(int weigthCode, int weigth, Date inclusionDate) {
		this.weigthCode = weigthCode;
		this.weigth = weigth;
		this.inclusionDate = inclusionDate;
	}

	public int getWeigthCode() {
		return weigthCode;
	}

	public float getWeigth() {
		return weigth;
	}

	public Date getInclusionDate() {
		return inclusionDate;
	}
}