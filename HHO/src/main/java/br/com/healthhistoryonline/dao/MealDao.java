package br.com.healthhistoryonline.dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import br.com.healthhistoryonline.model.Meal;
import br.com.healthhistoryonline.model.Snack;

public class MealDao {
	
	ConnectionManager conn = new ConnectionManager();
	
	/**
	 * @param data
	 */
	public void insertMeal(Snack data, String userName){	
		try {
			PreparedStatement snack = conn.getConnection().prepareStatement("INSERT INTO T_REFEICAO"
					+ "(cd_ref, nm_usuario, cd_tp_refeicao, dt_inclusao)"
					+ "VALUES (REFEICAO.Nextval, ?"
					+ ", (SELECT cd_tp_refeicao FROM T_TP_REFEICAO WHERE tp_refeicao = ? AND ROWNUM < 2), ?)");
		
			snack.setString(1, userName);
			snack.setString(2, data.typeFood);
			snack.setDate(3, java.sql.Date.valueOf(data.inclusionDate));
			
			conn.executeCommand(snack);
			
			for (Meal meal : data.meal) {
				
				PreparedStatement stat = conn.getConnection().prepareStatement("INSERT INTO T_REF_ALIMENTO"
						+ "(cd_relacao, cd_ref, cd_medida, qtd_alimento, nr_caloria, cd_alimento)"
						+ "VALUES (REF_ALIMENTO.Nextval, (SELECT MAX(cd_ref) FROM T_REFEICAO),"
						+ "(SELECT cd_medida FROM T_MEDIDA_ALIMENTO WHERE nm_medida = ? AND ROWNUM < 2), ?, ?,"
						+ "(SELECT cd_alimento FROM T_ALIMENTO WHERE nm_alimento = ? AND ROWNUM < 2))");
				
				stat.setString(1, meal.measure);
				stat.setInt(2, meal.quantity);
				stat.setInt(3, meal.calories);
				stat.setString(4, meal.food);
				
				conn.executeCommand(stat);
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
	
	public List<Snack> getAll(String userName){
		List<Snack> snackList = new ArrayList<Snack>();
		Map<Integer, Snack> dic = new HashMap<Integer, Snack>();
				
		try {
			PreparedStatement getSnack = conn.getConnection().prepareStatement("SELECT R.cd_ref, TP.tp_refeicao, R.dt_inclusao "
					+ "FROM T_REFEICAO R INNER JOIN T_TP_REFEICAO TP ON R.cd_tp_refeicao = TP.cd_tp_refeicao "
					+ "WHERE R.nm_usuario = ?");
			
			getSnack.setString(1, userName);
						
			ResultSet snackResponse = conn.getData(getSnack);
			
			while (snackResponse.next()) {
				dic.put(snackResponse.getInt(1), new Snack(snackResponse.getString(2), snackResponse.getString(3)));
			}
						
			conn.closeConnection();
			
			for (Map.Entry<Integer, Snack> map : dic.entrySet()) {
				
				List<Meal> mealList = new ArrayList<Meal>();
				
				PreparedStatement getMeal = conn.getConnection().prepareStatement("SELECT A.nm_alimento, RA.nr_caloria,"
						+ " RA.qtd_alimento, MA.nm_medida FROM T_REF_ALIMENTO RA "
						+ "INNER JOIN T_REFEICAO R ON R.cd_ref = RA.cd_ref "
						+ "INNER JOIN T_TP_REFEICAO TP ON R.cd_tp_refeicao = TP.cd_tp_refeicao "
						+ "INNER JOIN T_ALIMENTO A ON RA.cd_alimento = A.cd_alimento "
						+ "INNER JOIN T_MEDIDA_ALIMENTO MA ON RA.cd_medida = MA.cd_medida "
						+ "WHERE R.cd_ref = ?");
				
				getMeal.setInt(1, map.getKey());
				
				ResultSet mealResponse = conn.getData(getMeal);
				
				while (mealResponse.next()) {
					mealList.add(new Meal(mealResponse.getString(1),
							mealResponse.getInt(2),
							mealResponse.getInt(3),
							mealResponse.getString(4)));
				}
				
				map.getValue().setMeal(mealList);
											
				snackList.add(map.getValue());
								
				conn.closeConnection();
			}
		}
		catch (SQLException ex) 
		{
			ex.printStackTrace();
		}
		finally {
			conn.closeConnection();
		}
		
		return snackList;
	}
}