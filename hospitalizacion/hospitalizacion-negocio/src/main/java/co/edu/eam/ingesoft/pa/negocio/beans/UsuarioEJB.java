package co.edu.eam.ingesoft.pa.negocio.beans;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Paciente;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Usuario;
import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;

@LocalBean
@Stateless
public class UsuarioEJB {

	@PersistenceContext
	private EntityManager em;
	
	/**
	 * metodo para buscar un usuario registrado
	 * @param usuario
	 * @return el usuario con el respectivo usuario, o null si no se encuenra un usuario
	 */
	public Usuario buscar(String usuario){
		return em.find(Usuario.class, usuario);
	}
	
	/**
	 * metodo para persistir un usuario
	 * @param u, usuario a persistir
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void crear(Usuario u){
		Usuario usu = buscar(u.getUser());
		if(usu==null){
			em.persist(u);
		} else{
			throw new ExcepcionNegocio("El usuario ya está registrado");
		}	
	}
	
	/**
	 * metodo para eliminar un usuario
	 * @param usuario, user del usuario a eliminar
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminar(String usuario) {
		Usuario usu = buscar(usuario);
		if(usu!=null){
			em.remove(usu);
		} else{
			throw new ExcepcionNegocio("El usuario no está registrado");
		}	
	}
	
	/**
	 * metodo para editar un usuario registrado
	 * @param u, usuario a editar
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void editar(Usuario u) {
		Usuario usu = buscar(u.getUser());
		// no existe,no se puede editar...
		if (usu != null) {
			em.merge(u);
		} else {
			throw new ExcepcionNegocio("No existe el usuario a editar");
		}
	}
	
}
