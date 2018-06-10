package alquilerVehiculos.mvc.controlador;

import java.util.List;

import alquilerVehiculos.mvc.modelo.dominio.Alquiler;
import alquilerVehiculos.mvc.modelo.dominio.Cliente;
import alquilerVehiculos.mvc.modelo.dominio.vehiculo.TipoVehiculo;
import alquilerVehiculos.mvc.modelo.dominio.vehiculo.Vehiculo;

public interface IControladorAlquilerVehiculos {

	/**
	 * llama a metodo de comenzar
	 */
	void comenzar();

	void salir();

	// metodos de cliente Cliente
	/**
	 * @param cliente
	 */
	void anadirCliente(Cliente cliente);

	/**
	 * @param dni
	 */
	void borrarCliente(String dni);

	/**
	 * @param dni
	 * @return
	 */
	Cliente buscarCliente(String dni);

	List<Cliente> obtenerClientes();

	/**
	 * @param vehiculo
	 * @param tipoVehiculo
	 */
	void anadirVehiculo(Vehiculo vehiculo, TipoVehiculo tipoVehiculo);

	/**
	 * @param matricula
	 */
	void borrarVehiculo(String matricula);

	/**
	 * @param matricula
	 * @return
	 */

	Vehiculo buscarVehiculo(String matricula);

	/**
	 * @return coleccion de Vehiculos
	 */
	List<Vehiculo> obtenerVehiculos();

	/**
	 * @param cliente
	 * @param vehiculo
	 */
	void abrirAlquiler(Cliente cliente, Vehiculo vehiculo);

	/**
	 * @param vehiculo
	 */
	void cerrarAlquiler(Vehiculo vehiculo);

	/**
	 * @param string 
	 * @return vector de alquileres
	 */
	List<Alquiler> obtenerAlquileresAbiertos(String string);

	List<Alquiler> obtenerAlquileres();
	
	List <Alquiler>obtenerAlquileresCliente(String dni);

	List<Alquiler> obtenerAlquileresVehiculos(String matricula);

	List<Alquiler> obtenerAlquileresAbiertos();
	

}