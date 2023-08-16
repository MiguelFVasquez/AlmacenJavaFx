package Model;

public class ProductoRefrigerado extends Producto {
	private String codigoAprobacion;
	private String temperaturaRecomendada;


	/**
	 *
	 */
	public ProductoRefrigerado() {
		super();
	}


	/**
	 * @param codigo
	 * @param nombre
	 * @param descripcion
	 * @param valorUnitario
	 * @param cantidadExistencia
	 * @param tipoProducto
	 * @param codigoAprobacion
	 * @param temperaturaRecomendada
	 */
	public ProductoRefrigerado(String codigo, String nombre, String descripcion, double valorUnitario,
			int cantidadExistencia, TipoProducto tipoProducto, String codigoAprobacion, String temperaturaRecomendada) {
		super(codigo, nombre, descripcion, valorUnitario, cantidadExistencia, tipoProducto);
		this.codigoAprobacion = codigoAprobacion;
		this.temperaturaRecomendada = temperaturaRecomendada;
	}

	/**
	 * @param codigoAprobacion
	 * @param temperaturaRecomendada
	 */
	public ProductoRefrigerado(String codigoAprobacion, String temperaturaRecomendada) {
		super();
		this.codigoAprobacion = codigoAprobacion;
		this.temperaturaRecomendada = temperaturaRecomendada;
	}

	public String getCodigoAprobacion() {
		return codigoAprobacion;
	}
	public void setCodigoAprobacion(String codigoAprobacion) {
		this.codigoAprobacion = codigoAprobacion;
	}
	public String getTemperaturaRecomendada() {
		return temperaturaRecomendada;
	}
	public void setTemperaturaRecomendada(String temperaturaRecomendada) {
		this.temperaturaRecomendada = temperaturaRecomendada;
	}

	@Override
	public String toString() {
		return super.toString()+ "codigo Aprobacion: " + codigoAprobacion + "temperatura Recomendada: "+ temperaturaRecomendada;
	}






}
