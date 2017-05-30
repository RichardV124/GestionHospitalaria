package co.edu.eam.ingesoft.pa.hospitalizacion.web.controladores;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Messages;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Cama;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Cirugia;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.CitaCirugia;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Horario;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.HorarioMedico;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.HorarioMedicoPK;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Quirofano;
import co.edu.eam.ingesoft.pa.negocio.beans.CamaEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.CirugiaEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.CitaCirugiaEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.CitaEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.HorarioEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.HorarioMedicoEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.MedicoEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.QuirofanoEJB;
import co.edu.eam.ingesoft.pa.negocio.dtos.CamaDTO;
import co.edu.eam.ingesoft.pa.negocio.dtos.MedicoDisponibleDTO;

@ViewScoped
@Named("remitircirugiacontroller")
public class RemitirCirugiaAjaxController implements Serializable {

	@EJB
	private CitaCirugiaEJB citaCirugiaEJB;

	@EJB
	private CirugiaEJB cirugiaEJB;

	@EJB
	private QuirofanoEJB quirofanoEJB;

	@EJB
	private MedicoEJB medicoEJB;
	
	@EJB
	private HorarioEJB horarioEJB;
	
	@EJB
	private CitaEJB citaEJB;
	
	@EJB
	private HorarioMedicoEJB horarioMedicoEJB;
	
	// Componentes de citacirugia

	private String idCita;

	private String cedulaPaciente;

	private String nombrePaciente;

	private Cirugia cirugiaSeleccionada;

	private List<Cirugia> cirugias;

	private Quirofano quirofanoSeleccionado;

	private List<Quirofano> quirofanos;

	private Date fecha;

	private int medicoSeleccionado;

	private List<MedicoDisponibleDTO> medicos;

	private Horario horarioSeleccionado;

	private List<Horario> horarios;

	@PostConstruct
	public void inicializar() {

		// listarCombos();
		// pacientes = pacienteEJB.listarPacietes();

	}

	public void listarCombos() {
		
		try {

		cirugias = cirugiaEJB.listarCirugia();
		quirofanos = quirofanoEJB.listaQuirofano();
		
		} catch (Exception e) {
			Messages.addFlashGlobalError(e.getMessage());
			e.printStackTrace();
		}
	}

	// registrar
	public void remitirCirugia() {

		try {

			System.out.println("entro");

			CitaCirugia citaCirugia = new CitaCirugia();

			citaCirugia.setCirugia(cirugiaSeleccionada);
			// citaCirugia.setCita(cita);
			citaCirugia.setFecha(fecha);
			HorarioMedicoPK hmPK = new HorarioMedicoPK();
			hmPK.setHorario(horarioSeleccionado.getId());
			hmPK.setMedico(medicoSeleccionado);
			HorarioMedico horarioMedico = horarioMedicoEJB.buscar(hmPK);
			citaCirugia.setHorarioMedico(horarioMedico);
			citaCirugia.setQuirofano(quirofanoSeleccionado);
			
		citaCirugiaEJB.crearCitaCirugia(citaCirugia);

			Messages.addFlashGlobalInfo("Se ha hecho registrado la remision con exito");

		} catch (Exception e) {
			Messages.addFlashGlobalError(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * @return the idCita
	 */
	public String getIdCita() {
		return idCita;
	}

	/**
	 * @param idCita
	 *            the idCita to set
	 */
	public void setIdCita(String idCita) {
		this.idCita = idCita;
	}

	/**
	 * @return the cedulaPaciente
	 */
	public String getCedulaPaciente() {
		return cedulaPaciente;
	}

	/**
	 * @param cedulaPaciente
	 *            the cedulaPaciente to set
	 */
	public void setCedulaPaciente(String cedulaPaciente) {
		this.cedulaPaciente = cedulaPaciente;
	}

	/**
	 * @return the nombrePaciente
	 */
	public String getNombrePaciente() {
		return nombrePaciente;
	}

	/**
	 * @param nombrePaciente
	 *            the nombrePaciente to set
	 */
	public void setNombrePaciente(String nombrePaciente) {
		this.nombrePaciente = nombrePaciente;
	}
	
	/**
	 * @return the cirugias
	 */
	public List<Cirugia> getCirugias() {
		return cirugias;
	}

	/**
	 * @param cirugias
	 *            the cirugias to set
	 */
	public void setCirugias(List<Cirugia> cirugias) {
		this.cirugias = cirugias;
	}

	/**
	 * @return the quirofanos
	 */
	public List<Quirofano> getQuirofanos() {
		return quirofanos;
	}

	/**
	 * @param quirofanos
	 *            the quirofanos to set
	 */
	public void setQuirofanos(List<Quirofano> quirofanos) {
		this.quirofanos = quirofanos;
	}

	/**
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * @param fecha
	 *            the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return the cirugiaSeleccionada
	 */
	public Cirugia getCirugiaSeleccionada() {
		return cirugiaSeleccionada;
	}

	/**
	 * @param cirugiaSeleccionada the cirugiaSeleccionada to set
	 */
	public void setCirugiaSeleccionada(Cirugia cirugiaSeleccionada) {
		this.cirugiaSeleccionada = cirugiaSeleccionada;
	}

	/**
	 * @return the quirofanoSeleccionado
	 */
	public Quirofano getQuirofanoSeleccionado() {
		return quirofanoSeleccionado;
	}

	/**
	 * @param quirofanoSeleccionado the quirofanoSeleccionado to set
	 */
	public void setQuirofanoSeleccionado(Quirofano quirofanoSeleccionado) {
		this.quirofanoSeleccionado = quirofanoSeleccionado;
	}

	/**
	 * @return the medicoSeleccionado
	 */
	public int getMedicoSeleccionado() {
		return medicoSeleccionado;
	}

	/**
	 * @param medicoSeleccionado the medicoSeleccionado to set
	 */
	public void setMedicoSeleccionado(int medicoSeleccionado) {
		this.medicoSeleccionado = medicoSeleccionado;
	}

	/**
	 * @return the medicos
	 */
	public List<MedicoDisponibleDTO> getMedicos() {
		return medicos;
	}

	/**
	 * @param medicos the medicos to set
	 */
	public void setMedicos(List<MedicoDisponibleDTO> medicos) {
		this.medicos = medicos;
	}

	/**
	 * @return the horarioSeleccionado
	 */
	public Horario getHorarioSeleccionado() {
		return horarioSeleccionado;
	}

	/**
	 * @param horarioSeleccionado the horarioSeleccionado to set
	 */
	public void setHorarioSeleccionado(Horario horarioSeleccionado) {
		this.horarioSeleccionado = horarioSeleccionado;
	}

	/**
	 * @return the horarios
	 */
	public List<Horario> getHorarios() {
		return horarios;
	}

	/**
	 * @param horarios the horarios to set
	 */
	public void setHorarios(List<Horario> horarios) {
		this.horarios = horarios;
	}
	
	

}
