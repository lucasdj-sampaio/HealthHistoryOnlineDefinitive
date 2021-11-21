package br.com.healthhistoryonline.model;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import br.com.healthhistoryonline.sysmodel.File;

public class User{
/**@author ludiana pozzobon*/
	
	//-------- Atributos --------
	
	private String name;
	private String lastName;
	private String gender;
	private int age;
	private long cpf;
	private Date birthDate;
	private Phone phone;
	private File userPhoto;
	private Credential credential;
		
	//-------- Construtor --------
	
	/**@param M�todo para cria��o de usu�rio, usado para criar novos usu�rio no sistema, usando a heran�a de credential para controlar sess�o*/
	public User(String aName, String aLastName, char aGender, long aCpf , Date aBirthDate) {
		this.name = aName.substring(0, 1).toUpperCase() + aName.substring(1);
		this.lastName = aLastName.substring(0, 1).toUpperCase() + aLastName.substring(1);
		this.gender = completGender(aGender);
		this.cpf = aCpf;
		this.birthDate = aBirthDate;
		
		this.age = calcAge(aBirthDate);
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
	
	private int calcAge(Date aBirthDate) {
		Calendar dateOfBirth = new GregorianCalendar();

		dateOfBirth.setTime(aBirthDate);

		Calendar today = Calendar.getInstance();

		int age = today.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR);

		dateOfBirth.add(Calendar.YEAR, age);

		if (today.before(dateOfBirth)) {
			age--;
		}

		return age;
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

	public Phone getPhone() {
		return phone;
	}
	
	public void setPhone(Phone phones) {
		this.phone = phones;
	}
	
	public void setUserPhoto(File userPhoto) {
		this.userPhoto = userPhoto;
	}
	
	public File getUserPhoto() {
		return this.userPhoto;
	}

	public Credential getCredential() {
		return credential;
	}

	public void setCredential(Credential credential) {
		this.credential = credential;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setCpf(long cpf) {
		this.cpf = cpf;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public int getAge() {
		return age;
	}
}