package Controllers;

import java.time.LocalDate;
import java.util.List;

import Exceptions.ClienteException;
import Exceptions.ProductoException;
import Exceptions.TransaccionException;
import Model.Almacen;
import Model.Cliente;
import Model.ClienteJuridico;
import Model.ClienteNatural;
import Model.DetalleTransaccion;
import Model.Producto;
import Model.ProductoEnvasado;
import Model.ProductoPerecedero;
import Model.ProductoRefrigerado;
import Model.TipoPaisOrigen;
import Model.TipoProducto;
import Model.Transaccion;

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
		inicializarDatos();

	}
	private void inicializarDatos() {
		almacen = new Almacen();

		Producto producto1= new ProductoPerecedero("0000", "salchichas", "Salchicas enlatadas", 112, 123, TipoProducto.PERECEDERO, "23/08/2023");
		almacen.getListaProductos().add(producto1);

	}

	public Almacen getAlmacen() {
		return almacen;
	}

	public void setAlmacen(Almacen almacen) {
		this.almacen = almacen;
	}
//-------------------------CLIENTES----------------------------------------
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
	/**
	 *
	 * @return
	 */
	public List<Cliente> getListaClientes() {
		return almacen.getListaClientes();
	}

	public boolean eliminarCliente(Cliente clienteSeleccion) throws ClienteException {
		return almacen.eliminarPersona(clienteSeleccion);
	}
//----------------------PRODUCTOS-----------------------------------------
	public boolean crearProductoRefrigerado(String nombreProducto, String codigo, String cantidad, String descrp, String valor,
			String codigoAprob, String temperatura) throws ProductoException {
		Double valorU = Double.parseDouble(valor);
		int cantidadP = Integer.parseInt(cantidad);
		ProductoRefrigerado newProducto = new ProductoRefrigerado(codigo, nombreProducto, descrp, valorU, cantidadP,
				TipoProducto.REFRIGERADO, codigoAprob, temperatura);
		boolean flag = almacen.crearProductoRefrigerado(newProducto);
		return flag;
	}

	public boolean crearProductoEnvasado(String nombreProducto, String codigo, String cantidad, String descrp,
			String valor, String fechaEnvasado, String pesoEnvase, TipoPaisOrigen paisOrigen) throws ProductoException {
		Double valorU = Double.parseDouble(valor);
		int cantidadP = Integer.parseInt(cantidad);
		ProductoEnvasado producto = new ProductoEnvasado(codigo, nombreProducto, descrp, valorU, cantidadP,
				TipoProducto.ENVASADO, fechaEnvasado, pesoEnvase, paisOrigen);
		boolean flag = almacen.crearProductoEnvasado(producto);
		return flag;
	}

	public boolean crearProductoPerecedero(String nombreProducto, String codigo, String cantidad, String descrp,
			String valor, String fechaVencimiento) throws ProductoException {
		Double valorU = Double.parseDouble(valor);
		int cantidadP = Integer.parseInt(cantidad);
		ProductoPerecedero newProducto = new ProductoPerecedero(codigo, nombreProducto, descrp, valorU,
				cantidadP, TipoProducto.PERECEDERO, fechaVencimiento);
		boolean flag = almacen.crearProductoPerecedero(newProducto);
		return flag;
	}

	public boolean eliminarProducto(Producto productoSeleccion) throws ProductoException{
		return almacen.eliminarProducto(productoSeleccion);
	}


	public boolean venderProducto(Producto productoVender) throws ProductoException {
		return almacen.venderProducto(productoVender);
	}


	public List<Producto> getListaProductos() {
		return almacen.getListaProductos();
	}

//-----------------------------TRANSACCIONES----------------------------
	public boolean crearTransaccion(String codigo,String fecha, String cedulaCliente, String total, String iva) throws TransaccionException {
		double totalT= Double.parseDouble(total);
		float ivaT= Float.parseFloat(iva);
		Cliente clienteVenta= almacen.obtenerPersona(cedulaCliente);
		Transaccion newTransaccion= new Transaccion(codigo, fecha, clienteVenta, totalT, ivaT);
		return almacen.crearTransaccion(newTransaccion);

	}


	public boolean eliminarTransaccion(Transaccion eliminarTransaccion) throws TransaccionException{
		return almacen.eliminarTransaccion(eliminarTransaccion);
	}



	public List<Transaccion> getListaTransacciones(){
		return almacen.getListaTransacciones();
	}

}
