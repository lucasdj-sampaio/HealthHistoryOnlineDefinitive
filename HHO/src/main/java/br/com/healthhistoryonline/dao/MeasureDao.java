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
import br.com.healthhistoryonline.sysmodel.Height;
import br.com.healthhistoryonline.sysmodel.Weight;

public class MeasureDao {
	
	public Pair<Boolean, String> insertMeasure(Weight weight, String userName){	
		ConnectionManager conn = new ConnectionManager();

		try {
			PreparedStatement insertWeight = conn.getConnection().prepareStatement("INSERT INTO T_PESO"
					+ "(cd_peso, nm_usuario, dt_inclusao, nr_peso)"
					+ "VALUES (PESO.Nextval, ?, ?, ?)");
			
			insertWeight.setString(1, userName);
			insertWeight.setDate(2, java.sql.Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(weight.getInclusionDate())));
			insertWeight.setFloat(3, weight.getWeight());
			
			if (conn.executeCommand(insertWeight, true) == 1) {
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
	
	public Pair<Boolean, String> insertMeasure(Height height, String userName){	
		ConnectionManager conn = new ConnectionManager();
    
		try {
			PreparedStatement insertHeight = conn.getConnection().prepareStatement("INSERT INTO T_ALTURA"
					+ "(cd_altura, nm_usuario, dt_inclusao, nr_altura)"
					+ "VALUES (ALTURA.Nextval, ?, ?, ?)");
			
			insertHeight.setString(1, userName);
			insertHeight.setDate(2, java.sql.Date.valueOf(height.getInclusionDate().toString()));
			insertHeight.setFloat(3, height.getHeight());
			
			if (conn.executeCommand(insertHeight, true) == 1) {
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
	
	public Pair<Boolean, String> updateMeasure(Height height){	
		ConnectionManager conn = new ConnectionManager();

		try {
			PreparedStatement updateHeight = conn.getConnection().prepareStatement("UPDATE T_ALTURA"
					+ "(SET nr_altura = ?, dt_inclusao = ?, WHERE cd_altura = ?)");
			
			updateHeight.setFloat(1, height.getHeight());
			updateHeight.setDate(2, java.sql.Date.valueOf(height.getInclusionDate().toString()));
			updateHeight.setInt(3, height.getHeightCode());
			
			if (conn.executeCommand(updateHeight, false) == 1) {
				conn.getConnection().commit();
				
				return new Pair<Boolean, String>(true, "Altera????o de altura conclu??da");
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
	
	public Pair<Boolean, String> updateMeasure(Weight weight){	
		ConnectionManager conn = new ConnectionManager();

		try {
			PreparedStatement updateWeight = conn.getConnection().prepareStatement("UPDATE T_PESO "
					+ "SET nr_peso = ?, dt_inclusao = ? WHERE cd_peso = ?");
			
			updateWeight.setFloat(1, weight.getWeight());
			updateWeight.setDate(2, java.sql.Date.valueOf(new SimpleDateFormat("yyyy-MM-dd")
					.format(weight.getInclusionDate())));
			updateWeight.setInt(3, weight.getWeightCode());
			
			if (conn.executeCommand(updateWeight, false) == 1) {
				conn.getConnection().commit();
				
				return new Pair<Boolean, String>(true, "Altera??o de peso conclu?da");
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
	
	public Pair<Boolean, String> deleteMeasure(Weight weight){	
		ConnectionManager conn = new ConnectionManager();

		try {
			PreparedStatement deleteWeight = conn.getConnection().prepareStatement("DELETE FROM T_PESO WHERE cd_peso = ?");
			
			deleteWeight.setInt(1, weight.getWeightCode());
			
			if (conn.executeCommand(deleteWeight, false) == 1) {
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
	
	public Pair<Boolean, String> deleteMeasure(Height height){	
		ConnectionManager conn = new ConnectionManager();

		try {
			PreparedStatement deleteHeight = conn.getConnection().prepareStatement("DELETE FROM T_ALTURA WHERE cd_altura = ?");
			
			deleteHeight.setInt(1, height.getHeightCode());
			
			if (conn.executeCommand(deleteHeight, false) == 1) {
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
		ConnectionManager conn = new ConnectionManager();
		
		try {
			Measure measure = new Measure();
			measure.setHeight(getLastHeight(conn, userName));
			measure.setWeight(getLastWeight(conn, userName));
			
			if(measure.getHeight() != null && measure.getHeight() != null) {
				measure.setImc();
			}
			
			return new Pair<Boolean, Measure>(true, measure);
		}
		finally {
			conn.closeConnection();
		}
	}
	
	private Weight getLastWeight(ConnectionManager conn, String userName) {
				
		try {
			PreparedStatement weight = conn.getConnection().prepareStatement("SELECT cd_peso, nr_peso "
					+ "FROM T_PESO WHERE nm_usuario = ? AND ROWNUM = 1 ORDER BY dt_inclusao DESC");
			
			weight.setString(1, userName);
			
			ResultSet response = conn.getData(weight);
		
			if (response != null) {
				if (response.next()) {
					Weight wei = new Weight();
					wei.setWeightCode(response.getInt(1));
					wei.setWeight(response.getFloat(2));
					
					return wei;
				}
			}
			
			return null;
		}
		catch (SQLException ex) 
		{
			ex.printStackTrace();
			return null;
		}
	}
	
	private Height getLastHeight(ConnectionManager conn, String userName) {
		
		try {
			PreparedStatement height = conn.getConnection().prepareStatement("SELECT cd_altura, nr_altura "
					+ "FROM T_ALTURA WHERE nm_usuario = ? AND ROWNUM = 1 ORDER BY dt_inclusao DESC");
			
			height.setString(1, userName);
			
			ResultSet response = conn.getData(height);
		
			if (response != null) {
				if (response.next()) {
					Height hei = new Height(response.getFloat(1));
					hei.setHeightCode(response.getInt(2));
					
					return hei;
				}
			}
			
			return null;
		}
		catch (SQLException ex) 
		{
			ex.printStackTrace();
			return null;
		}
	}
	
	public Pair<Boolean, List<Height>> getAllHeight(String userName) throws ParseException{
		ConnectionManager conn = new ConnectionManager();
		
		List<Height> heightList = new ArrayList<Height>();
		
		try {
			PreparedStatement height = conn.getConnection().prepareStatement("SELECT cd_altura, nr_altura, dt_inclusao "
					+ "FROM T_ALTURA WHERE nm_usuario = ? ORDER BY dt_inclusao ASC");
					
			height.setString(1, userName);
			ResultSet response = conn.getData(height);
			
			while (response.next()) {
				Height hei = new Height(response.getInt(2));
				hei.setHeightCode(response.getInt(1));
				hei.setInclusionDate(new SimpleDateFormat("dd/MM/yyyy").parse(response.getDate(3).toString()));
				
				heightList.add(hei);
			}			
		}
		catch (SQLException ex) 
		{
			ex.printStackTrace();
			return new Pair<Boolean, List<Height>>(false, null);
		}
		finally {
			conn.closeConnection();
		}
		
		return new Pair<Boolean, List<Height>>(true, heightList);
	}
	
	public Pair<Boolean, List<Weight>> getAllWeight(String userName) throws ParseException{
		ConnectionManager conn = new ConnectionManager();
		
		List<Weight> weightList = new ArrayList<Weight>();

		try {
			PreparedStatement weight = conn.getConnection().prepareStatement("SELECT cd_peso, nr_peso, dt_inclusao "
					+ "FROM T_PESO WHERE nm_usuario = ? ORDER BY dt_inclusao DESC");
					
			weight.setString(1, userName);
			ResultSet response = conn.getData(weight);
			
			if(response != null) {
				while (response.next()) {
					Weight wei = new Weight();
					wei.setWeight(response.getFloat(2));
					wei.setWeightCode(response.getInt(1));
					wei.setInclusionDate(response.getDate(3));
					
					weightList.add(wei);
				}		
			}
		}
		catch (SQLException ex) 
		{
			ex.printStackTrace();
			return new Pair<Boolean, List<Weight>>(false, null);
		}
		finally {
			conn.closeConnection();
		}
		
		return new Pair<Boolean, List<Weight>>(true, weightList);
	}
}