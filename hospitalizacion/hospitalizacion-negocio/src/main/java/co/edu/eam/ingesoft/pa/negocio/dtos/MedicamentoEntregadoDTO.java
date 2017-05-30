package co.edu.eam.ingesoft.pa.negocio.dtos;

public class MedicamentoEntregadoDTO {
	
	private String medicamentoDescripcion;

	private String cantidad;

	
	public MedicamentoEntregadoDTO() {
		super();
	}

	/**
	 * @return the medicamentoDescripcion
	 */
	public String getMedicamentoDescripcion() {
		return medicamentoDescripcion;
	}

	/**
	 * @param medicamentoDescripcion the medicamentoDescripcion to set
	 */
	public void setMedicamentoDescripcion(String medicamentoDescripcion) {
		this.medicamentoDescripcion = medicamentoDescripcion;
	}

	/**
	 * @return the cantidad
	 */
	public String getCantidad() {
		return cantidad;
	}

	/**
	 * @param cantidad the cantidad to set
	 */
	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}
	
	

}
