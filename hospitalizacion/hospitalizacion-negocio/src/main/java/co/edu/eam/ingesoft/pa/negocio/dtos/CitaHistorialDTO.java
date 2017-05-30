package co.edu.eam.ingesoft.pa.negocio.dtos;

public class CitaHistorialDTO {

	private String idCita;
	
	private String nombrePaciente;
	
	private String fecha;
	
	private int hora;
	
	private String nombreMedico;

	private String anotaciones;
	
	public CitaHistorialDTO() {
		super();
	}

	/**
	 * @return the nombrePaciente
	 */
	public String getNombrePaciente() {
		return nombrePaciente;
	}

	/**
	 * @param nombrePaciente the nombrePaciente to set
	 */
	public void setNombrePaciente(String nombrePaciente) {
		this.nombrePaciente = nombrePaciente;
	}

	/**
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return the hora
	 */
	public int getHora() {
		return hora;
	}

	/**
	 * @param hora the hora to set
	 */
	public void setHora(int hora) {
		this.hora = hora;
	}

	/**
	 * @return the nombreMedico
	 */
	public String getNombreMedico() {
		return nombreMedico;
	}

	/**
	 * @param nombreMedico the nombreMedico to set
	 */
	public void setNombreMedico(String nombreMedico) {
		this.nombreMedico = nombreMedico;
	}
	
	/**
	 * @return the anotaciones
	 */
	public String getAnotaciones() {
		return anotaciones;
	}

	/**
	 * @param anotaciones the anotaciones to set
	 */
	public void setAnotaciones(String anotaciones) {
		this.anotaciones = anotaciones;
	}

	/**
	 * @return the idCita
	 */
	public String getIdCita() {
		return idCita;
	}

	/**
	 * @param idCita the idCita to set
	 */
	public void setIdCita(String idCita) {
		this.idCita = idCita;
	}
	
	
}
