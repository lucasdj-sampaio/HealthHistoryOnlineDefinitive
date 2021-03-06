package br.com.healthhistoryonline.model;
import java.util.Date;

public class Pressure {
/**@author giovana agudo*/
	
	//-------- Atributos --------
	private int pressureCode;
	private int diastolic;
	private int systolic;
	private String classification;
	private Date inclusionDate;
	
	//-------- Construtor --------
	
	/**@param Construtor para cria??o de obj de press?o*/
	public Pressure(int aSystolic, int aDiastolic, Date ainclusionDate) {
		this.diastolic = aDiastolic;
		this.systolic = aSystolic;
		this.inclusionDate = ainclusionDate;
		
		this.classification = getPressureClassification(aSystolic, aDiastolic);
	}
	
	//-------- M?todos --------
	
	/**@param Metodo recebe os valores de diastolico e sistolico*/
	private String getPressureClassification(int systolic, int diastolic) {
		if(systolic < 105 && diastolic < 60) {
			return "Baixa";
		} else if (systolic < 120 && diastolic < 80) {
			return "?tima";
		} else if (systolic < 130 && diastolic < 85) {
			return "Normal";
		} else if (systolic < 140 && diastolic < 90) {
			return "Acima do normal";
		} else if (systolic < 160 && diastolic < 100) {
			return "Hipertens?o de grau I";
		} else if (systolic < 180 && diastolic < 110) {
			return "Hipertens?o de grau II";
		} else {
			return "Hipertens?o de grau III";
		}
	}
	/**@return retorna uma string com a classifica??o de press?o do usu?rio*/

	public int getDiastolic() {
		return diastolic;
	}

	public int getSystolic() {
		return systolic;
	}

	public String getClassification() {
		return classification;
	}

	public Date getInclusionDate() {
		return inclusionDate;
	}

	public int getPressureCode() {
		return pressureCode;
	}

	public void setPressureCode(int pressureCode) {
		this.pressureCode = pressureCode;
	}
}
