package Model;

public class ProductoPerecedero extends Producto {
	private String fechaVencimiento;

	public ProductoPerecedero() {
		super();
	}

	/**
	 * @param fechaVencimiento
	 */
	public ProductoPerecedero(String fechaVencimiento) {
		super();
		this.fechaVencimiento = fechaVencimiento;
	}

	/**
	 * @param codigo
	 * @param nombre
	 * @param descripcion
	 * @param valorUnitario
	 * @param cantidadExistencia
	 * @param tipoProducto
	 * @param fechaVencimiento
	 */
	public ProductoPerecedero(String codigo, String nombre, String descripcion, double valorUnitario,
			int cantidadExistencia, TipoProducto tipoProducto, String fechaVencimiento) {
		super(codigo, nombre, descripcion, valorUnitario, cantidadExistencia, tipoProducto);
		this.fechaVencimiento = fechaVencimiento;
	}

	public String getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	@Override
	public String toString() {
		return super.toString()+ "\nFecha Vencimiento: " + fechaVencimiento;
	}





}
