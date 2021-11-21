package br.com.healthhistoryonline.model;
import br.com.healthhistoryonline.sysmodel.Weight;
import br.com.healthhistoryonline.sysmodel.Height;

public class Measure{
/**@author giovana agudo*/
	
	//-------- Atributos --------
	
	private Height height;
	private Weight weight; 
	private float imc;
		
	//-------- Métodos --------
	
	public void setImc() {
		this.imc = this.weight.getWeight() / (height.getHeight() * height.getHeight()) ;
	}

	public Height getHeight() {
		return height;
	}

	public Weight getWeight() {
		return weight;
	}

	public float getImc() {
		return imc;
	}

	public void setHeight(Height height) {
		this.height = height;
	}

	public void setWeight(Weight weight) {
		this.weight = weight;
	}
}
