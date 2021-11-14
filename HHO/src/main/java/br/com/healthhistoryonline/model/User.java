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
	
	/**@param Método para criação de usuário, usado para criar novos usuário no sistema, usando a herança de credential para controlar sessão*/
	public User(String aName, String aLastName, char aGender, long aCpf , Date aBirthDate) {
		this.name = aName;
		this.lastName = aLastName;
		this.gender = completGender(aGender);
		this.cpf = aCpf;
		this.birthDate = aBirthDate;
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