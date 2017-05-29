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

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.CitaCirugia;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.CitaCirugiaPK;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.ExamenesCita;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.PatologiaTratamiento;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.PatologiaTratamientoPK;
import co.edu.eam.ingesoft.pa.negocio.dtos.CitaCirugiaDTO;
import co.edu.eam.ingesoft.pa.negocio.dtos.ExamenesCitaDTO;
import co.edu.eam.ingesoft.pa.negocio.dtos.PatologiaTratamientoDTO;
import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;


@LocalBean
@Stateless
public class CitaCirugiaEJB {

	@PersistenceContext
	private EntityManager em;
	
	
	/**
	 * Metodo para asignar una cirugia en una cita
	 * @param cirugia tomada en la cita
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void crearCitaCirugia(CitaCirugia citaCirugia){
		CitaCirugiaPK pk= new CitaCirugiaPK();
		pk.setCirugia(citaCirugia.getCirugia().getId());
		pk.setCita(citaCirugia.getCita().getId());
		CitaCirugia busc= buscarCitaCirugia(pk);
		//no existe, se puede crear...
		if(busc==null){
			em.persist(citaCirugia);
		}else{
			throw new ExcepcionNegocio("Ya existe la cama");
		}
		
	}
	
	/**
	 * MEtodo para buscar una cirugia asignada en una cita
	 * @param id de la citacirugia
	 * @return la cita cirugia
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public CitaCirugia buscarCitaCirugia(CitaCirugiaPK pk){
		CitaCirugia citaCirugia = em.find(CitaCirugia.class, pk);
		if(citaCirugia!=null){
			return citaCirugia;
		}
		return null;
	}
	
	/**
	 * metodo para listar los examen cita
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<CitaCirugiaDTO> listarExamenesCita() {
		
		Query q = em.createNativeQuery("SELECT cc.CIRUGIA_ID,cc.CITA_ID,c.DESCRIPCION,tc.ID,tc.DESCRIPCION,q.ID,"
				+ "q.NOMBRE,m.CEDULA,m.NOMBRE FROM CITA_CIRUGIA cc JOIN CIRUGIA c ON c.ID=cc.CIRUGIA_ID "
				+ "JOIN TIPO_CIRUGIA tc ON c.TIPO_CIRUGIA_ID=tc.ID JOIN QUIROFANO q ON q.ID=cc.QUIROFANO_ID "
				+ "JOIN MEDICO m ON m.CEDULA=cc.HORARIO_MEDICO_MEDICO_CEDULA;");
		
		
		List<CitaCirugiaDTO> lista = new ArrayList<CitaCirugiaDTO>();
		List<Object[]> citasCirugias = q.getResultList();
		
		for (Object[] a : citasCirugias) {

			CitaCirugiaDTO dto = new CitaCirugiaDTO();
			dto.setCirugiaId(Integer.parseInt(a[0].toString()));
			dto.setCitaId(Integer.parseInt(a[1].toString()));
			dto.setCirugiaDescripcion(a[2].toString());
			dto.setTipoCirugiaId(Integer.parseInt(a[3].toString()));
			dto.setTipoCirugiaDescripcion(a[4].toString());
			dto.setQuirofanoId(Integer.parseInt(a[5].toString()));
			dto.setQuirofanoNombre(a[6].toString());
			dto.setCedulaMedico(Integer.parseInt(a[7].toString()));
			dto.setNombreMedico(a[8].toString());
			lista.add(dto);
	}

		return lista;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminar(CitaCirugiaDTO dto) {
		CitaCirugiaPK ccPK = new CitaCirugiaPK();
		ccPK.setCirugia(dto.getCirugiaId());
		ccPK.setCita(dto.getCitaId());
		CitaCirugia a = buscarCitaCirugia(ccPK);
		if(a!=null){
			em.remove(a);
		} else{
			throw new ExcepcionNegocio("La cita cirugia no esta registrada");
		}	
	}
	
}
