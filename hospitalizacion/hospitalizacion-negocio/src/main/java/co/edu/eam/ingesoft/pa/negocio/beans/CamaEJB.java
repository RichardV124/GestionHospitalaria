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
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Paciente;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Quirofano;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.TipoMedico;
import co.edu.eam.ingesoft.pa.negocio.dtos.CamaDTO;
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
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<CamaDTO> listarCamas() {
		
		Query q = em.createNativeQuery("SELECT c.id,c.descripcion,c.piso,c.habitacion,c.disponible FROM CAMA c");
		
		
		List<CamaDTO> lista = new ArrayList<CamaDTO>();
		List<Object[]> camas = q.getResultList();
		
		for (Object[] a : camas) {

			CamaDTO dto = new CamaDTO();
			dto.setId(Integer.parseInt(a[0].toString()));
			dto.setDescripcion(a[1].toString());
			dto.setPiso(a[2].toString());
			dto.setHabitacion(a[3].toString());
			dto.setOcupadoPor(a[4].toString());
			lista.add(dto);
	}

		return lista;
	}
	
	/**
	 * metodo para eliminar una cama registrada
	 * @param id, id de la cama a eliminar
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminar(int id) {
		Cama c = buscarCama(id);
		if(c!=null){
			em.remove(c);
		} else{
			throw new ExcepcionNegocio("La cama no esta registrada");
		}	
	}
	
	
	/**
	 * metodo para la edicion de una cama registrada
	 * 
	 * @param c
	 *            Cama a editar
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void editar(Cama c) {
		Cama cam = buscarCama(c.getId());
		// no existe,no se puede editar...
		if (cam != null) {
			em.merge(c);
		} else {
			throw new ExcepcionNegocio("No existe la cama a editar");
		}
	}
	
}
