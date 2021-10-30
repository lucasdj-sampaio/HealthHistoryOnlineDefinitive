package br.com.healthhistoryonline.model;
import java.util.List;

public class User{
/**@author ludiana pozzobon*/
	
	//-------- Atributos --------
	
	String name;
	String lastName;
	String gender;
	long cpf;
	String birthDate;
	List<Phone> phone;
		
	//-------- Construtor --------
	
	/**@param M�todo para cria��o de usu�rio, usado para criar novos usu�rio no sistema, usando a heran�a de credential para controlar sess�o*/
	public User(String anUserName, String emailAdress, String passwordString, String aName, String aLastName, char aGender, long aCpf , String aBirthDate, List<Phone> aPhone) {
		this.name = aName;
		this.lastName = aLastName;
		this.gender = completGender(aGender);
		this.cpf = aCpf;
		this.birthDate = aBirthDate;
		this.phone = aPhone;
	}
	
	
	//-------- M�todos --------
	
	/**@param Metodo, recebe um char referente ao sexo do usu�rio*/
	private String completGender(char gender) {
		switch(Character.toString(gender).toUpperCase()) {
			case "M":
				return "Masculino";
			case "F":
				return "Feminino";
			default:
				return "Outros";
		}
	}
}