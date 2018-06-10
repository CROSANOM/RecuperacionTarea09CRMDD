/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alquilerVehiculos.mvc.vista.iugraficatablas.controladores.vista;

import alquilerVehiculos.mvc.modelo.dominio.Alquiler;
import alquilerVehiculos.mvc.modelo.dominio.Cliente;
import alquilerVehiculos.mvc.modelo.dominio.DireccionPostal;
import alquilerVehiculos.mvc.modelo.dominio.ExcepcionAlquilerVehiculos;
import alquilerVehiculos.mvc.modelo.dominio.vehiculo.DatosTecnicosVehiculo;
import alquilerVehiculos.mvc.modelo.dominio.vehiculo.TipoVehiculo;
import alquilerVehiculos.mvc.modelo.dominio.vehiculo.Vehiculo;
import alquilerVehiculos.mvc.vista.iugraficatablas.IUGTablas;
import alquilerVehiculos.mvc.vista.iugraficatablas.utilidades.Dialogos;

import java.awt.font.ImageGraphicAttribute;
import java.io.IOException;
import java.util.Date;

import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @author crosanom
 *
 */
/**
 * @author crosanom
 *
 */
public class ControladorPrincipal {

	// ATRIBUTOS
	private ObservableList<Cliente> clientes;
	private ObservableList<Vehiculo> vehiculos;
	private ObservableList<Alquiler> alquileres;

	// controles + controladores de pane CLIENTES

	@FXML
	private Button btAceptarCliente, btCancelarCliente, btEliminarC;
	@FXML
	private TableView<Cliente> tvClientes;
	@FXML
	private TableColumn<Cliente, String> tcNombre, tcDni, tcLocalidad, tcDireccion, tcCodigoPostal;
	@FXML
	private TextField tfNombre, tfDni, tfCodigoPostal, tfLocalidad, tfDireccion, tfInsertarDni;
	// @FXML
	// private TextArea taDireccion;
	@FXML
	private RadioButton rbAnadirCliente, rbEliminarCliente;
	@FXML // para activar los campos inactivados ficha
	private GridPane gpFichaCliente;
	@FXML
	private VBox vbFichaEliminarC;

	// controladores + controles de pane VEHICULOS

	@FXML
	private TableView<Vehiculo> tvVehiculo;
	@FXML
	private TableColumn<Vehiculo, String> tcMatricula, tcMarca, tcModelo;
	@FXML
	private TableColumn<Vehiculo, Number> tcCilindrada, tcNumPlazas, tcPMA;
	@FXML
	// private TableColumn<Vehiculo, TipoVehiculo> tcTipoVehiculo;
	// @FXML
	private CheckBox cbAnadirVe, cbBuscarVe, cbEliminarVe;
	@FXML
	private GridPane gpFichaVehiculo;
	@FXML
	private TextField tfMatricula, tfMarca, tfModelo, tfCilindrada, tfNumeroPlazas, tfPMA, tfInsertarMatricula;
	@FXML
	private Button btAceptarAnadirVe, btCancelarAnadirVe, btAceptarOpVe, btCancelarOpVe;
	@FXML // Agrupado botones del ToggleGroup
	private HBox hbTipoVehiculo;
	@FXML
	private ToggleGroup tgTipoVe;
	@FXML
	private RadioButton rbTurismo, rbDeCarga, rbAutobus;
	@FXML
	private VBox vbCampoEliminarVe;

	// controles + contenedores de Pane ALQUILER

	@FXML
	private RadioButton rbGestionAlquiler, rbListadoAlquiler;
	@FXML
	private Button btAbrir, btCerrar;
	@FXML
	private GridPane gpFichaAlquiler;
	@FXML
	private TextField tfMatriculaVeA, tfDniClienteA;
	@FXML
	private CheckBox cbAlquiler, cbAlquilerAbierto;
	@FXML
	private HBox hbGestionAlquiler;
	@FXML
	private TableView<Alquiler> tvAlquiler;
	@FXML
	private TableColumn<Alquiler, String> tcClientesA;
	@FXML
	private TableColumn<Alquiler, String> tcVehiculo;
	// @FXML
	// private TableColumn<Alquiler, Integer> tcDias;
	// @FXML
	// private TableColumn<Alquiler, Date> tcFecha;

	// :::METODO INITIALIZE (ejecuta al cargar la aplicacion ) :::::::./

	@FXML
	private void initialize() throws IOException {

		crearTablaVehiculos();
		crearTablaClientes();
		crearTablaAlquileres();
		generarGroupTipoVe();

	}

	// :::::::::::::: METODOS PARA CLIENTES :::::::::::::::/

	private void crearTablaClientes() throws IOException {

		tcNombre.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nombre"));
		tcNombre.setResizable(false);
		tcDni.setCellValueFactory(new PropertyValueFactory<Cliente, String>("dni"));
		tcDni.setResizable(false);

		tcDireccion.setCellValueFactory(
				cliente -> new ReadOnlyStringWrapper(cliente.getValue().getDireccionPostal().getCalle()));
		tcLocalidad.setCellValueFactory(
				cliente -> new ReadOnlyStringWrapper(cliente.getValue().getDireccionPostal().getLocalidad()));
		tcCodigoPostal.setCellValueFactory(
				cliente -> new ReadOnlyStringWrapper(cliente.getValue().getDireccionPostal().getCodigoPostal()));

		clientes = FXCollections.observableArrayList(IUGTablas.controladorMVC.obtenerClientes());
		tvClientes.setItems(clientes);
		tvClientes.getSelectionModel().selectedItemProperty()
				.addListener((ov, oldValue, newValue) -> metodoactualizar(ov.getValue()));
		limpiarCampos();
		System.out.println("DESPUES DE CARGAR LIMPIA CAMPOS");
	}

	private Object metodoactualizar(Cliente value) {
		System.out.println("El codigo pasa por aqui");
		return null;

	}

	// ANADE CLIENTE ( validaCampos) (limpiaCampos y desactivaFicha)
	@FXML
	private void anadirCliente() {
		try {
			validarcamposCliente();
			DireccionPostal direccionPostal = new DireccionPostal(tfDireccion.getText(), tfLocalidad.getText(),
					tfCodigoPostal.getText());
			Cliente cliente = new Cliente(tfNombre.getText(), tfDni.getText(), direccionPostal);// crearCliente
			IUGTablas.controladorMVC.anadirCliente(cliente);// anadirClienteCreado
			Dialogos.mostrarDialogoAdvertencia("Aviso", "Operacion realizada");
			limpiarCampos();
			tvClientes.setItems(FXCollections.observableArrayList(IUGTablas.controladorMVC.obtenerClientes()));
		} catch (ExcepcionAlquilerVehiculos e) {
			Dialogos.mostrarDialogoError("Error", e.getMessage());

		}

		gpFichaCliente.setDisable(true);
		limpiarCampos();

	}

	// metodo para validarCamposCliente
	private void validarcamposCliente() {
		if (tfNombre.getText().isEmpty() || tfDni.getText().isEmpty() || tfDireccion.getText().isEmpty()
				|| tfLocalidad.getText().isEmpty() || tfCodigoPostal.getText().isEmpty())

			Dialogos.mostrarDialogoAdvertencia("Aviso", "Debe rellenar todos los campos");

	}

	// Deja visible la ficha de cliente

	@FXML
	private void activarFichaCliente() {
		gpFichaCliente.setDisable(false);
		rbAnadirCliente.setDisable(true);

	}

	// ELIMINAR CLIENTE

	@FXML
	private void eliminarCliente() throws IOException {

		if (Dialogos.mostrarDialogoConfirmacion("Aviso",
				"Vas a ELIMINAR un  CLIENTE, ¿estas seguro de creer CONTINUAR ?")) {
			try {
				tfInsertarDni.getText();

				IUGTablas.controladorMVC.borrarCliente(tfInsertarDni.getText());
				clientes.remove(clientes); // metodos de observable
				Dialogos.mostrarDialogoInformacion("Eliminar CLIENTE ", "CLIENTE  eliminado correctamente");
				crearTablaClientes();

			} catch (ExcepcionAlquilerVehiculos e) {
				Dialogos.mostrarDialogoError("Error", e.getMessage());
			}

			// limpiarCampos();

			vbFichaEliminarC.setDisable(true);
			tfInsertarDni.setText("");
			rbEliminarCliente.setDisable(false);
			rbEliminarCliente.isSelected();

		}

		// Activar ficha InsertarDni
	}

	@FXML
	private void activarInsertarDni() {
		vbFichaEliminarC.setDisable(false);
		rbEliminarCliente.setDisable(true);
		tfInsertarDni.setText("");

	}

	@FXML
	private void limpiarCampos() {
		tfNombre.setText("");
		tfDni.setText("");
		tfDireccion.setText("");
		tfCodigoPostal.setText("");
		tfLocalidad.setText("");
		gpFichaCliente.setDisable(true);
		rbAnadirCliente.setDisable(false);
		rbAnadirCliente.setSelected(false);
		rbAnadirCliente.requestFocus();
		rbEliminarCliente.setDisable(false);
		rbEliminarCliente.setSelected(false);
		rbEliminarCliente.requestFocus();

	}

	// :::::::::::::::::::.METODOS PARA VEHICULOS ::::::::::::::::::::::::.

	// genera ToogleGroup

	private void generarGroupTipoVe() {

		tgTipoVe = new ToggleGroup();
		rbAutobus.setToggleGroup(tgTipoVe);
		rbDeCarga.setToggleGroup(tgTipoVe);
		rbTurismo.setToggleGroup(tgTipoVe);

	}

	// Crear TablaVehiculos

	@FXML
	private void crearTablaVehiculos() throws IOException {

		tcMatricula.setCellValueFactory(new PropertyValueFactory<Vehiculo, String>("Matricula"));
		tcMatricula.setResizable(false);
		tcMarca.setCellValueFactory(new PropertyValueFactory<Vehiculo, String>("Marca"));
		tcMarca.setResizable(false);
		tcModelo.setCellValueFactory(new PropertyValueFactory<Vehiculo, String>("Modelo"));
		tcModelo.setResizable(false);

		tcCilindrada.setCellValueFactory(
				vehiculo -> new ReadOnlyIntegerWrapper(vehiculo.getValue().getDatosTecnicosVehiculo().getCilindrada()));
		tcNumPlazas.setCellValueFactory(vehiculo -> new ReadOnlyIntegerWrapper(
				vehiculo.getValue().getDatosTecnicosVehiculo().getNumerosPlazas()));
		tcPMA.setCellValueFactory(
				vehiculo -> new ReadOnlyIntegerWrapper(vehiculo.getValue().getDatosTecnicosVehiculo().getPma()));

		vehiculos = FXCollections.observableArrayList(IUGTablas.controladorMVC.obtenerVehiculos());
		tvVehiculo.setItems(vehiculos);
		tvVehiculo.getSelectionModel().selectedItemProperty()
				.addListener((ov, oldValue, newValue) -> accionesVehiculos(ov.getValue()));

	}

	private Object accionesVehiculos(Vehiculo value) {
		// TODO Auto-generated method stub
		return null;
	}

	// activa Vehiculo
	@FXML
	private void activarTipoVehiculo() {
		hbTipoVehiculo.setDisable(false);
		Dialogos.mostrarDialogoAdvertencia("Operacion Incompleta", "Seleccione un tipo Vehiculo");
		cbAnadirVe.setDisable(true);
		gpFichaVehiculo.setDisable(false);

	}

	// ANADE VEHICULO ( validaCampos )

	@FXML
	private void anadirVehiculo() {

		Vehiculo vehiculo;

		validarCamposVe();

		if (!rbAutobus.isSelected() && !rbTurismo.isSelected() && !rbDeCarga.isSelected()) {
			Dialogos.mostrarDialogoAdvertencia("Aviso", "Debe seleccionar el tipo de vehiculo");
		} else {
			try {
				DatosTecnicosVehiculo datosTecnicos = new DatosTecnicosVehiculo(
						Integer.parseInt(tfCilindrada.getText()), Integer.parseInt(tfNumeroPlazas.getText()),
						Integer.parseInt(tfPMA.getText()));
				if (rbTurismo.isSelected()) {
					vehiculo = TipoVehiculo.TURISMO.getInstancia(tfMatricula.getText(), tfMarca.getText(),
							tfModelo.getText(), datosTecnicos);
				} else if (rbAutobus.isSelected()) {
					vehiculo = TipoVehiculo.AUTOBUS.getInstancia(tfMatricula.getText(), tfMarca.getText(),
							tfModelo.getText(), datosTecnicos);
				} else {
					vehiculo = TipoVehiculo.DECARGA.getInstancia(tfMatricula.getText(), tfMarca.getText(),
							tfModelo.getText(), datosTecnicos);
				}
				IUGTablas.controladorMVC.anadirVehiculo(vehiculo, null);
				tvVehiculo.setItems(FXCollections.observableArrayList(IUGTablas.controladorMVC.obtenerVehiculos()));
				// deshabilitarLimpiarAnadirVehiculo();
				Dialogos.mostrarDialogoInformacion("Información", "Operación realizada");

			} catch (ExcepcionAlquilerVehiculos e) {
				Dialogos.mostrarDialogoError("Error", e.getMessage());
			}
		}
		limpiarCamposVe();
	}

	// validar Campos de Vehiculos
	private void validarCamposVe() {
		if (tfMarca.getText().isEmpty() || tfModelo.getText().isEmpty() || tfMatricula.getText().isEmpty()
				|| tfCilindrada.getText().isEmpty() || tfNumeroPlazas.getText().isEmpty() || tfPMA.getText().isEmpty())
			Dialogos.mostrarDialogoAdvertencia("Aviso", "Debe rellenar todos los campos");

	}

	// activarCampoInsertarMatricula

	@FXML
	private void activarInsertarMatricula() {
		vbCampoEliminarVe.setDisable(false);
		cbEliminarVe.setDisable(true);
		tfInsertarMatricula.setText("");

	}

	// ELIMINAR VEHICULOS

	@FXML
	void eliminarVehiculo() throws IOException {

		if (Dialogos.mostrarDialogoConfirmacion("Aviso",
				"Vas a ELIMINAR un  VEHICULO, ¿estas seguro de creer CONTINUAR ?")) {
			try {
				tfInsertarMatricula.getText();

				IUGTablas.controladorMVC.borrarVehiculo(tfInsertarMatricula.getText());
				vehiculos.remove(vehiculos); // metodos de observable
				Dialogos.mostrarDialogoInformacion("Borrar Vehículo", "Vehículo eliminado correctamente");
				crearTablaVehiculos();

			} catch (ExcepcionAlquilerVehiculos e) {
				Dialogos.mostrarDialogoError("Error", e.getMessage());
			}
			vbCampoEliminarVe.setDisable(true);
			tfInsertarMatricula.setText("INSERTAR MATRICULA");
			cbEliminarVe.setDisable(false);
			cbEliminarVe.setSelected(false);

		}

	}

	// METODO LIMPIAR CAMPOS

	@FXML
	private void limpiarCamposVe() {
		tfMatricula.setText("");
		tfMarca.setText("");
		tfModelo.setText("");
		tfCilindrada.setText("");
		tfNumeroPlazas.setText("");
		tfPMA.setText("");
		cbAnadirVe.requestFocus();
		cbAnadirVe.setSelected(false);
		cbAnadirVe.setDisable(false);
		hbTipoVehiculo.setDisable(true);
		tgTipoVe.selectToggle(null);
		gpFichaVehiculo.setDisable(true);
		tfInsertarMatricula.setText("");

		

	}

	// :::::::::::::::::::::::::::METODOS ALQUILER :::::::::::::::::::::: //

	@FXML
	private void crearTablaAlquileres() {

		tcClientesA
				.setCellValueFactory(alquiler -> new ReadOnlyStringWrapper(alquiler.getValue().getCliente().getDni()));
		tcClientesA.setResizable(false);

		tcVehiculo.setCellValueFactory(
				alquiler -> new ReadOnlyStringWrapper(alquiler.getValue().getVehiculo().getMatricula()));
		tcVehiculo.setResizable(false);

		// tcDias.setCellValueFactory(
		// alquiler -> new ReadOnlyIntegerWrapper(alquiler.getValue().getDias()))));
		// tcDias.setResizable(false);

		tvAlquiler.setItems(FXCollections.observableArrayList(IUGTablas.controladorMVC.obtenerAlquileres()));
		tvAlquiler.getSelectionModel().selectedItemProperty()
				.addListener((ov, oldValue, newValue) -> accionesAlquileres(ov.getValue()));
	}

	// abrir alquiler

	@FXML
	private void abrirAlquiler(ActionEvent evento) throws IOException {
		if (evento.getSource() == btAbrir) {
			try {
				String dniIniciar = tfDniClienteA.getText();
				String matriculaIniciar = tfMatriculaVeA.getText();

				Cliente cliente = IUGTablas.controladorMVC.buscarCliente(dniIniciar);
				Vehiculo vehiculo = IUGTablas.controladorMVC.buscarVehiculo(matriculaIniciar);
				IUGTablas.controladorMVC.abrirAlquiler(cliente, vehiculo);

				Dialogos.mostrarDialogoInformacion("Iniciar Alquiler", "Alquiler iniciado correctamente");
				tfDniClienteA.setText("");
				tfMatriculaVeA.setText("");

			} catch (ExcepcionAlquilerVehiculos e) {
				Dialogos.mostrarDialogoError("Iniciar alquiler", e.getMessage());
			}
		}
		crearTablaAlquileres();

	}

	// cerrar alquiler
	@FXML
	private void cerrarAlquiler(ActionEvent evento) throws IOException {
		if (evento.getSource() == btCerrar) {
			try {
				String dniCerrar = tfInsertarDni.getText();
				String matriculaCerrar = tfMatriculaVeA.getText();

				Cliente cliente = IUGTablas.controladorMVC.buscarCliente(dniCerrar);
				Vehiculo vehiculo = IUGTablas.controladorMVC.buscarVehiculo(matriculaCerrar);
				IUGTablas.controladorMVC.cerrarAlquiler(vehiculo);

				Dialogos.mostrarDialogoInformacion("Cerrar Alquiler", "Alquiler cerrado correctamente");
				tfMatriculaVeA.setText("");
				tfDniClienteA.setText("");

			} catch (ExcepcionAlquilerVehiculos e) {
				Dialogos.mostrarDialogoError("CERRAR ALQUILER", e.getMessage());
			}
		}
		crearTablaAlquileres();
	}

	// activar Ficha Alquiler

	@FXML

	private void activarFichaAlquilerAC() {

		rbGestionAlquiler.setDisable(true);
		gpFichaAlquiler.setDisable(false);
		
	}

	@FXML
	private void limpiarCamposA() {
		rbGestionAlquiler.setDisable(false);
		gpFichaAlquiler.setDisable(true);
		tfMatriculaVeA.setText("");

		tfDniClienteA.setText("");

	}
	// activar Listado Alquiler

	@FXML
	private void activaListadoAlquiler() {
		rbListadoAlquiler.setDisable(true);
		tvAlquiler.setDisable(false);
	}

	private Object accionesAlquileres(Alquiler value) {

		return null;
	}

	// mostrar alquilerAbierto

	@FXML
	private void mostrarAlquilerAbierto() {
		if (cbAlquilerAbierto.isSelected()) {

			tvAlquiler.focusModelProperty();
			tvAlquiler
					.setItems(FXCollections.observableArrayList(IUGTablas.controladorMVC.obtenerAlquileresAbiertos()));
			if (tvAlquiler.getItems().isEmpty()) {

			} else {
				tvAlquiler.getSelectionModel().clearSelection();

			}
		} else {
			tvAlquiler.setItems(FXCollections.observableArrayList(IUGTablas.controladorMVC.obtenerAlquileres()));
			tvAlquiler.getSelectionModel().clearSelection();
			rbListadoAlquiler.setDisable(true);

		}
		crearTablaAlquileres();
		System.out.println("Se crear de nuevo la tabla");
	}

}
