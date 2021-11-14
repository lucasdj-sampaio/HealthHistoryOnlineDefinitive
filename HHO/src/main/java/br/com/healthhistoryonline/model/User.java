package br.com.healthhistoryonline.model;
import java.util.Date;
import java.util.Set;

public class User{
/**@author ludiana pozzobon*/
	
	//-------- Atributos --------
	
	private String name;
	private String lastName;
	private String gender;
	private long cpf;
	private Date birthDate;
	private Set<Phone> phone;
	private String userPhoto;
	private Credential credential;
		
	//-------- Construtor --------
	
	/**@param M�todo para cria��o de usu�rio, usado para criar novos usu�rio no sistema, usando a heran�a de credential para controlar sess�o*/
	public User(String aName, String aLastName, char aGender, long aCpf , Date aBirthDate) {
		this.name = aName;
		this.lastName = aLastName;
		this.gender = completGender(aGender);
		this.cpf = aCpf;
		this.birthDate = aBirthDate;
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

	public String getName() {
		return name;
	}

	public String getLastName() {
		return lastName;
	}

	public String getGender() {
		return gender;
	}

	public long getCpf() {
		return cpf;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public Set<Phone> getPhone() {
		return phone;
	}
	
	public void setPhone(Set<Phone> phones) {
		this.phone = phones;
	}
	
	public void setUserPhoto(String userPhoto) {
		this.userPhoto = userPhoto;
	}
	
	public String getUserPhoto() {
		return this.userPhoto;
	}

	public Credential getCredential() {
		return credential;
	}

	public void setCredential(Credential credential) {
		this.credential = credential;
	}
}