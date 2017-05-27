package co.edu.eam.ingesoft.pa.negocio.beans;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Horario;
import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;

@LocalBean
@Stateless
public class HorarioEJB {

	
	@PersistenceContext
	private EntityManager em;
	
	/**
	 * metodo para buscar un horario
	 * @param id, identificador del horario a buscar
	 * @return el horario con el respectivo id, o null de no encontrarlo
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Horario buscar(int id){
		return em.find(Horario.class, id);
	}
	
	/**
	 * metodo para persistir un horario
	 * @param h, horario a persistir
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void crear(Horario h){
		Horario ho = buscar(h.getId());
		if(ho==null){
			em.persist(h);
		} else{
			throw new ExcepcionNegocio("El horario ya est� registrado");
		}	
	}
	
	/**
	 * metodo para eliminar un horario registrado
	 * @param id, identificador del horario a eliminar
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminar(int id) {
		Horario h = buscar(id);
		if(h!=null){
			em.remove(h);
		} else{
			throw new ExcepcionNegocio("El horario no est� registrado");
		}	
	}
	
	/**
	 * metodo para editar un horario
	 * @param h, horario a editar
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void editar(Horario h) {
		Horario ho = buscar(h.getId());
		// no existe,no se puede editar...
		if (ho != null) {
			em.merge(h);
		} else {
			throw new ExcepcionNegocio("No existe el horario a editar");
		}
	}
}