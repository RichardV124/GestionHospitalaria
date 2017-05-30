package co.edu.eam.ingesoft.pa.negocio.beans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Examen;
import co.edu.eam.ingesoft.pa.negocio.dtos.ExamenHistorialDTO;
import co.edu.eam.ingesoft.pa.negocio.dtos.MedicamentoEntregadoDTO;
import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;

public class ExamenEJB {
	
	@PersistenceContext
	private EntityManager em;
	
	
	/**
	 * Metodo para crear un examen
	 * @param cirugia
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void buscarCirugia(Examen examen){
		Examen busc=buscarExamen(examen.getNombre());
		//no existe, se puede crear...
		if(busc==null){
			em.persist(examen);
		}else{
			throw new ExcepcionNegocio("Ya existe la cama");
		}
		
	}
	
	/**
	 * MEtodo para buscar un examen.
	 * @param nombre del examen
	 * @return el examen
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Examen buscarExamen(String nombre){
		Examen examen = em.find(Examen.class, nombre);
		if(examen!=null){
			return examen;
		}
		return null;
	}
	
	/**
	 * metodo para listar los examen
	 */
	public List<Examen> listarCirugia(){	
		Query query = em.createNamedQuery(Examen.CONSULTA_LISTAR_EXAMENES);
		List<Examen> lista = query.getResultList();
		return lista;
	}

	
	/**
	 * metodo para listar los examenes realizados en la cita
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<ExamenHistorialDTO> listarExamenesHistorial(int cita) {

		Query q = em.createNativeQuery("SELECT e.NOMBRE,r.DESCRIPCION FROM EXAMENES_CITA ec "
				+ "JOIN EXAMEN e ON e.NOMBRE=ec.EXAMEN_NOMBRE JOIN RESULTADO r ON r.EXAMENES_CITA_ID=ec.ID "
				+ "WHERE ec.CITA_ID=?1;");
		q.setParameter(1, cita);

		List<ExamenHistorialDTO> lista = new ArrayList<ExamenHistorialDTO>();
		List<Object[]> medicamentos = q.getResultList();

		for (Object[] a : medicamentos) {

			ExamenHistorialDTO dto = new ExamenHistorialDTO();
			dto.setNombreExamen(a[0].toString());
			dto.setDescripcionExamen(a[1].toString());
			lista.add(dto);
		}

		return lista;
	}
	
}
