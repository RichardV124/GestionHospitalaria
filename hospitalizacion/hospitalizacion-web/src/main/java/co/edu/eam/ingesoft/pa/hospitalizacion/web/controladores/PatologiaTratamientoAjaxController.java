package co.edu.eam.ingesoft.pa.hospitalizacion.web.controladores;

import java.io.Serializable;
import java.text.ParseException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Messages;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Cama;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Paciente;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Patologia;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.PatologiaTratamiento;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Quirofano;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Tratamiento;
import co.edu.eam.ingesoft.avanzada.persistencia.enumeraciones.GeneroEnum;
import co.edu.eam.ingesoft.pa.negocio.beans.CamaEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.PatologiaEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.PatologiaTratamientoEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.QuirofanoEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.TratamientoEJB;
import co.edu.eam.ingesoft.pa.negocio.dtos.CamaDTO;
import co.edu.eam.ingesoft.pa.negocio.dtos.PatologiaTratamientoDTO;
import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;

@ViewScoped
@Named("patotratacontroller")
public class PatologiaTratamientoAjaxController implements Serializable {

	@EJB
	private PatologiaEJB patologiaEJB;

	@EJB
	private TratamientoEJB tratamientoEJB;

	@EJB
	private PatologiaTratamientoEJB patologiaTratamientoEJB;

	// Componentes de la patologia

	@NotNull
	private String patologiaBuscar;

	@NotNull
	private String descripcionPatologia;

	@NotNull
	private String tipoPatologia;

	@NotNull
	private String sintomasPatologia;

	
	private List<Patologia> patologias;

	@NotNull
	private String patologiaSeleccionada;

	// Componentes del tratamiento

	@NotNull
	private String tratamientoBuscar;

	private List<Tratamiento> tratamientos;

	@NotNull
	private String nombreTratamiento;

	@NotNull
	private String descripcionTratamiento;

	@NotNull
	private String tratamientoSeleccionada;

	private List<PatologiaTratamientoDTO> patologiasTratamientos;

	@PostConstruct
	public void inicializar() {

		try {
			
			listarTratamientos();
			listarPatologias();
			listarPatologiaTratamiento();
			
			
		} catch (

		ExcepcionNegocio e1) {
			Messages.addFlashGlobalError(e1.getMessage());
			e1.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Messages.addFlashGlobalInfo(e.getMessage());
			e.printStackTrace();
		}

	}

	// registrar
	public void crearPatologia() {

		try {

			if (!descripcionPatologia.isEmpty() || !sintomasPatologia.isEmpty()) {

				System.out.println("entro");

				Patologia p = new Patologia();

				p.setNombre(descripcionPatologia);
				p.setSintomas(sintomasPatologia);
				p.setTipo(tipoPatologia);

				patologiaEJB.crearPatologia(p);
				limpiar();
				Messages.addFlashGlobalInfo("La patologia se ha registrado con exito");
				listarPatologias();
			} else {
				Messages.addFlashGlobalError("Ingrese todos los datos");
				System.out.println("No entro");
			}

		} catch (Exception e) {
			Messages.addFlashGlobalError(e.getMessage());
			e.printStackTrace();
		}
	}

	// registrar
	public void crearTratamiento() {

		try {

			if (!descripcionTratamiento.isEmpty() || !nombreTratamiento.isEmpty()) {

				System.out.println("entro");

				Tratamiento t = new Tratamiento();

				t.setDescripcion(descripcionTratamiento);
				t.setNombre(nombreTratamiento);
				tratamientoEJB.crearTratamiento(t);

				limpiar();
				Messages.addFlashGlobalInfo("El tratamiento se ha registrado con exito");
				listarTratamientos();
			} else {
				Messages.addFlashGlobalError("Ingrese todos los datos");
				System.out.println("No entro");
			}

		} catch (Exception e) {
			Messages.addFlashGlobalError(e.getMessage());
			e.printStackTrace();
		}
	}

	// registrar
	public void asignarTratamiento() {

		try {

			if (!tratamientoSeleccionada.isEmpty() || !patologiaSeleccionada.isEmpty()) {

				System.out.println("entro");

				PatologiaTratamiento pt = new PatologiaTratamiento();

				Patologia pat = patologiaEJB.buscarPatologia(Integer.parseInt(patologiaSeleccionada));
				Tratamiento tra = tratamientoEJB.buscarTratamiento(Integer.parseInt(tratamientoSeleccionada));

				pt.setPatologia(pat);
				pt.setTratamiento(tra);

				patologiaTratamientoEJB.asignarTratamiento(pt);

				limpiar();
				Messages.addFlashGlobalInfo(
						"Se ha asignado el tratamiento " + tra.getNombre() + " a la patologia " + pat.getNombre());
				listarPatologiaTratamiento();

			} else {
				Messages.addFlashGlobalError("Ingrese todos los datos");
				System.out.println("No entro");
			}

		} catch (Exception e) {
			Messages.addFlashGlobalError(e.getMessage());
			e.printStackTrace();
		}
	}

	// registrar
	public void editarPatologia() {

		try {

			if (!descripcionPatologia.isEmpty() || !sintomasPatologia.isEmpty()) {

				System.out.println("entro");

				Patologia p = new Patologia();

				p.setNombre(descripcionPatologia);
				p.setSintomas(sintomasPatologia);
				p.setTipo(tipoPatologia);

				patologiaEJB.editar(p);

				limpiar();
				Messages.addFlashGlobalInfo("La patologia se ha registrado con exito");
			} else {
				Messages.addFlashGlobalError("Ingrese todos los datos");
				System.out.println("No entro");
			}

		} catch (Exception e) {
			Messages.addFlashGlobalError(e.getMessage());
			e.printStackTrace();
		}
	}

	// registrar
	public void editarTratamiento() {

		try {

			if (!descripcionTratamiento.isEmpty() || !nombreTratamiento.isEmpty()) {

				System.out.println("entro");

				Tratamiento t = new Tratamiento();

				t.setDescripcion(descripcionTratamiento);
				t.setNombre(nombreTratamiento);
				tratamientoEJB.editar(t);

				limpiar();
				Messages.addFlashGlobalInfo("El tratamiento se ha registrado con exito");

			} else {
				Messages.addFlashGlobalError("Ingrese todos los datos");
				System.out.println("No entro");
			}

		} catch (Exception e) {
			Messages.addFlashGlobalError(e.getMessage());
			e.printStackTrace();
		}
	}

	public void buscarTratamiento() {

		if (!tratamientoBuscar.isEmpty()) {

			Tratamiento tra = tratamientoEJB.buscarTratamiento(Integer.parseInt(tratamientoBuscar));
			if (tra != null) {
				nombreTratamiento = tra.getNombre();
				descripcionTratamiento = tra.getDescripcion();

			} else {
				Messages.addFlashGlobalWarn("El tratamiento no existe");
				// limpiar();
				//
			}
		} else {
			Messages.addFlashGlobalWarn("Ingrese el id del tratamiento a buscar");
		}

	}

	public void buscarPatologia() {

		if (!patologiaBuscar.isEmpty()) {

			Patologia pa = patologiaEJB.buscarPatologia(Integer.parseInt(patologiaBuscar));
			if (pa != null) {

				descripcionPatologia = pa.getNombre();
				tipoPatologia = pa.getTipo();
				sintomasPatologia = pa.getSintomas();

			} else {
				Messages.addFlashGlobalWarn("La patologia no existe");
				// limpiar();
				//
			}
		} else {
			Messages.addFlashGlobalWarn("Ingrese el id de la patologia a buscar");
		}

	}

	// public void eliminarCama(CamaDTO cama) {
	// try {
	// camaEJB.eliminar(cama.getId());
	// Messages.addFlashGlobalInfo("Se ha eliminado la cuenta asociada con
	// exito!");
	// } catch (Exception e) {
	// Messages.addFlashGlobalInfo(e.getMessage());
	// e.printStackTrace();
	// }
	// }

	public void limpiar() {
		sintomasPatologia = "";
		descripcionPatologia = "";

		nombreTratamiento = "";
		// pisoQuirofano = "";
		descripcionTratamiento = "";
	}

	/**
	 * metodo para llenar el combo con las patologias
	 */
	public void listarPatologias() {
		try {
			patologias = patologiaEJB.listarPatologias();
		} catch (ExcepcionNegocio e1) {
			Messages.addFlashGlobalError(e1.getMessage());
			e1.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Messages.addFlashGlobalInfo(e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * metodo para llenar el combo con los tratamientos
	 */
	public void listarTratamientos() {
		try {
			tratamientos = tratamientoEJB.listarTratamientosPatologia();
		} catch (ExcepcionNegocio e1) {
			Messages.addFlashGlobalError(e1.getMessage());
			e1.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Messages.addFlashGlobalInfo(e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * metodo para llenar el combo con los tratamientos
	 */
	public void listarPatologiaTratamiento() {
		try {
			patologiasTratamientos = patologiaEJB.listarTratamientosPatologia();
		} catch (ExcepcionNegocio e1) {
			Messages.addFlashGlobalError(e1.getMessage());
			e1.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Messages.addFlashGlobalInfo(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void eliminartratamientopatologia(PatologiaTratamientoDTO pt) {
		try{
		patologiaTratamientoEJB.eliminar(pt);
		Messages.addFlashGlobalInfo("Se ha eliminado la cuenta asociada con exito!");
		}catch(Exception e){
			Messages.addFlashGlobalInfo(e.getMessage());
			e.printStackTrace();
		}
	}

	public String getPatologiaBuscar() {
		return patologiaBuscar;
	}

	public void setPatologiaBuscar(String patologiaBuscar) {
		this.patologiaBuscar = patologiaBuscar;
	}

	public String getDescripcionPatologia() {
		return descripcionPatologia;
	}

	public void setDescripcionPatologia(String descripcionPatologia) {
		this.descripcionPatologia = descripcionPatologia;
	}

	public String getTipoPatologia() {
		return tipoPatologia;
	}

	public void setTipoPatologia(String tipoPatologia) {
		this.tipoPatologia = tipoPatologia;
	}

	public String getSintomasPatologia() {
		return sintomasPatologia;
	}

	public void setSintomasPatologia(String sintomasPatologia) {
		this.sintomasPatologia = sintomasPatologia;
	}

	public List<Patologia> getPatologias() {
		return patologias;
	}

	public void setPatologias(List<Patologia> patologias) {
		this.patologias = patologias;
	}

	public String getTratamientoBuscar() {
		return tratamientoBuscar;
	}

	public void setTratamientoBuscar(String tratamientoBuscar) {
		this.tratamientoBuscar = tratamientoBuscar;
	}

	public List<Tratamiento> getTratamientos() {
		return tratamientos;
	}

	public void setTratamientos(List<Tratamiento> tratamientos) {
		this.tratamientos = tratamientos;
	}

	public String getNombreTratamiento() {
		return nombreTratamiento;
	}

	public void setNombreTratamiento(String nombreTratamiento) {
		this.nombreTratamiento = nombreTratamiento;
	}

	public String getDescripcionTratamiento() {
		return descripcionTratamiento;
	}

	public void setDescripcionTratamiento(String descripcionTratamiento) {
		this.descripcionTratamiento = descripcionTratamiento;
	}

	public String getPatologiaSeleccionada() {
		return patologiaSeleccionada;
	}

	public void setPatologiaSeleccionada(String patologiaSeleccionada) {
		this.patologiaSeleccionada = patologiaSeleccionada;
	}

	public String getTratamientoSeleccionada() {
		return tratamientoSeleccionada;
	}

	public void setTratamientoSeleccionada(String tratamientoSeleccionada) {
		this.tratamientoSeleccionada = tratamientoSeleccionada;
	}

	public List<PatologiaTratamientoDTO> getPatologiasTratamientos() {
		return patologiasTratamientos;
	}

	public void setPatologiasTratamientos(List<PatologiaTratamientoDTO> patologiasTratamientos) {
		this.patologiasTratamientos = patologiasTratamientos;
	}
	
	

}
