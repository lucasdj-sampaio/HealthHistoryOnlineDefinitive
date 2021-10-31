package br.com.healthhistoryonline.model;
import java.util.List;

public class Snack {

	public String typeFood;
	public String inclusionDate;
	public List<Meal> meal;
	
	public Snack(String anTypeFood, String aInclusionDate) {
		this.typeFood = anTypeFood;
		this.inclusionDate = aInclusionDate;
	}
	
	public void setMeal(List<Meal> aMeal) {
		this.meal = aMeal;
	}
}