package Model;

import java.util.ArrayList;
import java.util.List;

public class Transaccion {
	private String codigo;
	private String fecha;
	private Cliente clienteTransaccion;
	private double total;
	private float iva;
	private List<DetalleTransaccion> listaDetalles = new ArrayList<DetalleTransaccion>();


	/**
	 *
	 */
	public Transaccion() {
		super();
	}

	/**
	 * @param codigo
	 * @param fecha
	 * @param clienteTransaccion
	 * @param total
	 * @param iva
	 */
	public Transaccion(String codigo, String fecha, Cliente clienteTransaccion, double total, float iva) {
		super();
		this.codigo = codigo;
		this.fecha = fecha;
		this.clienteTransaccion = clienteTransaccion;
		this.total = total;
		this.iva = iva;
	}
	/**
	 * @param codigo
	 * @param fecha
	 * @param clienteTransaccion
	 * @param total
	 * @param iva
	 * @param listaDetalles
	 */
	public Transaccion(String codigo, String fecha, Cliente clienteTransaccion, double total, float iva,
			List<DetalleTransaccion> listaDetalles) {
		super();
		this.codigo = codigo;
		this.fecha = fecha;
		this.clienteTransaccion = clienteTransaccion;
		this.total = total;
		this.iva = iva;
		this.listaDetalles = listaDetalles;
	}

	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public Cliente getClienteTransaccion() {
		return clienteTransaccion;
	}
	public void setClienteTransaccion(Cliente clienteTransaccion) {
		this.clienteTransaccion = clienteTransaccion;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public float getIva() {
		return iva;
	}
	public void setIva(float iva) {
		this.iva = iva;
	}

	public List<DetalleTransaccion> getListaDetalles() {
		return listaDetalles;
	}

	public void setListaDetalles(List<DetalleTransaccion> listaDetalles) {
		this.listaDetalles = listaDetalles;
	}

	/**
	 *
	 * Se recorre la lista de detalles, se verifica que el producto asociado tenga suficiente cantidad para ser vendido, si es el caso, se llama a la funcion nuevaCantidad()
	 * La cual toma al producto en cuention y a la cantidad actual le resta la cantidad indicada en el destalle y se setea el valor en existencia del producto
	 * @return
	 */
	public boolean verificarDetalles() {
		boolean flag= false;
		for (DetalleTransaccion detalleTransaccionAux : listaDetalles) {
			if (detalleTransaccionAux.verificarProducto()) {
				int nuevaCantidad= detalleTransaccionAux.nuevaCantidad();
				detalleTransaccionAux.getProductoVendido().setCantidadExistencia(nuevaCantidad);
				flag= true;
				return flag;
			}
		}
		return flag;
	}


	@Override
	public String toString() {
		return "Transaccion \nCodigo=" + codigo + "\nFecha: " + fecha + "\nCliente Transaccion: " + clienteTransaccion
				+ "\nTotal: " + total + "\nIva: " + iva ;
	}


}
