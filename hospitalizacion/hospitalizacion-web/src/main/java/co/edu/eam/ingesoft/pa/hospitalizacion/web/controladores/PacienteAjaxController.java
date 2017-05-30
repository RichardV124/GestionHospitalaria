package co.edu.eam.ingesoft.pa.hospitalizacion.web.controladores;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Messages;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Departamento;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Eps;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Municipio;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Paciente;
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
	
	private String nombre;
	
	private String busNumeroDocumento;
	
	private String numeroDocumento;
	
	private int epsSeleccionada;
	
	private List<Eps> listaEps;
	
	private int municipioSeleccionado;
	
	private List<Municipio> listaMunicipios;
	
	private int dptoSeleccionado;
	
	private List<Departamento> listaDptos;
	
	private String fecha;
	
	private String telefono;

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
		
//		if(!(fecha.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || telefono.isEmpty() || email.isEmpty() ||
//				generoSeleccionado.equalsIgnoreCase("Seleccione") || (epsSeleccionada == 0) || (numeroDocumento.isEmpty())) ){
//
//
//			System.out.println("entro");
//			
//		Paciente pa = pacienteEJB.buscarPaciente(Integer.parseInt(numeroDocumento));
//		if(pa == null){
//			Paciente p = new Paciente();
//			
//			p.setNombre(nombre);
//			p.setApellido(apellido);
//			p.setIdentificacion(Integer.parseInt(numeroDocumento));
//			p.setGenero(generoSeleccionado);
//			p.setEmail(email);
//			Eps e = epsEJB.buscarEps(epsSeleccionada);
//			p.setEps(e);
//			fechaNacimiento = new SimpleDateFormat("dd-MM-yyyy").parse(fecha);
//			p.setFechaNacimiento(fechaNacimiento);
//			p.setTelefono(telefono);
//			if(p.getFechaNacimiento() != null){
//				pacienteEJB.crearPaciente(p);
//				limpiar();
//				Messages.addFlashGlobalInfo("El paciente se ha registrado con exito");
//			}else{
//				System.out.println("No entro fecha");
//				Messages.addFlashGlobalError("No entro fecha");
//			}
//			
//			
//		}else{
//			Messages.addFlashGlobalError("El paciente con identificacion: "+numeroDocumento+" ya existe");
//		}
//		
//		}else{
//			Messages.addFlashGlobalError("Ingrese todos los datos");
//			System.out.println("No entro");
//		}
	}
	
	public void buscar(){
		System.out.print("QUE PEDOOOOOOOOOOOOOOOOOOOOO");
//		if(!busNumeroDocumento.isEmpty()){
//
//		Paciente pa = pacienteEJB.buscarPaciente(Integer.parseInt(busNumeroDocumento));
//		if(pa != null){
//			nombre = pa.getNombre();
//			apellido = pa.getApellido();
//			numeroDocumento = String.valueOf(pa.getIdentificacion());
//			telefono = pa.getTelefono();
//			fecha = pa.getFechaNacimiento().toString();
//			email = pa.getEmail();
//			generoSeleccionado = pa.getGenero();
//			epsSeleccionada = pa.getEps().getIdEps();
//		}else{
//			Messages.addFlashGlobalWarn("El paciente no existe");
//			limpiar();
//		
//		}
//		}else{
//			Messages.addFlashGlobalWarn("Por favor ingrese documento");
//		}

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
		generoSeleccionado = null;
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
	
	
}
