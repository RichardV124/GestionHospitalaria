package co.edu.eam.ingesoft.pa.negocio.beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Sintoma;
import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;


@LocalBean
@Stateless
public class SintomaEJB {

	@PersistenceContext
	private EntityManager em;
	
	
	/**
	 * Metodo para crear un sintoma
	 * @param sintoma
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void crearSintoma(Sintoma sintoma){
		Sintoma busc=buscarSintoma(sintoma.getId());
		//no existe, se puede crear...
		if(busc==null){
			em.persist(sintoma);
		}else{
			throw new ExcepcionNegocio("Ya existe el sintoma");
		}
		
	}
	
	/**
	 * MEtodo para buscar una sintoma.
	 * @param id de la sintoma
	 * @return el sintoma.
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Sintoma buscarSintoma(int id){
		Sintoma sintoma = em.find(Sintoma.class, id);
		if(sintoma!=null){
			return sintoma;
		}
		return null;
	}
	
	/**
	 * metodo para listar los sintomas
	 */
	public List<Sintoma> listarSintomas(){	
		Query query = em.createNamedQuery(Sintoma.CONSULTA_LISTAR_SINTOMAS);
		List<Sintoma> cus = query.getResultList();
		return cus;
	}
	
}
