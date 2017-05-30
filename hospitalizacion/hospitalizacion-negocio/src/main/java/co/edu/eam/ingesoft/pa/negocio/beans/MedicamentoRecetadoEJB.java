package co.edu.eam.ingesoft.pa.negocio.beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Farmacia;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.MedicamentoRecetado;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.MedicamentosRecetadosPK;
import co.edu.eam.ingesoft.pa.negocio.dtos.MedicamentoRecetadoDTO;
import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;

@LocalBean
@Stateless
public class MedicamentoRecetadoEJB {

	@PersistenceContext
	private EntityManager em;
	
	/**
	 * metodo para listar las citas pendientes del paciente
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<MedicamentoRecetadoDTO> listarMedicamentosRecetados(int idCita) {
		Query query = em.createNativeQuery("SELECT MR.MEDICAMENTO_ID ID, M.DESCRIPCION NOMBRE, MR.CANTIDAD_RECETADA, MR.DOSIS "
				+ "FROM MEDICAMENTO_RECETADOS MR INNER JOIN MEDICAMENTO M ON M.ID=MR.MEDICAMENTO_ID WHERE MR.CITA_ID=?1");
		query.setParameter(1, idCita);
		List<MedicamentoRecetadoDTO> cp = query.getResultList();
		if (cp.isEmpty()) {
			throw new ExcepcionNegocio("No medicamentos recetados");
		} else {
			return cp;
		}
	}
	
	/**
	 * metodo para buscar una receta medica
	 * @param mrPK, pk de la receta medica a buscar
	 * @return la receta medica con la respectiva pk, o null de no encontrarlo
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public MedicamentoRecetado buscar(MedicamentosRecetadosPK mrPK){
		return em.find(MedicamentoRecetado.class, mrPK);
	}
	
	/**
	 * metodo para persistir una receta medica
	 * @param mr, receta medica a persistir
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void crear(MedicamentoRecetado mr){
		MedicamentosRecetadosPK mrPK = new MedicamentosRecetadosPK();
		mrPK.setCita(mr.getCita().getId());
		mrPK.setMedicamento(mr.getMedicamento().getId());
		MedicamentoRecetado ho = buscar(mrPK);
		if(ho==null){
			em.persist(mr);
		} else{
			throw new ExcepcionNegocio("el medicamento ya se recetó");
		}	
	}
	
	/**
	 * metodo para eliminar una receta medica registrada
	 * @param mrPK, pk de la receta medica a eliminar
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminar(MedicamentosRecetadosPK mrPK) {
		MedicamentoRecetado h = buscar(mrPK);
		if(h!=null){
			em.remove(h);
		} else{
			throw new ExcepcionNegocio("no se ha registrado la receta medica");
		}	
	}
	
	/**
	 * metodo para editar una receta medica
	 * @param rm, receta medica a editar
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void editar(MedicamentoRecetado rm) {
		MedicamentosRecetadosPK mrPK = new MedicamentosRecetadosPK();
		mrPK.setCita(rm.getCita().getId());
		mrPK.setMedicamento(rm.getMedicamento().getId());
		MedicamentoRecetado ho = buscar(mrPK);
		// no existe,no se puede editar...
		if (ho != null) {
			em.merge(rm);
		} else {
			throw new ExcepcionNegocio("No existe la receta medica a editar");
		}
	}

}
