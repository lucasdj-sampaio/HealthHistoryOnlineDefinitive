package br.com.healthhistoryonline.dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import br.com.healthhistoryonline.model.Pressure;

public class PressureDao {

ConnectionManager conn = new ConnectionManager();
	
	/**
	 * @param data
	 */
	public void insertPressure(Pressure pressureDetails, String userName){	
		try {
			PreparedStatement pressure = conn.getConnection().prepareStatement("INSERT INTO T_PRESSAO"
					+ "(CD_PRESSAO, NM_USUARIO, NR_DIASTOLICA, NR_SISTOLICA, DT_INCLUSAO)"
					+ "VALUES (PRESSAO.Nextval, ?, ?, ?, ?)");
					
		
			pressure.setString(1, userName);
			pressure.setInt(2, pressureDetails.diastolic);
			pressure.setInt(3, pressureDetails.systolic);			
			pressure.setDate(4, java.sql.Date.valueOf(pressureDetails.inclusionDate));
		
			conn.executeCommand(pressure);			
			
		}
		catch (SQLException ex) 
		{
			ex.printStackTrace();
		}
		finally {
			conn.closeConnection();
		}
	}
	
	public Set<Pressure> getAll(String userName){
		Set<Pressure> listValues = new HashSet<Pressure>();

		try {
			PreparedStatement stat = conn.getConnection().prepareStatement("SELECT NR_DIASTOLICA, NR_SISTOLICA, DT_INCLUSAO FROM T_PRESSAO "
					+ "WHERE NM_USUARIO = ?");
			
			stat.setString(1, userName);
			
			ResultSet response = conn.getData(stat);

			while (response.next()) {
				listValues.add(new Pressure(response.getInt(1),								
						response.getInt(2),
						response.getString(3)));
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