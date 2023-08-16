package Model;

public class ClienteNatural extends Cliente{
	private String email;
	private String fechaNacimiento;
	/**
	 *
	 */
	public ClienteNatural() {
		super();
	}
	/**
	 * @param email
	 * @param fechaNacimiento
	 */
	public ClienteNatural(String email, String fechaNacimiento) {
		super();
		this.email = email;
		this.fechaNacimiento = fechaNacimiento;
	}
	/**
	 * @param nombre
	 * @param apellido
	 * @param identificacion
	 * @param direccion
	 * @param telefono
	 * @param email
	 * @param fechaNacimiento
	 */
	public ClienteNatural(String nombre, String apellido, String identificacion, String direccion, String telefono,
			String email, String fechaNacimiento) {
		super(nombre, apellido, identificacion, direccion, telefono);
		this.email = email;
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
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
		return "ClienteNatural [email=" + email + ", fechaNacimiento=" + fechaNacimiento + "]";
	}






}
