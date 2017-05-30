package co.edu.eam.ingesoft.avanzada.persistencia.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="HORARIO")
@NamedQueries({ 
	@NamedQuery(name = Horario.CONSULTA_LISTAR_HORARIOS, query = "SELECT h FROM Horario h") 
	})
public class Horario implements Serializable{
	
	public static final String CONSULTA_LISTAR_HORARIOS = "Horario.ListarHorarios";
	
	@Id
	@Column(name="ID")
	private int id;
	
	@Column(name="FECHA")
	private Date fecha;
	
	@Column(name="HORA_INICIAL")
	private int horaInicial;
	
	@Column(name="HORA_FINAL")
	private int horaFinal;

	public Horario() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Horario(int id, Date fecha, int horaInicial, int horaFinal) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.horaInicial = horaInicial;
		this.horaFinal = horaFinal;
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

	@Override
	public String toString() {
		return fecha + " horaInicial=" + horaInicial + ", horaFinal=" + horaFinal;
	}
	
	
}
