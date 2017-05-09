package co.edu.eam.ingesoft.pa.negocio.beans;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Cita;

@LocalBean
@Stateless
public class CitaEJB {

	@PersistenceContext
	private EntityManager em;
	
	/**
	 * metodo para buscar una cita medica
	 * @param id de la cita medica
	 * @return
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Cita buscar(String id) {
		return em.find(Cita.class, id);
	}
	
	
}
