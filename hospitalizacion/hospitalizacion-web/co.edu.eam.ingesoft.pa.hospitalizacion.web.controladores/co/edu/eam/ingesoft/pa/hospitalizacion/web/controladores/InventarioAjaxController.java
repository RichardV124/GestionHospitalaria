package co.edu.eam.ingesoft.pa.hospitalizacion.web.controladores;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Messages;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Medicamento;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Medico;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.TipoMedicamento;
import co.edu.eam.ingesoft.pa.negocio.beans.MedicamentoEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.TipoMedicamentoEJB;
import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;

@Named("inventarioAjaxController")
@ViewScoped
public class InventarioAjaxController {

	/**
	 * numero de referencia
	 */
	@NotNull(message = "Debe ingresar el numero de referencia")
	private String numRef;
	
	/**
	 * numero de referencia
	 */
	@NotNull(message = "Debe ingresar el nombre")
	private String nombre;
	
	/**
	 * fecha de vencimiento
	 */
	@NotNull(message = "Debe ingresar la fecha de vencimiento")
	private String fechaVencimiento;
	
	@EJB
	private TipoMedicamentoEJB tipoMedicamentoEJB;
	
	@EJB
	private MedicamentoEJB medicamentoEJB;
	
	private TipoMedicamento tipoSeleccionado;
	
	private List<TipoMedicamento> tipos;
	
	@PostConstruct
	public void inicializar() {
		try {
			listarTipos();
		} catch (ExcepcionNegocio e1) {
			Messages.addFlashGlobalError(e1.getMessage());
			e1.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Messages.addFlashGlobalInfo(e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	public void listarTipos(){
		tipos = tipoMedicamentoEJB.listarTipoMedicamentos();
	}

	public void registrarMedicamento() throws ParseException{
		Medicamento m = new Medicamento();
		m.setDescripcion(nombre);
		Date fechaV = new SimpleDateFormat("dd-MM-yyyy").parse(fechaVencimiento);
		m.setFechaVencimiento(fechaV);
		m.setId(Integer.parseInt(numRef));
		m.setTipoMedicamento(tipoSeleccionado);
		
		medicamentoEJB.crear(m);
		Messages.addFlashGlobalInfo("Se registró el medicamento con exito!");
	}
	
	
	
	public String getNumRef() {
		return numRef;
	}

	public void setNumRef(String numRef) {
		this.numRef = numRef;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public TipoMedicamento getTipoSeleccionado() {
		return tipoSeleccionado;
	}

	public void setTipoSeleccionado(TipoMedicamento tipoSeleccionado) {
		this.tipoSeleccionado = tipoSeleccionado;
	}

	public List<TipoMedicamento> getTipos() {
		return tipos;
	}

	public void setTipos(List<TipoMedicamento> tipos) {
		this.tipos = tipos;
	}
	
	
}
