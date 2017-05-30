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

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Cirugia;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Medico;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Quirofano;
import co.edu.eam.ingesoft.pa.negocio.dtos.MedicamentoEntregadoDTO;
import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;

@LocalBean
@Stateless
public class QuirofanoEJB {

	@PersistenceContext
	private EntityManager em;

	/**
	 * metodo para buscar un Quirofano
	 *
	 * @param id del quirofano a buscar
	 * @return el quirofano con el respectivo id, o null si no se encuenra un
	 *         quirofano
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Quirofano buscar(int id) {
		return em.find(Quirofano.class, id);
	}

	/**
	 * metodo para el registro de un Quirofano
	 * 
	 * @param m
	 *            Quirofano a registrar
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void crear(Quirofano q) {
		Quirofano qui = buscar(q.getId());
		if (qui == null) {
			em.persist(q);
		} else {
			throw new ExcepcionNegocio("El quirofano ya está registrado");
		}
	}

	/**
	 * metodo para eliminar un Quirofano registrado
	 * 
	 * @param id del Quirofano a eliminar
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminar(int numId) {
		Quirofano q = buscar(numId);
		if (q != null) {
			em.remove(q);
		} else {
			throw new ExcepcionNegocio("El Quirofano no está registrado");
		}
	}

	/**
	 * metodo para la edicion d eun Quirofano registrado
	 * 
	 * @param q
	 *            Quirofano a editar
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void editar(Quirofano q) {
		Quirofano qui = buscar(q.getId());
		// no existe,no se puede editar...
		if (qui != null) {
			em.merge(q);
		} else {
			throw new ExcepcionNegocio("No existe el Quirofano a editar");
		}
	}
	
	/**
	 * metodo para listar los quirofanos
	 */
	public List<Quirofano> listaQuirofano(){	
		Query query = em.createNamedQuery(Quirofano.CONSULTA_LISTAR_QUIROFANOS);
		List<Quirofano> lista = query.getResultList();
		return lista;
	}
	

}
