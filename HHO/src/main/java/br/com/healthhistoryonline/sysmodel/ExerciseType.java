package br.com.healthhistoryonline.sysmodel;

public class ExerciseType {
	
	private int exerciseCode;
	private String exercise;
	
	public int getExerciseCode() {
		return exerciseCode;
	}

	public String getExercise() {
		return exercise;
	}
	
	public void setExercise(String exercise) {
		this.exercise = exercise;
	}
	
	public void setExerciseCode(int exerciseCode) {
		this.exerciseCode = exerciseCode;
	}
}