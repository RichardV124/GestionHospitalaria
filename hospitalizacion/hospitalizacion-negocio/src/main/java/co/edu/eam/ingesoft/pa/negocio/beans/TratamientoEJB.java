package co.edu.eam.ingesoft.pa.negocio.beans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Cama;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Tratamiento;
import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;

@LocalBean
@Stateless
public class TratamientoEJB {

	@PersistenceContext
	private EntityManager em;
	
	
	
	/**
	 * Metodo para crear un tratamiento
	 * @param tratamiento
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void crearTratamiento(Tratamiento tratamiento){
		Tratamiento busc=buscarTratamiento(tratamiento.getId());
		//no existe, se puede crear...
		if(busc==null){
			em.persist(tratamiento);
		}else{
			throw new ExcepcionNegocio("Ya existe el tratamiento");
		}
		
	}
	
	/**
	 * Metodo para buscar un tratamiento
	 * @param id de la tratamiento
	 * @return el tratamiento.
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Tratamiento buscarTratamiento(int id){
		Tratamiento sintoma = em.find(Tratamiento.class, id);
		if(sintoma!=null){
			return sintoma;
		}
		return null;
	}
	
	
	/**
	 * metodo para listar los tratamientos
	 */
	public List<Tratamiento> listarTratamientosPatologia(){	
		Query query = em.createNamedQuery(Tratamiento.CONSULTA_LISTAR_TRATAMIENTOS);
		List<Tratamiento> lista = query.getResultList();
		return lista;
	}
	
	/**
	 * metodo para la edicion de un tratamiento registrada
	 * 
	 * @param t
	 *            tratamiento a editar
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void editar(Tratamiento t) {
		Tratamiento tra = buscarTratamiento(t.getId());
		// no existe,no se puede editar...
		if (tra != null) {
			em.merge(t);
		} else {
			throw new ExcepcionNegocio("No existe el tratamiento a editar");
		}
	}
}
