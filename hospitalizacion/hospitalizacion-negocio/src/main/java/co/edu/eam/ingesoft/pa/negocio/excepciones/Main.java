package co.edu.eam.ingesoft.pa.negocio.excepciones;

import java.util.List;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.edu.eam.ingesoft.pa.negocio.beans.TratamientoEJB;


public class Main {

	@EJB
	private static TratamientoEJB ejb;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	//	ejb.listarCamas();
		
//			
//			Query q = em.createNativeQuery("SELECT c.id,c.disponible,c.descripcion FROM CAMA c");
//			List<Object[]> authors = q.getResultList();
//
//			for (Object[] a : authors) {
//			    System.out.println("Cama "
//			            + a[0]
//			            + " "
//			            + a[1]);
//			}
//			ArrayList<Cama> listar = new ArrayList();
//			String sql = "";
//			PreparedStatement pstmt = con.prepareStatement(sql);
//			// ejecutar consulta
//			ResultSet res = pstmt.executeQuery();
//			// Hubo un registro...
//			while (res.next()) {
//				int id = res.getInt(1);
//				boolean dis = res.getBoolean(2);
//				String desc = res.getString(3);
//				Cama c = new Cama();
//				c.setId(id);
//				c.setDisponible(dis);
//				c.setDescripcion(desc);
//				System.out.println(id+" ASJKDJASDJ");
//				System.out.println(dis+" asdasd");
//				System.out.println(desc+" ASJKDJASDJ");
//				listar.add(c);

	}
}
