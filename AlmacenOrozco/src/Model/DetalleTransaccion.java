package Model;

public class DetalleTransaccion {

	private int cantidad;
	private double subTotal;
	private Producto productoVendido;

	public DetalleTransaccion() {
		super();
	}

	/**
	 * @param cantidad
	 * @param subTotal
	 * @param productoVendido
	 */
	public DetalleTransaccion(int cantidad, double subTotal, Producto productoVendido) {
		super();
		this.cantidad = cantidad;
		this.subTotal = subTotal;
		this.productoVendido = productoVendido;
	}

	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public double getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}
	public Producto getProductoVendido() {
		return productoVendido;
	}
	public void setProductoVendido(Producto productoVendido) {
		this.productoVendido = productoVendido;
	}

	@Override
	public String toString() {
		return "Detalle Transaccion \nCantidad: " + cantidad + "\nSub Total: " + subTotal + "\nProducto Vendido: "+ productoVendido;
	}

}
