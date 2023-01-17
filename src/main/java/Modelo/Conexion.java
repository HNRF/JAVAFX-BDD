package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;

public class Conexion {

	private Connection conexion = null;
	private Statement sentenciaSQL = null;

	public Connection conectar() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/maicalendar", "root", "");
			sentenciaSQL = conexion.createStatement();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.print("Error de conexion");
			e.printStackTrace();
		}
		return conexion;

	}

	public void desconectar() {

		try {
			if (sentenciaSQL != null) {// se cierra en caso de establecerse
				sentenciaSQL.close();
			}
			if (conexion != null) {// se cierra en caso de establecerse
				conexion.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	

}
