package co.edu.eam.ingesoft.pa.hospitalizacion.web.controladores;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Messages;

import co.edu.eam.ingesoft.pa.negocio.beans.CitaEJB;
import co.edu.eam.ingesoft.pa.negocio.dtos.CitasDiaDTO;
import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;

@Named("citasDiaAjaxController")
@ViewScoped
public class CitasDiaAjaxController implements Serializable{

	@EJB
	private CitaEJB citaEJB;
	
	private List<CitasDiaDTO> citas;
	
	
	@PostConstruct
	public void inicializar() {
		try {
			listarCitasDia();
		} catch (ExcepcionNegocio e1) {
			Messages.addFlashGlobalError(e1.getMessage());
			e1.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Messages.addFlashGlobalInfo(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void listarCitasDia(){
		citas = citaEJB.listarCitasDia(1);
	}
	
	public void Atender(){
		
	}

	public List<CitasDiaDTO> getCitas() {
		return citas;
	}

	public void setCitas(List<CitasDiaDTO> citas) {
		this.citas = citas;
	}
	
	
}
