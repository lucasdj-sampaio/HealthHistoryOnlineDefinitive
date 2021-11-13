package br.com.healthhistoryonline.model;
import java.util.Date;
import java.util.List;
import br.com.healthhistoryonline.sysmodel.SnackType;

public class Snack {
	
	private int snackCode;
	private SnackType typeFood;
	private Date inclusionDate;
	private List<Meal> meal;
	
	public SnackType getTypeFood() {
		return typeFood;
	}
	public void setTypeFood(SnackType typeFood) {
		this.typeFood = typeFood;
	}
	public Date getInclusionDate() {
		return inclusionDate;
	}
	public void setInclusionDate(Date inclusionDate) {
		this.inclusionDate = inclusionDate;
	}
	public List<Meal> getMeal() {
		return meal;
	}
	public void setMeal(List<Meal> meal) {
		this.meal = meal;
	}
	public int getSnackCode() {
		return snackCode;
	}
	public void setSnackCode(int snackCode) {
		this.snackCode = snackCode;
	}	
}