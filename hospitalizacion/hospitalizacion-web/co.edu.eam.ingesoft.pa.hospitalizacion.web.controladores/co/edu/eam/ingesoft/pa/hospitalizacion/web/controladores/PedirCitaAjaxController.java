package co.edu.eam.ingesoft.pa.hospitalizacion.web.controladores;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Messages;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Cita;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Horario;
import co.edu.eam.ingesoft.pa.negocio.beans.CitaEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.HorarioEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.MedicoEJB;
import co.edu.eam.ingesoft.pa.negocio.dtos.MedicoDisponibleDTO;
import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;

@Named("pedirCitaAjaxController")
@ViewScoped
public class PedirCitaAjaxController implements Serializable{

	private int medicoSeleccionado;
	
	private List<MedicoDisponibleDTO> medicos;
	
	private Horario horarioSeleccionado;
	
	private List<Horario> horarios;
	
	private List<Cita> citas;
	
	@EJB
	private MedicoEJB medicoEJB;
	
	@EJB
	private HorarioEJB horarioEJB;
	
	@EJB
	private CitaEJB citaEJB;
	
	@PostConstruct
	public void inicializar() {
		try {
			listarMedicosDisponibles();
		} catch (ExcepcionNegocio e1) {
			Messages.addFlashGlobalError(e1.getMessage());
			e1.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Messages.addFlashGlobalInfo(e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	public void listarMedicosDisponibles(){
		medicos = medicoEJB.listarMedicosDisponibles();
	}
	
	public void listarHorariosMedico(int cedMed){
		if(cedMed!=0){
			horarios = horarioEJB.listarHorariosMedicoDisponible(cedMed);
		}
	}
	
	public void cancelarCita(Cita cita){
		
	}
}
