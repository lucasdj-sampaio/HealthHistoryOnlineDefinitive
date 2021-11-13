package br.com.healthhistoryonline.model;
import br.com.healthhistoryonline.sysmodel.FoodType;
import br.com.healthhistoryonline.sysmodel.MeasureType;

public class Meal{
/**@author gabriela montefusco*/
	
	//-------- Atributos --------
	
	private FoodType food;
	private int calories;
	private int quantity;
	private MeasureType measure;
	
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

	public MeasureType getMeasure() {
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

	public void setMeasure(MeasureType measure) {
		this.measure = measure;
	}	
}