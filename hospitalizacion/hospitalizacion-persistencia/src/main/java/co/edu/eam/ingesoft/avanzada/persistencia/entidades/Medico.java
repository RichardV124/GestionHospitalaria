package co.edu.eam.ingesoft.avanzada.persistencia.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

public class Medico implements Serializable{

	@Id
	@Column(name="cedula")
	private String cedula;
	
	@Column(name="nombre")
	private	String nombre;
	
	@Column(name="direccion")
	private String direccion;
	
	@Column(name="telefono")
	private String telefono;
	
	@Column(name="id_tipomedico")
	@ManyToOne
	private TipoMedico tipoMedico;
	
	
	@JoinColumn(name="id_municipio")
	@ManyToOne
	private Municipio municipio;

	@OneToOne
	@JoinColumn(name="usuario")
	private Usuario usuario;
	
	public Medico() {
		super();
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}
	
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public TipoMedico getTipoMedico() {
		return tipoMedico;
	}

	public void setTipoMedico(TipoMedico tipoMedico) {
		this.tipoMedico = tipoMedico;
	}

	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
