package br.com.healthhistoryonline.dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import br.com.healthhistoryonline.model.Measure;
import br.com.healthhistoryonline.sysmodel.Pair;
import br.com.healthhistoryonline.sysmodel.Heigth;
import br.com.healthhistoryonline.sysmodel.Weigth;

public class MeasureDao {
	
	ConnectionManager conn = new ConnectionManager();
	
	public Pair<Boolean, String> insertMeasure(Weigth weigth, String userName){	
		try {
			PreparedStatement insertW = conn.getConnection().prepareStatement("INSERT INTO T_PESO"
					+ "(cd_peso, nm_usuario, dt_inclusao, nr_peso)"
					+ "VALUES (PESO.Nextval, ?, ?, ?)");
			
			insertW.setString(1, userName);
			insertW.setDate(2, java.sql.Date.valueOf(weigth.getInclusionDate().toString()));
			insertW.setFloat(3, weigth.getWeigth());
			
			if (conn.executeCommand(insertW, true) == 1) {
				return new Pair<Boolean, String>(true, "Peso incluso com sucesso!");
			}		
			
			return new Pair<Boolean, String>(false, "Erro ao cadastrar peso");
		}
		catch (SQLException ex) 
		{
			ex.printStackTrace();
			return new Pair<Boolean, String>(false, "Erro ao cadastrar peso");
		}
		finally {
			conn.closeConnection();
		}
	}
	
	public Pair<Boolean, String> insertMeasure(Heigth heigth, String userName){	
		try {
			PreparedStatement insertH = conn.getConnection().prepareStatement("INSERT INTO T_ALTURA"
					+ "(cd_altura, nm_usuario, dt_inclusao, nr_altura)"
					+ "VALUES (ALTURA.Nextval, ?, ?, ?)");
			
			insertH.setString(1, userName);
			insertH.setDate(2, java.sql.Date.valueOf(heigth.getInclusionDate().toString()));
			insertH.setFloat(3, heigth.getHeigth());
			
			if (conn.executeCommand(insertH, true) == 1) {
				return new Pair<Boolean, String>(true, "Altura incluso com sucesso!");
			}		
			
			return new Pair<Boolean, String>(false, "Erro ao cadastrar altura");
		}
		catch (SQLException ex) 
		{
			ex.printStackTrace();
			return new Pair<Boolean, String>(false, "Erro ao cadastrar altura");
		}
		finally {
			conn.closeConnection();
		}
	}
	
	public Pair<Boolean, String> updateMeasure(Heigth heigth){	
		try {
			PreparedStatement updateH = conn.getConnection().prepareStatement("UPDATE T_ALTURA"
					+ "(SET nr_altura = ?, dt_inclusao = ?, WHERE cd_altura = ?)");
			
			updateH.setFloat(1, heigth.getHeigth());
			updateH.setDate(2, java.sql.Date.valueOf(heigth.getInclusionDate().toString()));
			updateH.setInt(3, heigth.getHeigthCode());
			
			if (conn.executeCommand(updateH, false) == 1) {
				conn.getConnection().commit();
				
				return new Pair<Boolean, String>(true, "Alteração de altura concluída");
			}
			
			return new Pair<Boolean, String>(false, "Erro ao alterar altura");
		}
		catch (SQLException ex) 
		{
			ex.printStackTrace();
			return new Pair<Boolean, String>(false, "Erro ao alterar altura");
		}
		finally {
			conn.closeConnection();
		}
	}
	
	public Pair<Boolean, String> updateMeasure(Weigth weigth){	
		try {
			PreparedStatement updateW = conn.getConnection().prepareStatement("UPDATE T_PESO"
					+ "(SET nr_peso = ?, dt_inclusao = ?, WHERE cd_peso = ?)");
			
			updateW.setFloat(1, weigth.getWeigth());
			updateW.setDate(2, java.sql.Date.valueOf(weigth.getInclusionDate().toString()));
			updateW.setInt(3, weigth.getWeigthCode());
			
			if (conn.executeCommand(updateW, false) == 1) {
				conn.getConnection().commit();
				
				return new Pair<Boolean, String>(true, "Alteração de peso concluída");
			}
			
			return new Pair<Boolean, String>(false, "Erro ao alterar peso");
		}
		catch (SQLException ex) 
		{
			ex.printStackTrace();
			return new Pair<Boolean, String>(false, "Erro ao alterar peso");
		}
		finally {
			conn.closeConnection();
		}
	}
	
	public Pair<Boolean, String> deleteMeasure(Weigth weigth){	
		try {
			PreparedStatement deleteW = conn.getConnection().prepareStatement("DELETE FROM T_PESO WHERE cd_peso = ?");
			
			deleteW.setInt(1, weigth.getWeigthCode());
			
			if (conn.executeCommand(deleteW, false) == 1) {
				conn.getConnection().commit();
				
				return new Pair<Boolean, String>(true, "Peso removido com sucesso!");
			}
			
			conn.getConnection().rollback();
			return new Pair<Boolean, String>(false, "Erro ao deletar registro de peso");
		}
		catch (SQLException ex) 
		{
			ex.printStackTrace();
			return new Pair<Boolean, String>(false, "Erro ao deletar registro de peso");
		}
		finally {
			conn.closeConnection();
		}
	}
	
	public Pair<Boolean, String> deleteMeasure(Heigth heigth){	
		try {
			PreparedStatement deleteH = conn.getConnection().prepareStatement("DELETE FROM T_ALTURA WHERE cd_altura = ?");
			
			deleteH.setInt(1, heigth.getHeigthCode());
			
			if (conn.executeCommand(deleteH, false) == 1) {
				conn.getConnection().commit();
				
				return new Pair<Boolean, String>(true, "Altura removida com sucesso!");
			}
			
			conn.getConnection().rollback();
			return new Pair<Boolean, String>(false, "Erro ao deletar registro de altura");
		}
		catch (SQLException ex) 
		{
			ex.printStackTrace();
			return new Pair<Boolean, String>(false, "Erro ao deletar registro de altura");
		}
		finally {
			conn.closeConnection();
		}
	}
	
	//Get user measure
	public Pair<Boolean, Measure> getMeasure(String userName){
		
		try {
			PreparedStatement measure = conn.getConnection().prepareStatement("SELECT nr_peso, nr_altura FROM "
					+ "(SELECT nr_peso FROM T_PESO WHERE nm_usuario = ? AND ROWNUM = 1 ORDER BY dt_inclusao DESC), "
					+ "(SELECT nr_altura FROM T_ALTURA WHERE nm_usuario = ? AND ROWNUM = 1 ORDER BY dt_inclusao DESC)");
			
			measure.setString(1, userName);
			measure.setString(2, userName);
			
			ResultSet response = conn.getData(measure);
			
			if (response.next()) {
				return new Pair<Boolean, Measure>(true, new Measure(response.getFloat(2), response.getFloat(1)));
			}
			
			return new Pair<Boolean, Measure>(false, null);
		}
		catch (SQLException ex) 
		{
			ex.printStackTrace();
			return new Pair<Boolean, Measure>(false, null);
		}
		finally {
			conn.closeConnection();
		}
	}
	
	public Pair<Boolean, List<Heigth>> getAllHeigth(String userName) throws ParseException{
		List<Heigth> heigthList = new ArrayList<Heigth>();
		
		try {
			PreparedStatement heigth = conn.getConnection().prepareStatement("SELECT cd_altura, nr_altura, dt_inclusao "
					+ "FROM T_ALTURA WHERE nm_usuario = ? ORDER BY dt_inclusao ASC");
					
			heigth.setString(1, userName);
			ResultSet response = conn.getData(heigth);
			
			while (response.next()) {
				heigthList.add(new Heigth(response.getInt(1), response.getInt(2)
						, new SimpleDateFormat("dd/MM/yyyy").parse(response.getDate(3).toString())));
			}			
		}
		catch (SQLException ex) 
		{
			ex.printStackTrace();
			return new Pair<Boolean, List<Heigth>>(false, null);
		}
		finally {
			conn.closeConnection();
		}
		
		return new Pair<Boolean, List<Heigth>>(true, heigthList);
	}
	
	public Pair<Boolean, List<Weigth>> getAllWeigth(String userName) throws ParseException{
		List<Weigth> weigthList = new ArrayList<Weigth>();
		
		try {
			PreparedStatement weigth = conn.getConnection().prepareStatement("SELECT cd_peso, nr_peso, dt_inclusao "
					+ "FROM T_PESO WHERE nm_usuario = ? ORDER BY dt_inclusao ASC");
					
			weigth.setString(1, userName);
			ResultSet response = conn.getData(weigth);
			
			while (response.next()) {
				weigthList.add(new Weigth(response.getInt(1), response.getInt(2)
						, new SimpleDateFormat("dd/MM/yyyy").parse(response.getDate(3).toString())));
			}			
		}
		catch (SQLException ex) 
		{
			ex.printStackTrace();
			return new Pair<Boolean, List<Weigth>>(false, null);
		}
		finally {
			conn.closeConnection();
		}
		
		return new Pair<Boolean, List<Weigth>>(true, weigthList);
	}
}