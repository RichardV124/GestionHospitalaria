package co.edu.eam.ingesoft.pa.negocio.beans;

import java.util.List;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Cirugia;
import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;

public class CirugiaEJB {
	
	@PersistenceContext
	private EntityManager em;
	
	
	/**
	 * Metodo para crear una cirugia	
	 * @param cirugia
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void buscarCirugia(Cirugia cirugia){
		Cirugia busc=buscarCirugia(cirugia.getId());
		//no existe, se puede crear...
		if(busc==null){
			em.persist(cirugia);
		}else{
			throw new ExcepcionNegocio("Ya existe la cama");
		}
		
	}
	
	/**
	 * MEtodo para buscar una cirugia.
	 * @param id de la cirugia
	 * @return la cirugia.
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Cirugia buscarCirugia(int id){
		Cirugia cirugia = em.find(Cirugia.class, id);
		if(cirugia!=null){
			return cirugia;
		}
		return null;
	}
	
	/**
	 * metodo para listar las cirugia
	 */
	public List<Cirugia> listarCirugia(){	
		Query query = em.createNamedQuery(Cirugia.CONSULTA_LISTAR_CIRUGIAS);
		List<Cirugia> lista = query.getResultList();
		return lista;
	}

}
