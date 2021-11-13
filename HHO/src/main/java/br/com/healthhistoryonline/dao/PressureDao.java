package br.com.healthhistoryonline.dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;
import br.com.healthhistoryonline.model.Pressure;
import br.com.healthhistoryonline.sysmodel.Pair;

public class PressureDao {

ConnectionManager conn = new ConnectionManager();

	public Pair<Boolean, String> insertPressure(Pressure pressureDetails, String userName){	
		try {
			PreparedStatement pressure = conn.getConnection().prepareStatement("INSERT INTO T_PRESSAO"
					+ "(CD_PRESSAO, NM_USUARIO, NR_DIASTOLICA, NR_SISTOLICA, DT_INCLUSAO)"
					+ "VALUES (PRESSAO.Nextval, ?, ?, ?, ?)");
					
			pressure.setString(1, userName);
			pressure.setInt(2, pressureDetails.getDiastolic());
			pressure.setInt(3, pressureDetails.getSystolic());			
			pressure.setDate(4, java.sql.Date.valueOf(pressureDetails.getInclusionDate().toString()));
		
			if (conn.executeCommand(pressure, true) == 1) {
				return new Pair<Boolean, String>(true, "Pressão inclusa com sucesso!");
			}		
			
			return new Pair<Boolean, String>(false, "Erro ao cadastrar pressão");
		}
		catch (SQLException ex) 
		{
			ex.printStackTrace();
			return new Pair<Boolean, String>(false, "Erro ao cadastrar pressão");
		}
		finally {
			conn.closeConnection();
		}
	}
	
	public Pair<Boolean, Set<Pressure>> getAll(String userName) throws ParseException{
		Set<Pressure> listValues = new HashSet<Pressure>();

		try {
			PreparedStatement stat = conn.getConnection().prepareStatement("SELECT CD_PRESSAO, NR_DIASTOLICA, NR_SISTOLICA, DT_INCLUSAO FROM T_PRESSAO "
					+ "WHERE NM_USUARIO = ?");
			
			stat.setString(1, userName);
			
			ResultSet response = conn.getData(stat);

			while (response.next()) {
				Pressure pressure = new Pressure(response.getInt(2),								
						response.getInt(3),
						new SimpleDateFormat("dd/MM/yyyy").parse(response.getString(4)));
				
				pressure.setPressureCode(response.getInt(1));
				
				listValues.add(pressure);
			}

			conn.closeConnection();	
			
			return new Pair<Boolean, Set<Pressure>>(true, listValues);
		}
		catch (SQLException ex) 
		{
			ex.printStackTrace();
			return new Pair<Boolean, Set<Pressure>>(false, null);
		}
		finally {
			conn.closeConnection();
		}
	}
	
	public Pair<Boolean, String> updatePressure(Pressure pressureDetails){	
		try {
			PreparedStatement pressure = conn.getConnection().prepareStatement("UPDATE T_PRESSAO"
					+ "SET NR_DIASTOLICA = ?, NR_SISTOLICA = ?, DT_INCLUSAO = ? WHERE CD_PRESSAO = ?");				
		
			pressure.setInt(1, pressureDetails.getDiastolic());
			pressure.setInt(2, pressureDetails.getSystolic());			
			pressure.setDate(3, java.sql.Date.valueOf(pressureDetails.getInclusionDate().toString()));
			pressure.setInt(4, pressureDetails.getPressureCode());
		
			if (conn.executeCommand(pressure, false) == 1) {
				conn.getConnection().commit();
				
				return new Pair<Boolean, String>(true, "Alteração de Pressão concluída");
			}
			
			conn.getConnection().rollback();
			return new Pair<Boolean, String>(false, "Erro ao alterar Pressão");
		}
		catch (SQLException ex) 
		{
			ex.printStackTrace();
			return new Pair<Boolean, String>(false, "Erro ao alterar Pressão");
		}
		finally {
			conn.closeConnection();
		}
	}
	
	public Pair<Boolean, String> deletePressure(Pressure pressureDetails, String userName){	
		try {
			PreparedStatement pressure = conn.getConnection().prepareStatement("DELETE FROM T_PRESSAO"
					+ "WHERE CD_PRESSAO = ?");				
		
			pressure.setInt(1, pressureDetails.getPressureCode());
		
			if (conn.executeCommand(pressure, false) == 1) {
				conn.getConnection().commit();
				
				return new Pair<Boolean, String>(true, "Pressão deletada com sucesso.");
			}
			
			conn.getConnection().rollback();
			return new Pair<Boolean, String>(false, "Erro ao deletar Pressão");
		}
		catch (SQLException ex) 
		{
			ex.printStackTrace();
			return new Pair<Boolean, String>(false, "Erro ao deletar Pressão");
		}
		finally {
			conn.closeConnection();
		}
	}
}