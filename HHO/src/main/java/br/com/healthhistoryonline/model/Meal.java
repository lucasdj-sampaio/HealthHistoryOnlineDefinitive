package br.com.healthhistoryonline.model;

public class Meal{
/**@author gabriela montefusco*/
	
	//-------- Atributos --------
	
	private String food;
	private int calories;
	private int quantity;
	private String measure;
	
	//-------- Construtor --------
	
	/**@param parametros do construtor para criar objeto de refeição*/
	public Meal(String aFood, int aCalories, int aQuantity, String aMeasure) {
		this.food = aFood;
		this.calories = aCalories;
		this.quantity = aQuantity;
		this.measure = aMeasure;
	}

	public String getFood() {
		return food;
	}

	public int getCalories() {
		return calories;
	}

	public int getQuantity() {
		return quantity;
	}

	public String getMeasure() {
		return measure;
	}	
}