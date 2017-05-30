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
import co.edu.eam.ingesoft.pa.negocio.beans.CitaEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.MedicamentoEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.MedicamentoRecetadoEJB;
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
	
	private Medicamento medicamentoSeleccionado;
	
	private List<Medicamento> medicamentos;
	
	private List<MedicamentoRecetadoDTO> medicamentosRecetados;
	
	
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
		mr.setMedicamento(medicamentoSeleccionado);
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
}
