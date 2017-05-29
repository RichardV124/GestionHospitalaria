package co.edu.eam.ingesoft.pa.negocio.beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.FarmaciaMedicamento;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.FarmaciaMedicamentoPK;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.HorarioMedico;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.HorarioMedicoPK;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Medicamento;
import co.edu.eam.ingesoft.pa.negocio.dtos.FarmaciaMedicamentoDTO;
import co.edu.eam.ingesoft.pa.negocio.dtos.HorarioMedicoDTO;
import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;

@LocalBean
@Stateless
public class FarmaciaMedicamentoEJB {

	@PersistenceContext
	private EntityManager em;

	/**
	 * metodo para buscar un inventario 
	 * @param id, identificador del inventario
	 * @return el inventario con el respectivo id, o null si no se encuenra un medico
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public FarmaciaMedicamento buscar(FarmaciaMedicamentoPK fmPK) {
		return em.find(FarmaciaMedicamento.class, fmPK);
	}

	/**
	 * metodo para el registro de un inventario
	 * @param m Medico a registrar
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void crear(FarmaciaMedicamento fm) {
		FarmaciaMedicamentoPK fmPK = new FarmaciaMedicamentoPK();
		fmPK.setFarmacia(fm.getFarmacia().getId());
		fmPK.setMedicamento(fm.getMedicamento().getId());
		FarmaciaMedicamento fmb = buscar(fmPK);
		if (fmb == null) {
			em.persist(fm);
		} else {
			int nuevaCantidad = fm.getCantidad() + fmb.getCantidad();
			fmb.setCantidad(nuevaCantidad);
			em.merge(fmb);
		}
	}

	/**
	 * metodo para eliminar un medico registrado
	 * 
	 * @param numId,
	 *            numero de identificacion del medico a eliminar
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminar(FarmaciaMedicamentoPK fmPK) {
		FarmaciaMedicamento h = buscar(fmPK);
		if (h != null) {
			em.remove(h);
		} else {
			throw new ExcepcionNegocio("El inventario no ha sido registrado");
		}
	}

	/**
	 * metodo para la edicion d eun medico registrado
	 * 
	 * @param m
	 *            Medico a editar
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void editar(FarmaciaMedicamento fm) {
		FarmaciaMedicamentoPK fmPK = new FarmaciaMedicamentoPK();
		fmPK.setFarmacia(fm.getFarmacia().getId());
		fmPK.setMedicamento(fm.getMedicamento().getId());
		FarmaciaMedicamento fmb = buscar(fmPK);
		// no existe,no se puede editar...
		if (fmb != null) {
			em.merge(fm);
		} else {
			throw new ExcepcionNegocio("No existe el inventario a editar");
		}
	}
	
	/**
	 * metodo para listar el inventario
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<FarmaciaMedicamentoDTO> listarInventario() {
		Query query = em.createNativeQuery("SELECT M.ID, M.DESCRIPCION MEDICAMENTO,FM.CANTIDAD, F.DESCRIPCION FARMACIA"
				+"FROM FARMACIA_MEDICAMENTO FM INNER JOIN MEDICAMENTO M ON M.ID=FM.MEDICAMENTO_ID"
				+"INNER JOIN FARMACIA F ON F.ID=FM.FARMACIA_ID");
		List<FarmaciaMedicamentoDTO> hms = query.getResultList();
		if (hms.isEmpty()) {
			throw new ExcepcionNegocio("No hay inventario registrado en la base de datos");
		} else {
			return hms;
		}
	}
}
