package co.edu.eam.ingesoft.pa.hospitalizacion.web.controladores;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Messages;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Horario;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.HorarioMedico;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.HorarioMedicoPK;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Medico;
import co.edu.eam.ingesoft.pa.negocio.beans.HorarioEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.HorarioMedicoEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.MedicoEJB;
import co.edu.eam.ingesoft.pa.negocio.dtos.HorarioMedicoDTO;
import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;

@Named("horarioAjaxController")
@ViewScoped
public class HorarioAjaxController implements Serializable{

	/**
	 * fecha del horario
	 */
	@NotNull(message = "Debe ingresar la fecha")
	private String fecha;

	/**
	 * hora inicial
	 */
	@NotNull(message = "Debe digitar la hora de inicio del horario")
	private String horaInicial;

	/**
	 * Numero de identificacion del titular
	 */
	@NotNull(message = "Debe digitar la hora de final del horario")
	private String horaFinal;
	
	@NotNull(message = "Debe digitar el id del horario")
	private String buscarHorario;
	
	@EJB
	private HorarioEJB horarioEJB;
	
	@EJB
	private MedicoEJB medicoEJB;
	
	@EJB
	private HorarioMedicoEJB horarioMedicoEJB;
	
	private int medicoSeleccionado;
	
	private List<Medico> medicos;
	
	private int horarioSeleccionado;
	
	private List<Horario> horarios;
	
	private List<HorarioMedicoDTO> horariosAsig;
	
	
	@PostConstruct
	public void inicializar() {
		try {
			listarMedicos();
			listarHorarios();
			//listarHorariosAsignados();
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
		Horario h = horarioEJB.buscar(Integer.parseInt(buscarHorario));
		if(h!=null){
			fecha = String.valueOf(h.getFecha());
			horaInicial = String.valueOf(h.getHoraInicial());
			horaFinal = String.valueOf(h.getHoraFinal());	
		}else{
			Messages.addFlashGlobalInfo("El horario no est� registrado!");
		}
	}
	
	public void editar() throws ParseException{
		try {
			Horario h = new Horario();
			Date fechaH = new SimpleDateFormat("dd-MM-yyyy").parse(fecha);
			h.setFecha(fechaH);
			h.setHoraInicial(Integer.parseInt(horaInicial));
			h.setHoraFinal(Integer.parseInt(horaFinal));
			h.setId(Integer.parseInt(buscarHorario));
			
			horarioEJB.editar(h);
			Messages.addFlashGlobalInfo("Se edit� el horario con exito!");
			limpiarCampos();
			listarHorarios();
		} catch (ExcepcionNegocio e1) {
			Messages.addFlashGlobalError(e1.getMessage());
			e1.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Messages.addFlashGlobalInfo(e.getMessage());
			e.printStackTrace();
		}		
	}
	
	public void registrar() throws ParseException{
		Horario h = new Horario();
		Date fechaH = new SimpleDateFormat("dd-MM-yyyy").parse(fecha);
//		Calendar calendario = GregorianCalendar.getInstance();
//		Date fechaH = calendario.getTime();
		h.setFecha(fechaH);
		h.setHoraInicial(Integer.parseInt(horaInicial));
		h.setHoraFinal(Integer.parseInt(horaFinal));
		
		horarioEJB.crear(h);
		Messages.addFlashGlobalInfo("Se cre� el horario con exito!");
		limpiarCampos();
		listarHorarios();
	}
	
	public void asignarHorario(){
		HorarioMedico hm = new HorarioMedico();
		hm.setDisponible(true);		
		Medico m = medicoEJB.buscar(medicoSeleccionado);
		hm.setMedico(m);
		Horario h = horarioEJB.buscar(horarioSeleccionado);
		hm.setHorario(h);
		
		horarioMedicoEJB.crear(hm);
		Messages.addFlashGlobalInfo("Se le asign� el horario al medico con exito!");
		listarHorariosAsignados();
	}
	
	public void eliminarHorarioAsignado(HorarioMedicoDTO hm){
		HorarioMedicoPK hmPK = new HorarioMedicoPK();
		hmPK.setHorario(hm.getHorario());
		hmPK.setMedico(hm.getMedico());
 		horarioMedicoEJB.eliminar(hmPK);
 		Messages.addFlashGlobalInfo("Se le elimin� el horario asignado al medico con exito!");
	}
	
	public void limpiarCampos(){
		fecha="";
		horaInicial="";
		horaFinal="";
	}
	
	public void listarMedicos(){
		medicos = medicoEJB.listarMedicos();
	}
	
	public void listarHorariosAsignados(){
		horariosAsig = horarioMedicoEJB.listarHorariosMedico();
	}
	
	public void listarHorarios(){
		horarios = horarioEJB.listarHorarios();
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getHoraInicial() {
		return horaInicial;
	}

	public void setHoraInicial(String horaInicial) {
		this.horaInicial = horaInicial;
	}

	public String getHoraFinal() {
		return horaFinal;
	}

	public void setHoraFinal(String horaFinal) {
		this.horaFinal = horaFinal;
	}

	public int getMedicoSeleccionado() {
		return medicoSeleccionado;
	}

	public void setMedicoSeleccionado(int medicoSeleccionado) {
		this.medicoSeleccionado = medicoSeleccionado;
	}

	public List<Medico> getMedicos() {
		return medicos;
	}

	public void setMedicos(List<Medico> medicos) {
		this.medicos = medicos;
	}

	public int getHorarioSeleccionado() {
		return horarioSeleccionado;
	}

	public void setHorarioSeleccionado(int horarioSeleccionado) {
		this.horarioSeleccionado = horarioSeleccionado;
	}

	public List<Horario> getHorarios() {
		return horarios;
	}

	public void setHorarios(List<Horario> horarios) {
		this.horarios = horarios;
	}

	public List<HorarioMedicoDTO> getHorariosAsig() {
		return horariosAsig;
	}

	public void setHorariosAsig(List<HorarioMedicoDTO> horariosAsig) {
		this.horariosAsig = horariosAsig;
	}

	public String getBuscarHorario() {
		return buscarHorario;
	}

	public void setBuscarHorario(String buscarHorario) {
		this.buscarHorario = buscarHorario;
	}

	public HorarioEJB getHorarioEJB() {
		return horarioEJB;
	}

	public void setHorarioEJB(HorarioEJB horarioEJB) {
		this.horarioEJB = horarioEJB;
	}

	public MedicoEJB getMedicoEJB() {
		return medicoEJB;
	}

	public void setMedicoEJB(MedicoEJB medicoEJB) {
		this.medicoEJB = medicoEJB;
	}

	public HorarioMedicoEJB getHorarioMedicoEJB() {
		return horarioMedicoEJB;
	}

	public void setHorarioMedicoEJB(HorarioMedicoEJB horarioMedicoEJB) {
		this.horarioMedicoEJB = horarioMedicoEJB;
	}

	
}
