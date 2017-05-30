package co.edu.eam.ingesoft.pa.hospitalizacion.web.controladores;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Messages;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Cama;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Quirofano;
import co.edu.eam.ingesoft.pa.negocio.beans.CamaEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.QuirofanoEJB;
import co.edu.eam.ingesoft.pa.negocio.dtos.CamaDTO;

@ViewScoped
@Named("instalacontroller")
public class InstalacionesAjaxController implements Serializable {

	@EJB
	private CamaEJB camaEJB;

	@EJB
	private QuirofanoEJB quirofanoEJB;

	// Componentes del quirofano

	@NotNull
	private String quirofanoBuscar;

	@NotNull
	private String nombreQuirofano;

	@NotNull
	private String descripcionQuirofano;

	@NotNull
	private String pisoQuirofano;

	private List<Quirofano> quirofanos;

	// Componentes de la cama

	@NotNull
	private String camaBuscar;

	private List<CamaDTO> camas;

	@NotNull
	private String pisoCama;

	@NotNull
	private String habitacionCama;

	@NotNull
	private String descripcionCama;

	@PostConstruct
	public void inicializar() {

		actualizarTabla();

	}

	public void actualizarTabla() {
		quirofanos = quirofanoEJB.listaQuirofano();
		camas = camaEJB.listarCamas();
	}

	// registrar
	public void crearQuirofano() {

		try {

			if (!descripcionQuirofano.isEmpty()) {

				System.out.println("entro");

				Quirofano q = new Quirofano();

				q.setDescripcion(descripcionQuirofano);
				q.setNombre(nombreQuirofano);
				q.setPiso(pisoQuirofano);

				quirofanoEJB.crear(q);

				limpiar();
				Messages.addFlashGlobalInfo("El quirofano se ha registrado con exito");
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
	public void crearCama() {

		try {

			if (!descripcionCama.isEmpty()) {

				System.out.println("entro");

				Cama c = new Cama();

				c.setDescripcion(descripcionCama);
				c.setHabitacion(habitacionCama);
				c.setPiso(pisoCama);
				camaEJB.crearCama(c);

				limpiar();
				Messages.addFlashGlobalInfo("La cama se ha registrado con exito");

				actualizarTabla();
			} else {
				Messages.addFlashGlobalError("Ingrese todos los datos");
				System.out.println("No entro");
			}

		} catch (Exception e) {
			Messages.addFlashGlobalError(e.getMessage());
			e.printStackTrace();
		}
	}

	// editar
	public void editarQuirofano() {

		try {

			if (quirofanoBuscar.isEmpty()) {

				Messages.addFlashGlobalError("Debe buscar primero el quirofano a editar");

			} else if (!descripcionQuirofano.isEmpty()) {

				System.out.println("entro");

				Quirofano q = quirofanoEJB.buscar(Integer.parseInt(quirofanoBuscar));

				q.setDescripcion(descripcionQuirofano);
				q.setNombre(nombreQuirofano);
				q.setPiso(pisoQuirofano);

				quirofanoEJB.editar(q);
				actualizarTabla();
				limpiar();
				Messages.addFlashGlobalInfo("El quirofano se ha editado con exito");
			} else {
				Messages.addFlashGlobalError("Ingrese todos los datos");
				System.out.println("No entro");
			}

		} catch (Exception e) {
			Messages.addFlashGlobalError(e.getMessage());
			e.printStackTrace();
		}
	}

	// editar
	public void editarCama() {

		try {

			if (camaBuscar.isEmpty()) {

				Messages.addFlashGlobalError("Debe buscar primero la cama a editar");

			} else if (!descripcionCama.isEmpty()) {

				System.out.println("entro");

				Cama c = camaEJB.buscarCama(Integer.parseInt(camaBuscar));

				c.setDescripcion(descripcionCama);
				c.setHabitacion(habitacionCama);
				c.setPiso(pisoCama);
				camaEJB.editar(c);
				actualizarTabla();
				limpiar();
				Messages.addFlashGlobalInfo("La cama se ha editado con exito");
			} else {
				Messages.addFlashGlobalError("Ingrese todos los datos");
				System.out.println("No entro");
			}

		} catch (Exception e) {
			Messages.addFlashGlobalError(e.getMessage());
			e.printStackTrace();
		}
	}

	public void buscarQuirofano() {

		if (!quirofanoBuscar.isEmpty()) {

			Quirofano qui = quirofanoEJB.buscar(Integer.parseInt(quirofanoBuscar));
			if (qui != null) {
				nombreQuirofano = qui.getNombre();
				habitacionCama = qui.getPiso();
				descripcionQuirofano = qui.getDescripcion();
			} else {
				Messages.addFlashGlobalWarn("El quirofano no existe");
				// limpiar();
				//
			}
		} else {
			Messages.addFlashGlobalWarn("Ingrese el id del quirofano a buscar");
		}

	}

	public void buscarCama() {

		if (!camaBuscar.isEmpty()) {

			Cama ca = camaEJB.buscarCama(Integer.parseInt(camaBuscar));
			if (ca != null) {
				descripcionCama = ca.getDescripcion();
				pisoCama = ca.getPiso();
				habitacionCama = ca.getHabitacion();
			} else {
				Messages.addFlashGlobalWarn("La cama no existe");
				// limpiar();
				//
			}
		} else {
			Messages.addFlashGlobalWarn("Ingrese el id de la cama a buscar");
		}

	}

	public void eliminarCama(CamaDTO cama) {
		try {
			camaEJB.eliminar(cama.getId());
			Messages.addFlashGlobalInfo("Se ha eliminado la cama con exito!");
		} catch (Exception e) {
			Messages.addFlashGlobalInfo(e.getMessage());
			e.printStackTrace();
		}
	}

	public void eliminarQuirofano(Quirofano qui) {
		try {
			quirofanoEJB.eliminar(qui.getId());
			Messages.addFlashGlobalInfo("Se ha eliminado el quirofano con exito!");
		} catch (Exception e) {
			Messages.addFlashGlobalInfo(e.getMessage());
			e.printStackTrace();
		}
	}

	public void limpiar() {
		nombreQuirofano = "";
		// pisoQuirofano = "";
		descripcionQuirofano = "";
		pisoQuirofano = "";
		pisoCama = "";
		descripcionCama = "";
		habitacionCama = "";
	}

	public String getQuirofanoBuscar() {
		return quirofanoBuscar;
	}

	public void setQuirofanoBuscar(String quirofanoBuscar) {
		this.quirofanoBuscar = quirofanoBuscar;
	}

	public String getNombreQuirofano() {
		return nombreQuirofano;
	}

	public void setNombreQuirofano(String nombreQuirofano) {
		this.nombreQuirofano = nombreQuirofano;
	}

	public String getDescripcionQuirofano() {
		return descripcionQuirofano;
	}

	public void setDescripcionQuirofano(String descripcionQuirofano) {
		this.descripcionQuirofano = descripcionQuirofano;
	}

	public String getPisoQuirofano() {
		return pisoQuirofano;
	}

	public void setPisoQuirofano(String pisoQuirofano) {
		this.pisoQuirofano = pisoQuirofano;
	}

	public List<Quirofano> getQuirofanos() {
		return quirofanos;
	}

	public void setQuirofanos(List<Quirofano> quirofanos) {
		this.quirofanos = quirofanos;
	}

	public String getCamaBuscar() {
		return camaBuscar;
	}

	public void setCamaBuscar(String camaBuscar) {
		this.camaBuscar = camaBuscar;
	}

	public List<CamaDTO> getCamas() {
		return camas;
	}

	public void setCamas(List<CamaDTO> camas) {
		this.camas = camas;
	}

	public String getPisoCama() {
		return pisoCama;
	}

	public void setPisoCama(String pisoCama) {
		this.pisoCama = pisoCama;
	}

	public String getHabitacionCama() {
		return habitacionCama;
	}

	public void setHabitacionCama(String habitacionCama) {
		this.habitacionCama = habitacionCama;
	}

	public String getDescripcionCama() {
		return descripcionCama;
	}

	public void setDescripcionCama(String descripcionCama) {
		this.descripcionCama = descripcionCama;
	}

}
