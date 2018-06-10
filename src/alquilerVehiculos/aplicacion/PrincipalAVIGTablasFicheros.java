package alquilerVehiculos.aplicacion;

import alquilerVehiculos.mvc.controlador.ControladorAlquilerVehiculos;

import alquilerVehiculos.mvc.controlador.IControladorAlquilerVehiculos;
import alquilerVehiculos.mvc.modelo.AlquilerVehiculos;
import alquilerVehiculos.mvc.modelo.IModeloAlquilerVehiculos;
import alquilerVehiculos.mvc.vista.IVistaAlquilerVehiculos;
import alquilerVehiculos.mvc.vista.iugraficatablas.IUGTablas;



public class PrincipalAVIGTablasFicheros { // nombre clase 

	public static void main(String[] args) {  
		
		IModeloAlquilerVehiculos modelo = new AlquilerVehiculos(); // lama a Interfaz AlquilerVehiculos 
		IVistaAlquilerVehiculos vista = new IUGTablas();
		IControladorAlquilerVehiculos controlador = new ControladorAlquilerVehiculos (modelo, vista);
		
		controlador.comenzar();

	}

}
