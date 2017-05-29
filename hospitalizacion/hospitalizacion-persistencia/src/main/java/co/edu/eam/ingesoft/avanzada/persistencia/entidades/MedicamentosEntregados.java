package co.edu.eam.ingesoft.avanzada.persistencia.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="MEDICAMENTOS_ENTREGADOS")
public class MedicamentosEntregados implements Serializable{

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="CANTIDAD_ENTREGADA", nullable=false)
	private String cantidadEntregada;

	public MedicamentosEntregados() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MedicamentosEntregados(int id, String cantidadEntregada) {
		super();
		this.id = id;
		this.cantidadEntregada = cantidadEntregada;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCantidadEntregada() {
		return cantidadEntregada;
	}

	public void setCantidadEntregada(String cantidadEntregada) {
		this.cantidadEntregada = cantidadEntregada;
	}
	
}
