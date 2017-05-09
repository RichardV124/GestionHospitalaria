package co.edu.eam.ingesoft.pa.negocio.beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Patologia;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Sintoma;
import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;


@LocalBean
@Stateless
public class PatologiaEJB {

	@PersistenceContext
	private EntityManager em;
	
	
	/**
	 * Metodo para crear un sintoma
	 * @param sintoma
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void crearPatologia(Patologia patologia){
		Patologia busc=buscarPatologia(patologia.getId());
		//no existe, se puede crear...
		if(busc==null){
			em.persist(patologia);
		}else{
			throw new ExcepcionNegocio("Ya existe el sintoma");
		}
		
	}
	
	/**
	 * MEtodo para buscar una patologia
	 * @param id de la patologia
	 * @return la patologia.
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Patologia buscarPatologia(int id){
		Patologia sintoma = em.find(Patologia.class, id);
		if(sintoma!=null){
			return sintoma;
		}
		return null;
	}
	
	/**
	 * metodo para listar las patologias
	 */
	public List<Patologia> listarSintomasPatologia(){	
		Query query = em.createNamedQuery(Patologia.CONSULTA_LISTAR_PATOLOGIAS);
		List<Patologia> cus = query.getResultList();
		return cus;
	}
	
}
