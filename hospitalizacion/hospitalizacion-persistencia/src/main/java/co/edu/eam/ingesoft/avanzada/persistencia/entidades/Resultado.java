package co.edu.eam.ingesoft.avanzada.persistencia.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="RESULTADO")
public class Resultado implements Serializable{

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="DESCRIPCION",nullable=false,length=50)
	private String descripcion;
	
	@Column(name="FECHA")
	@Temporal(TemporalType.DATE)
	private Date fechaResultado;
	
	@ManyToOne
	@JoinColumn(name="EXAMENES_CITA_ID")
	private ExamenesCita examenesCita;

	public Resultado() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaResultado() {
		return fechaResultado;
	}

	public void setFechaResultado(Date fechaResultado) {
		this.fechaResultado = fechaResultado;
	}

	public ExamenesCita getExamenesCita() {
		return examenesCita;
	}

	public void setExamenesCita(ExamenesCita examenesCita) {
		this.examenesCita = examenesCita;
	}
	
	
	
	
}
