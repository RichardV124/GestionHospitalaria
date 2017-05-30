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
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Medico;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Municipio;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Paciente;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.TipoMedico;
import co.edu.eam.ingesoft.pa.negocio.beans.DepartamentoEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.EpsEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.MedicoEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.MunicipioEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.PacienteEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.RolEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.TipoMedicoEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.UsuarioEJB;

import javax.faces.bean.ManagedBean; 


@ViewScoped 
@Named("medicoajaxcontroller") 
public class MedicoAjaxController implements Serializable {
	
	@EJB
	private	MedicoEJB medicoEJB;
	
	@EJB
	private RolEJB rolEJB;
	
	@EJB
	private TipoMedicoEJB tipoMedicoEJB;
	
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
	
	private TipoMedico tipoMedicoSeleccionado;
	
	private List<TipoMedico> listaTipoMedico;
	
	private Municipio municipioSeleccionado;
	
	private List<Municipio> listaMunicipios;
	
	private Departamento dptoSeleccionado;
	
	private List<Departamento> listaDptos;
	
	private String fecha;
	
	private String telefono;

	private String email;
	
	private List<Medico> medicos;
	
	private Date fechaNacimiento;
	
	private String usuario;
	
	private String password;
	
	@PostConstruct
	public void inicializar(){
		System.out.print("yoloooooooooooooooo");
		listarCombos();
//		pacientes = pacienteEJB.listarPacietes();
				
	}
	
	public void listarCombos(){
		listaTipoMedico = tipoMedicoEJB.listarTipoMedicos();
		listaDptos = departamentoEJB.listarDepartamento();	
	}
	
	public void listarMunicipios(){
		if(dptoSeleccionado!=null){
				listaMunicipios = municipioEJB.listarMunicipio(dptoSeleccionado);	
		}
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
		tipoMedicoSeleccionado = null;
		dptoSeleccionado = null;
		municipioSeleccionado = null;
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

	public Municipio getMunicipioSeleccionado() {
		return municipioSeleccionado;
	}

	public void setMunicipioSeleccionado(Municipio municipioSeleccionado) {
		this.municipioSeleccionado = municipioSeleccionado;
	}

	public List<Municipio> getListaMunicipios() {
		return listaMunicipios;
	}

	public void setListaMunicipios(List<Municipio> listaMunicipios) {
		this.listaMunicipios = listaMunicipios;
	}

	public Departamento getDptoSeleccionado() {
		return dptoSeleccionado;
	}

	public void setDptoSeleccionado(Departamento dptoSeleccionado) {
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

	/**
	 * @return the tipoMedicoSeleccionado
	 */
	public TipoMedico getTipoMedicoSeleccionado() {
		return tipoMedicoSeleccionado;
	}

	/**
	 * @param tipoMedicoSeleccionado the tipoMedicoSeleccionado to set
	 */
	public void setTipoMedicoSeleccionado(TipoMedico tipoMedicoSeleccionado) {
		this.tipoMedicoSeleccionado = tipoMedicoSeleccionado;
	}

	/**
	 * @return the listaTipoMedico
	 */
	public List<TipoMedico> getListaTipoMedico() {
		return listaTipoMedico;
	}

	/**
	 * @param listaTipoMedico the listaTipoMedico to set
	 */
	public void setListaTipoMedico(List<TipoMedico> listaTipoMedico) {
		this.listaTipoMedico = listaTipoMedico;
	}

	/**
	 * @return the medicos
	 */
	public List<Medico> getMedicos() {
		return medicos;
	}

	/**
	 * @param medicos the medicos to set
	 */
	public void setMedicos(List<Medico> medicos) {
		this.medicos = medicos;
	}	
	
	
	
	
}
