package co.edu.eam.ingesoft.pa.negocio.beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Eps;
import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;

@LocalBean
@Stateless
public class EpsEJB {

	@PersistenceContext
	private EntityManager em;

	/**
	 * metodo para listar los bancos registrados
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Eps> listarEps() {
		Query query = em.createNamedQuery(Eps.CONSULTA_LISTAR_EPS);
		List<Eps> eps = query.getResultList();
		if (eps.isEmpty()) {
			throw new ExcepcionNegocio("No hay eps registradas en la base de datos");
		} else {
			return eps;
		}
	}
}
