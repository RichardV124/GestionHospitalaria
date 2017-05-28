package co.edu.eam.ingesoft.pa.negocio.beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Departamento;
import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;

@LocalBean
@Stateless
public class DepartamentoEJB {

	@PersistenceContext
	private EntityManager em;
	
	/**
	 * metodo para listar los bancos registrados
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Departamento> listarDepartamento() {
		Query query = em.createNamedQuery(Departamento.CONSULTA_LISTAR_DEPARTAMENTOS);
		List<Departamento> dep = query.getResultList();
		if (dep.isEmpty()) {
			throw new ExcepcionNegocio("No hay departamentos registrados en la base de datos");
		} else {
			return dep;
		}
	}
	
	/**
	 * metodo para buscar un departamento
	 * @param numId, numero de identificacion del departamento a buscar
	 * @return el departamento con el respectivo id, o null si no se encuentra el departamento
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Departamento buscar(int numId){
		return em.find(Departamento.class, numId);
	}
}
