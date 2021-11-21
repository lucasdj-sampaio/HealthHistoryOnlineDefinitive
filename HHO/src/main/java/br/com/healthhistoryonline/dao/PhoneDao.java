package br.com.healthhistoryonline.dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import br.com.healthhistoryonline.model.Phone;
import br.com.healthhistoryonline.sysmodel.Pair;

public class PhoneDao {
	
	public static Pair<Boolean, String> insertPhone(ConnectionManager conn, Phone phone, String userName){	
		
		try {
			PreparedStatement phoneStat = conn.getConnection().prepareStatement("INSERT INTO T_TELEFONE"
					+ "(CD_TELEFONE, NM_USUARIO, NR_DDI, NR_DDD, NR_TELEFONE)"
					+ "VALUES (TELEFONE.Nextval, ?, ?, ?, ?)");
		
			phoneStat.setString(1, userName);
			phoneStat.setInt(2, phone.getDdiNumber());
			phoneStat.setInt(3, phone.getDddNumber());
			phoneStat.setInt(4, phone.getNumber());
			
			if (conn.executeCommand(phoneStat, false) != 1) {
				conn.getConnection().rollback();
				return new Pair<Boolean, String>(false, "Falha ao cadastrar telefone!");
			}	
		
			return new Pair<Boolean, String>(true, "Inclusão de telefone realizada!");
		}
		catch (SQLException ex) 
		{
			ex.printStackTrace();
			return new Pair<Boolean, String>(false, "Falha ao cadastrar telefone!");
		}
	}
	
	public static Phone getAll(ConnectionManager conn, String userName){
		try {
			PreparedStatement phoneStat = conn.getConnection().prepareStatement("SELECT NR_DDI, NR_DDD, NR_TELEFONE, "
					+ "CD_TELEFONE FROM T_TELEFONE WHERE NM_USUARIO = ?");
			
			phoneStat.setString(1, userName);
			
			ResultSet response = conn.getData(phoneStat);
			
			if (response.next()) {
				Phone phone = new Phone(response.getInt(1),
						response.getInt(2),
						response.getInt(3));
				
				phone.setNumberCode(response.getInt(4));
				
				return phone;
			}		
		}
		catch (SQLException ex) 
		{
			ex.printStackTrace();
		}
		finally {
			conn.closeConnection();
		}
		
		return null;
	}
	
	public Pair<Boolean, String> updatePhone(Phone phone){	
		ConnectionManager conn = new ConnectionManager();
		try {		
	
			PreparedStatement phoneStat = conn.getConnection().prepareStatement("UPDATE T_TELEFONE "
					+ "SET NR_DDI = ?, NR_DDD = ?, NR_TELEFONE = ? "
					+ "WHERE CD_TELEFONE = ?");
			
			phoneStat.setInt(1, phone.getDdiNumber());
			phoneStat.setInt(2, phone.getDddNumber());
			phoneStat.setInt(3, phone.getNumber());
			phoneStat.setInt(4, phone.getNumberCode());
			
			if (conn.executeCommand(phoneStat, false) <= 1) {
				conn.getConnection().commit();
				return new Pair<Boolean, String>(true, "Refeição atualizada com sucesso!");
			}
			
			conn.getConnection().rollback();
			return new Pair<Boolean, String>(false, "Falha ao atualiazar telefone!");
		}
		catch (SQLException ex) 
		{
			ex.printStackTrace();
			return new Pair<Boolean, String>(false, "Falha ao atualiazar telefone!");
		}
		finally {
			conn.closeConnection();
		}
	}
	
	public Pair<Boolean, String> deletePhone(Phone phone){	
		ConnectionManager conn = new ConnectionManager();
		try {		
	
			PreparedStatement phoneStat = conn.getConnection().prepareStatement("DELETE T_TELEFONE "
					+ "WHERE CD_TELEFONE = ?");
			
			phoneStat.setInt(4, phone.getNumberCode());
			
			if (conn.executeCommand(phoneStat, false) == 1) {
				conn.getConnection().commit();
				return new Pair<Boolean, String>(true, "Remoção de telefone concluída!");
			}
			
			conn.getConnection().rollback();
			return new Pair<Boolean, String>(false, "Falha ao deletar telefone!");
		}
		catch (SQLException ex) 
		{
			ex.printStackTrace();
			return new Pair<Boolean, String>(false, "Falha ao deletar telefone!");
		}
		finally {
			conn.closeConnection();
		}
	}
}