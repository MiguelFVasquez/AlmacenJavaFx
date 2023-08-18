package Model;

import java.util.ArrayList;
import java.util.List;import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import Exceptions.ClienteException;
import Exceptions.ProductoException;
import Exceptions.TransaccionException;

public class Almacen {
	private List<Cliente> listaClientes= new ArrayList<>();
	private List<Producto> listaProductos= new ArrayList<>();
	private List<Transaccion> listaTransacciones= new ArrayList<>();


	/**
	 *
	 */
	public Almacen() {
		super();
	}

	/**
	 * @param listaPersonas
	 * @param listaProductos
	 * @param listaTransaccionesList
	 */
	public Almacen(List<Cliente> listaPersonas, List<Producto> listaProductos,
			List<Transaccion> listaTransaccionesList) {
		super();
		this.listaClientes = listaPersonas;
		this.listaProductos = listaProductos;
		this.listaTransacciones = listaTransaccionesList;
	}



	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public List<Producto> getListaProductos() {
		return listaProductos;
	}

	public void setListaProductos(List<Producto> listaProductos) {
		this.listaProductos = listaProductos;
	}

	public List<Transaccion> getListaTransacciones() {
		return listaTransacciones;
	}

	public void setListaTransacciones(List<Transaccion> listaTransacciones) {
		this.listaTransacciones = listaTransacciones;
	}

	//------------------------------------CRUD DE LOS CLIENTES---------------------------------------------------
	/**
	 * Se recorre la lista a traves del stream, si se encuentra un cliente con la misma cedula lo retorna
	 * si no, retornara null
	 * @param cedula
	 * @return
	 */
	public Cliente obtenerPersona(String cedula){
		return (Cliente) listaClientes.stream()
				.filter(c -> c.getIdentificacion().equals(cedula))
				.findFirst()
				.orElse(null);
	}

	/** Verifica si un cliente es igual a traves de la cedula
	 * Si se añade un cliente a la nueva lista es que ya existe un cliente en la lista
	 * Por lo tanto, si la lista NO está vacia significa que hay un cliente con esa identificacion
	 *
	 * @param cedula
	 * @return
	 */
	public boolean verificarCliente(String cedula){
		boolean encontrado= false;
		List<Cliente> clientesIguales= this.listaClientes.stream()
				.filter(c -> c.getIdentificacion().equals(cedula))
				.collect(Collectors.toList());

		if (!clientesIguales.isEmpty()) {
			encontrado=true;
		}

		return encontrado;

	}
//---------------------------A TENER EN CUENTA----------------------------------
	/**
	 * Estos dos metodos se llamaran eventualmente en el singleton y se va a hacer a traves de la opcion que ingresen en el combo box el tipo de cliente a registrar
	 * Si quiere registrar un cliente natural llama al metodo correspondiente y captura los datos necesarios y crea la intancia de cliente que le va a mandar a este metodo
	 *
	 */

//-------------------------------------------------------------------------------

	/**
	 * Si el cliente no existe, simplemente se añade el cliente a la lista
	 * Si existe lanza la excepcion
	 * @param newCliente
	 * @return
	 * @throws ClienteException
	 */
	public boolean crearClienteNatural(ClienteNatural newCliente) throws ClienteException{
		boolean creado = false;
		if (verificarCliente(newCliente.getIdentificacion())) {
			throw new ClienteException("El cliente que desea registrar ya se encuentra en el sistema");
		}
		else {
			creado=true;
			listaClientes.add(newCliente);
		}

		return creado;
	}
	/**
	 *
	 * @param newCliente
	 * @return
	 * @throws ClienteException
	 */
	public boolean crearClienteJuridico(ClienteJuridico newCliente) throws ClienteException{
		boolean creado = false;
		if (verificarCliente(newCliente.getIdentificacion())) {
			throw new ClienteException("El cliente que desea registrar ya se encuentra en el sistema");
		}
		else {
			creado=true;
			listaClientes.add(newCliente);
		}
		return creado;
	}

	/**
	 *
	 * @param personaEliminar
	 * @return
	 * @throws ClienteException
	 */
	public boolean eliminarPersona(Cliente personaEliminar) throws ClienteException{
		boolean eliminado= false;
		if (obtenerPersona(personaEliminar.getIdentificacion()) == null) {
			throw new ClienteException("La persona que desea eliminar no se encuentra registrada");
		}else {
			eliminado=true;
			listaClientes.remove(personaEliminar);
		}
		return eliminado;
	}




//------------------------------------CRUD DE LOS PRODUCTOS---------------------------------------------------
	/**
	 *
	 * @param codigo
	 * @return
	 */
	public boolean verificarProducto(String codigo){
		boolean verificado= false;
		List<Producto> productosEncontrados= this.listaProductos.stream()
				.filter(p -> p.getCodigo().equals(codigo))
				.collect(Collectors.toList());

		if (!productosEncontrados.isEmpty()) {
			verificado=true;
		}
		return verificado;
	}

	/**
	 *
	 * @param codigo
	 * @return
	 */
	public Producto obtenerProducto(String codigo) {
		return (Producto) listaProductos.stream()
				.filter(p ->p.getCodigo().equals(codigo))
				.findFirst()
				.orElse(null);
	}

//---------------------------A TENER EN CUENTA----------------------------------
		/**
		 * Estos dos metodos se llamaran eventualmente en el singleton y se va a hacer a traves de la opcion que ingresen en el combo box el tipo de producto a registrar
		 * Si quiere registrar un tipo de producto llama al metodo correspondiente y captura los datos necesarios y crea la intancia de productp que le va a mandar a este metodo
		 *
		 */

//-------------------------------------------------------------------------------



	/**
	 *
	 * @param newProducto
	 * @return
	 * @throws ProductoException
	 */
	public boolean crearProductoEnvasado(ProductoEnvasado newProducto) throws ProductoException{
		boolean creado= false;
		if (verificarProducto(newProducto.getCodigo())) {
			throw new ProductoException("El producto ya se encuentra registrado");
		}else {
			creado= true;
			 listaProductos.add(newProducto);
		}
		return creado;
	}
	/**
	 *
	 * @param newProducto
	 * @return
	 * @throws ProductoException
	 */
	public boolean crearProductoRefrigerado(ProductoRefrigerado newProducto) throws ProductoException{
		boolean creado= false;
		if (verificarProducto(newProducto.getCodigo())) {
			throw new ProductoException("El producto ya se encuentra registrado");
		}else {
			creado= true;
			 listaProductos.add(newProducto);
		}
		return creado;
	}
	/**
	 *
	 * @param newProducto
	 * @return
	 * @throws ProductoException
	 */
	public boolean crearProductoPerecedero(ProductoPerecedero newProducto) throws ProductoException{
		boolean creado= false;
		if (verificarProducto(newProducto.getCodigo())) {
			throw new ProductoException("El producto ya se encuentra registrado");
		}else {
			creado= true;
			 listaProductos.add(newProducto);
		}
		return creado;
	}
	/**
	 *
	 * @param productoEliminar
	 * @return
	 * @throws ProductoException
	 */
	public boolean eliminarProducto(Producto productoEliminar)throws ProductoException{
		boolean eliminado=false;
		if (obtenerProducto(productoEliminar.getCodigo()) == null ) {
			throw new ProductoException("El producto que desea eliminar no ha sido encontrado");
		}else {
			eliminado= true;
			listaProductos.remove(productoEliminar);
		}
		return eliminado;
	}
//------------------------CRUD DE LAS TRANSACCIONES---------------------------------------------------
	/**
	 *
	 * @param codigo
	 * @return
	 */
	public boolean verificarTransaccion(String codigo) {
		boolean verificado= false;
		for (Transaccion transaccionAux : listaTransacciones) {
			if (transaccionAux.getCodigo().equals(codigo)) {
				verificado=true;
			}
		}
		return verificado;
	}
	/**
	 *
	 * @param codigo
	 * @return
	 */
	public Transaccion obtenerTransaccion(String codigo) {
		for (Transaccion transaccionAux : listaTransacciones){
			if (transaccionAux.getCodigo().equals(codigo)) {
				return transaccionAux;
			}
		}
		return null;
	}
	/**
	 *
	 * @param newTransaccion
	 * @return
	 * @throws TransaccionException
	 */
	public boolean crearTransaccion(Transaccion newTransaccion) throws TransaccionException {
		boolean creado= false;
		if (verificarTransaccion(newTransaccion.getCodigo())) {
			throw new TransaccionException("La transaccion con el codigo " + newTransaccion.getCodigo() + " ya se encuentra registrada");
		}else {
			creado= true;
			listaTransacciones.add(newTransaccion);
		}
		return creado;
	}
	/**
	 *
	 * @param transaccionEliminar
	 * @return
	 * @throws TransaccionException
	 */
	public boolean eliminarTransaccion(Transaccion transaccionEliminar) throws TransaccionException{
		boolean eliminado= false;
		if (obtenerTransaccion(transaccionEliminar.getCodigo()) == null ) {
			throw new TransaccionException("La transaccion que desea elimnar no se encuentra registrada");
		}else {
			eliminado=true;
			listaTransacciones.remove(transaccionEliminar);
		}
		return eliminado;
	}



}
