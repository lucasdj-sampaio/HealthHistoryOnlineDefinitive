package br.com.healthhistoryonline.dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import br.com.healthhistoryonline.model.Meal;
import br.com.healthhistoryonline.model.Snack;
import br.com.healthhistoryonline.sysmodel.FoodType;
import br.com.healthhistoryonline.sysmodel.FoodMeasureType;
import br.com.healthhistoryonline.sysmodel.Pair;
import br.com.healthhistoryonline.sysmodel.SnackType;

public class MealDao {
	
	ConnectionManager conn = new ConnectionManager();

	public Pair<Boolean, String> insertMeal(Snack data, String userName){	
		try {
			PreparedStatement snack = conn.getConnection().prepareStatement("INSERT INTO T_REFEICAO"
					+ "(cd_ref, nm_usuario, cd_tp_refeicao, dt_inclusao)"
					+ "VALUES (REFEICAO.Nextval, ?"
					+ ", ?, ?)");
		
			snack.setString(1, userName);
			snack.setInt(2, data.getTypeFood().getSnackTypeCode());
			snack.setDate(3, java.sql.Date.valueOf(data.getInclusionDate().toString()));
			
			if (conn.executeCommand(snack, false) == 1) {
				for (Meal meal : data.getMeal()) {
					
					PreparedStatement stat = conn.getConnection().prepareStatement("INSERT INTO T_REF_ALIMENTO"
							+ "(cd_relacao, cd_ref, cd_medida, qtd_alimento, nr_caloria, cd_alimento)"
							+ "VALUES (REF_ALIMENTO.Nextval, (SELECT MAX(cd_ref) FROM T_REFEICAO),"
							+ "?, ?, ?, ?)");
					
					stat.setInt(1, meal.getMeasure().getMeasureCode());
					stat.setInt(2, meal.getQuantity());
					stat.setInt(3, meal.getCalories());
					stat.setInt(4, meal.getFood().getFoodCode());
					
					if (conn.executeCommand(snack, false) != 1) {
						return new Pair<Boolean, String>(false, "Falha ao cadastrar refeição!");
					}
				}
				
				conn.getConnection().commit();
				return new Pair<Boolean, String>(true, "Refeição cadastrada com sucesso!");
			}
			
			return new Pair<Boolean, String>(false, "Falha ao cadastrar refeição!");
		}
		catch (SQLException ex) 
		{
			ex.printStackTrace();
			return new Pair<Boolean, String>(false, "Falha ao cadastrar refeição!");
		}
		finally {
			conn.closeConnection();
		}
	}
	
	public Pair<Boolean, List<Snack>> getAll(String userName) throws ParseException{
		List<Snack> snackList = new ArrayList<Snack>();
				
		try {
			PreparedStatement getSnack = conn.getConnection().prepareStatement("SELECT R.cd_ref, TP.cd_tp_refeicao, TP.tp_refeicao "
					+ ", R.dt_inclusao FROM T_REFEICAO R INNER JOIN T_TP_REFEICAO TP ON R.cd_tp_refeicao = TP.cd_tp_refeicao "
					+ "WHERE R.nm_usuario = ?");
			
			getSnack.setString(1, userName);
						
			ResultSet response = conn.getData(getSnack);
			
			while (response.next()) {
				Snack snack = new Snack();
				snack.setSnackCode(response.getInt(1));
				snack.setTypeFood(new SnackType(response.getInt(2), response.getString(3)));
				snack.setInclusionDate(new SimpleDateFormat("dd/MM/yyyy").parse(response.getString(3)));
				
				snackList.add(snack);
			}
						
			conn.closeConnection();
						
			for (Snack currentSnack : snackList) {
				
				List<Meal> mealList = new ArrayList<Meal>();
				
				PreparedStatement getMeal = conn.getConnection().prepareStatement("SELECT A.cd_alimento, A.nm_alimento, RA.nr_caloria,"
						+ " RA.qtd_alimento, MA.cd_medida, MA.nm_medida FROM T_REF_ALIMENTO RA "
						+ "INNER JOIN T_REFEICAO R ON R.cd_ref = RA.cd_ref "
						+ "INNER JOIN T_TP_REFEICAO TP ON R.cd_tp_refeicao = TP.cd_tp_refeicao "
						+ "INNER JOIN T_ALIMENTO A ON RA.cd_alimento = A.cd_alimento "
						+ "INNER JOIN T_MEDIDA_ALIMENTO MA ON RA.cd_medida = MA.cd_medida "
						+ "WHERE R.cd_ref = ?");
				
				getMeal.setInt(1, currentSnack.getSnackCode());
				
				ResultSet mealResponse = conn.getData(getMeal);
				
				while (mealResponse.next()) {
					Meal meal = new Meal();
					
					meal.setFood(new FoodType(response.getInt(1), response.getString(2)));
					meal.setCalories(response.getInt(3));
					meal.setQuantity(response.getInt(4));
					meal.setMeasure(new FoodMeasureType(response.getInt(5), response.getString(6)));
					
					mealList.add(meal);
				}
											
				currentSnack.setMeal(mealList);
								
				conn.closeConnection();
			}
		}
		catch (SQLException ex) 
		{
			ex.printStackTrace();
			return new Pair<Boolean, List<Snack>>(false, null);
		}
		finally {
			conn.closeConnection();
		}
		
		return new Pair<Boolean, List<Snack>>(true, snackList);
	}
	
	public Set<FoodType> getAllFoodTypes(){
		Set<FoodType> foodTypes = new HashSet<FoodType>();
				
		try {
			PreparedStatement food = conn.getConnection().prepareStatement("SELECT cd_alimento, nm_alimento "
					+ "FROM T_ALIMENTO");
					
			ResultSet response = conn.getData(food);
			
			while (response.next()) {
				foodTypes.add(new FoodType(response.getInt(1), response.getString(2)));
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
		
		return foodTypes;
	}

	public Set<SnackType> getAllSnackTypes(){
		Set<SnackType> snackTypes = new HashSet<SnackType>();
				
		try {
			PreparedStatement stat = conn.getConnection().prepareStatement("SELECT cd_tp_refeicao, tp_refeicao "
					+ "FROM T_TP_REFEICAO");
					
			ResultSet response = conn.getData(stat);
			
			while (response.next()) {
				snackTypes.add(new SnackType(response.getInt(1), response.getString(2)));
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
		
		return snackTypes;
	}

	public Set<FoodMeasureType> getAllMeasureTypes(){
		Set<FoodMeasureType> measureTypes = new HashSet<FoodMeasureType>();
				
		try {
			PreparedStatement stat = conn.getConnection().prepareStatement("SELECT cd_medida, nm_medida "
					+ "FROM T_MEDIDA_ALIMENTO");
					
			ResultSet response = conn.getData(stat);
			
			while (response.next()) {
				measureTypes.add(new FoodMeasureType(response.getInt(1), response.getString(2)));
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
		
		return measureTypes;
	}

	public Pair<Boolean, String> updateMeal(Snack data){	
		try {
			PreparedStatement updateSnack = conn.getConnection().prepareStatement("UPDATE T_REFEICAO SET cd_tp_refeicao = ? WHERE cd_ref = ?");
		
			updateSnack.setInt(1, data.getTypeFood().getSnackTypeCode());
			updateSnack.setInt(2, data.getSnackCode());
						
			if (conn.executeCommand(updateSnack, false) <= 1) {
				conn.getConnection().commit();
				return new Pair<Boolean, String>(true, "Refeição atualizada com sucesso!");
			}	
			
			conn.getConnection().rollback();
			return new Pair<Boolean, String>(false, "Falha ao atualizar refeição!");
		}
		catch (SQLException ex) 
		{
			ex.printStackTrace();
			return new Pair<Boolean, String>(false, "Falha ao atualizar refeição!");
		}
		finally {
			conn.closeConnection();
		}
	}
	
	public Pair<Boolean, String> updateMeal(Meal meal){	
		try {
			PreparedStatement updateMeal = conn.getConnection().prepareStatement("UPDATE T_REF_ALIMENTO "
					+ "SET cd_medida = ? qtd_alimento = ?, nr_caloria = ?, cd_alimento = ? WHERE cd_relacao = ?");
		
			updateMeal.setInt(1, meal.getMeasure().getMeasureCode());
			updateMeal.setInt(2, meal.getQuantity());
			updateMeal.setInt(3, meal.getCalories());
			updateMeal.setInt(4, meal.getFood().getFoodCode());
			updateMeal.setInt(5, meal.getMealCode());
						
			
			if (conn.executeCommand(updateMeal, false) <= 1) {
				conn.getConnection().commit();
				return new Pair<Boolean, String>(true, "Refeição atualizada com sucesso!");
			}
			
			conn.getConnection().rollback();
			return new Pair<Boolean, String>(false, "Falha ao atualizar refeição!");
		}
		catch (SQLException ex) 
		{
			ex.printStackTrace();
			return new Pair<Boolean, String>(false, "Falha ao atualizar refeição!");
		}
		finally {
			conn.closeConnection();
		}
	}
	
	public Pair<Boolean, String> deleteMeal(Snack snack){	
		try {		
			PreparedStatement deleteMeal = conn.getConnection().prepareStatement("DELETE FROM T_REF_ALIMENTO "
					+ "WHERE cd_ref = ?");
		
			deleteMeal.setInt(1, snack.getSnackCode());
			
			if (conn.executeCommand(deleteMeal, false) == snack.getMeal().size()) {
				
				PreparedStatement deleteSnack = conn.getConnection().prepareStatement("DELETE FROM T_REFEICAO "
						+ "WHERE cd_ref = ?");
			
				deleteSnack.setInt(1, snack.getSnackCode());
				
				if (conn.executeCommand(deleteSnack, false) == 1) {
					conn.getConnection().commit();
					return new Pair<Boolean, String>(true, "Refeição removida com sucesso!");
				}
			}
			
			conn.getConnection().rollback();
			return new Pair<Boolean, String>(false, "Falha ao remover refeição!");
		}
		catch (SQLException ex) 
		{
			ex.printStackTrace();
			return new Pair<Boolean, String>(false, "Falha ao remover refeição!");
		}
		finally {
			conn.closeConnection();
		}	
	}
	
	public Pair<Boolean, String> deleteMeal(Meal meal){	
		try {
			PreparedStatement deleteMeal = conn.getConnection().prepareStatement("DELETE FROM T_REF_ALIMENTO "
					+ "WHERE cd_relacao = ?");
		
			deleteMeal.setInt(1, meal.getMealCode());
			
			if (conn.executeCommand(deleteMeal, false) <= 1) {
				conn.getConnection().commit();
				return new Pair<Boolean, String>(true, "Refeição removida com sucesso!");
			}
			
			conn.getConnection().rollback();
			return new Pair<Boolean, String>(false, "Falha ao remover refeição!");
		}
		catch (SQLException ex) 
		{
			ex.printStackTrace();
			return new Pair<Boolean, String>(false, "Falha ao remover refeição!");
		}
		finally {
			conn.closeConnection();
		}
	}
}