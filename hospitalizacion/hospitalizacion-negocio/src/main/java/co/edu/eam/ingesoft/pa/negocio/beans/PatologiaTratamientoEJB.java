package co.edu.eam.ingesoft.pa.negocio.beans;


import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Cama;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.PatologiaTratamiento;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.PatologiaTratamientoPK;
import co.edu.eam.ingesoft.pa.negocio.dtos.PatologiaTratamientoDTO;
import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;


@LocalBean
@Stateless
public class PatologiaTratamientoEJB {

	@PersistenceContext
	private EntityManager em;
	
		
	/**
	 * Metodo para crear un sintoma
	 * @param sintoma
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void asignarTratamiento(PatologiaTratamiento patologiaTratamiento){
	
		PatologiaTratamientoPK ptPK = new PatologiaTratamientoPK();
		ptPK.setPatologia(patologiaTratamiento.getPatologia().getId());
		ptPK.setTratamiento(patologiaTratamiento.getTratamiento().getId());
		PatologiaTratamiento a = buscar(ptPK);
		//no existe, se puede crear...
		if(a==null){
			em.persist(patologiaTratamiento);
		}else{
			throw new ExcepcionNegocio("Esta patologia ya tiene asignado este tratamiento");
		}
		
	}
	
	/**
	 * MEtodo para buscar una patologia Tratamiento
	 * @param id de la patologiaTratamiento
	 * @return la patologia Tratamiento.
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public PatologiaTratamiento buscar(PatologiaTratamientoPK ptPK){
		PatologiaTratamiento patologiaTratamiento = em.find(PatologiaTratamiento.class, ptPK);
		if(patologiaTratamiento!=null){
			return patologiaTratamiento;
		}
		return null;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminar(PatologiaTratamientoDTO dto) {
		PatologiaTratamientoPK ptPK = new PatologiaTratamientoPK();
		ptPK.setPatologia(dto.getIdPatologia());
		ptPK.setTratamiento(dto.getIdTratamiento());
		PatologiaTratamiento a = buscar(ptPK);;
		if(a!=null){
			em.remove(a);
		} else{
			throw new ExcepcionNegocio("La cama no esta registrada");
		}	
	}
	
}
