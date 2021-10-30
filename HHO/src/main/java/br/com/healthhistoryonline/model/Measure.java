package br.com.healthhistoryonline.model;

public class Measure{
/**@author giovana agudo*/
	
	//-------- Atributos --------
	
	public float height;
	public float weight; 
	public float imc;
	public String imcClassification;
	
	//-------- Construtor --------
	
	/**@param Construtor para criação do obj*/
	public Measure(float aHeight, float aWeight) {
		this.imc = imcCalculate(aHeight, aWeight);
		this.height = aHeight;
		this.weight = aWeight;
		
		this.imcClassification = getImcClassification(imc);
	}
	
	//-------- Métodos --------
	
	/**@param Método para calcular IMC*/
	private float imcCalculate(float height, float weight) {
		return weight / (height * height) ;
	}
	/**@return retorna calculo de IMC para guardar o valor do calculo*/
	
	/**@param Método criado para classificar o imc do usuário*/
	private String getImcClassification(float imc) {
		if(imc <18.5f) {
			return "Abaixo do peso";
		} else if (imc <25.0f) {
			return "Peso normal";
		} else if (imc <30.0f) {
			return "Sobrepeso";
		} else if (imc <35.0f) {
			return "Obesidade grau I";
		} else if (imc <40.0f) {
			return "Obesidade grau II";
		} else {
			return "Obesidade grau III";
		}
	}
	/**@return retorna uma string para registrar o retorno*/
}
