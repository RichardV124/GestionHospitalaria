package co.edu.eam.ingesoft.pa.negocio.beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Acceso;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Rol;
import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;

@LocalBean
@Stateless
public class RolEJB {

	@PersistenceContext
	private EntityManager em;

	/**
	 * metodo para listar los bancos registrados
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Rol> listarRoles() {
		Query query = em.createNamedQuery(Rol.CONSULTA_LISTAR_ROLES);
		List<Rol> roles = query.getResultList();
		if (roles.isEmpty()) {
			throw new ExcepcionNegocio("No hay roles registrados en la base de datos");
		} else {
			return roles;
		}
	}
	
//	@TransactionAttribute(TransactionAttributeType.REQUIRED)
//	public List<Rol> ListaRolesPersona(int identificacion){
//		Query q = em.createNamedQuery(Persona.ROLES_PERSONA);
//		q.setParameter(1, identificacion);
//		List<Rol> rolesPersona = q.getResultList();
//		return rolesPersona;
//	}
//	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Rol buscarRol (int id){
		return em.find(Rol.class, id);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Acceso> ListaAccesosRol(Rol rol){
		Query q = em.createNamedQuery(Acceso.LISTA_ACCESOS_ROL);
		q.setParameter(1, rol);
		List<Acceso> accesosRoles = q.getResultList();
		return accesosRoles;
	}
}
