package br.com.healthhistoryonline.sysmodel;

public class FoodType {
	
	private int foodCode;
	private String food;
	
	public FoodType(int foodCode, String food) {
		this.foodCode = foodCode;
		this.food = food;
	}
	
	public int getFoodCode() {
		return foodCode;
	}

	public String getFood() {
		return food;
	}
}