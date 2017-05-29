package co.edu.eam.ingesoft.pa.negocio.beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Horario;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Medicamento;
import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;

@LocalBean
@Stateless
public class MedicamentoEJB {

	
	@PersistenceContext
	private EntityManager em;
	
	/**
	 * metodo para buscar un medicamento
	 * @param id, identificador del medicamento a buscar
	 * @return el medicamento con el respectivo id, o null de no encontrarlo
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Medicamento buscar(int id){
		return em.find(Medicamento.class, id);
	}
	
	/**
	 * metodo para persistir un medicamento
	 * @param h, medicamento a persistir
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void crear(Medicamento h){
		Medicamento ho = buscar(h.getId());
		if(ho==null){
			em.persist(h);
		} else{
			throw new ExcepcionNegocio("El medicamento ya está registrado");
		}	
	}
	
	/**
	 * metodo para eliminar un medicamento registrado
	 * @param id, identificador del medicamento a eliminar
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminar(int id) {
		Medicamento h = buscar(id);
		if(h!=null){
			em.remove(h);
		} else{
			throw new ExcepcionNegocio("El medicamento no está registrado");
		}	
	}
	
	/**
	 * metodo para editar un horario
	 * @param h, horario a editar
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void editar(Medicamento h) {
		Medicamento ho = buscar(h.getId());
		// no existe,no se puede editar...
		if (ho != null) {
			em.merge(h);
		} else {
			throw new ExcepcionNegocio("No existe el horario a editar");
		}
	}
	
	/**
	 * metodo para listar los horarios registrados
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Medicamento> listarMedicamentos() {
		Query query = em.createNamedQuery(Medicamento.CONSULTA_LISTAR_MEDICAMENTOS);
		List<Medicamento> med = query.getResultList();
		if (med.isEmpty()) {
			throw new ExcepcionNegocio("No hay medicamentos registrados en la base de datos");
		} else {
			return med;
		}
	}
}
