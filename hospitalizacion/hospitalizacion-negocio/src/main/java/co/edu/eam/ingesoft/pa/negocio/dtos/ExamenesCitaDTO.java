package co.edu.eam.ingesoft.pa.negocio.dtos;

public class ExamenesCitaDTO {

	private int id;
	
	private int idCita;
	
	private String nombreExamen;
	
	private int idTipoExamen;
	
	private String descripcionTipoExamen;

	
	public ExamenesCitaDTO() {
		super();
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the idCita
	 */
	public int getIdCita() {
		return idCita;
	}

	/**
	 * @param idCita the idCita to set
	 */
	public void setIdCita(int idCita) {
		this.idCita = idCita;
	}

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
	 * @return the idTipoExamen
	 */
	public int getIdTipoExamen() {
		return idTipoExamen;
	}

	/**
	 * @param idTipoExamen the idTipoExamen to set
	 */
	public void setIdTipoExamen(int idTipoExamen) {
		this.idTipoExamen = idTipoExamen;
	}

	/**
	 * @return the descripcionTipoExamen
	 */
	public String getDescripcionTipoExamen() {
		return descripcionTipoExamen;
	}

	/**
	 * @param descripcionTipoExamen the descripcionTipoExamen to set
	 */
	public void setDescripcionTipoExamen(String descripcionTipoExamen) {
		this.descripcionTipoExamen = descripcionTipoExamen;
	}	
	
	
}
