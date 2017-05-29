package co.edu.eam.ingesoft.pa.negocio.dtos;

public class FarmaciaMedicamentoDTO {

	private int idMedicamento;
	
	private String medicamento;
	
	private int cantidad;
	
	private String farmacia;
	
	private int idFarmacia;

	public FarmaciaMedicamentoDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FarmaciaMedicamentoDTO(int idMedicamento, String medicamento, int cantidad, String farmacia,
			int idFarmacia) {
		super();
		this.idMedicamento = idMedicamento;
		this.medicamento = medicamento;
		this.cantidad = cantidad;
		this.farmacia = farmacia;
		this.idFarmacia = idFarmacia;
	}

	public int getIdMedicamento() {
		return idMedicamento;
	}

	public void setIdMedicamento(int idMedicamento) {
		this.idMedicamento = idMedicamento;
	}

	public String getMedicamento() {
		return medicamento;
	}

	public void setMedicamento(String medicamento) {
		this.medicamento = medicamento;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getFarmacia() {
		return farmacia;
	}

	public void setFarmacia(String farmacia) {
		this.farmacia = farmacia;
	}

	public int getIdFarmacia() {
		return idFarmacia;
	}

	public void setIdFarmacia(int idFarmacia) {
		this.idFarmacia = idFarmacia;
	}

	
}