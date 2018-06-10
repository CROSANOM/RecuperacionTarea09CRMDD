package alquilerVehiculos.mvc.modelo.dao;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOError;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import alquilerVehiculos.mvc.modelo.dominio.Cliente;
import alquilerVehiculos.mvc.modelo.dominio.ExcepcionAlquilerVehiculos;

/**
 * @author crosanom
 *
 */
public class Clientes { // Clientes clase

	// atributos
	private Map<String, Cliente> clientes; // interfaz MAP que se le pasan dos objetos como parametro tipo String y
											// Cliente
	private final String FICHERO_CLIENTES = "datos/clientes.dat";// ruta del fichero

	// constructor

	public Clientes() {
		clientes = new HashMap<String, Cliente>();
	}

	// Crear una lista de ClientesOrdenados que tiene como objetos clientesOrdenados
	/**
	 * @return vector de Clientes ordenados
	 */
	public List<Cliente> getClientes() {
		List<Cliente> clientesOrdenados = new Vector<Cliente>(clientes.values());
		Collections.sort(clientesOrdenados, new Comparator<Cliente>() {
			public int compare(Cliente uno, Cliente otro) {
				return uno.getNombre().compareTo(otro.getNombre());
			}
		});
		return clientesOrdenados;
	}

	// Metodos de lectura - escritura de fichero

	public void leerClientes() {

		File fichero = new File(FICHERO_CLIENTES);
		ObjectInputStream entrada;
		try {
			entrada = new ObjectInputStream(new FileInputStream(fichero));

			try {
				while (true) {
					Cliente cliente = (Cliente) entrada.readObject();
					clientes.put(cliente.getDni(), cliente);
				}
			} catch (EOFException eo) {
				entrada.close();
				System.out.println("Fichero clientes leído satisfactoriamente.");
				Cliente.aumentarUltimoIdentificador(calcularUltimoIdentificador());
			} catch (ClassNotFoundException e) {
				System.out.println("No puedo encontrar la clase que tengo que leer.");
			} catch (IOException e) {
				System.out.println("Error inesperado de Entrada/Salida.");
			}
		} catch (IOException e) {
			System.out.println("No puedo abrir el fihero de clientes.");
		}
	}

	// reformular CalcularUltimoIdentificador
	/**
	 * @return identificador
	 */
	private int calcularUltimoIdentificador() {
		int ultimoIdentificador = 0;
		for (Cliente cliente : clientes.values())
			if (cliente.getIdentificador() > ultimoIdentificador)
				ultimoIdentificador = cliente.getIdentificador();

		return ultimoIdentificador;

	}

	// escribirClientes

	public void escribirClientes() {
		File fichero = new File(FICHERO_CLIENTES);
		try {
			ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(fichero));
			for (Cliente cliente : clientes.values())
				salida.writeObject(cliente);
			salida.close();
			System.out.println("Fichero clientes escrito satisfactoriamente.");
		} catch (FileNotFoundException e) {
			System.out.println("No puedo crear el fichero de clientes");
		} catch (IOException e) {
			System.out.println("Error inesperado de Entrada/Salida");
		}
	}
	//
	/*
	 * anadir Clientes (BuscarIndiceLibreComprobandoExistencia,
	 * indiceNoSuperaTamano)
	 */

	/**
	 * @param cliente
	 */
	public void anadirCliente(Cliente cliente) {
		if (clientes.containsKey(cliente.getDni()))
			throw new ExcepcionAlquilerVehiculos("Ya existe un cliente con dicho DNI");
		else
			clientes.put(cliente.getDni(), cliente);
	}

	/**
	 * @param dni
	 */
	public void borrarCliente(String dni) {
		if (clientes.containsKey(dni))
			clientes.remove(dni);
		else
			throw new ExcepcionAlquilerVehiculos("El cliente a borrar no existe");
	}

	/**
	 * @param dni
	 * @return
	 */
	public Cliente buscarCliente(String dni) {
		if (clientes.containsKey(dni))
			return new Cliente(clientes.get(dni));
		else
			return null;
	}

}
