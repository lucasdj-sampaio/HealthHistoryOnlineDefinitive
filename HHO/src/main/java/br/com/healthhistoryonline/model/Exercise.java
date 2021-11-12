package br.com.healthhistoryonline.model;
import java.util.Date;

import br.com.healthhistoryonline.sysmodel.ExerciseType;

public class Exercise{
/**@author gabriela montefusco*/	
	
	//-------- Atributos --------
	private int activityId;
	private int spentCalories;
	private int bpm;
	private Date inclusionDate;
	private ExerciseType type;
	
	//-------- Construtor --------
	
	/**@param criando obj de exercício*/
	public Exercise (int aSpentCalories, int aBpm, Date inclusionDate) {
		this.spentCalories = aSpentCalories;
		this.bpm = aBpm;
		this.inclusionDate = inclusionDate;
	}
	
	public int getActivityId() {
		return activityId;
	}
	
	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}

	public int getSpentCalories() {
		return spentCalories;
	}

	public int getBpm() {
		return bpm;
	}

	public Date getInclusionDate() {
		return inclusionDate;
	}
	
	public ExerciseType getType() {
		return type;
	}
	
	public void setType(ExerciseType type) {
		this.type = type;
	}
}