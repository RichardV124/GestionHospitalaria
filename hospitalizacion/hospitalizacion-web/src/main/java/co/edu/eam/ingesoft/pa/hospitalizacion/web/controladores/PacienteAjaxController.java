package co.edu.eam.ingesoft.pa.hospitalizacion.web.controladores;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Messages;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Departamento;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Eps;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Municipio;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Paciente;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Rol;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Usuario;
import co.edu.eam.ingesoft.pa.negocio.beans.DepartamentoEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.EpsEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.MunicipioEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.PacienteEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.RolEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.UsuarioEJB;
import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;


@Named("pacienteAjaxController")
@ViewScoped
public class PacienteAjaxController implements Serializable {
	
	@EJB
	private	PacienteEJB pacienteEJB;
	
	@EJB
	private RolEJB rolEJB;
	
	@EJB
	private EpsEJB epsEJB;
	
	@EJB
	private MunicipioEJB municipioEJB;
	
	@EJB
	private DepartamentoEJB departamentoEJB;
	
	@EJB
	private UsuarioEJB usuarioEJB;
	
	private String generoSeleccionado;
	
	@NotNull(message = "Debe ingresar el nombre")
	private String nombre;
	
	private String busNumeroDocumento;
	
	@NotNull(message = "Debe ingresar el numero de documento")
	private String numeroDocumento;
	
	@NotNull(message = "Debe ingresar la direccion")
	private String direccion;
	
	private int epsSeleccionada;
	
	private List<Eps> listaEps;
	
	private int municipioSeleccionado;
	
	private List<Municipio> listaMunicipios;
	
	private int dptoSeleccionado;
	
	private List<Departamento> listaDptos;
	
	@NotNull(message = "Debe ingresar la fecha")
	private String fecha;
	
	@NotNull(message = "Debe ingresar el telefono")
	private String telefono;

	@NotNull(message = "Debe ingresar el email")
	private String email;
	
	private List<Paciente> pacientes;
	
	private Date fechaNacimiento;
	
	private String usuario;
	
	private String password;
	
	@PostConstruct
	public void inicializar(){
		try {
			listarCombos();
			//pacientes = pacienteEJB.listarPacietes();
			
		} catch (ExcepcionNegocio e1) {
			Messages.addFlashGlobalError(e1.getMessage());
			e1.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Messages.addFlashGlobalInfo(e.getMessage());
			e.printStackTrace();
		}
				
	}
	
	public void listarCombos(){
		listaEps = epsEJB.listarEps();
		listaDptos = departamentoEJB.listarDepartamento();
		listarMunicipios();
	}
	
	public void listarMunicipios(){
		Departamento d = departamentoEJB.buscar(dptoSeleccionado);
		listaMunicipios = municipioEJB.listarMunicipio(d);	
	}
	
	
	public void registrar() throws ParseException{
			
		try {
			Usuario u = new Usuario();
			u.setUser(usuario);
			u.setPassword(password);
			Rol r = rolEJB.buscarRol(2);
			u.setRol(r);
			usuarioEJB.crear(u);
		
			Paciente p = new Paciente();
			p.setNombre(nombre);
			p.setNumIdentificacion(Integer.parseInt(numeroDocumento));
			p.setGenero(generoSeleccionado);
			p.setEmail(email);
			Eps e = epsEJB.buscarEps(epsSeleccionada);
			p.setEps(e);
			fechaNacimiento = new SimpleDateFormat("dd-MM-yyyy").parse(fecha);
			p.setFechaNacimiento(fechaNacimiento);
			p.setTelefono(telefono);
			Municipio m = municipioEJB.buscar(municipioSeleccionado);
			p.setMunicipio(m);
			p.setUsuario(u);
			p.setDireccion(direccion);
			pacienteEJB.crear(p);
			limpiar();
			Messages.addFlashGlobalInfo("El paciente se ha registrado con exito");
		} catch (ExcepcionNegocio e1) {
			Messages.addFlashGlobalError(e1.getMessage());
			e1.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Messages.addFlashGlobalInfo(e.getMessage());
			e.printStackTrace();
		}
	
	}
	
	public void buscar(){
		try {
			if(!busNumeroDocumento.isEmpty()){

				Paciente pa = pacienteEJB.buscar(Integer.parseInt(busNumeroDocumento));
				if(pa != null){
					nombre = pa.getNombre();
					numeroDocumento = String.valueOf(pa.getNumIdentificacion());
					telefono = pa.getTelefono();
					fecha = pa.getFechaNacimiento().toString();
					email = pa.getEmail();
					direccion = pa.getDireccion();
					epsSeleccionada = pa.getEps().getId();
					usuario=pa.getUsuario().getUser();
					password=pa.getUsuario().getPassword();
				}else{
					Messages.addFlashGlobalWarn("El paciente no existe");
					limpiar();
				
				}
			}else{
				Messages.addFlashGlobalWarn("Por favor ingrese documento");
			}
		} catch (ExcepcionNegocio e1) {
			Messages.addFlashGlobalError(e1.getMessage());
			e1.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Messages.addFlashGlobalInfo(e.getMessage());
			e.printStackTrace();
		}
		

	}
	
	public void borrar(Paciente p) {
//		pacienteEJB.borrarPaciente(p);
//		Messages.addFlashGlobalInfo("El paciente ha sido eliminada exitosamente");
	}
	
	
	public void limpiar(){
		nombre = "";
		numeroDocumento = "";
		telefono = "";
		fecha = "";
		email = "";
		direccion="";
		generoSeleccionado = null;
		epsSeleccionada=0;
		dptoSeleccionado=0;
		municipioSeleccionado=0;
		numeroDocumento = "";
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the busNumeroDocumento
	 */
	public String getBusNumeroDocumento() {
		return busNumeroDocumento;
	}

	/**
	 * @param busNumeroDocumento the busNumeroDocumento to set
	 */
	public void setBusNumeroDocumento(String busNumeroDocumento) {
		this.busNumeroDocumento = busNumeroDocumento;
	}

	/**
	 * @return the numeroDocumento
	 */
	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	/**
	 * @param numeroDocumento the numeroDocumento to set
	 */
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	/**
	 * @return the generoSeleccionado
	 */
	public String getGeneroSeleccionado() {
		return generoSeleccionado;
	}

	/**
	 * @param generoSeleccionado the generoSeleccionado to set
	 */
	public void setGeneroSeleccionado(String generoSeleccionado) {
		this.generoSeleccionado = generoSeleccionado;
	}


	public int getEpsSeleccionada() {
		return epsSeleccionada;
	}

	public void setEpsSeleccionada(int epsSeleccionada) {
		this.epsSeleccionada = epsSeleccionada;
	}

	/**
	 * @return the listaEps
	 */
	public List<Eps> getListaEps() {
		return listaEps;
	}

	/**
	 * @param listaEps the listaEps to set
	 */
	public void setListaEps(List<Eps> listaEps) {
		this.listaEps = listaEps;
	}

	/**
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the pacientes
	 */
	public List<Paciente> getPacientes() {
		return pacientes;
	}

	/**
	 * @param pacientes the pacientes to set
	 */
	public void setPacientes(List<Paciente> pacientes) {
		this.pacientes = pacientes;
	}

	/**
	 * @return the fechaNacimiento
	 */
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	/**
	 * @param fechaNacimiento the fechaNacimiento to set
	 */
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public int getMunicipioSeleccionado() {
		return municipioSeleccionado;
	}

	public void setMunicipioSeleccionado(int municipioSeleccionado) {
		this.municipioSeleccionado = municipioSeleccionado;
	}

	public List<Municipio> getListaMunicipios() {
		return listaMunicipios;
	}

	public void setListaMunicipios(List<Municipio> listaMunicipios) {
		this.listaMunicipios = listaMunicipios;
	}

	public int getDptoSeleccionado() {
		return dptoSeleccionado;
	}

	public void setDptoSeleccionado(int dptoSeleccionado) {
		this.dptoSeleccionado = dptoSeleccionado;
	}

	public List<Departamento> getListaDptos() {
		return listaDptos;
	}

	public void setListaDptos(List<Departamento> listaDptos) {
		this.listaDptos = listaDptos;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}	
	
	
}
