package Model;

public class Cliente extends Persona {

	private String direccion;
	private String telefono;
	/**
	 *
	 */
	public Cliente() {
		super();
	}
	/**
	 * @param direccion
	 * @param telefono
	 */
	public Cliente(String direccion, String telefono) {
		super();
		this.direccion = direccion;
		this.telefono = telefono;
	}
	/**
	 * @param nombre
	 * @param apellido
	 * @param identificacion
	 * @param direccion
	 * @param telefono
	 */
	public Cliente(String nombre, String apellido, String identificacion, String direccion, String telefono) {
		super(nombre, apellido, identificacion);
		this.direccion = direccion;
		this.telefono = telefono;
	}


	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}
	@Override
	public String toString() {
		return super.toString()+"Cliente direccion=" + direccion + ", telefono=" + telefono + "]";
	}



}
