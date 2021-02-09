package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	public static Connection faz_conexao() throws SQLException {
		
		try {
		
			Class.forName("org.postgresql.Driver");
			
			return DriverManager.getConnection("jdbc:postgresql://localhost:5432/ImpostoRenda", "postgres", "R@mb01997");
			
		
		}catch(ClassNotFoundException e){
			
			throw new SQLException(e.getException());
			
		}
	}
}
