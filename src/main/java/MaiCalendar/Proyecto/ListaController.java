package MaiCalendar.Proyecto;


import java.sql.SQLException;



import Modelo.Calendario;
import Modelo.Conexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class ListaController {

	@FXML
	private Button btnDelete;

	@FXML
	private Button btnExport;

	@FXML
	private Button btnVistaCreate;

	@FXML
	private Button btnVistaUpdate;

	@FXML
	private TableColumn<Calendario, String> clFinActividad;

	@FXML
	private TableColumn<Calendario, String> clPrioridad;

	@FXML
	private TableColumn<Calendario, String> clInicioActividad;

	@FXML
	private TableColumn<Calendario, String> clMetas;

	@FXML
	private TableColumn<Calendario, String> clId;

	@FXML
	private TableView<Calendario> tblDatos;
	@FXML
	private TextField txtId;

	@FXML
	private TextField txtAño;

	@FXML
	private TextField txtFinFecha;

	@FXML
	private TextField txtPrioridad;

	@FXML
	private TextField txtIniciofecha;

	@FXML
	private TextField txtMes;

	@FXML
	private TextField txtmetas;

	// ObservableList para ir añadiendo

	private ObservableList<Calendario> calendario;

	// asigno el objecto con su columna correspondinete
	public void initialize() {
		txtId.setVisible(false);

		cargarLista();

	}

	private void cargarLista() {
		tblDatos.setEditable(true);

		Conexion cn = new Conexion();

		calendario = FXCollections.observableArrayList();
		Calendario.llenar(cn.conectar(), calendario);
		tblDatos.setItems(calendario);

		clId.setCellValueFactory(new PropertyValueFactory<Calendario, String>("id"));
		clMetas.setCellValueFactory(new PropertyValueFactory<Calendario, String>("metas"));
		clPrioridad.setCellValueFactory(new PropertyValueFactory<Calendario, String>("prioridad"));
		clInicioActividad.setCellValueFactory(new PropertyValueFactory<Calendario, String>("inicioActividad"));
		clFinActividad.setCellValueFactory(new PropertyValueFactory<Calendario, String>("finActividad"));

		tblDatos.refresh();
	}

	@FXML
	void CrearDatos(ActionEvent event) {

		Calendario guardar = new Calendario();

		guardar.registro(txtmetas.getText(), txtPrioridad.getText(), txtIniciofecha.getText(), txtFinFecha.getText());

		cargarLista();
	}

	@FXML
	void ExportarLista(ActionEvent event) {

	}

	@FXML
	void ActualizarDatos(ActionEvent event){
		
		Calendario borrar = new Calendario(Integer.valueOf(txtId.getText()), txtmetas.getText(),txtPrioridad.getText(),
				txtIniciofecha.getText(), txtFinFecha.getText());
		try {
			System.out.println("actualizado");
			borrar.actualizarRegistro(borrar);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cargarLista();

	}

	@FXML
	void Seleccionar(MouseEvent event) {

		Calendario selectItems = tblDatos.getSelectionModel().getSelectedItem();

		if (selectItems != null) {
			txtId.setText(String.valueOf(selectItems.getId()));
			txtmetas.setText(selectItems.getMetas());
			txtPrioridad.setText(selectItems.getPrioridad());
			txtIniciofecha.setText(selectItems.getInicioActividad());
			txtFinFecha.setText(selectItems.getFinActividad());

		}

	}

	@FXML
	void deleteDatos(ActionEvent event) {
		Calendario borrar = new Calendario(Integer.parseInt(txtId.getText()), txtmetas.getText(),txtPrioridad.getText(),
				txtIniciofecha.getText(), txtFinFecha.getText());
		try {
			borrar.borrarRegistro(borrar);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		cargarLista();

	}

}
