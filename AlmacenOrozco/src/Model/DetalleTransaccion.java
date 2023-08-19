package Model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

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

	public boolean verificarProducto(){
		return this.productoVendido.verificarCantidad();
	}

	public int nuevaCantidad(){
		return this.productoVendido.getCantidadExistencia() - this.cantidad;
	}

    public DoubleProperty getSubtotalProperty() {
        return new SimpleDoubleProperty(subTotal);
    }



	@Override
	public String toString() {
		return "Detalle Transaccion \nCantidad: " + cantidad + "\nSub Total: " + subTotal + "\nProducto Vendido: "+ productoVendido;
	}

}
