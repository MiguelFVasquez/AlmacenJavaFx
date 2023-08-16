package Model;

public class ProductoEnvasado extends Producto{
	private String fechaEnvasado;
	private String pesoEnvase;
	private TipoPaisOrigen paisOrigen;


	public ProductoEnvasado() {
		super();
	}
	/**
	 * @param fechaEnvasado
	 * @param pesoEnvase
	 * @param paisOrigen
	 */
	public ProductoEnvasado(String fechaEnvasado, String pesoEnvase, TipoPaisOrigen paisOrigen) {
		super();
		this.fechaEnvasado = fechaEnvasado;
		this.pesoEnvase = pesoEnvase;
		this.paisOrigen = paisOrigen;
	}


	/**
	 * @param codigo
	 * @param nombre
	 * @param descripcion
	 * @param valorUnitario
	 * @param cantidadExistencia
	 * @param tipoProducto
	 * @param fechaEnvasado
	 * @param pesoEnvase
	 * @param paisOrigen
	 */
	public ProductoEnvasado(String codigo, String nombre, String descripcion, double valorUnitario,
			int cantidadExistencia, TipoProducto tipoProducto, String fechaEnvasado, String pesoEnvase,
			TipoPaisOrigen paisOrigen) {
		super(codigo, nombre, descripcion, valorUnitario, cantidadExistencia, tipoProducto);
		this.fechaEnvasado = fechaEnvasado;
		this.pesoEnvase = pesoEnvase;
		this.paisOrigen = paisOrigen;
	}
	public String getFechaEnvasado() {
		return fechaEnvasado;
	}
	public void setFechaEnvasado(String fechaEnvasado) {
		this.fechaEnvasado = fechaEnvasado;
	}
	public String getPesoEnvase() {
		return pesoEnvase;
	}
	public void setPesoEnvase(String pesoEnvase) {
		this.pesoEnvase = pesoEnvase;
	}
	public TipoPaisOrigen getPaisOrigen() {
		return paisOrigen;
	}
	public void setPaisOrigen(TipoPaisOrigen paisOrigen) {
		this.paisOrigen = paisOrigen;
	}
	@Override
	public String toString() {
		return super.toString()+ "\nfecha Envasado: " + fechaEnvasado + "\nPeso del envase=" + pesoEnvase + "\nPais Origen: "+ paisOrigen ;
	}




}
