package br.com.healthhistoryonline.dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import br.com.healthhistoryonline.model.Pair;

public class LoginDao {
	
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
			
			return new Pair<Boolean, String>(false, null);
		}
		catch (SQLException ex) 
		{
			ex.printStackTrace();
			return new Pair<Boolean, String>(false, null);
		}
		finally {
			conn.closeConnection();
		}
	}
}