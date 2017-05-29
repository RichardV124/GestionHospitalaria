package co.edu.eam.ingesoft.pa.hospitalizacion.web.controladores;

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
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Medico;
import co.edu.eam.ingesoft.pa.negocio.beans.HorarioEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.MedicoEJB;
import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;

@Named("horarioAjaxController")
@ViewScoped
public class HorarioAjaxController {

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
	
	@EJB
	private HorarioEJB horarioEJB;
	
	@EJB
	private MedicoEJB medicoEJB;
	
	private Medico medicoSeleccionado;
	
	private List<Medico> medicos;
	
	private Horario horarioSeleccionado;
	
	private List<Horario> horarios;
	
	private List<HorarioMedico> horariosAsig;
	
	
	@PostConstruct
	public void inicializar() {
		try {
			listarMedicos();
			listarMedicos();
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
	
	public void registrar() throws ParseException{
		Horario h = new Horario();
		Date fechaH = new SimpleDateFormat("dd-MM-yyyy").parse(fecha);
		h.setFecha(fechaH);
		h.setHoraInicial(Integer.parseInt(horaInicial));
		h.setHoraFinal(Integer.parseInt(horaFinal));
		
		horarioEJB.crear(h);
		
		limpiarCampos();
		listarHorarios();
	}
	
	public void asignarHorario(){
		
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
		
	}
	
	public void listarHorarios(){
		horarios = horarioEJB.listarHorarios();
	}
	
	
	
}
