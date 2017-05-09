package co.edu.eam.ingesoft.pa.negocio.beans;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Paciente;
import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;

@LocalBean
@Stateless
public class PacienteEJB {
	
	@PersistenceContext
	private EntityManager em;
	
	/**
	 * metodo para buscar un paciente
	 * @param numId, numero de identificacion del paciente a buscar
	 * @return el paciente con el respectivo id, o null si no se encuenra un paciente
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Paciente buscar(int numId){
		return em.find(Paciente.class, numId);
	}
	
	/**
	 * metodo para el registro de un paciente
	 * @param p Paciente a registrar
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void crear(Paciente p){
		Paciente pa = buscar(p.getNumIdentificacion());
		if(pa==null){
			em.persist(p);
		} else{
			throw new ExcepcionNegocio("El paciente ya está registrado");
		}	
	}
	
	/**
	 * metodo para eliminar un paciente registrado
	 * @param numId, numero de identificacion del paciente a eliminar
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminar(int numId) {
		Paciente p = buscar(numId);
		if(p!=null){
			em.remove(p);
		} else{
			throw new ExcepcionNegocio("El usuario no está registrado");
		}	
	}
	
	/**
	 * metodo para la edicion d eun paciente registrado
	 * @param p Paciente a editar
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void editar(Paciente p) {
		Paciente pa = buscar(p.getNumIdentificacion());
		// no existe,no se puede editar...
		if (pa != null) {
			em.merge(p);
		} else {
			throw new ExcepcionNegocio("No existe el paciente a editar");
		}
	}
	

}
