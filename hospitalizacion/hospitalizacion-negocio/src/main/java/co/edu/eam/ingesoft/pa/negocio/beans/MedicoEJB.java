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
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Medico;
import co.edu.eam.ingesoft.pa.negocio.dtos.HorarioMedicoDTO;
import co.edu.eam.ingesoft.pa.negocio.dtos.MedicoDisponibleDTO;
import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;

@LocalBean
@Stateless
public class MedicoEJB {

	@PersistenceContext
	private EntityManager em;

	/**
	 * metodo para buscar un medico
	 * 
	 * @param numId,
	 *            numero de identificacion del medico a buscar
	 * @return el medico con el respectivo id, o null si no se encuenra un
	 *         medico
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Medico buscar(int numId) {
		return em.find(Medico.class, numId);
	}

	/**
	 * metodo para el registro de un Medico
	 * 
	 * @param m
	 *            Medico a registrar
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void crear(Medico m) {
		Medico me = buscar(m.getCedula());
		if (me == null) {
			em.persist(m);
		} else {
			throw new ExcepcionNegocio("El medico ya está registrado");
		}
	}

	/**
	 * metodo para eliminar un medico registrado
	 * 
	 * @param numId,
	 *            numero de identificacion del medico a eliminar
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminar(int numId) {
		Medico p = buscar(numId);
		if (p != null) {
			em.remove(p);
		} else {
			throw new ExcepcionNegocio("El usuario no está registrado");
		}
	}

	/**
	 * metodo para la edicion d eun medico registrado
	 * 
	 * @param m
	 *            Medico a editar
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void editar(Medico m) {
		Medico me = buscar(m.getCedula());
		// no existe,no se puede editar...
		if (me != null) {
			em.merge(m);
		} else {
			throw new ExcepcionNegocio("No existe el paciente a editar");
		}
	}
	
	/**
	 * metodo para listar los medicos registrados
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Medico> listarMedicos() {
		Query query = em.createNamedQuery(Medico.CONSULTA_LISTAR_MEDICOS);
		List<Medico> med = query.getResultList();
		if (med.isEmpty()) {
			throw new ExcepcionNegocio("No hay medicos registrados en la base de datos");
		} else {
			return med;
		}
	}
	
	/**
	 * metodo para listar los medicos disponibles
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<MedicoDisponibleDTO> listarMedicosDisponibles() {
		Query query = em.createNativeQuery("SELECT M.CEDULA, M.NOMBRE FROM HORARIO_MEDICO HM "
				+ "INNER JOIN MEDICO M ON HM.MEDICO_CEDULA=M.CEDULA WHERE HM.DISPONIBLE=1;");
		List<MedicoDisponibleDTO> md = query.getResultList();
		if (md.isEmpty()) {
			throw new ExcepcionNegocio("No hay ningun medico disponible");
		} else {
			return md;
		}
	}

}
