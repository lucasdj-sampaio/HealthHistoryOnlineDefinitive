package br.com.healthhistoryonline.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionManager {
	
	private Connection connection;
	
	public ConnectionManager() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
	}
	
	public Connection getConnection() {
		if	(connection == null) {
			openConnection();
			setCommit();
		}
		
		return this.connection;
	}
	
	private void openConnection() {
		try {
			this.connection = DriverManager.getConnection("jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl", "RM88770", "fiap21");
		}
		catch (SQLException ex) 
		{
			ex.printStackTrace();
		}
	}
	
	private void setCommit() {
		try {
			this.connection.setAutoCommit(false);
		}
		catch (SQLException ex) 
		{
			ex.printStackTrace();
		}
	}
	
	public void closeConnection() {
		try {
			this.connection.close();
		}
		catch (SQLException ex) 
		{
			ex.printStackTrace();
		}
	}
	
	public int executeCommand(PreparedStatement stat, boolean makeCommit) {
		int response = 0;
		
		try {
			response = stat.executeUpdate();
			
			if (makeCommit) {
				connection.commit();
			}	
		}
		catch (SQLException ex) 
		{
			ex.printStackTrace();
			
			try {
				connection.rollback();
			}
			catch (SQLException ex1) 
			{
				ex1.printStackTrace();
			}
		}
				
		return response;		
	}
	
	public ResultSet getData(PreparedStatement stat) throws SQLException {
		ResultSet result = null;
		
		try {
			result = stat.executeQuery();
		}
		catch (SQLException ex) 
		{
			ex.printStackTrace();
		}
		
		connection.commit();
		return result;
	}
}