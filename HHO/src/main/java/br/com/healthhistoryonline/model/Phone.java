package br.com.healthhistoryonline.model;

public class Phone{
/**@author victor*/
	
	//-------- Atributos --------
	
	private int numberCode;
	private int ddiNumber;
	private int dddNumber;
	private int number;
		
	//-------- Construtor --------
	
	/**@param Contrutor que recebe os dados de número dos usuário, obj criado em lista*/
	public Phone(int aDdi, int aDdd, int aNumber){
		this.ddiNumber = aDdi;
		this.dddNumber = aDdd;
		this.number = aNumber;
	}

	public int getDdiNumber() {
		return ddiNumber;
	}

	public int getDddNumber() {
		return dddNumber;
	}

	public int getNumber() {
		return number;
	}
	
	public int getNumberCode() {
		return numberCode;
	}
	
	public void setNumberCode(int numberCode) {
		this.numberCode = numberCode;
	}
}