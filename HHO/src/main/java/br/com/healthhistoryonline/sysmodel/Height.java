package br.com.healthhistoryonline.sysmodel;
import java.util.Date;
public class Height {

	private int heightCode;
	private float height;
	private Date inclusionDate;
	
	public Height(int heightCode, float height) {
		this.heightCode = heightCode;
		this.height = height;
	}

	public int getHeightCode() {
		return heightCode;
	}

	public float getHeight() {
		return height;
	}

	public Date getInclusionDate() {
		return inclusionDate;
	}
	
	public void setInclusionDate(Date inclusionDate) {
		this.inclusionDate = inclusionDate;
	}
}