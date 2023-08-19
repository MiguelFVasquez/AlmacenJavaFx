package Model;

public class ClienteJuridico extends Cliente {

	private String nit;

	/**
	 *
	 */
	public ClienteJuridico() {
		super();
	}

	/**
	 * @param nit
	 */
	public ClienteJuridico(String nit) {
		super();
		this.nit = nit;
	}

	/**
	 * @param nombre
	 * @param apellido
	 * @param identificacion
	 * @param direccion
	 * @param telefono
	 * @param nit
	 */
	public ClienteJuridico(String nombre, String apellido, String identificacion, String direccion, String telefono,
			String nit) {
		super(nombre, apellido, identificacion, direccion, telefono);
		this.nit = nit;
	}

	public String getNit() {
		return nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
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
		return super.toString()+ "\nCliente Juridico nit: " + nit ;
	}






}
