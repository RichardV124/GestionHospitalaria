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

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Cita;
import co.edu.eam.ingesoft.pa.negocio.dtos.CitaHistorialDTO;
import co.edu.eam.ingesoft.pa.negocio.dtos.ExamenesCitaDTO;
import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;

@LocalBean
@Stateless
public class CitaEJB {

	@PersistenceContext
	private EntityManager em;

	/**
	 * metodo para buscar una cita medica
	 * 
	 * @param id
	 *            de la cita medica
	 * @return
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Cita buscar(int id) {
		return em.find(Cita.class, id);
	}

	/**
	 * metodo para registrar una cita medica
	 * 
	 * @param c,
	 *            cita medica a registrar
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void crear(Cita c) {
		Cita cm = buscar(c.getId());
		if (cm == null) {
			em.persist(c);
		} else {
			throw new ExcepcionNegocio("La cita médica ya se ha registrado");
		}
	}

	/**
	 * metodo para editar una cita medica registrada
	 * 
	 * @param c,
	 *            cita medica a editar
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void editar(Cita c) {
		Cita cm = buscar(c.getId());
		// no existe,no se puede editar...
		if (cm != null) {
			em.merge(c);
		} else {
			throw new ExcepcionNegocio("No existe la cita médica a editar");
		}
	}

	/**
	 * metodo para eliminar una cita medica asignada
	 * 
	 * @param id,
	 *            identificador de la cita medica
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminar(int id) {
		Cita c = buscar(id);
		if (c != null) {
			em.remove(c);
		} else {
			throw new ExcepcionNegocio("La Cita medica no está registrado");
		}
	}

	/**
	 * metodo para listar las citas que ha tenido el paciente
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<CitaHistorialDTO> listarCitaHistorial(int paciente) {

		Query q = em.createNativeQuery("SELECT p.NOMBRE,h.FECHA,h.HORA_FINAL,m.NOMBRE,c.ANOTACIONES,c.ID FROM CITA c"
				+ " JOIN PACIENTE p ON c.PACIENTE_CEDULA=p.CEDULA JOIN HORARIO h ON h.ID=c.HORARIO_MEDICO_HORARIO_ID "
				+ "JOIN MEDICO m ON m.CEDULA=c.HORARIO_MEDICO_MEDICO_CEDULA WHERE c.PACIENTE_CEDULA=?1;");
		q.setParameter(1, paciente);

		List<CitaHistorialDTO> lista = new ArrayList<CitaHistorialDTO>();
		List<Object[]> citas = q.getResultList();

		if (!citas.isEmpty()) {
			for (Object[] a : citas) {

				CitaHistorialDTO dto = new CitaHistorialDTO();
				dto.setNombrePaciente(a[0].toString());
				dto.setFecha(a[1].toString());
				dto.setHora(Integer.parseInt(a[2].toString()));
				dto.setNombreMedico(a[3].toString());
				dto.setAnotaciones(a[4].toString());
				dto.setIdCita(a[5].toString());
				lista.add(dto);
			}

			return lista;
		} else {
			throw new ExcepcionNegocio("Este paciente no cuenta con historial de citas");
		}
	}

}
