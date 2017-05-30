package co.edu.eam.ingesoft.pa.negocio.dtos;

public class ExamenHistorialDTO {

	private String nombreExamen;
	
	private String descripcionExamen;

	
	/**
	 * @return the nombreExamen
	 */
	public String getNombreExamen() {
		return nombreExamen;
	}




	/**
	 * @param nombreExamen the nombreExamen to set
	 */
	public void setNombreExamen(String nombreExamen) {
		this.nombreExamen = nombreExamen;
	}




	/**
	 * @return the descripcionExamen
	 */
	public String getDescripcionExamen() {
		return descripcionExamen;
	}




	/**
	 * @param descripcionExamen the descripcionExamen to set
	 */
	public void setDescripcionExamen(String descripcionExamen) {
		this.descripcionExamen = descripcionExamen;
	}




	public ExamenHistorialDTO() {
		super();
	}
	
	
	
}
