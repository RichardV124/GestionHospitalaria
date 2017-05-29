package co.edu.eam.ingesoft.avanzada.persistencia.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="HOSPITALIZACION")
public class Hospitalizacion implements Serializable{

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="CAMA_ID")
	private Cama cama;
	
	@Column(name="FECHA_SALIDA")
	@Temporal(TemporalType.TIME)
	private Date fechaEntrada;
	
	@Column(name="FECHA_ENTRADA")
	@Temporal(TemporalType.TIME)
	private Date fechaSalida;
	
	@OneToOne
	@JoinColumn(name="RESULTADO_ID",nullable=true)
	private Resultado resultado;
	
	@OneToOne
	@JoinColumns({ @JoinColumn(name = "CITA_CIRUGIA_CITA_ID", referencedColumnName = "CITA_ID",nullable=true),
			@JoinColumn(name = "CITA_CIRU_CIRU_ID", referencedColumnName = "CIRUGIA_ID",nullable=true) })
	private CitaCirugia citaCirugia;

	public Hospitalizacion() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Cama getCama() {
		return cama;
	}

	public void setCama(Cama cama) {
		this.cama = cama;
	}

	public Date getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(Date fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public Date getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public Resultado getResultado() {
		return resultado;
	}

	public void setResultado(Resultado resultado) {
		this.resultado = resultado;
	}

	public CitaCirugia getCitaCirugia() {
		return citaCirugia;
	}

	public void setCitaCirugia(CitaCirugia citaCirugia) {
		this.citaCirugia = citaCirugia;
	}
	
	
	
}
