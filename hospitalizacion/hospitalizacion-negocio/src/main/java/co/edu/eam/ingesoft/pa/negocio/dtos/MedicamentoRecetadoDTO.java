package co.edu.eam.ingesoft.pa.negocio.dtos;

public class MedicamentoRecetadoDTO {

	private int id;
	
	private String medicamento;
	
	private int cantidad;
	
	private String dosis;

	public MedicamentoRecetadoDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MedicamentoRecetadoDTO(int id, String medicamento, int cantidad, String dosis) {
		super();
		this.id = id;
		this.medicamento = medicamento;
		this.cantidad = cantidad;
		this.dosis = dosis;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getDosis() {
		return dosis;
	}

	public void setDosis(String dosis) {
		this.dosis = dosis;
	}
	
}
