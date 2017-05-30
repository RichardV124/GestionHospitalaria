package co.edu.eam.ingesoft.pa.negocio.beans;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Cita;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.HorarioMedico;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.HorarioMedicoPK;
import co.edu.eam.ingesoft.pa.negocio.dtos.CitaPendienteDTO;
import co.edu.eam.ingesoft.pa.negocio.dtos.CitasDiaDTO;
import co.edu.eam.ingesoft.pa.negocio.dtos.FarmaciaMedicamentoDTO;
import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;

@LocalBean
@Stateless
public class CitaEJB {

	@EJB
	private HorarioMedicoEJB horarioMedicoEJB;
	
	@PersistenceContext
	private EntityManager em;
	
	/**
	 * metodo para buscar una cita medica
	 * @param id de la cita medica
	 * @return
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Cita buscar(int id) {
		return em.find(Cita.class, id);
	}
	
	/**
	 * metodo para registrar una cita medica
	 * @param c, cita medica a registrar
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void crear(Cita c){
		Cita cm = buscar(c.getId());
		if(cm==null){
			em.persist(c);
			editarHorario(c);
		} else{
			throw new ExcepcionNegocio("La cita médica ya se ha registrado");
		}	
	}
	
	public void editarHorario(Cita c){
		HorarioMedicoPK hmPK = new HorarioMedicoPK();
		hmPK.setHorario(c.getHorarioMedico().getHorario().getId());
		hmPK.setMedico(c.getHorarioMedico().getMedico().getCedula());
		HorarioMedico hm = horarioMedicoEJB.buscar(hmPK);
		hm.setDisponible(false);
		horarioMedicoEJB.editar(hm);
	}
	
	/**
	 * metodo para editar una cita medica registrada
	 * @param c, cita medica a editar
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
	 * @param id, identificador de la cita medica
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminar(int id) {
		Cita c = buscar(id);
		if(c!=null){
			em.remove(c);
		} else{
			throw new ExcepcionNegocio("La Cita medica no está registrado");
		}	
	}
	
	/**
	 * metodo para listar las citas pendientes del paciente
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<CitaPendienteDTO> listarCitasPendientes(int cedPac) {
		Query query = em.createNativeQuery("SELECT H.FECHA, H.HORA_INICIAL, H.HORA_FINAL, M.NOMBRE MEDICO, CA.DESCRIPCION FROM CITA C " 
				+"INNER JOIN HORARIO_MEDICO HM ON C.HORARIO_MEDICO_HORARIO_ID=HM.HORARIO_ID "
				+"INNER JOIN HORARIO H ON H.ID=HM.HORARIO_ID "
				+"INNER JOIN CARACTER CA ON C.CARACTER_ID=CA.ID "
				+"INNER JOIN MEDICO M ON HM.MEDICO_CEDULA=M.CEDULA "
				+"WHERE C.PACIENTE_CEDULA=?1");
		query.setParameter(1, cedPac);
		List<CitaPendienteDTO> cp = query.getResultList();
		if (cp.isEmpty()) {
			throw new ExcepcionNegocio("No hay citas pendientes");
		} else {
			return cp;
		}
	}
	
	/**
	 * metodo para listar el inventario
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<CitasDiaDTO> listarCitasDia(int cedMed) {
		Query query = em.createNativeQuery("SELECT C.ID, case when C.ATENDIDO=0 then 'SIN ATENDER' else 'ATENDIDA' end ESTADO, H.HORA_INICIAL, H.HORA_FINAL, P.NOMBRE PACIENTE, P.CEDULA FROM CITA C "
				+"INNER JOIN HORARIO_MEDICO HM ON C.HORARIO_MEDICO_HORARIO_ID=HM.HORARIO_ID "
				+"INNER JOIN HORARIO H ON H.ID=HM.HORARIO_ID "
				+"INNER JOIN PACIENTE P ON P.CEDULA=C.PACIENTE_CEDULA "
				+"WHERE HM.MEDICO_CEDULA=?1 AND TO_CHAR(SYSDATE, 'yyyy')=TO_CHAR(H.FECHA, 'yyyy') AND TO_CHAR(SYSDATE, 'mm')=TO_CHAR(H.FECHA, 'mm') " 
				+"AND TO_CHAR(SYSDATE, 'dd')=TO_CHAR(H.FECHA, 'dd') ORDER BY ESTADO DESC");
		query.setParameter(1, cedMed);
		List<CitasDiaDTO> cp = query.getResultList();
		if (cp.isEmpty()) {
			throw new ExcepcionNegocio("No hay citas pentientes para hoy");
		} else {
			return cp;
		}
	}
}
