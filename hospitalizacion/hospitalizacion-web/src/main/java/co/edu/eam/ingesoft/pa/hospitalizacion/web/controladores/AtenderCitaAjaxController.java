package co.edu.eam.ingesoft.pa.hospitalizacion.web.controladores;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Messages;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Cita;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Medicamento;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.MedicamentoRecetado;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.MedicamentosRecetadosPK;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Patologia;
import co.edu.eam.ingesoft.pa.negocio.beans.CitaEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.MedicamentoEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.MedicamentoRecetadoEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.PatologiaEJB;
import co.edu.eam.ingesoft.pa.negocio.dtos.MedicamentoRecetadoDTO;
import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;

@Named("atenderCitaAjaxController")
@ViewScoped
public class AtenderCitaAjaxController implements Serializable{

	/**
	 * cantidad del medicamento
	 */
	@NotNull(message = "Debe ingresar la cantidad")
	private String cantidad;
	
	/**
	 * dosis del medicamento
	 */
	@NotNull(message = "Debe ingresar la dosis")
	private String dosis;
	/**
	 * recomendaciones para el medicamento
	 */
	@NotNull(message = "Debe ingresar las recomendaciones")
	private String recomendaciones;
	
	@EJB
	private MedicamentoEJB medicamentoEJB;
	
	@EJB
	private MedicamentoRecetadoEJB medicamentoRecetadoEJB;
	
	@EJB
	private CitaEJB citaEJB;
	
	@EJB
	private PatologiaEJB patologiaEJB;
	
	private int medicamentoSeleccionado;
	
	private List<Medicamento> medicamentos;
	
	private int patologiaSeleccionada;
	
	private List<Patologia> patologias;
	
	private List<MedicamentoRecetadoDTO> medicamentosRecetados;
	
	private String paciente;
	
	private String horaInicial;
	
	private String horaFinal;
	
	private String anotaciones;
	
	
	@PostConstruct
	public void inicializar() {
		try {
			listarMedicamentos();
		} catch (ExcepcionNegocio e1) {
			Messages.addFlashGlobalError(e1.getMessage());
			e1.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Messages.addFlashGlobalInfo(e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	public void listarMedicamentos(){
		medicamentos = medicamentoEJB.listarMedicamentos();
	}
	
	public void listarMedicamentosRecetados(){
		medicamentosRecetados = medicamentoRecetadoEJB.listarMedicamentosRecetados(1);
	}
	
	public void agregarRecetaMedica(){
		MedicamentoRecetado mr = new MedicamentoRecetado();
		Cita c = citaEJB.buscar(1);
		mr.setCita(c);
		mr.setCantidadRecetada(Integer.parseInt(cantidad));
		mr.setDosis(dosis);
		Medicamento m = medicamentoEJB.buscar(medicamentoSeleccionado);
		mr.setMedicamento(m);
		mr.setDescripcion(recomendaciones);
		mr.setReclamado(false);
		
		medicamentoRecetadoEJB.crear(mr);
		Messages.addFlashGlobalInfo("Se recetó el medicamento con exito!");
	}
	
	public void eliminarRecetaMedica(MedicamentoRecetadoDTO med){
		Cita c = citaEJB.buscar(1);
		MedicamentosRecetadosPK mrPk = new MedicamentosRecetadosPK();		
		mrPk.setCita(c.getId());
		mrPk.setMedicamento(med.getId());
		medicamentoRecetadoEJB.eliminar(mrPk);
		Messages.addFlashGlobalInfo("Se eliminó el medicamento con recetado!");
	}

	public String getCantidad() {
		return cantidad;
	}

	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}

	public String getDosis() {
		return dosis;
	}

	public void setDosis(String dosis) {
		this.dosis = dosis;
	}

	public String getRecomendaciones() {
		return recomendaciones;
	}

	public void setRecomendaciones(String recomendaciones) {
		this.recomendaciones = recomendaciones;
	}

	public int getMedicamentoSeleccionado() {
		return medicamentoSeleccionado;
	}

	public void setMedicamentoSeleccionado(int medicamentoSeleccionado) {
		this.medicamentoSeleccionado = medicamentoSeleccionado;
	}

	public List<Medicamento> getMedicamentos() {
		return medicamentos;
	}

	public void setMedicamentos(List<Medicamento> medicamentos) {
		this.medicamentos = medicamentos;
	}

	public List<MedicamentoRecetadoDTO> getMedicamentosRecetados() {
		return medicamentosRecetados;
	}

	public void setMedicamentosRecetados(List<MedicamentoRecetadoDTO> medicamentosRecetados) {
		this.medicamentosRecetados = medicamentosRecetados;
	}

	public String getPaciente() {
		return paciente;
	}

	public void setPaciente(String paciente) {
		this.paciente = paciente;
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

	public String getAnotaciones() {
		return anotaciones;
	}

	public void setAnotaciones(String anotaciones) {
		this.anotaciones = anotaciones;
	}

	public int getPatologiaSeleccionada() {
		return patologiaSeleccionada;
	}

	public void setPatologiaSeleccionada(int patologiaSeleccionada) {
		this.patologiaSeleccionada = patologiaSeleccionada;
	}

	public List<Patologia> getPatologias() {
		return patologias;
	}

	public void setPatologias(List<Patologia> patologias) {
		this.patologias = patologias;
	}
	
	
}
