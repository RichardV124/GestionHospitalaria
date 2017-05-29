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
import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;

@LocalBean
@Stateless
public class FarmaciaEJB {

	@PersistenceContext
	private EntityManager em;
	
	/**
	 * metodo para buscar una farmacia
	 * @param id, identificador de la farmacia a buscar
	 * @return la farmacia con el respectivo id, o null de no encontrarlo
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Farmacia buscar(int id){
		return em.find(Farmacia.class, id);
	}
	
	/**
	 * metodo para persistir una farmacia
	 * @param h, farmacia a persistir
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void crear(Farmacia h){
		Farmacia ho = buscar(h.getId());
		if(ho==null){
			em.persist(h);
		} else{
			throw new ExcepcionNegocio("La farmacia ya está registrada");
		}	
	}
	
	/**
	 * metodo para eliminar una farmacia registrada
	 * @param id, identificador de la farmacia a eliminar
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminar(int id) {
		Farmacia h = buscar(id);
		if(h!=null){
			em.remove(h);
		} else{
			throw new ExcepcionNegocio("La farmacia no está registrado");
		}	
	}
	
	/**
	 * metodo para editar una farmacia
	 * @param h, farmacia a editar
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void editar(Farmacia h) {
		Farmacia ho = buscar(h.getId());
		// no existe,no se puede editar...
		if (ho != null) {
			em.merge(h);
		} else {
			throw new ExcepcionNegocio("No existe la farmacia a editar");
		}
	}
	
	/**
	 * metodo para listar las farmacias registradas
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Farmacia> listarFarmacias() {
		Query query = em.createNamedQuery(Farmacia.CONSULTA_LISTAR_FARMACIAS);
		List<Farmacia> far = query.getResultList();
		if (far.isEmpty()) {
			throw new ExcepcionNegocio("No hay farmacias registradas en la base de datos");
		} else {
			return far;
		}
	}
}
