package co.edu.eam.ingesoft.pa.negocio.dtos;

public class MedicoDisponibleDTO {

	private int cedula;
	
	private String nombre;

	public MedicoDisponibleDTO(int cedula, String nombre) {
		super();
		this.cedula = cedula;
		this.nombre = nombre;
	}

	public MedicoDisponibleDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getCedula() {
		return cedula;
	}

	public void setCedula(int cedula) {
		this.cedula = cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
