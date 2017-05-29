package co.edu.eam.ingesoft.pa.negocio.dtos;


public class CamaDTO {
	
	private int id;

	private String piso;

	private String habitacion;
	
	private String ocupadoPor;

	private String descripcion;

	public CamaDTO() {
		super();
	}

	public CamaDTO(int id, String piso, String habitacion, String ocupadoPor, String descripcion) {
		super();
		this.id = id;
		this.piso = piso;
		this.habitacion = habitacion;
		this.ocupadoPor = ocupadoPor;
		this.descripcion = descripcion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPiso() {
		return piso;
	}

	public void setPiso(String piso) {
		this.piso = piso;
	}

	public String getHabitacion() {
		return habitacion;
	}

	public void setHabitacion(String habitacion) {
		this.habitacion = habitacion;
	}

	public String getOcupadoPor() {
		return ocupadoPor;
	}

	public void setOcupadoPor(String ocupadoPor) {
		this.ocupadoPor = ocupadoPor;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	
	
}
