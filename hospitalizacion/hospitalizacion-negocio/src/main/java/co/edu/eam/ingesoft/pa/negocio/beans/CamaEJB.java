package co.edu.eam.ingesoft.pa.negocio.beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Cama;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.TipoMedico;
import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;


@LocalBean
@Stateless
public class CamaEJB {

	@PersistenceContext
	private EntityManager em;
	
	
	/**
	 * Metodo para crear una cama
	 * @param cama
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void crearCama(Cama cama){
		Cama busc=buscarCama(cama.getId());
		//no existe, se puede crear...
		if(busc==null){
			em.persist(cama);
		}else{
			throw new ExcepcionNegocio("Ya existe la cama");
		}
		
	}
	
	/**
	 * MEtodo para buscar una cama.
	 * @param id de la cama
	 * @return la cama.
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Cama buscarCama(int id){
		Cama cama = em.find(Cama.class, id);
		if(cama!=null){
			return cama;
		}
		return null;
	}
	
	/**
	 * metodo para listar las Camas
	 */
	public List<Cama> listarCamas(){	
		Query query = em.createNamedQuery(Cama.CONSULTA_LISTAR_CAMAS);
		List<Cama> lista = query.getResultList();
		return lista;
	}
	
}
