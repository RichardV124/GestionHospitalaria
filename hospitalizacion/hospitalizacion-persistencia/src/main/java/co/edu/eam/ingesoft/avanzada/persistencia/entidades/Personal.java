package co.edu.eam.ingesoft.avanzada.persistencia.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="PERSONAL")
public class Personal {

	@Id
	@Column(name="CEDULA")
	private int cedula;
	
	@Column(name="NOMBRE",nullable=false,length=50)
	private String nombre;
	
	@ManyToOne
	@JoinColumn(name="FARMACIA_ID")
	private Farmacia farmacia;
	
	@OneToOne
	@JoinColumn(name="USUARIO_USUARIO",unique=true)
	private Usuario usuario;

	public Personal() {
		super();
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

	public Farmacia getFarmacia() {
		return farmacia;
	}

	public void setFarmacia(Farmacia farmacia) {
		this.farmacia = farmacia;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	
	
}
