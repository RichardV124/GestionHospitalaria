package co.edu.eam.ingesoft.avanzada.persistencia.utilidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AdministradorConexionJDBC {

	public static Connection getConexion() throws SQLException{
		//1.2 conectarse
		//del paquete java.sql
		System.out.println("Conectandose.....");
		//?autoReconnect=true&useSSL=false
	String url= "jdbc:oracle:thin:@localhost:1521:XE";
	String user="triviniers";
	String pass="triviniers";
	
	Connection con= DriverManager.getConnection(url,user,pass);
	return con;
	}
	
}
