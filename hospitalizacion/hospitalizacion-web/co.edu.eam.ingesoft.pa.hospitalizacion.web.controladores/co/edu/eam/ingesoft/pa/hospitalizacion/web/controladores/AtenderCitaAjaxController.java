package co.edu.eam.ingesoft.pa.hospitalizacion.web.controladores;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Messages;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Medicamento;
import co.edu.eam.ingesoft.pa.negocio.beans.MedicamentoEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.MedicamentoRecetadoEJB;
import co.edu.eam.ingesoft.pa.negocio.dtos.MedicamentoRecetadoDTO;
import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;

@Named("atenderCitaAjaxController")
@ViewScoped
public class AtenderCitaAjaxController implements Serializable{

	@EJB
	private MedicamentoEJB medicamentoEJB;
	
	@EJB
	private MedicamentoRecetadoEJB medicamentoRecetadoEJB;
	
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
}
