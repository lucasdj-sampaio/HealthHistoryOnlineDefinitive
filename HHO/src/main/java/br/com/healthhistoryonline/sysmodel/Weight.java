package br.com.healthhistoryonline.sysmodel;
import java.util.Date;

public class Weight {

	private int weightCode;
	private float weight;
	private Date inclusionDate;

	public int getWeightCode() {
		return weightCode;
	}

	public float getWeight() {
		return weight;
	}

	public Date getInclusionDate() {
		return inclusionDate;
	}
	
	public void setInclusionDate(Date inclusionDate) {
		this.inclusionDate = inclusionDate;
	}
	
	public void setWeight(float weight) {
		this.weight = weight;
	}

	public void setWeightCode(int weightCode) {
		this.weightCode = weightCode;
	}
}