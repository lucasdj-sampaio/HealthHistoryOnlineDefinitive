package br.com.healthhistoryonline.model;

public class Phone{
/**@author victor*/
	
	//-------- Atributos --------
	
	
	public int ddiNumber;
	public int dddNumber;
	public int number;
		
	//-------- Construtor --------
	
	/**@param Contrutor que recebe os dados de número dos usuário, obj criado em lista*/
	public Phone(int aDdi, int aDdd, int aNumber){
		this.ddiNumber = aDdi;
		this.dddNumber = aDdd;
		this.number = aNumber;
	}


}