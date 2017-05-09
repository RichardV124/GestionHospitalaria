package co.edu.eam.ingesoft.pa.negocio.beans;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Cita;
import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;

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
	public Cita buscar(int id) {
		return em.find(Cita.class, id);
	}
	
	/**
	 * metodo para registrar una cita medica
	 * @param c, cita medica a registrar
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void crear(Cita c){
		Cita cm = buscar(c.getId());
		if(cm==null){
			em.persist(c);
		} else{
			throw new ExcepcionNegocio("La cita médica ya se ha registrado");
		}	
	}
	
	/**
	 * metodo para editar una cita medica registrada
	 * @param c, cita medica a editar
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void editar(Cita c) {
		Cita cm = buscar(c.getId());
		// no existe,no se puede editar...
		if (cm != null) {
			em.merge(c);
		} else {
			throw new ExcepcionNegocio("No existe la cita médica a editar");
		}
	}
	
	/**
	 * metodo para eliminar una cita medica asignada
	 * @param id, identificador de la cita medica
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminar(int id) {
		Cita c = buscar(id);
		if(c!=null){
			em.remove(c);
		} else{
			throw new ExcepcionNegocio("La Cita medica no está registrado");
		}	
	}
	
}
