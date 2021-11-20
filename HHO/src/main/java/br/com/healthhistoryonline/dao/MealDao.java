package br.com.healthhistoryonline.dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import br.com.healthhistoryonline.model.Meal;
import br.com.healthhistoryonline.model.Snack;
import br.com.healthhistoryonline.sysmodel.Pair;
import br.com.healthhistoryonline.sysmodel.SnackType;

public class MealDao {
	
	public Pair<Boolean, String> insertMeal(Snack data, String userName){	
		ConnectionManager conn = new ConnectionManager();
		try {
			PreparedStatement snack = conn.getConnection().prepareStatement("INSERT INTO T_REFEICAO"
					+ "(cd_ref, nm_usuario, cd_tp_refeicao, dt_inclusao, nr_caloria)"
					+ "VALUES (REFEICAO.Nextval, ?"
					+ ", ?, ?, ?)");
		
			snack.setString(1, userName);
			snack.setInt(2, data.getTypeFood().getSnackTypeCode());
			snack.setDate(3, java.sql.Date.valueOf(data.getInclusionDate().toString()));
			snack.setInt(4, data.getCalories());
			
			if (conn.executeCommand(snack, false) == 1) {
				for (Meal meal : data.getMeal()) {
					
					PreparedStatement stat = conn.getConnection().prepareStatement("INSERT INTO T_REF_ALIMENTO"
							+ "(cd_relacao, cd_ref, nm_alimento)"
							+ "VALUES (REF_ALIMENTO.Nextval, (SELECT MAX(cd_ref) FROM T_REFEICAO), ?)");
					
					stat.setString(1, meal.getMealName());
					
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
	
	public Pair<Boolean, Map<Date, List<Snack>>> getAll(String userName) throws ParseException{
		Map<Date, List<Snack>> map = new HashMap<Date, List<Snack>>();
		
		ConnectionManager conn = new ConnectionManager();
		try {
			PreparedStatement getSnack = conn.getConnection().prepareStatement("SELECT R.cd_ref, TP.cd_tp_refeicao, TP.tp_refeicao "
					+ ", R.nr_caloria, R.dt_inclusao FROM T_REFEICAO R INNER JOIN T_TP_REFEICAO TP ON R.cd_tp_refeicao = TP.cd_tp_refeicao "
					+ "WHERE R.nm_usuario = ? ORDER BY R.dt_inclusao ASC");
			
			getSnack.setString(1, userName);
						
			ResultSet response = conn.getData(getSnack);
			
			while (response.next()) {
				SnackType type = new SnackType(response.getInt(2));
				type.setSnack(response.getString(3));
								
				Snack snack = new Snack();
				snack.setSnackCode(response.getInt(1));
				snack.setTypeFood(type);
				snack.setCalories(response.getInt(4));
				
				snack.setMeal(getRefFood(conn, snack.getSnackCode()));
				
				Date currentDate = new SimpleDateFormat("dd/MM/yyyy").parse(response.getString(5));
				
				if (map.containsKey(currentDate)){
					map.get(currentDate).add(snack);
				}
				else {
					List<Snack> list = new ArrayList<Snack>();
					list.add(snack);
					
					map.put(currentDate, list);
				}
			}
						
			conn.closeConnection();
		}
		catch (SQLException ex) 
		{
			ex.printStackTrace();
			return new Pair<Boolean, Map<Date, List<Snack>>>(false, null);
		}
		finally {
			conn.closeConnection();
		}
		
		return new Pair<Boolean, Map<Date, List<Snack>>>(true, map);
	}
	
	private List<Meal> getRefFood(ConnectionManager conn, int refCode){
		List<Meal> mealList = new ArrayList<Meal>();
		
		try {
			
			PreparedStatement getMeal = conn.getConnection().prepareStatement("SELECT cd_relacao, nm_alimento "
					+ "WHERE R.cd_ref = ?");
			
			getMeal.setInt(1, refCode);
			
			ResultSet mealResponse = conn.getData(getMeal);
			
			while (mealResponse.next()) {
				Meal meal = new Meal(mealResponse.getString(2));
				meal.setMealCode(mealResponse.getInt(1));
				
				mealList.add(meal);
			}
		}		
		catch (SQLException ex) 
		{
			ex.printStackTrace();
		}
		
		return mealList;
	}

	public Set<SnackType> getAllSnackTypes(){
		Set<SnackType> snackTypes = new HashSet<SnackType>();
		
		ConnectionManager conn = new ConnectionManager();
		try {
			PreparedStatement stat = conn.getConnection().prepareStatement("SELECT cd_tp_refeicao, tp_refeicao "
					+ "FROM T_TP_REFEICAO");
					
			ResultSet response = conn.getData(stat);
			
			while (response.next()) {
				SnackType type = new SnackType(response.getInt(1));
				type.setSnack(response.getString(2));
				
				snackTypes.add(type);
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

	public Pair<Boolean, String> updateMeal(Meal meal){	
		ConnectionManager conn = new ConnectionManager();
		try {
			PreparedStatement updateMeal = conn.getConnection().prepareStatement("UPDATE T_REF_ALIMENTO "
					+ "SET nm_alimento = ? WHERE cd_relacao = ?");
		
			updateMeal.setString(1, meal.getMealName());
			updateMeal.setInt(2, meal.getMealCode());
						
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
		ConnectionManager conn = new ConnectionManager();
		try {		
			PreparedStatement deleteMeal = conn.getConnection().prepareStatement("DELETE FROM T_REF_ALIMENTO "
					+ "WHERE cd_ref = ?");
		
			deleteMeal.setInt(1, snack.getSnackCode());
			
			if (conn.executeCommand(deleteMeal, false) > 0) {
				
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
	
	public Pair<Boolean, String> deleteMeal(int mealCode){	
		ConnectionManager conn = new ConnectionManager();
		try {
			PreparedStatement deleteMeal = conn.getConnection().prepareStatement("DELETE FROM T_REF_ALIMENTO "
					+ "WHERE cd_relacao = ?");
		
			deleteMeal.setInt(1, mealCode);
			
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