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

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.ExamenesCita;
import co.edu.eam.ingesoft.pa.negocio.dtos.ExamenesCitaDTO;
import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;


@LocalBean
@Stateless
public class ExamenCitaEJB {

	@PersistenceContext
	private EntityManager em;
	
	
	/**
	 * Metodo para asignar un examen en una cita
	 * @param examen tomado en la cita
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void crearExamenCita(ExamenesCita exCita){
		ExamenesCita busc= buscarExamenCita(exCita.getId());
		//no existe, se puede crear...
		if(busc==null){
			em.persist(exCita);
		}else{
			throw new ExcepcionNegocio("Ya existe la cama");
		}
		
	}
	
	/**
	 * MEtodo para buscar un examen tomado en una cita
	 * @param id del examen cita
	 * @return el examen cita.
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ExamenesCita buscarExamenCita(int id){
		ExamenesCita examenCita = em.find(ExamenesCita.class, id);
		if(examenCita!=null){
			return examenCita;
		}
		return null;
	}
	
	/**
	 * metodo para listar los examen cita
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<ExamenesCitaDTO> listarExamenesCita() {
		
		Query q = em.createNativeQuery("SELECT ec.ID,ec.CITA_ID,e.NOMBRE,te.ID,te.DESCRIPCION FROM EXAMENES_CITA ec "
				+ "JOIN EXAMEN e ON e.NOMBRE=ec.EXAMEN_NOMBRE "
				+ "JOIN TIPO_EXAMEN te ON te.ID=e.TIPO_EXAMEN_ID;");
		
		
		List<ExamenesCitaDTO> lista = new ArrayList<ExamenesCitaDTO>();
		List<Object[]> examenes = q.getResultList();
		
		for (Object[] a : examenes) {

			ExamenesCitaDTO dto = new ExamenesCitaDTO();
			dto.setId(Integer.parseInt(a[0].toString()));
			dto.setIdCita(Integer.parseInt(a[0].toString()));
			dto.setNombreExamen(a[2].toString());
			dto.setIdTipoExamen(Integer.parseInt(a[3].toString()));
			dto.setDescripcionTipoExamen(a[4].toString());
			lista.add(dto);
	}

		return lista;
	}
	
	/**
	 * metodo para eliminar un examen asignado en una cita
	 * @param id, id del examen cita
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminar(int id) {
		ExamenesCita ex = buscarExamenCita(id);
		if(ex!=null){
			em.remove(ex);
		} else{
			throw new ExcepcionNegocio("El examen cita no esta registrado");
		}	
	}
	
}
