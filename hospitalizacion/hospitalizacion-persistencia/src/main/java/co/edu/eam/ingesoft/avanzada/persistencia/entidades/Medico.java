package co.edu.eam.ingesoft.avanzada.persistencia.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="MEDICO")
@NamedQueries({ 
	@NamedQuery(name = Medico.CONSULTA_LISTAR_MEDICOS, query = "SELECT m FROM Medico m") 
	})
public class Medico implements Serializable{

	public static final String CONSULTA_LISTAR_MEDICOS = "Medico.ListarMedicos";
	
	@Id
	@Column(name="CEDULA")
	private String cedula;
	
	@Column(name="NOMBRE")
	private	String nombre;
	
	@Column(name="DIRECCION")
	private String direccion;
	
	@Column(name="TELEFONO")
	private String telefono;
	
	@JoinColumn(name="TIPO_MEDICO_ID")
	@ManyToOne
	private TipoMedico tipoMedico;
	
	
	@JoinColumn(name="MUNICIPIO_ID")
	@ManyToOne
	private Municipio municipio;

	@OneToOne
	@JoinColumn(name="USUARIO_USUARIO")
	private Usuario usuario;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="GENERO")
	private String genero;
	
	@Column(name="FECHA_NACIMIENTO")
	private Date fechaNacimiento;
	
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

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	
	
}
