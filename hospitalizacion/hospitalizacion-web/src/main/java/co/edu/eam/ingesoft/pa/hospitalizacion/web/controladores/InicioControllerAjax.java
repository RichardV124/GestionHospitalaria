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
		return "/paginas/seguro/gestionmedico.xhtml?faces-redirect=true";
	}
	
	public String redirigirGestionPatologia(){
		return "/paginas/seguro/gestionpatologia.xhtml?faces-redirect=true";
	}
	
	public String redirigirGestionPaciente(){
		return "/paginas/seguro/gestionpaciente.xhtml?faces-redirect=true";
	}
	
	public String redirigirGestionInventario(){
		return "/paginas/seguro/gestioninventario.xhtml?faces-redirect=true";
	}
	
	public String redirigirAtenderCita(){
		return "/paginas/seguro/atendercita.xhtml?faces-redirect=true";
	}
	
	public String redirigirPedirCita(){
		return "/paginas/seguro/pedircita.xhtml?faces-redirect=true";
	}
	
	public String redirigirCitasDelDia(){
		return "/paginas/seguro/citasdeldia.xhtml?faces-redirect=true";
	}
	
	public String redirigirGestionHorarios(){
		return "/paginas/seguro/gestionhorarios.xhtml?faces-redirect=true";
	}


}