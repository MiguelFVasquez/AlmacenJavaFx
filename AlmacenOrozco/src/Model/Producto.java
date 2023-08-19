package Model;

public class Producto {
	private String codigo;
	private String nombre;
	private String descripcion;
	private double valorUnitario;
	private int cantidadExistencia;
	private TipoProducto tipoProducto;


	/**
	 *
	 */
	public Producto() {
		super();
	}



	/**
	 * @param codigo
	 * @param nombre
	 * @param descripcion
	 * @param valorUnitario
	 * @param cantidadExistencia
	 * @param tipoProducto
	 */
	public Producto(String codigo, String nombre, String descripcion, double valorUnitario, int cantidadExistencia,
			TipoProducto tipoProducto) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.valorUnitario = valorUnitario;
		this.cantidadExistencia = cantidadExistencia;
		this.tipoProducto = tipoProducto;
	}




	public String getCodigo() {
		return codigo;
	}


	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public int getCantidadExistencia() {
		return cantidadExistencia;
	}

	public void setCantidadExistencia(int cantidadExistencia) {
		this.cantidadExistencia = cantidadExistencia;
	}


	public TipoProducto getTipoProducto() {
		return tipoProducto;
	}

	public void setTipoProducto(TipoProducto tipoProducto) {
		this.tipoProducto = tipoProducto;
	}

	/**
	 *
	 * @return
	 */
	public boolean verificarCantidad(){
		if (this.cantidadExistencia<=0) {
			return false;
		}
		return true;
	}



	@Override
	public String toString() {
		return "Producto \nCodigo: " + codigo + "\nNombre: " + nombre + "\nDescripcion: " + descripcion + "\nValor Unitario: "+ valorUnitario +
				"\nCantidad Existencia: " + cantidadExistencia;
	}


}
