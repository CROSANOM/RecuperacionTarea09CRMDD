package alquilerVehiculos.mvc.modelo;

import java.util.List;
import alquilerVehiculos.mvc.modelo.dao.Alquileres;
import alquilerVehiculos.mvc.modelo.dao.Clientes;
import alquilerVehiculos.mvc.modelo.dao.Vehiculos;
import alquilerVehiculos.mvc.modelo.dominio.Alquiler;
import alquilerVehiculos.mvc.modelo.dominio.Cliente;
import alquilerVehiculos.mvc.modelo.dominio.vehiculo.TipoVehiculo;
import alquilerVehiculos.mvc.modelo.dominio.vehiculo.Vehiculo;

/**
 * @author crosanom
 *
 */

public class AlquilerVehiculos implements IModeloAlquilerVehiculos {
	// atributos
	private Clientes clientes;
	private Vehiculos vehiculos;
	private Alquileres alquileres;

	// constructor

	public AlquilerVehiculos() {
		clientes = new Clientes();
		vehiculos = new Vehiculos();
		alquileres = new Alquileres();
	}

	// Metodos clientes

	@Override
	public void anadirCliente(Cliente cliente) {
		clientes.anadirCliente(cliente);
	}

	@Override
	public void borrarCliente(String dni) {
		clientes.borrarCliente(dni);
	}

	@Override
	public Cliente buscarCliente(String dni) {
		return clientes.buscarCliente(dni);
	}
	
	@Override
	public List<Cliente> getCliente() {
		return clientes.getClientes();
	}

	// metodos para Vehiculos

	@Override
	public void anadirVehiculo(Vehiculo vehiculo, TipoVehiculo tipoVehiculo) {
		vehiculos.anadirVehiculo(vehiculo, tipoVehiculo);
	}

	@Override
	public void borrarVehiculo(String matricula) {
		vehiculos.borrarVehiculo(matricula);
	}

	@Override
	public Vehiculo buscarVehiculo(String matricula) {
		return vehiculos.buscarVehiculo(matricula);
	}

	@Override
	public List<Vehiculo> obtenerVehiculos() {
		return vehiculos.getVehiculos();
	}

	// metodos para los alquileres

	@Override
	public void abrirAlquiler(Cliente cliente, Vehiculo vehiculo) {
		alquileres.abrirAlquiler(cliente, vehiculo);
	}

	@Override
	public void cerrarAlquiler(Vehiculo vehiculo) {
		alquileres.cerrarAlquiler(vehiculo);
	}

	public List<Alquiler> obtenerAlquileres() {
		return alquileres.getAlquileres();

	}
	

	//  obtener Alquileres Abiertos
	public List<Alquiler> obtenerAlquileresAbiertos() {
		return alquileres.obtenerAlquileresAbiertos();

	}

 // obtenerAlquileresCliente ( String dni ) 
	
	public List<Alquiler> obtenerAlquileresCliente(String dni) {
		return alquileres.obtenerAlquileresCliente(dni);

	}

	public List<Alquiler> obtenerAlquileresVehiculos(String matricula) {

		return alquileres.obtenerAlquileresVehiculos(matricula);
	}
	// acceso para leer - escribir clientes, vehiculos, alquileres

	public void leerClientes() {
		clientes.leerClientes();
	}

	public void escribirClientes() {
		clientes.escribirClientes();
	}

	public void leerVehiculos() {
		vehiculos.leerVehiculos();
	}

	public void escribirVehiculos() {
		vehiculos.escribirVehiculos();
	}

	public void leerAlquileres() {
		alquileres.leerAlquileres();
	}

	public void escribirAlquileres() {
		alquileres.escribirAlquileres();
	}

	@Override
	public String toString() {
		return "AlquilerVehiculos [clientes=" + clientes + ", vehiculos=" + vehiculos + ", alquileres=" + alquileres
				+ "]";
	}




}
