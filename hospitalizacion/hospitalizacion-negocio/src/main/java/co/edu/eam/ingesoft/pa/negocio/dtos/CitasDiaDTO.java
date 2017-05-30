package co.edu.eam.ingesoft.pa.negocio.dtos;

import java.util.Date;

public class CitasDiaDTO {

	private int id;
	
	private String estado;
	
	private int horaInicial;
	
	private int horaFinal;
	
	private String paciente;
	
	private int cedula;

	public CitasDiaDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CitasDiaDTO(int id, String estado, int horaInicial, int horaFinal, String paciente, int cedula) {
		super();
		this.id = id;
		this.estado = estado;
		this.horaInicial = horaInicial;
		this.horaFinal = horaFinal;
		this.paciente = paciente;
		this.cedula = cedula;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public int getHoraInicial() {
		return horaInicial;
	}

	public void setHoraInicial(int horaInicial) {
		this.horaInicial = horaInicial;
	}

	public int getHoraFinal() {
		return horaFinal;
	}

	public void setHoraFinal(int horaFinal) {
		this.horaFinal = horaFinal;
	}

	public String getPaciente() {
		return paciente;
	}

	public void setPaciente(String paciente) {
		this.paciente = paciente;
	}

	public int getCedula() {
		return cedula;
	}

	public void setCedula(int cedula) {
		this.cedula = cedula;
	}
	
}
