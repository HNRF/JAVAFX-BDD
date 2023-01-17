package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Calendario {

	// Variables
	private int id;
	private String metas;
	private String prioridad;
	private String inicioActividad;
	private String finActividad;

	// Constructor

	

	public Calendario(int id, String metas, String prioridad, String inicioActividad, String finActividad) {
		super();
		this.id = id;
		this.metas = metas;
		this.prioridad = prioridad;
		this.inicioActividad = inicioActividad;
		this.finActividad = finActividad;
	}

	public Calendario() {
		super();
	}

	// Getters & Setters

	public String getMetas() {
		return metas;
	}

	public void setMetas(String metas) {
		this.metas = metas;
	}

	public String getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(String prioridad) {
		this.prioridad = prioridad;
	}

	public String getInicioActividad() {
		return inicioActividad;
	}

	public void setInicioActividad(String inicioActividad) {
		this.inicioActividad = inicioActividad;
	}

	public String getFinActividad() {
		return finActividad;
	}

	public void setFinActividad(String finActividad) {
		this.finActividad = finActividad;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
		
	public static void llenar(Connection connection, ObservableList<Calendario> calendario) {

		Statement sentenciaSQL = null;
		String sql = "";
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/maicalendar", "root", "");
			sentenciaSQL = connection.createStatement();
			sql = "SELECT * FROM datos_calendario";
			ResultSet resultado = sentenciaSQL.executeQuery(sql);
			while (resultado.next()) {
				calendario.add(new Calendario(resultado.getInt("id"), resultado.getString("Metas"),
						resultado.getString("Prioridad"), resultado.getString("Inicio"), resultado.getString("Fin")));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void borrarRegistro(Calendario calendario) throws SQLException {

		Connection conexion = null;
		conexion = DriverManager.getConnection("jdbc:mysql://localhost/maicalendar", "root", "");


		try {
			
			
			PreparedStatement datoSta = conexion.prepareStatement("DELETE FROM datos_calendario Where id=?");
			datoSta.setInt(1, calendario.getId());
			datoSta.executeUpdate();
		} catch (Exception e) {

		}
	}

	public void registro(String Metas, String Prioridad, String Inicio, String Fin) {

		Connection conexion = null;
		Statement sentenciaSQL = null;
		ResultSet rs;
		int resultado = 0;
		String sql = "";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/maicalendar", "root", "");
			sentenciaSQL = conexion.createStatement();
			sql = "insert into datos_calendario (Metas,Prioridad,Inicio,Fin) values ('" + Metas + "','" + Prioridad
					+ "','" + Inicio + "','" + Fin + "')";
			resultado = sentenciaSQL.executeUpdate(sql);
			if (resultado >= 1) {
				System.out.println("Se ha insertado bien.");
			}
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			// System.out.println("Error");
		} finally {
			try {
				if (sentenciaSQL != null) {
					sentenciaSQL.close();
				}
				if (conexion != null) {
					conexion.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	public void actualizarRegistro(Calendario calendario) throws SQLException {
		Connection conexion = null;
		
		try {
			System.out.println("hola");
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/maicalendar", "root", "");
			PreparedStatement actualizar = conexion.prepareStatement("UPDATE datos_calendario SET id=?, Metas=?,Prioridad=?,Inicio=?,Fin=? WHERE id=?");
			actualizar.setInt(1, getId());
			actualizar.setString(2, getMetas());
			actualizar.setString(3, getPrioridad());
			actualizar.setString(4, getInicioActividad());
			actualizar.setString(5, getFinActividad());
			actualizar.setInt(6, getId());
			actualizar.executeUpdate();
		} catch (Exception e) {
			
		}
		
	}
}




