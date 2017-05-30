package co.edu.eam.ingesoft.pa.hospitalizacion.web.controladores;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;

import co.edu.eam.ingesoft.pa.negocio.beans.CirugiaEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.CitaEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.ExamenEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.MedicamentoEJB;

import co.edu.eam.ingesoft.pa.negocio.beans.PacienteEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.PatologiaEJB;

import co.edu.eam.ingesoft.pa.negocio.dtos.CirugiaHistorialDTO;
import co.edu.eam.ingesoft.pa.negocio.dtos.CitaHistorialDTO;
import co.edu.eam.ingesoft.pa.negocio.dtos.ExamenHistorialDTO;
import co.edu.eam.ingesoft.pa.negocio.dtos.MedicamentoEntregadoDTO;
import co.edu.eam.ingesoft.pa.negocio.dtos.PatologiaHistorialDTO;

@Named("historialController")
@ViewScoped
public class HistorialMedicoAjaxController implements Serializable {

	@EJB
	private PacienteEJB pacienteEJB;

	@EJB
	private CitaEJB citaEJB;

	@EJB
	private MedicamentoEJB medicamentoEJB;

	@EJB
	private ExamenEJB examenEJB;

	@EJB
	private PatologiaEJB patologiaEJB;

	@EJB
	private CirugiaEJB cirugiaEJB;

	private String documentoBuscar;

	private String nombrePaciente;

	private String anotaciones;

	private String descripcionExamen;

	private String sintomas;

	private List<CitaHistorialDTO> citas;

	private List<MedicamentoEntregadoDTO> medicamentos;

	private List<ExamenHistorialDTO> examenes;

	private List<PatologiaHistorialDTO> patologias;

	private List<CirugiaHistorialDTO> cirugias;

	@PostConstruct
	public void inicializar() {
		System.out.print("yoloooooooooooooooo");
		// pacientes = pacienteEJB.listarPacietes();

	}

	public void buscarHistorial() {
		citas = citaEJB.listarCitaHistorial(Integer.parseInt(documentoBuscar));
		nombrePaciente = citas.get(0).getNombrePaciente();
	}

	public void verCita(CitaHistorialDTO cita) {

		anotaciones = cita.getAnotaciones();
		llenarTodo(cita.getIdCita());

	}

	public void llenarTodo(String cita) {
		medicamentos = medicamentoEJB.listarMedicamentoHistorial(Integer.parseInt(cita));
		examenes = examenEJB.listarExamenesHistorial(Integer.parseInt(cita));
		patologias = patologiaEJB.listarPatologiasHistorial(Integer.parseInt(cita));
		cirugias = cirugiaEJB.listarCirugiasHistorial(Integer.parseInt(cita));
	}

	public void verResultadosExamen(ExamenHistorialDTO ex) {

		descripcionExamen = ex.getDescripcionExamen();
	}

	public void verSintomas(PatologiaHistorialDTO pat) {

		sintomas = pat.getSintomas();
	}

	/**
	 * @return the documentoBuscar
	 */
	public String getDocumentoBuscar() {
		return documentoBuscar;
	}

	/**
	 * @param documentoBuscar
	 *            the documentoBuscar to set
	 */
	public void setDocumentoBuscar(String documentoBuscar) {
		this.documentoBuscar = documentoBuscar;
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
	 * @return the citas
	 */
	public List<CitaHistorialDTO> getCitas() {
		return citas;
	}

	/**
	 * @param citas
	 *            the citas to set
	 */
	public void setCitas(List<CitaHistorialDTO> citas) {
		this.citas = citas;
	}

	/**
	 * @return the medicamentos
	 */
	public List<MedicamentoEntregadoDTO> getMedicamentos() {
		return medicamentos;
	}

	/**
	 * @param medicamentos
	 *            the medicamentos to set
	 */
	public void setMedicamentos(List<MedicamentoEntregadoDTO> medicamentos) {
		this.medicamentos = medicamentos;
	}

	/**
	 * @return the sintomas
	 */
	public String getSintomas() {
		return sintomas;
	}

	/**
	 * @param sintomas
	 *            the sintomas to set
	 */
	public void setSintomas(String sintomas) {
		this.sintomas = sintomas;
	}

	/**
	 * @return the examenes
	 */
	public List<ExamenHistorialDTO> getExamenes() {
		return examenes;
	}

	/**
	 * @param examenes
	 *            the examenes to set
	 */
	public void setExamenes(List<ExamenHistorialDTO> examenes) {
		this.examenes = examenes;
	}

	/**
	 * @return the patologias
	 */
	public List<PatologiaHistorialDTO> getPatologias() {
		return patologias;
	}

	/**
	 * @param patologias
	 *            the patologias to set
	 */
	public void setPatologias(List<PatologiaHistorialDTO> patologias) {
		this.patologias = patologias;
	}

	/**
	 * @return the cirugias
	 */
	public List<CirugiaHistorialDTO> getCirugias() {
		return cirugias;
	}

	/**
	 * @param cirugias
	 *            the cirugias to set
	 */
	public void setCirugias(List<CirugiaHistorialDTO> cirugias) {
		this.cirugias = cirugias;
	}

	/**
	 * @return the anotaciones
	 */
	public String getAnotaciones() {
		return anotaciones;
	}

	/**
	 * @param anotaciones
	 *            the anotaciones to set
	 */
	public void setAnotaciones(String anotaciones) {
		this.anotaciones = anotaciones;
	}

	/**
	 * @return the descripcionExamen
	 */
	public String getDescripcionExamen() {
		return descripcionExamen;
	}

	/**
	 * @param descripcionExamen
	 *            the descripcionExamen to set
	 */
	public void setDescripcionExamen(String descripcionExamen) {
		this.descripcionExamen = descripcionExamen;
	}

}
