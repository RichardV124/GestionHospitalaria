package co.edu.eam.ingesoft.avanzada.persistencia.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@IdClass(FarmaciaMedicamentoPK.class)
@Table(name="FARMACIA_MEDICAMENTO")
public class FarmaciaMedicamento implements Serializable{
	
	@Id
	@JoinColumn(name="FARMACIA_ID")
	@ManyToOne(cascade={})
	private Farmacia farmacia;
	
	@Id
	@JoinColumn(name="MEDICAMENTO_ID")
	@ManyToOne(cascade={})
	private Medicamento medicamento;
	
	@Column(name="CANTIDAD")
	private int cantidad;

	public FarmaciaMedicamento() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FarmaciaMedicamento(Farmacia farmacia, Medicamento medicamento, int cantidad) {
		super();
		this.farmacia = farmacia;
		this.medicamento = medicamento;
		this.cantidad = cantidad;
	}

	public Farmacia getFarmacia() {
		return farmacia;
	}

	public void setFarmacia(Farmacia farmacia) {
		this.farmacia = farmacia;
	}

	public Medicamento getMedicamento() {
		return medicamento;
	}

	public void setMedicamento(Medicamento medicamento) {
		this.medicamento = medicamento;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
}
