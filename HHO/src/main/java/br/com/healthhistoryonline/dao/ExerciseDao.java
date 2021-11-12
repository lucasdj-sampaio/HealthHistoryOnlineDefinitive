package br.com.healthhistoryonline.dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;
import br.com.healthhistoryonline.model.Exercise;
import br.com.healthhistoryonline.sysmodel.ExerciseType;
import br.com.healthhistoryonline.sysmodel.Pair;

public class ExerciseDao {
	
	ConnectionManager conn = new ConnectionManager();
	
	/**
	 * @param data
	 */
	public Pair<Boolean, String> insertExercise(Exercise exerciseDetails, String userName){	
		try {
			PreparedStatement exercise = conn.getConnection().prepareStatement("INSERT INTO T_EXERCICIO"
					+ "(CD_ATIVIDADE, NM_USUARIO, NR_KCAL_GASTO, NR_BPM, DT_INCLUSAO, CD_EXERCICIO)"
					+ "VALUES (EXERCICIO.Nextval, ?, ?, ?, ?, ?)");
			
			exercise.setString(1, userName);
			exercise.setInt(2, exerciseDetails.getSpentCalories());
			exercise.setInt(3, exerciseDetails.getBpm());
			exercise.setDate(4, java.sql.Date.valueOf(exerciseDetails.getInclusionDate().toString()));
			exercise.setInt(5, exerciseDetails.getType().getExerciseCode());
			
			if (conn.executeCommand(exercise, true) == 1) {
				return new Pair<Boolean, String>(true, "Exercício incluso com sucesso!");
			}		
			
			return new Pair<Boolean, String>(false, "Erro ao cadastrar exercício");
		}
		catch (SQLException ex) 
		{
			ex.printStackTrace();
			return new Pair<Boolean, String>(false, "Erro ao cadastrar exercício");
		}
		finally {
			conn.closeConnection();
		}
	}
	
	public Pair<Boolean, String> updateExercise(Exercise exerciseDetails, String userName){	
		try {
			PreparedStatement exercise = conn.getConnection().prepareStatement("UPDATE T_EXERCICIO"
					+ "(SET NR_KCAL_GASTO = ?, NR_BPM = ?, DT_INCLUSAO = ?, CD_EXERCICIO = ? WHERE CD_ATIVIDADE = ?)");
			
			exercise.setInt(1, exerciseDetails.getSpentCalories());
			exercise.setInt(2, exerciseDetails.getBpm());
			exercise.setDate(3, java.sql.Date.valueOf(exerciseDetails.getInclusionDate().toString()));
			exercise.setInt(4, exerciseDetails.getType().getExerciseCode());
			exercise.setInt(5, exerciseDetails.getActivityId());
			
			if (conn.executeCommand(exercise, false) == 1) {
				conn.getConnection().commit();
				
				return new Pair<Boolean, String>(true, "Alteração de exercício concluída");
			}
			
			return new Pair<Boolean, String>(false, "Erro ao alterar exercicio");
		}
		catch (SQLException ex) 
		{
			ex.printStackTrace();
			return new Pair<Boolean, String>(false, "Erro ao alterar exercicio");
		}
		finally {
			conn.closeConnection();
		}
	}
	
	//Get all from specify user
	public Set<Exercise> getAll(String userName) throws ParseException{
		Set<Exercise> listValues = new HashSet<Exercise>();
		
		try {
			PreparedStatement stat = conn.getConnection().prepareStatement("SELECT E.NR_KCAL_GASTO, E.nr_bpm, E.DT_INCLUSAO,"
					+ " TE.TP_EXERCICIO, CD_ATIVIDADE FROM T_EXERCICIO E INNER JOIN T_TP_EXERCICIO TE "
					+ "ON E.CD_EXERCICIO = TE.CD_EXERCICIO WHERE NM_USUARIO = ?");
			
			stat.setString(1, userName);
			
			ResultSet response = conn.getData(stat);
			
			while (response.next()) {
				ExerciseType type = new ExerciseType();
				type.setExercise(response.getString(4));
				
				Exercise exercise = new Exercise(response.getInt(1)
						, response.getInt(2), new SimpleDateFormat("dd/MM/yyyy").parse(response.getString(3)));
				
				exercise.setActivityId(response.getInt(5));
				exercise.setType(type);
				
				listValues.add(exercise);
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
	
	public Set<ExerciseType> getAll(){
		Set<ExerciseType> listValues = new HashSet<ExerciseType>();
		
		try {
			PreparedStatement stat = conn.getConnection().prepareStatement("SELECT CD_EXERCICIO, TP_EXERCICIO "
					+ "FROM T_TP_EXERCICIO");
					
			ResultSet response = conn.getData(stat);
			
			while (response.next()) {
				ExerciseType type = new ExerciseType();
				type.setExerciseCode(response.getInt(1));
				type.setExercise(response.getString(2));
				
				listValues.add(type);
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