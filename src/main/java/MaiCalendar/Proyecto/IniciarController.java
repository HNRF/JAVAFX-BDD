package MaiCalendar.Proyecto;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Modelo.Conexion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class IniciarController {

	@FXML
	private Button btnIniciarSesion;

	@FXML
	private Button btnRegistrar;

	@FXML
	private TextField txtContraseñaLogin;

	@FXML
	private TextField txtEmailLogin;

	Conexion conexionBD = new Conexion();
	Connection conexion = conexionBD.conectar();

	@FXML
	void Iniciar(ActionEvent event) throws IOException {

		App.setRoot("Vista3");

		String usuario = txtEmailLogin.getText();
		String contraseña = txtContraseñaLogin.getText();

		Statement sentenciaSQL = null;
		String sql = "";
		ResultSet rs;

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			conexion = DriverManager.getConnection("jdbc:mysql://localhost/maicalendar", "root", "");

			sentenciaSQL = conexion.createStatement();
			sql = "SELECT `Email`, `Contraseña` FROM `registro_usuario` WHERE Email ='" + usuario + "' AND Contraseña='"
					+ contraseña + "'";

			rs = sentenciaSQL.executeQuery(sql);

		} catch (SQLException | ClassNotFoundException ex) {
			// TODO: handle exception
			mostrarAlertInfo(event, "BIENVENID@S");
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
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setHeaderText(null);
		alert.setTitle("MENSAJE");
		alert.setContentText(texto);
		alert.showAndWait();
	}

	@FXML
	void Registrar(ActionEvent event) throws IOException {
		App.setRoot("Vista2");
	}

}
