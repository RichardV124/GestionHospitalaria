package co.edu.eam.ingesoft.pa.hospitalizacion.web.seguridad;

import java.io.IOException;

import javax.inject.Inject;


//@WebFilter(urlPatterns="/paginas/seguro/*")
//public class FiltroSesion implements Filter {
//
//	@Inject
//	private SessionController sesion;
//	
//	public void destroy() {
//		// TODO Auto-generated method stub
//		
//	}
//
//	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain)
//			throws IOException, ServletException {
//		System.out.println("Filtrando peticion!!!!!");
//		
//		HttpServletRequest req=(HttpServletRequest) arg0;
//		HttpServletResponse res=(HttpServletResponse) arg1;
//		
//		Usuario usuario=sesion.getUsuario();
//		System.out.println("usuario:"+usuario);
//		
//		if(usuario!=null){
//			chain.doFilter(arg0, arg1);
//		}else{
//			res.sendRedirect(req.getContextPath()+"/paginas/publico/login.xhtml");
//		}
//		
//		
//	}
//
//	public void init(FilterConfig arg0) throws ServletException {
//		// TODO Auto-generated method stub
//		
//	}
//
//}