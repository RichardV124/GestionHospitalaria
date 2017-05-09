package co.edu.eam.ingesoft.pa.negocio.beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.TipoMedico;

@LocalBean
@Stateless
public class TipoMedicoEJB {

	@PersistenceContext
	private EntityManager em;
	
	
	/**
	 * metodo para listar los tipo de medicos
	 */
	public List<TipoMedico> listarTipoMedicos(){	
		Query query = em.createNamedQuery(TipoMedico.CONSULTA_LISTAR_TIPO_MEDICO);
		List<TipoMedico> lista = query.getResultList();
		return lista;
	}
}
