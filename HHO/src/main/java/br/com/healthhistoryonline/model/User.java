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
	
	/**@param Método para criação de usuário, usado para criar novos usuário no sistema, usando a herança de credential para controlar sessão*/
	public User(String anUserName, String emailAdress, String passwordString, String aName, String aLastName, char aGender, long aCpf , String aBirthDate, List<Phone> aPhone) {
		this.name = aName;
		this.lastName = aLastName;
		this.gender = completGender(aGender);
		this.cpf = aCpf;
		this.birthDate = aBirthDate;
		this.phone = aPhone;
	}
	
	
	//-------- Métodos --------
	
	/**@param Metodo, recebe um char referente ao sexo do usuário*/
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