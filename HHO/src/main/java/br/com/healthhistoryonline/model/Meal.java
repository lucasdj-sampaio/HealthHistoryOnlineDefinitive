package br.com.healthhistoryonline.model;
import br.com.healthhistoryonline.sysmodel.FoodType;
import br.com.healthhistoryonline.sysmodel.FoodMeasureType;

public class Meal{
/**@author gabriela montefusco*/
	
	//-------- Atributos --------
	
	private int mealCode;
	private FoodType food;
	private int calories;
	private int quantity;
	private FoodMeasureType measure;
	
	//-------- Construtor --------

	public FoodType getFood() {
		return food;
	}

	public int getCalories() {
		return calories;
	}

	public int getQuantity() {
		return quantity;
	}

	public FoodMeasureType getMeasure() {
		return measure;
	}

	public void setFood(FoodType food) {
		this.food = food;
	}

	public void setCalories(int calories) {
		this.calories = calories;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setMeasure(FoodMeasureType measure) {
		this.measure = measure;
	}

	public int getMealCode() {
		return mealCode;
	}

	public void setMealCode(int mealCode) {
		this.mealCode = mealCode;
	}	
}