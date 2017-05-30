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
	
	private Medico medicoSeleccionado;
	
	private List<Medico> medicos;
	
	private Horario horarioSeleccionado;
	
	private List<Horario> horarios;
	
	private List<HorarioMedicoDTO> horariosAsig;
	
	
	@PostConstruct
	public void inicializar() {
		try {
			listarMedicos();
			listarHorarios();
			listarHorariosAsignados();
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
			Messages.addFlashGlobalInfo("El horario no está registrado!");
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
			Messages.addFlashGlobalInfo("Se editó el horario con exito!");
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
		h.setFecha(fechaH);
		h.setHoraInicial(Integer.parseInt(horaInicial));
		h.setHoraFinal(Integer.parseInt(horaFinal));
		
		horarioEJB.crear(h);
		Messages.addFlashGlobalInfo("Se creó el horario con exito!");
		limpiarCampos();
		listarHorarios();
	}
	
	public void asignarHorario(){
		HorarioMedico hm = new HorarioMedico();
		hm.setDisponible(true);		
		hm.setMedico(medicoSeleccionado);
		hm.setHorario(horarioSeleccionado);
		
		horarioMedicoEJB.crear(hm);
		Messages.addFlashGlobalInfo("Se le asignó el horario al medico con exito!");
		listarHorariosAsignados();
	}
	
	public void eliminarHorarioAsignado(HorarioMedicoDTO hm){
		HorarioMedicoPK hmPK = new HorarioMedicoPK();
		hmPK.setHorario(hm.getHorario());
		hmPK.setMedico(hm.getMedico());
 		horarioMedicoEJB.eliminar(hmPK);
 		Messages.addFlashGlobalInfo("Se le eliminó el horario asignado al medico con exito!");
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

	public Medico getMedicoSeleccionado() {
		return medicoSeleccionado;
	}

	public void setMedicoSeleccionado(Medico medicoSeleccionado) {
		this.medicoSeleccionado = medicoSeleccionado;
	}

	public List<Medico> getMedicos() {
		return medicos;
	}

	public void setMedicos(List<Medico> medicos) {
		this.medicos = medicos;
	}

	public Horario getHorarioSeleccionado() {
		return horarioSeleccionado;
	}

	public void setHorarioSeleccionado(Horario horarioSeleccionado) {
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

}
