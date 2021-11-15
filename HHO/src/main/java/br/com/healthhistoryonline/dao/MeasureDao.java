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
	
	ConnectionManager conn = new ConnectionManager();
	
	public Pair<Boolean, String> insertMeasure(Weight weight, String userName){	
		try {
			PreparedStatement insertWeight = conn.getConnection().prepareStatement("INSERT INTO T_PESO"
					+ "(cd_peso, nm_usuario, dt_inclusao, nr_peso)"
					+ "VALUES (PESO.Nextval, ?, ?, ?)");
			
			insertWeight.setString(1, userName);
			insertWeight.setDate(2, java.sql.Date.valueOf(weight.getInclusionDate().toString()));
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
		try {
			PreparedStatement updateHeight = conn.getConnection().prepareStatement("UPDATE T_ALTURA"
					+ "(SET nr_altura = ?, dt_inclusao = ?, WHERE cd_altura = ?)");
			
			updateHeight.setFloat(1, height.getHeight());
			updateHeight.setDate(2, java.sql.Date.valueOf(height.getInclusionDate().toString()));
			updateHeight.setInt(3, height.getHeightCode());
			
			if (conn.executeCommand(updateHeight, false) == 1) {
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
	
	public Pair<Boolean, String> updateMeasure(Weight weight){	
		try {
			PreparedStatement updateWeight = conn.getConnection().prepareStatement("UPDATE T_PESO"
					+ "(SET nr_peso = ?, dt_inclusao = ?, WHERE cd_peso = ?)");
			
			updateWeight.setFloat(1, weight.getWeight());
			updateWeight.setDate(2, java.sql.Date.valueOf(weight.getInclusionDate().toString()));
			updateWeight.setInt(3, weight.getWeightCode());
			
			if (conn.executeCommand(updateWeight, false) == 1) {
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
	
	public Pair<Boolean, String> deleteMeasure(Weight weight){	
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
		
		try {
			PreparedStatement measure = conn.getConnection().prepareStatement("SELECT * FROM "
					+ "(SELECT cd_peso, nr_peso FROM T_PESO WHERE nm_usuario = ? AND ROWNUM = 1 ORDER BY dt_inclusao DESC), "
					+ "(SELECT cd_altura, nr_altura FROM T_ALTURA WHERE nm_usuario = ? AND ROWNUM = 1 ORDER BY dt_inclusao DESC)");
			
			measure.setString(1, userName);
			measure.setString(2, userName);
			
			ResultSet response = conn.getData(measure);
			
			if (response.next()) {
				Height hei = new Height(response.getInt(3), response.getFloat(4));
				Weight wei = new Weight(response.getInt(1), response.getFloat(2));
				
				return new Pair<Boolean, Measure>(true, new Measure(hei, wei));
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
	
	public Pair<Boolean, List<Height>> getAllHeight(String userName) throws ParseException{
		List<Height> heightList = new ArrayList<Height>();
		
		try {
			PreparedStatement height = conn.getConnection().prepareStatement("SELECT cd_altura, nr_altura, dt_inclusao "
					+ "FROM T_ALTURA WHERE nm_usuario = ? ORDER BY dt_inclusao ASC");
					
			height.setString(1, userName);
			ResultSet response = conn.getData(height);
			
			while (response.next()) {
				Height hei = new Height(response.getInt(1), response.getInt(2));
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
		List<Weight> weightList = new ArrayList<Weight>();
		
		try {
			PreparedStatement weight = conn.getConnection().prepareStatement("SELECT cd_peso, nr_peso, dt_inclusao "
					+ "FROM T_PESO WHERE nm_usuario = ? ORDER BY dt_inclusao ASC");
					
			weight.setString(1, userName);
			ResultSet response = conn.getData(weight);
			
			while (response.next()) {
				Weight wei = new Weight(response.getInt(1), response.getFloat(2));
				wei.setInclusionDate(new SimpleDateFormat("dd/MM/yyyy").parse(response.getDate(3).toString()));
				
				weightList.add(wei);
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