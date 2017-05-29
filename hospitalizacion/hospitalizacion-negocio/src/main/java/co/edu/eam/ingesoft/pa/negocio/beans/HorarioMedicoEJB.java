package co.edu.eam.ingesoft.pa.negocio.beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.HorarioMedico;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.HorarioMedicoPK;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Medico;
import co.edu.eam.ingesoft.pa.negocio.dtos.HorarioMedicoDTO;
import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;

@LocalBean
@Stateless
public class HorarioMedicoEJB {

	@PersistenceContext
	private EntityManager em;

	/**
	 * metodo para buscar un horario asignado
	 * 
	 * @param numId,
	 *            numero de identificacion del medico a buscar
	 * @return el medico con el respectivo id, o null si no se encuenra un
	 *         medico
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public HorarioMedico buscar(HorarioMedicoPK hmPK) {
		return em.find(HorarioMedico.class, hmPK);
	}

	/**
	 * metodo para el registro de un Medico
	 * 
	 * @param m
	 *            Medico a registrar
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void crear(HorarioMedico hm) {
		HorarioMedicoPK hmPK = new HorarioMedicoPK();
		hmPK.setHorario(hm.getHorario().getId());
		hmPK.setMedico(hm.getMedico().getCedula());
		HorarioMedico me = buscar(hmPK);
		if (me == null) {
			em.persist(hm);
		} else {
			throw new ExcepcionNegocio("El horario ya se le asignó a este medico");
		}
	}

	/**
	 * metodo para eliminar un medico registrado
	 * 
	 * @param numId,
	 *            numero de identificacion del medico a eliminar
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminar(HorarioMedicoPK hmPK) {
		HorarioMedico h = buscar(hmPK);
		if (h != null) {
			em.remove(h);
		} else {
			throw new ExcepcionNegocio("El uhorario no ha sido asignado");
		}
	}

	/**
	 * metodo para la edicion d eun medico registrado
	 * 
	 * @param m
	 *            Medico a editar
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void editar(HorarioMedico hm) {
		HorarioMedicoPK hmPK = new HorarioMedicoPK();
		hmPK.setHorario(hm.getHorario().getId());
		hmPK.setMedico(hm.getMedico().getCedula());
		HorarioMedico me = buscar(hmPK);
		// no existe,no se puede editar...
		if (me != null) {
			em.merge(hm);
		} else {
			throw new ExcepcionNegocio("No existe el horario asignado a editar");
		}
	}
	
	/**
	 * metodo para listar los medicos registrados
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<HorarioMedicoDTO> listarHorariosMedico() {
		Query query = em.createNativeQuery("SELECT HM.MEDICO_CEDULA, HM.HORARIO_ID, M.NOMBRE, H.FECHA, H.HORA_INICIAL, H.HORA_FINAL "
				+ "FROM HORARIO_MEDICO HM INNER JOIN HORARIO H ON H.ID=HM.HORARIO_ID"
				+ "INNER JOIN MEDICO M ON M.CEDULA=HM.MEDICO_CEDULA;");
		List<HorarioMedicoDTO> hms = query.getResultList();
		if (hms.isEmpty()) {
			throw new ExcepcionNegocio("No hay ningun horario asignado en la base de datos");
		} else {
			return hms;
		}
	}
}
