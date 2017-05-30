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
import co.edu.eam.ingesoft.pa.negocio.dtos.CirugiaHistorialDTO;
import co.edu.eam.ingesoft.pa.negocio.dtos.MedicamentoEntregadoDTO;
import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;


@LocalBean
@Stateless
public class CirugiaEJB {
	
	@PersistenceContext
	private EntityManager em;
	
	
	/**
	 * Metodo para crear una cirugia	
	 * @param cirugia
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void buscarCirugia(Cirugia cirugia){
		Cirugia busc=buscarCirugia(cirugia.getId());
		//no existe, se puede crear...
		if(busc==null){
			em.persist(cirugia);
		}else{
			throw new ExcepcionNegocio("Ya existe la cama");
		}
		
	}
	
	/**
	 * MEtodo para buscar una cirugia.
	 * @param id de la cirugia
	 * @return la cirugia.
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Cirugia buscarCirugia(int id){
		Cirugia cirugia = em.find(Cirugia.class, id);
		if(cirugia!=null){
			return cirugia;
		}
		return null;
	}
	
	/**
	 * metodo para listar las cirugia
	 */
	public List<Cirugia> listarCirugia(){	
		Query query = em.createNamedQuery(Cirugia.CONSULTA_LISTAR_CIRUGIAS);
		List<Cirugia> lista = query.getResultList();
		return lista;
	}

	/**
	 * metodo para listar las cirugias realizadas por medio de la cita
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<CirugiaHistorialDTO> listarCirugiasHistorial(int cita) {

		Query q = em.createNativeQuery("SELECT c.DESCRIPCION,c.TIEMPO_ESTIMADO,cc.FECHA FROM CITA_CIRUGIA cc "
				+ "JOIN CIRUGIA c ON c.ID=cc.CIRUGIA_ID WHERE cc.CITA_ID=1?;");
		q.setParameter(1, cita);

		List<CirugiaHistorialDTO> lista = new ArrayList<CirugiaHistorialDTO>();
		List<Object[]> cirugias = q.getResultList();

		for (Object[] a : cirugias) {

			CirugiaHistorialDTO dto = new CirugiaHistorialDTO();
			dto.setDescripcion(a[0].toString());
			dto.setDuracion(a[1].toString());
			dto.setFecha(a[2].toString());
			lista.add(dto);
		}

		return lista;
	}
	
}
