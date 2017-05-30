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

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Patologia;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.PatologiaTratamiento;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Tratamiento;
import co.edu.eam.ingesoft.pa.negocio.dtos.CamaDTO;
import co.edu.eam.ingesoft.pa.negocio.dtos.MedicamentoEntregadoDTO;
import co.edu.eam.ingesoft.pa.negocio.dtos.PatologiaHistorialDTO;
import co.edu.eam.ingesoft.pa.negocio.dtos.PatologiaTratamientoDTO;
import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;

@LocalBean
@Stateless
public class PatologiaEJB {

	@PersistenceContext
	private EntityManager em;

	/**
	 * Metodo para crear un sintoma
	 * 
	 * @param sintoma
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void crearPatologia(Patologia patologia) {
		Patologia busc = buscarPatologia(patologia.getId());
		// no existe, se puede crear...
		if (busc == null) {
			em.persist(patologia);
		} else {
			throw new ExcepcionNegocio("Ya existe el sintoma");
		}

	}

	/**
	 * MEtodo para buscar una patologia
	 * 
	 * @param id
	 *            de la patologia
	 * @return la patologia.
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Patologia buscarPatologia(int id) {
		Patologia sintoma = em.find(Patologia.class, id);
		if (sintoma != null) {
			return sintoma;
		}
		return null;
	}

	/**
	 * metodo para listar las patologias
	 */
	public List<Patologia> listarPatologias() {
		Query query = em.createNamedQuery(Patologia.CONSULTA_LISTAR_PATOLOGIAS);
		List<Patologia> cus = query.getResultList();
		return cus;
	}

	/**
	 * metodo para la edicion de una patologia registrada
	 * 
	 * @param t
	 *            patologia a editar
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void editar(Patologia t) {
		Patologia pat = buscarPatologia(t.getId());
		// no existe,no se puede editar...
		if (pat != null) {
			em.merge(t);
		} else {
			throw new ExcepcionNegocio("No existe la patologia a editar");
		}
	}

	/**
	 * metodo para listar las patologias con sus tratamientos
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<PatologiaTratamientoDTO> listarTratamientosPatologia() {

		Query q = em.createNativeQuery("SELECT p.ID,p.NOMBRE,t.ID,t.DESCRIPCION FROM PATOLOGIA_TRATAMIENTO pt "
				+ "JOIN PATOLOGIA p ON pt.PATOLOGIA_ID=p.ID JOIN TRATAMIENTO t ON pt.TRATAMIENTO_ID=t.ID;");

		List<PatologiaTratamientoDTO> lista = new ArrayList<PatologiaTratamientoDTO>();
		List<Object[]> camas = q.getResultList();

		for (Object[] a : camas) {

			PatologiaTratamientoDTO dto = new PatologiaTratamientoDTO();
			dto.setIdPatologia(Integer.parseInt(a[0].toString()));
			dto.setPatologia(a[1].toString());
			dto.setIdTratamiento(Integer.parseInt(a[2].toString()));
			dto.setTratamiento(a[3].toString());
			lista.add(dto);
		}

		return lista;
	}
	
	/**
	 * metodo para listar las patologias presentadas en la cita
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<PatologiaHistorialDTO> listarPatologiasHistorial(int cita) {

		Query q = em.createNativeQuery("SELECT p.NOMBRE,p.SINTOMAS FROM CITA_PATOLOGIA_TRATAMIENTO cpt "
				+ "JOIN PATOLOGIA_TRATAMIENTO pt ON cpt.PATO_TRATA_PATO_ID=pt.PATOLOGIA_ID "
				+ "JOIN PATOLOGIA p ON p.ID=pt.PATOLOGIA_ID WHERE cpt.CITA_ID=?1;");
		q.setParameter(1, cita);

		List<PatologiaHistorialDTO> lista = new ArrayList<PatologiaHistorialDTO>();
		List<Object[]> patologias = q.getResultList();

		for (Object[] a : patologias) {

			PatologiaHistorialDTO dto = new PatologiaHistorialDTO();
			dto.setNombre(a[0].toString());
			dto.setSintomas(a[1].toString());
			lista.add(dto);
		}

		return lista;
	}

}
