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
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Municipio;
import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;

@Stateless
@LocalBean
public class MunicipioEJB {

	@PersistenceContext
	private EntityManager em;

	/**
	 * metodo para listar los municipios registrados
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Municipio> listarMunicipio() {
		Query query = em.createNamedQuery(Municipio.CONSULTA_LISTAR_MUNICIPIOS);
		List<Municipio> mun = query.getResultList();
		if (mun.isEmpty()) {
			throw new ExcepcionNegocio("No hay municipios registrados en la base de datos");
		} else {
			return mun;
		}
	}
}
