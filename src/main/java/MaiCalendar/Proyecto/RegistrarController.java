package MaiCalendar.Proyecto;

import java.io.IOException;
import java.sql.Statement;
import java.util.IllegalFormatCodePointException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import Modelo.Conexion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class RegistrarController {

	@FXML
	private Button btnRegistrarValidar;

	@FXML
	private TextField txtApellido;

	@FXML
	private TextField txtContraseñaLogin;

	@FXML
	private TextField txtEmailLogin;

	@FXML
	private TextField txtNombre;

	Conexion conexcionBD = new Conexion();
	Connection conexion = conexcionBD.conectar();

	@FXML
	void registrar(ActionEvent event) throws IOException {

		App.setRoot("Vista1");

		String sql = "";
		Statement sentenciaSQL = null;
		int resultado = 0;
		String nombre;
		String apellidos;
		String email;
		String contraseña;

		nombre = txtNombre.getText();
		apellidos = txtApellido.getText();
		email = txtEmailLogin.getText();
		contraseña = txtContraseñaLogin.getText();

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			conexion = DriverManager.getConnection("jdbc:mysql://localhost/maicalendar", "root", "");

			sentenciaSQL = conexion.createStatement();
			sql = "insert into registro_usuario (id_usuario,Nombre, Apellidos, Email, Contraseña) VALUES (0,'" + nombre
					+ "','" + apellidos + "','" + email + "','" + contraseña + "')";

			resultado = sentenciaSQL.executeUpdate(sql);

			if (resultado >= 1) {
				mostrarAlertInfo(event, "REGISTRO COMPLETO");
			}

		} catch (SQLException | ClassNotFoundException ex) {
			// TODO: handle exception
			mostrarAlertInfo(event, "ERROR AL GUARDAR DATOS");
			ex.printStackTrace();

		} finally {
			try {
				if (sentenciaSQL != null) {
					sentenciaSQL.close();

				}
				if (conexion != null) {
					conexion.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	private void mostrarAlertInfo(ActionEvent event, String texto) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setHeaderText(null);
		alert.setTitle("ALERTA!!");
		alert.setContentText(texto);
		alert.showAndWait();
	}

}
