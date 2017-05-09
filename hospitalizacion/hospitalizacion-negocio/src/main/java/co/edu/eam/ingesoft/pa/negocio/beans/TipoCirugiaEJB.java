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
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.TipoCirugia;
import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;


@LocalBean
@Stateless
public class TipoCirugiaEJB {

	@PersistenceContext
	private EntityManager em;
	
	
	/**
	 * Metodo para crear un tipo de cirugia
	 * @param sintoma
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void crearTipoCirugia(TipoCirugia tipo){
		TipoCirugia busc=buscarTipoCirugia(tipo.getId());
		//no existe, se puede crear...
		if(busc==null){
			em.persist(tipo);
		}else{
			throw new ExcepcionNegocio("Ya existe el tipo de cirugia");
		}
		
	}
	
	/**
	 * MEtodo para buscar un tipo de cirugia
	 * @param id del tipo de cirugia
	 * @return el tipo de cirugia.
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public TipoCirugia buscarTipoCirugia(int id){
		TipoCirugia tipo = em.find(TipoCirugia.class, id);
		if(tipo!=null){
			return tipo;
		}
		return null;
	}
	
	/**
	 * metodo para listar los sintomas
	 */
	public List<TipoCirugia> listarTipoCirugia(){	
		Query query = em.createNamedQuery(TipoCirugia.CONSULTA_LISTAR_TIPO_CIRUGIA);
		List<TipoCirugia> cus = query.getResultList();
		return cus;
	}
	
}
