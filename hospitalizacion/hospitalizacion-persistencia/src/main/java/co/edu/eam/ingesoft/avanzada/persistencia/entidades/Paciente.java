package co.edu.eam.ingesoft.avanzada.persistencia.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import co.edu.eam.ingesoft.avanzada.persistencia.enumeraciones.GeneroEnum;


@Entity
@Table(name="T_PACIENTE")
public class Paciente implements Serializable{
	
	@Id
	@Column(name="num_identificacion", length=50)
	private int numIdentificacion;
	
	@Column(name="nombre", nullable=false, length=50)
	private String nombre;
	
	@Column(name="direccion", nullable=false, length=50)
	private String direccion;
	
	@Column(name="telefono", nullable=false, length=50)
	private String telefono;
	
	@JoinColumn(name="eps")
	@ManyToOne(cascade={})
	private Eps eps;
	
	@JoinColumn(name="municipio")
	@ManyToOne(cascade={})
	private Municipio municipio;
	
	@OneToOne
	@JoinColumn(name="usuario",unique=true)
	private Usuario usuario;
	
	@Column(name="fecha_nacimiento")
	private Date fechaNacimiento;
	
	@Column(name="email")
	private String email;
	
	@Column(name="genero")
	private String genero;

	public Paciente() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Paciente(int numIdentificacion, String nombre, String direccion, String telefono, Eps eps,
			Municipio municipio, Usuario usuario, Date fechaNacimiento, String email, String genero) {
		super();
		this.numIdentificacion = numIdentificacion;
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefono = telefono;
		this.eps = eps;
		this.municipio = municipio;
		this.usuario = usuario;
		this.fechaNacimiento = fechaNacimiento;
		this.email = email;
		this.genero = genero;
	}

	public int getNumIdentificacion() {
		return numIdentificacion;
	}

	public void setNumIdentificacion(int numIdentificacion) {
		this.numIdentificacion = numIdentificacion;
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

	public Eps getEps() {
		return eps;
	}

	public void setEps(Eps eps) {
		this.eps = eps;
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

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}
	
}