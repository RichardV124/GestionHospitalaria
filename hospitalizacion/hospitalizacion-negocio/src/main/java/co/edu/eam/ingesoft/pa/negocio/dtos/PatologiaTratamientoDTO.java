package co.edu.eam.ingesoft.pa.negocio.dtos;

public class PatologiaTratamientoDTO {

	
	private int idTratamiento;
	
	private int idPatologia;
	
	private String tratamiento;
	
	private String patologia;

	
	public PatologiaTratamientoDTO() {
		super();
	}

	public String getTratamiento() {
		return tratamiento;
	}

	public void setTratamiento(String tratamiento) {
		this.tratamiento = tratamiento;
	}

	public String getPatologia() {
		return patologia;
	}

	public void setPatologia(String patologia) {
		this.patologia = patologia;
	}

	public int getIdTratamiento() {
		return idTratamiento;
	}

	public void setIdTratamiento(int idTratamiento) {
		this.idTratamiento = idTratamiento;
	}

	public int getIdPatologia() {
		return idPatologia;
	}

	public void setIdPatologia(int idPatologia) {
		this.idPatologia = idPatologia;
	}
	
	
	
}
