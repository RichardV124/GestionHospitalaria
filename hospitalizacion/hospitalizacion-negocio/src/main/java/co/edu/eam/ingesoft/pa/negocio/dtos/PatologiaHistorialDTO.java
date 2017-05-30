package co.edu.eam.ingesoft.pa.negocio.dtos;

public class PatologiaHistorialDTO {

	private String nombre;
	
	private String sintomas;
	
	
	public PatologiaHistorialDTO() {
		super();
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the sintomas
	 */
	public String getSintomas() {
		return sintomas;
	}

	/**
	 * @param sintomas the sintomas to set
	 */
	public void setSintomas(String sintomas) {
		this.sintomas = sintomas;
	}
	
	
	
	
}
