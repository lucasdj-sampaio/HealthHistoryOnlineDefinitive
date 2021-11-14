package br.com.healthhistoryonline.dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.healthhistoryonline.model.Credential;
import br.com.healthhistoryonline.model.User;
import br.com.healthhistoryonline.sysmodel.Pair;

public class UserDao {
	
	ConnectionManager conn = new ConnectionManager();
	
	public Pair<Boolean, String> validLogin(String user, String password){
		try {
			PreparedStatement stat = conn.getConnection().prepareStatement("SELECT COUNT(NM_USUARIO), NM_USUARIO "
					+ "FROM T_CREDENCIAL WHERE NM_USUARIO = ? OR EMAIL = ? AND SENHA = ? "
					+ "GROUP BY NM_USUARIO;");
			
			stat.setString(1, user);
			stat.setString(2, user);
			stat.setString(3, password);
			
			ResultSet response = conn.getData(stat);
			
			if (response.next()) {
				if (response.getInt(1) == 1) {
					return new Pair<Boolean, String>(true, response.getString(1));
				}
			}
			
			return new Pair<Boolean, String>(false, "Usuário/Senha inválido");
		}
		catch (SQLException ex) 
		{
			ex.printStackTrace();
			return new Pair<Boolean, String>(false, "Erro ao realizar acesso");
		}
		finally {
			conn.closeConnection();
		}
	}
	
	public Pair<Boolean, String> insertUser(User userData) {
		try {
			if (!validLogin(userData.getCredential().getUserName())) {
				return new Pair<Boolean, String>(false, "Usuário já cadastrado!");
			}
			
			if (!includeUser(userData.getCredential())) {
				conn.getConnection().rollback();
				return new Pair<Boolean, String>(false, "Erro ao criar credenciais");	
			}
			
			if (!includeUser(userData)) {
				conn.getConnection().rollback();
				return new Pair<Boolean, String>(false, "Erro ao cadastrar usuários");
			}
			
			conn.getConnection().commit();
			return new Pair<Boolean, String>(true, "Usuário cadastrado com sucesso");		
		}
		catch (SQLException ex) 
		{
			ex.printStackTrace();
			return new Pair<Boolean, String>(false, "Erro ao cadastrar usuários");
		}
		finally {
			conn.closeConnection();
		}
	}
	
	private boolean includeUser(User userData) {
		try {
			PreparedStatement newUser = conn.getConnection().prepareStatement("INSERT INTO T_USUARIO (?, ?, ?, ?, ?, ?)");
			
			newUser.setString(1, userData.getCredential().getUserName());
			newUser.setString(2, userData.getName());
			newUser.setString(3, userData.getLastName());
			newUser.setLong(4, userData.getCpf());
			newUser.setDate(5, java.sql.Date.valueOf(userData.getBirthDate().toString()));
			newUser.setString(6, String.valueOf(userData.getGender().charAt(0)).toString());
			
			if (conn.executeCommand(newUser, false) == 1) {
				return true;
			}
			
			return false;
		}
		catch (SQLException ex) 
		{
			ex.printStackTrace();
			return false;
		}
		finally {
			conn.closeConnection();
		}
	}
	
	private boolean includeUser(Credential credential) {
		try {
			PreparedStatement newUser = conn.getConnection().prepareStatement("INSERT INTO T_CREDENCIAL "
					+ "VALUES (?,?,?)");
			
			newUser.setString(1, credential.getUserName());
			newUser.setString(2, credential.getMailAddress());
			newUser.setString(3, credential.getPassword());
			
			if (conn.executeCommand(newUser, false) == 1) {
				return true;
			}
			
			return false;
		}
		catch (SQLException ex) 
		{
			ex.printStackTrace();
			return false;
		}
		finally {
			conn.closeConnection();
		}
	}
	
	public Pair<Boolean, User> getUser(String userName) {
		try {
			PreparedStatement getUser = conn.getConnection().prepareStatement("SELECT NOME, SOBRENOME, NR_CPF"
					+ ", DT_NASCIMENTO, DS_SEXO, CD_ARQUIVO FROM T_USUARIO WHERE NM_USUARIO = ?");
			
			getUser.setString(1, userName);
			
			ResultSet response = conn.getData(getUser);
			
			if (response.next()) {
				User user = new User(response.getString(1), response.getString(2)
						, response.getString(5).charAt(0), response.getLong(3), response.getDate(4));
				
				user.setUserPhoto(response.getString(6));
				user.setPhone(PhoneDao.getAll(conn, userName));
				user.setCredential(getCredential(userName));
				
				return new Pair<Boolean, User>(true, user);
			}
			
			return new Pair<Boolean, User>(false, null);
		}
		catch (SQLException ex) 
		{
			ex.printStackTrace();
			return new Pair<Boolean, User>(false, null);
		}
		finally {
			conn.closeConnection();
		}
	}
	
	private Credential getCredential(String userName){
		try {
			PreparedStatement getCredential = conn.getConnection().prepareStatement("SELECT EMAIL, SENHA "
					+ "WHERE NM_USUARIO = ?");
			
			getCredential.setString(1, userName);
			
			ResultSet response = conn.getData(getCredential);
			
			if (response.next()) {
				return new Credential(userName, response.getString(1), response.getString(2));
			}
			
			return null;
		}
		catch (SQLException ex) 
		{
			ex.printStackTrace();
			return null;
		}
		finally {
			conn.closeConnection();
		}
	}
		
	public Pair<Boolean, String> updateUser(Credential credential){
		try {
			PreparedStatement updateUser = conn.getConnection().prepareStatement("UPDATE T_CREDENCIAL "
					+ "SET EMAIL = ?, SENHA = ? WHERE NM_USUARIO = ?");
		
			updateUser.setString(1, credential.getMailAddress());
			updateUser.setString(2, credential.getPassword());
			updateUser.setString(3, credential.getUserName());
			
			if (conn.executeCommand(updateUser, true) <= 1) {
				return new Pair<Boolean, String>(true, "Credenciais atualizadas com sucesso!");
			}
			
			return new Pair<Boolean, String>(false, "Erro ao atualizar credenciais");
		}
		catch (SQLException ex) 
		{
			ex.printStackTrace();
			return new Pair<Boolean, String>(false, "Erro ao atualizar credenciais");
		}
		finally {
			conn.closeConnection();
		}
	}
	
	public Pair<Boolean, String> updateUser(User user){
		try {
			PreparedStatement updateUser = conn.getConnection().prepareStatement("UPDATE T_USUARIO "
					+ "SET NOME = ?, SOBRENOME = ?, NR_CPF = ?, DT_NASCIMENTO = ?, DS_SEXO = ?, CD_ARQUIVO = ? "
					+ "WHERE NM_USUARIO = ?");
		
			updateUser.setString(1, user.getName());
			updateUser.setString(2, user.getLastName());
			updateUser.setLong(3, user.getCpf());
			updateUser.setDate(4, java.sql.Date.valueOf(user.getBirthDate().toString()));
			updateUser.setString(5, String.valueOf(user.getGender().charAt(0)).toString());
			updateUser.setString(6, user.getUserPhoto());
			
			if (conn.executeCommand(updateUser, true) <= 1) {
				return new Pair<Boolean, String>(true, "Usuário atualizado com sucesso!");
			}
			
			return new Pair<Boolean, String>(false, "Erro ao atualizar dados do usuário");
		}
		catch (SQLException ex) 
		{
			ex.printStackTrace();
			return new Pair<Boolean, String>(false, "Erro ao atualizar dados do usuário");
		}
		finally {
			conn.closeConnection();
		}
	} 
	
	private boolean validLogin(String userName){
		try {
			PreparedStatement stat = conn.getConnection().prepareStatement("SELECT NM_USUARIO "
					+ "FROM T_CREDENCIAL WHERE NM_USUARIO = ? OR EMAIL = ?");
			
			stat.setString(1, userName);
			stat.setString(2, userName);
			
			ResultSet response = conn.getData(stat);
			
			if (response.next()) {
				return false;
			}
			
			return true;
		}
		catch (SQLException ex) 
		{
			ex.printStackTrace();
			return false;
		}
		finally {
			conn.closeConnection();
		}
	}
}