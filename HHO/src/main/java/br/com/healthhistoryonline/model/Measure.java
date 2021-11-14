package br.com.healthhistoryonline.model;
import br.com.healthhistoryonline.sysmodel.Weight;
import br.com.healthhistoryonline.sysmodel.Height;

public class Measure{
/**@author giovana agudo*/
	
	//-------- Atributos --------
	
	private Height height;
	private Weight weight; 
	private float imc;
	private String imcClassification;
	
	//-------- Construtor --------
	
	/**@param Construtor para criação do obj*/
	public Measure(Height height, Weight weight) {
		imcCalculate(height.getHeight(), weight.getWeight());
		this.height = height;
		this.weight = weight;
		
		this.imcClassification = getImcClassification(imc);
	}
	
	//-------- Métodos --------
	
	/**@param Método para calcular IMC*/
	private void imcCalculate(float height, float weight) {
		this.imc = weight / (height * height) ;
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

	public Height getHeight() {
		return height;
	}

	public Weight getWeight() {
		return weight;
	}

	public float getImc() {
		return imc;
	}

	public String getImcClassification() {
		return imcClassification;
	}
}
