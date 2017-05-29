package co.edu.eam.ingesoft.pa.negocio.dtos;

public class CitaCirugiaDTO {

	private int cirugiaId;
	private int citaId;
	private String cirugiaDescripcion;
	private int tipoCirugiaId;
	private String tipoCirugiaDescripcion;
	private int quirofanoId;
	private String quirofanoNombre;
	private int cedulaMedico;
	private String nombreMedico;

	public CitaCirugiaDTO() {
		super();
	}

	/**
	 * @return the cirugiaId
	 */
	public int getCirugiaId() {
		return cirugiaId;
	}

	/**
	 * @param cirugiaId
	 *            the cirugiaId to set
	 */
	public void setCirugiaId(int cirugiaId) {
		this.cirugiaId = cirugiaId;
	}

	/**
	 * @return the citaId
	 */
	public int getCitaId() {
		return citaId;
	}

	/**
	 * @param citaId
	 *            the citaId to set
	 */
	public void setCitaId(int citaId) {
		this.citaId = citaId;
	}

	/**
	 * @return the cirugiaDescripcion
	 */
	public String getCirugiaDescripcion() {
		return cirugiaDescripcion;
	}

	/**
	 * @param cirugiaDescripcion
	 *            the cirugiaDescripcion to set
	 */
	public void setCirugiaDescripcion(String cirugiaDescripcion) {
		this.cirugiaDescripcion = cirugiaDescripcion;
	}

	/**
	 * @return the tipoCirugiaId
	 */
	public int getTipoCirugiaId() {
		return tipoCirugiaId;
	}

	/**
	 * @param tipoCirugiaId
	 *            the tipoCirugiaId to set
	 */
	public void setTipoCirugiaId(int tipoCirugiaId) {
		this.tipoCirugiaId = tipoCirugiaId;
	}

	/**
	 * @return the tipoCirugiaDescripcion
	 */
	public String getTipoCirugiaDescripcion() {
		return tipoCirugiaDescripcion;
	}

	/**
	 * @param tipoCirugiaDescripcion
	 *            the tipoCirugiaDescripcion to set
	 */
	public void setTipoCirugiaDescripcion(String tipoCirugiaDescripcion) {
		this.tipoCirugiaDescripcion = tipoCirugiaDescripcion;
	}

	/**
	 * @return the quirofanoId
	 */
	public int getQuirofanoId() {
		return quirofanoId;
	}

	/**
	 * @param quirofanoId
	 *            the quirofanoId to set
	 */
	public void setQuirofanoId(int quirofanoId) {
		this.quirofanoId = quirofanoId;
	}

	/**
	 * @return the quirofanoNombre
	 */
	public String getQuirofanoNombre() {
		return quirofanoNombre;
	}

	/**
	 * @param quirofanoNombre
	 *            the quirofanoNombre to set
	 */
	public void setQuirofanoNombre(String quirofanoNombre) {
		this.quirofanoNombre = quirofanoNombre;
	}

	/**
	 * @return the cedulaMedico
	 */
	public int getCedulaMedico() {
		return cedulaMedico;
	}

	/**
	 * @param cedulaMedico
	 *            the cedulaMedico to set
	 */
	public void setCedulaMedico(int cedulaMedico) {
		this.cedulaMedico = cedulaMedico;
	}

	/**
	 * @return the nombreMedico
	 */
	public String getNombreMedico() {
		return nombreMedico;
	}

	/**
	 * @param nombreMedico
	 *            the nombreMedico to set
	 */
	public void setNombreMedico(String nombreMedico) {
		this.nombreMedico = nombreMedico;
	}

}
