package co.edu.eam.ingesoft.pa.negocio.beans;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.TipoMedicamento;

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
}
