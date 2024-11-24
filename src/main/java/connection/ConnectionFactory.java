package connection;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
	
	private static String dbUrl;
	private static String dbUsername;
	private static String dbPassword;
	private static String dbDriver;
	
	static {
		try (InputStream input=ConnectionFactory.class.getClassLoader().getResourceAsStream("application.properties")){
			if(input==null) {
				throw new RuntimeException("Configuração de banco indisponivel/inexistente");
			}
			Properties properties=new Properties();
			properties.load(input);
			dbUrl=properties.getProperty("db.url");
			dbUsername=properties.getProperty("db.username");
			dbPassword=properties.getProperty("db.password");
			dbDriver=properties.getProperty("db.driver");
			Class.forName(dbDriver);
		}catch(Exception e) {
			
			throw new RuntimeException("Erro ao incializar banco de dados", e);
		}
	}
	public static Connection getConnection() {
		try {
			
			return DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
		}catch(SQLException e){
			throw new RuntimeException("Erro ao conectar ao banco", e);
		}
	}
}
