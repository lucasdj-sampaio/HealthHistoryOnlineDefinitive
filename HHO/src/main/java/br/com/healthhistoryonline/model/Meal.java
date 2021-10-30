package br.com.healthhistoryonline.model;

public class Meal{
/**@author gabriela montefusco*/
	
	//-------- Atributos --------
	
	public String food;
	public int calories;
	public int quantity;
	public String measure;
	
	//-------- Construtor --------
	
	/**@param parametros do construtor para criar objeto de refeição*/
	public Meal(String aFood, int aCalories, int aQuantity, String aMeasure) {
		this.food = aFood;
		this.calories = aCalories;
		this.quantity = aQuantity;
		this.measure = aMeasure;
	}
}