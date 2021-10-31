package br.com.healthhistoryonline.model;

public class Pressure {
/**@author giovana agudo*/
	
	//-------- Atributos --------
	public int diastolic;
	public int systolic;
	public String classification;
	public String inclusionDate;
	
	//-------- Construtor --------
	
	/**@param Construtor para criação de obj de pressão*/
	public Pressure(int aSystolic, int aDiastolic, String ainclusionDate) {
		
		this.diastolic = aDiastolic;
		this.systolic = aSystolic;
		this.inclusionDate = ainclusionDate;
		
		this.classification = getPressureClassification(aSystolic, aDiastolic);
	}
	
	//-------- Métodos --------
	
	/**@param Metodo recebe os valores de diastolico e sistolico*/
	private String getPressureClassification(int systolic, int diastolic) {
		if(systolic < 105 && diastolic < 60) {
			return "Baixa";
		} else if (systolic < 120 && diastolic < 80) {
			return "Ótima";
		} else if (systolic < 130 && diastolic < 85) {
			return "Normal";
		} else if (systolic < 140 && diastolic < 90) {
			return "Acima do normal";
		} else if (systolic < 160 && diastolic < 100) {
			return "Hipertensão de grau I";
		} else if (systolic < 180 && diastolic < 110) {
			return "Hipertensão de grau II";
		} else {
			return "Hipertensão de grau III";
		}
	}
	/**@return retorna uma string com a classificação de pressão do usuário*/
}
