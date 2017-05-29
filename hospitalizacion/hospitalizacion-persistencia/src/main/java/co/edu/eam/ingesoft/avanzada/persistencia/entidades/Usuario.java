package co.edu.eam.ingesoft.avanzada.persistencia.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="USUARIO")
@NamedQueries({
@NamedQuery(name="Usuario.buscarXUser",query="SELECT u FROM Usuario u WHERE u.user=?1")
})
public class Usuario implements Serializable {

	public static final String BUSCAR_POR_USUARIO = "Usuario.buscarXUser";
	
	@Id
	@Column(unique=true,name="USUARIO")
	private String user;
	
	@Column(name="PASSWORD")
	private String password;
	
	@ManyToOne
	@JoinColumn(name="ROL_ID")
	private Rol rol;
	
	public Usuario() {
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}
}
