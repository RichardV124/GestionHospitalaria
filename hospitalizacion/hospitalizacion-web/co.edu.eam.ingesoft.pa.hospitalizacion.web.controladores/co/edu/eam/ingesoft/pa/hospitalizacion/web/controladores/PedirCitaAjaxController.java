package co.edu.eam.ingesoft.pa.hospitalizacion.web.controladores;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Messages;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.CaracterCita;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Cita;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Horario;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.HorarioMedico;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Medico;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Paciente;
import co.edu.eam.ingesoft.pa.negocio.beans.CaracterCitaEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.CitaEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.HorarioEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.MedicoEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.PacienteEJB;
import co.edu.eam.ingesoft.pa.negocio.dtos.CitaPendienteDTO;
import co.edu.eam.ingesoft.pa.negocio.dtos.MedicoDisponibleDTO;
import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;

@Named("pedirCitaAjaxController")
@ViewScoped
public class PedirCitaAjaxController implements Serializable{

	private int medicoSeleccionado;
	
	private List<MedicoDisponibleDTO> medicos;
	
	private Horario horarioSeleccionado;
	
	private List<Horario> horarios;
	
	private CaracterCita caracterSeleccionado;
	
	private List<CaracterCita> caracteres;
	
	private List<CitaPendienteDTO> citasPendientes;
	
	@EJB
	private MedicoEJB medicoEJB;
	
	@EJB
	private HorarioEJB horarioEJB;
	
	@EJB
	private CitaEJB citaEJB;
	
	@EJB
	private PacienteEJB pacienteEJB;
	
	@EJB
	private CaracterCitaEJB caracterCitaEJB;
	
	@PostConstruct
	public void inicializar() {
		try {
			listarMedicosDisponibles();
			listarCaracterCita();
		} catch (ExcepcionNegocio e1) {
			Messages.addFlashGlobalError(e1.getMessage());
			e1.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Messages.addFlashGlobalInfo(e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	public void registrar(){
		Cita c = new Cita();
		Paciente p = pacienteEJB.buscar(1);
		c.setPaciente(p);
		c.setAtendido(false);
		HorarioMedico hm = new HorarioMedico();
		hm.setHorario(horarioSeleccionado);
		Medico m = medicoEJB.buscar(medicoSeleccionado);
		hm.setMedico(m);
		c.setHorarioMedico(hm);
		c.setCaracter(caracterSeleccionado);
		
		citaEJB.crear(c);
		Messages.addFlashGlobalInfo("Se registr� la cita con exito!");
	}
	
	public void listarCitasPEndientes(){
		citasPendientes = citaEJB.listarCitasPendientes(1);
	}
	
	public void listarMedicosDisponibles(){
		medicos = medicoEJB.listarMedicosDisponibles();
	}
	
	public void listarCaracterCita(){
		caracteres = caracterCitaEJB.listarCaracterCita();
	}
	
	public void listarHorariosMedico(){
		if(medicoSeleccionado!=0){
			horarios = horarioEJB.listarHorariosMedicoDisponible(medicoSeleccionado);
		}
	}
	
	public void cancelarCita(CitaPendienteDTO cita){
		citaEJB.eliminar(cita.getId());
	}

	public int getMedicoSeleccionado() {
		return medicoSeleccionado;
	}

	public void setMedicoSeleccionado(int medicoSeleccionado) {
		this.medicoSeleccionado = medicoSeleccionado;
	}

	public List<MedicoDisponibleDTO> getMedicos() {
		return medicos;
	}

	public void setMedicos(List<MedicoDisponibleDTO> medicos) {
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

	public CaracterCita getCaracterSeleccionado() {
		return caracterSeleccionado;
	}

	public void setCaracterSeleccionado(CaracterCita caracterSeleccionado) {
		this.caracterSeleccionado = caracterSeleccionado;
	}

	public List<CaracterCita> getCaracteres() {
		return caracteres;
	}

	public void setCaracteres(List<CaracterCita> caracteres) {
		this.caracteres = caracteres;
	}
	
}
