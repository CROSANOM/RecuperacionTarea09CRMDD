package alquilerVehiculos.mvc.vista.iugraficatablas;

import alquilerVehiculos.mvc.controlador.IControladorAlquilerVehiculos;
import alquilerVehiculos.mvc.vista.IVistaAlquilerVehiculos;
import alquilerVehiculos.mvc.vista.iugraficatablas.utilidades.Dialogos;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.WindowEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.SplitPane;

/**
 * @author crosanom
 *
 */
public class IUGTablas extends Application implements IVistaAlquilerVehiculos { // clase que hereda Application e implen
																				// interfazIvista

	public static IControladorAlquilerVehiculos controladorMVC;

	/*
	 * tiene 4 metodos
	 * 
	 * start heredado Application ( recibe parametro un escenarioPrincipal )
	 * 
	 * confirmarSalida,
	 * 
	 * setControlador que le pasamos la Icontrolador
	 * 
	 * comenzar
	 */

	// metodo start heradado de Application se sobre escribe

	@Override
	public void start(Stage escenarioPrincipal) {

		try {

			// cargamos un cargador con la venta principal en raiz que es objeto de tipo
			// VBox

			                 SplitPane raiz = (SplitPane) FXMLLoader.load(getClass()
					.getResource("/alquilerVehiculos/mvc/vista/iugraficatablas/vistas/VentanaPrincipalMd.fxml"));

			Scene escena = new Scene(raiz);
			escenarioPrincipal.setOnCloseRequest(e -> confirmarSalir(escenarioPrincipal, e));
			escenarioPrincipal.setTitle("Alquiler de Vehiculos");
			escenarioPrincipal.setScene(escena);
			escenarioPrincipal.setResizable(false);
			escenarioPrincipal.show(); // muestre el escenario Principal con la escena 

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	// declar el metodo confirmarSalir parametros Stage Evento Ventana a
	/**
	 * @param escenarioPrincipal
	 * @param e
	 */
	private void confirmarSalir(Stage escenarioPrincipal, WindowEvent e) {
		if (Dialogos.mostrarDialogoConfirmacion("salir", "�Estas seguro que quieres salir de la Aplicaci�n?",
				escenarioPrincipal)) {

			controladorMVC.salir();
			escenarioPrincipal.close();

		} else
			e.consume();
	}

//	@Override
//	public void setControlador(IControladorAlquilerVehiculos controladorMVC) {
//		IUGTablas.controladorMVC = controladorMVC;
//
//	}

	@Override
	public void comenzar() { //

		launch(this.getClass());

	}

	@Override
	public void setControlador(IControladorAlquilerVehiculos controlador) {
		IUGTablas.controladorMVC = controlador;
		
	}

}
