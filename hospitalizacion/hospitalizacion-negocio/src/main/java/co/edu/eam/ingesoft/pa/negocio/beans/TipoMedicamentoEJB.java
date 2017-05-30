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
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.TipoMedicamento;

@LocalBean
@Stateless
public class TipoMedicamentoEJB {

	@PersistenceContext
	private EntityManager em;
	
	
	/**
	 * metodo para listar los tipo de medicos
	 */
	public List<TipoMedicamento> listarTipoMedicamentos(){	
		Query query = em.createNamedQuery(TipoMedicamento.CONSULTA_LISTAR_TIPO_MEDICAMENTO);
		List<TipoMedicamento> lista = query.getResultList();
		return lista;
	}
	
	/**
	 * metodo para buscar un departamento
	 * @param numId, numero de identificacion del departamento a buscar
	 * @return el departamento con el respectivo id, o null si no se encuentra el departamento
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public TipoMedicamento buscar(int numId){
		return em.find(TipoMedicamento.class, numId);
	}
}
