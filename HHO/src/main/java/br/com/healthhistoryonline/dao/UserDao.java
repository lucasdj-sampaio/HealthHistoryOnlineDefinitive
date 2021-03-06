package br.com.healthhistoryonline.dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import br.com.healthhistoryonline.model.Credential;
import br.com.healthhistoryonline.model.User;
import br.com.healthhistoryonline.sysmodel.File;
import br.com.healthhistoryonline.sysmodel.Pair;

public class UserDao {
	
	public Pair<Boolean, String> validLogin(String user, String password){
		ConnectionManager conn = new ConnectionManager();
		
		try {
			PreparedStatement stat = conn.getConnection().prepareStatement("SELECT COUNT(NM_USUARIO), NM_USUARIO "
					+ "FROM T_CREDENCIAL WHERE (NM_USUARIO = ? OR EMAIL = ?) AND SENHA = ?"
					+ "GROUP BY NM_USUARIO");
			
			stat.setString(1, user);
			stat.setString(2, user);
			stat.setString(3, password);
			
			ResultSet response = conn.getData(stat);
			
			if (response.next()) {
				if (response.getInt(1) == 1) {
					return new Pair<Boolean, String>(true, response.getString(1));
				}
			}
		
			return new Pair<Boolean, String>(false, "Usu?rio/Senha inv?lido");
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
		ConnectionManager conn = new ConnectionManager();
		
		try {
			if (!validLogin(conn, userData.getCredential().getUserName())) {
				return new Pair<Boolean, String>(false, "Usu?rio j? cadastrado!");
			}
			
			if (!includeUser(conn, userData.getCredential())) {
				conn.getConnection().rollback();
				return new Pair<Boolean, String>(false, "Erro ao criar credenciais");	
			}
			
			if (!includeUser(conn, userData)) {
				conn.getConnection().rollback();
				return new Pair<Boolean, String>(false, "Erro ao cadastrar usu?rios");
			}
			
			if (!PhoneDao.insertPhone(conn, userData.getPhone(), userData.getCredential().getUserName()).getFirst()) {
				conn.getConnection().rollback();
				return new Pair<Boolean, String>(false, "Erro ao cadastrar telefone");
			}
			
			conn.getConnection().commit();
			return new Pair<Boolean, String>(true, "Usu?rio cadastrado com sucesso");		
		}
		catch (SQLException ex) 
		{
			ex.printStackTrace();
			return new Pair<Boolean, String>(false, "Erro ao cadastrar usu?rios");
		}
		finally {
			conn.closeConnection();
		}
	}
	
	private boolean includeUser(ConnectionManager conn, User userData) {	
		
		try {
			PreparedStatement newUser = conn.getConnection().prepareStatement("INSERT INTO T_USUARIO (NM_USUARIO, NOME, "
					+ "SOBRENOME, NR_CPF, DT_NASCIMENTO, DS_SEXO) VALUES (?, ?, ?, ?, ?, ?)");
			
			newUser.setString(1, userData.getCredential().getUserName());
			newUser.setString(2, userData.getName());
			newUser.setString(3, userData.getLastName());
			newUser.setLong(4, userData.getCpf());
			newUser.setDate(5, java.sql.Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(userData.getBirthDate())));
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
	}
	
	private boolean includeUser(ConnectionManager conn, Credential credential) {
		
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
	}
	
	public Pair<Boolean, User> getUser(String userName) {
		ConnectionManager conn = new ConnectionManager();
		
		try {
			PreparedStatement getUser = conn.getConnection().prepareStatement("SELECT NOME, SOBRENOME, NR_CPF, DT_NASCIMENTO, DS_SEXO"
					+ ", U.CD_ARQUIVO, A.CAMINHO, C.NM_USUARIO FROM T_USUARIO U "
					+ "INNER JOIN T_CREDENCIAL C ON U.NM_USUARIO = C.NM_USUARIO "
					+ "LEFT JOIN T_ARQUIVO A ON A.CD_ARQUIVO = U.CD_ARQUIVO "
					+ "WHERE C.NM_USUARIO = ? OR C.EMAIL = ?");
			
			getUser.setString(1, userName);
			getUser.setString(2, userName);
			
			ResultSet response = conn.getData(getUser);
			
			if (response.next()) {
				User user = new User(response.getString(1), response.getString(2)
						, response.getString(5).charAt(0), response.getLong(3), response.getDate(4));
							
				user.setCredential(getCredential(response.getString(8)));
				
				File photo = new File(response.getString(7) == null 
						? "./_img/Usuario/"+ user.getGender() +".png" 
						: response.getString(7));
				photo.setFileCode(response.getInt(6) > 0 
						? response.getInt(6)
						: 0);
				
				user.setUserPhoto(photo);
				
				user.setPhone(PhoneDao.getAll(conn, user.getCredential().getUserName()));
				
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
		ConnectionManager conn = new ConnectionManager();
		
		try {
			PreparedStatement getCredential = conn.getConnection().prepareStatement("SELECT EMAIL, SENHA "
					+ "FROM T_CREDENCIAL WHERE NM_USUARIO = ?");
			
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
		ConnectionManager conn = new ConnectionManager();
		
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
		ConnectionManager conn = new ConnectionManager();
		
		try { 
			PreparedStatement updateUser = conn.getConnection().prepareStatement("UPDATE T_USUARIO "
					+ "SET NOME = ?, SOBRENOME = ?, NR_CPF = ?, DT_NASCIMENTO = ?, DS_SEXO = ? "
					+ "WHERE NM_USUARIO = ?");
		
			updateUser.setString(1, user.getName());
			updateUser.setString(2, user.getLastName());
			updateUser.setLong(3, user.getCpf());
			updateUser.setDate(4, java.sql.Date.valueOf(user.getBirthDate().toString()));
			updateUser.setString(5, String.valueOf(user.getGender().charAt(0)).toString());
			
			if (conn.executeCommand(updateUser, false) > 1) {
				conn.getConnection().rollback();
				return new Pair<Boolean, String>(false, "Erro ao atualizar dados do usu?rio");
			}
			
			conn.getConnection().commit();
			return new Pair<Boolean, String>(true, "Usu?rio atualizado com sucesso!");
		}
		catch (SQLException ex) 
		{
			ex.printStackTrace();
			return new Pair<Boolean, String>(false, "Erro ao atualizar dados do usu?rio");
		}
		finally {
			conn.closeConnection();
		}
	} 
	
	public Pair<Boolean, String> updatePhoto(File photo) {
		ConnectionManager conn = new ConnectionManager();
		
		try { 
			if(photo.getFileCode() != 0) {
				PreparedStatement updatePhoto = conn.getConnection().prepareStatement("UPDATE T_ARQUIVO "
						+ "SET CAMINHO = ? WHERE CD_ARQUIVO = ?");
			
				updatePhoto.setString(1, photo.getFileName());
		        updatePhoto.setInt(2, photo.getFileCode());
				
				if (conn.executeCommand(updatePhoto, false) <= 1) {
					conn.getConnection().commit();
					return new Pair<Boolean, String>(true, "Foto atualizada com sucesso");
				}
			}
			else {
				PreparedStatement insertPhoto = conn.getConnection().prepareStatement("INSERT INTO T_ARQUIVO "
						+ "(CAMINHO, CD_ARQUIVO) VALUES (?, ARQUIVO.Nextval)");
			
				insertPhoto.setString(1, photo.getFileName());
				
				if (conn.executeCommand(insertPhoto, false) == 1) {
					conn.getConnection().commit();
					return new Pair<Boolean, String>(true, "Foto inclu?da com sucesso");
				}
			}
			
			conn.getConnection().rollback();
			return new Pair<Boolean, String>(false, "Erro ao atualizar foto do usu?rio");
		}
		catch (SQLException ex) 
		{
			ex.printStackTrace();
			return new Pair<Boolean, String>(false, "Erro ao atualizar foto do usu?rio");
		}
		finally {
			conn.closeConnection();
		}
	}
	
	private boolean validLogin(ConnectionManager conn, String userName){
		
		try {
			PreparedStatement stat = conn.getConnection().prepareStatement("SELECT NM_USUARIO "
					+ "FROM T_CREDENCIAL WHERE NM_USUARIO = ? OR EMAIL = ?");
			
			stat.setString(1, userName);
			stat.setString(2, userName);
			
			ResultSet response = conn.getData(stat);
			
			if(response != null) {
				if (response.next()) {
					return false;
				}
			}
			
			return true;
		}
		catch (SQLException ex) 
		{
			ex.printStackTrace();
			return false;
		}
	}
}