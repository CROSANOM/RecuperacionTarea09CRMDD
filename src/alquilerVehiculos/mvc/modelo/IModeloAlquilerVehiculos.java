package alquilerVehiculos.mvc.modelo;

import java.util.List;

import alquilerVehiculos.mvc.modelo.dao.Alquileres;
import alquilerVehiculos.mvc.modelo.dao.Clientes;
import alquilerVehiculos.mvc.modelo.dao.Vehiculos;
import alquilerVehiculos.mvc.modelo.dominio.Alquiler;
import alquilerVehiculos.mvc.modelo.dominio.Cliente;
import alquilerVehiculos.mvc.modelo.dominio.vehiculo.TipoVehiculo;
import alquilerVehiculos.mvc.modelo.dominio.vehiculo.Vehiculo;

public interface IModeloAlquilerVehiculos {

	/**
	 * @return coleccion de clientes
	 */
	List<Cliente> getCliente();

	/**
	 * @return coleccion de vehiculos
	 */
	List<Vehiculo> obtenerVehiculos();

	/**
	 * @return coleccion de alquileres
	 */
	List<Alquiler> obtenerAlquileres();

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

	// metodos de los Vehiculos
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
	 * @param cliente
	 * @param vehiculo
	 */
	void abrirAlquiler(Cliente cliente, Vehiculo vehiculo);

	/**
	 * @param vehiculo
	 */
	void cerrarAlquiler(Vehiculo vehiculo);

	void leerClientes();

	void escribirClientes();

	void leerVehiculos();

	void escribirVehiculos();

	void leerAlquileres();

	void escribirAlquileres();

	String toString();

	/**
	 * @return coleccion de Clientes
	 */

	List<Alquiler> obtenerAlquileresAbiertos();

	List<Alquiler> obtenerAlquileresCliente(String dni);

	List<Alquiler> obtenerAlquileresVehiculos(String matricula);

}