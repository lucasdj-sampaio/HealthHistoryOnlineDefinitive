package br.com.healthhistoryonline.dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import br.com.healthhistoryonline.model.Phone;

public class PhoneDao {
	
	ConnectionManager conn = new ConnectionManager();
	
	/**
	 * @param data
	 */
	public void insertPhone(List<Phone> phoneDetails, String userName){	
		try {
			
			for (Phone phone : phoneDetails) {
				PreparedStatement phoneStat = conn.getConnection().prepareStatement("INSERT INTO T_TELEFONE"
						+ "(CD_TELEFONE, NM_USUARIO, NR_DDI, NR_DDD, NR_TELEFONE)"
						+ "VALUES (TELEFONE.Nextval, ?, ?, ?, ?)");
			
				phoneStat.setString(1, userName);
				phoneStat.setInt(2, phone.ddiNumber);
				phoneStat.setInt(3, phone.dddNumber);
				phoneStat.setInt(4, phone.number);

				conn.executeCommand(phoneStat);
			}	
		}
		catch (SQLException ex) 
		{
			ex.printStackTrace();
		}
		finally {
			conn.closeConnection();
		}
	}
	
	public static Set<Phone> getAll(String userName){
		Set<Phone> listValues = new HashSet<Phone>();
		
		try {
			PreparedStatement stat = conn.getConnection().prepareStatement("SELECT NR_DDI, NR_DDD, NR_TELEFONE FROM T_TELEFONE WHERE NM_USUARIO = ?");
			
			stat.setString(1, userName);
			
			ResultSet response = conn.getData(stat);
			
			while (response.next()) {
				listValues.add(new Phone(response.getInt(1),
						response.getInt(2),
						response.getInt(3)));
			}
			
			conn.closeConnection();			
		}
		catch (SQLException ex) 
		{
			ex.printStackTrace();
		}
		finally {
			conn.closeConnection();
		}
		
		return listValues;
	}
}