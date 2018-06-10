package alquilerVehiculos.mvc.modelo.dao;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import alquilerVehiculos.mvc.modelo.dominio.Alquiler;
import alquilerVehiculos.mvc.modelo.dominio.Cliente;
import alquilerVehiculos.mvc.modelo.dominio.ExcepcionAlquilerVehiculos;
import alquilerVehiculos.mvc.modelo.dominio.vehiculo.Vehiculo;

public class Alquileres {
	// atributos
	private List<Alquiler> alquileres;
	private final String FICHERO_ALQUILERES = "datos/alquileres.dat";

	// constructor que pasa un vector alquileres a alquileres
	public Alquileres() {
		alquileres = new Vector<Alquiler>();
	}

	// Vector de alquileres modificado getAlquieresAbiertos
	public List<Alquiler> getAlquileres() {
		return new Vector<Alquiler>(alquileres);

	}
	// METODOS de alquiler

	// leer
	public void leerAlquileres() {
		File fichero = new File(FICHERO_ALQUILERES);
		ObjectInputStream entrada;
		try {
			entrada = new ObjectInputStream(new FileInputStream(fichero));// se crear un objetoInput al que se le pasa
																			// un fichero
			try {
				while (true) {

					Alquiler alquiler = (Alquiler) entrada.readObject();// se pasa a un alquiler del tipo Alquiler el
																		// fichero a traves del objet ObjetrRea..
					alquileres.add(alquiler); // anadir un alquiler

				}
				// Excepciones se capturan para que se pare el programa
			} catch (EOFException eo) {
				entrada.close();
				System.out.println("Fichero alquileres leí­do satisfactoriamente.");
			} catch (ClassNotFoundException e) {
				System.out.println("No puedo encontrar la clase que tengo que leer.");
			} catch (IOException e) {
				System.out.println("Error inesperado de Entrada/Salida.");
			}
		} catch (IOException e) {
			System.out.println("No puedo abrir el fihero de alquieres.");
		}
	}

	// metodo Escribir
	public void escribirAlquileres() {
		File fichero = new File(FICHERO_ALQUILERES); // crea el fichero
		try {
			ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(fichero));// se crea el objeto
																								// salida que es del //
																								// tipo Object y le
																								// pasamos un fichero
			for (Alquiler alquiler : alquileres)// se recorren lo alquileres buscando alquileres
				salida.writeObject(alquiler); // al salir escribir un alquiler metodo Write
			salida.close();// metodo de cerrar fichero
			System.out.println("Fichero alquileres escrito satisfactoriamente");
			// excepciones
		} catch (FileNotFoundException e) {
			System.out.println("No puedo crear el fichero de alquileres");
		} catch (IOException e) {
			System.out.println("Error inesperado de Entrada/Salida");
		}

	}

	// abrir alquiler con llamada a metodo ( CompruebaExistencia )

	/**
	 * @param cliente
	 * @param vehiculo
	 */
	public void abrirAlquiler(Cliente cliente, Vehiculo vehiculo) {
		compruebaExistencia(vehiculo);
		// int indice =alquieres.get(indice);
		alquileres.add(new Alquiler(cliente, vehiculo));
	}

	// compruebaExista (indiceNosuperaTamano)
	/**
	 * @param vehiculo
	 */

	private void compruebaExistencia(Vehiculo vehiculo) {

		int indice = 0;
		boolean encontrado = false;
		while (indiceNoSuperaTamano(indice) && !encontrado) {
			if (alquileres.get(indice) != null) {
				encontrado = true;
			} else if (alquileres.get(indice).getVehiculo().getMatricula().equals(vehiculo.getMatricula())
					&& !alquileres.get(indice).getVehiculo().getDisponible()) {
				throw new ExcepcionAlquilerVehiculos("El vehiculo no esta disponible, ya esta alquilado");
			} else {
				indice++; // pasar a la siguiente posicion
			}
		}

	}

	/**
	 * @param indice
	 * @return boolean si indice supera false, si es menor true
	 */
	private boolean indiceNoSuperaTamano(int indice) {
		return indice < alquileres.size();

	}

	// cerrar alquiler ( buscarAlquilerAbierto)
	/**
	 * @param vehiculo
	 */
	public void cerrarAlquiler(Vehiculo vehiculo) {

		int indice = buscarAlquilerAbierto(vehiculo);
		if (indiceNoSuperaTamano(indice))
			alquileres.get(indice).cerrar();
		else
			throw new ExcepcionAlquilerVehiculos("No hay ningun alquiler Abierto");
	}

	/**
	 * @param vehiculo
	 * @return
	 */
	private int buscarAlquilerAbierto(Vehiculo vehiculo) {
		int indice = 0;
		boolean encontradoAlquiler = false;
		while (indiceNoSuperaTamano(indice) && !encontradoAlquiler) {
			if (alquileres.get(indice).getVehiculo().getMatricula().equals(vehiculo.getMatricula())
					&& alquileres.get(indice) != null && alquileres.get(indice).getDias() == 0)
				encontradoAlquiler = true;
			else
				indice++;
		}
		return encontradoAlquiler ? indice : alquileres.size();

	}

	// ObtenerAlquileresAbiertos

	public List<Alquiler> obtenerAlquileresAbiertos() {
		int posicion = 0;

		// Interfaz List implemente la clase Vector
		List<Alquiler> alquileresAbiertos = new Vector<Alquiler>();

		while (posicion < alquileres.size()) {

			if (alquileres.get(posicion).getDias() == 0) {

				alquileresAbiertos.add(alquileres.get(posicion));
			}
			posicion++;
		}

		return alquileresAbiertos;
	}

	// ObtenerAlquileresClientes (String dni )

	public List<Alquiler> obtenerAlquileresCliente(String dni) { // nombre del metodo

		int posicion = 0;

		List<Alquiler> alquileresCliente = new Vector<Alquiler>(); // instancia la interfaz list con vector

		while (posicion < alquileres.size()) { // mientras

			if (alquileres.get(posicion).getCliente().getDni().equals(dni)) { // condicion

				alquileres.add(alquileres.get(posicion)); //
			}

			posicion++;
		}
		return alquileresCliente;
	}

	// Obtener AlquileresVehiculos

	public List<Alquiler> obtenerAlquileresVehiculos(String matricula){
		 int posicion = 0; 
		  
		 List <Alquiler> alquileresVehiculos = new Vector <Alquiler> ();
		 
		 while (posicion<alquileres.size()) {
			 
			if (alquileres.get(posicion).getVehiculo().getMatricula().equals(matricula)) {
				
				alquileres.add(alquileres.get(posicion));
			}
			 
			posicion++;
		 }
		
		 return alquileresVehiculos;
	}
	
	
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Alquileres [alquileres=" + alquileres + ", FICHERO_ALQUILERES=" + FICHERO_ALQUILERES + "]";
	}


	
}
