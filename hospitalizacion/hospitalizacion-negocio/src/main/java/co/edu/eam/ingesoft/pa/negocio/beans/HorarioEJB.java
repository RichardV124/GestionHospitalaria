package co.edu.eam.ingesoft.pa.negocio.beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Horario;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Medico;
import co.edu.eam.ingesoft.pa.negocio.dtos.MedicoDisponibleDTO;
import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;

@LocalBean
@Stateless
public class HorarioEJB {

	
	@PersistenceContext
	private EntityManager em;
	
	/**
	 * metodo para buscar un horario
	 * @param id, identificador del horario a buscar
	 * @return el horario con el respectivo id, o null de no encontrarlo
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Horario buscar(int id){
		return em.find(Horario.class, id);
	}
	
	/**
	 * metodo para persistir un horario
	 * @param h, horario a persistir
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void crear(Horario h){
		Horario ho = buscar(h.getId());
		if(ho==null){
			em.persist(h);
		} else{
			throw new ExcepcionNegocio("El horario ya está registrado");
		}	
	}
	
	/**
	 * metodo para eliminar un horario registrado
	 * @param id, identificador del horario a eliminar
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminar(int id) {
		Horario h = buscar(id);
		if(h!=null){
			em.remove(h);
		} else{
			throw new ExcepcionNegocio("El horario no está registrado");
		}	
	}
	
	/**
	 * metodo para editar un horario
	 * @param h, horario a editar
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void editar(Horario h) {
		Horario ho = buscar(h.getId());
		// no existe,no se puede editar...
		if (ho != null) {
			em.merge(h);
		} else {
			throw new ExcepcionNegocio("No existe el horario a editar");
		}
	}
	
	/**
	 * metodo para listar los horarios registrados
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Horario> listarHorarios() {
		Query query = em.createNamedQuery(Horario.CONSULTA_LISTAR_HORARIOS);
		List<Horario> hor = query.getResultList();
		if (hor.isEmpty()) {
			throw new ExcepcionNegocio("No hay horarios registrados en la base de datos");
		} else {
			return hor;
		}
	}
	
	/**
	 * metodo para listar los horarios del medico disponible
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Horario> listarHorariosMedicoDisponible(int cedMed) {
		Query query = em.createNativeQuery("SELECT H.ID, H.FECHA, H.HORA_INICIAL, H.HORA_FINAL FROM HORARIO_MEDICO HM "
				+ "INNER JOIN HORARIO H ON HM.HORARIO_ID=H.ID WHERE HM.MEDICO_CEDULA=?1 AND HM.DISPONIBLE=1");
		query.setParameter(1, cedMed);
		List<Horario> hmd = query.getResultList();
		if (hmd.isEmpty()) {
			throw new ExcepcionNegocio("El medico no tiene horarios disponibles");
		} else {
			return hmd;
		}
	}
}
