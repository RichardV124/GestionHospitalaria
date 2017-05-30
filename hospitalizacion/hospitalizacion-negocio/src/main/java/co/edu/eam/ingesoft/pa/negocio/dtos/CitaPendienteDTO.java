package co.edu.eam.ingesoft.pa.negocio.dtos;

import java.util.Date;

public class CitaPendienteDTO {

	private int id;
	
	private Date fecha;
	
	private int horaInicial;
	
	private int horaFinal;
	
	private String medico;
	
	private String caracter;

	public CitaPendienteDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CitaPendienteDTO(int id, Date fecha, int horaInicial, int horaFinal, String medico, String caracter) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.horaInicial = horaInicial;
		this.horaFinal = horaFinal;
		this.medico = medico;
		this.caracter = caracter;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
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

	public String getMedico() {
		return medico;
	}

	public void setMedico(String medico) {
		this.medico = medico;
	}

	public String getCaracter() {
		return caracter;
	}

	public void setCaracter(String caracter) {
		this.caracter = caracter;
	}	

}
