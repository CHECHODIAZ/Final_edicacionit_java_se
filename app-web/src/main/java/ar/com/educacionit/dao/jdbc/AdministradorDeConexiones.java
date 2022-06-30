package ar.com.educacionit.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

import ar.com.educacionit.dao.exceptions.GenericException;

public class AdministradorDeConexiones {

	public static Connection obtenerConexion() throws GenericException {
		String url = System.getenv("SPRING_DATASOURCE_URL");//"jdbc:postgresql://ec2-52-54-212-232.compute-1.amazonaws.com:5432/dd4m283uvn4d9b?serverTimezone=UTC&userSSL=false";
		String user = System.getenv("SPRING_DATASOURCE_USERNAME");//"kygpwpubecdjel";
		String password = System.getenv("SPRING_DATASOURCE_PASSWORD"); //"d884ca8f605fb98c5af23a0b071939c0e9fd60aecc549d96895e0aae362a7220";
		String diverName  = System.getenv("SPRING_DATASOURCE_DRIVER"); //"org.postgresql.Driver";
		
		try {
			//com.mysql.cj.jdbc.Driver.class.newInstance();
			Class.forName(diverName);
			Connection connection = DriverManager.getConnection(url, user, password);
			return connection;
		} catch (Exception  e) {
			throw new GenericException("Error oteniendo conexion: " + e.getMessage(),e);
		} 
	}
	
	public static void main(String[] args) {
		
		try (Connection con = AdministradorDeConexiones.obtenerConexion();) {			
			System.out.println("Conexion obtenida");
		}catch (Exception e) {			
			e.printStackTrace();
		}
	}
}
