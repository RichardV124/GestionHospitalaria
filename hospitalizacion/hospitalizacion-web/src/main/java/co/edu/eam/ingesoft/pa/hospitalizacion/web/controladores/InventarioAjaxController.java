package co.edu.eam.ingesoft.pa.hospitalizacion.web.controladores;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Messages;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Farmacia;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.FarmaciaMedicamento;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.FarmaciaMedicamentoPK;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Medicamento;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.TipoMedicamento;
import co.edu.eam.ingesoft.pa.negocio.beans.FarmaciaEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.FarmaciaMedicamentoEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.MedicamentoEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.TipoMedicamentoEJB;
import co.edu.eam.ingesoft.pa.negocio.dtos.FarmaciaMedicamentoDTO;
import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;

@Named("inventarioAjaxController")
@ViewScoped
public class InventarioAjaxController implements Serializable{

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
	
	/**
	 * cantidad del medicamento
	 */
	@NotNull(message = "Debe ingresar la cantidad")
	private String cantidadMed;
	
	@EJB
	private TipoMedicamentoEJB tipoMedicamentoEJB;
	
	@EJB
	private MedicamentoEJB medicamentoEJB;
	
	@EJB
	private FarmaciaEJB farmaciaEJB;
	
	@EJB
	private FarmaciaMedicamentoEJB farmaciaMedicamentoEJB;
	
	private int tipoSeleccionado;
	
	private List<TipoMedicamento> tipos;
	
	private int farmaciaSeleccionada;
	
	private List<Farmacia> farmacias;
	
	private int medicamentoSeleccionado;
	
	private List<Medicamento> medicamentos;
	
	private List<FarmaciaMedicamentoDTO> inventario;
	
	@PostConstruct
	public void inicializar() {
		try {
			listarTipos();
			listarFarmacias();
			listarMedicamentos();
			listarInventario();
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
	
	public void listarFarmacias(){
		farmacias = farmaciaEJB.listarFarmacias();
	}

	public void listarMedicamentos(){
		medicamentos = medicamentoEJB.listarMedicamentos();
	}
	
	public void listarInventario(){
		inventario = farmaciaMedicamentoEJB.listarInventario();
	}
	
	public void registrarMedicamento() throws ParseException{
		Medicamento m = new Medicamento();
		m.setDescripcion(nombre);
//		Calendar calendario = GregorianCalendar.getInstance();
//		Date fechaV = calendario.getTime();
		Date fechaV = new SimpleDateFormat("dd-MM-yyyy").parse(fechaVencimiento);
		m.setFechaVencimiento(fechaV);
		m.setId(Integer.parseInt(numRef));
		TipoMedicamento tm = tipoMedicamentoEJB.buscar(tipoSeleccionado);
		m.setTipoMedicamento(tm);
		
		medicamentoEJB.crear(m);
		Messages.addFlashGlobalInfo("Se registró el medicamento con exito!");
		listarMedicamentos();
	}
	
	public void aniadirInventario(){
		FarmaciaMedicamento fm = new FarmaciaMedicamento();
		Farmacia f = farmaciaEJB.buscar(farmaciaSeleccionada);
		fm.setFarmacia(f);
		Medicamento m = medicamentoEJB.buscar(medicamentoSeleccionado);
		fm.setMedicamento(m);
		fm.setCantidad(Integer.parseInt(cantidadMed));
		farmaciaMedicamentoEJB.crear(fm);
		Messages.addFlashGlobalInfo("Se registró el inventario con exito!");
	}
	
	public void eliminarInventario(FarmaciaMedicamentoDTO inv){
		FarmaciaMedicamentoPK fmPK = new FarmaciaMedicamentoPK();
		fmPK.setFarmacia(inv.getIdFarmacia());
		fmPK.setMedicamento(inv.getIdMedicamento());
		farmaciaMedicamentoEJB.eliminar(fmPK);
		Messages.addFlashGlobalInfo("Se eliminó el item del inventario con exito!");
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

	public String getCantidadMed() {
		return cantidadMed;
	}

	public void setCantidadMed(String cantidadMed) {
		this.cantidadMed = cantidadMed;
	}

	public TipoMedicamentoEJB getTipoMedicamentoEJB() {
		return tipoMedicamentoEJB;
	}

	public void setTipoMedicamentoEJB(TipoMedicamentoEJB tipoMedicamentoEJB) {
		this.tipoMedicamentoEJB = tipoMedicamentoEJB;
	}

	public MedicamentoEJB getMedicamentoEJB() {
		return medicamentoEJB;
	}

	public void setMedicamentoEJB(MedicamentoEJB medicamentoEJB) {
		this.medicamentoEJB = medicamentoEJB;
	}

	public FarmaciaEJB getFarmaciaEJB() {
		return farmaciaEJB;
	}

	public void setFarmaciaEJB(FarmaciaEJB farmaciaEJB) {
		this.farmaciaEJB = farmaciaEJB;
	}

	public FarmaciaMedicamentoEJB getFarmaciaMedicamentoEJB() {
		return farmaciaMedicamentoEJB;
	}

	public void setFarmaciaMedicamentoEJB(FarmaciaMedicamentoEJB farmaciaMedicamentoEJB) {
		this.farmaciaMedicamentoEJB = farmaciaMedicamentoEJB;
	}

	public int getTipoSeleccionado() {
		return tipoSeleccionado;
	}

	public void setTipoSeleccionado(int tipoSeleccionado) {
		this.tipoSeleccionado = tipoSeleccionado;
	}

	public List<TipoMedicamento> getTipos() {
		return tipos;
	}

	public void setTipos(List<TipoMedicamento> tipos) {
		this.tipos = tipos;
	}

	public int getFarmaciaSeleccionada() {
		return farmaciaSeleccionada;
	}

	public void setFarmaciaSeleccionada(int farmaciaSeleccionada) {
		this.farmaciaSeleccionada = farmaciaSeleccionada;
	}

	public List<Farmacia> getFarmacias() {
		return farmacias;
	}

	public void setFarmacias(List<Farmacia> farmacias) {
		this.farmacias = farmacias;
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

	public List<FarmaciaMedicamentoDTO> getInventario() {
		return inventario;
	}

	public void setInventario(List<FarmaciaMedicamentoDTO> inventario) {
		this.inventario = inventario;
	}
	
}
