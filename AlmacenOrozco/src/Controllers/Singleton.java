package Controllers;

import java.time.LocalDate;
import java.util.List;

import Exceptions.ClienteException;
import Model.Almacen;
import Model.Cliente;
import Model.ClienteJuridico;
import Model.ClienteNatural;

public class Singleton {

	Almacen almacen = null;

	private static class SingletonHolder {
		// El constructor de Singleton puede ser llamado desde aquí al ser
		// protected
		private final static Singleton eINSTANCE = new Singleton();
	}

	public static Singleton getInstance() {
		return SingletonHolder.eINSTANCE;
	}

	public Singleton(){

	}

	public Almacen getAlmacen() {
		return almacen;
	}

	public void setAlmacen(Almacen almacen) {
		this.almacen = almacen;
	}

	public boolean crearClienteNatural(String nombre, String apellido, String id, String direccion, String telefono,
			String email, String fechaNacimiento) throws ClienteException {
		ClienteNatural cliente  = new ClienteNatural(nombre, apellido, id, direccion, telefono, email, fechaNacimiento);
		boolean flag = almacen.crearClienteNatural(cliente);
		return flag;
	}

	public boolean crearClienteJuridico(String nombre, String apellido, String id, String direccion, String telefono,
			String nit) throws ClienteException {
		ClienteJuridico cliente = new ClienteJuridico(nombre, apellido, id, direccion, telefono, nit);
		boolean flag = almacen.crearClienteJuridico(cliente);
		return flag;
	}

	public List<Cliente> getListaClientes() {
		return almacen.getListaClientes();
	}





}
