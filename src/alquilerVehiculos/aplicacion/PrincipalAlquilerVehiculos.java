package alquilerVehiculos.aplicacion;

import alquilerVehiculos.mvc.controlador.ControladorAlquilerVehiculos;

import alquilerVehiculos.mvc.controlador.IControladorAlquilerVehiculos;
import alquilerVehiculos.mvc.modelo.AlquilerVehiculos;
import alquilerVehiculos.mvc.modelo.IModeloAlquilerVehiculos;
import alquilerVehiculos.mvc.vista.IVistaAlquilerVehiculos;
import alquilerVehiculos.mvc.vista.iutextual.IUTextual;

// Accedo a las clases que controlan la aplicación incluida la consola no se instancian sus metodos Staticos
public class PrincipalAlquilerVehiculos {

	// metodo main 
	
	/**
	 * @param args llama a Interfaz y al metodo comenzar
	 */
	public static void main(String[] args) {
		IModeloAlquilerVehiculos modelo = new AlquilerVehiculos();
		IVistaAlquilerVehiculos vista = new IUTextual();
		IControladorAlquilerVehiculos controlador = new ControladorAlquilerVehiculos(modelo, vista);

		controlador.comenzar();
		
		
		
	}

}
