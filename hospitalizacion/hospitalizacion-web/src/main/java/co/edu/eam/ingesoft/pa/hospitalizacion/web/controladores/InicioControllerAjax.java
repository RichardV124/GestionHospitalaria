package co.edu.eam.ingesoft.pa.hospitalizacion.web.controladores;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;

@Named("inicioAjax")
@ViewScoped
public class InicioControllerAjax implements Serializable {

	@PostConstruct
	public void inicializar() {
		
	}
	
	public String redirigirHistorialMedico(){
		return "/paginas/seguro/historialmedico.xhtml?faces-redirect=true";
	}
	
	public String redirigirGestionInstalacion(){
		return "/paginas/seguro/gestioninstalaciones.xhtml?faces-redirect=true";
	}
	
	public String redirigirGestionMedico(){
		return "/paginas/seguro/gestionmedicoxhtml?faces-redirect=true";
	}
	
	public String redirigirGestionPatologia(){
		return "/paginas/seguro/gestionpatologia.xhtml?faces-redirect=true";
	}


}