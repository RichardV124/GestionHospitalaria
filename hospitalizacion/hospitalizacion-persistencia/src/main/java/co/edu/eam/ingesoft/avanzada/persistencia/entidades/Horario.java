package co.edu.eam.ingesoft.avanzada.persistencia.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="T_HORARIO")
public class Horario implements Serializable{
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="dia")
	private int dia;
	
	@Column(name="hora_inicial")
	private int horaInicial;
	
	@Column(name="horaFinal")
	private int horaFinal;

	public Horario() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Horario(int id, int dia, int horaInicial, int horaFinal) {
		super();
		this.id = id;
		this.dia = dia;
		this.horaInicial = horaInicial;
		this.horaFinal = horaFinal;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
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
