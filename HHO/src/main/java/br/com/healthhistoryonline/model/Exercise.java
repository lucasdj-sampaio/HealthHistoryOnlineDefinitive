package br.com.healthhistoryonline.model;

public class Exercise{
/**@author gabriela montefusco*/	
	
	//-------- Atributos --------
	public int spentCalories;
	public int bpm;
	public String inclusionDate;
	public String typeExercise;
	
	//-------- Construtor --------
	
	/**@param criando obj de exercício*/
	public Exercise (int aSpentCalories, int aBpm, String aInclusionDate, String aTypeExercise) {
		this.spentCalories = aSpentCalories;
		this.bpm = aBpm;
		this.inclusionDate = aInclusionDate;
		this.typeExercise = aTypeExercise;
	}
}