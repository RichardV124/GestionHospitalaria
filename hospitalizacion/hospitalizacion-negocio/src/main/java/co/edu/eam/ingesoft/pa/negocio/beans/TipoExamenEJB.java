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
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.TipoExamen;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.TipoMedico;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Tratamiento;
import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;

@LocalBean
@Stateless
public class TipoExamenEJB {

	@PersistenceContext
	private EntityManager em;
	
	
	
	/**
	 * Metodo para crear un tipo de examen
	 * @param tipo
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void crearTipoExamen(TipoExamen tipo){
		TipoExamen busc=buscarTipoExamen(tipo.getId());
		//no existe, se puede crear...
		if(busc==null){
			em.persist(tipo);
		}else{
			throw new ExcepcionNegocio("Ya existe el tipo de examen");
		}
		
	}
	
	/**
	 * Metodo para buscar un tipo deExamen
	 * @param id del tipo de Examen
	 * @return el tipo de Examen.
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public TipoExamen buscarTipoExamen(int id){
		TipoExamen tipoExamen = em.find(TipoExamen.class, id);
		if(tipoExamen!=null){
			return tipoExamen;
		}
		return null;
	}
	
	
	/**
	 * metodo para listar los tipo de examenes
	 */
	public List<TipoExamen> listarTipoExamen(){	
		Query query = em.createNamedQuery(TipoExamen.CONSULTA_LISTAR_TIPO_EXAMEN);
		List<TipoExamen> lista = query.getResultList();
		return lista;
	}
}
