package br.com.healthhistoryonline.dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import br.com.healthhistoryonline.model.Exercise;


public class ExerciseDao {
	
	ConnectionManager conn = new ConnectionManager();
	
	/**
	 * @param data
	 */
	public void insertExercise(Exercise exerciseDetails){	
		try {
			PreparedStatement exercise = conn.getConnection().prepareStatement("INSERT INTO T_EXERCICIO"
					+ "(CD_ATIVIDADE, NM_USUARIO, NR_KCAL_GASTO, NR_BPM, DT_INCLUSAO, CD_EXERCICIO)"
					+ "VALUES (EXERCICIO.Nextval, ?, ?, ?, ?, (SELECT CD_EXERCICIO FROM T_TP_EXERCICIO WHERE tp_exercicio = ? AND ROWNUM < 2))");
			
			exercise.setString(1, "giovanaagudo");
			exercise.setInt(2, exerciseDetails.spentCalories);
			exercise.setInt(3, exerciseDetails.bpm);
			exercise.setDate(4, java.sql.Date.valueOf(exerciseDetails.inclusionDate));
			exercise.setString(5, exerciseDetails.typeExercise);
			
			conn.executeCommand(exercise);
			
		}
		catch (SQLException ex) 
		{
			ex.printStackTrace();
		}
		finally {
			conn.closeConnection();
		}
	}
	
	public Set<Exercise> getAll(String userName){
		Set<Exercise> listValues = new HashSet<Exercise>();
		
		try {
			PreparedStatement stat = conn.getConnection().prepareStatement("SELECT E.NR_KCAL_GASTO, E.nr_bpm, E.DT_INCLUSAO,"
					+ " TE.TP_EXERCICIO FROM T_EXERCICIO E INNER JOIN T_TP_EXERCICIO TE "
					+ "ON E.CD_EXERCICIO = TE.CD_EXERCICIO WHERE NM_USUARIO = ?");
			
			stat.setString(1, userName);
			
			ResultSet response = conn.getData(stat);
			
			while (response.next()) {
				listValues.add(new Exercise(response.getInt(1), response.getInt(2), response.getString(3), response.getString(4)));
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