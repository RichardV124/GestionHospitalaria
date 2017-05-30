package co.edu.eam.ingesoft.pa.negocio.beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.edu.eam.ingesoft.pa.negocio.dtos.MedicamentoRecetadoDTO;
import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;

@LocalBean
@Stateless
public class MedicamentoRecetadoEJB {

	@PersistenceContext
	private EntityManager em;
	
	/**
	 * metodo para listar las citas pendientes del paciente
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<MedicamentoRecetadoDTO> listarMedicamentosRecetados(int idCita) {
		Query query = em.createNativeQuery("SELECT MR.MEDICAMENTO_ID ID, M.DESCRIPCION NOMBRE, MR.CANTIDAD_RECETADA, MR.DOSIS "
				+ "FROM MEDICAMENTO_RECETADOS MR INNER JOIN MEDICAMENTO M ON M.ID=MR.MEDICAMENTO_ID WHERE MR.CITA_ID=?1");
		query.setParameter(1, idCita);
		List<MedicamentoRecetadoDTO> cp = query.getResultList();
		if (cp.isEmpty()) {
			throw new ExcepcionNegocio("No medicamentos recetados");
		} else {
			return cp;
		}
	}

}
