package co.edu.eam.ingesoft.pa.negocio.dtos;

import java.util.Date;

public class HorarioMedicoDTO {

	private int medico;
	
	private int horario;
	
	private String nombre;
	
	private Date fecha;
	
	private int horaInicial;
	
	private int horaFinal;

	public HorarioMedicoDTO(int medico, int horario, String nombre, Date fecha, int horaInicial, int horaFinal) {
		super();
		this.medico = medico;
		this.horario = horario;
		this.nombre = nombre;
		this.fecha = fecha;
		this.horaInicial = horaInicial;
		this.horaFinal = horaFinal;
	}

	public HorarioMedicoDTO() {
		super();
	}

	public int getMedico() {
		return medico;
	}

	public void setMedico(int medico) {
		this.medico = medico;
	}

	public int getHorario() {
		return horario;
	}

	public void setHorario(int horario) {
		this.horario = horario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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
		
}
