package br.com.healthhistoryonline.model;

public class Meal{
/**@author gabriela montefusco*/
	
	//-------- Atributos --------
	
	private int mealCode;
	private String mealName;
	
	public Meal(String mealName) {
		this.mealName = mealName;
	}

	public int getMealCode() {
		return mealCode;
	}

	public String getMealName() {
		return mealName;
	}

	public void setMealCode(int mealCode) {
		this.mealCode = mealCode;
	}
}