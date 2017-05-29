package co.edu.eam.ingesoft.pa.negocio.beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.CaracterCita;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Departamento;
import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;

@LocalBean
@Stateless
public class CaracterCitaEJB {

	@PersistenceContext
	private EntityManager em;
	
	/**
	 * metodo para listar el caracter de la cita
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<CaracterCita> listarCaracterCita() {
		Query query = em.createNamedQuery(CaracterCita.CONSULTA_LISTAR_CARACTER_CITA);
		List<CaracterCita> cc = query.getResultList();
		if (cc.isEmpty()) {
			throw new ExcepcionNegocio("No hay ningun tipo de caracter de cita registrado en la base de datos");
		} else {
			return cc;
		}
	}
}
