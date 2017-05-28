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
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Medico;
import co.edu.eam.ingesoft.pa.negocio.beans.HorarioEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.MedicoEJB;

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
	
	
	@PostConstruct
	public void inicializar() {
		
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
